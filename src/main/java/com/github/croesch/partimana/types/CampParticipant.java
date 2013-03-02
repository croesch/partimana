package com.github.croesch.partimana.types;

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
