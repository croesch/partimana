package com.github.croesch.partimana.view;

import static org.fest.assertions.Assertions.assertThat;

import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.view.api.ICampEditView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
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
    this.testView = GuiActionRunner.execute(new GuiQuery<CampEditView>() {
      @Override
      protected CampEditView executeInEDT() throws Throwable {
        return new CampEditView(null);
      }
    });

    this.camp = new Camp("OFZ", new Date(500000), new Date(1000000), "Raversbeuren", "1,50€");

    this.camp.setRatePerDayChildren("0,5€");

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        CampEditViewTest.this.testView.setCamp(CampEditViewTest.this.camp);
      }
    });
  }

  @Test
  public void testGetName() {
    assertThat(this.testView.getNameOfCamp()).isEqualTo(this.camp.getName());
  }

  @Test
  public void testGetLocation() {
    assertThat(this.testView.getLocationOfCamp()).isEqualTo(this.camp.getLocation());
  }

  @Test
  public void testGetFrom() throws ParseException {
    final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    final Date date = sdf.parse(sdf.format(this.camp.getFromDate()));
    assertThat(this.testView.getFrom()).isEqualTo(date);
  }

  @Test
  public void testGetTo() throws ParseException {
    final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    final Date date = sdf.parse(sdf.format(this.camp.getUntilDate()));
    assertThat(this.testView.getUntil()).isEqualTo(date);
  }

  @Test
  public final void testGetRatePerParticipant() {
    assertThat(this.testView.getRatePerParticipant()).isEqualTo(this.camp.getRatePerParticipant());
  }

  @Test
  public final void testGetRatePerDayChildren() {
    assertThat(this.testView.getRatePerDay()).isEqualTo(this.camp.getRatePerDayChildren());
  }

  @Test
  public final void testGetId() {
    assertThat(this.testView.getId()).isEqualTo(this.camp.getId());
  }
}
