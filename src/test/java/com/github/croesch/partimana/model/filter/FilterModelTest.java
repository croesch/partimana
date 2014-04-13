package com.github.croesch.partimana.model.filter;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.model.api.IFilter;
import com.github.croesch.partimana.model.filter.cat.c.LocationCategory;
import com.github.croesch.partimana.model.filter.cat.c.NameCategory;
import com.github.croesch.partimana.model.filter.types.EndsWith;
import com.github.croesch.partimana.model.filter.types.StartsWith;
import com.github.croesch.partimana.types.Camp;
import java.util.Arrays;
import java.util.Date;
import org.junit.Test;

/**
 * Provides test cases for {@link FilterModel}.
 *
 * @author croesch
 * @since Date: Nov 1, 2012
 */
public class FilterModelTest {

  private Camp c1 = new Camp("OFZ", new Date(15000000), new Date(110000000), "Berlin", "20 USD");

  private Camp c2 = new Camp("HFZ", new Date(25000000), new Date(210000000), "Frankfurt", "2 EUR");

  private Camp c3 = new Camp("Freizeit", new Date(35000000), new Date(310000000), "Stuttgart", "2");

  private Camp c4 = new Camp("Lager", new Date(45000000), new Date(410000000), "Hannover", "10");

  private Camp c5 = new Camp("Camp", new Date(55000000), new Date(510000000), "München", "200");

  @Test
  public void test_setFilter_And_getFilterMatchingElements_WithDifferentFilters() {
    final FilterModel<Camp> fm = new FilterModel<Camp>(Arrays.asList(c1, c2, c3, c4, c5));
    assertThat(fm.getFilterMatchingElements()).containsExactly(c1, c2, c3, c4, c5);

    fm.setFilter(createFilter1());
    assertThat(fm.getFilterMatchingElements()).containsExactly(c1);

    fm.setFilter(createFilter2());
    assertThat(fm.getFilterMatchingElements()).containsExactly(c2);

    fm.setFilter(createFilter15());
    assertThat(fm.getFilterMatchingElements()).containsExactly(c1, c5);

    fm.setFilter(createFilter12());
    assertThat(fm.getFilterMatchingElements()).containsExactly(c1, c2);
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

  private IFilter<Camp> createFilter1() {
    final CampFilter filter1 = new CampFilter();
    final NameCategory cat1 = new NameCategory();
    final StartsWith startsWidthO = new StartsWith();
    startsWidthO.setFilterValue("O");
    cat1.setFilter(startsWidthO);
    filter1.setCategory(cat1);

    return filter1;
  }

  private IFilter<Camp> createFilter2() {
    final CampFilter filter2 = new CampFilter();
    final NameCategory cat2 = new NameCategory();
    final StartsWith startsWidthH = new StartsWith();
    startsWidthH.setFilterValue("H");
    cat2.setFilter(startsWidthH);
    filter2.setCategory(cat2);

    return filter2;
  }
}
