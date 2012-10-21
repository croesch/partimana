package com.github.croesch.partimana.model.filter.types;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;

import com.github.croesch.partimana.i18n.Text;

/**
 * Provides test cases for {@link Before}.
 * 
 * @author croesch
 * @since Date: Oct 21, 2012
 */
public class BeforeTest {

  @Test
  public void testIsMatching() {
    final Before before = new Before();
    before.setFilterValue(null);

    assertThat(before.getFilterValue()).isNull();
    assertThat(before.isMatching(new Date())).isFalse();
    assertThat(before.isMatching(null)).isFalse();

    final Date date = new Date();
    before.setFilterValue(date);
    assertThat(before.getFilterValue()).isEqualTo(date);
    // make sure the date is NOT before the filter value
    assertThat(before.isMatching(new Date(System.currentTimeMillis() + 1))).isFalse();
    assertThat(before.isMatching(null)).isFalse();

    assertThat(before.isMatching(new Date(100))).isTrue();
  }

  @Test
  public void testShortDescription() {
    assertThat(new Before().getShortDescription()).isNotNull();
    assertThat(new Before().getShortDescription()).isEqualTo(Text.FILTER_TYPE_BEFORE.text());
  }
}
