package com.github.croesch.partimana.types;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

/**
 * Provides different test methods for {@link Participant}
 *
 * @author croesch
 * @since Date: Jun 18, 2011
 */
public class ParticipantTest {

  /** example post code */
  private static final int SAMPLE_POSTCODE = 12345;

  /** object to test with */
  private Participant participant;

  /**
   * Sets up an object of {@link Participant}
   *
   * @since Date: Jun 18, 2011
   */
  @Before
  public final void setUp() {
    participant = new Participant("Mustermann",
                                  "Max",
                                  Gender.MALE,
                                  Denomination.OTHER,
                                  new Date(),
                                  "Musterstrasse 12",
                                  SAMPLE_POSTCODE,
                                  "Musterhausen",
                                  CountyCouncil.OTHER);
  }

  /**
   * Test method for {@link Participant#Participant(Participant)}.
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testParticipantParticipantRFSTNE() {
    new Participant(null);
  }

  /**
   * Test method for {@link Participant#Participant(Participant)}.
   */
  @Test
  public final void testParticipantParticipant() {
    final Date d1 = new Date(123);

    participant.setBank("bank");
    participant.setBankAccountNumber(1);
    participant.setBankCodeNumber(12);
    participant.setComment("comment");
    participant.setDateSinceInDataBase(d1);
    participant.setDateUpToInSystem(d1);
    participant.setDenomination(Denomination.JEWISH);
    participant.setFax("fax");
    participant.setMailAddress("mail");
    participant.setMobilePhone("mobile");
    participant.setPhone("phone");
    participant.setPhoneOfParents("phone");
    participant.setCityPostal("city");
    participant.setPostCodePostal(3124);
    participant.setStreetPostal("street");

    final Participant p = new Participant(participant);
    assertThat(p).isEqualTo(participant);

    d1.setTime(1212);
    assertThat(p.getDateSinceInDataBase()).isNotEqualTo(d1);
    assertThat(p.getDateUpToInSystem()).isNotEqualTo(d1);
    assertThat(p).isEqualTo(participant);

    p.setBirthDate(d1);
    assertThat(p).isNotEqualTo(participant);
  }

  /**
   * Test method for {@link Participant#Participant(String, String, Gender, Denomination, Date, String, int, String,
   * CountyCouncil)} .
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testParticipantRFSTNE1() {
    participant = new Participant(null,
                                  "Max",
                                  Gender.MALE,
                                  Denomination.OTHER,
                                  new Date(),
                                  "Musterstrasse 12",
                                  SAMPLE_POSTCODE,
                                  "Musterhausen",
                                  CountyCouncil.OTHER);
  }

  /**
   * Test method for {@link Participant#Participant(String, String, Gender, Denomination, Date, String, int, String,
   * CountyCouncil)} .
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testParticipantRFSTNE2() {
    participant = new Participant("Mustermann",
                                  null,
                                  Gender.MALE,
                                  Denomination.OTHER,
                                  new Date(),
                                  "Musterstrasse 12",
                                  SAMPLE_POSTCODE,
                                  "Musterhausen",
                                  CountyCouncil.OTHER);
  }

  /**
   * Test method for {@link Participant#Participant(String, String, Gender, Denomination, Date, String, int, String,
   * CountyCouncil)} .
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testParticipantRFSTNE3() {
    participant = new Participant("Mustermann",
                                  "Max",
                                  null,
                                  Denomination.OTHER,
                                  new Date(),
                                  "Musterstrasse 12",
                                  SAMPLE_POSTCODE,
                                  "Musterhausen",
                                  CountyCouncil.OTHER);
  }

  /**
   * Test method for {@link Participant#Participant(String, String, Gender, Denomination, Date, String, int, String,
   * CountyCouncil)} .
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testParticipantRFSTNE4() {
    participant = new Participant("Mustermann",
                                  "Max",
                                  Gender.MALE,
                                  null,
                                  new Date(),
                                  "Musterstrasse 12",
                                  SAMPLE_POSTCODE,
                                  "Musterhausen",
                                  CountyCouncil.OTHER);
  }

  /**
   * Test method for {@link Participant#Participant(String, String, Gender, Denomination, Date, String, int, String,
   * CountyCouncil)} .
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testParticipantRFSTNE5() {
    participant = new Participant("Mustermann",
                                  "Max",
                                  Gender.MALE,
                                  Denomination.OTHER,
                                  null,
                                  "Musterstrasse 12",
                                  SAMPLE_POSTCODE,
                                  "Musterhausen",
                                  CountyCouncil.OTHER);
  }

  /**
   * Test method for {@link Participant#Participant(String, String, Gender, Denomination, Date, String, int, String,
   * CountyCouncil)} .
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testParticipantRFSTNE6() {
    participant = new Participant("Mustermann",
                                  "Max",
                                  Gender.MALE,
                                  Denomination.OTHER,
                                  new Date(),
                                  null,
                                  SAMPLE_POSTCODE,
                                  "Musterhausen",
                                  CountyCouncil.OTHER);
  }

  /**
   * Test method for {@link Participant#Participant(String, String, Gender, Denomination, Date, String, int, String,
   * CountyCouncil)} .
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testParticipantRFSTNE7() {
    participant = new Participant("Mustermann",
                                  "Max",
                                  Gender.MALE,
                                  Denomination.OTHER,
                                  new Date(),
                                  "Musterstrasse 12",
                                  SAMPLE_POSTCODE,
                                  null,
                                  CountyCouncil.OTHER);
  }

  /**
   * Test method for {@link Participant#Participant(String, String, Gender, Denomination, Date, String, int, String,
   * CountyCouncil)} .
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testParticipantRFSTNE8() {
    participant = new Participant("Mustermann",
                                  "Max",
                                  Gender.MALE,
                                  Denomination.OTHER,
                                  new Date(),
                                  "Musterstrasse 12",
                                  SAMPLE_POSTCODE,
                                  "Musterhausen",
                                  null);
  }

  /**
   * Test method for {@link Participant#Participant(String, String, Gender, Denomination, Date, String, int, String,
   * CountyCouncil)} .
   */
  @Test(expected = IllegalArgumentException.class)
  public final void testParticipantIAE() {
    participant = new Participant("Mustermann",
                                  "Max",
                                  Gender.MALE,
                                  Denomination.OTHER,
                                  new Date(),
                                  "Musterstrasse 12",
                                  -1,
                                  "Musterhausen",
                                  CountyCouncil.OTHER);
  }

  /**
   * Test method for {@link Participant#Participant(String, String, Gender, Denomination, Date, String, int, String,
   * CountyCouncil)} .
   */
  @Test
  public final void testParticipant() {
    final Date birth = new Date();
    participant = new Participant("Mustermann",
                                  "Max",
                                  Gender.MALE,
                                  Denomination.OTHER,
                                  birth,
                                  "Musterstrasse 12",
                                  SAMPLE_POSTCODE,
                                  "Musterhausen",
                                  CountyCouncil.OTHER);
    assertThat(participant.getForeName()).isEqualTo("Max");
    assertThat(participant.getLastName()).isEqualTo("Mustermann");
    assertThat(participant.getGender()).isEqualTo(Gender.MALE);
    assertThat(participant.getDenomination()).isEqualTo(Denomination.OTHER);
    assertThat(participant.getBirthDate()).isEqualTo(birth);
    assertThat(participant.getStreet()).isEqualTo("Musterstrasse 12");
    assertThat(participant.getPostCode()).isEqualTo(SAMPLE_POSTCODE);
    assertThat(participant.getCity()).isEqualTo("Musterhausen");
    assertThat(participant.getCountyCouncil()).isEqualTo(CountyCouncil.OTHER);
  }

  /**
   * Test method for {@link Participant#getLastName()} and {@link Participant#setLastName(String)}.
   */
  @Test
  public final void testSetAndGetLastName() {
    participant.setLastName("last");
    assertThat(participant.getLastName()).isEqualTo("last");

    participant.setLastName("another");
    assertThat(participant.getLastName()).isEqualTo("another");

    participant.setLastName("");
    assertThat(participant.getLastName()).isEmpty();
  }

  /**
   * Test method for {@link Participant#getForeName()} and {@link Participant#setForeName(String)}.
   */
  @Test
  public final void testSetAndGetForeName() {
    participant.setForeName("first");
    assertThat(participant.getForeName()).isEqualTo("first");

    participant.setForeName("another");
    assertThat(participant.getForeName()).isEqualTo("another");

    participant.setForeName("");
    assertThat(participant.getForeName()).isEmpty();
  }

  /**
   * Test method for {@link Participant#getGender()} and {@link Participant#setGender(Gender)}.
   */
  @Test
  public final void testSetAndGetGender() {
    participant.setGender(Gender.FEMALE);
    assertThat(participant.getGender()).isEqualTo(Gender.FEMALE);

    participant.setGender(Gender.MALE);
    assertThat(participant.getGender()).isEqualTo(Gender.MALE);
  }

  /**
   * Test method for {@link Participant#getDenomination()} and {@link Participant#setDenomination(Denomination)}.
   */
  @Test
  public final void testSetAndGetDenomination() {
    participant.setDenomination(Denomination.CATHOLIC);
    assertThat(participant.getDenomination()).isEqualTo(Denomination.CATHOLIC);

    participant.setDenomination(Denomination.JEWISH);
    assertThat(participant.getDenomination()).isEqualTo(Denomination.JEWISH);

    participant.setDenomination(Denomination.FREE_CHURCH);
    assertThat(participant.getDenomination()).isEqualTo(Denomination.FREE_CHURCH);
  }

  /**
   * Test method for {@link Participant#getBirthDate()} and {@link Participant#setBirthDate(Date)}.
   */
  @Test
  public final void testSetAndGetBirthDate() {
    final Date birth = new Date();
    final Date copy = (Date) birth.clone();
    participant.setBirthDate(birth);
    assertThat(participant.getBirthDate()).isEqualTo(birth);

    birth.setTime(0);
    assertThat(participant.getBirthDate()).isNotEqualTo(birth);
    assertThat(participant.getBirthDate()).isEqualTo(copy);

    final Date d = participant.getBirthDate();
    d.setTime(0);
    assertThat(participant.getBirthDate()).isEqualTo(copy);
  }

  /**
   * Test method for {@link Participant#getStreet()} and {@link Participant#setStreet(String)}.
   */
  @Test
  public final void testSetAndGetStreet() {
    participant.setStreet("street");
    assertThat(participant.getStreet()).isEqualTo("street");

    participant.setStreet("other");
    assertThat(participant.getStreet()).isEqualTo("other");

    participant.setStreet("");
    assertThat(participant.getStreet()).isEqualTo("");
  }

  /**
   * Test method for {@link Participant#getPostCode()} and {@link Participant#setPostCode(int)}.
   */
  @Test
  public final void testSetAndGetPostCode() {
    final int pc1 = 1;
    participant.setPostCode(pc1);
    assertThat(participant.getPostCode()).isEqualTo(pc1);

    final int pc2 = 13423;
    participant.setPostCode(pc2);
    assertThat(participant.getPostCode()).isEqualTo(pc2);

    final int pc3 = 77789;
    participant.setPostCode(pc3);
    assertThat(participant.getPostCode()).isEqualTo(Integer.valueOf(pc3).intValue());
  }

  /**
   * Test method for {@link Participant#getPostCode()} and {@link Participant#setPostCode(int)}.
   */
  @Test(expected = IllegalArgumentException.class)
  public final void testSetAndGetPostCodeIAE1() {
    participant.setPostCode(-1);
  }

  /**
   * Test method for {@link Participant#getPostCode()} and {@link Participant#setPostCode(int)}.
   */
  @Test(expected = IllegalArgumentException.class)
  public final void testSetAndGetPostCodeIAE2() {
    final int tooHigh = 100000;
    participant.setPostCode(tooHigh);
  }

  /**
   * Test method for {@link Participant#getCity()} and {@link Participant#setCity(String)}.
   */
  @Test
  public final void testSetAndGetCity() {
    participant.setCity("stadt");
    assertThat(participant.getCity()).isEqualTo("stadt");

    participant.setCity("");
    assertThat(participant.getCity()).isEqualTo("");

    participant.setCity("...");
    assertThat(participant.getCity()).isEqualTo("...");
  }

  /**
   * Test method for {@link Participant#getStreetPostal()} and {@link Participant#setStreetPostal(String)}.
   */
  @Test
  public final void testSetAndGetStreetPostal() {
    participant.setStreetPostal("street");
    assertThat(participant.getStreetPostal()).isEqualTo("street");

    participant.setStreetPostal("other");
    assertThat(participant.getStreetPostal()).isEqualTo("other");

    participant.setStreetPostal("");
    assertThat(participant.getStreetPostal()).isEqualTo("");
  }

  /**
   * Test method for {@link Participant#getPostCodePostal()} and {@link Participant#setPostCodePostal(int)}.
   */
  @Test
  public final void testSetAndGetPostCodePostal() {
    final int pc1 = 1;
    participant.setPostCodePostal(pc1);
    assertThat(participant.getPostCodePostal()).isEqualTo(pc1);

    final int pc2 = 13423;
    participant.setPostCodePostal(pc2);
    assertThat(participant.getPostCodePostal()).isEqualTo(pc2);

    final int pc3 = 77789;
    participant.setPostCodePostal(pc3);
    assertThat(participant.getPostCodePostal()).isEqualTo(Integer.valueOf(pc3).intValue());
  }

  /**
   * Test method for {@link Participant#getPostCodePostal()} and {@link Participant#setPostCodePostal(int)}.
   */
  @Test(expected = IllegalArgumentException.class)
  public final void testSetAndGetPostCodePostalIAE1() {
    participant.setPostCodePostal(-1);
  }

  /**
   * Test method for {@link Participant#getPostCodePostal()} and {@link Participant#setPostCodePostal(int)}.
   */
  @Test(expected = IllegalArgumentException.class)
  public final void testSetAndGetPostCodePostalIAE2() {
    final int tooHigh = 100000;
    participant.setPostCodePostal(tooHigh);
  }

  /**
   * Test method for {@link Participant#getCityPostal()} and {@link Participant#setCityPostal(String)}.
   */
  @Test
  public final void testSetAndGetCityPostal() {
    participant.setCityPostal("stadt");
    assertThat(participant.getCityPostal()).isEqualTo("stadt");

    participant.setCityPostal("");
    assertThat(participant.getCityPostal()).isEqualTo("");

    participant.setCityPostal("...");
    assertThat(participant.getCityPostal()).isEqualTo("...");
  }

  /**
   * Test method for {@link Participant#getPhone()} and {@link Participant#setPhone(String)}.
   */
  @Test
  public final void testSetAndGetPhone() {
    participant.setPhone("...");
    assertThat(participant.getPhone()).isEqualTo("...");

    participant.setPhone(null);
    assertThat(participant.getPhone()).isNull();

    participant.setPhone("0123/456789");
    assertThat(participant.getPhone()).isEqualTo("0123/456789");
  }

  /**
   * Test method for {@link Participant#getFax()} and {@link Participant#setFax(String)}.
   */
  @Test
  public final void testSetAndGetFax() {
    participant.setFax("...");
    assertThat(participant.getFax()).isEqualTo("...");

    participant.setFax(null);
    assertThat(participant.getFax()).isNull();

    participant.setFax("0123/456789");
    assertThat(participant.getFax()).isEqualTo("0123/456789");
  }

  /**
   * Test method for {@link Participant#getMobilePhone()} and {@link Participant#setMobilePhone(String)}.
   */
  @Test
  public final void testSetAndGetMobilePhone() {
    participant.setMobilePhone("...");
    assertThat(participant.getMobilePhone()).isEqualTo("...");

    participant.setMobilePhone(null);
    assertThat(participant.getMobilePhone()).isNull();

    participant.setMobilePhone("0123/456789");
    assertThat(participant.getMobilePhone()).isEqualTo("0123/456789");
  }

  /**
   * Test method for {@link Participant#getPhoneOfParents()} and {@link Participant#setPhoneOfParents(String)}.
   */
  @Test
  public final void testSetAndGetPhoneOfParents() {
    participant.setPhoneOfParents("...");
    assertThat(participant.getPhoneOfParents()).isEqualTo("...");

    participant.setPhoneOfParents(null);
    assertThat(participant.getPhoneOfParents()).isNull();

    participant.setPhoneOfParents("0123/456789");
    assertThat(participant.getPhoneOfParents()).isEqualTo("0123/456789");
  }

  /**
   * Test method for {@link Participant#getMailAddress()} and {@link Participant#setMailAddress(String)}.
   */
  @Test
  public final void testSetAndGetMailAddress() {
    participant.setMailAddress("...");
    assertThat(participant.getMailAddress()).isEqualTo("...");

    participant.setMailAddress(null);
    assertThat(participant.getMailAddress()).isEqualTo(null);

    participant.setMailAddress("abc@def-ghi.jk");
    assertThat(participant.getMailAddress()).isEqualTo("abc@def-ghi.jk");
  }

  /**
   * Test method for {@link Participant#getCountyCouncil()} and {@link Participant#setCountyCouncil(CountyCouncil)}.
   */
  @Test
  public final void testSetAndGetCountyCouncil() {
    participant.setCountyCouncil(CountyCouncil.CITY_FRANKENTHAL);
    assertThat(participant.getCountyCouncil()).isEqualTo(CountyCouncil.CITY_FRANKENTHAL);

    participant.setCountyCouncil(CountyCouncil.CITY_LUDWIGSHAFEN);
    assertThat(participant.getCountyCouncil()).isEqualTo(CountyCouncil.CITY_LUDWIGSHAFEN);

    participant.setCountyCouncil(CountyCouncil.COUNTY_BAD_KREUZNACH);
    assertThat(participant.getCountyCouncil()).isEqualTo(CountyCouncil.COUNTY_BAD_KREUZNACH);
  }

  /**
   * Test method for {@link Participant#getBankCodeNumber()} and {@link Participant#setBankCodeNumber(int)}.
   */
  @Test
  public final void testSetAndGetBankCodeNumber() {
    final int nr1 = 1234567;
    participant.setBankCodeNumber(nr1);
    assertThat(participant.getBankCodeNumber()).isEqualTo(nr1);

    final int nr2 = 0;
    participant.setBankCodeNumber(nr2);
    assertThat(participant.getBankCodeNumber()).isEqualTo(nr2);

    final int nr3 = 98765432;
    participant.setBankCodeNumber(nr3);
    assertThat(participant.getBankCodeNumber()).isEqualTo(nr3);
  }

  /**
   * Test method for {@link Participant#getBank()} and {@link Participant#setBank(String)}.
   */
  @Test
  public final void testSetAndGetBank() {
    participant.setBank("...");
    assertThat(participant.getBank()).isEqualTo("...");

    participant.setBank(null);
    assertThat(participant.getBank()).isEqualTo(null);

    participant.setBank("bank of him");
    assertThat(participant.getBank()).isEqualTo("bank of him");
  }

  /**
   * Test method for {@link Participant#getBankAccountNumber()} and {@link Participant#setBankAccountNumber(int)}.
   */
  @Test
  public final void testSetAndGetBankAccountNumber() {
    final int nr1 = 1234567;
    participant.setBankAccountNumber(nr1);
    assertThat(participant.getBankAccountNumber()).isEqualTo(nr1);

    final int nr2 = 0;
    participant.setBankAccountNumber(nr2);
    assertThat(participant.getBankAccountNumber()).isEqualTo(nr2);

    final int nr3 = 98765432;
    participant.setBankAccountNumber(nr3);
    assertThat(participant.getBankAccountNumber()).isEqualTo(nr3);
  }

  /**
   * Test method for {@link Participant#getComment()} and {@link Participant#setComment(String)}.
   */
  @Test
  public final void testSetAndGetComment() {
    participant.setComment("...");
    assertThat(participant.getComment()).isEqualTo("...");

    participant.setComment(null);
    assertThat(participant.getComment()).isEqualTo(null);

    participant.setComment("this is a stupid comment! <-");
    assertThat(participant.getComment()).isEqualTo("this is a stupid comment! <-");
  }

  /**
   * Test method for {@link Participant#getDateSinceInDataBase()} and {@link Participant#setDateSinceInDataBase(Date)}.
   */
  @Test
  public final void testSetAndGetDateSinceInDataBase() {
    participant.setDateSinceInDataBase(null);
    assertThat(participant.getDateSinceInDataBase()).isNull();

    Date d1 = new Date();
    participant.setDateSinceInDataBase(d1);
    assertThat(participant.getDateSinceInDataBase()).isEqualTo(d1);

    final Date d2 = new Date(0);
    participant.setDateSinceInDataBase(d2);
    assertThat(participant.getDateSinceInDataBase()).isEqualTo(d2);
    d2.setTime(d1.getTime());
    assertThat(participant.getDateSinceInDataBase()).isNotEqualTo(d2);

    participant.setDateSinceInDataBase(d2);
    d1 = participant.getDateSinceInDataBase();
    d1.setTime(12);
    assertThat(participant.getDateSinceInDataBase()).isEqualTo(d2);
  }

  /**
   * Test method for {@link Participant#getDateUpToInSystem()} and {@link Participant#setDateUpToInSystem(Date)}.
   */
  @Test
  public final void testSetAndGetDateUpToInSystem() {
    participant.setDateUpToInSystem(null);
    assertThat(participant.getDateUpToInSystem()).isNull();

    Date d1 = new Date();
    participant.setDateUpToInSystem(d1);
    assertThat(participant.getDateUpToInSystem()).isEqualTo(d1);

    final Date d2 = new Date(0);
    participant.setDateUpToInSystem(d2);
    assertThat(participant.getDateUpToInSystem()).isEqualTo(d2);
    d2.setTime(d1.getTime());
    assertThat(participant.getDateUpToInSystem()).isNotEqualTo(d2);

    participant.setDateUpToInSystem(d2);
    d1 = participant.getDateUpToInSystem();
    d1.setTime(12);
    assertThat(participant.getDateUpToInSystem()).isEqualTo(d2);
  }

  /**
   * Test method for {@link Participant#getId()}.
   */
  @Test
  public final void testGetId() {
    final long id = participant.getId();
    participant = new Participant("name",
                                  "first",
                                  Gender.FEMALE,
                                  Denomination.EVANGELIC,
                                  new Date(),
                                  "street",
                                  SAMPLE_POSTCODE,
                                  "city",
                                  CountyCouncil.COUNTY_GERMERSHEIM);

    assertThat(participant.getId()).isEqualTo(id + 1);
    participant = new Participant(id + 2,
                                  "name",
                                  "first",
                                  Gender.FEMALE,
                                  Denomination.EVANGELIC,
                                  new Date(),
                                  "street",
                                  SAMPLE_POSTCODE,
                                  "city",
                                  CountyCouncil.COUNTY_GERMERSHEIM);
    assertThat(participant.getId()).isEqualTo(id + 2);
  }

  /**
   * Test method for {@link Participant#toString()}.
   */
  @Test
  public final void testToString() {
    assertThat(participant.toString()).contains(String.valueOf(participant.getId()));
  }

  /**
   * Test method for {@link Participant#equals(Object)}.
   */
  @Test
  public final void testEquals() {
    Participant p = new Participant("Mustermann",
                                    "Hans",
                                    Gender.FEMALE,
                                    Denomination.OTHER,
                                    new Date(),
                                    "Musterstrasse 12",
                                    SAMPLE_POSTCODE,
                                    "Musterhausen",
                                    CountyCouncil.OTHER);

    p.setBank("bank");
    p.setBankAccountNumber(4);
    p.setBankCodeNumber(7);
    p.setComment("com");
    p.setCountyCouncil(CountyCouncil.CITY_LANDAU);
    p.setDateSinceInDataBase(new Date(12));
    p.setDateUpToInSystem(new Date(24));
    p.setDenomination(Denomination.FREE_CHURCH);
    p.setFax("fax");

    assertThat(participant).isEqualTo(participant);
    assertThat(participant).isNotEqualTo(null);
    assertThat(participant).isNotEqualTo("participant");
    assertThat(participant).isNotEqualTo(p);
    assertThat(p).isNotEqualTo(participant);

    participant.setBank("bank");
    assertThat(participant).isNotEqualTo(p);

    participant.setBankAccountNumber(4);
    assertThat(participant).isNotEqualTo(p);

    participant.setBankCodeNumber(7);
    assertThat(participant).isNotEqualTo(p);

    p.setBirthDate(participant.getBirthDate());
    assertThat(participant).isNotEqualTo(p);
    assertThat(p).isNotEqualTo(participant);

    participant.setComment("comment");
    assertThat(participant).isNotEqualTo(p);

    participant.setComment("com");
    assertThat(participant).isNotEqualTo(p);

    participant.setCountyCouncil(CountyCouncil.CITY_LANDAU);
    assertThat(participant).isNotEqualTo(p);

    participant.setDateSinceInDataBase(new Date());
    assertThat(participant).isNotEqualTo(p);

    participant.setDateSinceInDataBase(new Date(12));
    assertThat(participant).isNotEqualTo(p);

    participant.setDateUpToInSystem(new Date());
    assertThat(participant).isNotEqualTo(p);

    participant.setDateUpToInSystem(new Date(24));
    assertThat(participant).isNotEqualTo(p);

    participant.setDenomination(p.getDenomination());
    assertThat(participant).isNotEqualTo(p);

    participant.setFax("d");
    assertThat(participant).isNotEqualTo(p);

    participant.setFax(p.getFax());
    assertThat(participant).isNotEqualTo(p);

    participant.setForeName(p.getForeName());
    assertThat(participant).isNotEqualTo(p);

    participant.setGender(p.getGender());
    assertThat(participant).isNotEqualTo(p);

    p = new Participant(participant);
    p.setLastName("l");
    assertThat(participant).isNotEqualTo(p);

    participant.setLastName(p.getLastName());
    p.setStreet("s");
    assertThat(participant).isNotEqualTo(p);

    participant.setStreet(p.getStreet());
    p.setMailAddress("mail-addy");
    assertThat(participant).isNotEqualTo(p);

    participant.setMailAddress("mail");
    assertThat(participant).isNotEqualTo(p);

    p.setMailAddress(participant.getMailAddress());
    p.setMobilePhone("mobile");
    assertThat(participant).isNotEqualTo(p);

    participant.setMobilePhone("phone");
    assertThat(participant).isNotEqualTo(p);
    participant.setMobilePhone(p.getMobilePhone());
    p.setPhone("mobile");
    assertThat(participant).isNotEqualTo(p);

    participant.setPhone("phone");
    assertThat(participant).isNotEqualTo(p);
    p.setPhone(participant.getPhone());
    p.setPhoneOfParents("pop");
    assertThat(participant).isNotEqualTo(p);

    participant.setPhoneOfParents("phone-of-parents");
    assertThat(participant).isNotEqualTo(p);
    p.setPhoneOfParents(participant.getPhoneOfParents());

    assertThat(participant).isEqualTo(p);
    p.setPostCodePostal(177);
    assertThat(participant).isNotEqualTo(p);
  }

  /**
   * Test method for {@link Participant#equals(Object)}.
   */
  @Test
  public final void testEquals_OtherDateNull() {
    final Participant p = new Participant(participant);

    p.setDateUpToInSystem(new Date(24));

    assertThat(p).isNotEqualTo(participant);
  }
}
