package com.github.croesch.partimana.types;

import com.github.croesch.partimana.i18n.Text;

/**
 * A role a {@link Participant} has when taking part in a {@link Camp}.
 *
 * @author croesch
 * @since Date: Mar 2, 2013
 */
public enum Role {

  /** the participant is the leader of the camp */
  DIRECTION(0, Text.CAMP_PARTICIPANT_DIRECTION),
  /** the participant is part of the staff */
  STAFF(1, Text.CAMP_PARTICIPANT_STAFF),
  /** the participant works in the kitchen of the camp */
  KITCHEN_STAFF(2, Text.CAMP_PARTICIPANT_KITCHEN_STAFF),
  /** the participant is a simple participant */
  PARTICIPANT(3, Text.CAMP_PARTICIPANT_PARTICIPANT);

  /** the id of this role to store in a database */
  private final int id;

  /** the localized text of this role */
  private final String text;

  /**
   * Creates this role a participant can have when participating in a camp.
   *
   * @param dbId the database conform id of this role
   * @param txt  localized text of this role
   * @since Date: Mar 2, 2013
   */
  private Role(final int dbId, final Text txt) {
    this.id = dbId;
    this.text = txt.text();
  }

  /**
   * Returns the role to the given database id. Or <code>null</code> if this id doesn't belong to a role.
   *
   * @param dbId the id to search the role for
   * @return the role for the given database id, or <code>null</code> if no role has this id.
   * @since Date: Mar 2, 2013
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
   * @return the database conform id of this role
   * @since Date: Mar 2, 2013
   */
  public int getId() {
    return this.id;
  }

  @Override
  public String toString() {
    return this.text;
  }
}
