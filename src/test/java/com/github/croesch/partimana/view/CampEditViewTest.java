package com.github.croesch.partimana.view;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.view.api.ICampEditView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.edt.GuiTask;
import org.junit.Before;
import org.junit.Test;

/**
 * Provides tests for {@link ICampEditView}
 *
 * @author croesch
 * @since Date: Sep 13, 2012
 */
public class CampEditViewTest {

  private ICampEditView testView;

  private Camp camp;

  @Before
  public void setUp() {
    testView = GuiActionRunner.execute(new GuiQuery<CampEditView>() {
      @Override
      protected CampEditView executeInEDT() throws Throwable {
        return new CampEditView(null);
      }
    });

    camp = new Camp("OFZ", new Date(500000), new Date(1000000), "Raversbeuren", "1,50€");

    camp.setRatePerDayChildren("0,5€");

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        testView.setCamp(camp);
      }
    });
  }

  @Test
  public void testGetName() {
    assertThat(testView.getNameOfCamp()).isEqualTo(camp.getName());
  }

  @Test
  public void testGetLocation() {
    assertThat(testView.getLocationOfCamp()).isEqualTo(camp.getLocation());
  }

  @Test
  public void testGetFrom() throws ParseException {
    final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    final Date date = sdf.parse(sdf.format(camp.getFromDate()));
    assertThat(testView.getFrom()).isEqualTo(date);
  }

  @Test
  public void testGetTo() throws ParseException {
    final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    final Date date = sdf.parse(sdf.format(camp.getUntilDate()));
    assertThat(testView.getUntil()).isEqualTo(date);
  }

  @Test
  public final void testGetRatePerParticipant() {
    assertThat(testView.getRatePerParticipant()).isEqualTo(camp.getRatePerParticipant());
  }

  @Test
  public final void testGetRatePerDayChildren() {
    assertThat(testView.getRatePerDay()).isEqualTo(camp.getRatePerDayChildren());
  }

  @Test
  public final void testGetId() {
    assertThat(testView.getId()).isEqualTo(camp.getId());
  }
}
