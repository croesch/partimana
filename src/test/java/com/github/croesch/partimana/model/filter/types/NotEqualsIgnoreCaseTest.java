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

  @Test
  public void testEquals() {
    final NotEqualsIgnoreCase filterType = new NotEqualsIgnoreCase();
    assertThat(filterType).isNotEqualTo(null);
    assertThat(filterType).isNotEqualTo(new After());
    assertThat(filterType).isNotEqualTo("");

    assertThat(filterType).isEqualTo(filterType);
    assertThat(filterType).isEqualTo(new NotEqualsIgnoreCase());

    filterType.setFilterValue("filter");
    assertThat(filterType).isNotEqualTo(new NotEqualsIgnoreCase());
    assertThat(new NotEqualsIgnoreCase()).isNotEqualTo(filterType);

    final NotEqualsIgnoreCase other = new NotEqualsIgnoreCase();
    other.setFilterValue("filter");
    assertThat(filterType).isEqualTo(other);
  }

  @Test
  public void testHashCode() {
    final NotEqualsIgnoreCase filterType = new NotEqualsIgnoreCase();

    assertThat(filterType.hashCode()).isEqualTo(filterType.hashCode());
    assertThat(filterType.hashCode()).isEqualTo(new NotEqualsIgnoreCase().hashCode());

    filterType.setFilterValue("filter");

    final NotEqualsIgnoreCase other = new NotEqualsIgnoreCase();
    other.setFilterValue("filter");
    assertThat(filterType.hashCode()).isEqualTo(other.hashCode());
  }
}
