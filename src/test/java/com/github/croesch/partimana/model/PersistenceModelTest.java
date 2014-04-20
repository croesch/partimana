package com.github.croesch.partimana.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.settings.DataBaseSettings;
import com.github.croesch.partimana.types.*;
import com.github.croesch.util.DateUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Provides test cases for {@link PersistenceModel}.
 *
 * @author croesch
 * @since Date: Oct 13, 2012
 */
public class PersistenceModelTest {

  private PersistenceModel pm;

  @Before
  public void setUp() {
    pm = new PersistenceModel();
  }

  @After
  public void tearDown() throws IOException, SQLException {
    Connection con = null;
    try {
      con = DriverManager.getConnection(DataBaseSettings.DB_URL.value(),
                                        DataBaseSettings.DB_USER.value(),
                                        DataBaseSettings.DB_PASSWORD.value());
      con.prepareStatement("DELETE FROM `campParticipants`").execute();
      con.prepareStatement("DELETE FROM `participants`").execute();
      con.prepareStatement("DELETE FROM `camps`").execute();
    } finally {
      try {
        if (con != null) {
          con.close();
        }
      } finally {
        pm.close();
      }
    }
  }

  @Test
  public void testIsEmpty() throws SQLException, IOException {
    assertThat(pm.getMapOfCamps()).isEmpty();
    assertThat(pm.getMapOfParticipants()).isEmpty();

    Connection con = null;
    try {
      con = DriverManager.getConnection(DataBaseSettings.DB_URL.value(),
                                        DataBaseSettings.DB_USER.value(),
                                        DataBaseSettings.DB_PASSWORD.value());
      ResultSet resultSet = con.prepareStatement("SELECT COUNT(*) FROM `camps`").executeQuery();
      resultSet.first();
      assertThat(resultSet.getInt(1)).isZero();
      resultSet = con.prepareStatement("SELECT COUNT(*) FROM `participants`").executeQuery();
      resultSet.first();
      assertThat(resultSet.getInt(1)).isZero();
      resultSet = con.prepareStatement("SELECT COUNT(*) FROM `campParticipants`").executeQuery();
      resultSet.first();
      assertThat(resultSet.getInt(1)).isZero();
    } finally {
      if (con != null) {
        con.close();
      }
    }
  }

  @Test
  public void testCreateDeleteParticipant() throws IOException {
    final Participant p = new Participant("Müller",
                                          "Alfred",
                                          Gender.MALE,
                                          Denomination.FREE_CHURCH,
                                          new Date(1234567890),
                                          "Street",
                                          4031,
                                          "City",
                                          CountyCouncil.CITY_KAISERSLAUTERN);
    final Date before = new Date();
    pm.create(p);
    final Date after = new Date();

    assertThat(p.getDateSinceInDataBase().before(before)).isFalse();
    assertThat(p.getDateSinceInDataBase().after(after)).isFalse();
    assertThat(pm.getMapOfParticipants().get(p.getId())).isEqualTo(p);
    assertThat(pm.getMapOfParticipants().size()).isEqualTo(1);

    final PersistenceModel pm2 = new PersistenceModel();
    assertThat(pm2.getMapOfParticipants().get(p.getId())).isEqualTo(p);
    assertThat(pm2.getMapOfParticipants().size()).isEqualTo(1);
    pm2.deleteParticipant(p.getId());
    pm2.close();

    assertThat(pm.getMapOfParticipants()).isEmpty();
  }

  @Test
  public void testUpdateParticipant() throws IOException {
    final Participant p = new Participant("Müller",
                                          "Alfred",
                                          Gender.MALE,
                                          Denomination.FREE_CHURCH,
                                          new Date(1234567890),
                                          "Street",
                                          4031,
                                          "City",
                                          CountyCouncil.CITY_KAISERSLAUTERN);
    pm.create(p);
    p.setForeName("Peter");

    final PersistenceModel pm2 = new PersistenceModel();
    assertThat(pm2.getMapOfParticipants().get(p.getId())).isNotEqualTo(p);
    assertThat(pm2.getMapOfParticipants().size()).isEqualTo(1);

    pm.update(p);

    assertThat(pm2.getMapOfParticipants().get(p.getId())).isEqualTo(p);
    assertThat(pm2.getMapOfParticipants().size()).isEqualTo(1);

    pm2.deleteParticipant(p.getId());
    pm2.close();

    assertThat(pm.getMapOfParticipants()).isEmpty();
  }

  @Test
  public void testCreateDeleteCamp() throws IOException {
    final Camp c = new Camp("OFZ", new Date(2345678), new Date(987654321), "dort", "2,-");
    final Participant p = new Participant("Müller",
                                          "Alfred",
                                          Gender.MALE,
                                          Denomination.FREE_CHURCH,
                                          new Date(1234567890),
                                          "Street",
                                          4031,
                                          "City",
                                          CountyCouncil.CITY_KAISERSLAUTERN);
    c.addParticipant(new CampParticipant(p));
    pm.create(p);
    pm.create(c);
    assertThat(pm.getMapOfParticipants().get(p.getId())).isEqualTo(p);
    assertThat(pm.getMapOfParticipants().size()).isEqualTo(1);
    assertThat(pm.getMapOfCamps().get(c.getId())).isEqualTo(c);
    assertThat(pm.getMapOfCamps().size()).isEqualTo(1);

    final PersistenceModel pm2 = new PersistenceModel();
    assertThat(pm2.getMapOfCamps().get(c.getId())).isEqualTo(c);
    assertThat(pm2.getMapOfCamps().size()).isEqualTo(1);
    assertThat(pm2.getMapOfParticipants().get(p.getId())).isEqualTo(p);
    assertThat(pm2.getMapOfParticipants().size()).isEqualTo(1);
    pm2.deleteCamp(c.getId());
    pm2.close();

    assertThat(pm.getMapOfCamps()).isEmpty();

    assertThat(pm.getMapOfParticipants().get(p.getId())).isEqualTo(p);
    assertThat(pm.getMapOfParticipants().size()).isEqualTo(1);

    pm.deleteParticipant(p.getId());

    assertThat(pm.getMapOfParticipants()).isEmpty();
  }

  @Test
  public void testUpdateCamp() throws IOException {
    final Camp c = new Camp("OFZ", new Date(2345678), new Date(987654321), "dort", "2,-");
    final Participant p = new Participant("Müller",
                                          "Alfred",
                                          Gender.MALE,
                                          Denomination.FREE_CHURCH,
                                          new Date(1234567890),
                                          "Street",
                                          4031,
                                          "City",
                                          CountyCouncil.CITY_KAISERSLAUTERN);
    final CampParticipant cp = new CampParticipant(p);
    cp.setSignedIn(new Date(0));
    c.addParticipant(cp);
    pm.create(p);
    pm.create(c);
    c.setName("FREIZEIT");
    c.setCancelDate(new Date(1234567887654L));
    cp.setRole(Role.KITCHEN_STAFF);
    final Date date = new Date();
    cp.setSignedIn(date);

    final PersistenceModel pm2 = new PersistenceModel();
    assertThat(pm2.getMapOfParticipants().get(p.getId())).isEqualTo(p);
    assertThat(pm2.getMapOfParticipants().size()).isEqualTo(1);
    Camp storedCamp = pm2.getMapOfCamps().get(c.getId());
    assertThat(storedCamp).isNotEqualTo(c);
    assertThat(storedCamp.getParticipants().get(storedCamp.getParticipants().indexOf(cp)).getRole())
        .isEqualTo(Role.PARTICIPANT);
    assertThat(storedCamp.getParticipants().get(storedCamp.getParticipants().indexOf(cp)).getSignedIn())
        .isEqualTo(new DateUtil(new Date(0)).getDateWithoutTime());
    assertThat(storedCamp.getParticipants().get(storedCamp.getParticipants().indexOf(cp)).getSignedOff()).isNull();
    assertThat(storedCamp.getCancelDate()).isNull();
    assertThat(pm2.getMapOfCamps().size()).isEqualTo(1);

    pm.update(c);

    assertThat(pm2.getMapOfParticipants().get(p.getId())).isEqualTo(p);
    assertThat(pm2.getMapOfParticipants().size()).isEqualTo(1);
    storedCamp = pm2.getMapOfCamps().get(c.getId());
    assertThat(storedCamp).isEqualTo(c);
    assertThat(storedCamp.getParticipants().get(storedCamp.getParticipants().indexOf(cp)).getRole())
        .isEqualTo(Role.KITCHEN_STAFF);
    final Date dateWithoutTime = new DateUtil(date).getDateWithoutTime();
    assertThat(storedCamp.getParticipants().get(storedCamp.getParticipants().indexOf(cp)).getSignedIn())
        .isEqualTo(dateWithoutTime);
    assertThat(storedCamp.getParticipants().get(storedCamp.getParticipants().indexOf(cp)).getSignedOff()).isNull();
    assertThat(storedCamp.getCancelDate()).isEqualTo(new DateUtil(new Date(1234567887654L)).getDateWithoutTime());
    assertThat(pm2.getMapOfCamps().size()).isEqualTo(1);

    pm2.deleteCamp(c.getId());
    pm2.close();

    assertThat(pm.getMapOfCamps()).isEmpty();

    assertThat(pm.getMapOfParticipants().get(p.getId())).isEqualTo(p);
    assertThat(pm.getMapOfParticipants().size()).isEqualTo(1);

    pm.deleteParticipant(p.getId());

    assertThat(pm.getMapOfParticipants()).isEmpty();
  }
}
