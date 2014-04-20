package com.github.croesch.partimana.model.filter.cat.p;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.cat.c.LocationCategory;
import com.github.croesch.partimana.model.filter.types.DenominationEquals;
import com.github.croesch.partimana.model.filter.types.DenominationNotEquals;
import com.github.croesch.partimana.model.filter.types.StringEquals;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;
import java.util.Date;
import org.junit.Test;

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
    category = new DenominationCategory();
  }

  @Test
  public void testGetFilters() {
    assertThat(category.getFilterTypes()).containsOnly(new DenominationEquals(), new DenominationNotEquals());

    final IFilterType<Denomination> filterType = category.getFilterTypes().get(0);
    filterType.setFilterValue(Denomination.OTHER);

    assertThat(category.getFilterTypes().contains(filterType)).isFalse();
  }

  @Test
  public void testSetAndGetFilter() {
    assertThat(category.getFilter()).isNull();
    category.setFilter(new DenominationEquals());
    assertThat(category.getFilter()).isEqualTo(new DenominationEquals());
    category.setFilter(null);
    assertThat(category.getFilter()).isNull();
  }

  @Test
  public void testGetShortDescription() {
    assertThat(category.getShortDescription()).isEqualTo(Text.FILTER_CAT_PARTICIPANT_DENOMINATION.text());
    assertThat(category.getShortDescription()).isEqualTo(Text.PARTICIPANT_DENOMINATION.text());
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

    final DenominationEquals equals = new DenominationEquals();
    equals.setFilterValue(Denomination.EVANGELIC);
    category.setFilter(equals);
    assertThat(category.isMatchingFilter(p)).isFalse();

    final DenominationNotEquals notEquals = new DenominationNotEquals();
    notEquals.setFilterValue(Denomination.EVANGELIC);
    category.setFilter(notEquals);
    assertThat(category.isMatchingFilter(p)).isTrue();
  }

  @Test
  public void testEquals() {
    assertThat(category).isEqualTo(new DenominationCategory());
    assertThat(category).isEqualTo(category);
    assertThat(category).isNotEqualTo(null);
    assertThat(category).isNotEqualTo(new LocationCategory());
    assertThat(category).isNotEqualTo("category");

    category.setFilter(new DenominationEquals());
    final DenominationCategory other = new DenominationCategory();
    assertThat(category).isNotEqualTo(other);
    assertThat(other).isNotEqualTo(category);
    other.setFilter(new DenominationEquals());
    assertThat(category).isEqualTo(other);

    final LocationCategory different = new LocationCategory();
    different.setFilter(new StringEquals());
    category.getFilter().setFilterValue(Denomination.MUSLIM);
    assertThat(category).isNotEqualTo(different);
    assertThat(different).isNotEqualTo(category);
  }

  @Test
  public void testHashCode() {
    assertThat(category.hashCode()).isEqualTo(new DenominationCategory().hashCode());
    assertThat(category.hashCode()).isEqualTo(category.hashCode());
    assertThat(category.hashCode()).isEqualTo(new LocationCategory().hashCode());

    category.setFilter(new DenominationEquals());
    final DenominationCategory other = new DenominationCategory();
    other.setFilter(new DenominationEquals());
    assertThat(category.hashCode()).isEqualTo(other.hashCode());

    final LocationCategory different = new LocationCategory();
    different.setFilter(new StringEquals());
    assertThat(category.hashCode()).isEqualTo(different.hashCode());
  }
}
