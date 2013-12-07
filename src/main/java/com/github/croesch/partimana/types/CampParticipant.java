package com.github.croesch.partimana.types;

import java.util.Date;

import com.github.croesch.annotate.MayBeNull;
import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.types.api.IFilterable;
import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;

/**
 * Wrapper for a {@link Participant} that visits a camp.
 * 
 * @author croesch
 * @since Date: Sep 16, 2012
 */
public final class CampParticipant implements IFilterable {

  /** the {@link Participant} that visits the camp */
  @NotNull
  private final Participant participant;

  /** the role of the participant in the camp */
  private Role role = Role.PARTICIPANT;

  /** the date since when the participant is included in the camp */
  @NotNull
  private Date signedIn = new Date();

  /** if the participant decided to not participate this holds the date */
  @MayBeNull
  private Date signedOff;

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
   * @since Date: Mar 5, 2013
   * @return the date since when the participant is included in the camp
   */
  public Date getSignedIn() {
    return this.signedIn;
  }

  /**
   * @since Date: Mar 5, 2013
   * @param date the date since when the participant is included in the camp
   */
  public void setSignedIn(final Date date) {
    if (date == null) {
      throw new IllegalArgumentException();
    }
    this.signedIn = date;
  }

  /**
   * @since Date: Mar 5, 2013
   * @return if the participant decided to not participate the date,<br>
   *         otherwise <code>null</code>
   */
  public Date getSignedOff() {
    return this.signedOff;
  }

  /**
   * Sets when this participant decided to not take part in the camp.
   * 
   * @since Date: Mar 5, 2013
   * @param date the date when the participant decided to not take part in the camp,<br>
   *        or <code>null</code> if the participant didn't decide to not take part in the camp
   */
  public void setSignedOff(final Date date) {
    this.signedOff = date;
  }

  /**
   * Defines which role this participant has in this camp.
   * 
   * @since Date: Mar 2, 2013
   * @param newRole the role of the participant in the camp
   */
  public void setRole(final Role newRole) {
    this.role = newRole;
  }

  /**
   * @since Date: Mar 2, 2013
   * @return the role this participant has while participating in this camp
   */
  public Role getRole() {
    return this.role;
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
    return this.participant.equals(((CampParticipant) obj).participant);
  }

  /**
   * @since Date: Sep 16, 2012
   * @return the id of the {@link Participant}.
   */
  public long getId() {
    return this.participant.getId();
  }

  /**
   * @since Date: Sep 16, 2012
   * @return the fore name of the {@link Participant}.
   */
  @NotNull
  public String getForeName() {
    return this.participant.getForeName();
  }

  /**
   * @since Date: Sep 16, 2012
   * @return the last name of the {@link Participant}.
   */
  @NotNull
  public String getLastName() {
    return this.participant.getLastName();
  }

  /**
   * @since Date: Jun 1, 2013
   * @return the street of the {@link Participant}.
   */
  @NotNull
  public String getStreet() {
    return this.participant.getStreet();
  }

  /**
   * @since Date: Jun 1, 2013
   * @return the string representation of the post code of the {@link Participant}.
   */
  @NotNull
  public String getPostCode() {
    return String.valueOf(this.participant.getPostCode());
  }

  /**
   * @since Date: Jun 1, 2013
   * @return the city of the {@link Participant}.
   */
  @NotNull
  public String getCity() {
    return this.participant.getCity();
  }
}
