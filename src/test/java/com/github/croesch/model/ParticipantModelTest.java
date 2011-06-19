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
  }

  /**
   * Test method for {@link ParticipantModel#getParticipant(long)}.
   */
  @Test
  public void testGetParticipant() {
    final Participant p1 = new Participant("name",
                                           "first",
                                           Gender.FEMALE,
                                           Denomination.CATHOLIC,
                                           new Date(),
                                           "street",
                                           12,
                                           "city",
                                           CountyCouncil.COUNTY_RHEIN_PFALZ);
    final Participant p2 = new Participant("name",
                                           "first",
                                           Gender.FEMALE,
                                           Denomination.CATHOLIC,
                                           new Date(),
                                           "street",
                                           12,
                                           "city",
                                           CountyCouncil.COUNTY_RHEIN_PFALZ);
    this.persistenceModel.create(p1);
    this.persistenceModel.create(p2);
    this.pModel = new ParticipantModel(this.persistenceModel);

    assertThat(this.pModel.getParticipant(p1.getId())).isEqualTo(p1);
    assertThat(this.pModel.getParticipant(p2.getId())).isEqualTo(p2);
    assertThat(this.pModel.getParticipant(p1.getId())).isEqualTo(p1);
    assertThat(this.pModel.getParticipant(Long.MAX_VALUE)).isNull();
  }
}
