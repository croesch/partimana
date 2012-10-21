package com.github.croesch.partimana.model.filter.types;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.github.croesch.partimana.i18n.Text;

/**
 * Provides test cases for {@link EqualsIgnoreCase}.
 * 
 * @author croesch
 * @since Date: Oct 21, 2012
 */
public class EqualsIgnoreCaseTest {

  @Test
  public void testIsMatching() {
    final EqualsIgnoreCase equals = new EqualsIgnoreCase();
    equals.setFilterValue(null);

    assertThat(equals.getFilterValue()).isNull();
    assertThat(equals.isMatching("xyz")).isFalse();
    assertThat(equals.isMatching(null)).isFalse();

    equals.setFilterValue("bar");
    assertThat(equals.getFilterValue()).isEqualTo("bar");
    assertThat(equals.isMatching("fooBar")).isFalse();
    assertThat(equals.isMatching(null)).isFalse();

    assertThat(equals.isMatching("bar")).isTrue();
    assertThat(equals.isMatching("Bar")).isTrue();
    assertThat(equals.isMatching("BAR")).isTrue();
    assertThat(equals.isMatching("BARe")).isFalse();
  }

  @Test
  public void testShortDescription() {
    assertThat(new EqualsIgnoreCase().getShortDescription()).isNotNull();
    assertThat(new EqualsIgnoreCase().getShortDescription()).isEqualTo(Text.FILTER_TYPE_EQUALS_IGNORE_CASE.text());
  }
}
