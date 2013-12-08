package com.github.croesch.partimana.model.filter.cat.c;

import static org.fest.assertions.Assertions.assertThat;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.types.*;
import com.github.croesch.partimana.types.Camp;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

/**
 * Provides test cases for {@link LocationCategory}.
 *
 * @author croesch
 * @since Date: Oct 27, 2012
 */
public class LocationCategoryTest {

  private LocationCategory category;

  @Before
  public void setUp() {
    this.category = new LocationCategory();
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
    assertThat(this.category.getShortDescription()).isEqualTo(Text.FILTER_CAT_CAMP_LOCATION.text());
    assertThat(this.category.getShortDescription()).isEqualTo(Text.CAMP_LOCATION.text());
  }

  @Test
  public void testIsMatchingFilter() {
    assertThat(this.category.isMatchingFilter(null)).isFalse();

    final Camp c = new Camp("OFZ", new Date(5000000), new Date(10000000), "dort", "20");
    assertThat(this.category.isMatchingFilter(c)).isFalse();

    final StartsWith startsWith = new StartsWith();
    startsWith.setFilterValue("do");
    this.category.setFilter(startsWith);
    assertThat(this.category.isMatchingFilter(c)).isTrue();

    final EndsWith endsWith = new EndsWith();
    endsWith.setFilterValue("do");
    this.category.setFilter(endsWith);
    assertThat(this.category.isMatchingFilter(c)).isFalse();
  }

  @Test
  public void testEquals() {
    assertThat(this.category).isEqualTo(new LocationCategory());
    assertThat(this.category).isEqualTo(this.category);
    assertThat(this.category).isNotEqualTo(null);
    assertThat(this.category).isNotEqualTo(new FromCategory());
    assertThat(this.category).isNotEqualTo("category");

    this.category.setFilter(new StringEquals());
    final LocationCategory other = new LocationCategory();
    assertThat(this.category).isNotEqualTo(other);
    assertThat(other).isNotEqualTo(this.category);
    other.setFilter(new StringEquals());
    assertThat(this.category).isEqualTo(other);

    final FromCategory different = new FromCategory();
    different.setFilter(new DateEquals());
    this.category.getFilter().setFilterValue("12");
    assertThat(this.category).isNotEqualTo(different);
    assertThat(different).isNotEqualTo(this.category);
  }

  @Test
  public void testHashCode() {
    assertThat(this.category.hashCode()).isEqualTo(new LocationCategory().hashCode());
    assertThat(this.category.hashCode()).isEqualTo(this.category.hashCode());
    assertThat(this.category.hashCode()).isEqualTo(new FromCategory().hashCode());

    this.category.setFilter(new StringEquals());
    final LocationCategory other = new LocationCategory();
    other.setFilter(new StringEquals());
    assertThat(this.category.hashCode()).isEqualTo(other.hashCode());

    final FromCategory different = new FromCategory();
    different.setFilter(new DateEquals());
    assertThat(this.category.hashCode()).isEqualTo(different.hashCode());
  }
}
