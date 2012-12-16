package com.github.croesch.partimana;

import org.fest.swing.data.TableCell;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.finder.WindowFinder;
import org.fest.swing.fixture.FrameFixture;
import org.junit.Test;

import com.github.croesch.partimana.controller.Controller;
import com.github.croesch.partimana.model.helper.HashMapPersistenceModel;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.view.CampListViewGUITest;
import com.github.croesch.partimana.view.CampParticipantListViewGUITest;
import com.github.croesch.partimana.view.ParticipantListViewGUITest;
import com.github.croesch.partimana.view.View;

/**
 * Provides a testcase that tests a workflow while using partimana.
 * 
 * @author croesch
 * @since Date: Sep 23, 2012
 */
public class WorkflowTest extends PartiManaDefaultGUITestCase {

  @Override
  protected void before() {}

  @Test
  public void test() {
    GuiActionRunner.execute(new GuiQuery<Controller>() {
      @Override
      protected Controller executeInEDT() throws Throwable {
        return new Controller(WorkflowTest.this, new HashMapPersistenceModel(), null);
      }
    });

    final FrameFixture frame = WindowFinder.findFrame(View.class).using(robot());
    frame.maximize();

    selectParticipantTab(frame);

    enterPart(frame, "Mario", "Müller", Gender.MALE, "0808.88", Denomination.ORTHODOX, CountyCouncil.CITY_PIRMASENS);
    frame.button("saveParticipant").click();

    reqPart(frame, "Mario", "Müller", Gender.MALE, "08.08.1988", Denomination.ORTHODOX, CountyCouncil.CITY_PIRMASENS);
    ParticipantListViewGUITest.requireParticipant(frame.table(), 0, "Mario", "Müller");

    frame.button("newParticipant").click();
    enterPart(frame, "Marianne", "Schmidt", Gender.FEMALE, "0304.1930", Denomination.JEWISH, CountyCouncil.CITY_LANDAU);
    frame.button("saveParticipant").click();

    reqPart(frame, "Marianne", "Schmidt", Gender.FEMALE, "03.04.1930", Denomination.JEWISH, CountyCouncil.CITY_LANDAU);
    ParticipantListViewGUITest.requireParticipant(frame.table(), 0, "Mario", "Müller");
    ParticipantListViewGUITest.requireParticipant(frame.table(), 1, "Marianne", "Schmidt");

    selectCampTab(frame);
    ParticipantListViewGUITest.requireParticipant(frame.table("participants"), 0, "Mario", "Müller");
    ParticipantListViewGUITest.requireParticipant(frame.table("participants"), 1, "Marianne", "Schmidt");

    enterCamp(frame, "OFZ", "Raversbeuren", "0808.2000", "2408.2000", "100 EUR");
    frame.button("saveCamp").click();
    reqCamp(frame, "OFZ", "Raversbeuren", "08.08.2000", "24.08.2000", "100 EUR");
    CampListViewGUITest.requireCamp(frame.table("camps"), 0, "OFZ", "Raversbeuren");

    frame.button("newCamp").click();
    enterCamp(frame, "HFZ", "Lindenmühle", "05.10.2000", "15.10.2000", "90 EUR");
    frame.button("saveCamp").click();
    reqCamp(frame, "HFZ", "Lindenmühle", "05.10.2000", "15.10.2000", "90 EUR");
    CampListViewGUITest.requireCamp(frame.table("camps"), 0, "OFZ", "Raversbeuren");
    CampListViewGUITest.requireCamp(frame.table("camps"), 1, "HFZ", "Lindenmühle");

    frame.table("camps").cell(TableCell.row(0).column(0)).doubleClick();
    reqCamp(frame, "OFZ", "Raversbeuren", "08.08.2000", "24.08.2000", "100 EUR");

    frame.table("participants").cell(TableCell.row(0).column(0)).doubleClick();
    ParticipantListViewGUITest.requireParticipant(frame.table("participants"), 0, "Marianne", "Schmidt");
    CampParticipantListViewGUITest.requireParticipant(frame.table("campParticipants"), 0, "Mario", "Müller");
    frame.button("saveCamp").click();

    frame.table("camps").cell(TableCell.row(1).column(0)).doubleClick();
    reqCamp(frame, "HFZ", "Lindenmühle", "05.10.2000", "15.10.2000", "90 EUR");

    frame.table("participants").cell(TableCell.row(0).column(0)).doubleClick();
    ParticipantListViewGUITest.requireParticipant(frame.table("participants"), 0, "Marianne", "Schmidt");
    CampParticipantListViewGUITest.requireParticipant(frame.table("campParticipants"), 0, "Mario", "Müller");
    frame.table("participants").cell(TableCell.row(0).column(1)).doubleClick();
    CampParticipantListViewGUITest.requireParticipant(frame.table("campParticipants"), 0, "Mario", "Müller");
    CampParticipantListViewGUITest.requireParticipant(frame.table("campParticipants"), 1, "Marianne", "Schmidt");
    frame.button("saveCamp").click();

    frame.table("camps").cell(TableCell.row(0).column(0)).doubleClick();
    reqCamp(frame, "OFZ", "Raversbeuren", "08.08.2000", "24.08.2000", "100 EUR");
    ParticipantListViewGUITest.requireParticipant(frame.table("participants"), 0, "Marianne", "Schmidt");
    CampParticipantListViewGUITest.requireParticipant(frame.table("campParticipants"), 0, "Mario", "Müller");

    frame.table("camps").cell(TableCell.row(1).column(0)).doubleClick();
    reqCamp(frame, "HFZ", "Lindenmühle", "05.10.2000", "15.10.2000", "90 EUR");
    CampParticipantListViewGUITest.requireParticipant(frame.table("campParticipants"), 0, "Mario", "Müller");
    CampParticipantListViewGUITest.requireParticipant(frame.table("campParticipants"), 1, "Marianne", "Schmidt");

    selectParticipantTab(frame);
    ParticipantListViewGUITest.requireParticipant(frame.table(), 0, "Mario", "Müller");
    ParticipantListViewGUITest.requireParticipant(frame.table(), 1, "Marianne", "Schmidt");

    frame.button("newParticipant").click();
    enterPart(frame, "Peter", "Maurer", Gender.MALE, "05.07.1957", Denomination.CATHOLIC,
              CountyCouncil.CITY_LUDWIGSHAFEN);
    frame.button("saveParticipant").click();
    reqPart(frame, "Peter", "Maurer", Gender.MALE, "05.07.1957", Denomination.CATHOLIC, CountyCouncil.CITY_LUDWIGSHAFEN);
    ParticipantListViewGUITest.requireParticipant(frame.table(), 0, "Mario", "Müller");
    ParticipantListViewGUITest.requireParticipant(frame.table(), 1, "Marianne", "Schmidt");
    ParticipantListViewGUITest.requireParticipant(frame.table(), 2, "Peter", "Maurer");

    selectCampTab(frame);
    ParticipantListViewGUITest.requireParticipant(frame.table("participants"), 0, "Peter", "Maurer");
    CampParticipantListViewGUITest.requireParticipant(frame.table("campParticipants"), 0, "Mario", "Müller");
    CampParticipantListViewGUITest.requireParticipant(frame.table("campParticipants"), 1, "Marianne", "Schmidt");

    frame.table("camps").cell(TableCell.row(0).column(0)).doubleClick();
    reqCamp(frame, "OFZ", "Raversbeuren", "08.08.2000", "24.08.2000", "100 EUR");
    ParticipantListViewGUITest.requireParticipant(frame.table("participants"), 0, "Marianne", "Schmidt");
    ParticipantListViewGUITest.requireParticipant(frame.table("participants"), 1, "Peter", "Maurer");
    CampParticipantListViewGUITest.requireParticipant(frame.table("campParticipants"), 0, "Mario", "Müller");

    frame.table("camps").cell(TableCell.row(1).column(0)).doubleClick();
    reqCamp(frame, "HFZ", "Lindenmühle", "05.10.2000", "15.10.2000", "90 EUR");
    ParticipantListViewGUITest.requireParticipant(frame.table("participants"), 0, "Peter", "Maurer");
    CampParticipantListViewGUITest.requireParticipant(frame.table("campParticipants"), 0, "Mario", "Müller");
    CampParticipantListViewGUITest.requireParticipant(frame.table("campParticipants"), 1, "Marianne", "Schmidt");

    frame.button("newCamp").click();
    reqCamp(frame, "", "", "", "", "");
    ParticipantListViewGUITest.requireParticipant(frame.table("participants"), 0, "Mario", "Müller");
    ParticipantListViewGUITest.requireParticipant(frame.table("participants"), 1, "Marianne", "Schmidt");
    ParticipantListViewGUITest.requireParticipant(frame.table("participants"), 2, "Peter", "Maurer");
    frame.table("campParticipants").requireRowCount(0);

    frame.menuItem("searchCamp").click();
  }

  private void selectParticipantTab(final FrameFixture frame) {
    frame.tabbedPane().selectTab(0);
    frame.menuItem("searchCamp").requireDisabled();
  }

  private void selectCampTab(final FrameFixture frame) {
    frame.tabbedPane().selectTab(1);
    frame.menuItem("searchCamp").requireEnabled();
  }

  private void enterCamp(final FrameFixture frame,
                         final String name,
                         final String location,
                         final String from,
                         final String until,
                         final String rate) {

    frame.textBox("nameTF").enterText(name);
    frame.textBox("locationTF").enterText(location);
    frame.textBox("fromTF").enterText(from);
    frame.textBox("untilTF").enterText(until);
    frame.textBox("ratePerParticipantTF").enterText(rate);
  }

  private void reqCamp(final FrameFixture frame,
                       final String name,
                       final String location,
                       final String from,
                       final String until,
                       final String rate) {

    frame.textBox("nameTF").requireText(name);
    frame.textBox("locationTF").requireText(location);
    frame.textBox("fromTF").requireText(from);
    frame.textBox("untilTF").requireText(until);
    frame.textBox("ratePerParticipantTF").requireText(rate);
  }

  private void enterPart(final FrameFixture frame,
                         final String foreName,
                         final String lastName,
                         final Gender gender,
                         final String birth,
                         final Denomination denomination,
                         final CountyCouncil cc) {

    frame.textBox("firstNameTF").enterText(foreName);
    frame.textBox("lastNameTF").enterText(lastName);
    frame.comboBox("genderCB").selectItem(gender.toString());
    frame.textBox("birthTF").enterText(birth);
    frame.comboBox("denominationCB").selectItem(denomination.toString());
    frame.comboBox("countyCouncilCB").selectItem(cc.toString());
  }

  private void reqPart(final FrameFixture frame,
                       final String foreName,
                       final String lastName,
                       final Gender gender,
                       final String birth,
                       final Denomination denomination,
                       final CountyCouncil cc) {

    frame.textBox("firstNameTF").requireText(foreName);
    frame.textBox("lastNameTF").requireText(lastName);
    frame.comboBox("genderCB").requireSelection(gender.toString());
    frame.textBox("birthTF").requireText(birth);
    frame.comboBox("denominationCB").requireSelection(denomination.toString());
    frame.comboBox("countyCouncilCB").requireSelection(cc.toString());
  }
}
