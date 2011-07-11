package com.github.croesch.actions;

/**
 * Enumeration that contains some different actions to pass to someone to react on the action.
 * 
 * @author croesch
 * @since Date: May 29, 2011 12:29:49 PM
 */
public enum UserAction {

  /** action to exit the program */
  EXIT,
  /** the action to save a participant */
  SAVE_PARTICIPANT,
  /** the action to delete a participant */
  DELETE_PARTICIPANT,
  /** the action to create a new participant */
  CREATE_PARTICIPANT,
  /** the action that notifies about a selection change of participants */
  PARTICIPANT_SELECTED;

}
