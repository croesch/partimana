package com.github.croesch.model;

import java.util.HashMap;
import java.util.Map;

import com.github.croesch.model.api.IPersistenceModel;
import com.github.croesch.types.Camp;
import com.github.croesch.types.Participant;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: May 29, 2011
 */
class PersistenceModel implements IPersistenceModel {

  @Override
  public void update(final Participant p) {
    // TODO Auto-generated method stub

  }

  @Override
  public void update(final Camp c) {
    // TODO Auto-generated method stub

  }

  @Override
  public Map<Long, Camp> getMapOfCamps() {
    return new HashMap<Long, Camp>();
  }

  @Override
  public Map<Long, Participant> getMapOfParticipants() {
    return new HashMap<Long, Participant>();
  }

  @Override
  public void deleteParticipant(final long id) {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteCamp(final long id) {
    // TODO Auto-generated method stub

  }

  @Override
  public void create(final Participant p) {
    // TODO Auto-generated method stub

  }

  @Override
  public void create(final Camp c) {
    // TODO Auto-generated method stub

  }

}
