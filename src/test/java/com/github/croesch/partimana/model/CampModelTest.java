package com.github.croesch.partimana.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.model.helper.HashMapPersistenceModel;
import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

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
   * @since Date: Aug 8, 2011
   */
  @Before
  public void setUp() {
    persistenceModel = new HashMapPersistenceModel();
    cModel = new CampModel(persistenceModel);

    c1 = new Camp("Sommercamp", new Date(99999), new Date(88888888), "Spain", "5,0€");
    c2 = new Camp("Wintercamp", new Date(444444), new Date(333333333), "Russia", "1,30 €");
  }

  /**
   * Test method for {@link CampModel#getCamp(long)}.
   */
  @Test
  public void testGetCamp() {
    persistenceModel.create(c1);
    persistenceModel.create(c2);
    cModel = new CampModel(persistenceModel);

    assertThat(cModel.getCamp(c1.getId())).isEqualTo(c1);
    assertThat(cModel.getCamp(c2.getId())).isEqualTo(c2);
    assertThat(cModel.getCamp(c1.getId())).isEqualTo(c1);
    assertThat(cModel.getCamp(Long.MAX_VALUE)).isNull();

    final Camp c = cModel.getCamp(c2.getId());
    c.setRatePerDayChildren("new bank");
    assertThat(cModel.getCamp(c2.getId())).isNotEqualTo(c);
  }

  /**
   * Test method for {@link CampModel#store(Camp)}.
   */
  @Test
  public void testCreateCampKAEE() {
    cModel.store(c1);
    cModel.store(c1);
    assertThat(cModel.getCamp(c1.getId())).isEqualTo(c1);
    assertThat(persistenceModel.getMapOfCamps().get(c1.getId())).isEqualTo(c1);

    c1.setRatePerDayChildren("new bank");
    cModel.store(c1);
    assertThat(cModel.getCamp(c1.getId())).isEqualTo(c1);
    assertThat(persistenceModel.getMapOfCamps().get(c1.getId())).isEqualTo(c1);
  }

  /**
   * Test method for {@link CampModel#deleteCamp(long)}.
   */
  @Test
  public void testDeleteCamp() {
    cModel.store(c1);
    assertThat(cModel.getCamp(c1.getId())).isEqualTo(c1);
    assertThat(persistenceModel.getMapOfCamps().get(c1.getId())).isEqualTo(c1);
    cModel.deleteCamp(c1.getId());
    assertThat(cModel.getCamp(c1.getId())).isNull();
    assertThat(persistenceModel.getMapOfCamps().get(c1.getId())).isNull();
    cModel.deleteCamp(c1.getId());
    assertThat(cModel.getCamp(c1.getId())).isNull();
    assertThat(persistenceModel.getMapOfCamps().get(c1.getId())).isNull();

    cModel.store(c1);
    assertThat(cModel.getCamp(c1.getId())).isEqualTo(c1);
    assertThat(persistenceModel.getMapOfCamps().get(c1.getId())).isEqualTo(c1);
    c1.setRatePerDayChildren("other bank");
    assertThat(cModel.getCamp(c1.getId())).isNotEqualTo(c1);
    assertThat(persistenceModel.getMapOfCamps().get(c1.getId())).isNotEqualTo(c1);
    cModel.deleteCamp(c1.getId());
    assertThat(cModel.getCamp(c1.getId())).isNull();
    assertThat(persistenceModel.getMapOfCamps().get(c1.getId())).isNull();
  }

  /**
   * Test method for {@link CampModel#store(Camp)}.
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public void testStoreCampRFSTNE() {
    cModel.store(null);
  }

  /**
   * Test method for {@link CampModel#store(Camp)}.
   */
  @Test
  public void testStoreCamp() {
    cModel.store(c1);
    assertThat(cModel.getCamp(c1.getId())).isEqualTo(c1);
    assertThat(persistenceModel.getMapOfCamps().get(c1.getId())).isEqualTo(c1);
    c1.setRatePerDayChildren("new bank");
    assertThat(cModel.getCamp(c1.getId())).isNotEqualTo(c1);
    assertThat(persistenceModel.getMapOfCamps().get(c1.getId())).isNotEqualTo(c1);

    cModel.store(c2);
    c1.setRatePerDayChildren(null);
    assertThat(cModel.getCamp(c2.getId())).isEqualTo(c2);
    assertThat(persistenceModel.getMapOfCamps().get(c2.getId())).isEqualTo(c2);
    assertThat(cModel.getCamp(c1.getId())).isEqualTo(c1);
    assertThat(persistenceModel.getMapOfCamps().get(c1.getId())).isEqualTo(c1);
    c2.setRatePerDayChildren("new bank");
    assertThat(cModel.getCamp(c2.getId())).isNotEqualTo(c2);
    assertThat(persistenceModel.getMapOfCamps().get(c2.getId())).isNotEqualTo(c2);
    assertThat(cModel.getCamp(c1.getId())).isEqualTo(c1);
    assertThat(persistenceModel.getMapOfCamps().get(c1.getId())).isEqualTo(c1);
    cModel.store(c2);
    assertThat(cModel.getCamp(c2.getId())).isEqualTo(c2);
    assertThat(persistenceModel.getMapOfCamps().get(c2.getId())).isEqualTo(c2);
  }

  /**
   * Test method for {@link CampModel#getListOfCamps()}
   */
  @Test
  public void testGetListOfCamps() {
    cModel.store(c1);
    cModel.store(c1);
    assertThat(cModel.getListOfCamps()).containsExactly(c1);
    cModel.store(c2);
    cModel.deleteCamp(c1.getId());
    assertThat(cModel.getListOfCamps()).containsExactly(c2);
    cModel.store(c1);
    cModel.store(c1);
    assertThat(cModel.getListOfCamps()).contains(c2, c1);
  }
}
