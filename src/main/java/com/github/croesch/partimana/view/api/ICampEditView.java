package com.github.croesch.partimana.view.api;

import java.util.Date;
import java.util.List;

import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.types.CampParticipant;
import com.github.croesch.partimana.types.Participant;

/**
 * Provides methods for the view that is able to edit a {@link Camp}.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011
 */
public interface ICampEditView {

  /**
   * Clears all fields.
   * 
   * @author croesch
   * @since Date: Sep 13, 2012
   */
  void clear();

  /**
   * Returns the id of the {@link Camp} or -1 if the participant doesn't exist already.
   * 
   * @author croesch
   * @since Date: Sep 13, 2012
   * @return the id that represents the id of the camp or <code>-1</code>, if this camp doesn't exist already
   */
  long getId();

  /**
   * Visualises the given {@link Camp} to edit with the view.
   * 
   * @author croesch
   * @since Date: Sep 13, 2012
   * @param camp {@link Camp} to edit, <code>null</code> will clear the view
   */
  void setCamp(Camp camp);

  /**
   * Returns the name the user entered.
   * 
   * @author croesch
   * @since Date: Sep 13, 2012
   * @return {@link String} that represents the name of the {@link Camp}.
   */
  String getNameOfCamp();

  /**
   * Returns the location the user entered.
   * 
   * @author croesch
   * @since Date: Sep 13, 2012
   * @return {@link String} that represents the location of the {@link Camp}.
   */
  String getLocationOfCamp();

  /**
   * Returns the date the user entered, when the camp begins.
   * 
   * @author croesch
   * @since Date: Sep 13, 2012
   * @return {@link Date} that represents the beginning of the {@link Camp}.
   */
  Date getFrom();

  /**
   * Returns the date the user entered, when the camp ends.
   * 
   * @author croesch
   * @since Date: Sep 13, 2012
   * @return {@link Date} that represents the ending of the {@link Camp}.
   */
  Date getUntil();

  /**
   * Returns the per-day-rate the user entered.
   * 
   * @author croesch
   * @since Date: Sep 13, 2012
   * @return {@link String} that represents the rate of the {@link Camp} per day.
   */
  String getRatePerDay();

  /**
   * Returns the rate the user entered.
   * 
   * @author croesch
   * @since Date: Sep 13, 2012
   * @return {@link String} that represents the rate of the {@link Camp}.
   */
  String getRatePerParticipant();

  /**
   * Performs an update of the list of participants and will visualize the information of the given list.
   * 
   * @since Date: Sep 13, 2012
   * @param participants the list of participants that is up to date.
   */
  void update(List<Participant> participants);

  /**
   * Returns the participants the user has added to this camp.
   * 
   * @since Date: Sep 23, 2012
   * @return the {@link List} of {@link CampParticipant}s the user added to this {@link Camp}.
   */
  List<CampParticipant> getCampParticipants();
}
