package com.github.croesch.partimana.model.filter.cat.cp;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.cat.c.LocationCategory;
import com.github.croesch.partimana.model.filter.types.*;
import com.github.croesch.partimana.types.*;
import java.util.Date;
import org.junit.Test;

/**
 * Provides test cases for {@link SignedInCategory}.
 *
 * @author croesch
 * @since Date: Mar 16, 2014
 */
public class SignedInCategoryTest {

  private SignedInCategory category;

  @org.junit.Before
  public void setUp() {
    this.category = new SignedInCategory();
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
    assertThat(this.category.getShortDescription()).isEqualTo(Text.FILTER_CAT_CAMP_PARTICIPANT_SIGNED_IN.text());
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
    CampParticipant cp = new CampParticipant(p);
    cp.setSignedIn(new Date(800000000));
    assertThat(this.category.isMatchingFilter(cp)).isFalse();

    final After after = new After();
    after.setFilterValue(new Date(7000000));
    this.category.setFilter(after);
    assertThat(this.category.isMatchingFilter(cp)).isTrue();

    final Before before = new Before();
    before.setFilterValue(new Date(7000000));
    this.category.setFilter(before);
    assertThat(this.category.isMatchingFilter(cp)).isFalse();
  }

  @Test
  public void testEquals() {
    assertThat(this.category).isEqualTo(new SignedInCategory());
    assertThat(this.category).isEqualTo(this.category);
    assertThat(this.category).isNotEqualTo(null);
    assertThat(this.category).isNotEqualTo(new LocationCategory());
    assertThat(this.category).isNotEqualTo("category");

    this.category.setFilter(new DateEquals());
    final SignedInCategory other = new SignedInCategory();
    assertThat(this.category).isNotEqualTo(other);
    assertThat(other).isNotEqualTo(this.category);
    other.setFilter(new DateEquals());
    assertThat(this.category).isEqualTo(other);

    final LocationCategory different = new LocationCategory();
    different.setFilter(new StringEquals());
    this.category.getFilter().setFilterValue(new Date());
    assertThat(this.category).isNotEqualTo(different);
    assertThat(different).isNotEqualTo(this.category);
  }

  @Test
  public void testHashCode() {
    assertThat(this.category.hashCode()).isEqualTo(new SignedInCategory().hashCode());
    assertThat(this.category.hashCode()).isEqualTo(this.category.hashCode());
    assertThat(this.category.hashCode()).isEqualTo(new LocationCategory().hashCode());

    this.category.setFilter(new DateEquals());
    final SignedInCategory other = new SignedInCategory();
    other.setFilter(new DateEquals());
    assertThat(this.category.hashCode()).isEqualTo(other.hashCode());

    final LocationCategory different = new LocationCategory();
    different.setFilter(new StringEquals());
    assertThat(this.category.hashCode()).isEqualTo(different.hashCode());
  }
}
