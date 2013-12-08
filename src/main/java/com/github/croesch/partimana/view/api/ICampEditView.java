package com.github.croesch.partimana.view.api;

import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.types.CampParticipant;
import com.github.croesch.partimana.types.Participant;
import java.util.Date;
import java.util.List;

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
   * @since Date: Sep 13, 2012
   */
  void clear();

  /**
   * Returns the id of the {@link Camp} or -1 if the participant doesn't exist already.
   *
   * @return the id that represents the id of the camp or <code>-1</code>, if this camp doesn't exist already
   * @since Date: Sep 13, 2012
   */
  long getId();

  /**
   * Visualises the given {@link Camp} to edit with the view.
   *
   * @param camp {@link Camp} to edit, <code>null</code> will clear the view
   * @since Date: Sep 13, 2012
   */
  void setCamp(Camp camp);

  /**
   * Returns the name the user entered.
   *
   * @return {@link String} that represents the name of the {@link Camp}.
   * @since Date: Sep 13, 2012
   */
  String getNameOfCamp();

  /**
   * Returns the location the user entered.
   *
   * @return {@link String} that represents the location of the {@link Camp}.
   * @since Date: Sep 13, 2012
   */
  String getLocationOfCamp();

  /**
   * Returns the date the user entered, when the camp begins.
   *
   * @return {@link Date} that represents the beginning of the {@link Camp}.
   * @since Date: Sep 13, 2012
   */
  Date getFrom();

  /**
   * Returns the date the user entered, when the camp ends.
   *
   * @return {@link Date} that represents the ending of the {@link Camp}.
   * @since Date: Sep 13, 2012
   */
  Date getUntil();

  /**
   * Returns the per-day-rate the user entered.
   *
   * @return {@link String} that represents the rate of the {@link Camp} per day.
   * @since Date: Sep 13, 2012
   */
  String getRatePerDay();

  /**
   * Returns the rate the user entered.
   *
   * @return {@link String} that represents the rate of the {@link Camp}.
   * @since Date: Sep 13, 2012
   */
  String getRatePerParticipant();

  /**
   * Performs an update of the list of participants and will visualize the information of the given list.
   *
   * @param participants the list of participants that is up to date.
   * @since Date: Sep 13, 2012
   */
  void update(List<Participant> participants);

  /**
   * Returns the participants the user has added to this camp.
   *
   * @return the {@link List} of {@link CampParticipant}s the user added to this {@link Camp}.
   * @since Date: Sep 23, 2012
   */
  List<CampParticipant> getCampParticipants();
}
