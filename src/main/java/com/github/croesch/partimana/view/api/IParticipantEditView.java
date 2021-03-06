package com.github.croesch.partimana.view.api;

import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;
import java.util.Date;

/**
 * Provides methods for the view that is able to edit a {@link Participant}.
 *
 * @author croesch
 * @since Date: Jun 8, 2011
 */
public interface IParticipantEditView {

  /**
   * Visualises the given {@link Participant} to edit with the view.
   *
   * @param participant {@link Participant} to edit, <code>null</code> will clear the view
   * @since Date: Jun 26, 2011
   */
  void setParticipant(Participant participant);

  /**
   * Returns the first name that the user entered.
   *
   * @return {@link String} that represents the first name of the {@link Participant}.
   * @since Date: Jun 26, 2011
   */
  String getFirstName();

  /**
   * Returns the last name that the user entered.
   *
   * @return {@link String} that represents the last name of the {@link Participant}.
   * @since Date: Jun 26, 2011
   */
  String getLastName();

  /**
   * Returns the gender that the user entered.
   *
   * @return {@link Gender} that represents the gender of the {@link Participant}.
   * @since Date: Jun 26, 2011
   */
  Gender getGender();

  /**
   * Returns the denomination that the user entered.
   *
   * @return {@link Denomination} that represents the denomination of the {@link Participant}.
   * @since Date: Jun 26, 2011
   */
  Denomination getDenomination();

  /**
   * Returns the birth that the user entered.
   *
   * @return {@link Date} that represents the birth date of the {@link Participant}.
   * @since Date: Jun 26, 2011
   */
  Date getBirthDate();

  /**
   * Returns the street (postal) that the user entered.
   *
   * @return {@link String} that represents the street where the {@link Participant} can receive post.
   * @since Date: Jun 26, 2011
   */
  String getPostalStreet();

  /**
   * Returns the post code (postal) that the user entered.
   *
   * @return number that represents the post code of the city where the {@link Participant} can receive post.
   * @since Date: Jun 26, 2011
   */
  int getPostalPostCode();

  /**
   * Returns the city (postal) that the user entered.
   *
   * @return {@link String} that represents the city where the {@link Participant} can receive post.
   * @since Date: Jun 26, 2011
   */
  String getPostalCity();

  /**
   * Returns the county council that the user entered.
   *
   * @return {@link String} that represents the county council of the {@link Participant}
   * @since Date: Jun 26, 2011
   */
  CountyCouncil getCountyCouncil();

  /**
   * Returns the bank name that the user entered.
   *
   * @return {@link String} that represents the bank name of the {@link Participant}s bank
   * @since Date: Jun 26, 2011
   */
  String getBank();

  /**
   * Returns the bank account number that the user entered.
   *
   * @return number that represents the bank account number of the {@link Participant}
   * @since Date: Jun 26, 2011
   */
  int getBankAccountNumber();

  /**
   * Returns the banks bank code number that the user entered.
   *
   * @return number that represents the bank code number of the {@link Participant}s bank
   * @since Date: Jun 26, 2011
   */
  int getBankCodeNumber();

  /**
   * Returns the comment that the user entered.
   *
   * @return {@link String} that represents the comment about the {@link Participant}
   * @since Date: Jun 26, 2011
   */
  String getComment();

  /**
   * Returns the date until the person is kept in data base, that the user entered.
   *
   * @return {@link Date} until that this {@link Participant} will be kept in data base
   * @since Date: Jun 26, 2011
   */
  Date getDateUpToInDataBase();

  /**
   * Returns the fax number that the user entered.
   *
   * @return {@link String} that represents the fax number of the {@link Participant}
   * @since Date: Jun 26, 2011
   */
  String getFax();

  /**
   * Returns the mail address that the user entered.
   *
   * @return {@link String} that represents the mail address of the {@link Participant}
   * @since Date: Jun 26, 2011
   */
  String getMailAddress();

  /**
   * Returns the mobile phone number that the user entered.
   *
   * @return {@link String} that represents the mobile phone number of the {@link Participant}
   * @since Date: Jun 26, 2011
   */
  String getMobilePhone();

  /**
   * Returns the phone number that the user entered.
   *
   * @return {@link String} that represents the phone number of the {@link Participant}
   * @since Date: Jun 26, 2011
   */
  String getPhone();

  /**
   * Returns the phone of parents that the user entered.
   *
   * @return {@link String} that represents the phone of parents of the {@link Participant}
   * @since Date: Jun 26, 2011
   */
  String getPhoneOfParents();

  /**
   * Returns the street (living) that the user entered.
   *
   * @return {@link String} that represents the street where the {@link Participant} lives.
   * @since Date: Jun 26, 2011
   */
  String getLivingStreet();

  /**
   * Returns the post code (living) that the user entered.
   *
   * @return number that represents the post code of the city where the {@link Participant} lives.
   * @since Date: Jun 26, 2011
   */
  int getLivingPostCode();

  /**
   * Returns the city (living) that the user entered.
   *
   * @return {@link String} that represents the city where the {@link Participant} lives.
   * @since Date: Jun 26, 2011
   */
  String getLivingCity();

  /**
   * Returns the id of the {@link Participant} or -1 if the participant doesn't exist already.
   *
   * @return the id that represents the id of the participant or <code>-1</code>, if this participant doesn't exist
   * already
   * @since Date: Jun 29, 2011
   */
  long getId();

  /**
   * Clears all fields.
   *
   * @since Date: Jun 29, 2011
   */
  void clear();
}
