package com.github.croesch.partimana.model.filter.cat.c;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.types.After;
import com.github.croesch.partimana.model.filter.types.Before;
import com.github.croesch.partimana.model.filter.types.Equals;
import com.github.croesch.partimana.model.filter.types.NotEquals;
import com.github.croesch.partimana.types.Camp;

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
    assertThat(this.category.getFilterTypes()).containsOnly(new After(), new Before(), new Equals<Object>(),
                                                            new NotEquals<Object>());

    final IFilterType<Date> filterType = this.category.getFilterTypes().get(0);
    filterType.setFilterValue(new Date(12345));

    assertThat(this.category.getFilterTypes().contains(filterType)).isFalse();
  }

  @Test
  public void testSetAndGetFilter() {
    assertThat(this.category.getFilter()).isNull();
    this.category.setFilter(new Equals<Date>());
    assertThat(this.category.getFilter()).isEqualTo(new Equals<Date>());
    this.category.setFilter(null);
    assertThat(this.category.getFilter()).isNull();
  }

  @Test
  public void testGetShortDescription() {
    assertThat(this.category.getShortDescription()).isEqualTo(Text.FILTER_CAT_CAMP_UNTIL.text());
  }

  @Test
  public void isMatchingFilter() {
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
}
