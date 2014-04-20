package com.github.croesch.partimana.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.model.helper.HashMapPersistenceModel;
import com.github.croesch.partimana.types.*;
import com.github.croesch.partimana.view.api.IView;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

/**
 * Provides test methods for {@link Model}.
 *
 * @author croesch
 * @since Date: Jul 10, 2011
 */
public class ModelTest implements IView {

  private Model model;

  private boolean updateCalled;

  /**
   * Initialises the model.
   *
   * @since Date: Jul 10, 2011
   */
  @Before
  public void setUp() {
    model = new Model(new HashMapPersistenceModel());
    model.setView(this);

    updateCalled = false;
  }

  /**
   * Test method for {@link Model#store(Participant)}.
   */
  @Test
  public final void testStoreParticipant() {
    final Participant p = new Participant("lastname",
                                          "firstname",
                                          Gender.MALE,
                                          Denomination.MUSLIM,
                                          new Date(),
                                          "street",
                                          13245,
                                          "city",
                                          CountyCouncil.COUNTY_KIRCHHEIMBOLANDEN);

    assertThat(updateCalled).isFalse();
    model.store(p);

    assertThat(updateCalled).isTrue();
  }

  @Test
  public final void testGetParticipant() {
    final Participant p = new Participant("lastname",
                                          "firstname",
                                          Gender.MALE,
                                          Denomination.MUSLIM,
                                          new Date(),
                                          "street",
                                          13245,
                                          "city",
                                          CountyCouncil.COUNTY_KIRCHHEIMBOLANDEN);
    model.setView(null);

    model.store(p);
    assertThat(model.getParticipant(p.getId())).isNotSameAs(p);
    assertThat(model.getParticipant(p.getId())).isEqualTo(p);
  }

  @Test
  public final void testGetListOfParticipants() {
    final Participant p1 = new Participant("lastname",
                                           "firstname",
                                           Gender.MALE,
                                           Denomination.MUSLIM,
                                           new Date(),
                                           "street",
                                           13245,
                                           "city",
                                           CountyCouncil.COUNTY_KIRCHHEIMBOLANDEN);
    final Participant p2 = new Participant("mustermann",
                                           "marianne",
                                           Gender.FEMALE,
                                           Denomination.FREE_CHURCH,
                                           new Date(),
                                           "musterstreet",
                                           11245,
                                           "mustercity",
                                           CountyCouncil.COUNTY_KUSEL);
    model.setView(null);

    assertThat(model.getListOfParticipants()).isEmpty();

    model.store(p1);
    model.store(p2);

    assertThat(model.getListOfParticipants()).containsOnly(p1, p2);

    model.deleteParticipant(p1.getId());
    assertThat(model.getListOfParticipants()).containsOnly(p2);

    model.deleteParticipant(p1.getId());
    assertThat(model.getListOfParticipants()).containsOnly(p2);

    model.deleteParticipant(p2.getId());
    assertThat(model.getListOfParticipants()).isEmpty();
  }

  @Test
  public final void testGetListOfCamps() {
    final Participant p = new Participant("lastname",
                                          "firstname",
                                          Gender.MALE,
                                          Denomination.MUSLIM,
                                          new Date(),
                                          "street",
                                          13245,
                                          "city",
                                          CountyCouncil.COUNTY_KIRCHHEIMBOLANDEN);
    final Camp c1 = new Camp("name", new Date(12000), new Date(2900000), "place", "rate");
    final Camp c2 = new Camp("mustername", new Date(2000), new Date(12900000), "musterplace", "the-rate");
    model.setView(null);

    assertThat(model.getListOfCamps()).isEmpty();

    model.store(p);
    model.store(c1);
    model.store(c2);

    assertThat(model.getListOfCamps()).containsOnly(c1, c2);

    model.deleteCamp(c1.getId());
    assertThat(model.getListOfCamps()).containsOnly(c2);

    model.deleteCamp(c1.getId());
    assertThat(model.getListOfCamps()).containsOnly(c2);

    model.deleteCamp(c2.getId());
    assertThat(model.getListOfCamps()).isEmpty();
  }

  @Test
  public final void testGetCamp() {
    final Camp c = new Camp("name", new Date(12000), new Date(2900000), "place", "rate");

    model.setView(null);
    model.store(c);

    assertThat(model.getCamp(c.getId())).isNotSameAs(c);
    assertThat(model.getCamp(c.getId())).isEqualTo(c);
  }

  /**
   * Test method for {@link Model#store(Camp)}.
   */
  @Test
  public final void testStoreCamp() {
    final Camp c = new Camp("name", new Date(12000), new Date(2900000), "place", "rate");

    assertThat(updateCalled).isFalse();
    model.store(c);

    assertThat(updateCalled).isTrue();
  }

  /**
   * Test method for {@link Model#store(Participant)}.
   */
  @Test
  public final void testStoreParticipant_ViewIsNull() {
    final Participant p = new Participant("lastname",
                                          "firstname",
                                          Gender.MALE,
                                          Denomination.MUSLIM,
                                          new Date(),
                                          "street",
                                          13245,
                                          "city",
                                          CountyCouncil.COUNTY_KIRCHHEIMBOLANDEN);
    model.setView(null);
    assertThat(updateCalled).isFalse();
    model.store(p);

    assertThat(updateCalled).isFalse();
  }

  /**
   * Test method for {@link Model#store(Camp)}.
   */
  @Test
  public final void testStoreCamp_ViewIsNull() {
    final Camp c = new Camp("name", new Date(12000), new Date(2900000), "place", "rate");

    model.setView(null);
    assertThat(updateCalled).isFalse();
    model.store(c);

    assertThat(updateCalled).isFalse();
  }

  /**
   * Test method for {@link Model#deleteParticipant(long)}.
   */
  @Test
  public final void testDeleteParticipant() {
    final Participant p = new Participant("lastname",
                                          "firstname",
                                          Gender.MALE,
                                          Denomination.MUSLIM,
                                          new Date(),
                                          "street",
                                          13245,
                                          "city",
                                          CountyCouncil.COUNTY_KIRCHHEIMBOLANDEN);
    assertThat(updateCalled).isFalse();
    model.deleteParticipant(p.getId());

    assertThat(updateCalled).isTrue();
  }

  /**
   * Test method for {@link Model#deleteCamp(long)}.
   */
  @Test
  public final void testDeleteCamp() {
    final Camp c = new Camp("name", new Date(12000), new Date(2900000), "place", "rate");
    assertThat(updateCalled).isFalse();
    model.deleteCamp(c.getId());

    assertThat(updateCalled).isTrue();
  }

  /**
   * Test method for {@link Model#deleteParticipant(long)}.
   */
  @Test
  public final void testDeleteParticipant_ViewIsNull() {
    final Participant p = new Participant("lastname",
                                          "firstname",
                                          Gender.MALE,
                                          Denomination.MUSLIM,
                                          new Date(),
                                          "street",
                                          13245,
                                          "city",
                                          CountyCouncil.COUNTY_KIRCHHEIMBOLANDEN);
    model.setView(null);
    assertThat(updateCalled).isFalse();
    model.deleteParticipant(p.getId());

    assertThat(updateCalled).isFalse();
  }

  /**
   * Test method for {@link Model#deleteCamp(long)}.
   */
  @Test
  public final void testDeleteCamp_ViewIsNull() {
    final Camp c = new Camp("name", new Date(12000), new Date(2900000), "place", "rate");

    model.setView(null);
    assertThat(updateCalled).isFalse();
    model.deleteParticipant(c.getId());

    assertThat(updateCalled).isFalse();
  }

  @Override
  public void update() {
    updateCalled = true;
  }
}
