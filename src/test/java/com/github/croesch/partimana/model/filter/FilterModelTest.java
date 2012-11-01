package com.github.croesch.partimana.model.filter;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.github.croesch.partimana.model.api.IFilter;
import com.github.croesch.partimana.model.filter.cat.c.LocationCategory;
import com.github.croesch.partimana.model.filter.cat.c.NameCategory;
import com.github.croesch.partimana.model.filter.types.Contains;
import com.github.croesch.partimana.model.filter.types.EndsWith;
import com.github.croesch.partimana.types.Camp;

/**
 * Provides test cases for {@link FilterModel}.
 * 
 * @author croesch
 * @since Date: Nov 1, 2012
 */
public class FilterModelTest {

  private Camp c1;

  private Camp c2;

  private Camp c3;

  private Camp c4;

  private Camp c5;

  @Before
  public void setUp() {
    this.c1 = new Camp("OFZ", new Date(15000000), new Date(110000000), "Berlin", "20 USD");
    this.c2 = new Camp("HFZ", new Date(25000000), new Date(210000000), "Frankfurt", "2 EUR");
    this.c3 = new Camp("Freizeit", new Date(35000000), new Date(310000000), "Stuttgart", "2");
    this.c4 = new Camp("Lager", new Date(45000000), new Date(410000000), "Hannover", "10");
    this.c5 = new Camp("Camp", new Date(55000000), new Date(510000000), "München", "200");
  }

  @Test
  public void test() {
    FilterModel<Camp> fm = new FilterModel<Camp>(Arrays.asList(this.c1, this.c2, this.c3, this.c4, this.c5));
    assertThat(fm.getFilterMatchingElements()).isEmpty();

    fm.and(createFilter15());
    assertThat(fm.getFilterMatchingElements()).containsExactly(this.c1, this.c5);

    fm.or(createFilter12());
    assertThat(fm.getFilterMatchingElements()).containsExactly(this.c1, this.c5, this.c2);

    fm = new FilterModel<Camp>(Arrays.asList(this.c1, this.c2, this.c3, this.c4, this.c5));
    fm.or(createFilter15());
    assertThat(fm.getFilterMatchingElements()).containsExactly(this.c1, this.c5);

    fm.and(createFilter12());
    assertThat(fm.getFilterMatchingElements()).containsExactly(this.c1);

    fm.or(createFilter234());
    assertThat(fm.getFilterMatchingElements()).containsExactly(this.c1, this.c2, this.c3, this.c4);

    fm.and(createFilter12());
    assertThat(fm.getFilterMatchingElements()).containsExactly(this.c1, this.c2);

    fm.and(createFilter15());
    assertThat(fm.getFilterMatchingElements()).containsExactly(this.c1);

    fm.or(createFilter15());
    assertThat(fm.getFilterMatchingElements()).containsExactly(this.c1, this.c5);
  }

  private IFilter<Camp> createFilter15() {
    final CampFilter filter15 = new CampFilter();
    final LocationCategory cat15 = new LocationCategory();
    final EndsWith endsWithN = new EndsWith();
    endsWithN.setFilterValue("n");
    cat15.setFilter(endsWithN);
    filter15.setCategory(cat15);

    return filter15;
  }

  private IFilter<Camp> createFilter12() {
    final CampFilter filter12 = new CampFilter();
    final NameCategory cat12 = new NameCategory();
    final EndsWith endsWithFZ = new EndsWith();
    endsWithFZ.setFilterValue("FZ");
    cat12.setFilter(endsWithFZ);
    filter12.setCategory(cat12);

    return filter12;
  }

  private IFilter<Camp> createFilter234() {
    final CampFilter filter234 = new CampFilter();
    final LocationCategory cat234 = new LocationCategory();
    final Contains containsA = new Contains();
    containsA.setFilterValue("a");
    cat234.setFilter(containsA);
    filter234.setCategory(cat234);

    return filter234;
  }

}
