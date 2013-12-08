package com.github.croesch.partimana.model.filter.cat.p;

import static org.fest.assertions.Assertions.assertThat;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.types.*;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;
import java.util.Date;
import org.junit.Test;

/**
 * Provides test cases for {@link BankAccountNumberCategory}.
 *
 * @author croesch
 * @since Date: Oct 27, 2012
 */
public class BankAccountNumberCategoryTest {

  private BankAccountNumberCategory category;

  @org.junit.Before
  public void setUp() {
    this.category = new BankAccountNumberCategory();
  }

  @Test
  public void testGetFilters() {
    assertThat(this.category.getFilterTypes())
        .containsOnly(new IntegerEquals(), new IntegerNotEquals(), new LessThan(), new GreaterThan());

    final IFilterType<Integer> filterType = this.category.getFilterTypes().get(0);
    filterType.setFilterValue(12345);

    assertThat(this.category.getFilterTypes().contains(filterType)).isFalse();
  }

  @Test
  public void testSetAndGetFilter() {
    assertThat(this.category.getFilter()).isNull();
    this.category.setFilter(new IntegerEquals());
    assertThat(this.category.getFilter()).isEqualTo(new IntegerEquals());
    this.category.setFilter(null);
    assertThat(this.category.getFilter()).isNull();
  }

  @Test
  public void testGetShortDescription() {
    assertThat(this.category.getShortDescription()).isEqualTo(Text.FILTER_CAT_PARTICIPANT_BANK_ACC_NUM.text());
    assertThat(this.category.getShortDescription()).isEqualTo(Text.PARTICIPANT_BANK_ACCOUNT_NUMBER.text());
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
    p.setBankAccountNumber(123456789);
    assertThat(this.category.isMatchingFilter(p)).isFalse();

    final LessThan less = new LessThan();
    less.setFilterValue(500);
    this.category.setFilter(less);
    assertThat(this.category.isMatchingFilter(p)).isFalse();

    final GreaterThan greater = new GreaterThan();
    greater.setFilterValue(500);
    this.category.setFilter(greater);
    assertThat(this.category.isMatchingFilter(p)).isTrue();
  }

  @Test
  public void testEquals() {
    assertThat(this.category).isEqualTo(new BankAccountNumberCategory());
    assertThat(this.category).isEqualTo(this.category);
    assertThat(this.category).isNotEqualTo(null);
    assertThat(this.category).isNotEqualTo(new ForeNameCategory());
    assertThat(this.category).isNotEqualTo("category");

    this.category.setFilter(new IntegerEquals());
    final BankAccountNumberCategory other = new BankAccountNumberCategory();
    assertThat(this.category).isNotEqualTo(other);
    assertThat(other).isNotEqualTo(this.category);
    other.setFilter(new IntegerEquals());
    assertThat(this.category).isEqualTo(other);

    final ForeNameCategory different = new ForeNameCategory();
    different.setFilter(new StringEquals());
    this.category.getFilter().setFilterValue(12);
    assertThat(this.category).isNotEqualTo(different);
    assertThat(different).isNotEqualTo(this.category);
  }

  @Test
  public void testHashCode() {
    assertThat(this.category.hashCode()).isEqualTo(new BankAccountNumberCategory().hashCode());
    assertThat(this.category.hashCode()).isEqualTo(this.category.hashCode());
    assertThat(this.category.hashCode()).isEqualTo(new ForeNameCategory().hashCode());

    this.category.setFilter(new IntegerEquals());
    final BankAccountNumberCategory other = new BankAccountNumberCategory();
    other.setFilter(new IntegerEquals());
    assertThat(this.category.hashCode()).isEqualTo(other.hashCode());

    final ForeNameCategory different = new ForeNameCategory();
    different.setFilter(new StringEquals());
    assertThat(this.category.hashCode()).isEqualTo(different.hashCode());
  }
}
