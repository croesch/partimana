package com.github.croesch.partimana.model.filter.types;

import static org.fest.assertions.Assertions.assertThat;

import com.github.croesch.partimana.i18n.Text;
import org.junit.Test;

/**
 * Provides test cases for {@link LessThan}.
 *
 * @author croesch
 * @since Date: Oct 21, 2012
 */
public class LessThanTest {

  @Test
  public void testIsMatching() {
    final LessThan lessThan = new LessThan();
    lessThan.setFilterValue(null);

    assertThat(lessThan.getFilterValue()).isNull();
    assertThat(lessThan.isMatching(12)).isFalse();
    assertThat(lessThan.isMatching(null)).isFalse();

    lessThan.setFilterValue(14);
    assertThat(lessThan.getFilterValue()).isEqualTo(14);
    assertThat(lessThan.isMatching(14)).isFalse();
    assertThat(lessThan.isMatching(null)).isFalse();

    assertThat(lessThan.isMatching(13)).isTrue();
    assertThat(lessThan.isMatching(0)).isTrue();
    assertThat(lessThan.isMatching(-1)).isTrue();
    assertThat(lessThan.isMatching(Integer.MIN_VALUE)).isTrue();
    assertThat(lessThan.isMatching(15)).isFalse();
  }

  @Test
  public void testShortDescription() {
    assertThat(new LessThan().getShortDescription()).isNotNull();
    assertThat(new LessThan().getShortDescription()).isEqualTo(Text.FILTER_TYPE_LESS_THAN.text());
  }

  @Test
  public void testEquals() {
    final LessThan filterType = new LessThan();
    assertThat(filterType).isNotEqualTo(null);
    assertThat(filterType).isNotEqualTo(new After());
    assertThat(filterType).isNotEqualTo(42);

    assertThat(filterType).isEqualTo(filterType);
    assertThat(filterType).isEqualTo(new LessThan());

    filterType.setFilterValue(42);
    assertThat(filterType).isNotEqualTo(new LessThan());
    assertThat(new LessThan()).isNotEqualTo(filterType);

    final LessThan other = new LessThan();
    other.setFilterValue(42);
    assertThat(filterType).isEqualTo(other);
  }

  @Test
  public void testHashCode() {
    final LessThan filterType = new LessThan();

    assertThat(filterType.hashCode()).isEqualTo(filterType.hashCode());
    assertThat(filterType.hashCode()).isEqualTo(new LessThan().hashCode());

    filterType.setFilterValue(42);

    final LessThan other = new LessThan();
    other.setFilterValue(Integer.valueOf(42));
    assertThat(filterType.hashCode()).isEqualTo(other.hashCode());
  }
}
