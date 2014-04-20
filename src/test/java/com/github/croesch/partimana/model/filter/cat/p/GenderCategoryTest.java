package com.github.croesch.partimana.model.filter.cat.p;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.cat.c.LocationCategory;
import com.github.croesch.partimana.model.filter.types.GenderEquals;
import com.github.croesch.partimana.model.filter.types.GenderNotEquals;
import com.github.croesch.partimana.model.filter.types.StringEquals;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;
import java.util.Date;
import org.junit.Test;

/**
 * Provides test cases for {@link GenderCategory}.
 *
 * @author croesch
 * @since Date: Oct 27, 2012
 */
public class GenderCategoryTest {

  private GenderCategory category;

  @org.junit.Before
  public void setUp() {
    category = new GenderCategory();
  }

  @Test
  public void testGetFilters() {
    assertThat(category.getFilterTypes()).containsOnly(new GenderEquals(), new GenderNotEquals());

    final IFilterType<Gender> filterType = category.getFilterTypes().get(0);
    filterType.setFilterValue(Gender.FEMALE);

    assertThat(category.getFilterTypes().contains(filterType)).isFalse();
  }

  @Test
  public void testSetAndGetFilter() {
    assertThat(category.getFilter()).isNull();
    category.setFilter(new GenderEquals());
    assertThat(category.getFilter()).isEqualTo(new GenderEquals());
    category.setFilter(null);
    assertThat(category.getFilter()).isNull();
  }

  @Test
  public void testGetShortDescription() {
    assertThat(category.getShortDescription()).isEqualTo(Text.FILTER_CAT_PARTICIPANT_GENDER.text());
    assertThat(category.getShortDescription()).isEqualTo(Text.PARTICIPANT_GENDER.text());
  }

  @Test
  public void testIsMatchingFilter() {
    assertThat(category.isMatchingFilter(null)).isFalse();

    final Participant p = new Participant("Musterfrau",
                                          "Maxi",
                                          Gender.FEMALE,
                                          Denomination.CATHOLIC,
                                          new Date(100000000),
                                          "street",
                                          12,
                                          "city",
                                          CountyCouncil.COUNTY_RHEIN_PFALZ);
    assertThat(category.isMatchingFilter(p)).isFalse();

    final GenderEquals equals = new GenderEquals();
    equals.setFilterValue(Gender.MALE);
    category.setFilter(equals);
    assertThat(category.isMatchingFilter(p)).isFalse();

    final GenderNotEquals notEquals = new GenderNotEquals();
    notEquals.setFilterValue(Gender.MALE);
    category.setFilter(notEquals);
    assertThat(category.isMatchingFilter(p)).isTrue();
  }

  @Test
  public void testEquals() {
    assertThat(category).isEqualTo(new GenderCategory());
    assertThat(category).isEqualTo(category);
    assertThat(category).isNotEqualTo(null);
    assertThat(category).isNotEqualTo(new LocationCategory());
    assertThat(category).isNotEqualTo("category");

    category.setFilter(new GenderEquals());
    final GenderCategory other = new GenderCategory();
    assertThat(category).isNotEqualTo(other);
    assertThat(other).isNotEqualTo(category);
    other.setFilter(new GenderEquals());
    assertThat(category).isEqualTo(other);

    final LocationCategory different = new LocationCategory();
    different.setFilter(new StringEquals());
    category.getFilter().setFilterValue(Gender.MALE);
    assertThat(category).isNotEqualTo(different);
    assertThat(different).isNotEqualTo(category);
  }

  @Test
  public void testHashCode() {
    assertThat(category.hashCode()).isEqualTo(new GenderCategory().hashCode());
    assertThat(category.hashCode()).isEqualTo(category.hashCode());
    assertThat(category.hashCode()).isEqualTo(new LocationCategory().hashCode());

    category.setFilter(new GenderEquals());
    final GenderCategory other = new GenderCategory();
    other.setFilter(new GenderEquals());
    assertThat(category.hashCode()).isEqualTo(other.hashCode());

    final LocationCategory different = new LocationCategory();
    different.setFilter(new StringEquals());
    assertThat(category.hashCode()).isEqualTo(different.hashCode());
  }
}
