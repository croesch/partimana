package com.github.croesch.partimana.model.filter.cat.p;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.types.Equals;
import com.github.croesch.partimana.model.filter.types.GreaterThan;
import com.github.croesch.partimana.model.filter.types.LessThan;
import com.github.croesch.partimana.model.filter.types.NotEquals;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;

/**
 * Provides test cases for {@link LivingPostCodeCategory}.
 * 
 * @author croesch
 * @since Date: Oct 27, 2012
 */
public class LivingPostCodeCategoryTest {

  private LivingPostCodeCategory category;

  @org.junit.Before
  public void setUp() {
    this.category = new LivingPostCodeCategory();
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
    assertThat(this.category.getShortDescription()).isEqualTo(Text.FILTER_CAT_PARTICIPANT_LIVING_POSTAL_CODE.text());
    assertThat(this.category.getShortDescription()).isEqualTo(Text.POST_CODE.text() + " ("
                                                                      + Text.PARTICIPANT_ADDRESS_LIVING.text() + ")");
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
    assertThat(this.category.isMatchingFilter(p)).isFalse();

    final LessThan less = new LessThan();
    less.setFilterValue(5);
    this.category.setFilter(less);
    assertThat(this.category.isMatchingFilter(p)).isFalse();

    final GreaterThan greater = new GreaterThan();
    greater.setFilterValue(5);
    this.category.setFilter(greater);
    assertThat(this.category.isMatchingFilter(p)).isTrue();
  }
}