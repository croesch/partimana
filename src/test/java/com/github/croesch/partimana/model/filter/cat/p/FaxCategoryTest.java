package com.github.croesch.partimana.model.filter.cat.p;

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
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;

/**
 * Provides test cases for {@link FaxCategory}.
 * 
 * @author croesch
 * @since Date: Oct 27, 2012
 */
public class FaxCategoryTest {

  private FaxCategory category;

  @Before
  public void setUp() {
    this.category = new FaxCategory();
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
    assertThat(this.category.getShortDescription()).isEqualTo(Text.FILTER_CAT_PARTICIPANT_FAX.text());
    assertThat(this.category.getShortDescription()).isEqualTo(Text.PARTICIPANT_FAX.text());
  }

  @Test
  public void isMatchingFilter() {
    assertThat(this.category.isMatchingFilter(null)).isFalse();

    final Participant p = new Participant("Maxi",
                                          "Musterfrau",
                                          Gender.FEMALE,
                                          Denomination.CATHOLIC,
                                          new Date(100000000),
                                          "street",
                                          12,
                                          "city",
                                          CountyCouncil.COUNTY_RHEIN_PFALZ);
    p.setFax("Special thing");
    assertThat(this.category.isMatchingFilter(p)).isFalse();

    final StartsWith startsWith = new StartsWith();
    startsWith.setFilterValue("Spec");
    this.category.setFilter(startsWith);
    assertThat(this.category.isMatchingFilter(p)).isTrue();

    final EndsWith endsWith = new EndsWith();
    endsWith.setFilterValue("Spec");
    this.category.setFilter(endsWith);
    assertThat(this.category.isMatchingFilter(p)).isFalse();
  }
}