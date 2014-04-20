package com.github.croesch.partimana.model.filter.cat.c;

import static org.assertj.core.api.Assertions.assertThat;

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
    category = new LocationCategory();
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
    assertThat(category.getShortDescription()).isEqualTo(Text.FILTER_CAT_CAMP_LOCATION.text());
    assertThat(category.getShortDescription()).isEqualTo(Text.CAMP_LOCATION.text());
  }

  @Test
  public void testIsMatchingFilter() {
    assertThat(category.isMatchingFilter(null)).isFalse();

    final Camp c = new Camp("OFZ", new Date(5000000), new Date(10000000), "dort", "20");
    assertThat(category.isMatchingFilter(c)).isFalse();

    final StartsWith startsWith = new StartsWith();
    startsWith.setFilterValue("do");
    category.setFilter(startsWith);
    assertThat(category.isMatchingFilter(c)).isTrue();

    final EndsWith endsWith = new EndsWith();
    endsWith.setFilterValue("do");
    category.setFilter(endsWith);
    assertThat(category.isMatchingFilter(c)).isFalse();
  }

  @Test
  public void testEquals() {
    assertThat(category).isEqualTo(new LocationCategory());
    assertThat(category).isEqualTo(category);
    assertThat(category).isNotEqualTo(null);
    assertThat(category).isNotEqualTo(new FromCategory());
    assertThat(category).isNotEqualTo("category");

    category.setFilter(new StringEquals());
    final LocationCategory other = new LocationCategory();
    assertThat(category).isNotEqualTo(other);
    assertThat(other).isNotEqualTo(category);
    other.setFilter(new StringEquals());
    assertThat(category).isEqualTo(other);

    final FromCategory different = new FromCategory();
    different.setFilter(new DateEquals());
    category.getFilter().setFilterValue("12");
    assertThat(category).isNotEqualTo(different);
    assertThat(different).isNotEqualTo(category);
  }

  @Test
  public void testHashCode() {
    assertThat(category.hashCode()).isEqualTo(new LocationCategory().hashCode());
    assertThat(category.hashCode()).isEqualTo(category.hashCode());
    assertThat(category.hashCode()).isEqualTo(new FromCategory().hashCode());

    category.setFilter(new StringEquals());
    final LocationCategory other = new LocationCategory();
    other.setFilter(new StringEquals());
    assertThat(category.hashCode()).isEqualTo(other.hashCode());

    final FromCategory different = new FromCategory();
    different.setFilter(new DateEquals());
    assertThat(category.hashCode()).isEqualTo(different.hashCode());
  }
}
