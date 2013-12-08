package com.github.croesch.partimana.model.filter.cat.c;

import static org.fest.assertions.Assertions.assertThat;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.cat.p.ForeNameCategory;
import com.github.croesch.partimana.model.filter.types.*;
import com.github.croesch.partimana.types.Camp;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

/**
 * Provides test cases for {@link NameCategory}.
 *
 * @author croesch
 * @since Date: Oct 27, 2012
 */
public class NameCategoryTest {

  private NameCategory category;

  @Before
  public void setUp() {
    this.category = new NameCategory();
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
    assertThat(this.category.getShortDescription()).isEqualTo(Text.FILTER_CAT_CAMP_NAME.text());
    assertThat(this.category.getShortDescription()).isEqualTo(Text.CAMP_NAME.text());
  }

  @Test
  public void testIsMatchingFilter() {
    assertThat(this.category.isMatchingFilter(null)).isFalse();

    final Camp c = new Camp("OFZ", new Date(5000000), new Date(10000000), "dort", "20");
    assertThat(this.category.isMatchingFilter(c)).isFalse();

    final Contains contains = new Contains();
    contains.setFilterValue("FZ");
    this.category.setFilter(contains);
    assertThat(this.category.isMatchingFilter(c)).isTrue();

    final EndsWith endsWith = new EndsWith();
    endsWith.setFilterValue("Fz");
    this.category.setFilter(endsWith);
    assertThat(this.category.isMatchingFilter(c)).isFalse();
  }

  @Test
  public void testEquals() {
    assertThat(this.category).isEqualTo(new NameCategory());
    assertThat(this.category).isEqualTo(this.category);
    assertThat(this.category).isNotEqualTo(null);
    assertThat(this.category).isNotEqualTo(new ForeNameCategory());
    assertThat(this.category).isNotEqualTo("category");

    this.category.setFilter(new StringEquals());
    final NameCategory other = new NameCategory();
    assertThat(this.category).isNotEqualTo(other);
    assertThat(other).isNotEqualTo(this.category);
    other.setFilter(new StringEquals());
    assertThat(this.category).isEqualTo(other);

    final ForeNameCategory different = new ForeNameCategory();
    different.setFilter(new StringEquals());
    this.category.getFilter().setFilterValue("12");
    assertThat(this.category).isNotEqualTo(different);
    assertThat(different).isNotEqualTo(this.category);
  }

  @Test
  public void testHashCode() {
    assertThat(this.category.hashCode()).isEqualTo(new NameCategory().hashCode());
    assertThat(this.category.hashCode()).isEqualTo(this.category.hashCode());
    assertThat(this.category.hashCode()).isEqualTo(new ForeNameCategory().hashCode());

    this.category.setFilter(new StringEquals());
    final NameCategory other = new NameCategory();
    other.setFilter(new StringEquals());
    assertThat(this.category.hashCode()).isEqualTo(other.hashCode());

    final ForeNameCategory different = new ForeNameCategory();
    different.setFilter(new StringEquals());
    assertThat(this.category.hashCode()).isEqualTo(different.hashCode());
  }
}
