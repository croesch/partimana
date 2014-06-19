package com.github.croesch.partimana.types;

import com.github.croesch.annotate.MayBeNull;
import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.types.api.IFilterable;
import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;
import java.util.Date;

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
   * @param p the {@link Participant} to wrap
   * @since Date: Sep 16, 2012
   */
  public CampParticipant(final Participant p) {
    if (p == null) {
      throw new RequiredFieldSetToNullException();
    }
    participant = p;
  }

  /**
   * @return the date since when the participant is included in the camp
   * @since Date: Mar 5, 2013
   */
  public Date getSignedIn() {
    return signedIn;
  }

  /**
   * @param date the date since when the participant is included in the camp
   * @since Date: Mar 5, 2013
   */
  public void setSignedIn(final Date date) {
    if (date == null) {
      throw new IllegalArgumentException();
    }
    signedIn = date;
  }

  /**
   * @return if the participant decided to not participate the date,<br> otherwise <code>null</code>
   * @since Date: Mar 5, 2013
   */
  public Date getSignedOff() {
    return signedOff;
  }

  /**
   * Sets when this participant decided to not take part in the camp.
   *
   * @param date the date when the participant decided to not take part in the camp,<br> or <code>null</code> if the
   *             participant didn't decide to not take part in the camp
   * @since Date: Mar 5, 2013
   */
  public void setSignedOff(final Date date) {
    signedOff = date;
  }

  /**
   * Defines which role this participant has in this camp.
   *
   * @param newRole the role of the participant in the camp
   * @since Date: Mar 2, 2013
   */
  public void setRole(final Role newRole) {
    role = newRole;
  }

  /**
   * @return the role this participant has while participating in this camp
   * @since Date: Mar 2, 2013
   */
  public Role getRole() {
    return role;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + participant.hashCode();
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
    return participant.equals(((CampParticipant) obj).participant);
  }

  /**
   * @return the id of the {@link Participant}.
   * @since Date: Sep 16, 2012
   */
  public long getId() {
    return participant.getId();
  }

  /**
   * @return the fore name of the {@link Participant}.
   * @since Date: Sep 16, 2012
   */
  @NotNull
  public String getForeName() {
    return participant.getForeName();
  }

  /**
   * @return the last name of the {@link Participant}.
   * @since Date: Sep 16, 2012
   */
  @NotNull
  public String getLastName() {
    return participant.getLastName();
  }

  /**
   * @return the street of the {@link Participant}.
   * @since Date: Jun 1, 2013
   */
  @NotNull
  public String getStreet() {
    return participant.getStreet();
  }

  /**
   * @return the string representation of the post code of the {@link Participant}.
   * @since Date: Jun 1, 2013
   */
  @NotNull
  public String getPostCode() {
    return String.valueOf(participant.getPostCode());
  }

  /**
   * @return the city of the {@link Participant}.
   * @since Date: Jun 1, 2013
   */
  @NotNull
  public String getCity() {
    return participant.getCity();
  }

  /**
   * @return the gender of the {@link Participant}.
   * @since Date: Dec 8, 2013
   */
  @NotNull
  public Gender getGender() {
    return participant.getGender();
  }

  /**
   * @return the {@link Denomination} of the {@link Participant}.
   * @since Date: Jun 19, 2014
   */
  @NotNull
  public Denomination getDenomination() {
    return participant.getDenomination();
  }

  /**
   * @return the birth date of the {@link Participant}.
   * @since Date: Jun 19, 2014
   */
  @NotNull
  public Date getBirthDate() {
    return participant.getBirthDate();
  }

  /**
   * @return the phone number of the {@link Participant}.
   * @since Date: Jun 19, 2014
   */
  @MayBeNull
  public String getPhone() {
    return participant.getPhone();
  }

  /**
   * @return the mobile phone of the {@link Participant}.
   * @since Date: Jun 19, 2014
   */
  @MayBeNull
  public String getMobilePhone() {
    return participant.getMobilePhone();
  }

  /**
   * @return the mail address of the {@link Participant}.
   * @since Date: Jun 19, 2014
   */
  @MayBeNull
  public String getMailAddress() {
    return participant.getMailAddress();
  }

  /**
   * @return the {@link CountyCouncil} of the {@link Participant}.
   * @since Date: Jun 19, 2014
   */
  @NotNull
  public CountyCouncil getCountyCouncil() {
    return participant.getCountyCouncil();
  }
}
