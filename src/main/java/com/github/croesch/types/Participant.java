package com.github.croesch.types;

import java.util.Date;

/**
 * Represents a participant.
 * 
 * @author croesch
 * @since Date: May 29, 2011 12:28:42 PM
 */
public class Participant {

  /** the last name of the participant */
  private final String lastName;

  /** the first name of the participant */
  private final String foreName;

  /** the gender of the participant */
  private final Gender gender;

  /** the denomination/confession of the participant */
  private final Denomination denomination;

  /** the date of birth of the participant */
  private final Date birthDate;

  /** the street where the participant lives */
  private final String street;

  /** the post code of the city where the participant lives */
  private final int postCode;

  /** the city where the participant lives */
  private final String city;

  /** the street to post to */
  private final String streetPostal;

  /** the post code of the city to post to */
  private final int postCodePostal;

  /** the city where to post to */
  private final String cityPostal;

  /** the phone number of the participant */
  private final String phone;

  /** the fax number of the participant */
  private final String fax;

  /** the mobile phone number of the participant */
  private final String mobilePhone;

  /** the phone number of the parents of the participant */
  private final String phoneOfParents;

  /** the mail address of the participant */
  private final String mailAddress;

  /**
   * Constructs a new {@link Participant}.
   * 
   * @author croesch
   * @since Date: Jun 16, 2011 9:16:04 PM
   * @param name the last name of the person
   * @param firstName the first name of the person
   * @param g the gender of the person
   * @param den the denomination/confession of the person
   * @param birth the birthday of the person
   * @param str the street where the person lives
   * @param pc the post code of the city where the person lives
   * @param c the city where the person lives
   */
  public Participant(final String name,
                     final String firstName,
                     final Gender g,
                     final Denomination den,
                     final Date birth,
                     final String str,
                     final int pc,
                     final String c) {
    this.lastName = name;
    this.foreName = firstName;
    this.gender = g;
    this.denomination = den;
    this.birthDate = birth;
    this.street = str;
    this.postCode = pc;
    this.city = c;
    this.streetPostal = null;
    this.postCodePostal = 0;
    this.cityPostal = null;
    this.phone = null;
    this.fax = null;
    this.mobilePhone = null;
    this.phoneOfParents = null;
    this.mailAddress = null;
  }
}
