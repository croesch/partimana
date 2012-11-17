package com.github.croesch.partimana.model.filter.cat.p;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.cat.c.LocationCategory;
import com.github.croesch.partimana.model.filter.types.BooleanEquals;
import com.github.croesch.partimana.model.filter.types.BooleanNotEquals;
import com.github.croesch.partimana.model.filter.types.StringEquals;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;

/**
 * Provides test cases for {@link CanBeAGECategory}.
 * 
 * @author croesch
 * @since Date: Oct 27, 2012
 */
public class CanBeAGECategoryTest {

  private CanBeAGECategory category;

  @org.junit.Before
  public void setUp() {
    this.category = new CanBeAGECategory();
  }

  @Test
  public void testGetFilters() {
    assertThat(this.category.getFilterTypes()).containsOnly(new BooleanEquals(), new BooleanNotEquals());

    final IFilterType<Boolean> filterType = this.category.getFilterTypes().get(0);
    filterType.setFilterValue(false);

    assertThat(this.category.getFilterTypes().contains(filterType)).isFalse();
  }

  @Test
  public void testSetAndGetFilter() {
    assertThat(this.category.getFilter()).isNull();
    this.category.setFilter(new BooleanEquals());
    assertThat(this.category.getFilter()).isEqualTo(new BooleanEquals());
    this.category.setFilter(null);
    assertThat(this.category.getFilter()).isNull();
  }

  @Test
  public void testGetShortDescription() {
    assertThat(this.category.getShortDescription()).isEqualTo(Text.FILTER_CAT_PARTICIPANT_CAN_BE_AGE.text());
    assertThat(this.category.getShortDescription()).isEqualTo(Text.PARTICIPANT_AGE.text());
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
    p.setPossibleAGE(true);
    assertThat(this.category.isMatchingFilter(p)).isFalse();

    final BooleanEquals equals = new BooleanEquals();
    equals.setFilterValue(true);
    this.category.setFilter(equals);
    assertThat(this.category.isMatchingFilter(p)).isTrue();

    final BooleanNotEquals notEquals = new BooleanNotEquals();
    notEquals.setFilterValue(true);
    this.category.setFilter(notEquals);
    assertThat(this.category.isMatchingFilter(p)).isFalse();
  }

  @Test
  public void testEquals() {
    assertThat(this.category).isEqualTo(new CanBeAGECategory());
    assertThat(this.category).isEqualTo(this.category);
    assertThat(this.category).isNotEqualTo(null);
    assertThat(this.category).isNotEqualTo(new LocationCategory());
    assertThat(this.category).isNotEqualTo("category");

    this.category.setFilter(new BooleanEquals());
    final CanBeAGECategory other = new CanBeAGECategory();
    assertThat(this.category).isNotEqualTo(other);
    assertThat(other).isNotEqualTo(this.category);
    other.setFilter(new BooleanEquals());
    assertThat(this.category).isEqualTo(other);

    final LocationCategory different = new LocationCategory();
    different.setFilter(new StringEquals());
    this.category.getFilter().setFilterValue(false);
    assertThat(this.category).isNotEqualTo(different);
    assertThat(different).isNotEqualTo(this.category);
  }

  @Test
  public void testHashCode() {
    assertThat(this.category.hashCode()).isEqualTo(new CanBeAGECategory().hashCode());
    assertThat(this.category.hashCode()).isEqualTo(this.category.hashCode());
    assertThat(this.category.hashCode()).isEqualTo(new LocationCategory().hashCode());

    this.category.setFilter(new BooleanEquals());
    final CanBeAGECategory other = new CanBeAGECategory();
    other.setFilter(new BooleanEquals());
    assertThat(this.category.hashCode()).isEqualTo(other.hashCode());

    final LocationCategory different = new LocationCategory();
    different.setFilter(new StringEquals());
    assertThat(this.category.hashCode()).isEqualTo(different.hashCode());
  }
}
