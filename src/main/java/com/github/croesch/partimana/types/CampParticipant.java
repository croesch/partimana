package com.github.croesch.partimana.types;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;

/**
 * Wrapper for a {@link Participant} that visits a camp.
 * 
 * @author croesch
 * @since Date: Sep 16, 2012
 */
public final class CampParticipant {

  /** the {@link Participant} that visits the camp */
  @NotNull
  private final Participant participant;

  /** if the participant is isAGE */
  private boolean isAGE = false;

  /** if the participant is isBoard */
  private boolean isBoard = false;

  /** if the participant is extended isBoard */
  private boolean isExtendedBoard = false;

  /** if the participant is isKitchen */
  private boolean isKitchen = false;

  /** if the participant is isMAK */
  private boolean isMAK = false;

  /** if the participant is isMisc */
  private boolean isMisc = false;

  /** if the participant is normal participant */
  private boolean isNormalParticipant = false;

  /** if the participant is isSeminar */
  private boolean isSeminar = false;

  /** if the participant is isStaff */
  private boolean isStaff = false;

  /** if the participant is isStaff of youth */
  private boolean isStaffYouth = false;

  /**
   * Constructs this wrapper for the given {@link Participant} that visits a camp.
   * 
   * @since Date: Sep 16, 2012
   * @param p the {@link Participant} to wrap
   */
  public CampParticipant(final Participant p) {
    if (p == null) {
      throw new RequiredFieldSetToNullException();
    }
    this.participant = p;
  }

  /**
   * Returns whether the participant can be AGE
   * 
   * @since Date: Sep 16, 2012
   * @return <code>true</code>, if the participant can be AGE
   */
  public boolean isPossibleAGE() {
    return this.participant.isPossibleAGE();
  }

  /**
   * Returns whether the participant can be member of isBoard
   * 
   * @since Date: Sep 16, 2012
   * @return <code>true</code>, if the participant can be member of isBoard
   */
  public boolean isPossibleBoard() {
    return this.participant.isPossibleBoard();
  }

  /**
   * Returns whether the participant can be member of extended isBoard
   * 
   * @since Date: Sep 16, 2012
   * @return <code>true</code>, if the participant can be member of extended isBoard
   */
  public boolean isPossibleExtendedBoard() {
    return this.participant.isPossibleExtendedBoard();
  }

  /**
   * Returns whether the participant can be in isKitchen.
   * 
   * @since Date: Sep 16, 2012
   * @return <code>true</code>, if the participant can be in isKitchen.
   */
  public boolean isPossibleKitchen() {
    return this.participant.isPossibleKitchen();
  }

  /**
   * Returns whether the participant can be MAK
   * 
   * @since Date: Sep 16, 2012
   * @return <code>true</code>, if the participant can be MAK
   */
  public boolean isPossibleMAK() {
    return this.participant.isPossibleMAK();
  }

  /**
   * Returns whether the participant can be member of something else.
   * 
   * @since Date: Sep 16, 2012
   * @return <code>true</code>, if the participant can be member of anything else.
   */
  public boolean isPossibleMisc() {
    return this.participant.isPossibleMisc();
  }

  /**
   * Gets whether the participant can participate as a participant.
   * 
   * @since Date: Sep 16, 2012
   * @return <code>true</code>, if the user can participate as a participant
   */
  public boolean isPossibleParticipant() {
    return this.participant.isPossibleParticipant();
  }

  /**
   * Returns whether the participant can be in a isSeminar.
   * 
   * @since Date: Sep 16, 2012
   * @return <code>true</code>, if the participant can be in a isSeminar
   */
  public boolean isPossibleSeminar() {
    return this.participant.isPossibleSeminar();
  }

  /**
   * Gets whether the user can participate as a isStaff member.
   * 
   * @since Date: Sep 16, 2012
   * @return <code>true</code>, if the user can participate as a isStaff member
   */
  public boolean isPossibleStaff() {
    return this.participant.isPossibleStaff();
  }

  /**
   * Returns the participant can be isStaff-youth
   * 
   * @since Date: Sep 16, 2012
   * @return <code>true</code>, if the participant can be isStaff-youth
   */
  public boolean isPossibleStaffYouth() {
    return this.participant.isPossibleStaffYouth();
  }

  /**
   * Returns whether the participant is AGE.
   * 
   * @since Date: Sep 16, 2012
   * @return <code>true</code>, if the participant <b>is</b> AGE.
   */
  public boolean isAGE() {
    return this.isAGE;
  }

  /**
   * Sets whether the participant is AGE.
   * 
   * @since Date: Sep 16, 2012
   * @param age <code>true</code>, if the participant <b>is</b> AGE.
   */
  public void setAGE(final boolean age) {
    this.isAGE = age;
  }

  /**
   * Returns whether the participant <b>is</b> board.
   * 
   * @since Date: Sep 16, 2012
   * @return <code>true</code>, if the participant <b>is</b> board.
   */
  public boolean isBoard() {
    return this.isBoard;
  }

  /**
   * Sets whether the participant <b>is</b> board.
   * 
   * @since Date: Sep 16, 2012
   * @param board <code>true</code>, if the participant <b>is</b> AGE.
   */
  public void setBoard(final boolean board) {
    this.isBoard = board;
  }

  /**
   * Returns whether the participant <b>is</b> extended board.
   * 
   * @since Date: Sep 16, 2012
   * @return <code>true</code>, if the participant <b>is</b> extended board.
   */
  public boolean isExtendedBoard() {
    return this.isExtendedBoard;
  }

  /**
   * Sets whether the participant <b>is</b> extended board.
   * 
   * @since Date: Sep 16, 2012
   * @param extendedBoard <code>true</code>, if the participant <b>is</b> extended board.
   */
  public void setExtendedBoard(final boolean extendedBoard) {
    this.isExtendedBoard = extendedBoard;
  }

  /**
   * Returns whether the participant <b>is</b> kitchen.
   * 
   * @since Date: Sep 16, 2012
   * @return <code>true</code>, if the participant <b>is</b> kitchen.
   */
  public boolean isKitchen() {
    return this.isKitchen;
  }

  /**
   * Sets whether the participant <b>is</b> kitchen.
   * 
   * @since Date: Sep 16, 2012
   * @param kitchen <code>true</code>, if the participant <b>is</b> kitchen.
   */
  public void setKitchen(final boolean kitchen) {
    this.isKitchen = kitchen;
  }

  /**
   * Returns whether the participant <b>is</b> MAK.
   * 
   * @since Date: Sep 16, 2012
   * @return <code>true</code>, if the participant <b>is</b> MAK.
   */
  public boolean isMAK() {
    return this.isMAK;
  }

  /**
   * Sets whether the participant <b>is</b> MAK.
   * 
   * @since Date: Sep 16, 2012
   * @param mak <code>true</code>, if the participant <b>is</b> MAK.
   */
  public void setMAK(final boolean mak) {
    this.isMAK = mak;
  }

  /**
   * Returns whether the participant <b>is</b> Misc.
   * 
   * @since Date: Sep 16, 2012
   * @return <code>true</code>, if the participant <b>is</b> Misc.
   */
  public boolean isMisc() {
    return this.isMisc;
  }

  /**
   * Sets whether the participant <b>is</b> Misc.
   * 
   * @since Date: Sep 16, 2012
   * @param misc <code>true</code>, if the participant <b>is</b> Misc.
   */
  public void setMisc(final boolean misc) {
    this.isMisc = misc;
  }

  /**
   * Returns whether the participant <b>is</b> normal participant.
   * 
   * @since Date: Sep 16, 2012
   * @return <code>true</code>, if the participant <b>is</b> participant.
   */
  public boolean isParticipant() {
    return this.isNormalParticipant;
  }

  /**
   * Sets whether the participant <b>is</b> normal participant.
   * 
   * @since Date: Sep 16, 2012
   * @param normalParticipant <code>true</code>, if the participant <b>is</b> normal participant.
   */
  public void setParticipant(final boolean normalParticipant) {
    this.isNormalParticipant = normalParticipant;
  }

  /**
   * Returns whether the participant <b>is</b> seminar.
   * 
   * @since Date: Sep 16, 2012
   * @return <code>true</code>, if the participant <b>is</b> seminar.
   */
  public boolean isSeminar() {
    return this.isSeminar;
  }

  /**
   * Sets whether the participant <b>is</b> seminar.
   * 
   * @since Date: Sep 16, 2012
   * @param seminar <code>true</code>, if the participant <b>is</b> seminar.
   */
  public void setSeminar(final boolean seminar) {
    this.isSeminar = seminar;
  }

  /**
   * Returns whether the participant <b>is</b> staff.
   * 
   * @since Date: Sep 16, 2012
   * @return <code>true</code>, if the participant <b>is</b> staff.
   */
  public boolean isStaff() {
    return this.isStaff;
  }

  /**
   * Sets whether the participant <b>is</b> staff.
   * 
   * @since Date: Sep 16, 2012
   * @param staff <code>true</code>, if the participant <b>is</b> staff.
   */
  public void setStaff(final boolean staff) {
    this.isStaff = staff;
  }

  /**
   * Returns whether the participant <b>is</b> staff youth.
   * 
   * @since Date: Sep 16, 2012
   * @return <code>true</code>, if the participant <b>is</b> staff youth.
   */
  public boolean isStaffYouth() {
    return this.isStaffYouth;
  }

  /**
   * Sets whether the participant <b>is</b> staff youth.
   * 
   * @since Date: Sep 16, 2012
   * @param staffYouth <code>true</code>, if the participant <b>is</b> staff youth.
   */
  public void setStaffYouth(final boolean staffYouth) {
    this.isStaffYouth = staffYouth;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + this.participant.hashCode();
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    if (!this.participant.equals(((CampParticipant) obj).participant)) {
      return false;
    }
    return true;
  }

  /**
   * Returns the id of the {@link Participant}.
   * 
   * @since Date: Sep 16, 2012
   * @return the id of the {@link Participant}.
   */
  public long getId() {
    return this.participant.getId();
  }

  /**
   * Returns the fore name of the {@link Participant}.
   * 
   * @since Date: Sep 16, 2012
   * @return the fore name of the {@link Participant}.
   */
  public String getForeName() {
    return this.participant.getForeName();
  }

  /**
   * Returns the last name of the {@link Participant}.
   * 
   * @since Date: Sep 16, 2012
   * @return the last name of the {@link Participant}.
   */
  public String getLastName() {
    return this.participant.getLastName();
  }
}
