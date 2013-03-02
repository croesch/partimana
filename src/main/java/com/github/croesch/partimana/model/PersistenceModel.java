package com.github.croesch.partimana.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IPersistenceModel;
import com.github.croesch.partimana.settings.DataBaseSettings;
import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.types.CampParticipant;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;

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
      this.con = DriverManager.getConnection(DataBaseSettings.DB_URL.value(), DataBaseSettings.DB_USER.value(),
                                             DataBaseSettings.DB_PASSWORD.value());
    } catch (final SQLException e) {
      LOGGER.fatal(Text.ERROR_EXCEPTION.text(e.getClass().getName()), e);
    }
  }

  @Override
  public void update(final Participant p) {
    PreparedStatement stmt = null;
    try {
      stmt = this.con.prepareStatement("UPDATE `participants` SET lastName=?, foreName=?, gender=?, denomination=?, birth=?, "
                                       + "livingStreet=?, livingCity=?, livingPlz=?, postStreet=?, postCity=?, postPlz=?, "
                                       + "phone=?, fax=?, mobilePhone=?, phoneParents=?, mailAddress=?, countyCouncil=?, "
                                       + "bankCodeNumber=?, bank=?, bankAccountNumber=?, commentar=?, sinceInDb=?, dateUpInDb=?, "
                                       + "canBeParticipant=?, canBeStaff=?, canBeStaffYouth=?, canBeBoard=?, canBeExtendedBoard=?, "
                                       + "canBeMAK=?, canBeAGE=?, canBeKitchen=?, canBeSeminar=?, canBeMisc=? WHERE id=?");
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
   * @since Date: Oct 13, 2012
   * @param stmt releases database connections of the given statement.
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

  @Override
  public void update(final Camp c) {
    PreparedStatement stmt = null;
    try {
      stmt = this.con.prepareStatement("UPDATE `camps` SET name=?, fromDate=?, until=?, location=?, "
                                       + "ratePerParticipant=?, ratePerDayChild=? WHERE id=?");
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
   * @since Date: Oct 14, 2012
   * @param camp the camp to fill with its participants from the database
   */
  private void updateCampParticipants(final Camp camp) {
    final Map<Long, Participant> mapOfParticipants = getMapOfParticipants();

    PreparedStatement stmt = null;
    try {
      stmt = this.con.prepareStatement("SELECT id, isParticipant, "
                                       + "isStaff, isStaffYouth, isBoard, isExtendedBoard, "
                                       + "isMAK, isAGE, isKitchen, isSeminar, isMisc "
                                       + "FROM `campParticipants` WHERE camp=?");
      stmt.setLong(1, camp.getId());
      final ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        int i = 0;
        final Participant p = mapOfParticipants.get(rs.getLong(++i));
        if (p == null) {
          // participant isn't in the database
          LOGGER.error(Text.ERROR_PARTICIPANT_NOT_IN_DB);
          continue;
        }
        final CampParticipant cp = new CampParticipant(p);
        cp.setParticipant(rs.getBoolean(++i));
        cp.setStaff(rs.getBoolean(++i));
        cp.setStaffYouth(rs.getBoolean(++i));
        cp.setBoard(rs.getBoolean(++i));
        cp.setExtendedBoard(rs.getBoolean(++i));
        cp.setMAK(rs.getBoolean(++i));
        cp.setAGE(rs.getBoolean(++i));
        cp.setKitchen(rs.getBoolean(++i));
        cp.setSeminar(rs.getBoolean(++i));
        cp.setMisc(rs.getBoolean(++i));

        camp.addParticipant(cp);
      }
    } catch (final SQLException e) {
      LOGGER.fatal(Text.ERROR_EXCEPTION.text(e.getClass().getName()), e);
    } finally {
      close(stmt);
    }
  }

  /**
   * Updates the participants of the camp with the given id to the given list of participants.
   * 
   * @since Date: Oct 14, 2012
   * @param campParticipants the participants to store in the database
   * @param id the id of the camp the participants belong to
   */
  private void updateCampParticipants(final List<CampParticipant> campParticipants, final long id) {
    PreparedStatement stmt = null;
    try {
      stmt = this.con.prepareStatement("DELETE FROM `campParticipants` WHERE camp=?");
      stmt.setLong(1, id);
      stmt.executeUpdate();

      for (final CampParticipant part : campParticipants) {
        PreparedStatement s = null;
        try {
          s = this.con.prepareStatement("INSERT INTO `campParticipants` SET id=?, camp=?, isParticipant=?, "
                                        + "isStaff=?, isStaffYouth=?, isBoard=?, isExtendedBoard=?, "
                                        + "isMAK=?, isAGE=?, isKitchen=?, isSeminar=?, isMisc=?");
          int i = 0;
          s.setLong(++i, part.getId());
          s.setLong(++i, id);
          s.setBoolean(++i, part.isParticipant());
          s.setBoolean(++i, part.isStaff());
          s.setBoolean(++i, part.isStaffYouth());
          s.setBoolean(++i, part.isBoard());
          s.setBoolean(++i, part.isExtendedBoard());
          s.setBoolean(++i, part.isMAK());
          s.setBoolean(++i, part.isAGE());
          s.setBoolean(++i, part.isKitchen());
          s.setBoolean(++i, part.isSeminar());
          s.setBoolean(++i, part.isMisc());
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
    try {
      stmt = this.con.prepareStatement("SELECT id, name, fromDate, until, location, "
                                       + "ratePerParticipant, ratePerDayChild FROM `camps` ORDER BY id");
      final ResultSet rs = stmt.executeQuery();

      final HashMap<Long, Camp> hashMap = new HashMap<Long, Camp>();

      while (rs.next()) {
        final Camp c = new Camp(rs.getLong("id"),
                                rs.getString("name"),
                                new java.util.Date(rs.getDate("fromDate").getTime()),
                                new java.util.Date(rs.getDate("until").getTime()),
                                rs.getString("location"),
                                rs.getString("ratePerParticipant"));
        c.setRatePerDayChildren(rs.getString("ratePerDayChild"));

        updateCampParticipants(c);

        hashMap.put(c.getId(), c);
      }

      return hashMap;
    } catch (final SQLException e) {
      LOGGER.fatal(Text.ERROR_EXCEPTION.text(e.getClass().getName()), e);
    } finally {
      close(stmt);
    }

    return new HashMap<Long, Camp>();
  }

  @Override
  public Map<Long, Participant> getMapOfParticipants() {
    PreparedStatement stmt = null;
    try {
      stmt = this.con.prepareStatement("SELECT id, lastName, foreName, gender, denomination, birth, livingStreet, "
                                       + "livingCity, livingPlz, postStreet, postCity, postPlz, "
                                       + "phone, fax, mobilePhone, phoneParents, mailAddress, countyCouncil, "
                                       + "bankCodeNumber, bank, bankAccountNumber, commentar, sinceInDb, "
                                       + "dateUpInDb FROM `participants` ORDER BY id");
      final ResultSet rs = stmt.executeQuery();

      final HashMap<Long, Participant> hashMap = new HashMap<Long, Participant>();

      while (rs.next()) {
        final Participant p = new Participant(rs.getLong("id"),
                                              rs.getString("lastName"),
                                              rs.getString("foreName"),
                                              Gender.of(rs.getString("gender")),
                                              Denomination.of(rs.getInt("denomination")),
                                              new java.util.Date(rs.getDate("birth").getTime()),
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
    }

    return new HashMap<Long, Participant>();
  }

  @Override
  public void deleteParticipant(final long id) {
    PreparedStatement stmt = null;
    try {
      stmt = this.con.prepareStatement("DELETE FROM `participants` WHERE id=?");

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
      stmt = this.con.prepareStatement("DELETE FROM `campParticipants` WHERE camp=?");
      stmt.setLong(1, id);

      stmt.executeUpdate();

      stmt = this.con.prepareStatement("DELETE FROM `camps` WHERE id=?");
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
      stmt = this.con.prepareStatement("INSERT INTO `participants` SET lastName=?, foreName=?, gender=?, denomination=?, birth=?, "
                                       + "livingStreet=?, livingCity=?, livingPlz=?, postStreet=?, postCity=?, postPlz=?, "
                                       + "phone=?, fax=?, mobilePhone=?, phoneParents=?, mailAddress=?, countyCouncil=?, "
                                       + "bankCodeNumber=?, bank=?, bankAccountNumber=?, commentar=?, sinceInDb=?, dateUpInDb=?, "
                                       + "canBeParticipant=?, canBeStaff=?, canBeStaffYouth=?, canBeBoard=?, canBeExtendedBoard=?, "
                                       + "canBeMAK=?, canBeAGE=?, canBeKitchen=?, canBeSeminar=?, canBeMisc=?, id=?");
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
   * @since Date: Oct 14, 2012
   * @param c the camp that provides the attributes to store with the given statement
   * @param stmt the statement used to store the attributes of the given camp
   * @throws SQLException if the attributes couldn't be set to the statement
   */
  private void insertCampIntoStatement(final Camp c, final PreparedStatement stmt) throws SQLException {
    int i = 0;
    stmt.setString(++i, c.getName());
    stmt.setDate(++i, getSqlDate(c.getFromDate()));
    stmt.setDate(++i, getSqlDate(c.getUntilDate()));
    stmt.setString(++i, c.getLocation());
    stmt.setString(++i, c.getRatePerParticipant());
    stmt.setString(++i, c.getRatePerDayChildren());
    stmt.setLong(++i, c.getId());
  }

  /**
   * Fills the given statement with the information fetched from the given participant.
   * 
   * @since Date: Oct 14, 2012
   * @param p the participant that provides the attributes to store with the given statement
   * @param stmt the statement used to store the attributes of the given participant
   * @throws SQLException if the attributes couldn't be set to the statement
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
   * @since Date: Oct 14, 2012
   * @param date the date in util package format
   * @return the date in sql package format
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
   * @since Date: Oct 14, 2012
   * @param date the date in sql package format
   * @return the date in util package format
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
      stmt = this.con.prepareStatement("INSERT INTO `camps` SET name=?, fromDate=?, until=?, location=?, "
                                       + "ratePerParticipant=?, ratePerDayChild=?, id=?");
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
      this.con.close();
    } catch (final SQLException e) {
      LOGGER.fatal(Text.ERROR_EXCEPTION.text(e.getClass().getName()), e);
    }
  }
}
