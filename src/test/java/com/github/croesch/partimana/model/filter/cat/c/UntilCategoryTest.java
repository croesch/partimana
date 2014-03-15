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
    this.category = new UntilCategory();
  }

  @Test
  public void testGetFilters() {
    assertThat(this.category.getFilterTypes())
        .containsOnly(new After(), new Before(), new DateEquals(), new DateNotEquals());

    final IFilterType<Date> filterType = this.category.getFilterTypes().get(0);
    filterType.setFilterValue(new Date(12345));

    assertThat(this.category.getFilterTypes().contains(filterType)).isFalse();
  }

  @Test
  public void testSetAndGetFilter() {
    assertThat(this.category.getFilter()).isNull();
    this.category.setFilter(new DateEquals());
    assertThat(this.category.getFilter()).isEqualTo(new DateEquals());
    this.category.setFilter(null);
    assertThat(this.category.getFilter()).isNull();
  }

  @Test
  public void testGetShortDescription() {
    assertThat(this.category.getShortDescription()).isEqualTo(Text.FILTER_CAT_CAMP_UNTIL.text());
    assertThat(this.category.getShortDescription()).isEqualTo(Text.CAMP_DATE_TO.text());
  }

  @Test
  public void testIsMatchingFilter() {
    assertThat(this.category.isMatchingFilter(null)).isFalse();

    final Camp c = new Camp("OFZ", new Date(5000000), new Date(10000000), "dort", "20");
    assertThat(this.category.isMatchingFilter(c)).isFalse();

    final After after = new After();
    after.setFilterValue(new Date(7000000));
    this.category.setFilter(after);
    assertThat(this.category.isMatchingFilter(c)).isTrue();

    final Before before = new Before();
    before.setFilterValue(new Date(7000000));
    this.category.setFilter(before);
    assertThat(this.category.isMatchingFilter(c)).isFalse();
  }

  @Test
  public void testEquals() {
    assertThat(this.category).isEqualTo(new UntilCategory());
    assertThat(this.category).isEqualTo(this.category);
    assertThat(this.category).isNotEqualTo(null);
    assertThat(this.category).isNotEqualTo(new ForeNameCategory());
    assertThat(this.category).isNotEqualTo("category");

    this.category.setFilter(new DateEquals());
    final UntilCategory other = new UntilCategory();
    assertThat(this.category).isNotEqualTo(other);
    assertThat(other).isNotEqualTo(this.category);
    other.setFilter(new DateEquals());
    assertThat(this.category).isEqualTo(other);

    final ForeNameCategory different = new ForeNameCategory();
    different.setFilter(new StringEquals());
    this.category.getFilter().setFilterValue(new Date());
    assertThat(this.category).isNotEqualTo(different);
    assertThat(different).isNotEqualTo(this.category);
  }

  @Test
  public void testHashCode() {
    assertThat(this.category.hashCode()).isEqualTo(new UntilCategory().hashCode());
    assertThat(this.category.hashCode()).isEqualTo(this.category.hashCode());
    assertThat(this.category.hashCode()).isEqualTo(new ForeNameCategory().hashCode());

    this.category.setFilter(new DateEquals());
    final UntilCategory other = new UntilCategory();
    other.setFilter(new DateEquals());
    assertThat(this.category.hashCode()).isEqualTo(other.hashCode());

    final ForeNameCategory different = new ForeNameCategory();
    different.setFilter(new StringEquals());
    assertThat(this.category.hashCode()).isEqualTo(different.hashCode());
  }
}
