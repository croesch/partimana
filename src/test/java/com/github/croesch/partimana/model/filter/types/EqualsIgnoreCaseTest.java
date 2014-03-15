package com.github.croesch.partimana.model.filter.types;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.i18n.Text;
import org.junit.Test;

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

  @Test
  public void testEquals() {
    final EqualsIgnoreCase filterType = new EqualsIgnoreCase();
    assertThat(filterType).isNotEqualTo(null);
    assertThat(filterType).isNotEqualTo(new After());
    assertThat(filterType).isNotEqualTo("");

    assertThat(filterType).isEqualTo(filterType);
    assertThat(filterType).isEqualTo(new EqualsIgnoreCase());

    filterType.setFilterValue("filter");
    assertThat(filterType).isNotEqualTo(new EqualsIgnoreCase());
    assertThat(new EqualsIgnoreCase()).isNotEqualTo(filterType);

    final EqualsIgnoreCase other = new EqualsIgnoreCase();
    other.setFilterValue("filter");
    assertThat(filterType).isEqualTo(other);
  }

  @Test
  public void testHashCode() {
    final EqualsIgnoreCase filterType = new EqualsIgnoreCase();

    assertThat(filterType.hashCode()).isEqualTo(filterType.hashCode());
    assertThat(filterType.hashCode()).isEqualTo(new EqualsIgnoreCase().hashCode());

    filterType.setFilterValue("filter");

    final EqualsIgnoreCase other = new EqualsIgnoreCase();
    other.setFilterValue("filter");
    assertThat(filterType.hashCode()).isEqualTo(other.hashCode());
  }
}
