package com.github.croesch.partimana.model.filter.types;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.github.croesch.partimana.i18n.Text;

/**
 * Provides test cases for {@link LessThan}.
 * 
 * @author croesch
 * @since Date: Oct 21, 2012
 */
public class LessThanTest {

  @Test
  public void testIsMatching() {
    final LessThan greaterThan = new LessThan();
    greaterThan.setFilterValue(null);

    assertThat(greaterThan.getFilterValue()).isNull();
    assertThat(greaterThan.isMatching(12)).isFalse();
    assertThat(greaterThan.isMatching(null)).isFalse();

    greaterThan.setFilterValue(14);
    assertThat(greaterThan.getFilterValue()).isEqualTo(14);
    assertThat(greaterThan.isMatching(14)).isFalse();
    assertThat(greaterThan.isMatching(null)).isFalse();

    assertThat(greaterThan.isMatching(13)).isTrue();
    assertThat(greaterThan.isMatching(0)).isTrue();
    assertThat(greaterThan.isMatching(-1)).isTrue();
    assertThat(greaterThan.isMatching(Integer.MIN_VALUE)).isTrue();
    assertThat(greaterThan.isMatching(15)).isFalse();
  }

  @Test
  public void testShortDescription() {
    assertThat(new LessThan().getShortDescription()).isNotNull();
    assertThat(new LessThan().getShortDescription()).isEqualTo(Text.FILTER_TYPE_LESS_THAN.text());
  }
}
