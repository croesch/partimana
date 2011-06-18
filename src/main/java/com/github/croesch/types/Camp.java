package com.github.croesch.types;

import java.util.Date;

import com.github.croesch.types.exceptions.RequiredFieldSetToNullException;

/**
 * Represents a single camp.
 * 
 * @author croesch
 * @since Date: May 29, 2011 12:28:53 PM
 */
public class Camp {

  /** the name of the camp */
  private String name = null;

  /** the date when the camp starts */
  private Date from = null;

  /** the date when the camp ends */
  private Date until = null;

  /** the location where the camp will be */
  private String location = null;

  /** the rate that each participant has to pay */
  private String ratePerParticipant = null;

  /** the rate that each day-child has to pay */
  private String ratePerDayChildren = null;

  /**
   * Constructs a new {@link Camp} with the given parameters.
   * 
   * @author croesch
   * @since Date: Jun 18, 2011
   * @param n the name of the camp
   * @param f the date when the camp starts
   * @param t the date when the camp ends
   * @param where the location where the camp will be
   * @param rate the rate for each participant
   */
  public Camp(final String n, final Date f, final Date t, final String where, final String rate) {
    setName(n);
    setFromDate(f);
    setUntilDate(t);
    setLocation(where);
    setRatePerParticipant(rate);
  }

  /**
   * Returns the name of the camp
   * 
   * @since Date: Jun 18, 2011
   * @return the name of the camp
   */
  public final String getName() {
    return this.name;
  }

  /**
   * Sets the name of the camp.
   * 
   * @since Date: Jun 18, 2011
   * @param n the name of the camp
   * @throws RequiredFieldSetToNullException if the given name is <code>null</code>
   */
  public final void setName(final String n) throws RequiredFieldSetToNullException {
    if (n == null) {
      throw new RequiredFieldSetToNullException();
    }
    this.name = n;
  }

  /**
   * Returns the date from that the camp starts
   * 
   * @since Date: Jun 18, 2011
   * @return the date when the camp starts
   */
  public final Date getFromDate() {
    return this.from;
  }

  /**
   * Sets the date when the camp starts
   * 
   * @since Date: Jun 18, 2011
   * @param date the date when the camp starts
   * @throws RequiredFieldSetToNullException if the given date is <code>null</code>
   */
  public final void setFromDate(final Date date) throws RequiredFieldSetToNullException {
    if (date == null) {
      throw new RequiredFieldSetToNullException();
    }
    this.from = date;
  }

  /**
   * Returns the date when the camp ends
   * 
   * @since Date: Jun 18, 2011
   * @return the date when the camp ends
   */
  public final Date getUntilDate() {
    return this.until;
  }

  /**
   * Sets the date when the camp ends
   * 
   * @since Date: Jun 18, 2011
   * @param date the date when the camp ends
   * @throws RequiredFieldSetToNullException if the given date is <code>null</code>
   */
  public final void setUntilDate(final Date date) throws RequiredFieldSetToNullException {
    if (date == null) {
      throw new RequiredFieldSetToNullException();
    }
    this.until = date;
  }

  /**
   * Returns the location of the camp
   * 
   * @since Date: Jun 18, 2011
   * @return the location of the camp
   */
  public final String getLocation() {
    return this.location;
  }

  /**
   * Sets the location of the camp
   * 
   * @since Date: Jun 18, 2011
   * @param loc the location of the camp
   * @throws RequiredFieldSetToNullException if the given location is <code>null</code>
   */
  public final void setLocation(final String loc) throws RequiredFieldSetToNullException {
    if (loc == null) {
      throw new RequiredFieldSetToNullException();
    }
    this.location = loc;
  }

  /**
   * Returns the rate per participant
   * 
   * @since Date: Jun 18, 2011
   * @return the rate per participant
   */
  public final String getRatePerParticipant() {
    return this.ratePerParticipant;
  }

  /**
   * Sets the rate per participant
   * 
   * @since Date: Jun 18, 2011
   * @param rate the rate per participant
   * @throws RequiredFieldSetToNullException if the given rate is <code>null</code>
   */
  public final void setRatePerParticipant(final String rate) throws RequiredFieldSetToNullException {
    if (rate == null) {
      throw new RequiredFieldSetToNullException();
    }
    this.ratePerParticipant = rate;
  }

  /**
   * Returns the rate per day-children
   * 
   * @since Date: Jun 18, 2011
   * @return the rate per day-children
   */
  public final String getRatePerDayChildren() {
    return this.ratePerDayChildren;
  }

  /**
   * Sets the rate per day-children
   * 
   * @since Date: Jun 18, 2011
   * @param rate the rate per day-children
   */
  public final void setRatePerDayChildren(final String rate) {
    this.ratePerDayChildren = rate;
  }

}
