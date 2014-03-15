package com.github.croesch.partimana.view.components;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * Provides test cases for {@link LongComparator}.
 *
 * @author croesch
 * @since Date: Sep 12, 2012
 */
public class LongComparatorTest {

  @Test
  public void testCompare_EqualValues() {
    final LongComparator comp = new LongComparator();
    assertThat(comp.compare(Long.valueOf(1), Long.valueOf(1))).isZero();
    assertThat(comp.compare(Long.valueOf(0), Long.valueOf(0))).isZero();
    assertThat(comp.compare(Long.valueOf(Integer.MIN_VALUE), Long.valueOf(Integer.MIN_VALUE))).isZero();
    assertThat(comp.compare(Long.valueOf(Integer.MAX_VALUE), Long.valueOf(Integer.MAX_VALUE))).isZero();
    assertThat(comp.compare(Long.valueOf(Long.MIN_VALUE), Long.valueOf(Long.MIN_VALUE))).isZero();
    assertThat(comp.compare(Long.valueOf(Long.MAX_VALUE), Long.valueOf(Long.MAX_VALUE))).isZero();
    assertThat(comp.compare(Long.valueOf(-1), Long.valueOf(-1))).isZero();
  }

  @Test
  public void testCompare_UnequalValues() {
    final LongComparator comp = new LongComparator();
    assertThat(comp.compare(Long.valueOf(1), Long.valueOf(10))).isLessThan(0);
    assertThat(comp.compare(Long.valueOf(10), Long.valueOf(1))).isGreaterThan(0);

    assertThat(comp.compare(Long.valueOf(0), Long.valueOf(Integer.MAX_VALUE))).isLessThan(0);
    assertThat(comp.compare(Long.valueOf(Integer.MAX_VALUE), Long.valueOf(0))).isGreaterThan(0);

    assertThat(comp.compare(Long.valueOf(Integer.MIN_VALUE), Long.valueOf(-1))).isLessThan(0);
    assertThat(comp.compare(Long.valueOf(-1), Long.valueOf(Integer.MIN_VALUE))).isGreaterThan(0);

    assertThat(comp.compare(Long.valueOf(Integer.MAX_VALUE), Long.valueOf(Long.MAX_VALUE))).isLessThan(0);
    assertThat(comp.compare(Long.valueOf(Long.MAX_VALUE), Long.valueOf(Integer.MAX_VALUE))).isGreaterThan(0);

    assertThat(comp.compare(Long.valueOf(Long.MIN_VALUE), Long.valueOf(Integer.MIN_VALUE))).isLessThan(0);
    assertThat(comp.compare(Long.valueOf(Integer.MIN_VALUE), Long.valueOf(Long.MIN_VALUE))).isGreaterThan(0);

    assertThat(comp.compare(Long.valueOf(Long.MAX_VALUE + 1), Long.valueOf(Long.MAX_VALUE))).isLessThan(0);
    assertThat(comp.compare(Long.valueOf(Long.MAX_VALUE), Long.valueOf(Long.MAX_VALUE + 1))).isGreaterThan(0);

    assertThat(comp.compare(Long.valueOf(-1), Long.valueOf(5555))).isLessThan(0);
    assertThat(comp.compare(Long.valueOf(5555), Long.valueOf(-1))).isGreaterThan(0);
  }
}
