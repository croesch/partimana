package com.github.croesch.partimana.model.filter;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.croesch.partimana.model.api.IFilterCategory;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.cat.c.FromCategory;
import com.github.croesch.partimana.model.filter.cat.c.LocationCategory;
import com.github.croesch.partimana.model.filter.cat.c.UntilCategory;
import com.github.croesch.partimana.model.filter.types.After;
import com.github.croesch.partimana.model.filter.types.Contains;
import com.github.croesch.partimana.model.filter.types.StartsWith;
import com.github.croesch.partimana.model.filter.types.StringEquals;
import com.github.croesch.partimana.types.Camp;

/**
 * Provides test cases for {@link CampFilter}.
 * 
 * @author croesch
 * @since Date: Oct 28, 2012
 */
public class CampFilterTest {

  private CampFilter filter;

  private Camp c1;

  private Camp c2;

  private Camp c3;

  private Camp c4;

  private Camp c5;

  @Before
  public void setUp() {
    this.filter = new CampFilter();

    this.c1 = new Camp("OFZ", new Date(15000000), new Date(110000000), "Berlin", "20 USD");
    this.c2 = new Camp("HFZ", new Date(25000000), new Date(210000000), "Frankfurt", "2 EUR");
    this.c3 = new Camp("Freizeit", new Date(35000000), new Date(310000000), "Stuttgart", "2");
    this.c4 = new Camp("Lager", new Date(45000000), new Date(410000000), "Hannover", "10");
    this.c5 = new Camp("Camp", new Date(55000000), new Date(510000000), "München", "200");
  }

  @Test
  public void testSetGetCategory() {
    assertThat(this.filter.getCategory()).isNull();

    assertThat(this.filter.getCategories()).isNotNull();

    for (final IFilterCategory<Camp, ?> category : this.filter.getCategories()) {
      this.filter.setCategory(category);
      assertThat(this.filter.getCategory()).isEqualTo(category);
    }

    this.filter.setCategory(null);
    assertThat(this.filter.getCategory()).isNull();
  }

  @Test
  public void testFilterWithoutCategory() {
    assertThat(this.filter.filter(Arrays.asList(this.c1, this.c2, this.c3, this.c4, this.c5))).isEmpty();
  }

  @Test
  public void testFilter() {
    final List<Camp> campList = Arrays.asList(this.c1, this.c2, this.c3, this.c4, this.c5);

    IFilterCategory<Camp, Date> cat = new UntilCategory();
    final IFilterType<Date> filter = new After();

    this.filter.setCategory(cat);
    assertThat(this.filter.filter(campList)).isEmpty();

    filter.setFilterValue(new Date(310000000));
    cat.setFilter(filter);
    assertThat(this.filter.filter(campList)).containsOnly(this.c4, this.c5);

    cat = new FromCategory();
    cat.setFilter(filter);
    this.filter.setCategory(cat);
    assertThat(this.filter.filter(campList)).isEmpty();

    final IFilterCategory<Camp, String> category = new LocationCategory();
    final IFilterType<String> filter2 = new Contains();
    category.setFilter(filter2);
    filter2.setFilterValue("er");
    this.filter.setCategory(category);
    assertThat(this.filter.filter(campList)).containsOnly(this.c1, this.c4);

    final StartsWith startsWith = new StartsWith();
    startsWith.setFilterValue("");
    category.setFilter(startsWith);
    assertThat(this.filter.filter(campList)).containsOnly(this.c1, this.c2, this.c3, this.c4, this.c5);
  }

  @Test
  public void testEquals() {
    assertThat(this.filter).isEqualTo(new CampFilter());
    final LocationCategory category1 = new LocationCategory();
    this.filter.setCategory(category1);

    final CampFilter other = new CampFilter();
    final ParticipantFilter different = new ParticipantFilter();
    assertThat(this.filter).isNotEqualTo(other);
    assertThat(this.filter).isNotEqualTo(different);
    assertThat(other).isEqualTo(different);
    final LocationCategory category2 = new LocationCategory();
    other.setCategory(category2);
    assertThat(this.filter).isEqualTo(other);
    assertThat(other).isNotEqualTo(different);

    final StringEquals equals1 = new StringEquals();
    category1.setFilter(equals1);
    assertThat(this.filter).isNotEqualTo(other);
    final StringEquals equals2 = new StringEquals();
    category2.setFilter(equals2);
    assertThat(this.filter).isEqualTo(other);

    equals1.setFilterValue("Peter");
    assertThat(this.filter).isNotEqualTo(other);
    equals2.setFilterValue("peter");
    assertThat(this.filter).isNotEqualTo(other);
    equals2.setFilterValue("Peter");
    assertThat(this.filter).isEqualTo(other);
  }

  @Test
  public void testHashCode() {
    assertThat(this.filter.hashCode()).isEqualTo(new CampFilter().hashCode());
    final LocationCategory category1 = new LocationCategory();
    this.filter.setCategory(category1);

    final CampFilter other = new CampFilter();
    assertThat(other.hashCode()).isEqualTo(new ParticipantFilter().hashCode());
    final LocationCategory category2 = new LocationCategory();
    other.setCategory(category2);
    assertThat(this.filter.hashCode()).isEqualTo(other.hashCode());

    final StringEquals equals1 = new StringEquals();
    category1.setFilter(equals1);
    final StringEquals equals2 = new StringEquals();
    category2.setFilter(equals2);
    assertThat(this.filter.hashCode()).isEqualTo(other.hashCode());

    equals1.setFilterValue("Peter");
    assertThat(this.filter).isNotEqualTo(other);
    equals2.setFilterValue("peter");
    assertThat(this.filter).isNotEqualTo(other);
    equals2.setFilterValue("Peter");
    assertThat(this.filter.hashCode()).isEqualTo(other.hashCode());
  }
}
