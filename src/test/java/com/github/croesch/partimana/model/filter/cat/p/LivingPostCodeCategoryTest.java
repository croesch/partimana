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
 * Provides test cases for {@link LivingPostCodeCategory}.
 *
 * @author croesch
 * @since Date: Oct 27, 2012
 */
public class LivingPostCodeCategoryTest {

  private LivingPostCodeCategory category;

  @org.junit.Before
  public void setUp() {
    category = new LivingPostCodeCategory();
  }

  @Test
  public void testGetFilters() {
    assertThat(category.getFilterTypes())
        .containsOnly(new IntegerEquals(), new IntegerNotEquals(), new LessThan(), new GreaterThan());

    final IFilterType<Integer> filterType = category.getFilterTypes().get(0);
    filterType.setFilterValue(12345);

    assertThat(category.getFilterTypes().contains(filterType)).isFalse();
  }

  @Test
  public void testSetAndGetFilter() {
    assertThat(category.getFilter()).isNull();
    category.setFilter(new IntegerEquals());
    assertThat(category.getFilter()).isEqualTo(new IntegerEquals());
    category.setFilter(null);
    assertThat(category.getFilter()).isNull();
  }

  @Test
  public void testGetShortDescription() {
    assertThat(category.getShortDescription()).isEqualTo(Text.FILTER_CAT_PARTICIPANT_LIVING_POSTAL_CODE.text());
    assertThat(category.getShortDescription())
        .isEqualTo(Text.POST_CODE.text() + " (" + Text.PARTICIPANT_ADDRESS_LIVING.text() + ")");
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

    final LessThan less = new LessThan();
    less.setFilterValue(5);
    category.setFilter(less);
    assertThat(category.isMatchingFilter(p)).isFalse();

    final GreaterThan greater = new GreaterThan();
    greater.setFilterValue(5);
    category.setFilter(greater);
    assertThat(category.isMatchingFilter(p)).isTrue();
  }

  @Test
  public void testEquals() {
    assertThat(category).isEqualTo(new LivingPostCodeCategory());
    assertThat(category).isEqualTo(category);
    assertThat(category).isNotEqualTo(null);
    assertThat(category).isNotEqualTo(new LocationCategory());
    assertThat(category).isNotEqualTo("category");

    category.setFilter(new IntegerEquals());
    final LivingPostCodeCategory other = new LivingPostCodeCategory();
    assertThat(category).isNotEqualTo(other);
    assertThat(other).isNotEqualTo(category);
    other.setFilter(new IntegerEquals());
    assertThat(category).isEqualTo(other);

    final LocationCategory different = new LocationCategory();
    different.setFilter(new StringEquals());
    category.getFilter().setFilterValue(42);
    assertThat(category).isNotEqualTo(different);
    assertThat(different).isNotEqualTo(category);
  }

  @Test
  public void testHashCode() {
    assertThat(category.hashCode()).isEqualTo(new LivingPostCodeCategory().hashCode());
    assertThat(category.hashCode()).isEqualTo(category.hashCode());
    assertThat(category.hashCode()).isEqualTo(new LocationCategory().hashCode());

    category.setFilter(new IntegerEquals());
    final LivingPostCodeCategory other = new LivingPostCodeCategory();
    other.setFilter(new IntegerEquals());
    assertThat(category.hashCode()).isEqualTo(other.hashCode());

    final LocationCategory different = new LocationCategory();
    different.setFilter(new StringEquals());
    assertThat(category.hashCode()).isEqualTo(different.hashCode());
  }
}
