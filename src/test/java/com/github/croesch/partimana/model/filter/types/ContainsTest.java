package com.github.croesch.partimana.model.filter.types;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.i18n.Text;
import org.junit.Test;

/**
 * Provides test cases for {@link Contains}.
 *
 * @author croesch
 * @since Date: Oct 21, 2012
 */
public class ContainsTest {

  @Test
  public void testIsMatching() {
    final Contains contains = new Contains();
    contains.setFilterValue(null);

    assertThat(contains.getFilterValue()).isNull();
    assertThat(contains.isMatching("xyz")).isFalse();
    assertThat(contains.isMatching(null)).isFalse();

    contains.setFilterValue("bar");
    assertThat(contains.getFilterValue()).isEqualTo("bar");
    assertThat(contains.isMatching("fooBar")).isFalse();
    assertThat(contains.isMatching(null)).isFalse();

    assertThat(contains.isMatching("foobar")).isTrue();
    assertThat(contains.isMatching("bare")).isTrue();
    assertThat(contains.isMatching("->bar<-")).isTrue();
  }

  @Test
  public void testShortDescription() {
    assertThat(new Contains().getShortDescription()).isNotNull();
    assertThat(new Contains().getShortDescription()).isEqualTo(Text.FILTER_TYPE_CONTAINS.text());
  }

  @Test
  public void testEquals() {
    final Contains filterType = new Contains();
    assertThat(filterType).isNotEqualTo(null);
    assertThat(filterType).isNotEqualTo(new After());
    assertThat(filterType).isNotEqualTo("");

    assertThat(filterType).isEqualTo(filterType);
    assertThat(filterType).isEqualTo(new Contains());

    filterType.setFilterValue("filter");
    assertThat(filterType).isNotEqualTo(new Contains());
    assertThat(new Contains()).isNotEqualTo(filterType);

    final Contains other = new Contains();
    other.setFilterValue("filter");
    assertThat(filterType).isEqualTo(other);
  }

  @Test
  public void testHashCode() {
    final Contains filterType = new Contains();

    assertThat(filterType.hashCode()).isEqualTo(filterType.hashCode());
    assertThat(filterType.hashCode()).isEqualTo(new Contains().hashCode());

    filterType.setFilterValue("filter");

    final Contains other = new Contains();
    other.setFilterValue("filter");
    assertThat(filterType.hashCode()).isEqualTo(other.hashCode());
  }
}
