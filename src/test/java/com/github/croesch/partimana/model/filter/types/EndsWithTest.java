package com.github.croesch.partimana.model.filter.types;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.github.croesch.partimana.i18n.Text;

/**
 * Provides test cases for {@link EndsWith}.
 * 
 * @author croesch
 * @since Date: Oct 21, 2012
 */
public class EndsWithTest {

  @Test
  public void testIsMatching() {
    final EndsWith endsWith = new EndsWith();
    endsWith.setFilterValue(null);

    assertThat(endsWith.getFilterValue()).isNull();
    assertThat(endsWith.isMatching("xyz")).isFalse();
    assertThat(endsWith.isMatching(null)).isFalse();

    endsWith.setFilterValue("bar");
    assertThat(endsWith.getFilterValue()).isEqualTo("bar");
    assertThat(endsWith.isMatching("fooBar")).isFalse();
    assertThat(endsWith.isMatching(null)).isFalse();

    assertThat(endsWith.isMatching("foobar")).isTrue();
    assertThat(endsWith.isMatching("bare")).isFalse();
    assertThat(endsWith.isMatching("->bar<-")).isFalse();
  }

  @Test
  public void testShortDescription() {
    assertThat(new EndsWith().getShortDescription()).isNotNull();
    assertThat(new EndsWith().getShortDescription()).isEqualTo(Text.FILTER_TYPE_ENDS_WITH.text());
  }

  @Test
  public void testEquals() {
    final EndsWith filterType = new EndsWith();
    assertThat(filterType).isNotEqualTo(null);
    assertThat(filterType).isNotEqualTo(new After());
    assertThat(filterType).isNotEqualTo("");

    assertThat(filterType).isEqualTo(filterType);
    assertThat(filterType).isEqualTo(new EndsWith());

    filterType.setFilterValue("filter");
    assertThat(filterType).isNotEqualTo(new EndsWith());
    assertThat(new EndsWith()).isNotEqualTo(filterType);

    final EndsWith other = new EndsWith();
    other.setFilterValue("filter");
    assertThat(filterType).isEqualTo(other);
  }

  @Test
  public void testHashCode() {
    final EndsWith filterType = new EndsWith();

    assertThat(filterType.hashCode()).isEqualTo(filterType.hashCode());
    assertThat(filterType.hashCode()).isEqualTo(new EndsWith().hashCode());

    filterType.setFilterValue("filter");

    final EndsWith other = new EndsWith();
    other.setFilterValue("filter");
    assertThat(filterType.hashCode()).isEqualTo(other.hashCode());
  }
}
