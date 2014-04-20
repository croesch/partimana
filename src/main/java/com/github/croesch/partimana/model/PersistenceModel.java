package com.github.croesch.partimana.model;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IPersistenceModel;
import com.github.croesch.partimana.settings.DataBaseSettings;
import com.github.croesch.partimana.types.*;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * The persistence model to store participants and camps.
 *
 * @author croesch
 * @since Date: May 29, 2011
 */
public final class PersistenceModel implements IPersistenceModel {

  /** logging class */
  private static final Logger LOGGER = Logger.getLogger(PersistenceModel.class);

  /** the database connection that contains all the stored objects */
  @NotNull
  private Connection con;

  /**
   * Creates the persistence model to store participants and camps. Creates a connection to the database defined by
   * {@link DataBaseSettings}.
   *
   * @since Date: Oct 13, 2012
   */
  public PersistenceModel() {
    try {
      con = DriverManager.getConnection(DataBaseSettings.DB_URL.value(),
                                        DataBaseSettings.DB_USER.value(),
                                        DataBaseSettings.DB_PASSWORD.value());
    } catch (final SQLException e) {
      LOGGER.fatal(Text.ERROR_EXCEPTION.text(e.getClass().getName()), e);
    }
  }

  @Override
  public void update(final Participant p) {
    PreparedStatement stmt = null;
    try {
      stmt = con.prepareStatement(
          "UPDATE `participants` SET lastName=?, foreName=?, gender=?, denomination=?, birth=?, "
          + "livingStreet=?, livingCity=?, livingPlz=?, postStreet=?, postCity=?, postPlz=?, "
          + "phone=?, fax=?, mobilePhone=?, phoneParents=?, mailAddress=?, countyCouncil=?, "
          + "bankCodeNumber=?, bank=?, bankAccountNumber=?, commentar=?, sinceInDb=?, " + "dateUpInDb=? WHERE id=?");
      insertParticipantIntoStatement(p, stmt);

      stmt.executeUpdate();
    } catch (final SQLException e) {
      LOGGER.fatal(Text.ERROR_EXCEPTION.text(e.getClass().getName()), e);
    } finally {
      close(stmt);
    }
  }

  /**
   * Releases the resources of the given statement.
   *
   * @param stmt releases database connections of the given statement.
   * @since Date: Oct 13, 2012
   */
  private void close(final PreparedStatement stmt) {
    if (stmt != null) {
      try {
        stmt.close();
      } catch (final SQLException e) {
        LOGGER.warn(Text.ERROR_EXCEPTION.text(e.getClass().getName()), e);
      }
    }
  }

  /**
   * Releases the resources of the given result set.
   *
   * @param rs the result set that should be closed
   * @since Date: Mar 3, 2013
   */
  private void close(final ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
      } catch (final SQLException e) {
        LOGGER.warn(Text.ERROR_EXCEPTION.text(e.getClass().getName()), e);
      }
    }
  }

  @Override
  public void update(final Camp c) {
    PreparedStatement stmt = null;
    try {
      stmt = con.prepareStatement("UPDATE `camps` SET name=?, fromDate=?, until=?, location=?, "
                                  + "ratePerParticipant=?, ratePerDayChild=?, cancelledSince=? WHERE id=?");
      insertCampIntoStatement(c, stmt);
      stmt.executeUpdate();

      updateCampParticipants(c.getParticipants(), c.getId());
    } catch (final SQLException e) {
      LOGGER.fatal(Text.ERROR_EXCEPTION.text(e.getClass().getName()), e);
    } finally {
      close(stmt);
    }
  }

  /**
   * Adds all participants to the given camp that are stored in the database.
   *
   * @param camp the camp to fill with its participants from the database
   * @since Date: Oct 14, 2012
   */
  private void updateCampParticipants(final Camp camp) {
    final Map<Long, Participant> mapOfParticipants = getMapOfParticipants();

    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = con.prepareStatement(
          "SELECT id, role, sinceInCamp, sinceNotInCamp" + " FROM `campParticipants` WHERE camp=?");
      stmt.setLong(1, camp.getId());
      rs = stmt.executeQuery();
      while (rs.next()) {
        int i = 0;
        final Participant p = mapOfParticipants.get(rs.getLong(++i));
        if (p == null) {
          // participant isn't in the database
          LOGGER.error(Text.ERROR_PARTICIPANT_NOT_IN_DB);
          continue;
        }
        final CampParticipant cp = new CampParticipant(p);
        cp.setRole(Role.valueOf(rs.getInt(++i)));
        cp.setSignedIn(getUtilDate(rs.getDate(++i)));
        cp.setSignedOff(getUtilDate(rs.getDate(++i)));

        camp.addParticipant(cp);
      }
    } catch (final SQLException e) {
      LOGGER.fatal(Text.ERROR_EXCEPTION.text(e.getClass().getName()), e);
    } finally {
      close(stmt);
      close(rs);
    }
  }

  /**
   * Updates the participants of the camp with the given id to the given list of participants.
   *
   * @param campParticipants the participants to store in the database
   * @param id               the id of the camp the participants belong to
   * @since Date: Oct 14, 2012
   */
  private void updateCampParticipants(final List<CampParticipant> campParticipants, final long id) {
    PreparedStatement stmt = null;
    try {
      stmt = con.prepareStatement("DELETE FROM `campParticipants` WHERE camp=?");
      stmt.setLong(1, id);
      stmt.executeUpdate();

      for (final CampParticipant part : campParticipants) {
        PreparedStatement s = null;
        try {
          s = con.prepareStatement(
              "INSERT INTO `campParticipants` SET id=?, camp=?," + " role=?, sinceInCamp=?, sinceNotInCamp=?");
          int i = 0;
          s.setLong(++i, part.getId());
          s.setLong(++i, id);
          s.setInt(++i, part.getRole().getId());
          s.setDate(++i, getSqlDate(part.getSignedIn()));
          s.setDate(++i, getSqlDate(part.getSignedOff()));
          s.executeUpdate();
        } finally {
          close(s);
        }
      }
    } catch (final SQLException e) {
      LOGGER.fatal(Text.ERROR_EXCEPTION.text(e.getClass().getName()), e);
    } finally {
      close(stmt);
    }
  }

  @Override
  public Map<Long, Camp> getMapOfCamps() {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = con.prepareStatement("SELECT id, name, fromDate, until, location, "
                                  + "ratePerParticipant, ratePerDayChild, cancelledSince FROM `camps` ORDER BY " +
                                  "id");
      rs = stmt.executeQuery();

      final HashMap<Long, Camp> hashMap = new HashMap<Long, Camp>();

      while (rs.next()) {
        final Camp c = new Camp(rs.getLong("id"),
                                rs.getString("name"),
                                getUtilDate(rs.getDate("fromDate")),
                                getUtilDate(rs.getDate("until")),
                                rs.getString("location"),
                                rs.getString("ratePerParticipant"));
        c.setRatePerDayChildren(rs.getString("ratePerDayChild"));
        c.setCancelDate(getUtilDate(rs.getDate("cancelledSince")));

        updateCampParticipants(c);

        hashMap.put(c.getId(), c);
      }

      return hashMap;
    } catch (final SQLException e) {
      LOGGER.fatal(Text.ERROR_EXCEPTION.text(e.getClass().getName()), e);
    } finally {
      close(stmt);
      close(rs);
    }

    return new HashMap<Long, Camp>();
  }

  @Override
  public Map<Long, Participant> getMapOfParticipants() {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = con.prepareStatement("SELECT id, lastName, foreName, gender, denomination, birth, livingStreet, "
                                  + "livingCity, livingPlz, postStreet, postCity, postPlz, "
                                  + "phone, fax, mobilePhone, phoneParents, mailAddress, countyCouncil, "
                                  + "bankCodeNumber, bank, bankAccountNumber, commentar, sinceInDb, "
                                  + "dateUpInDb FROM `participants` ORDER BY id");
      rs = stmt.executeQuery();

      final HashMap<Long, Participant> hashMap = new HashMap<Long, Participant>();

      while (rs.next()) {
        final Participant p = new Participant(rs.getLong("id"),
                                              rs.getString("lastName"),
                                              rs.getString("foreName"),
                                              Gender.of(rs.getString("gender")),
                                              Denomination.of(rs.getInt("denomination")),
                                              getUtilDate(rs.getDate("birth")),
                                              rs.getString("livingStreet"),
                                              rs.getInt("livingPlz"),
                                              rs.getString("livingCity"),
                                              CountyCouncil.of(rs.getInt("countyCouncil")));
        p.setBank(rs.getString("bank"));
        p.setBankAccountNumber(rs.getInt("bankAccountNumber"));
        p.setBankCodeNumber(rs.getInt("bankCodeNumber"));
        p.setCityPostal(rs.getString("postCity"));
        p.setComment(rs.getString("commentar"));
        p.setDateSinceInDataBase(getUtilDate(rs.getDate("sinceInDb")));
        p.setDateUpToInSystem(getUtilDate(rs.getDate("dateUpInDb")));
        p.setFax(rs.getString("fax"));
        p.setMailAddress(rs.getString("mailAddress"));
        p.setMobilePhone(rs.getString("mobilePhone"));
        p.setPhone(rs.getString("phone"));
        p.setPhoneOfParents(rs.getString("phoneParents"));
        p.setPostCodePostal(rs.getInt("postPlz"));
        p.setStreetPostal(rs.getString("postStreet"));

        hashMap.put(p.getId(), p);
      }

      return hashMap;
    } catch (final SQLException e) {
      LOGGER.fatal(Text.ERROR_EXCEPTION.text(e.getClass().getName()), e);
    } finally {
      close(stmt);
      close(rs);
    }

    return new HashMap<Long, Participant>();
  }

  @Override
  public void deleteParticipant(final long id) {
    PreparedStatement stmt = null;
    try {
      stmt = con.prepareStatement("DELETE FROM `participants` WHERE id=?");

      stmt.setLong(1, id);

      stmt.executeUpdate();
    } catch (final SQLException e) {
      LOGGER.fatal(Text.ERROR_EXCEPTION.text(e.getClass().getName()), e);
    } finally {
      close(stmt);
    }
  }

  @Override
  public void deleteCamp(final long id) {

    PreparedStatement stmt = null;
    try {
      stmt = con.prepareStatement("DELETE FROM `campParticipants` WHERE camp=?");
      stmt.setLong(1, id);

      stmt.executeUpdate();

      stmt = con.prepareStatement("DELETE FROM `camps` WHERE id=?");
      stmt.setLong(1, id);

      stmt.executeUpdate();
    } catch (final SQLException e) {
      LOGGER.fatal(Text.ERROR_EXCEPTION.text(e.getClass().getName()), e);
    } finally {
      close(stmt);
    }
  }

  @Override
  public void create(final Participant p) {
    PreparedStatement stmt = null;
    try {
      stmt = con.prepareStatement(
          "INSERT INTO `participants` SET lastName=?, foreName=?, gender=?, denomination=?, birth=?, "
          + "livingStreet=?, livingCity=?, livingPlz=?, postStreet=?, postCity=?, postPlz=?, "
          + "phone=?, fax=?, mobilePhone=?, phoneParents=?, mailAddress=?, countyCouncil=?, "
          + "bankCodeNumber=?, bank=?, bankAccountNumber=?, commentar=?, sinceInDb=?, dateUpInDb=?, " + "id=?");
      p.setDateSinceInDataBase(new java.util.Date());
      insertParticipantIntoStatement(p, stmt);

      stmt.executeUpdate();
    } catch (final SQLException e) {
      LOGGER.fatal(Text.ERROR_EXCEPTION.text(e.getClass().getName()), e);
    } finally {
      close(stmt);
    }
  }

  /**
   * Fills the given statement with the information fetched from the given camp.
   *
   * @param c    the camp that provides the attributes to store with the given statement
   * @param stmt the statement used to store the attributes of the given camp
   * @throws SQLException if the attributes couldn't be set to the statement
   * @since Date: Oct 14, 2012
   */
  private void insertCampIntoStatement(final Camp c, final PreparedStatement stmt) throws SQLException {
    int i = 0;
    stmt.setString(++i, c.getName());
    stmt.setDate(++i, getSqlDate(c.getFromDate()));
    stmt.setDate(++i, getSqlDate(c.getUntilDate()));
    stmt.setString(++i, c.getLocation());
    stmt.setString(++i, c.getRatePerParticipant());
    stmt.setString(++i, c.getRatePerDayChildren());
    stmt.setDate(++i, getSqlDate(c.getCancelDate()));
    stmt.setLong(++i, c.getId());
  }

  /**
   * Fills the given statement with the information fetched from the given participant.
   *
   * @param p    the participant that provides the attributes to store with the given statement
   * @param stmt the statement used to store the attributes of the given participant
   * @throws SQLException if the attributes couldn't be set to the statement
   * @since Date: Oct 14, 2012
   */
  private void insertParticipantIntoStatement(final Participant p, final PreparedStatement stmt) throws SQLException {
    int i = 0;
    stmt.setString(++i, p.getLastName());
    stmt.setString(++i, p.getForeName());
    stmt.setString(++i, p.getGender().getStorableString());
    stmt.setInt(++i, p.getDenomination().getStorableRepresentation());
    stmt.setDate(++i, getSqlDate(p.getBirthDate()));
    stmt.setString(++i, p.getStreet());
    stmt.setString(++i, p.getCity());
    stmt.setInt(++i, p.getPostCode());
    stmt.setString(++i, p.getStreetPostal());
    stmt.setString(++i, p.getCityPostal());
    stmt.setInt(++i, p.getPostCodePostal());
    stmt.setString(++i, p.getPhone());
    stmt.setString(++i, p.getFax());
    stmt.setString(++i, p.getMobilePhone());
    stmt.setString(++i, p.getPhoneOfParents());
    stmt.setString(++i, p.getMailAddress());
    stmt.setInt(++i, p.getCountyCouncil().getStorableRepresentation());
    stmt.setInt(++i, p.getBankCodeNumber());
    stmt.setString(++i, p.getBank());
    stmt.setInt(++i, p.getBankAccountNumber());
    stmt.setString(++i, p.getComment());
    stmt.setDate(++i, getSqlDate(p.getDateSinceInDataBase()));
    stmt.setDate(++i, getSqlDate(p.getDateUpToInSystem()));
    stmt.setLong(++i, p.getId());
  }

  /**
   * Converts the date of the util package to a sql date.
   *
   * @param date the date in util package format
   * @return the date in sql package format
   * @since Date: Oct 14, 2012
   */
  private Date getSqlDate(final java.util.Date date) {
    if (date == null) {
      return null;
    }
    return new Date(date.getTime());
  }

  /**
   * Converts the date of the sql package to a util package based date.
   *
   * @param date the date in sql package format
   * @return the date in util package format
   * @since Date: Oct 14, 2012
   */
  private java.util.Date getUtilDate(final Date date) {
    if (date == null) {
      return null;
    }
    return new java.util.Date(date.getTime());
  }

  @Override
  public void create(final Camp c) {
    PreparedStatement stmt = null;
    try {
      stmt = con.prepareStatement("INSERT INTO `camps` SET name=?, fromDate=?, until=?, location=?, "
                                  + "ratePerParticipant=?, ratePerDayChild=?, cancelledSince=?, id=?");
      insertCampIntoStatement(c, stmt);
      stmt.executeUpdate();

      updateCampParticipants(c.getParticipants(), c.getId());
    } catch (final SQLException e) {
      LOGGER.fatal(Text.ERROR_EXCEPTION.text(e.getClass().getName()), e);
    } finally {
      close(stmt);
    }
  }

  @Override
  public void close() throws IOException {
    try {
      con.close();
    } catch (final SQLException e) {
      LOGGER.fatal(Text.ERROR_EXCEPTION.text(e.getClass().getName()), e);
    }
  }
}
