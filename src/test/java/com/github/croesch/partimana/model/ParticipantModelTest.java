package com.github.croesch.partimana.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.model.helper.HashMapPersistenceModel;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;
import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

/**
 * Test methods for {@link ParticipantModel}
 *
 * @author croesch
 * @since Date: Jun 19, 2011
 */
public final class ParticipantModelTest {

  /** the model to test */
  private ParticipantModel pModel;

  /** our persistence model to test the behavior of the {@link ParticipantModel} */
  private HashMapPersistenceModel persistenceModel;

  private Participant p1;

  private Participant p2;

  /**
   * Sets up the two models.
   *
   * @since Date: Jun 19, 2011
   */
  @Before
  public void setUp() {
    persistenceModel = new HashMapPersistenceModel();
    pModel = new ParticipantModel(persistenceModel);

    p1 = new Participant("name",
                         "first",
                         Gender.FEMALE,
                         Denomination.CATHOLIC,
                         new Date(),
                         "street",
                         12,
                         "city",
                         CountyCouncil.COUNTY_RHEIN_PFALZ);
    p2 = new Participant("name",
                         "first",
                         Gender.FEMALE,
                         Denomination.CATHOLIC,
                         new Date(),
                         "street",
                         12,
                         "city",
                         CountyCouncil.COUNTY_RHEIN_PFALZ);
  }

  /**
   * Test method for {@link ParticipantModel#getParticipant(long)}.
   */
  @Test
  public void testGetParticipant() {
    persistenceModel.create(p1);
    persistenceModel.create(p2);
    pModel = new ParticipantModel(persistenceModel);

    assertThat(pModel.getParticipant(p1.getId())).isEqualTo(p1);
    assertThat(pModel.getParticipant(p2.getId())).isEqualTo(p2);
    assertThat(pModel.getParticipant(p1.getId())).isEqualTo(p1);
    assertThat(pModel.getParticipant(Long.MAX_VALUE)).isNull();

    final Participant p = pModel.getParticipant(p2.getId());
    p.setBank("new bank");
    assertThat(pModel.getParticipant(p2.getId())).isNotEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantModel#store(Participant)}.
   */
  @Test
  public void testCreateParticipantKAEE() {
    pModel.store(p1);
    pModel.store(p1);
    assertThat(pModel.getParticipant(p1.getId())).isEqualTo(p1);
    assertThat(persistenceModel.getMapOfParticipants().get(p1.getId())).isEqualTo(p1);

    p1.setBank("new bank");
    pModel.store(p1);
    assertThat(pModel.getParticipant(p1.getId())).isEqualTo(p1);
    assertThat(persistenceModel.getMapOfParticipants().get(p1.getId())).isEqualTo(p1);
  }

  /**
   * Test method for {@link ParticipantModel#deleteParticipant(long)}.
   */
  @Test
  public void testDeleteParticipant() {
    pModel.store(p1);
    assertThat(pModel.getParticipant(p1.getId())).isEqualTo(p1);
    assertThat(persistenceModel.getMapOfParticipants().get(p1.getId())).isEqualTo(p1);
    pModel.deleteParticipant(p1.getId());
    assertThat(pModel.getParticipant(p1.getId())).isNull();
    assertThat(persistenceModel.getMapOfParticipants().get(p1.getId())).isNull();
    pModel.deleteParticipant(p1.getId());
    assertThat(pModel.getParticipant(p1.getId())).isNull();
    assertThat(persistenceModel.getMapOfParticipants().get(p1.getId())).isNull();

    pModel.store(p1);
    assertThat(pModel.getParticipant(p1.getId())).isEqualTo(p1);
    assertThat(persistenceModel.getMapOfParticipants().get(p1.getId())).isEqualTo(p1);
    p1.setBank("other bank");
    assertThat(pModel.getParticipant(p1.getId())).isNotEqualTo(p1);
    assertThat(persistenceModel.getMapOfParticipants().get(p1.getId())).isNotEqualTo(p1);
    pModel.deleteParticipant(p1.getId());
    assertThat(pModel.getParticipant(p1.getId())).isNull();
    assertThat(persistenceModel.getMapOfParticipants().get(p1.getId())).isNull();
  }

  /**
   * Test method for {@link ParticipantModel#store(Participant)}.
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public void testStoreParticipantRFSTNE() {
    pModel.store(null);
  }

  /**
   * Test method for {@link ParticipantModel#store(Participant)}.
   */
  @Test
  public void testStoreParticipant() {
    final Participant stored = pModel.store(p1);
    assertThat(pModel.getParticipant(p1.getId())).isEqualTo(p1);
    assertThat(pModel.getParticipant(p1.getId())).isEqualTo(stored);
    assertThat(persistenceModel.getMapOfParticipants().get(p1.getId())).isEqualTo(p1);
    p1.setBank("new bank");
    assertThat(pModel.getParticipant(p1.getId())).isNotEqualTo(p1);
    assertThat(persistenceModel.getMapOfParticipants().get(p1.getId())).isNotEqualTo(p1);

    pModel.store(p2);
    p1.setBank(null);
    assertThat(pModel.getParticipant(p2.getId())).isEqualTo(p2);
    assertThat(persistenceModel.getMapOfParticipants().get(p2.getId())).isEqualTo(p2);
    assertThat(pModel.getParticipant(p1.getId())).isEqualTo(p1);
    assertThat(persistenceModel.getMapOfParticipants().get(p1.getId())).isEqualTo(p1);
    p2.setBank("new bank");
    assertThat(pModel.getParticipant(p2.getId())).isNotEqualTo(p2);
    assertThat(persistenceModel.getMapOfParticipants().get(p2.getId())).isNotEqualTo(p2);
    assertThat(pModel.getParticipant(p1.getId())).isEqualTo(p1);
    assertThat(persistenceModel.getMapOfParticipants().get(p1.getId())).isEqualTo(p1);
    pModel.store(p2);
    assertThat(pModel.getParticipant(p2.getId())).isEqualTo(p2);
    assertThat(persistenceModel.getMapOfParticipants().get(p2.getId())).isEqualTo(p2);
  }

  /**
   * Test method for {@link ParticipantModel#getListOfParticipants()}
   */
  @Test
  public void testGetListOfParticipants() {
    pModel.store(p1);
    pModel.store(p1);
    assertThat(pModel.getListOfParticipants()).containsExactly(p1);
    pModel.store(p2);
    pModel.deleteParticipant(p1.getId());
    assertThat(pModel.getListOfParticipants()).containsExactly(p2);
    pModel.store(p1);
    pModel.store(p1);
    assertThat(pModel.getListOfParticipants()).contains(p2, p1);
  }
}
