package com.github.croesch.partimana.model.filter.types;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;

import com.github.croesch.partimana.i18n.Text;

/**
 * Provides test cases for {@link After}.
 * 
 * @author croesch
 * @since Date: Oct 21, 2012
 */
public class AfterTest {

  @Test
  public void testIsMatching() {
    final After after = new After();
    after.setFilterValue(null);

    assertThat(after.getFilterValue()).isNull();
    assertThat(after.isMatching(new Date())).isFalse();
    assertThat(after.isMatching(null)).isFalse();

    final Date date = new Date();
    after.setFilterValue(date);
    assertThat(after.getFilterValue()).isEqualTo(date);
    // make sure the date is NOT after the filter value
    assertThat(after.isMatching(new Date(System.currentTimeMillis() - 100))).isFalse();
    assertThat(after.isMatching(null)).isFalse();

    after.setFilterValue(new Date(10));
    assertThat(after.isMatching(new Date())).isTrue();
  }

  @Test
  public void testShortDescription() {
    assertThat(new After().getShortDescription()).isNotNull();
    assertThat(new After().getShortDescription()).isEqualTo(Text.FILTER_TYPE_AFTER.text());
  }

  @Test
  public void testEquals() {
    final After filterType = new After();
    assertThat(filterType).isNotEqualTo(null);
    assertThat(filterType).isNotEqualTo(new Before());
    assertThat(filterType).isNotEqualTo(new Date());

    assertThat(filterType).isEqualTo(filterType);
    assertThat(filterType).isEqualTo(new After());

    final Date date = new Date();
    filterType.setFilterValue(date);
    assertThat(filterType).isNotEqualTo(new After());
    assertThat(new After()).isNotEqualTo(filterType);

    final After other = new After();
    other.setFilterValue(date);
    assertThat(filterType).isEqualTo(other);
  }

  @Test
  public void testHashCode() {
    final After filterType = new After();

    assertThat(filterType.hashCode()).isEqualTo(filterType.hashCode());
    assertThat(filterType.hashCode()).isEqualTo(new After().hashCode());

    final Date date = new Date();
    filterType.setFilterValue(date);

    final After other = new After();
    other.setFilterValue(date);
    assertThat(filterType.hashCode()).isEqualTo(other.hashCode());
  }
}
