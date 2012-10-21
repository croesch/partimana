package com.github.croesch.partimana.model.filter.types;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.github.croesch.partimana.i18n.Text;

/**
 * Provides test cases for {@link GreaterThan}.
 * 
 * @author croesch
 * @since Date: Oct 21, 2012
 */
public class GreaterThanTest {

  @Test
  public void testIsMatching() {
    final GreaterThan greaterThan = new GreaterThan();
    greaterThan.setFilterValue(null);

    assertThat(greaterThan.getFilterValue()).isNull();
    assertThat(greaterThan.isMatching(12)).isFalse();
    assertThat(greaterThan.isMatching(null)).isFalse();

    greaterThan.setFilterValue(14);
    assertThat(greaterThan.getFilterValue()).isEqualTo(14);
    assertThat(greaterThan.isMatching(14)).isFalse();
    assertThat(greaterThan.isMatching(null)).isFalse();

    assertThat(greaterThan.isMatching(15)).isTrue();
    assertThat(greaterThan.isMatching(100)).isTrue();
    assertThat(greaterThan.isMatching(Integer.MAX_VALUE)).isTrue();
    assertThat(greaterThan.isMatching(14)).isFalse();
  }

  @Test
  public void testShortDescription() {
    assertThat(new GreaterThan().getShortDescription()).isNotNull();
    assertThat(new GreaterThan().getShortDescription()).isEqualTo(Text.FILTER_TYPE_GREATER_THAN.text());
  }

  @Test
  public void testEquals() {
    final GreaterThan filterType = new GreaterThan();
    assertThat(filterType).isNotEqualTo(null);
    assertThat(filterType).isNotEqualTo(new After());
    assertThat(filterType).isNotEqualTo(42);

    assertThat(filterType).isEqualTo(filterType);
    assertThat(filterType).isEqualTo(new GreaterThan());

    filterType.setFilterValue(42);
    assertThat(filterType).isNotEqualTo(new GreaterThan());
    assertThat(new GreaterThan()).isNotEqualTo(filterType);

    final GreaterThan other = new GreaterThan();
    other.setFilterValue(42);
    assertThat(filterType).isEqualTo(other);
  }

  @Test
  public void testHashCode() {
    final GreaterThan filterType = new GreaterThan();

    assertThat(filterType.hashCode()).isEqualTo(filterType.hashCode());
    assertThat(filterType.hashCode()).isEqualTo(new GreaterThan().hashCode());

    filterType.setFilterValue(42);

    final GreaterThan other = new GreaterThan();
    other.setFilterValue(Integer.valueOf(42));
    assertThat(filterType.hashCode()).isEqualTo(other.hashCode());
  }
}
