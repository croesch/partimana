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
    this.persistenceModel = new HashMapPersistenceModel();
    this.pModel = new ParticipantModel(this.persistenceModel);

    this.p1 = new Participant("name",
                              "first",
                              Gender.FEMALE,
                              Denomination.CATHOLIC,
                              new Date(),
                              "street",
                              12,
                              "city",
                              CountyCouncil.COUNTY_RHEIN_PFALZ);
    this.p2 = new Participant("name",
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
    this.persistenceModel.create(this.p1);
    this.persistenceModel.create(this.p2);
    this.pModel = new ParticipantModel(this.persistenceModel);

    assertThat(this.pModel.getParticipant(this.p1.getId())).isEqualTo(this.p1);
    assertThat(this.pModel.getParticipant(this.p2.getId())).isEqualTo(this.p2);
    assertThat(this.pModel.getParticipant(this.p1.getId())).isEqualTo(this.p1);
    assertThat(this.pModel.getParticipant(Long.MAX_VALUE)).isNull();

    final Participant p = this.pModel.getParticipant(this.p2.getId());
    p.setBank("new bank");
    assertThat(this.pModel.getParticipant(this.p2.getId())).isNotEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantModel#store(Participant)}.
   */
  @Test
  public void testCreateParticipantKAEE() {
    this.pModel.store(this.p1);
    this.pModel.store(this.p1);
    assertThat(this.pModel.getParticipant(this.p1.getId())).isEqualTo(this.p1);
    assertThat(this.persistenceModel.getMapOfParticipants().get(this.p1.getId())).isEqualTo(this.p1);

    this.p1.setBank("new bank");
    this.pModel.store(this.p1);
    assertThat(this.pModel.getParticipant(this.p1.getId())).isEqualTo(this.p1);
    assertThat(this.persistenceModel.getMapOfParticipants().get(this.p1.getId())).isEqualTo(this.p1);
  }

  /**
   * Test method for {@link ParticipantModel#deleteParticipant(long)}.
   */
  @Test
  public void testDeleteParticipant() {
    this.pModel.store(this.p1);
    assertThat(this.pModel.getParticipant(this.p1.getId())).isEqualTo(this.p1);
    assertThat(this.persistenceModel.getMapOfParticipants().get(this.p1.getId())).isEqualTo(this.p1);
    this.pModel.deleteParticipant(this.p1.getId());
    assertThat(this.pModel.getParticipant(this.p1.getId())).isNull();
    assertThat(this.persistenceModel.getMapOfParticipants().get(this.p1.getId())).isNull();
    this.pModel.deleteParticipant(this.p1.getId());
    assertThat(this.pModel.getParticipant(this.p1.getId())).isNull();
    assertThat(this.persistenceModel.getMapOfParticipants().get(this.p1.getId())).isNull();

    this.pModel.store(this.p1);
    assertThat(this.pModel.getParticipant(this.p1.getId())).isEqualTo(this.p1);
    assertThat(this.persistenceModel.getMapOfParticipants().get(this.p1.getId())).isEqualTo(this.p1);
    this.p1.setBank("other bank");
    assertThat(this.pModel.getParticipant(this.p1.getId())).isNotEqualTo(this.p1);
    assertThat(this.persistenceModel.getMapOfParticipants().get(this.p1.getId())).isNotEqualTo(this.p1);
    this.pModel.deleteParticipant(this.p1.getId());
    assertThat(this.pModel.getParticipant(this.p1.getId())).isNull();
    assertThat(this.persistenceModel.getMapOfParticipants().get(this.p1.getId())).isNull();
  }

  /**
   * Test method for {@link ParticipantModel#store(Participant)}.
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public void testStoreParticipantRFSTNE() {
    this.pModel.store(null);
  }

  /**
   * Test method for {@link ParticipantModel#store(Participant)}.
   */
  @Test
  public void testStoreParticipant() {
    final Participant stored = this.pModel.store(this.p1);
    assertThat(this.pModel.getParticipant(this.p1.getId())).isEqualTo(this.p1);
    assertThat(this.pModel.getParticipant(this.p1.getId())).isEqualTo(stored);
    assertThat(this.persistenceModel.getMapOfParticipants().get(this.p1.getId())).isEqualTo(this.p1);
    this.p1.setBank("new bank");
    assertThat(this.pModel.getParticipant(this.p1.getId())).isNotEqualTo(this.p1);
    assertThat(this.persistenceModel.getMapOfParticipants().get(this.p1.getId())).isNotEqualTo(this.p1);

    this.pModel.store(this.p2);
    this.p1.setBank(null);
    assertThat(this.pModel.getParticipant(this.p2.getId())).isEqualTo(this.p2);
    assertThat(this.persistenceModel.getMapOfParticipants().get(this.p2.getId())).isEqualTo(this.p2);
    assertThat(this.pModel.getParticipant(this.p1.getId())).isEqualTo(this.p1);
    assertThat(this.persistenceModel.getMapOfParticipants().get(this.p1.getId())).isEqualTo(this.p1);
    this.p2.setBank("new bank");
    assertThat(this.pModel.getParticipant(this.p2.getId())).isNotEqualTo(this.p2);
    assertThat(this.persistenceModel.getMapOfParticipants().get(this.p2.getId())).isNotEqualTo(this.p2);
    assertThat(this.pModel.getParticipant(this.p1.getId())).isEqualTo(this.p1);
    assertThat(this.persistenceModel.getMapOfParticipants().get(this.p1.getId())).isEqualTo(this.p1);
    this.pModel.store(this.p2);
    assertThat(this.pModel.getParticipant(this.p2.getId())).isEqualTo(this.p2);
    assertThat(this.persistenceModel.getMapOfParticipants().get(this.p2.getId())).isEqualTo(this.p2);
  }

  /**
   * Test method for {@link ParticipantModel#getListOfParticipants()}
   */
  @Test
  public void testGetListOfParticipants() {
    this.pModel.store(this.p1);
    this.pModel.store(this.p1);
    assertThat(this.pModel.getListOfParticipants()).containsExactly(this.p1);
    this.pModel.store(this.p2);
    this.pModel.deleteParticipant(this.p1.getId());
    assertThat(this.pModel.getListOfParticipants()).containsExactly(this.p2);
    this.pModel.store(this.p1);
    this.pModel.store(this.p1);
    assertThat(this.pModel.getListOfParticipants()).contains(this.p2, this.p1);
  }
}
