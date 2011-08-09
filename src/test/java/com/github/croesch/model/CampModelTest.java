package com.github.croesch.model;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.github.croesch.model.helper.HashMapPersistenceModel;
import com.github.croesch.types.Camp;
import com.github.croesch.types.exceptions.RequiredFieldSetToNullException;

/**
 * Test methods for {@link CampModel}
 * 
 * @author croesch
 * @since Date: Aug 8, 2011
 */
public final class CampModelTest {

  /** the model to test */
  private CampModel cModel;

  /** our persistence model to test the behavior of the {@link CampModel} */
  private HashMapPersistenceModel persistenceModel;

  private Camp c1;

  private Camp c2;

  /**
   * Sets up the two models.
   * 
   * @author croesch
   * @since Date: Aug 8, 2011
   */
  @Before
  public void setUp() {
    this.persistenceModel = new HashMapPersistenceModel();
    this.cModel = new CampModel(this.persistenceModel);

    this.c1 = new Camp("Sommercamp", new Date(99999), new Date(88888888), "Spain", "5,0€");
    this.c2 = new Camp("Wintercamp", new Date(444444), new Date(333333333), "Russia", "1,30 €");
  }

  /**
   * Test method for {@link CampModel#getCamp(long)}.
   */
  @Test
  public void testGetCamp() {
    this.persistenceModel.create(this.c1);
    this.persistenceModel.create(this.c2);
    this.cModel = new CampModel(this.persistenceModel);

    assertThat(this.cModel.getCamp(this.c1.getId())).isEqualTo(this.c1);
    assertThat(this.cModel.getCamp(this.c2.getId())).isEqualTo(this.c2);
    assertThat(this.cModel.getCamp(this.c1.getId())).isEqualTo(this.c1);
    assertThat(this.cModel.getCamp(Long.MAX_VALUE)).isNull();

    final Camp c = this.cModel.getCamp(this.c2.getId());
    c.setRatePerDayChildren("new bank");
    assertThat(this.cModel.getCamp(this.c2.getId())).isNotEqualTo(c);
  }

  /**
   * Test method for {@link CampModel#store(Camp)}.
   */
  @Test
  public void testCreateCampKAEE() {
    this.cModel.store(this.c1);
    this.cModel.store(this.c1);
    assertThat(this.cModel.getCamp(this.c1.getId())).isEqualTo(this.c1);
    assertThat(this.persistenceModel.getMapOfCamps().get(this.c1.getId())).isEqualTo(this.c1);

    this.c1.setRatePerDayChildren("new bank");
    this.cModel.store(this.c1);
    assertThat(this.cModel.getCamp(this.c1.getId())).isEqualTo(this.c1);
    assertThat(this.persistenceModel.getMapOfCamps().get(this.c1.getId())).isEqualTo(this.c1);
  }

  /**
   * Test method for {@link CampModel#delete(Camp)}.
   */
  @Test
  public void testDeleteCamp() {
    this.cModel.store(this.c1);
    assertThat(this.cModel.getCamp(this.c1.getId())).isEqualTo(this.c1);
    assertThat(this.persistenceModel.getMapOfCamps().get(this.c1.getId())).isEqualTo(this.c1);
    this.cModel.deleteCamp(this.c1.getId());
    assertThat(this.cModel.getCamp(this.c1.getId())).isNull();
    assertThat(this.persistenceModel.getMapOfCamps().get(this.c1.getId())).isNull();
    this.cModel.deleteCamp(this.c1.getId());
    assertThat(this.cModel.getCamp(this.c1.getId())).isNull();
    assertThat(this.persistenceModel.getMapOfCamps().get(this.c1.getId())).isNull();

    this.cModel.store(this.c1);
    assertThat(this.cModel.getCamp(this.c1.getId())).isEqualTo(this.c1);
    assertThat(this.persistenceModel.getMapOfCamps().get(this.c1.getId())).isEqualTo(this.c1);
    this.c1.setRatePerDayChildren("other bank");
    assertThat(this.cModel.getCamp(this.c1.getId())).isNotEqualTo(this.c1);
    assertThat(this.persistenceModel.getMapOfCamps().get(this.c1.getId())).isNotEqualTo(this.c1);
    this.cModel.deleteCamp(this.c1.getId());
    assertThat(this.cModel.getCamp(this.c1.getId())).isNull();
    assertThat(this.persistenceModel.getMapOfCamps().get(this.c1.getId())).isNull();
  }

  /**
   * Test method for {@link CampModel#store(Camp)}.
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public void testStoreCampRFSTNE() {
    this.cModel.store(null);
  }

  /**
   * Test method for {@link CampModel#store(Camp)}.
   */
  @Test
  public void testStoreCamp() {
    this.cModel.store(this.c1);
    assertThat(this.cModel.getCamp(this.c1.getId())).isEqualTo(this.c1);
    assertThat(this.persistenceModel.getMapOfCamps().get(this.c1.getId())).isEqualTo(this.c1);
    this.c1.setRatePerDayChildren("new bank");
    assertThat(this.cModel.getCamp(this.c1.getId())).isNotEqualTo(this.c1);
    assertThat(this.persistenceModel.getMapOfCamps().get(this.c1.getId())).isNotEqualTo(this.c1);

    this.cModel.store(this.c2);
    this.c1.setRatePerDayChildren(null);
    assertThat(this.cModel.getCamp(this.c2.getId())).isEqualTo(this.c2);
    assertThat(this.persistenceModel.getMapOfCamps().get(this.c2.getId())).isEqualTo(this.c2);
    assertThat(this.cModel.getCamp(this.c1.getId())).isEqualTo(this.c1);
    assertThat(this.persistenceModel.getMapOfCamps().get(this.c1.getId())).isEqualTo(this.c1);
    this.c2.setRatePerDayChildren("new bank");
    assertThat(this.cModel.getCamp(this.c2.getId())).isNotEqualTo(this.c2);
    assertThat(this.persistenceModel.getMapOfCamps().get(this.c2.getId())).isNotEqualTo(this.c2);
    assertThat(this.cModel.getCamp(this.c1.getId())).isEqualTo(this.c1);
    assertThat(this.persistenceModel.getMapOfCamps().get(this.c1.getId())).isEqualTo(this.c1);
    this.cModel.store(this.c2);
    assertThat(this.cModel.getCamp(this.c2.getId())).isEqualTo(this.c2);
    assertThat(this.persistenceModel.getMapOfCamps().get(this.c2.getId())).isEqualTo(this.c2);
  }

  /**
   * Test method for {@link CampModel#getListOfCamps()}
   */
  @Test
  public void testGetListOfCamps() {
    this.cModel.store(this.c1);
    this.cModel.store(this.c1);
    assertThat(this.cModel.getListOfCamps()).containsExactly(this.c1);
    this.cModel.store(this.c2);
    this.cModel.deleteCamp(this.c1.getId());
    assertThat(this.cModel.getListOfCamps()).containsExactly(this.c2);
    this.cModel.store(this.c1);
    this.cModel.store(this.c1);
    assertThat(this.cModel.getListOfCamps()).contains(this.c2, this.c1);

  }
}