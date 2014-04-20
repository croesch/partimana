package com.github.croesch.partimana.types;

import com.github.croesch.annotate.MayBeNull;
import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.types.api.IFilterable;
import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Represents a single camp.
 *
 * @author croesch
 * @since Date: May 29, 2011
 */
public final class Camp implements IFilterable {

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

  /** the date when this camp has been cancelled, or <code>null</code> if not cancelled */
  @MayBeNull
  private Date cancelDate = null;

  /** the participants of this camp */
  @NotNull
  private final List<CampParticipant> participants = new ArrayList<CampParticipant>();

  /**
   * Constructs a new {@link Camp} with the given parameters.
   *
   * @param n     the name of the camp
   * @param f     the date when the camp starts
   * @param t     the date when the camp ends
   * @param where the location where the camp will be
   * @param rate  the rate for each participant
   * @since Date: Jun 18, 2011
   */
  public Camp(final String n, final Date f, final Date t, final String where, final String rate) {
    this(highestId + 1, n, f, t, where, rate);
  }

  /**
   * Constructs a new {@link Camp} with the given parameters.
   *
   * @param forcedId the number to identify this camp, must be higher than the highest number until now
   * @param n        the name of the camp
   * @param f        the date when the camp starts
   * @param t        the date when the camp ends
   * @param where    the location where the camp will be
   * @param rate     the rate for each participant
   * @since Date: Aug 8, 2011
   */
  public Camp(final long forcedId, final String n, final Date f, final Date t, final String where, final String rate) {
    setName(n);
    setFromDate(f);
    setUntilDate(t);
    setLocation(where);
    setRatePerParticipant(rate);

    id = forcedId;
    setNewHighestIdTo(forcedId);
  }

  /**
   * Sets the new highest id to the given value.
   *
   * @param newId the new highest id of the {@link Camp}s.
   * @since Date: Sep 12, 2012
   */
  private static void setNewHighestIdTo(final long newId) {
    if (newId <= highestId) {
      return;
    }
    highestId = newId;
  }

  /**
   * Constructs a new {@link Camp} that is equal to the given {@link Camp}.
   *
   * @param c the {@link Camp} to fetch the data from.
   * @throws RequiredFieldSetToNullException if the given {@link Camp} is <code>null</code>
   * @since Date: Aug 8, 2011
   */
  public Camp(final Camp c) throws RequiredFieldSetToNullException {
    if (c == null) {
      throw new RequiredFieldSetToNullException();
    }

    from = c.from;
    id = c.id;
    location = c.location;
    name = c.name;
    ratePerDayChildren = c.ratePerDayChildren;
    ratePerParticipant = c.ratePerParticipant;
    until = c.until;
    participants.addAll(c.participants);
    cancelDate = c.cancelDate;
  }

  /**
   * Returns the name of the camp
   *
   * @return the name of the camp
   * @since Date: Jun 18, 2011
   */
  @NotNull
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the camp.
   *
   * @param n the name of the camp
   * @throws RequiredFieldSetToNullException if the given name is <code>null</code>
   * @since Date: Jun 18, 2011
   */
  public void setName(final String n) throws RequiredFieldSetToNullException {
    if (n == null) {
      throw new RequiredFieldSetToNullException();
    }
    name = n;
  }

  /**
   * Returns the date from that the camp starts
   *
   * @return the date when the camp starts
   * @since Date: Jun 18, 2011
   */
  @NotNull
  public Date getFromDate() {
    return new Date(from.getTime());
  }

  /**
   * Sets the date when the camp starts
   *
   * @param date the date when the camp starts
   * @throws RequiredFieldSetToNullException if the given date is <code>null</code>
   * @since Date: Jun 18, 2011
   */
  public void setFromDate(final Date date) throws RequiredFieldSetToNullException {
    if (date == null) {
      throw new RequiredFieldSetToNullException();
    }
    from = new Date(date.getTime());
  }

  /**
   * Returns the date when the camp ends
   *
   * @return the date when the camp ends
   * @since Date: Jun 18, 2011
   */
  @NotNull
  public Date getUntilDate() {
    return new Date(until.getTime());
  }

  /**
   * Sets the date when the camp ends
   *
   * @param date the date when the camp ends
   * @throws RequiredFieldSetToNullException if the given date is <code>null</code>
   * @since Date: Jun 18, 2011
   */
  public void setUntilDate(final Date date) throws RequiredFieldSetToNullException {
    if (date == null) {
      throw new RequiredFieldSetToNullException();
    }
    until = new Date(date.getTime());
  }

  /**
   * Returns the location of the camp
   *
   * @return the location of the camp
   * @since Date: Jun 18, 2011
   */
  @NotNull
  public String getLocation() {
    return location;
  }

  /**
   * Sets the location of the camp
   *
   * @param loc the location of the camp
   * @throws RequiredFieldSetToNullException if the given location is <code>null</code>
   * @since Date: Jun 18, 2011
   */
  public void setLocation(final String loc) throws RequiredFieldSetToNullException {
    if (loc == null) {
      throw new RequiredFieldSetToNullException();
    }
    location = loc;
  }

  /**
   * Returns the rate per participant
   *
   * @return the rate per participant
   * @since Date: Jun 18, 2011
   */
  @NotNull
  public String getRatePerParticipant() {
    return ratePerParticipant;
  }

  /**
   * Sets the rate per participant
   *
   * @param rate the rate per participant
   * @throws RequiredFieldSetToNullException if the given rate is <code>null</code>
   * @since Date: Jun 18, 2011
   */
  public void setRatePerParticipant(final String rate) throws RequiredFieldSetToNullException {
    if (rate == null) {
      throw new RequiredFieldSetToNullException();
    }
    ratePerParticipant = rate;
  }

  /**
   * Returns the rate per day-children
   *
   * @return the rate per day-children
   * @since Date: Jun 18, 2011
   */
  @MayBeNull
  public String getRatePerDayChildren() {
    return ratePerDayChildren;
  }

  /**
   * Sets the rate per day-children
   *
   * @param rate the rate per day-children
   * @since Date: Jun 18, 2011
   */
  public void setRatePerDayChildren(final String rate) {
    ratePerDayChildren = rate;
  }

  /**
   * Returns the id of this camp as stored in the data base.
   *
   * @return the id that identifies this camp in something like a data base.
   * @since Date: Jun 18, 2011
   */
  public long getId() {
    return id;
  }

  /**
   * Returns the {@link Participant}s of this camp.
   *
   * @return the participants of this camp.
   * @since Date: Sep 16, 2012
   */
  @NotNull
  public List<CampParticipant> getParticipants() {
    return new ArrayList<CampParticipant>(participants);
  }

  /**
   * Adds the given {@link CampParticipant} to this camp.
   *
   * @param participant the {@link CampParticipant} to add to this camp.
   * @since Date: Sep 16, 2012
   */
  public void addParticipant(final CampParticipant participant) {
    if (participant != null) {
      participants.add(participant);
    }
  }

  /**
   * Removes the given {@link CampParticipant} from this camp.
   *
   * @param participant the {@link CampParticipant} to remove from this camp.
   * @since Date: Sep 16, 2012
   */
  public void removeParticipant(final CampParticipant participant) {
    if (participant != null) {
      participants.remove(participant);
    }
  }

  /**
   * Removes all {@link CampParticipant}s from this camp.
   *
   * @since Date: Sep 24, 2012
   */
  public void removeAllParticipants() {
    participants.clear();
  }

  @Override
  @NotNull
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("Camp [id=");
    builder.append(id);
    builder.append(", name=");
    builder.append(name);
    builder.append(", from=");
    builder.append(from);
    builder.append(", until=");
    builder.append(until);
    builder.append(", location=");
    builder.append(location);
    builder.append(", ratePerParticipant=");
    builder.append(ratePerParticipant);
    builder.append(", ratePerDayChildren=");
    builder.append(ratePerDayChildren);
    builder.append("]");
    return builder.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    final int intSize = 32;

    int result = 1;
    result = prime * result + from.hashCode();
    result = prime * result + (int) (id ^ (id >>> intSize));
    result = prime * result + location.hashCode();
    result = prime * result + name.hashCode();
    result *= prime;
    if (ratePerDayChildren != null) {
      result += ratePerDayChildren.hashCode();
    }
    result = prime * result + ratePerParticipant.hashCode();
    result = prime * result + until.hashCode();
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
    if (!areDatesEqual(from, other.from)) {
      return false;
    }
    if (id != other.id) {
      return false;
    }
    if (!location.equals(other.location)) {
      return false;
    }
    if (!name.equals(other.name)) {
      return false;
    }
    if (!participants.equals(other.participants)) {
      return false;
    }
    if (ratePerDayChildren == null) {
      if (other.ratePerDayChildren != null) {
        return false;
      }
    } else if (!ratePerDayChildren.equals(other.ratePerDayChildren)) {
      return false;
    }
    if (!ratePerParticipant.equals(other.ratePerParticipant)) {
      return false;
    }
    return areDatesEqual(until, other.until);
  }

  /**
   * Returns whether two dates are equal, ignoring the time.
   *
   * @param one the date one to compare
   * @param two the date two to compare
   * @return <code>true</code>, if the dates are equal.
   * @since Date: Jul 10, 2011
   */
  private boolean areDatesEqual(final Date one, final Date two) {
    if (one == null) {
      return two == null;
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

    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
  }

  /**
   * @return the date, when the camp has been cancelled, or <code>null</code> if the camp hasn't been cancelled
   * @since Date: Mar 11, 2013
   */
  @MayBeNull
  public Date getCancelDate() {
    if (cancelDate == null) {
      return null;
    }
    return new Date(cancelDate.getTime());
  }

  /**
   * Sets the date when the camp has been cancelled, or <code>null</code> if not cancelled.
   *
   * @param date the date when the camp has been cancelled, or <code>null</code> if not cancelled
   * @since Date: Mar 11, 2013
   */
  public void setCancelDate(final Date date) {
    if (date == null) {
      cancelDate = null;
    } else {
      cancelDate = new Date(date.getTime());
    }
  }

  /**
   * @param participantsToInclude the selected participant IDs to include. If empty, all participants of this camp are
   *                              included.
   * @return a string being a CSV representation of this camp - or to be more specific: a CSV representation of the
   * selected participants of this camp
   * @since Date: Jun 1, 2013
   */
  public String toCSV(Collection<Long> participantsToInclude) {
    final String separator = ";";
    final String lf = System.getProperty("line.separator");

    final StringBuilder sb = new StringBuilder(
        "vorname" + separator + "name" + separator + "strasse" + separator + "plz" + separator + "wohnort" + separator
        + "geschlecht" + separator + "freizeit" + separator + "preis" + separator + "von" + separator + "bis");
    sb.append(lf);
    for (final CampParticipant cp : getParticipants()) {
      if (participantsToInclude.isEmpty() || participantsToInclude.contains(cp.getId())) {
        sb.append(cp.getForeName()).append(separator);
        sb.append(cp.getLastName()).append(separator);
        sb.append(cp.getStreet()).append(separator);
        sb.append(cp.getPostCode()).append(separator);
        sb.append(cp.getCity()).append(separator);
        sb.append(cp.getGender().getRepresentation()).append(separator);
        sb.append(getName()).append(separator);
        if (cp.getRole() == Role.DAY_CHILD) {
          sb.append(getRatePerDayChildren());
        } else {
          sb.append(getRatePerParticipant());
        }
        sb.append(separator).append(getFormattedDate(getFromDate())).append(separator);
        sb.append(getFormattedDate(getUntilDate())).append(lf);
      }
    }
    return sb.toString();
  }

  /**
   * TODO extract to a util class. Or a class where at least somebody would expect date formatting facility.
   *
   * @param date the date that should be formatted
   * @return the formatted string of the given date.
   * @since Date: Dec 8, 2013
   */
  private String getFormattedDate(Date date) {
    DateFormat dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG);
    return dateFormat.format(date);
  }
}
