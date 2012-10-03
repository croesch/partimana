package com.github.croesch.partimana.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.croesch.partimana.model.api.IPersistenceModel;
import com.github.croesch.partimana.settings.DataBaseSettings;
import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.types.CampParticipant;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: May 29, 2011
 */
class PersistenceModel implements IPersistenceModel {

  private Connection con;

  public PersistenceModel() {
    try {
      this.con = DriverManager.getConnection(DataBaseSettings.DB_URL.value(), DataBaseSettings.DB_USER.value(),
                                             DataBaseSettings.DB_PASSWORD.value());
    } catch (final SQLException e) {
      e.printStackTrace(); // TODO
    }
  }

  @Override
  public void update(final Participant p) {
    try {
      final PreparedStatement stmt = this.con.prepareStatement("UPDATE `participants` SET lastName=?, foreName=?, gender=?, denomination=?, birth=?, "
                                                               + "livingStreet=?, livingCity=?, livingPlz=?, postStreet=?, postCity=?, postPlz=?, "
                                                               + "phone=?, fax=?, mobilePhone=?, phoneParents=?, mailAddress=?, countyCouncil=?, "
                                                               + "bankCodeNumber=?, bank=?, bankAccountNumber=?, commentar=?, sinceInDb=?, dateUpInDb=?, "
                                                               + "canBeParticipant=?, canBeStaff=?, canBeStaffYouth=?, canBeBoard=?, canBeExtendedBoard=?, "
                                                               + "canBeMAK=?, canBeAGE=?, canBeKitchen=?, canBeSeminar=?, canBeMisc=? WHERE id=?");
      insertParticipantIntoStatement(p, stmt);

      stmt.executeUpdate();
    } catch (final SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void update(final Camp c) {
    try {
      final PreparedStatement stmt = this.con.prepareStatement("UPDATE `camps` SET name=?, fromDate=?, until=?, location=?, "
                                                               + "ratePerParticipant=?, ratePerDayChild=? WHERE id=?");
      insertCampIntoStatement(c, stmt);
      stmt.executeUpdate();

      updateCampParticipants(c.getParticipants(), c.getId());
    } catch (final SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private void updateCampParticipants(final List<CampParticipant> campParticipants, final long id) {
    try {
      final PreparedStatement stmt = this.con.prepareStatement("DELETE FROM `campParticipants` WHERE camp=?");
      stmt.setLong(1, id);
      stmt.executeUpdate();

      for (final CampParticipant part : campParticipants) {
        final PreparedStatement s = this.con.prepareStatement("INSERT INTO `campParticipants` SET id=?, camp=?, isParticipant=?, "
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
      }
    } catch (final SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public Map<Long, Camp> getMapOfCamps() {
    try {
      final ResultSet rs = this.con.prepareStatement("SELECT id, name, fromDate, until, location, "
                                                             + "ratePerParticipant, ratePerDayChild FROM `camps` ORDER BY id")
                                   .executeQuery();

      final HashMap<Long, Camp> hashMap = new HashMap<Long, Camp>();

      while (rs.next()) {
        final Camp c = new Camp(rs.getLong("id"),
                                rs.getString("name"),
                                new java.util.Date(rs.getDate("fromDate").getTime()),
                                new java.util.Date(rs.getDate("until").getTime()),
                                rs.getString("location"),
                                rs.getString("ratePerParticipant"));
        c.setRatePerDayChildren(rs.getString("ratePerDayChild"));

        hashMap.put(c.getId(), c);
      }

      return hashMap;
    } catch (final SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return new HashMap<Long, Camp>();
  }

  @Override
  public Map<Long, Participant> getMapOfParticipants() {
    try {
      final ResultSet rs = this.con.prepareStatement("SELECT id, lastName, foreName, gender, denomination, birth, livingStreet, "
                                                             + "livingCity, livingPlz, postStreet, postCity, postPlz, "
                                                             + "phone, fax, mobilePhone, phoneParents, mailAddress, countyCouncil, "
                                                             + "bankCodeNumber, bank, bankAccountNumber, commentar, sinceInDb, "
                                                             + "dateUpInDb, canBeParticipant, canBeStaff, canBeStaffYouth, canBeBoard, "
                                                             + "canBeExtendedBoard, canBeMAK, canBeAGE, canBeKitchen, "
                                                             + "canBeSeminar, canBeMisc FROM `participants` ORDER BY id")
                                   .executeQuery();

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
        p.setPossibleAGE(rs.getBoolean("canBeAGE"));
        p.setPossibleBoard(rs.getBoolean("canBeBoard"));
        p.setPossibleExtendedBoard(rs.getBoolean("canBeExtendedBoard"));
        p.setPossibleKitchen(rs.getBoolean("canBeKitchen"));
        p.setPossibleMAK(rs.getBoolean("canBeMAK"));
        p.setPossibleMisc(rs.getBoolean("canBeMisc"));
        p.setPossibleParticipant(rs.getBoolean("canBeParticipant"));
        p.setPossibleSeminar(rs.getBoolean("canBeSeminar"));
        p.setPossibleStaff(rs.getBoolean("canBeStaff"));
        p.setPossibleStaffYouth(rs.getBoolean("canBeStaffYouth"));
        p.setPostCodePostal(rs.getInt("postPlz"));
        p.setStreetPostal(rs.getString("postStreet"));

        hashMap.put(p.getId(), p);
      }

      return hashMap;
    } catch (final SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return new HashMap<Long, Participant>();
  }

  @Override
  public void deleteParticipant(final long id) {
    try {
      final PreparedStatement stmt = this.con.prepareStatement("DELETE FROM `participants` WHERE id=?");

      stmt.setLong(1, id);

      stmt.executeUpdate();
    } catch (final SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void deleteCamp(final long id) {
    try {
      final PreparedStatement stmt = this.con.prepareStatement("DELETE FROM `camps` WHERE id=?");

      stmt.setLong(1, id);

      stmt.executeUpdate();
    } catch (final SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void create(final Participant p) {
    try {
      final PreparedStatement stmt = this.con.prepareStatement("INSERT INTO `participants` SET lastName=?, foreName=?, gender=?, denomination=?, birth=?, "
                                                               + "livingStreet=?, livingCity=?, livingPlz=?, postStreet=?, postCity=?, postPlz=?, "
                                                               + "phone=?, fax=?, mobilePhone=?, phoneParents=?, mailAddress=?, countyCouncil=?, "
                                                               + "bankCodeNumber=?, bank=?, bankAccountNumber=?, commentar=?, sinceInDb=?, dateUpInDb=?, "
                                                               + "canBeParticipant=?, canBeStaff=?, canBeStaffYouth=?, canBeBoard=?, canBeExtendedBoard=?, "
                                                               + "canBeMAK=?, canBeAGE=?, canBeKitchen=?, canBeSeminar=?, canBeMisc=?, id=?");
      insertParticipantIntoStatement(p, stmt);

      stmt.executeUpdate();
    } catch (final SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

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
    stmt.setBoolean(++i, p.isPossibleParticipant());
    stmt.setBoolean(++i, p.isPossibleStaff());
    stmt.setBoolean(++i, p.isPossibleStaffYouth());
    stmt.setBoolean(++i, p.isPossibleBoard());
    stmt.setBoolean(++i, p.isPossibleExtendedBoard());
    stmt.setBoolean(++i, p.isPossibleMAK());
    stmt.setBoolean(++i, p.isPossibleAGE());
    stmt.setBoolean(++i, p.isPossibleKitchen());
    stmt.setBoolean(++i, p.isPossibleSeminar());
    stmt.setBoolean(++i, p.isPossibleMisc());
    stmt.setLong(++i, p.getId());
  }

  private Date getSqlDate(final java.util.Date date) {
    if (date == null) {
      return null;
    }
    return new Date(date.getTime());
  }

  private java.util.Date getUtilDate(final Date date) {
    if (date == null) {
      return null;
    }
    return new java.util.Date(date.getTime());
  }

  @Override
  public void create(final Camp c) {
    try {
      final PreparedStatement stmt = this.con.prepareStatement("INSERT INTO `camps` SET name=?, fromDate=?, until=?, location=?, "
                                                               + "ratePerParticipant=?, ratePerDayChild=?, id=?");
      insertCampIntoStatement(c, stmt);
      stmt.executeUpdate();

      updateCampParticipants(c.getParticipants(), c.getId());
    } catch (final SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
