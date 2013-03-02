package com.github.croesch.partimana.types;

/**
 * A role a {@link Participant} has when taking part in a {@link Camp}.
 * 
 * @author croesch
 * @since Date: Mar 2, 2013
 */
public enum Role {

  /** the participant is the leader of the camp */
  DIRECTION (0),
  /** the participant is part of the staff */
  STAFF (1),
  /** the participant works in the kitchen of the camp */
  KITCHEN_STAFF (2),
  /** the participant is a simple participant */
  PARTICIPANT (3);

  /** the id of this role to store in a database */
  private final int id;

  /**
   * Creates this role a participant can have when participating in a camp.
   * 
   * @since Date: Mar 2, 2013
   * @param dbId the database conform id of this role
   */
  private Role(final int dbId) {
    this.id = dbId;
  }

  /**
   * Returns the role to the given database id. Or <code>null</code> if this id doesn't belong to a role.
   * 
   * @since Date: Mar 2, 2013
   * @param dbId the id to search the role for
   * @return the role for the given database id, or <code>null</code> if no role has this id.
   */
  public static Role valueOf(final int dbId) {
    for (final Role role : values()) {
      if (role.getId() == dbId) {
        return role;
      }
    }
    return null;
  }

  /**
   * @since Date: Mar 2, 2013
   * @return the database conform id of this role
   */
  public int getId() {
    return this.id;
  }
}
