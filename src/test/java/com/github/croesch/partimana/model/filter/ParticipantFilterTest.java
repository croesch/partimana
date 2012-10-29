package com.github.croesch.partimana.model.filter;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.croesch.partimana.model.api.IFilterCategory;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.cat.p.BirthdayCategory;
import com.github.croesch.partimana.model.filter.cat.p.GenderCategory;
import com.github.croesch.partimana.model.filter.cat.p.LivingCityCategory;
import com.github.croesch.partimana.model.filter.types.After;
import com.github.croesch.partimana.model.filter.types.Contains;
import com.github.croesch.partimana.model.filter.types.Equals;
import com.github.croesch.partimana.model.filter.types.StartsWith;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;

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
    this.filter = new ParticipantFilter();

    this.p1 = new Participant("Mustermann",
                              "Max",
                              Gender.MALE,
                              Denomination.CATHOLIC,
                              new Date(100000000),
                              "Musterstraße",
                              12345,
                              "Musterstadt",
                              CountyCouncil.COUNTY_RHEIN_PFALZ);
    this.p2 = new Participant("Schmidt",
                              "Jutta",
                              Gender.FEMALE,
                              Denomination.JEWISH,
                              new Date(1100000000),
                              "Hauptstraße",
                              77443,
                              "Entenhausen",
                              CountyCouncil.COUNTY_KAISERSLAUTERN);
    this.p3 = new Participant("Hansen",
                              "Hans",
                              Gender.MALE,
                              Denomination.CATHOLIC,
                              new Date(1200000000),
                              "Bauweg",
                              44551,
                              "Berlin",
                              CountyCouncil.COUNTY_GERMERSHEIM);
    this.p4 = new Participant("Mustermann",
                              "Josefine",
                              Gender.FEMALE,
                              Denomination.JEWISH,
                              new Date(1300000000),
                              "Maierweg",
                              33441,
                              "Bremen",
                              CountyCouncil.COUNTY_KAISERSLAUTERN);
    this.p5 = new Participant("Maier",
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
    assertThat(this.filter.getCategory()).isNull();

    assertThat(this.filter.getCategories()).isNotNull();

    for (final IFilterCategory<Participant, ?> category : this.filter.getCategories()) {
      this.filter.setCategory(category);
      assertThat(this.filter.getCategory()).isEqualTo(category);
    }

    this.filter.setCategory(null);
    assertThat(this.filter.getCategory()).isNull();
  }

  @Test
  public void testFilterWithoutCategory() {
    assertThat(this.filter.filter(Arrays.asList(this.p1, this.p2, this.p3, this.p4, this.p5))).isEmpty();
  }

  @Test
  public void testFilter() {
    final List<Participant> participantList = Arrays.asList(this.p1, this.p2, this.p3, this.p4, this.p5);

    final IFilterCategory<Participant, Date> cat = new BirthdayCategory();
    final IFilterType<Date> filterType = new After();

    this.filter.setCategory(cat);
    assertThat(this.filter.filter(participantList)).isEmpty();

    filterType.setFilterValue(new Date(1100000000));
    cat.setFilter(filterType);
    assertThat(this.filter.filter(participantList)).containsOnly(this.p3, this.p4, this.p5);

    final IFilterCategory<Participant, Gender> cat2 = new GenderCategory();
    final IFilterType<Gender> filterType2 = new Equals<Gender>();
    cat2.setFilter(filterType2);
    this.filter.setCategory(cat2);
    assertThat(this.filter.filter(participantList)).isEmpty();
    filterType2.setFilterValue(Gender.FEMALE);
    assertThat(this.filter.filter(participantList)).containsOnly(this.p2, this.p4, this.p5);

    final IFilterCategory<Participant, String> cat3 = new LivingCityCategory();
    final IFilterType<String> filterType3 = new Contains();
    cat3.setFilter(filterType3);
    filterType3.setFilterValue("er");
    this.filter.setCategory(cat3);
    assertThat(this.filter.filter(participantList)).containsOnly(this.p1, this.p3, this.p5);

    final StartsWith startsWith = new StartsWith();
    startsWith.setFilterValue("");
    cat3.setFilter(startsWith);
    assertThat(this.filter.filter(participantList)).containsOnly(this.p1, this.p2, this.p3, this.p4, this.p5);
  }
}
