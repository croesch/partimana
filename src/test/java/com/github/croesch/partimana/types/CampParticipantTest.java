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
    this.participant = new Participant("Mustermann",
                                       "Max",
                                       Gender.MALE,
                                       Denomination.OTHER,
                                       new Date(),
                                       "Musterstrasse 12",
                                       SAMPLE_POSTCODE,
                                       "Musterhausen",
                                       CountyCouncil.OTHER);

    this.campParticipant = new CampParticipant(this.participant);
  }

  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testCampParticipant_Null() {
    new CampParticipant(null);
  }

  @Test
  public final void testCampParticipant_Participant() {
    final Date d1 = new Date(123);

    this.participant.setBank("bank");
    this.participant.setBankAccountNumber(1);
    this.participant.setBankCodeNumber(12);
    this.participant.setComment("comment");
    this.participant.setDateSinceInDataBase(d1);
    this.participant.setDateUpToInSystem(d1);
    this.participant.setDenomination(Denomination.JEWISH);
    this.participant.setFax("fax");
    this.participant.setMailAddress("mail");
    this.participant.setMobilePhone("mobile");
    this.participant.setPhone("phone");
    this.participant.setPhoneOfParents("phone");
    this.participant.setCityPostal("city");
    this.participant.setPostCodePostal(3124);
    this.participant.setStreetPostal("street");

    assertCampParticipantCallsParticipant(this.campParticipant);

    this.participant.setForeName("Hanz");
    this.participant.setLastName("Decker");
    this.participant.setStreet("a new street 42");
    this.participant.setPostCode(12500);
    this.participant.setCity("a new city");

    assertCampParticipantCallsParticipant(this.campParticipant);

    this.participant.setForeName("Hans");
    this.participant.setLastName("Becker");
    this.participant.setStreet("Examplestreet 12");
    this.participant.setPostCode(12345);
    this.participant.setCity("Examplecity");

    assertCampParticipantCallsParticipant(this.campParticipant);
  }

  private void assertCampParticipantCallsParticipant(final CampParticipant cp) {
    assertThat(cp.getId()).isEqualTo(this.participant.getId());
    assertThat(cp.getForeName()).isEqualTo(this.participant.getForeName());
    assertThat(cp.getLastName()).isEqualTo(this.participant.getLastName());
    assertThat(cp.getStreet()).isEqualTo(this.participant.getStreet());
    assertThat(cp.getPostCode()).isEqualTo(String.valueOf(this.participant.getPostCode()));
    assertThat(cp.getCity()).isEqualTo(this.participant.getCity());
  }

  @Test
  public final void testRole() {
    for (final Role role : Role.values()) {
      this.campParticipant.setRole(role);
      assertThat(this.campParticipant.getRole()).isEqualTo(role);
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSignedIn_Null() {
    this.campParticipant.setSignedIn(null);
  }

  @Test
  public final void testSignedIn() {
    final Date before = new Date();
    this.campParticipant = new CampParticipant(this.participant);
    final Date after = new Date();

    assertThat(this.campParticipant.getSignedIn()).isNotNull();
    assertThat(this.campParticipant.getSignedIn().before(before)).isFalse();
    assertThat(this.campParticipant.getSignedIn().after(after)).isFalse();

    Date signedIn = new Date(12345678901L);
    this.campParticipant.setSignedIn(signedIn);
    assertThat(this.campParticipant.getSignedIn()).isEqualTo(signedIn);

    signedIn = new Date(22345678901L);
    this.campParticipant.setSignedIn(signedIn);
    assertThat(this.campParticipant.getSignedIn()).isEqualTo(signedIn);
  }

  @Test
  public final void testSignedOff() {
    assertThat(this.campParticipant.getSignedOff()).isNull();

    Date signedOff = new Date(12345678901L);
    this.campParticipant.setSignedOff(signedOff);
    assertThat(this.campParticipant.getSignedOff()).isEqualTo(signedOff);

    signedOff = new Date(22345678901L);
    this.campParticipant.setSignedOff(signedOff);
    assertThat(this.campParticipant.getSignedOff()).isEqualTo(signedOff);
  }

  @Test
  public final void testEquals() {
    assertThat(this.campParticipant).isNotEqualTo(this.participant);
    assertThat(this.campParticipant).isNotEqualTo(null);
    assertThat(this.campParticipant).isEqualTo(this.campParticipant);
    assertThat(this.campParticipant).isEqualTo(new CampParticipant(this.participant));

    final Participant p2 = new Participant(this.participant);

    assertThat(this.campParticipant).isEqualTo(new CampParticipant(p2));

    p2.setBank("blub");
    assertThat(this.campParticipant).isNotEqualTo(new CampParticipant(p2));
  }

  @Test
  public final void testHashCode() {
    assertThat(this.campParticipant.hashCode()).isNotEqualTo(this.participant.hashCode());
    assertThat(this.campParticipant.hashCode()).isEqualTo(this.campParticipant.hashCode());
    assertThat(this.campParticipant.hashCode()).isEqualTo(new CampParticipant(this.participant).hashCode());

    final Participant p2 = new Participant(this.participant);

    assertThat(this.campParticipant.hashCode()).isEqualTo(new CampParticipant(p2).hashCode());

    p2.setBank("blub");
    assertThat(this.campParticipant.hashCode()).isNotEqualTo(new CampParticipant(p2).hashCode());
  }


  @Test
  public final void testGetGender() {
    assertThat(this.campParticipant.getGender()).isEqualTo(Gender.MALE);

    participant.setGender(Gender.FEMALE);
    assertThat(this.campParticipant.getGender()).isEqualTo(Gender.FEMALE);
  }
}
