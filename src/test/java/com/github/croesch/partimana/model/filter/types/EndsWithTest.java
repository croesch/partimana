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
}
