package com.github.croesch.partimana.model.filter.types;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.github.croesch.partimana.i18n.Text;

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
}
