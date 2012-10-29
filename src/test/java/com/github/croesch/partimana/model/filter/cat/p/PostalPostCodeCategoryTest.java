package com.github.croesch.partimana.model.filter.cat.p;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.cat.c.LocationCategory;
import com.github.croesch.partimana.model.filter.types.Equals;
import com.github.croesch.partimana.model.filter.types.GreaterThan;
import com.github.croesch.partimana.model.filter.types.LessThan;
import com.github.croesch.partimana.model.filter.types.NotEquals;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;

/**
 * Provides test cases for {@link PostalPostCodeCategory}.
 * 
 * @author croesch
 * @since Date: Oct 27, 2012
 */
public class PostalPostCodeCategoryTest {

  private PostalPostCodeCategory category;

  @org.junit.Before
  public void setUp() {
    this.category = new PostalPostCodeCategory();
  }

  @Test
  public void testGetFilters() {
    assertThat(this.category.getFilterTypes()).containsOnly(new Equals<Object>(), new NotEquals<Object>(),
                                                            new LessThan(), new GreaterThan());

    final IFilterType<Integer> filterType = this.category.getFilterTypes().get(0);
    filterType.setFilterValue(12345);

    assertThat(this.category.getFilterTypes().contains(filterType)).isFalse();
  }

  @Test
  public void testSetAndGetFilter() {
    assertThat(this.category.getFilter()).isNull();
    this.category.setFilter(new Equals<Integer>());
    assertThat(this.category.getFilter()).isEqualTo(new Equals<Integer>());
    this.category.setFilter(null);
    assertThat(this.category.getFilter()).isNull();
  }

  @Test
  public void testGetShortDescription() {
    assertThat(this.category.getShortDescription()).isEqualTo(Text.FILTER_CAT_PARTICIPANT_POSTAL_POSTAL_CODE.text());
    assertThat(this.category.getShortDescription()).isEqualTo(Text.POST_CODE.text() + " ("
                                                                      + Text.PARTICIPANT_ADDRESS_POSTAL.text() + ")");
  }

  @Test
  public void isMatchingFilter() {
    assertThat(this.category.isMatchingFilter(null)).isFalse();

    final Participant p = new Participant("Musterfrau",
                                          "Maxi",
                                          Gender.FEMALE,
                                          Denomination.CATHOLIC,
                                          new Date(100000000),
                                          "street",
                                          12,
                                          "city",
                                          CountyCouncil.COUNTY_RHEIN_PFALZ);
    p.setPostCodePostal(15);
    assertThat(this.category.isMatchingFilter(p)).isFalse();

    final LessThan less = new LessThan();
    less.setFilterValue(12);
    this.category.setFilter(less);
    assertThat(this.category.isMatchingFilter(p)).isFalse();

    final GreaterThan greater = new GreaterThan();
    greater.setFilterValue(12);
    this.category.setFilter(greater);
    assertThat(this.category.isMatchingFilter(p)).isTrue();
  }

  @Test
  public void testEquals() {
    assertThat(this.category).isEqualTo(new PostalPostCodeCategory());
    assertThat(this.category).isEqualTo(this.category);
    assertThat(this.category).isNotEqualTo(null);
    assertThat(this.category).isEqualTo(new LocationCategory());
    assertThat(this.category).isNotEqualTo("category");

    this.category.setFilter(new Equals<Integer>());
    final PostalPostCodeCategory other = new PostalPostCodeCategory();
    assertThat(this.category).isNotEqualTo(other);
    assertThat(other).isNotEqualTo(this.category);
    other.setFilter(new Equals<Integer>());
    assertThat(this.category).isEqualTo(other);

    final LocationCategory different = new LocationCategory();
    different.setFilter(new Equals<String>());
    assertThat(this.category).isEqualTo(different);
    this.category.getFilter().setFilterValue(42);
    assertThat(this.category).isNotEqualTo(different);
    assertThat(different).isNotEqualTo(this.category);
  }

  @Test
  public void testHashCode() {
    assertThat(this.category.hashCode()).isEqualTo(new PostalPostCodeCategory().hashCode());
    assertThat(this.category.hashCode()).isEqualTo(this.category.hashCode());
    assertThat(this.category.hashCode()).isEqualTo(new LocationCategory().hashCode());

    this.category.setFilter(new Equals<Integer>());
    final PostalPostCodeCategory other = new PostalPostCodeCategory();
    other.setFilter(new Equals<Integer>());
    assertThat(this.category.hashCode()).isEqualTo(other.hashCode());

    final LocationCategory different = new LocationCategory();
    different.setFilter(new Equals<String>());
    assertThat(this.category.hashCode()).isEqualTo(different.hashCode());
  }
}
