package com.github.croesch.partimana.types;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.github.croesch.annotate.MayBeNull;
import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;

/**
 * Represents a single camp.
 * 
 * @author croesch
 * @since Date: May 29, 2011
 */
public final class Camp {

  /** the id of that camp as stored in data base */
  private final long id;

  /** the highest id to calculate the next id for a new camp */
  private static long highestId = 0;

  /** the name of the camp */
  @NotNull
  private String name = null;

  /** the date when the camp starts */
  @NotNull
  private Date from = null;

  /** the date when the camp ends */
  @NotNull
  private Date until = null;

  /** the location where the camp will be */
  @NotNull
  private String location = null;

  /** the rate that each participant has to pay */
  @NotNull
  private String ratePerParticipant = null;

  /** the rate that each day-child has to pay */
  @MayBeNull
  private String ratePerDayChildren = null;

  /** the participants of this camp */
  @NotNull
  private final List<CampParticipant> participants = new ArrayList<CampParticipant>();

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
    this(highestId + 1, n, f, t, where, rate);
  }

  /**
   * Constructs a new {@link Camp} with the given parameters.
   * 
   * @author croesch
   * @since Date: Aug 8, 2011
   * @param forcedId the number to identify this camp, must be higher than the highest number until now
   * @param n the name of the camp
   * @param f the date when the camp starts
   * @param t the date when the camp ends
   * @param where the location where the camp will be
   * @param rate the rate for each participant
   */
  public Camp(final long forcedId, final String n, final Date f, final Date t, final String where, final String rate) {
    setName(n);
    setFromDate(f);
    setUntilDate(t);
    setLocation(where);
    setRatePerParticipant(rate);

    this.id = forcedId;
    setNewHighestIdTo(forcedId);
  }

  /**
   * Sets the new highest id to the given value.
   * 
   * @since Date: Sep 12, 2012
   * @param newId the new highest id of the {@link Camp}s.
   */
  private static void setNewHighestIdTo(final long newId) {
    if (newId <= highestId) {
      throw new IllegalArgumentException();
    }
    highestId = newId;
  }

  /**
   * Constructs a new {@link Camp} that is equal to the given {@link Camp}.
   * 
   * @author croesch
   * @since Date: Aug 8, 2011
   * @param c the {@link Camp} to fetch the data from.
   * @throws RequiredFieldSetToNullException if the given {@link Camp} is <code>null</code>
   */
  public Camp(final Camp c) throws RequiredFieldSetToNullException {
    if (c == null) {
      throw new RequiredFieldSetToNullException();
    }

    this.from = c.from;
    this.id = c.id;
    this.location = c.location;
    this.name = c.name;
    this.ratePerDayChildren = c.ratePerDayChildren;
    this.ratePerParticipant = c.ratePerParticipant;
    this.until = c.until;
    this.participants.addAll(c.participants);
  }

  /**
   * Returns the name of the camp
   * 
   * @since Date: Jun 18, 2011
   * @return the name of the camp
   */
  @NotNull
  public String getName() {
    return this.name;
  }

  /**
   * Sets the name of the camp.
   * 
   * @since Date: Jun 18, 2011
   * @param n the name of the camp
   * @throws RequiredFieldSetToNullException if the given name is <code>null</code>
   */
  public void setName(final String n) throws RequiredFieldSetToNullException {
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
  @NotNull
  public Date getFromDate() {
    return new Date(this.from.getTime());
  }

  /**
   * Sets the date when the camp starts
   * 
   * @since Date: Jun 18, 2011
   * @param date the date when the camp starts
   * @throws RequiredFieldSetToNullException if the given date is <code>null</code>
   */
  public void setFromDate(final Date date) throws RequiredFieldSetToNullException {
    if (date == null) {
      throw new RequiredFieldSetToNullException();
    }
    this.from = new Date(date.getTime());
  }

  /**
   * Returns the date when the camp ends
   * 
   * @since Date: Jun 18, 2011
   * @return the date when the camp ends
   */
  @NotNull
  public Date getUntilDate() {
    return new Date(this.until.getTime());
  }

  /**
   * Sets the date when the camp ends
   * 
   * @since Date: Jun 18, 2011
   * @param date the date when the camp ends
   * @throws RequiredFieldSetToNullException if the given date is <code>null</code>
   */
  public void setUntilDate(final Date date) throws RequiredFieldSetToNullException {
    if (date == null) {
      throw new RequiredFieldSetToNullException();
    }
    this.until = new Date(date.getTime());
  }

  /**
   * Returns the location of the camp
   * 
   * @since Date: Jun 18, 2011
   * @return the location of the camp
   */
  @NotNull
  public String getLocation() {
    return this.location;
  }

  /**
   * Sets the location of the camp
   * 
   * @since Date: Jun 18, 2011
   * @param loc the location of the camp
   * @throws RequiredFieldSetToNullException if the given location is <code>null</code>
   */
  public void setLocation(final String loc) throws RequiredFieldSetToNullException {
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
  @NotNull
  public String getRatePerParticipant() {
    return this.ratePerParticipant;
  }

  /**
   * Sets the rate per participant
   * 
   * @since Date: Jun 18, 2011
   * @param rate the rate per participant
   * @throws RequiredFieldSetToNullException if the given rate is <code>null</code>
   */
  public void setRatePerParticipant(final String rate) throws RequiredFieldSetToNullException {
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
  @MayBeNull
  public String getRatePerDayChildren() {
    return this.ratePerDayChildren;
  }

  /**
   * Sets the rate per day-children
   * 
   * @since Date: Jun 18, 2011
   * @param rate the rate per day-children
   */
  public void setRatePerDayChildren(final String rate) {
    this.ratePerDayChildren = rate;
  }

  /**
   * Returns the id of this camp as stored in the data base.
   * 
   * @author croesch
   * @since Date: Jun 18, 2011
   * @return the id that identifies this camp in something like a data base.
   */
  public long getId() {
    return this.id;
  }

  /**
   * Returns the {@link Participant}s of this camp.
   * 
   * @since Date: Sep 16, 2012
   * @return the participants of this camp.
   */
  @NotNull
  public List<CampParticipant> getParticipants() {
    return new ArrayList<CampParticipant>(this.participants);
  }

  /**
   * Adds the given {@link CampParticipant} to this camp.
   * 
   * @since Date: Sep 16, 2012
   * @param participant the {@link CampParticipant} to add to this camp.
   */
  public void addParticipant(final CampParticipant participant) {
    if (participant != null) {
      this.participants.add(participant);
    }
  }

  /**
   * Removes the given {@link CampParticipant} from this camp.
   * 
   * @since Date: Sep 16, 2012
   * @param participant the {@link CampParticipant} to remove from this camp.
   */
  public void removeParticipant(final CampParticipant participant) {
    if (participant != null) {
      this.participants.remove(participant);
    }
  }

  /**
   * Removes all {@link CampParticipant}s from this camp.
   * 
   * @since Date: Sep 24, 2012
   */
  public void removeAllParticipants() {
    this.participants.clear();
  }

  @Override
  @NotNull
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("Camp [id=");
    builder.append(this.id);
    builder.append(", name=");
    builder.append(this.name);
    builder.append(", from=");
    builder.append(this.from);
    builder.append(", until=");
    builder.append(this.until);
    builder.append(", location=");
    builder.append(this.location);
    builder.append(", ratePerParticipant=");
    builder.append(this.ratePerParticipant);
    builder.append(", ratePerDayChildren=");
    builder.append(this.ratePerDayChildren);
    builder.append("]");
    return builder.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    final int intSize = 32;

    int result = 1;
    result = prime * result + this.from.hashCode();
    result = prime * result + (int) (this.id ^ (this.id >>> intSize));
    result = prime * result + this.location.hashCode();
    result = prime * result + this.name.hashCode();
    result *= prime;
    if (this.ratePerDayChildren != null) {
      result += this.ratePerDayChildren.hashCode();
    }
    result = prime * result + this.ratePerParticipant.hashCode();
    result = prime * result + this.until.hashCode();
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Camp)) {
      return false;
    }

    final Camp other = (Camp) obj;
    if (!areDatesEqual(this.from, other.from)) {
      return false;
    }
    if (this.id != other.id) {
      return false;
    }
    if (!this.location.equals(other.location)) {
      return false;
    }
    if (!this.name.equals(other.name)) {
      return false;
    }
    if (!this.participants.equals(other.participants)) {
      return false;
    }
    if (this.ratePerDayChildren == null) {
      if (other.ratePerDayChildren != null) {
        return false;
      }
    } else if (!this.ratePerDayChildren.equals(other.ratePerDayChildren)) {
      return false;
    }
    if (!this.ratePerParticipant.equals(other.ratePerParticipant)) {
      return false;
    }
    if (!areDatesEqual(this.until, other.until)) {
      return false;
    }
    return true;
  }

  /**
   * Returns whether two dates are equal, ignoring the time.
   * 
   * @since Date: Jul 10, 2011
   * @param one the date one to compare
   * @param two the date two to compare
   * @return <code>true</code>, if the dates are equal.
   */
  private boolean areDatesEqual(final Date one, final Date two) {
    if (one == null) {
      if (two != null) {
        return false;
      }
      return true;
    }
    if (two == null) {
      return false;
    }

    if (one.equals(two)) {
      return true;
    }
    final Calendar cal1 = new GregorianCalendar();
    cal1.setTime(one);

    final Calendar cal2 = new GregorianCalendar();
    cal2.setTime(two);

    if (cal1.get(Calendar.DAY_OF_MONTH) != cal2.get(Calendar.DAY_OF_MONTH)) {
      return false;
    }

    if (cal1.get(Calendar.MONTH) != cal2.get(Calendar.MONTH)) {
      return false;
    }

    if (cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR)) {
      return false;
    }
    return true;
  }
}
