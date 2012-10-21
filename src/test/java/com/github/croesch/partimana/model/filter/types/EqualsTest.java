package com.github.croesch.partimana.model.filter.types;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;

import com.github.croesch.partimana.i18n.Text;

/**
 * Provides test cases for {@link Equals}.
 * 
 * @author croesch
 * @since Date: Oct 21, 2012
 */
public class EqualsTest {

  @Test
  public void testIsMatching_Object() {
    final Equals<Object> equals = new Equals<Object>();
    equals.setFilterValue(new Date(1234));

    assertThat(equals.isMatching(1234)).isFalse();
    assertThat(equals.isMatching(new Date(1235))).isFalse();
    assertThat(equals.isMatching(new Date(1234))).isTrue();
  }

  @Test
  public void testIsMatching_String() {
    final Equals<String> equals = new Equals<String>();
    equals.setFilterValue(null);

    assertThat(equals.getFilterValue()).isNull();
    assertThat(equals.isMatching("xyz")).isFalse();
    assertThat(equals.isMatching(null)).isFalse();

    equals.setFilterValue("bar");
    assertThat(equals.getFilterValue()).isEqualTo("bar");
    assertThat(equals.isMatching("fooBar")).isFalse();
    assertThat(equals.isMatching(null)).isFalse();

    assertThat(equals.isMatching("bar")).isTrue();
  }

  @Test
  public void testShortDescription() {
    assertThat(new Equals<String>().getShortDescription()).isNotNull();
    assertThat(new Equals<Object>().getShortDescription()).isEqualTo(Text.FILTER_TYPE_EQUALS.text());
    assertThat(new Equals<Integer>().getShortDescription()).isEqualTo(Text.FILTER_TYPE_EQUALS.text());
    assertThat(new Equals<Equals<Test>>().getShortDescription()).isEqualTo(Text.FILTER_TYPE_EQUALS.text());
  }

  @Test
  public void testEquals() {
    final Equals<String> filterType = new Equals<String>();
    assertThat(filterType).isNotEqualTo(null);
    assertThat(filterType).isNotEqualTo(new After());
    assertThat(filterType).isNotEqualTo("");

    assertThat(filterType).isEqualTo(filterType);
    assertThat(filterType).isEqualTo(new Equals<Object>());

    filterType.setFilterValue("filter");
    assertThat(filterType).isNotEqualTo(new Equals<String>());
    assertThat(new Equals<String>()).isNotEqualTo(filterType);

    final Equals<Object> other = new Equals<Object>();
    other.setFilterValue("filter");
    assertThat(filterType).isEqualTo(other);
  }

  @Test
  public void testHashCode() {
    final Equals<String> filterType = new Equals<String>();

    assertThat(filterType.hashCode()).isEqualTo(filterType.hashCode());
    assertThat(filterType.hashCode()).isEqualTo(new Equals<Date>().hashCode());

    filterType.setFilterValue("filter");

    final Equals<Object> other = new Equals<Object>();
    other.setFilterValue("filter");
    assertThat(filterType.hashCode()).isEqualTo(other.hashCode());
  }
}
