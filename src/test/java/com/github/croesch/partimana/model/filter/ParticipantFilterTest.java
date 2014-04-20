package com.github.croesch.partimana.model.filter;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.model.api.IFilterCategory;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.cat.p.BirthdayCategory;
import com.github.croesch.partimana.model.filter.cat.p.ForeNameCategory;
import com.github.croesch.partimana.model.filter.cat.p.GenderCategory;
import com.github.croesch.partimana.model.filter.cat.p.LivingCityCategory;
import com.github.croesch.partimana.model.filter.types.*;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Provides test cases for {@link ParticipantFilter}.
 *
 * @author croesch
 * @since Date: Oct 29, 2012
 */
public class ParticipantFilterTest {

  private ParticipantFilter filter;

  private Participant p1;

  private Participant p2;

  private Participant p3;

  private Participant p4;

  private Participant p5;

  @Before
  public void setUp() {
    filter = new ParticipantFilter();

    p1 = new Participant("Mustermann",
                              "Max",
                              Gender.MALE,
                              Denomination.CATHOLIC,
                              new Date(100000000),
                              "Musterstraße",
                              12345,
                              "Musterstadt",
                              CountyCouncil.COUNTY_RHEIN_PFALZ);
    p2 = new Participant("Schmidt",
                              "Jutta",
                              Gender.FEMALE,
                              Denomination.JEWISH,
                              new Date(1100000000),
                              "Hauptstraße",
                              77443,
                              "Entenhausen",
                              CountyCouncil.COUNTY_KAISERSLAUTERN);
    p3 = new Participant("Hansen",
                              "Hans",
                              Gender.MALE,
                              Denomination.CATHOLIC,
                              new Date(1200000000),
                              "Bauweg",
                              44551,
                              "Berlin",
                              CountyCouncil.COUNTY_GERMERSHEIM);
    p4 = new Participant("Mustermann",
                              "Josefine",
                              Gender.FEMALE,
                              Denomination.JEWISH,
                              new Date(1300000000),
                              "Maierweg",
                              33441,
                              "Bremen",
                              CountyCouncil.COUNTY_KAISERSLAUTERN);
    p5 = new Participant("Maier",
                              "Hellen",
                              Gender.FEMALE,
                              Denomination.CATHOLIC,
                              new Date(1400000000),
                              "Juppstraße",
                              11556,
                              "Berlin",
                              CountyCouncil.COUNTY_RHEIN_PFALZ);
  }

  @Test
  public void testSetGetCategory() {
    assertThat(filter.getCategory()).isNull();

    assertThat(filter.getCategories()).isNotNull();

    for (final IFilterCategory<Participant, ?> category : filter.getCategories()) {
      filter.setCategory(category);
      assertThat(filter.getCategory()).isEqualTo(category);
    }

    filter.setCategory(null);
    assertThat(filter.getCategory()).isNull();
  }

  @Test
  public void testFilterWithoutCategory() {
    assertThat(filter.filter(Arrays.asList(p1, p2, p3, p4, p5))).isEmpty();
  }

  @Test
  public void testFilter() {
    final List<Participant> participantList = Arrays.asList(p1, p2, p3, p4, p5);

    final IFilterCategory<Participant, Date> cat = new BirthdayCategory();
    final IFilterType<Date> filterType = new After();

    filter.setCategory(cat);
    assertThat(filter.filter(participantList)).isEmpty();

    filterType.setFilterValue(new Date(1100000000));
    cat.setFilter(filterType);
    assertThat(filter.filter(participantList)).containsOnly(p3, p4, p5);

    final IFilterCategory<Participant, Gender> cat2 = new GenderCategory();
    final IFilterType<Gender> filterType2 = new GenderEquals();
    cat2.setFilter(filterType2);
    filter.setCategory(cat2);
    assertThat(filter.filter(participantList)).isEmpty();
    filterType2.setFilterValue(Gender.FEMALE);
    assertThat(filter.filter(participantList)).containsOnly(p2, p4, p5);

    final IFilterCategory<Participant, String> cat3 = new LivingCityCategory();
    final IFilterType<String> filterType3 = new Contains();
    cat3.setFilter(filterType3);
    filterType3.setFilterValue("er");
    filter.setCategory(cat3);
    assertThat(filter.filter(participantList)).containsOnly(p1, p3, p5);

    final StartsWith startsWith = new StartsWith();
    startsWith.setFilterValue("");
    cat3.setFilter(startsWith);
    assertThat(filter.filter(participantList)).containsOnly(p1, p2, p3, p4, p5);
  }

  @Test
  public void testEquals() {
    assertThat(filter).isEqualTo(new ParticipantFilter());
    final ForeNameCategory category1 = new ForeNameCategory();
    filter.setCategory(category1);

    final ParticipantFilter other = new ParticipantFilter();
    final CampFilter different = new CampFilter();
    assertThat(filter).isNotEqualTo(other);
    assertThat(filter).isNotEqualTo(different);
    assertThat(other).isEqualTo(different);
    final ForeNameCategory category2 = new ForeNameCategory();
    other.setCategory(category2);
    assertThat(filter).isEqualTo(other);
    assertThat(other).isNotEqualTo(different);

    final StringEquals equals1 = new StringEquals();
    category1.setFilter(equals1);
    assertThat(filter).isNotEqualTo(other);
    final StringEquals equals2 = new StringEquals();
    category2.setFilter(equals2);
    assertThat(filter).isEqualTo(other);

    equals1.setFilterValue("Peter");
    assertThat(filter).isNotEqualTo(other);
    equals2.setFilterValue("peter");
    assertThat(filter).isNotEqualTo(other);
    equals2.setFilterValue("Peter");
    assertThat(filter).isEqualTo(other);
  }

  @Test
  public void testHashCode() {
    assertThat(filter.hashCode()).isEqualTo(new ParticipantFilter().hashCode());
    final ForeNameCategory category1 = new ForeNameCategory();
    filter.setCategory(category1);

    final ParticipantFilter other = new ParticipantFilter();
    assertThat(other.hashCode()).isEqualTo(new CampFilter().hashCode());
    final ForeNameCategory category2 = new ForeNameCategory();
    other.setCategory(category2);
    assertThat(filter.hashCode()).isEqualTo(other.hashCode());

    final StringEquals equals1 = new StringEquals();
    category1.setFilter(equals1);
    final StringEquals equals2 = new StringEquals();
    category2.setFilter(equals2);
    assertThat(filter.hashCode()).isEqualTo(other.hashCode());

    equals1.setFilterValue("Peter");
    assertThat(filter).isNotEqualTo(other);
    equals2.setFilterValue("peter");
    assertThat(filter).isNotEqualTo(other);
    equals2.setFilterValue("Peter");
    assertThat(filter.hashCode()).isEqualTo(other.hashCode());
  }
}
