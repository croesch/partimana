package com.github.croesch.partimana.model.helper;

import com.github.croesch.partimana.model.api.IPersistenceModel;
import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.types.Participant;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper class to have a simple {@link IPersistenceModel}.
 *
 * @author croesch
 * @since Date: Jun 19, 2011
 */
public final class HashMapPersistenceModel implements IPersistenceModel {

  /** 'stored' {@link Participant}s */
  private final HashMap<Long, Participant> participantMap = new HashMap<Long, Participant>();

  /** 'stored' {@link Camp}s */
  private final HashMap<Long, Camp> campMap = new HashMap<Long, Camp>();

  @Override
  public void update(final Participant p) {
    participantMap.put(Long.valueOf(p.getId()), p);
  }

  @Override
  public void update(final Camp c) {
    campMap.put(Long.valueOf(c.getId()), c);
  }

  @Override
  public Map<Long, Camp> getMapOfCamps() {
    return new HashMap<Long, Camp>(campMap);
  }

  @Override
  public Map<Long, Participant> getMapOfParticipants() {
    return new HashMap<Long, Participant>(participantMap);
  }

  @Override
  public void deleteParticipant(final long id) {
    participantMap.remove(id);
  }

  @Override
  public void deleteCamp(final long id) {
    campMap.remove(id);
  }

  @Override
  public void create(final Participant p) {
    update(p);
  }

  @Override
  public void create(final Camp c) {
    update(c);
  }

  @Override
  public void close() throws IOException {
  }
}
