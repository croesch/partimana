package com.github.croesch.partimana.model.filter.types;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;

import com.github.croesch.partimana.i18n.Text;

/**
 * Provides test cases for {@link NotEquals}.
 * 
 * @author croesch
 * @since Date: Oct 21, 2012
 */
public class NotEqualsTest {

  @Test
  public void testIsMatching_Object() {
    final NotEquals<Object> notEquals = new NotEquals<Object>();
    notEquals.setFilterValue(new Date(1234));

    assertThat(notEquals.isMatching(1234)).isTrue();
    assertThat(notEquals.isMatching("1234")).isTrue();
    assertThat(notEquals.isMatching(new Date(1235))).isTrue();
    assertThat(notEquals.isMatching(new Date(1234))).isFalse();
  }

  @Test
  public void testIsMatching_String() {
    final NotEquals<String> notEquals = new NotEquals<String>();
    notEquals.setFilterValue(null);

    assertThat(notEquals.getFilterValue()).isNull();
    assertThat(notEquals.isMatching("xyz")).isFalse();
    assertThat(notEquals.isMatching(null)).isFalse();

    notEquals.setFilterValue("bar");
    assertThat(notEquals.getFilterValue()).isEqualTo("bar");
    assertThat(notEquals.isMatching("bar")).isFalse();
    assertThat(notEquals.isMatching(null)).isFalse();

    assertThat(notEquals.isMatching("fooBar")).isTrue();
    assertThat(notEquals.isMatching("")).isTrue();
  }

  @Test
  public void testShortDescription() {
    assertThat(new NotEquals<String>().getShortDescription()).isNotNull();
    assertThat(new NotEquals<Object>().getShortDescription()).isEqualTo(Text.FILTER_TYPE_NOT_EQUALS.text());
    assertThat(new NotEquals<Integer>().getShortDescription()).isEqualTo(Text.FILTER_TYPE_NOT_EQUALS.text());
    assertThat(new NotEquals<Equals<Test>>().getShortDescription()).isEqualTo(Text.FILTER_TYPE_NOT_EQUALS.text());
  }
}
