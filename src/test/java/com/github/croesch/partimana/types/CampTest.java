package com.github.croesch.partimana.types;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import org.junit.Test;

/**
 * Provides test methods for {@link Camp}
 *
 * @author croesch
 * @since Date: Jun 18, 2011
 */
public class CampTest {

  /** object under test */
  private Camp camp = new Camp("Testcamp", new Date(0), new Date(100000), "here", "100");

  @Test
  public final void testCamp() {
    final int untilDate = 100000;
    assertThat(camp.getName()).isEqualTo("Testcamp");
    assertThat(camp.getFromDate()).isEqualTo(new Date(0));
    assertThat(camp.getUntilDate()).isEqualTo(new Date(untilDate));
    assertThat(camp.getLocation()).isEqualTo("here");
    assertThat(camp.getRatePerParticipant()).isEqualTo("100");
  }

  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testSetAndGetNameRFSTNE() {
    camp.setName(null);
  }

  @Test
  public final void testSetAndGetName() {
    camp.setName("name");
    assertThat(camp.getName()).isEqualTo("name");

    camp.setName("...");
    assertThat(camp.getName()).isEqualTo("...");

    camp.setName("");
    assertThat(camp.getName()).isEqualTo("");
  }

  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testSetAndGetFromDateRFSTNE() {
    camp.setFromDate(null);
  }

  @Test
  public final void testSetAndGetFromDate() {
    final Date d1 = new Date();
    camp.setFromDate(d1);
    assertThat(camp.getFromDate()).isEqualTo(d1);

    final Date d2 = new Date(17);
    camp.setFromDate(d2);
    assertThat(camp.getFromDate()).isEqualTo(d2);

    Date d3 = new Date(99);
    camp.setFromDate(d3);
    assertThat(camp.getFromDate()).isEqualTo(d3);

    d3.setTime(98);
    assertThat(camp.getFromDate()).isNotEqualTo(d3);

    d3 = camp.getFromDate();
    d3.setTime(98);
    assertThat(camp.getFromDate()).isNotEqualTo(d3);
  }

  @Test
  public final void testSetAndGetCancelDate() {
    final Date d1 = new Date();
    camp.setCancelDate(d1);
    assertThat(camp.getCancelDate()).isEqualTo(d1);

    final Date d2 = new Date(17);
    camp.setCancelDate(d2);
    assertThat(camp.getCancelDate()).isEqualTo(d2);

    Date d3 = new Date(99);
    camp.setCancelDate(d3);
    assertThat(camp.getCancelDate()).isEqualTo(d3);

    d3.setTime(98);
    assertThat(camp.getCancelDate()).isNotEqualTo(d3);

    d3 = camp.getCancelDate();
    d3.setTime(98);
    assertThat(camp.getCancelDate()).isNotEqualTo(d3);

    camp.setCancelDate(null);
    assertThat(camp.getCancelDate()).isNull();
  }

  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testSetAndGetUntilDateRFSTNE() {
    camp.setUntilDate(null);
  }

  @Test
  public final void testSetAndGetUntilDate() {
    final Date d1 = new Date();
    camp.setUntilDate(d1);
    assertThat(camp.getUntilDate()).isEqualTo(d1);

    final Date d2 = new Date(17);
    camp.setUntilDate(d2);
    assertThat(camp.getUntilDate()).isEqualTo(d2);

    Date d3 = new Date(99);
    camp.setUntilDate(d3);
    assertThat(camp.getUntilDate()).isEqualTo(d3);

    d3.setTime(98);
    assertThat(camp.getUntilDate()).isNotEqualTo(d3);

    d3 = camp.getUntilDate();
    d3.setTime(98);
    assertThat(camp.getUntilDate()).isNotEqualTo(d3);
  }

  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testSetAndGetLocationRFSTNE() {
    camp.setLocation(null);
  }

  @Test
  public final void testSetAndGetLocation() {
    camp.setLocation("loc");
    assertThat(camp.getLocation()).isEqualTo("loc");

    camp.setLocation("...");
    assertThat(camp.getLocation()).isEqualTo("...");

    camp.setLocation("");
    assertThat(camp.getLocation()).isEqualTo("");
  }

  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testSetAndGetRatePerParticipantRFSTNE() {
    camp.setRatePerParticipant(null);
  }

  @Test
  public final void testSetAndGetRatePerParticipant() {
    camp.setRatePerParticipant("12");
    assertThat(camp.getRatePerParticipant()).isEqualTo("12");

    camp.setRatePerParticipant("...");
    assertThat(camp.getRatePerParticipant()).isEqualTo("...");

    camp.setRatePerParticipant("");
    assertThat(camp.getRatePerParticipant()).isEqualTo("");
  }

  @Test
  public final void testSetAndGetRatePerDayChildren() {
    camp.setRatePerDayChildren("12");
    assertThat(camp.getRatePerDayChildren()).isEqualTo("12");

    camp.setRatePerDayChildren("...");
    assertThat(camp.getRatePerDayChildren()).isEqualTo("...");

    camp.setRatePerDayChildren("");
    assertThat(camp.getRatePerDayChildren()).isEqualTo("");
  }

  @Test
  public final void testGetId() {
    final long id = camp.getId();
    camp = new Camp("n", new Date(), new Date(), "w", "r");

    assertThat(camp.getId()).isEqualTo(id + 1);

    camp = new Camp(id + 2, "n", new Date(), new Date(), "w", "r");
    assertThat(camp.getId()).isEqualTo(id + 2);
  }

  @Test
  public final void testToString() {
    assertThat(camp.toString()).contains(String.valueOf(camp.getId()));
  }

  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testCampCampRFSTNE() {
    new Camp(null);
  }

  @Test
  public final void testCampCamp() {
    final Participant p = new Participant("Mustermann",
                                          "Max",
                                          Gender.MALE,
                                          Denomination.OTHER,
                                          new Date(),
                                          "Musterstrasse 12",
                                          98667,
                                          "Musterhausen",
                                          CountyCouncil.OTHER);

    camp.addParticipant(new CampParticipant(p));
    camp.setRatePerDayChildren("children-rate");
    camp.setCancelDate(new Date(20013));

    final Camp c = new Camp(camp);
    assertThat(c).isEqualTo(camp);
    assertThat(c.getCancelDate()).isEqualTo(camp.getCancelDate());

    camp.removeAllParticipants();
    assertThat(c).isNotEqualTo(camp);
    camp.addParticipant(new CampParticipant(p));
    assertThat(c).isEqualTo(camp);

    c.setRatePerDayChildren("day-children-rate");
    assertThat(c).isNotEqualTo(camp);
  }

  @Test
  public final void testCampEquals() {

    camp.setRatePerDayChildren("children-rate");

    Camp c = new Camp(camp);
    assertThat(c).isEqualTo(camp);
    assertThat(camp).isEqualTo(camp);
    assertThat(c).isEqualTo(c);
    assertThat(c).isNotEqualTo(null);
    assertThat(c).isNotEqualTo("camp");

    c.setFromDate(new Date(1444234567987l));
    assertThat(c).isNotEqualTo(camp);

    c = new Camp(c.getId() + 10,
                 camp.getName(),
                 camp.getFromDate(),
                 camp.getUntilDate(),
                 camp.getLocation(),
                 camp.getRatePerParticipant());
    c.setRatePerDayChildren(camp.getRatePerDayChildren());
    assertThat(c).isNotEqualTo(camp);
    c = new Camp(camp);

    assertThat(c).isEqualTo(camp);
    c.setLocation("nowhere");
    assertThat(c).isNotEqualTo(camp);
    c.setLocation(camp.getLocation());

    assertThat(c).isEqualTo(camp);
    c.setName("no-name");
    assertThat(c).isNotEqualTo(camp);
    c.setName(camp.getName());

    assertThat(c).isEqualTo(camp);
    c.setRatePerDayChildren(null);
    assertThat(c).isNotEqualTo(camp);
    c.setRatePerDayChildren(camp.getRatePerDayChildren());

    assertThat(c).isEqualTo(camp);
    c.setRatePerParticipant("no rate");
    assertThat(c).isNotEqualTo(camp);
    c.setRatePerParticipant(camp.getRatePerParticipant());

    assertThat(c).isEqualTo(camp);
    c.setUntilDate(new Date(1444234567987l));
    assertThat(c).isNotEqualTo(camp);
  }

  @Test
  public final void testCampHashCode() {

    camp.setRatePerDayChildren("children-rate");

    Camp c = new Camp(camp);
    assertThat(c.hashCode()).isEqualTo(camp.hashCode());
    assertThat(camp.hashCode()).isEqualTo(camp.hashCode());
    assertThat(c.hashCode()).isEqualTo(c.hashCode());
    assertThat(c.hashCode()).isNotNull();

    c.setFromDate(new Date(987));
    assertThat(c.hashCode()).isNotEqualTo(camp.hashCode());

    c = new Camp(c.getId() + 10,
                 camp.getName(),
                 camp.getFromDate(),
                 camp.getUntilDate(),
                 camp.getLocation(),
                 camp.getRatePerParticipant());
    c.setRatePerDayChildren(camp.getRatePerDayChildren());
    assertThat(c.hashCode()).isNotEqualTo(camp.hashCode());
    c = new Camp(camp);

    assertThat(c.hashCode()).isEqualTo(camp.hashCode());
    c.setLocation("nowhere");
    assertThat(c.hashCode()).isNotEqualTo(camp.hashCode());
    c.setLocation(camp.getLocation());

    assertThat(c.hashCode()).isEqualTo(camp.hashCode());
    c.setName("no-name");
    assertThat(c.hashCode()).isNotEqualTo(camp.hashCode());
    c.setName(camp.getName());

    assertThat(c.hashCode()).isEqualTo(camp.hashCode());
    c.setRatePerDayChildren(null);
    assertThat(c.hashCode()).isNotEqualTo(camp.hashCode());
    c.setRatePerDayChildren(camp.getRatePerDayChildren());

    assertThat(c.hashCode()).isEqualTo(camp.hashCode());
    c.setRatePerParticipant("no rate");
    assertThat(c.hashCode()).isNotEqualTo(camp.hashCode());
    c.setRatePerParticipant(camp.getRatePerParticipant());

    assertThat(c.hashCode()).isEqualTo(camp.hashCode());
    c.setUntilDate(new Date(987));
    assertThat(c.hashCode()).isNotEqualTo(camp.hashCode());
  }

  @Test
  public void testGetParticipants() {
    assertThat(camp.getParticipants()).isEmpty();

    final Participant participant = new Participant("Mustermann",
                                                    "Max",
                                                    Gender.MALE,
                                                    Denomination.OTHER,
                                                    new Date(),
                                                    "Musterstrasse 12",
                                                    98667,
                                                    "Musterhausen",
                                                    CountyCouncil.OTHER);
    camp.getParticipants().add(new CampParticipant(participant));
    assertThat(camp.getParticipants()).isEmpty();

    camp.addParticipant(new CampParticipant(participant));
    assertThat(camp.getParticipants()).containsExactly(new CampParticipant(participant));
  }

  @Test
  public void testAddAndRemoveParticipant() {
    final Participant participant = new Participant("Mustermann",
                                                    "Max",
                                                    Gender.MALE,
                                                    Denomination.OTHER,
                                                    new Date(),
                                                    "Musterstrasse 12",
                                                    98667,
                                                    "Musterhausen",
                                                    CountyCouncil.OTHER);
    final Participant participant2 = new Participant("Mustermann",
                                                     "Max",
                                                     Gender.MALE,
                                                     Denomination.OTHER,
                                                     new Date(),
                                                     "Musterstrasse 12",
                                                     98667,
                                                     "Musterhausen",
                                                     CountyCouncil.OTHER);

    camp.addParticipant(new CampParticipant(participant2));
    assertThat(camp.getParticipants()).containsOnly(new CampParticipant(participant2));

    camp.addParticipant(new CampParticipant(participant));
    assertThat(camp.getParticipants())
        .containsOnly(new CampParticipant(participant2), new CampParticipant(participant));

    participant.setBank("blub");
    assertThat(camp.getParticipants())
        .containsOnly(new CampParticipant(participant2), new CampParticipant(participant));
    camp.removeParticipant(new CampParticipant(participant2));
    assertThat(camp.getParticipants()).containsOnly(new CampParticipant(participant));

    camp.addParticipant(null);
    assertThat(camp.getParticipants()).containsOnly(new CampParticipant(participant));

    camp.removeParticipant(null);
    assertThat(camp.getParticipants()).containsOnly(new CampParticipant(participant));

    camp.removeParticipant(new CampParticipant(participant2));
    assertThat(camp.getParticipants()).containsOnly(new CampParticipant(participant));

    camp.removeParticipant(new CampParticipant(participant));
    assertThat(camp.getParticipants()).isEmpty();

    camp.addParticipant(new CampParticipant(participant2));
    camp.addParticipant(new CampParticipant(participant));
    assertThat(camp.getParticipants())
        .containsOnly(new CampParticipant(participant2), new CampParticipant(participant));
    camp.removeAllParticipants();
    assertThat(camp.getParticipants()).isEmpty();
    camp.removeAllParticipants();
    assertThat(camp.getParticipants()).isEmpty();
  }

  @Test
  public void testToCSV() {
    final Participant participant = new Participant("Kleemann",
                                                    "Lorenz",
                                                    Gender.MALE,
                                                    Denomination.EVANGELIC,
                                                    // 10.01.2005
                                                    new Date(1105311600000L),
                                                    "Lehmweg 28",
                                                    67688,
                                                    "Rodenbach",
                                                    CountyCouncil.COUNTY_BAD_DUERKHEIM);
    final Participant participant2 = new Participant("Eichhorn",
                                                     "Sosaya",
                                                     Gender.FEMALE,
                                                     Denomination.CATHOLIC,
                                                     // 20.11.2002
                                                     new Date(1037746800000L),
                                                     "Eisenkehlstr. 37",
                                                     67475,
                                                     "Weidenthal",
                                                     CountyCouncil.COUNTY_GERMERSHEIM);
    final Participant participant3 = new Participant("Duck",
                                                     "Donald",
                                                     Gender.MALE,
                                                     Denomination.JEWISH,
                                                     // 06.03.2008
                                                     new Date(1204758000000L),
                                                     "Entenstraße 2",
                                                     12345,
                                                     "Entenhausen",
                                                     CountyCouncil.COUNTY_KUSEL);
    final Participant participant4 = new Participant("Eichhorn",
                                                     "Cosima",
                                                     Gender.FEMALE,
                                                     Denomination.OTHER,
                                                     // 13.12.2001
                                                     new Date(1008198000000L),
                                                     "Eisenkehlstr. 37",
                                                     67475,
                                                     "Weidenthal",
                                                     CountyCouncil.COUNTY_RHEIN_PFALZ);
    participant2.setPhone(" ");
    participant2.setMobilePhone(" ");
    participant2.setMailAddress(" ");
    participant3.setPhone("987654");
    participant3.setMobilePhone("0123-456789");
    participant3.setMailAddress("donald@duck.de");
    participant4.setPhone("555-444333");
    participant4.setMobilePhone("0160/1112223");
    participant4.setMailAddress("cosima.eichhorn@t-online.de");

    camp.addParticipant(new CampParticipant(participant));
    camp.addParticipant(new CampParticipant(participant2));
    CampParticipant campParticipant3 = new CampParticipant(participant3);
    campParticipant3.setRole(Role.DAY_CHILD);
    camp.addParticipant(campParticipant3);
    camp.addParticipant(new CampParticipant(participant4));
    assertThat(camp.getParticipants()).containsExactly(new CampParticipant(participant),
                                                       new CampParticipant(participant2),
                                                       campParticipant3,
                                                       new CampParticipant(participant4));

    camp.setRatePerDayChildren("22");

    String lf = System.getProperty("line.separator");
    String from = formatDate(camp.getFromDate());
    String until = formatDate(camp.getUntilDate());

    String headerCSV =
        "vorname;name;strasse;plz;wohnort;geschlecht;geburtstag;alter;kreisverwaltung;konfession;telefon;"
        + "mobiltelefon;mail;freizeit;preis;von;bis";
    String participant1CSV =
        "Lorenz;Kleemann;Lehmweg 28;67688;Rodenbach;m;" + birth(participant) + ";" + age(participant)
        + ";Kreis Bad Dürkheim;Evangelisch;;;;" + "Testcamp;100;" + from + ";" + until;
    String participant2CSV =
        "Sosaya;Eichhorn;Eisenkehlstr. 37;67475;Weidenthal;w;" + birth(participant2) + ";" + age(participant2)
        + ";Kreis Gemersheim;Katholisch; ; ; ;" + "Testcamp;100;" + from + ";" + until;
    String participant3CSV =
        "Donald;Duck;Entenstraße 2;12345;Entenhausen;m;" + birth(participant3) + ";" + age(participant3)
        + ";Kreis Kusel;Jüdisch;987654;0123-456789;donald@duck.de;" + "Testcamp;22;" + from + ";" + until;
    String participant4CSV =
        "Cosima;Eichhorn;Eisenkehlstr. 37;67475;Weidenthal;w;" + birth(participant4) + ";" + age(participant4)
        + ";Kreis Rhein-Pfalz;Andere Konfession;555-444333;0160/1112223;cosima.eichhorn@t-online.de;" + "Testcamp;100;"
        + from + ";" + until;

    // all participants
    assertThat(camp.toCSV(Collections.<Long>emptySet())).isEqualTo(
        headerCSV + lf + participant1CSV + lf + participant2CSV + lf + participant3CSV + lf + participant4CSV + lf);
    assertThat(camp.toCSV(asList(participant3.getId(),
                                 participant2.getId(),
                                 participant.getId(),
                                 participant4.getId()))).isEqualTo(
        headerCSV + lf + participant1CSV + lf + participant2CSV + lf + participant3CSV + lf + participant4CSV + lf);

    // one participant
    assertThat(camp.toCSV(asList(participant.getId()))).isEqualTo(headerCSV + lf + participant1CSV + lf);
    assertThat(camp.toCSV(asList(participant4.getId()))).isEqualTo(headerCSV + lf + participant4CSV + lf);
    // duplicate id
    assertThat(camp.toCSV(asList(participant.getId(), participant.getId())))
        .isEqualTo(headerCSV + lf + participant1CSV + lf);

    // more participants
    assertThat(camp.toCSV(asList(participant4.getId(), participant3.getId())))
        .isEqualTo(headerCSV + lf + participant3CSV + lf + participant4CSV + lf);
    assertThat(camp.toCSV(asList(participant.getId(), participant3.getId())))
        .isEqualTo(headerCSV + lf + participant1CSV + lf + participant3CSV + lf);
  }

  private String birth(Participant participant) {
    return formatDate(participant.getBirthDate());
  }

  private String age(Participant participant) {
    Calendar dob = Calendar.getInstance();
    dob.setTime(participant.getBirthDate());
    Calendar today = Calendar.getInstance();
    int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
    if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
      age--;
    } else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) < dob
        .get(Calendar.DAY_OF_MONTH)) {
      age--;
    }
    return String.valueOf(age);
  }

  private String formatDate(Date date) {
    return SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG).format(date);
  }
}
