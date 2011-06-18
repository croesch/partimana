package com.github.croesch.types;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.github.croesch.types.exceptions.RequiredFieldSetToNullException;

/**
 * Provides test methods for {@link Camp}
 * 
 * @author croesch
 * @since Date: Jun 18, 2011
 */
public class CampTest {

  /** object under test */
  private Camp camp;

  /**
   * Sets up the test object.
   * 
   * @author croesch
   * @since Date: Jun 18, 2011
   */
  @Before
  public final void setUp() {
    final int untilDate = 100000;
    this.camp = new Camp("Testcamp", new Date(0), new Date(untilDate), "here", "100");
  }

  /**
   * Test method for {@link Camp}
   */
  @Test
  public final void testCamp() {
    final int untilDate = 100000;
    assertThat(this.camp.getName()).isEqualTo("Testcamp");
    assertThat(this.camp.getFromDate()).isEqualTo(new Date(0));
    assertThat(this.camp.getUntilDate()).isEqualTo(new Date(untilDate));
    assertThat(this.camp.getLocation()).isEqualTo("here");
    assertThat(this.camp.getRatePerParticipant()).isEqualTo("100");
  }

  /**
   * Test method for {@link Camp#setName(String)} and {@link Camp#getName()}
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testSetAndGetNameRFSTNE() {
    this.camp.setName(null);
  }

  /**
   * Test method for {@link Camp#setName(String)} and {@link Camp#getName()}
   */
  @Test
  public final void testSetAndGetName() {
    this.camp.setName("name");
    assertThat(this.camp.getName()).isEqualTo("name");

    this.camp.setName("...");
    assertThat(this.camp.getName()).isEqualTo("...");

    this.camp.setName("");
    assertThat(this.camp.getName()).isEqualTo("");
  }

  /**
   * Test method for {@link Camp#setFromDate(Date)} and {@link Camp#getFromDate()}
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testSetAndGetFromDateRFSTNE() {
    this.camp.setFromDate(null);
  }

  /**
   * Test method for {@link Camp#setFromDate(Date)} and {@link Camp#getFromDate()}
   */
  @Test
  public final void testSetAndGetFromDate() {
    final Date d1 = new Date();
    this.camp.setFromDate(d1);
    assertThat(this.camp.getFromDate()).isEqualTo(d1.clone());

    final Date d2 = new Date(17);
    this.camp.setFromDate(d2);
    assertThat(this.camp.getFromDate()).isEqualTo(d2.clone());

    final Date d3 = new Date(99);
    this.camp.setFromDate(d3);
    assertThat(this.camp.getFromDate()).isEqualTo(d3.clone());
  }

  /**
   * Test method for {@link Camp#setUntilDate(Date)} and {@link Camp#getUntilDate()}
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testSetAndGetUntilDateRFSTNE() {
    this.camp.setUntilDate(null);
  }

  /**
   * Test method for {@link Camp#setUntilDate(Date)} and {@link Camp#getUntilDate()}
   */
  @Test
  public final void testSetAndGetUntilDate() {
    final Date d1 = new Date();
    this.camp.setUntilDate(d1);
    assertThat(this.camp.getUntilDate()).isEqualTo(d1.clone());

    final Date d2 = new Date(17);
    this.camp.setUntilDate(d2);
    assertThat(this.camp.getUntilDate()).isEqualTo(d2.clone());

    final Date d3 = new Date(99);
    this.camp.setUntilDate(d3);
    assertThat(this.camp.getUntilDate()).isEqualTo(d3.clone());
  }

  /**
   * Test method for {@link Camp#setLocation(String)} and {@link Camp#getLocation()}
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testSetAndGetLocationRFSTNE() {
    this.camp.setLocation(null);
  }

  /**
   * Test method for {@link Camp#setLocation(String)} and {@link Camp#getLocation()}
   */
  @Test
  public final void testSetAndGetLocation() {
    this.camp.setLocation("loc");
    assertThat(this.camp.getLocation()).isEqualTo("loc");

    this.camp.setLocation("...");
    assertThat(this.camp.getLocation()).isEqualTo("...");

    this.camp.setLocation("");
    assertThat(this.camp.getLocation()).isEqualTo("");
  }

  /**
   * Test method for {@link Camp#setRatePerParticipant(String)} and {@link Camp#getRatePerParticipant()}
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testSetAndGetRatePerParticipantRFSTNE() {
    this.camp.setRatePerParticipant(null);
  }

  /**
   * Test method for {@link Camp#setRatePerParticipant(String)} and {@link Camp#getRatePerParticipant()}
   */
  @Test
  public final void testSetAndGetRatePerParticipant() {
    this.camp.setRatePerParticipant("12");
    assertThat(this.camp.getRatePerParticipant()).isEqualTo("12");

    this.camp.setRatePerParticipant("...");
    assertThat(this.camp.getRatePerParticipant()).isEqualTo("...");

    this.camp.setRatePerParticipant("");
    assertThat(this.camp.getRatePerParticipant()).isEqualTo("");
  }

  /**
   * Test method for {@link Camp#setRatePerDayChildren(String)} and {@link Camp#getRatePerDayChildren()}
   */
  @Test
  public final void testSetAndGetRatePerDayChildren() {
    this.camp.setRatePerDayChildren("12");
    assertThat(this.camp.getRatePerDayChildren()).isEqualTo("12");

    this.camp.setRatePerDayChildren("...");
    assertThat(this.camp.getRatePerDayChildren()).isEqualTo("...");

    this.camp.setRatePerDayChildren("");
    assertThat(this.camp.getRatePerDayChildren()).isEqualTo("");
  }

}
