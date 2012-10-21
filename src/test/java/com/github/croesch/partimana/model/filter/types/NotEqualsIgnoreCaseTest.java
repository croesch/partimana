package com.github.croesch.partimana.model.filter.types;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.github.croesch.partimana.i18n.Text;

/**
 * Provides test cases for {@link NotEqualsIgnoreCase}.
 * 
 * @author croesch
 * @since Date: Oct 21, 2012
 */
public class NotEqualsIgnoreCaseTest {

  @Test
  public void testIsMatching() {
    final NotEqualsIgnoreCase notEquals = new NotEqualsIgnoreCase();
    notEquals.setFilterValue(null);

    assertThat(notEquals.getFilterValue()).isNull();
    assertThat(notEquals.isMatching("xyz")).isFalse();
    assertThat(notEquals.isMatching(null)).isFalse();

    notEquals.setFilterValue("bar");
    assertThat(notEquals.getFilterValue()).isEqualTo("bar");
    assertThat(notEquals.isMatching("fooBar")).isTrue();
    assertThat(notEquals.isMatching(null)).isFalse();

    assertThat(notEquals.isMatching("bar")).isFalse();
    assertThat(notEquals.isMatching("Bar")).isFalse();
    assertThat(notEquals.isMatching("BAR")).isFalse();
    assertThat(notEquals.isMatching("BARe")).isTrue();
  }

  @Test
  public void testShortDescription() {
    assertThat(new NotEqualsIgnoreCase().getShortDescription()).isNotNull();
    assertThat(new NotEqualsIgnoreCase().getShortDescription()).isEqualTo(Text.FILTER_TYPE_NOT_EQUALS_IGNORE_CASE.text());
  }
}
