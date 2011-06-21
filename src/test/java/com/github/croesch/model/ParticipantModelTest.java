package com.github.croesch.model;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.github.croesch.model.helper.HashMapPersistenceModel;
import com.github.croesch.types.CountyCouncil;
import com.github.croesch.types.Denomination;
import com.github.croesch.types.Gender;
import com.github.croesch.types.Participant;
import com.github.croesch.types.exceptions.RequiredFieldSetToNullException;

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
   * @author croesch
   * @since Date: Jun 19, 2011
   * @throws java.lang.Exception
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
   * Test method for {@link ParticipantModel#delete(Participant)}.
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public void testDeleteParticipantRFSTNE() {
    this.pModel.delete(null);
  }

  /**
   * Test method for {@link ParticipantModel#delete(Participant)}.
   */
  @Test
  public void testDeleteParticipant() {
    this.pModel.store(this.p1);
    assertThat(this.pModel.getParticipant(this.p1.getId())).isEqualTo(this.p1);
    assertThat(this.persistenceModel.getMapOfParticipants().get(this.p1.getId())).isEqualTo(this.p1);
    this.pModel.delete(this.p1);
    assertThat(this.pModel.getParticipant(this.p1.getId())).isNull();
    assertThat(this.persistenceModel.getMapOfParticipants().get(this.p1.getId())).isNull();
    this.pModel.delete(this.p1);
    assertThat(this.pModel.getParticipant(this.p1.getId())).isNull();
    assertThat(this.persistenceModel.getMapOfParticipants().get(this.p1.getId())).isNull();

    this.pModel.store(this.p1);
    assertThat(this.pModel.getParticipant(this.p1.getId())).isEqualTo(this.p1);
    assertThat(this.persistenceModel.getMapOfParticipants().get(this.p1.getId())).isEqualTo(this.p1);
    this.p1.setBank("other bank");
    assertThat(this.pModel.getParticipant(this.p1.getId())).isNotEqualTo(this.p1);
    assertThat(this.persistenceModel.getMapOfParticipants().get(this.p1.getId())).isNotEqualTo(this.p1);
    this.pModel.delete(this.p1);
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
    this.pModel.store(this.p1);
    assertThat(this.pModel.getParticipant(this.p1.getId())).isEqualTo(this.p1);
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
    this.pModel.delete(this.p1);
    assertThat(this.pModel.getListOfParticipants()).containsExactly(this.p2);
    this.pModel.store(this.p1);
    this.pModel.store(this.p1);
    assertThat(this.pModel.getListOfParticipants()).contains(this.p2, this.p1);

  }
}
