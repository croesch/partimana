package com.github.croesch.partimana.model.filter.cat.p;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.types.Equals;
import com.github.croesch.partimana.model.filter.types.NotEquals;
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
    assertThat(this.category.getFilterTypes()).containsOnly(new Equals<Object>(), new NotEquals<Object>());

    final IFilterType<Boolean> filterType = this.category.getFilterTypes().get(0);
    filterType.setFilterValue(false);

    assertThat(this.category.getFilterTypes().contains(filterType)).isFalse();
  }

  @Test
  public void testSetAndGetFilter() {
    assertThat(this.category.getFilter()).isNull();
    this.category.setFilter(new Equals<Boolean>());
    assertThat(this.category.getFilter()).isEqualTo(new Equals<Boolean>());
    this.category.setFilter(null);
    assertThat(this.category.getFilter()).isNull();
  }

  @Test
  public void testGetShortDescription() {
    assertThat(this.category.getShortDescription()).isEqualTo(Text.FILTER_CAT_PARTICIPANT_CAN_BE_AGE.text());
    assertThat(this.category.getShortDescription()).isEqualTo(Text.PARTICIPANT_AGE.text());
  }

  @Test
  public void isMatchingFilter() {
    assertThat(this.category.isMatchingFilter(null)).isFalse();

    final Participant p = new Participant("Maxi",
                                          "Musterfrau",
                                          Gender.FEMALE,
                                          Denomination.CATHOLIC,
                                          new Date(100000000),
                                          "street",
                                          12,
                                          "city",
                                          CountyCouncil.COUNTY_RHEIN_PFALZ);
    p.setPossibleAGE(true);
    assertThat(this.category.isMatchingFilter(p)).isFalse();

    final Equals<Boolean> equals = new Equals<Boolean>();
    equals.setFilterValue(true);
    this.category.setFilter(equals);
    assertThat(this.category.isMatchingFilter(p)).isTrue();

    final NotEquals<Boolean> notEquals = new NotEquals<Boolean>();
    notEquals.setFilterValue(true);
    this.category.setFilter(notEquals);
    assertThat(this.category.isMatchingFilter(p)).isFalse();
  }
}
