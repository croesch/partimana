package com.github.croesch.partimana.model.filter.cat.p;

import static org.fest.assertions.Assertions.assertThat;

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
 * Provides test cases for {@link PostalCityCategory}.
 *
 * @author croesch
 * @since Date: Oct 27, 2012
 */
public class PostalCityCategoryTest {

  private PostalCityCategory category;

  @Before
  public void setUp() {
    this.category = new PostalCityCategory();
  }

  @Test
  public void testGetFilters() {
    assertThat(this.category.getFilterTypes()).containsOnly(new StringEquals(),
                                                            new StringNotEquals(),
                                                            new StartsWith(),
                                                            new EndsWith(),
                                                            new EqualsIgnoreCase(),
                                                            new NotEqualsIgnoreCase(),
                                                            new Contains());

    final IFilterType<String> filterType = this.category.getFilterTypes().get(0);
    filterType.setFilterValue("12345");

    assertThat(this.category.getFilterTypes().contains(filterType)).isFalse();
  }

  @Test
  public void testSetAndGetFilter() {
    assertThat(this.category.getFilter()).isNull();
    this.category.setFilter(new StartsWith());
    assertThat(this.category.getFilter()).isEqualTo(new StartsWith());
    this.category.setFilter(null);
    assertThat(this.category.getFilter()).isNull();
  }

  @Test
  public void testGetShortDescription() {
    assertThat(this.category.getShortDescription()).isEqualTo(Text.FILTER_CAT_PARTICIPANT_POSTAL_CITY.text());
    assertThat(this.category.getShortDescription())
        .isEqualTo(Text.CITY.text() + " (" + Text.PARTICIPANT_ADDRESS_POSTAL.text() + ")");
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
    p.setCityPostal("CITY");
    assertThat(this.category.isMatchingFilter(p)).isFalse();

    final StartsWith startsWith = new StartsWith();
    startsWith.setFilterValue("CITY");
    this.category.setFilter(startsWith);
    assertThat(this.category.isMatchingFilter(p)).isTrue();

    final EndsWith endsWith = new EndsWith();
    endsWith.setFilterValue("city");
    this.category.setFilter(endsWith);
    assertThat(this.category.isMatchingFilter(p)).isFalse();
  }

  @Test
  public void testEquals() {
    assertThat(this.category).isEqualTo(new PostalCityCategory());
    assertThat(this.category).isEqualTo(this.category);
    assertThat(this.category).isNotEqualTo(null);
    assertThat(this.category).isNotEqualTo(new LocationCategory());
    assertThat(this.category).isNotEqualTo("category");

    this.category.setFilter(new StringEquals());
    final PostalCityCategory other = new PostalCityCategory();
    assertThat(this.category).isNotEqualTo(other);
    assertThat(other).isNotEqualTo(this.category);
    other.setFilter(new StringEquals());
    assertThat(this.category).isEqualTo(other);

    final LocationCategory different = new LocationCategory();
    different.setFilter(new StringEquals());
    this.category.getFilter().setFilterValue("juppi <- :)");
    assertThat(this.category).isNotEqualTo(different);
    assertThat(different).isNotEqualTo(this.category);
  }

  @Test
  public void testHashCode() {
    assertThat(this.category.hashCode()).isEqualTo(new PostalCityCategory().hashCode());
    assertThat(this.category.hashCode()).isEqualTo(this.category.hashCode());
    assertThat(this.category.hashCode()).isEqualTo(new LocationCategory().hashCode());

    this.category.setFilter(new StringEquals());
    final PostalCityCategory other = new PostalCityCategory();
    other.setFilter(new StringEquals());
    assertThat(this.category.hashCode()).isEqualTo(other.hashCode());

    final LocationCategory different = new LocationCategory();
    different.setFilter(new StringEquals());
    assertThat(this.category.hashCode()).isEqualTo(different.hashCode());
  }
}
