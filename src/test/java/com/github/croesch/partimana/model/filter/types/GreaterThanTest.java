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
}
