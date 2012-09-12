package com.github.croesch.partimana.model;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;
import com.github.croesch.partimana.view.api.IView;

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
    this.model.setView(null);

    this.model.store(p);
    assertThat(this.model.getParticipant(p.getId())).isNotSameAs(p);
    assertThat(this.model.getParticipant(p.getId())).isEqualTo(p);
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
    this.model.setView(null);

    assertThat(this.model.getListOfParticipants()).isEmpty();

    this.model.store(p1);
    this.model.store(p2);

    assertThat(this.model.getListOfParticipants()).containsOnly(p1, p2);

    this.model.deleteParticipant(p1.getId());
    assertThat(this.model.getListOfParticipants()).containsOnly(p2);

    this.model.deleteParticipant(p1.getId());
    assertThat(this.model.getListOfParticipants()).containsOnly(p2);

    this.model.deleteParticipant(p2.getId());
    assertThat(this.model.getListOfParticipants()).isEmpty();
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
    this.model.setView(null);

    assertThat(this.model.getListOfCamps()).isEmpty();

    this.model.store(p);
    this.model.store(c1);
    this.model.store(c2);

    assertThat(this.model.getListOfCamps()).containsOnly(c1, c2);

    this.model.deleteCamp(c1.getId());
    assertThat(this.model.getListOfCamps()).containsOnly(c2);

    this.model.deleteCamp(c1.getId());
    assertThat(this.model.getListOfCamps()).containsOnly(c2);

    this.model.deleteCamp(c2.getId());
    assertThat(this.model.getListOfCamps()).isEmpty();
  }

  @Test
  public final void testGetCamp() {
    final Camp c = new Camp("name", new Date(12000), new Date(2900000), "place", "rate");

    this.model.setView(null);
    this.model.store(c);

    assertThat(this.model.getCamp(c.getId())).isNotSameAs(c);
    assertThat(this.model.getCamp(c.getId())).isEqualTo(c);
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
