package com.github.croesch.partimana.model.filter.cat.p;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.cat.c.LocationCategory;
import com.github.croesch.partimana.model.filter.types.*;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;
import java.util.Date;
import org.junit.Test;

/**
 * Provides test cases for {@link BirthdayCategory}.
 *
 * @author croesch
 * @since Date: Oct 27, 2012
 */
public class BirthdayCategoryTest {

  private BirthdayCategory category;

  @org.junit.Before
  public void setUp() {
    category = new BirthdayCategory();
  }

  @Test
  public void testGetFilters() {
    assertThat(category.getFilterTypes())
        .containsOnly(new After(), new Before(), new DateEquals(), new DateNotEquals());

    final IFilterType<Date> filterType = category.getFilterTypes().get(0);
    filterType.setFilterValue(new Date(12345));

    assertThat(category.getFilterTypes().contains(filterType)).isFalse();
  }

  @Test
  public void testSetAndGetFilter() {
    assertThat(category.getFilter()).isNull();
    category.setFilter(new DateEquals());
    assertThat(category.getFilter()).isEqualTo(new DateEquals());
    category.setFilter(null);
    assertThat(category.getFilter()).isNull();
  }

  @Test
  public void testGetShortDescription() {
    assertThat(category.getShortDescription()).isEqualTo(Text.FILTER_CAT_PARTICIPANT_BIRTHDAY.text());
    assertThat(category.getShortDescription()).isEqualTo(Text.PARTICIPANT_BIRTHDAY.text());
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
    p.setBank("Special bank");
    assertThat(category.isMatchingFilter(p)).isFalse();

    final After after = new After();
    after.setFilterValue(new Date(7000000));
    category.setFilter(after);
    assertThat(category.isMatchingFilter(p)).isTrue();

    final Before before = new Before();
    before.setFilterValue(new Date(7000000));
    category.setFilter(before);
    assertThat(category.isMatchingFilter(p)).isFalse();
  }

  @Test
  public void testEquals() {
    assertThat(category).isEqualTo(new BirthdayCategory());
    assertThat(category).isEqualTo(category);
    assertThat(category).isNotEqualTo(null);
    assertThat(category).isNotEqualTo(new LocationCategory());
    assertThat(category).isNotEqualTo("category");

    category.setFilter(new DateEquals());
    final BirthdayCategory other = new BirthdayCategory();
    assertThat(category).isNotEqualTo(other);
    assertThat(other).isNotEqualTo(category);
    other.setFilter(new DateEquals());
    assertThat(category).isEqualTo(other);

    final LocationCategory different = new LocationCategory();
    different.setFilter(new StringEquals());
    category.getFilter().setFilterValue(new Date());
    assertThat(category).isNotEqualTo(different);
    assertThat(different).isNotEqualTo(category);
  }

  @Test
  public void testHashCode() {
    assertThat(category.hashCode()).isEqualTo(new BirthdayCategory().hashCode());
    assertThat(category.hashCode()).isEqualTo(category.hashCode());
    assertThat(category.hashCode()).isEqualTo(new LocationCategory().hashCode());

    category.setFilter(new DateEquals());
    final BirthdayCategory other = new BirthdayCategory();
    other.setFilter(new DateEquals());
    assertThat(category.hashCode()).isEqualTo(other.hashCode());

    final LocationCategory different = new LocationCategory();
    different.setFilter(new StringEquals());
    assertThat(category.hashCode()).isEqualTo(different.hashCode());
  }
}
