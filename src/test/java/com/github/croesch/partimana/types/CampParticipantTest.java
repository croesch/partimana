package com.github.croesch.partimana.types;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;

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
    this.participant.setPossibleAGE(true);
    this.participant.setPossibleBoard(false);
    this.participant.setPossibleExtendedBoard(false);
    this.participant.setPossibleKitchen(true);
    this.participant.setPossibleMAK(true);
    this.participant.setPossibleMisc(false);
    this.participant.setPossibleParticipant(false);
    this.participant.setPossibleSeminar(false);
    this.participant.setPossibleStaff(false);
    this.participant.setPossibleStaffYouth(true);
    this.participant.setCityPostal("city");
    this.participant.setPostCodePostal(3124);
    this.participant.setStreetPostal("street");

    assertCampParticipantCallsParticipant(this.campParticipant);

    this.participant.setPossibleAGE(true);
    this.participant.setPossibleBoard(true);
    this.participant.setPossibleExtendedBoard(true);
    this.participant.setPossibleKitchen(true);
    this.participant.setPossibleMAK(true);
    this.participant.setPossibleMisc(true);
    this.participant.setPossibleParticipant(true);
    this.participant.setPossibleSeminar(true);
    this.participant.setPossibleStaff(true);
    this.participant.setPossibleStaffYouth(true);

    assertCampParticipantCallsParticipant(this.campParticipant);

    this.participant.setPossibleAGE(false);
    this.participant.setPossibleBoard(false);
    this.participant.setPossibleExtendedBoard(false);
    this.participant.setPossibleKitchen(false);
    this.participant.setPossibleMAK(false);
    this.participant.setPossibleMisc(false);
    this.participant.setPossibleParticipant(false);
    this.participant.setPossibleSeminar(false);
    this.participant.setPossibleStaff(false);
    this.participant.setPossibleStaffYouth(false);

    assertCampParticipantCallsParticipant(this.campParticipant);
  }

  private void assertCampParticipantCallsParticipant(final CampParticipant cp) {
    assertThat(cp.isPossibleAGE()).isEqualTo(this.participant.isPossibleAGE());
    assertThat(cp.isPossibleBoard()).isEqualTo(this.participant.isPossibleBoard());
    assertThat(cp.isPossibleExtendedBoard()).isEqualTo(this.participant.isPossibleExtendedBoard());
    assertThat(cp.isPossibleKitchen()).isEqualTo(this.participant.isPossibleKitchen());
    assertThat(cp.isPossibleMAK()).isEqualTo(this.participant.isPossibleMAK());
    assertThat(cp.isPossibleMisc()).isEqualTo(this.participant.isPossibleMisc());
    assertThat(cp.isPossibleParticipant()).isEqualTo(this.participant.isPossibleParticipant());
    assertThat(cp.isPossibleSeminar()).isEqualTo(this.participant.isPossibleSeminar());
    assertThat(cp.isPossibleStaff()).isEqualTo(this.participant.isPossibleStaff());
    assertThat(cp.isPossibleStaffYouth()).isEqualTo(this.participant.isPossibleStaffYouth());
  }

  @Test
  public final void testSetAndIsParticipant() {
    this.campParticipant.setParticipant(false);
    assertThat(this.campParticipant.isParticipant()).isFalse();

    this.campParticipant.setParticipant(true);
    assertThat(this.campParticipant.isParticipant()).isTrue();

    this.campParticipant.setParticipant(false);
    assertThat(this.campParticipant.isParticipant()).isFalse();
  }

  @Test
  public final void testSetAndIsStaffYouth() {
    this.campParticipant.setStaffYouth(false);
    assertThat(this.campParticipant.isStaffYouth()).isFalse();

    this.campParticipant.setStaffYouth(true);
    assertThat(this.campParticipant.isStaffYouth()).isTrue();

    this.campParticipant.setStaffYouth(false);
    assertThat(this.campParticipant.isStaffYouth()).isFalse();
  }

  @Test
  public final void testSetAndIsAGE() {
    this.campParticipant.setAGE(false);
    assertThat(this.campParticipant.isAGE()).isFalse();

    this.campParticipant.setAGE(true);
    assertThat(this.campParticipant.isAGE()).isTrue();

    this.campParticipant.setAGE(false);
    assertThat(this.campParticipant.isAGE()).isFalse();
  }

  @Test
  public final void testSetAndIsMAK() {
    this.campParticipant.setMAK(false);
    assertThat(this.campParticipant.isMAK()).isFalse();

    this.campParticipant.setMAK(true);
    assertThat(this.campParticipant.isMAK()).isTrue();

    this.campParticipant.setMAK(false);
    assertThat(this.campParticipant.isMAK()).isFalse();
  }

  @Test
  public final void testSetAndIsBoard() {
    this.campParticipant.setBoard(false);
    assertThat(this.campParticipant.isBoard()).isFalse();

    this.campParticipant.setBoard(true);
    assertThat(this.campParticipant.isBoard()).isTrue();

    this.campParticipant.setBoard(false);
    assertThat(this.campParticipant.isBoard()).isFalse();
  }

  @Test
  public final void testSetAndIsExtendedBoard() {
    this.campParticipant.setExtendedBoard(false);
    assertThat(this.campParticipant.isExtendedBoard()).isFalse();

    this.campParticipant.setExtendedBoard(true);
    assertThat(this.campParticipant.isExtendedBoard()).isTrue();

    this.campParticipant.setExtendedBoard(false);
    assertThat(this.campParticipant.isExtendedBoard()).isFalse();
  }

  @Test
  public final void testSetAndIsKitchen() {
    this.campParticipant.setKitchen(false);
    assertThat(this.campParticipant.isKitchen()).isFalse();

    this.campParticipant.setKitchen(true);
    assertThat(this.campParticipant.isKitchen()).isTrue();

    this.campParticipant.setKitchen(false);
    assertThat(this.campParticipant.isKitchen()).isFalse();
  }

  @Test
  public final void testSetAndIsMisc() {
    this.campParticipant.setMisc(false);
    assertThat(this.campParticipant.isMisc()).isFalse();

    this.campParticipant.setMisc(true);
    assertThat(this.campParticipant.isMisc()).isTrue();

    this.campParticipant.setMisc(false);
    assertThat(this.campParticipant.isMisc()).isFalse();
  }

  @Test
  public final void testSetAndIsSeminar() {
    this.campParticipant.setSeminar(false);
    assertThat(this.campParticipant.isSeminar()).isFalse();

    this.campParticipant.setSeminar(true);
    assertThat(this.campParticipant.isSeminar()).isTrue();

    this.campParticipant.setSeminar(false);
    assertThat(this.campParticipant.isSeminar()).isFalse();
  }

  @Test
  public final void testSetAndIsStaff() {
    this.campParticipant.setStaff(false);
    assertThat(this.campParticipant.isStaff()).isFalse();

    this.campParticipant.setStaff(true);
    assertThat(this.campParticipant.isStaff()).isTrue();

    this.campParticipant.setStaff(false);
    assertThat(this.campParticipant.isStaff()).isFalse();
  }
}
