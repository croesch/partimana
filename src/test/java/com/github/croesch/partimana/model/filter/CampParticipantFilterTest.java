package com.github.croesch.partimana.model.filter;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.model.api.IFilterCategory;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.cat.cp.SignedInCategory;
import com.github.croesch.partimana.model.filter.types.After;
import com.github.croesch.partimana.types.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Provides test cases for {@link ParticipantFilter}.
 *
 * @author croesch
 * @since Date: Mar 16, 2014
 */
public class CampParticipantFilterTest {

  private CampParticipantFilter filter;

  private CampParticipant cp1;

  private CampParticipant cp2;

  private CampParticipant cp3;

  private CampParticipant cp4;

  private CampParticipant cp5;

  @Before
  public void setUp() {
    filter = new CampParticipantFilter();

    cp1 = new CampParticipant(new Participant("Mustermann",
                                              "Max",
                                              Gender.MALE,
                                              Denomination.CATHOLIC,
                                              new Date(100000000),
                                              "Musterstraße",
                                              12345,
                                              "Musterstadt",
                                              CountyCouncil.COUNTY_RHEIN_PFALZ));
    cp1.setSignedIn(new Date(100000000));
    cp2 = new CampParticipant(new Participant("Schmidt",
                                              "Jutta",
                                              Gender.FEMALE,
                                              Denomination.JEWISH,
                                              new Date(1100000000),
                                              "Hauptstraße",
                                              77443,
                                              "Entenhausen",
                                              CountyCouncil.COUNTY_KAISERSLAUTERN));
    cp2.setSignedIn(new Date(1100000000));
    cp3 = new CampParticipant(new Participant("Hansen",
                                              "Hans",
                                              Gender.MALE,
                                              Denomination.CATHOLIC,
                                              new Date(1200000000),
                                              "Bauweg",
                                              44551,
                                              "Berlin",
                                              CountyCouncil.COUNTY_GERMERSHEIM));
    cp3.setSignedIn(new Date(1200000000));
    cp4 = new CampParticipant(new Participant("Mustermann",
                                              "Josefine",
                                              Gender.FEMALE,
                                              Denomination.JEWISH,
                                              new Date(1300000000),
                                              "Maierweg",
                                              33441,
                                              "Bremen",
                                              CountyCouncil.COUNTY_KAISERSLAUTERN));
    cp4.setSignedIn(new Date(1300000000));
    cp5 = new CampParticipant(new Participant("Maier",
                                              "Hellen",
                                              Gender.FEMALE,
                                              Denomination.CATHOLIC,
                                              new Date(1400000000),
                                              "Juppstraße",
                                              11556,
                                              "Berlin",
                                              CountyCouncil.COUNTY_RHEIN_PFALZ));
    cp5.setSignedIn(new Date(1400000000));
  }

  @Test
  public void testSetGetCategory() {
    assertThat(filter.getCategory()).isNull();

    assertThat(filter.getCategories()).isNotNull();

    for (final IFilterCategory<CampParticipant, ?> category : filter.getCategories()) {
      filter.setCategory(category);
      assertThat(filter.getCategory()).isEqualTo(category);
    }

    filter.setCategory(null);
    assertThat(filter.getCategory()).isNull();
  }

  @Test
  public void testFilterWithoutCategory() {
    assertThat(filter.filter(Arrays.asList(cp1, cp2, cp3, cp4, cp5))).isEmpty();
  }

  @Test
  public void testFilter() {
    final List<CampParticipant> campParticipantList = Arrays.asList(cp1, cp2, cp3, cp4, cp5);

    final IFilterCategory<CampParticipant, Date> cat = new SignedInCategory();
    final IFilterType<Date> filterType = new After();

    filter.setCategory(cat);
    assertThat(filter.filter(campParticipantList)).isEmpty();

    filterType.setFilterValue(new Date(1100000000));
    cat.setFilter(filterType);
    assertThat(filter.filter(campParticipantList)).containsOnly(cp3, cp4, cp5);
  }

  @Test
  public void testEquals() {
    assertThat(filter).isEqualTo(new CampParticipantFilter());
    final SignedInCategory category1 = new SignedInCategory();
    filter.setCategory(category1);

    final CampParticipantFilter other = new CampParticipantFilter();
    final CampFilter different = new CampFilter();
    assertThat(filter).isNotEqualTo(other);
    assertThat(filter).isNotEqualTo(different);
    assertThat(other).isEqualTo(different);
    final SignedInCategory category2 = new SignedInCategory();
    other.setCategory(category2);
    assertThat(filter).isEqualTo(other);
    assertThat(other).isNotEqualTo(different);

    final After after1 = new After();
    category1.setFilter(after1);
    assertThat(filter).isNotEqualTo(other);
    final After after2 = new After();
    category2.setFilter(after2);
    assertThat(filter).isEqualTo(other);

    after1.setFilterValue(new Date(12345678910L));
    assertThat(filter).isNotEqualTo(other);
    after2.setFilterValue(new Date(22345678910L));
    assertThat(filter).isNotEqualTo(other);
    after2.setFilterValue(new Date(12345678910L));
    assertThat(filter).isEqualTo(other);
  }

  @Test
  public void testHashCode() {
    assertThat(filter.hashCode()).isEqualTo(new CampParticipantFilter().hashCode());
    final SignedInCategory category1 = new SignedInCategory();
    filter.setCategory(category1);

    final CampParticipantFilter other = new CampParticipantFilter();
    assertThat(other.hashCode()).isEqualTo(new CampFilter().hashCode());
    final SignedInCategory category2 = new SignedInCategory();
    other.setCategory(category2);
    assertThat(filter.hashCode()).isEqualTo(other.hashCode());

    final After after1 = new After();
    category1.setFilter(after1);
    final After after2 = new After();
    category2.setFilter(after2);
    assertThat(filter.hashCode()).isEqualTo(other.hashCode());

    after1.setFilterValue(new Date(12345678910L));
    assertThat(filter).isNotEqualTo(other);
    after2.setFilterValue(new Date(22345678910L));
    assertThat(filter).isNotEqualTo(other);
    after2.setFilterValue(new Date(12345678910L));
    assertThat(filter.hashCode()).isEqualTo(other.hashCode());
  }
}
