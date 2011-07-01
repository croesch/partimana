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
 * @since Date: May 29, 2011 12:20:27 PM
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
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Map<Long, Participant> getMapOfParticipants() {
    return new HashMap<Long, Participant>();
  }

  @Override
  public void delete(final Participant p) {
    // TODO Auto-generated method stub

  }

  @Override
  public void delete(final Camp c) {
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
