package com.github.croesch.partimana.types;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

/**
 * Provides different test methods for {@link CampParticipant}
 *
 * @author croesch
 * @since Date: Sep 16, 2012
 */
public class CampParticipantTest {

  /** example post code */
  private static final int SAMPLE_POSTCODE = 11996;

  /** {@link Participant} to test with */
  private Participant participant;

  private CampParticipant campParticipant;

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

    campParticipant = new CampParticipant(participant);
  }

  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testCampParticipant_Null() {
    new CampParticipant(null);
  }

  @Test
  public final void testCampParticipant_Participant() {
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

    assertCampParticipantCallsParticipant(campParticipant);

    participant.setForeName("Hanz");
    participant.setLastName("Decker");
    participant.setStreet("a new street 42");
    participant.setPostCode(12500);
    participant.setCity("a new city");

    assertCampParticipantCallsParticipant(campParticipant);

    participant.setForeName("Hans");
    participant.setLastName("Becker");
    participant.setStreet("Examplestreet 12");
    participant.setPostCode(12345);
    participant.setCity("Examplecity");

    assertCampParticipantCallsParticipant(campParticipant);
  }

  private void assertCampParticipantCallsParticipant(final CampParticipant cp) {
    assertThat(cp.getId()).isEqualTo(participant.getId());
    assertThat(cp.getForeName()).isEqualTo(participant.getForeName());
    assertThat(cp.getLastName()).isEqualTo(participant.getLastName());
    assertThat(cp.getStreet()).isEqualTo(participant.getStreet());
    assertThat(cp.getPostCode()).isEqualTo(String.valueOf(participant.getPostCode()));
    assertThat(cp.getCity()).isEqualTo(participant.getCity());
  }

  @Test
  public final void testRole() {
    for (final Role role : Role.values()) {
      campParticipant.setRole(role);
      assertThat(campParticipant.getRole()).isEqualTo(role);
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSignedIn_Null() {
    campParticipant.setSignedIn(null);
  }

  @Test
  public final void testSignedIn() {
    final Date before = new Date();
    campParticipant = new CampParticipant(participant);
    final Date after = new Date();

    assertThat(campParticipant.getSignedIn()).isNotNull();
    assertThat(campParticipant.getSignedIn().before(before)).isFalse();
    assertThat(campParticipant.getSignedIn().after(after)).isFalse();

    Date signedIn = new Date(12345678901L);
    campParticipant.setSignedIn(signedIn);
    assertThat(campParticipant.getSignedIn()).isEqualTo(signedIn);

    signedIn = new Date(22345678901L);
    campParticipant.setSignedIn(signedIn);
    assertThat(campParticipant.getSignedIn()).isEqualTo(signedIn);
  }

  @Test
  public final void testSignedOff() {
    assertThat(campParticipant.getSignedOff()).isNull();

    Date signedOff = new Date(12345678901L);
    campParticipant.setSignedOff(signedOff);
    assertThat(campParticipant.getSignedOff()).isEqualTo(signedOff);

    signedOff = new Date(22345678901L);
    campParticipant.setSignedOff(signedOff);
    assertThat(campParticipant.getSignedOff()).isEqualTo(signedOff);
  }

  @Test
  public final void testEquals() {
    assertThat(campParticipant).isNotEqualTo(participant);
    assertThat(campParticipant).isNotEqualTo(null);
    assertThat(campParticipant).isEqualTo(campParticipant);
    assertThat(campParticipant).isEqualTo(new CampParticipant(participant));

    final Participant p2 = new Participant(participant);

    assertThat(campParticipant).isEqualTo(new CampParticipant(p2));

    p2.setBank("blub");
    assertThat(campParticipant).isNotEqualTo(new CampParticipant(p2));
  }

  @Test
  public final void testHashCode() {
    assertThat(campParticipant.hashCode()).isNotEqualTo(participant.hashCode());
    assertThat(campParticipant.hashCode()).isEqualTo(campParticipant.hashCode());
    assertThat(campParticipant.hashCode()).isEqualTo(new CampParticipant(participant).hashCode());

    final Participant p2 = new Participant(participant);

    assertThat(campParticipant.hashCode()).isEqualTo(new CampParticipant(p2).hashCode());

    p2.setBank("blub");
    assertThat(campParticipant.hashCode()).isNotEqualTo(new CampParticipant(p2).hashCode());
  }

  @Test
  public final void testGetGender() {
    assertThat(campParticipant.getGender()).isEqualTo(Gender.MALE);

    participant.setGender(Gender.FEMALE);
    assertThat(campParticipant.getGender()).isEqualTo(Gender.FEMALE);
  }

  @Test
  public final void testGetDenomination() {
    assertThat(campParticipant.getDenomination()).isEqualTo(participant.getDenomination());

    participant.setDenomination(Denomination.FREE_CHURCH);
    assertThat(campParticipant.getDenomination()).isEqualTo(Denomination.FREE_CHURCH);
  }

  @Test
  public final void testGetBirthDate() {
    assertThat(campParticipant.getBirthDate()).isEqualTo(participant.getBirthDate());

    Date birthDate = new Date(123456789123456L);
    participant.setBirthDate(birthDate);
    assertThat(campParticipant.getBirthDate()).isEqualTo(birthDate);
  }

  @Test
  public final void testGetPhone() {
    assertThat(campParticipant.getPhone()).isEqualTo(participant.getPhone());

    participant.setPhone("444-333-2222");
    assertThat(campParticipant.getPhone()).isEqualTo("444-333-2222");

    participant.setPhone(null);
    assertThat(campParticipant.getPhone()).isNull();
  }

  @Test
  public final void testGetMobilePhone() {
    assertThat(campParticipant.getMobilePhone()).isEqualTo(participant.getMobilePhone());

    participant.setMobilePhone("444-333-2222");
    assertThat(campParticipant.getMobilePhone()).isEqualTo("444-333-2222");

    participant.setMobilePhone(null);
    assertThat(campParticipant.getMobilePhone()).isNull();
  }

  @Test
  public final void testGetMailAddress() {
    assertThat(campParticipant.getMailAddress()).isEqualTo(participant.getMailAddress());

    participant.setMailAddress("peter.pan@web.de");
    assertThat(campParticipant.getMailAddress()).isEqualTo("peter.pan@web.de");

    participant.setMailAddress(null);
    assertThat(campParticipant.getMailAddress()).isNull();
  }

  @Test
  public final void testGetCountyCouncil() {
    assertThat(campParticipant.getCountyCouncil()).isEqualTo(participant.getCountyCouncil());

    participant.setCountyCouncil(CountyCouncil.CITY_FRANKENTHAL);
    assertThat(campParticipant.getCountyCouncil()).isEqualTo(CountyCouncil.CITY_FRANKENTHAL);
  }
}
