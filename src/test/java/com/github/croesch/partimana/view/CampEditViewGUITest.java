package com.github.croesch.partimana.view;

import static org.fest.assertions.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.fixture.Containers;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JLabelFixture;
import org.fest.swing.fixture.JPanelFixture;
import org.fest.swing.fixture.JTextComponentFixture;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.croesch.partimana.PartiManaDefaultGUITestCase;
import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.view.api.ICampEditView;

/**
 * Provides gui tests for {@link CampEditView}
 * 
 * @author croesch
 * @since Date: Sep 13, 2012
 */
public class CampEditViewGUITest extends PartiManaDefaultGUITestCase {

  private ICampEditView editView;

  private JPanelFixture testView;

  private Camp camp;

  private FrameFixture window;

  @BeforeClass
  public static void first() {
    Locale.setDefault(Locale.GERMAN);
  }

  @Override
  protected void before() {
    final CampEditView view = GuiActionRunner.execute(new GuiQuery<CampEditView>() {
      @Override
      protected CampEditView executeInEDT() throws Throwable {
        return new CampEditView();
      }
    });

    this.camp = new Camp("OFZ", new Date(500000), new Date(1000000), "Raversbeuren", "1,50€");
    this.camp.setRatePerDayChildren("0,5€");

    GuiActionRunner.execute(new GuiTask() {

      @Override
      protected void executeInEDT() throws Throwable {
        view.setCamp(CampEditViewGUITest.this.camp);
      }
    });
    view.setName("editView");
    this.editView = view;

    this.window = new FrameFixture(robot(), Containers.frameFor(view));
    this.window.show();
    this.testView = this.window.panel("editView");
  }

  @Override
  protected void after() {
    this.window.cleanUp();
    cleanUp();
  }

  @Test
  public void testClear() {
    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        CampEditViewGUITest.this.editView.clear();
      }
    });
    this.testView.textBox("nameTF").requireEmpty();
    this.testView.textBox("locationTF").requireEmpty();
    this.testView.textBox("fromTF").requireEmpty();
    this.testView.textBox("untilTF").requireEmpty();
    this.testView.textBox("ratePerParticipantTF").requireEmpty();
    this.testView.textBox("ratePerDayTF").requireEmpty();
    this.testView.label("idLbl").requireText((String) null);

    assertThat(this.editView.getNameOfCamp()).isEmpty();
    assertThat(this.editView.getLocationOfCamp()).isEmpty();
    assertThat(this.editView.getId()).isEqualTo(0);
    assertThat(this.editView.getFrom()).isNull();
    assertThat(this.editView.getUntil()).isNull();
    assertThat(this.editView.getRatePerParticipant()).isEmpty();
    assertThat(this.editView.getRatePerDay()).isEmpty();
  }

  @Test
  public void testSetCamp() {
    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        CampEditViewGUITest.this.editView.setCamp(null);
      }
    });
    this.testView.textBox("nameTF").requireEmpty();
    this.testView.textBox("locationTF").requireEmpty();
    this.testView.textBox("fromTF").requireEmpty();
    this.testView.textBox("untilTF").requireEmpty();
    this.testView.textBox("ratePerParticipantTF").requireEmpty();
    this.testView.textBox("ratePerDayTF").requireEmpty();
    this.testView.label("idLbl").requireText((String) null);

    assertThat(this.editView.getNameOfCamp()).isEmpty();
    assertThat(this.editView.getLocationOfCamp()).isEmpty();
    assertThat(this.editView.getId()).isEqualTo(0);
    assertThat(this.editView.getFrom()).isNull();
    assertThat(this.editView.getUntil()).isNull();
    assertThat(this.editView.getRatePerParticipant()).isEmpty();
    assertThat(this.editView.getRatePerDay()).isEmpty();
  }

  @Test
  public void testGetName() {
    final JTextComponentFixture testObj = this.testView.textBox("nameTF");

    testObj.requireText(this.camp.getName());
    testObj.deleteText();
    testObj.enterText("OFZ");
    assertThat(this.editView.getNameOfCamp()).isEqualTo("OFZ");
  }

  @Test
  public void testGetLocation() {
    final JTextComponentFixture testObj = this.testView.textBox("locationTF");

    testObj.requireText(this.camp.getLocation());
    testObj.deleteText();
    testObj.enterText("Raversbeuren");
    assertThat(this.editView.getLocationOfCamp()).isEqualTo("Raversbeuren");
  }

  @Test
  public final void testGetFrom() throws ParseException {
    final JTextComponentFixture testObj = this.testView.textBox("fromTF");
    final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    testObj.requireText(sdf.format(this.camp.getFromDate()));
    testObj.deleteText();
    testObj.enterText("24.03.2002");
    assertThat(this.editView.getFrom()).isEqualTo(sdf.parse("24.03.2002"));
  }

  @Test
  public final void testGetTo() throws ParseException {
    final JTextComponentFixture testObj = this.testView.textBox("untilTF");
    final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    testObj.requireText(sdf.format(this.camp.getUntilDate()));
    testObj.deleteText();
    testObj.enterText("14.04.2002");
    assertThat(this.editView.getUntil()).isEqualTo(sdf.parse("14.04.2002"));
  }

  @Test
  public final void testGetRatePerParticipant() {
    final JTextComponentFixture testObj = this.testView.textBox("ratePerParticipantTF");

    testObj.requireText(this.camp.getRatePerParticipant());
    testObj.deleteText();
    testObj.enterText("1,50 EUR");
    assertThat(this.editView.getRatePerParticipant()).isEqualTo("1,50 EUR");
  }

  @Test
  public final void testGetRatePerDay() {
    final JTextComponentFixture testObj = this.testView.textBox("ratePerDayTF");

    testObj.requireText(this.camp.getRatePerDayChildren());
    testObj.deleteText();
    testObj.enterText("2,50 EUR");
    assertThat(this.editView.getRatePerDay()).isEqualTo("2,50 EUR");
  }

  @Test
  public final void testGetID() {
    final JLabelFixture testObj = this.testView.label("idLbl");
    testObj.requireText(String.valueOf(this.camp.getId()));
    assertThat(this.editView.getId()).isEqualTo(this.camp.getId());
  }
}
