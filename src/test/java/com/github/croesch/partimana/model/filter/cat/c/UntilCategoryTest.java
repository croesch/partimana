package com.github.croesch.partimana.model.filter.cat.c;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.cat.p.ForeNameCategory;
import com.github.croesch.partimana.model.filter.types.*;
import com.github.croesch.partimana.types.Camp;
import java.util.Date;
import org.junit.Test;

/**
 * Provides test cases for {@link UntilCategory}.
 *
 * @author croesch
 * @since Date: Oct 27, 2012
 */
public class UntilCategoryTest {

  private UntilCategory category;

  @org.junit.Before
  public void setUp() {
    category = new UntilCategory();
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
    assertThat(category.getShortDescription()).isEqualTo(Text.FILTER_CAT_CAMP_UNTIL.text());
    assertThat(category.getShortDescription()).isEqualTo(Text.CAMP_DATE_TO.text());
  }

  @Test
  public void testIsMatchingFilter() {
    assertThat(category.isMatchingFilter(null)).isFalse();

    final Camp c = new Camp("OFZ", new Date(5000000), new Date(10000000), "dort", "20");
    assertThat(category.isMatchingFilter(c)).isFalse();

    final After after = new After();
    after.setFilterValue(new Date(7000000));
    category.setFilter(after);
    assertThat(category.isMatchingFilter(c)).isTrue();

    final Before before = new Before();
    before.setFilterValue(new Date(7000000));
    category.setFilter(before);
    assertThat(category.isMatchingFilter(c)).isFalse();
  }

  @Test
  public void testEquals() {
    assertThat(category).isEqualTo(new UntilCategory());
    assertThat(category).isEqualTo(category);
    assertThat(category).isNotEqualTo(null);
    assertThat(category).isNotEqualTo(new ForeNameCategory());
    assertThat(category).isNotEqualTo("category");

    category.setFilter(new DateEquals());
    final UntilCategory other = new UntilCategory();
    assertThat(category).isNotEqualTo(other);
    assertThat(other).isNotEqualTo(category);
    other.setFilter(new DateEquals());
    assertThat(category).isEqualTo(other);

    final ForeNameCategory different = new ForeNameCategory();
    different.setFilter(new StringEquals());
    category.getFilter().setFilterValue(new Date());
    assertThat(category).isNotEqualTo(different);
    assertThat(different).isNotEqualTo(category);
  }

  @Test
  public void testHashCode() {
    assertThat(category.hashCode()).isEqualTo(new UntilCategory().hashCode());
    assertThat(category.hashCode()).isEqualTo(category.hashCode());
    assertThat(category.hashCode()).isEqualTo(new ForeNameCategory().hashCode());

    category.setFilter(new DateEquals());
    final UntilCategory other = new UntilCategory();
    other.setFilter(new DateEquals());
    assertThat(category.hashCode()).isEqualTo(other.hashCode());

    final ForeNameCategory different = new ForeNameCategory();
    different.setFilter(new StringEquals());
    assertThat(category.hashCode()).isEqualTo(different.hashCode());
  }
}
