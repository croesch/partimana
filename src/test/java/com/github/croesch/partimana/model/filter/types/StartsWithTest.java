package com.github.croesch.partimana.model.filter.types;

import static org.fest.assertions.Assertions.assertThat;

import com.github.croesch.partimana.i18n.Text;
import org.junit.Test;

/**
 * Provides test cases for {@link StartsWith}.
 *
 * @author croesch
 * @since Date: Oct 21, 2012
 */
public class StartsWithTest {

  @Test
  public void testIsMatching() {
    final StartsWith startsWith = new StartsWith();
    startsWith.setFilterValue(null);

    assertThat(startsWith.getFilterValue()).isNull();
    assertThat(startsWith.isMatching("xyz")).isFalse();
    assertThat(startsWith.isMatching(null)).isFalse();

    startsWith.setFilterValue("bar");
    assertThat(startsWith.getFilterValue()).isEqualTo("bar");
    assertThat(startsWith.isMatching("fooBar")).isFalse();
    assertThat(startsWith.isMatching(null)).isFalse();

    assertThat(startsWith.isMatching("foobar")).isFalse();
    assertThat(startsWith.isMatching("bare")).isTrue();
    assertThat(startsWith.isMatching("->bar<-")).isFalse();
  }

  @Test
  public void testShortDescription() {
    assertThat(new StartsWith().getShortDescription()).isNotNull();
    assertThat(new StartsWith().getShortDescription()).isEqualTo(Text.FILTER_TYPE_STARTS_WITH.text());
  }

  @Test
  public void testEquals() {
    final StartsWith filterType = new StartsWith();
    assertThat(filterType).isNotEqualTo(null);
    assertThat(filterType).isNotEqualTo(new After());
    assertThat(filterType).isNotEqualTo("");

    assertThat(filterType).isEqualTo(filterType);
    assertThat(filterType).isEqualTo(new StartsWith());

    filterType.setFilterValue("filter");
    assertThat(filterType).isNotEqualTo(new StartsWith());
    assertThat(new StartsWith()).isNotEqualTo(filterType);

    final StartsWith other = new StartsWith();
    other.setFilterValue("filter");
    assertThat(filterType).isEqualTo(other);
  }

  @Test
  public void testHashCode() {
    final StartsWith filterType = new StartsWith();

    assertThat(filterType.hashCode()).isEqualTo(filterType.hashCode());
    assertThat(filterType.hashCode()).isEqualTo(new StartsWith().hashCode());

    filterType.setFilterValue("filter");

    final StartsWith other = new StartsWith();
    other.setFilterValue("filter");
    assertThat(filterType.hashCode()).isEqualTo(other.hashCode());
  }
}
