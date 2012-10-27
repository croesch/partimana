package com.github.croesch.partimana.model.filter.cat.p;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.types.After;
import com.github.croesch.partimana.model.filter.types.Before;
import com.github.croesch.partimana.model.filter.types.Equals;
import com.github.croesch.partimana.model.filter.types.NotEquals;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;

/**
 * Provides test cases for {@link DateSinceCategory}.
 * 
 * @author croesch
 * @since Date: Oct 27, 2012
 */
public class DateSinceCategoryTest {

  private DateSinceCategory category;

  @org.junit.Before
  public void setUp() {
    this.category = new DateSinceCategory();
  }

  @Test
  public void testGetFilters() {
    assertThat(this.category.getFilterTypes()).containsOnly(new After(), new Before(), new Equals<Object>(),
                                                            new NotEquals<Object>());

    final IFilterType<Date> filterType = this.category.getFilterTypes().get(0);
    filterType.setFilterValue(new Date(12345));

    assertThat(this.category.getFilterTypes().contains(filterType)).isFalse();
  }

  @Test
  public void testSetAndGetFilter() {
    assertThat(this.category.getFilter()).isNull();
    this.category.setFilter(new Equals<Date>());
    assertThat(this.category.getFilter()).isEqualTo(new Equals<Date>());
    this.category.setFilter(null);
    assertThat(this.category.getFilter()).isNull();
  }

  @Test
  public void testGetShortDescription() {
    assertThat(this.category.getShortDescription()).isEqualTo(Text.FILTER_CAT_PARTICIPANT_DATE_SINCE.text());
    assertThat(this.category.getShortDescription()).isEqualTo(Text.PARTICIPANT_DATE_SINCE.text());
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
    p.setDateSinceInDataBase(new Date(800000000));
    assertThat(this.category.isMatchingFilter(p)).isFalse();

    final After after = new After();
    after.setFilterValue(new Date(7000000));
    this.category.setFilter(after);
    assertThat(this.category.isMatchingFilter(p)).isTrue();

    final Before before = new Before();
    before.setFilterValue(new Date(7000000));
    this.category.setFilter(before);
    assertThat(this.category.isMatchingFilter(p)).isFalse();
  }
}
