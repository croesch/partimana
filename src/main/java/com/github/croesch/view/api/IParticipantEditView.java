package com.github.croesch.view.api;

import java.util.Date;

import com.github.croesch.types.CountyCouncil;
import com.github.croesch.types.Denomination;
import com.github.croesch.types.Gender;
import com.github.croesch.types.Participant;

/**
 * Provides methods for the view that is able to edit a {@link Participant}.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011 6:20:02 AM
 */
public interface IParticipantEditView {

  /**
   * Visualises the given {@link Participant} to edit with the view.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @param participant {@link Participant} to edit, <code>null</code> will clear the view
   */
  void setParticipant(Participant participant);

  /**
   * Returns the first name that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return {@link String} that represents the first name of the {@link Participant}.
   */
  String getFirstName();

  /**
   * Returns the last name that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return {@link String} that represents the last name of the {@link Participant}.
   */
  String getLastName();

  /**
   * Returns the gender that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return {@link Gender} that represents the gender of the {@link Participant}.
   */
  Gender getGender();

  /**
   * Returns the denomination that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return {@link Denomination} that represents the denomination of the {@link Participant}.
   */
  Denomination getDenomination();

  /**
   * Returns the birth that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return {@link Date} that represents the birth date of the {@link Participant}.
   */
  Date getBirthDate();

  /**
   * Returns the street (postal) that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return {@link String} that represents the street where the {@link Participant} can receive post.
   */
  String getPostalStreet();

  /**
   * Returns the post code (postal) that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return number that represents the post code of the city where the {@link Participant} can receive post.
   */
  int getPostalPostCode();

  /**
   * Returns the city (postal) that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return {@link String} that represents the city where the {@link Participant} can receive post.
   */
  String getPostalCity();

  /**
   * Returns the county council that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return {@link String} that represents the county council of the {@link Participant}
   */
  CountyCouncil getCountyCouncil();

  /**
   * Returns the bank name that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return {@link String} that represents the bank name of the {@link Participant}s bank
   */
  String getBank();

  /**
   * Returns the bank account number that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return number that represents the bank account number of the {@link Participant}
   */
  int getBankAccountNumber();

  /**
   * Returns the banks bank code number that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return number that represents the bank code number of the {@link Participant}s bank
   */
  int getBankCodeNumber();

  /**
   * Returns the comment that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return {@link String} that represents the comment about the {@link Participant}
   */
  String getComment();

  /**
   * Returns the date until the person is kept in data base, that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return {@link Date} until that this {@link Participant} will be kept in data base
   */
  Date getDateUpToInDataBase();

  /**
   * Returns the fax number that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return {@link String} that represents the fax number of the {@link Participant}
   */
  String getFax();

  /**
   * Returns the mail address that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return {@link String} that represents the mail address of the {@link Participant}
   */
  String getMailAddress();

  /**
   * Returns the mobile phone number that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return {@link String} that represents the mobile phone number of the {@link Participant}
   */
  String getMobilePhone();

  /**
   * Returns the phone number that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return {@link String} that represents the phone number of the {@link Participant}
   */
  String getPhone();

  /**
   * Returns the phone of parents that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return {@link String} that represents the phone of parents of the {@link Participant}
   */
  String getPhoneOfParents();

  /**
   * Returns whether the {@link Participant} is member of AGE
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return <code>true</code>, if the {@link Participant} is member of AGE
   */
  boolean getPossibleAGE();

  /**
   * Returns whether the {@link Participant} is member of board
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return <code>true</code>, if the {@link Participant} is member of board
   */
  boolean getPossibleBoard();

  /**
   * Returns whether the {@link Participant} is member of extended board
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return <code>true</code>, if the {@link Participant} is member of extended board
   */
  boolean getPossibleExtendedBoard();

  /**
   * Returns whether the {@link Participant} can participate as kitchen member.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return <code>true</code>, if the {@link Participant} can participate as kitchen member
   */
  boolean getPossibleKitchen();

  /**
   * Returns whether the {@link Participant} can participate as MAK.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return <code>true</code>, if the {@link Participant} can participate as MAK
   */
  boolean getPossibleMAK();

  /**
   * Returns whether the {@link Participant} can participate as s.th. else.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return <code>true</code>, if the {@link Participant} can participate as s.th. else (placeholder)
   */
  boolean getPossibleMisc();

  /**
   * Returns whether the {@link Participant} can participate as participant.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return <code>true</code>, if the {@link Participant} can participate as participant
   */
  boolean getPossibleParticipant();

  /**
   * Returns whether the {@link Participant} can be member of seminars, that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return <code>true</code>, if the {@link Participant} can be member of seminars.
   */
  boolean getPossibleSeminar();

  /**
   * Returns whether the {@link Participant} can be staff that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return <code>true</code>, if the {@link Participant} can be staff.
   */
  boolean getPossibleStaff();

  /**
   * Returns whether the {@link Participant} can be staff of youth that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return <code>true</code>, if the {@link Participant} can be staff of youth.
   */
  boolean getPossibleStaffYouth();

  /**
   * Returns the street (living) that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return {@link String} that represents the street where the {@link Participant} lives.
   */
  String getLivingStreet();

  /**
   * Returns the post code (living) that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return number that represents the post code of the city where the {@link Participant} lives.
   */
  int getLivingPostCode();

  /**
   * Returns the city (living) that the user entered.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   * @return {@link String} that represents the city where the {@link Participant} lives.
   */
  String getLivingCity();

  /**
   * Returns the id of the {@link Participant} or -1 if the participant doesn't exist already.
   * 
   * @author croesch
   * @since Date: Jun 29, 2011
   * @return the id that represents the id of the participant or <code>-1</code>, if this participant doesn't exist
   *         already
   */
  long getId();

  /**
   * Clears all fields.
   * 
   * @author croesch
   * @since Date: Jun 29, 2011
   */
  void clear();

}
