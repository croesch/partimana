package com.github.croesch.partimana.model.filter.types;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.components.CDateField;
import com.github.croesch.partimana.i18n.Text;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.Test;

/**
 * Provides tests for {@link DateEquals}.
 *
 * @author croesch
 * @since Date: Apr 15, 2014
 */
public class DateEqualsTest {
  private final Calendar cal = new GregorianCalendar();

  private DateEquals sut = new DateEquals();

  @Test
  public void testGetShortDescription() {
    assertThat(new DateEquals().getShortDescription()).isEqualTo(Text.FILTER_TYPE_EQUALS.text());
  }

  @Test
  public void testMatches() throws Exception {
    cal.set(2004, 10, 03, 12, 4);
    CDateField field = new CDateField();
    Date date1 = cal.getTime();
    field.setDate(date1);

    cal.set(Calendar.HOUR_OF_DAY, 17);
    Date date2 = cal.getTime();

    sut.parseFilterValue(field);

    assertThat(date1).isNotEqualTo(date2).isInSameDayAs(date2);
    assertThat(sut.matches(date1)).isTrue();
    assertThat(sut.matches(date2)).isTrue();

    cal.set(Calendar.DAY_OF_MONTH, 04);
    assertThat(sut.matches(cal.getTime())).isFalse();

    cal.set(Calendar.DAY_OF_MONTH, 02);
    assertThat(sut.matches(cal.getTime())).isFalse();
  }
}
