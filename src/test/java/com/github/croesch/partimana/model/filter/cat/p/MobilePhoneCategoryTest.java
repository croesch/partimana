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
import org.junit.Before;
import org.junit.Test;

/**
 * Provides test cases for {@link MobilePhoneCategory}.
 *
 * @author croesch
 * @since Date: Oct 27, 2012
 */
public class MobilePhoneCategoryTest {

  private MobilePhoneCategory category;

  @Before
  public void setUp() {
    category = new MobilePhoneCategory();
  }

  @Test
  public void testGetFilters() {
    assertThat(category.getFilterTypes()).containsOnly(new StringEquals(),
                                                       new StringNotEquals(),
                                                       new StartsWith(),
                                                       new EndsWith(),
                                                       new EqualsIgnoreCase(),
                                                       new NotEqualsIgnoreCase(),
                                                       new Contains());

    final IFilterType<String> filterType = category.getFilterTypes().get(0);
    filterType.setFilterValue("12345");

    assertThat(category.getFilterTypes().contains(filterType)).isFalse();
  }

  @Test
  public void testSetAndGetFilter() {
    assertThat(category.getFilter()).isNull();
    category.setFilter(new StartsWith());
    assertThat(category.getFilter()).isEqualTo(new StartsWith());
    category.setFilter(null);
    assertThat(category.getFilter()).isNull();
  }

  @Test
  public void testGetShortDescription() {
    assertThat(category.getShortDescription()).isEqualTo(Text.FILTER_CAT_PARTICIPANT_MOBILE_PHONE.text());
    assertThat(category.getShortDescription()).isEqualTo(Text.PARTICIPANT_MOBILE_PHONE.text());
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
    p.setMobilePhone("Special thing");
    assertThat(category.isMatchingFilter(p)).isFalse();

    final StartsWith startsWith = new StartsWith();
    startsWith.setFilterValue("Spec");
    category.setFilter(startsWith);
    assertThat(category.isMatchingFilter(p)).isTrue();

    final EndsWith endsWith = new EndsWith();
    endsWith.setFilterValue("Spec");
    category.setFilter(endsWith);
    assertThat(category.isMatchingFilter(p)).isFalse();
  }

  @Test
  public void testEquals() {
    assertThat(category).isEqualTo(new MobilePhoneCategory());
    assertThat(category).isEqualTo(category);
    assertThat(category).isNotEqualTo(null);
    assertThat(category).isNotEqualTo(new LocationCategory());
    assertThat(category).isNotEqualTo("category");

    category.setFilter(new StringEquals());
    final MobilePhoneCategory other = new MobilePhoneCategory();
    assertThat(category).isNotEqualTo(other);
    assertThat(other).isNotEqualTo(category);
    other.setFilter(new StringEquals());
    assertThat(category).isEqualTo(other);

    final LocationCategory different = new LocationCategory();
    different.setFilter(new StringEquals());
    category.getFilter().setFilterValue("juppi <- :)");
    assertThat(category).isNotEqualTo(different);
    assertThat(different).isNotEqualTo(category);
  }

  @Test
  public void testHashCode() {
    assertThat(category.hashCode()).isEqualTo(new MobilePhoneCategory().hashCode());
    assertThat(category.hashCode()).isEqualTo(category.hashCode());
    assertThat(category.hashCode()).isEqualTo(new LocationCategory().hashCode());

    category.setFilter(new StringEquals());
    final MobilePhoneCategory other = new MobilePhoneCategory();
    other.setFilter(new StringEquals());
    assertThat(category.hashCode()).isEqualTo(other.hashCode());

    final LocationCategory different = new LocationCategory();
    different.setFilter(new StringEquals());
    assertThat(category.hashCode()).isEqualTo(different.hashCode());
  }
}
