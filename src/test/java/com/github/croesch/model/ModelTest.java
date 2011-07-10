package com.github.croesch.model;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

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
  public final void testStore() {
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
   * Test method for {@link Model#store(Participant)}.
   */
  @Test
  public final void testStore_ParticipantIsNull() {
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
   * Test method for {@link Model#delete(Participant)}.
   */
  @Test
  public final void testDelete() {
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
    this.model.delete(p);

    assertThat(this.updateCalled).isTrue();
  }

  /**
   * Test method for {@link Model#delete(Participant)}.
   */
  @Test
  public final void testDelete_ParticipantIsNull() {
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
    this.model.delete(p);

    assertThat(this.updateCalled).isFalse();
  }

  @Override
  public void update() {
    this.updateCalled = true;
  }
}
