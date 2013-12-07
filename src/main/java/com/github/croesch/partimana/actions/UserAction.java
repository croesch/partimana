package com.github.croesch.partimana.actions;

/**
 * Enumeration that contains some different actions to pass to someone to react on the action.
 * 
 * @author croesch
 * @since Date: May 29, 2011
 */
public enum UserAction {

  /** action to exit the program */
  EXIT,
  /** the action to save a participant */
  SAVE_PARTICIPANT,
  /** the action to search a participant */
  SEARCH_PARTICIPANT,
  /** the action to delete a participant */
  DELETE_PARTICIPANT,
  /** the action to create a new participant */
  CREATE_PARTICIPANT,
  /** the action that notifies about a selection change of participants */
  PARTICIPANT_SELECTED,
  /** the action that notifies about a selection change of camp participants */
  CAMP_PARTICIPANT_SELECTED,
  /** the action that notifies about a selection change of camps */
  CAMP_SELECTED,
  /** the action to save a camp */
  SAVE_CAMP,
  /** the action to save a camp as csv */
  SAVE_CAMP_TO_CSV,
  /** the action to search a camp */
  SEARCH_CAMP,
  /** the action to delete a camp */
  DELETE_CAMP,
  /** the action to cancel a camp */
  CANCEL_CAMP,
  /** the action to create a camp */
  CREATE_CAMP

}
