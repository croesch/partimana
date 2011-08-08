package com.github.croesch.model;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.github.croesch.types.Camp;
import com.github.croesch.types.CountyCouncil;
import com.github.croesch.types.Denomination;
import com.github.croesch.types.Gender;
import com.github.croesch.types.Participant;
import com.github.croesch.view.api.IView;

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
    this.model = new Model();
    this.model.setView(this);

    this.updateCalled = false;
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

    assertThat(this.updateCalled).isFalse();
    this.model.store(p);

    assertThat(this.updateCalled).isTrue();
  }

  /**
   * Test method for {@link Model#store(Camp)}.
   */
  @Test
  public final void testStoreCamp() {
    final Camp c = new Camp("name", new Date(12000), new Date(2900000), "place", "rate");

    assertThat(this.updateCalled).isFalse();
    this.model.store(c);

    assertThat(this.updateCalled).isTrue();
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
    this.model.setView(null);
    assertThat(this.updateCalled).isFalse();
    this.model.store(p);

    assertThat(this.updateCalled).isFalse();
  }

  /**
   * Test method for {@link Model#store(Camp)}.
   */
  @Test
  public final void testStoreCamp_ViewIsNull() {
    final Camp c = new Camp("name", new Date(12000), new Date(2900000), "place", "rate");

    this.model.setView(null);
    assertThat(this.updateCalled).isFalse();
    this.model.store(c);

    assertThat(this.updateCalled).isFalse();
  }

  /**
   * Test method for {@link Model#delete(Participant)}.
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
    assertThat(this.updateCalled).isFalse();
    this.model.deleteParticipant(p.getId());

    assertThat(this.updateCalled).isTrue();
  }

  /**
   * Test method for {@link Model#delete(Camp)}.
   */
  @Test
  public final void testDeleteCamp() {
    final Camp c = new Camp("name", new Date(12000), new Date(2900000), "place", "rate");
    assertThat(this.updateCalled).isFalse();
    this.model.deleteCamp(c.getId());

    assertThat(this.updateCalled).isTrue();
  }

  /**
   * Test method for {@link Model#delete(Participant)}.
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
    this.model.setView(null);
    assertThat(this.updateCalled).isFalse();
    this.model.deleteParticipant(p.getId());

    assertThat(this.updateCalled).isFalse();
  }

  /**
   * Test method for {@link Model#delete(Camp)}.
   */
  @Test
  public final void testDeleteCamp_ViewIsNull() {
    final Camp c = new Camp("name", new Date(12000), new Date(2900000), "place", "rate");

    this.model.setView(null);
    assertThat(this.updateCalled).isFalse();
    this.model.deleteParticipant(c.getId());

    assertThat(this.updateCalled).isFalse();
  }

  @Override
  public void update() {
    this.updateCalled = true;
  }
}
