package com.github.croesch.partimana.model.filter.cat.p;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.cat.c.LocationCategory;
import com.github.croesch.partimana.model.filter.types.Equals;
import com.github.croesch.partimana.model.filter.types.NotEquals;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;

/**
 * Provides test cases for {@link DenominationCategory}.
 * 
 * @author croesch
 * @since Date: Oct 27, 2012
 */
public class DenominationCategoryTest {

  private DenominationCategory category;

  @org.junit.Before
  public void setUp() {
    this.category = new DenominationCategory();
  }

  @Test
  public void testGetFilters() {
    assertThat(this.category.getFilterTypes()).containsOnly(new Equals<Object>(), new NotEquals<Object>());

    final IFilterType<Denomination> filterType = this.category.getFilterTypes().get(0);
    filterType.setFilterValue(Denomination.OTHER);

    assertThat(this.category.getFilterTypes().contains(filterType)).isFalse();
  }

  @Test
  public void testSetAndGetFilter() {
    assertThat(this.category.getFilter()).isNull();
    this.category.setFilter(new Equals<Denomination>());
    assertThat(this.category.getFilter()).isEqualTo(new Equals<Denomination>());
    this.category.setFilter(null);
    assertThat(this.category.getFilter()).isNull();
  }

  @Test
  public void testGetShortDescription() {
    assertThat(this.category.getShortDescription()).isEqualTo(Text.FILTER_CAT_PARTICIPANT_DENOMINATION.text());
    assertThat(this.category.getShortDescription()).isEqualTo(Text.PARTICIPANT_DENOMINTAION.text());
  }

  @Test
  public void testIsMatchingFilter() {
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
    assertThat(this.category.isMatchingFilter(p)).isFalse();

    final Equals<Denomination> equals = new Equals<Denomination>();
    equals.setFilterValue(Denomination.EVANGELIC);
    this.category.setFilter(equals);
    assertThat(this.category.isMatchingFilter(p)).isFalse();

    final NotEquals<Denomination> notEquals = new NotEquals<Denomination>();
    notEquals.setFilterValue(Denomination.EVANGELIC);
    this.category.setFilter(notEquals);
    assertThat(this.category.isMatchingFilter(p)).isTrue();
  }

  @Test
  public void testEquals() {
    assertThat(this.category).isEqualTo(new DenominationCategory());
    assertThat(this.category).isEqualTo(this.category);
    assertThat(this.category).isNotEqualTo(null);
    assertThat(this.category).isEqualTo(new LocationCategory());
    assertThat(this.category).isNotEqualTo("category");

    this.category.setFilter(new Equals<Denomination>());
    final DenominationCategory other = new DenominationCategory();
    assertThat(this.category).isNotEqualTo(other);
    assertThat(other).isNotEqualTo(this.category);
    other.setFilter(new Equals<Denomination>());
    assertThat(this.category).isEqualTo(other);

    final LocationCategory different = new LocationCategory();
    different.setFilter(new Equals<String>());
    assertThat(this.category).isEqualTo(different);
    this.category.getFilter().setFilterValue(Denomination.MUSLIM);
    assertThat(this.category).isNotEqualTo(different);
    assertThat(different).isNotEqualTo(this.category);
  }

  @Test
  public void testHashCode() {
    assertThat(this.category.hashCode()).isEqualTo(new DenominationCategory().hashCode());
    assertThat(this.category.hashCode()).isEqualTo(this.category.hashCode());
    assertThat(this.category.hashCode()).isEqualTo(new LocationCategory().hashCode());

    this.category.setFilter(new Equals<Denomination>());
    final DenominationCategory other = new DenominationCategory();
    other.setFilter(new Equals<Denomination>());
    assertThat(this.category.hashCode()).isEqualTo(other.hashCode());

    final LocationCategory different = new LocationCategory();
    different.setFilter(new Equals<String>());
    assertThat(this.category.hashCode()).isEqualTo(different.hashCode());
  }
}
