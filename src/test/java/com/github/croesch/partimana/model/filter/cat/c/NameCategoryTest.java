package com.github.croesch.partimana.model.filter.cat.c;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.types.Contains;
import com.github.croesch.partimana.model.filter.types.EndsWith;
import com.github.croesch.partimana.model.filter.types.Equals;
import com.github.croesch.partimana.model.filter.types.EqualsIgnoreCase;
import com.github.croesch.partimana.model.filter.types.NotEquals;
import com.github.croesch.partimana.model.filter.types.NotEqualsIgnoreCase;
import com.github.croesch.partimana.model.filter.types.StartsWith;
import com.github.croesch.partimana.types.Camp;

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
    assertThat(this.category.getFilterTypes()).containsOnly(new Equals<Object>(), new NotEquals<Object>(),
                                                            new StartsWith(), new EndsWith(), new EqualsIgnoreCase(),
                                                            new NotEqualsIgnoreCase(), new Contains());

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
  public void isMatchingFilter() {
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
}
