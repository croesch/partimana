package com.github.croesch.partimana.view;

import static com.github.croesch.partimana.view.CampParticipantListViewGUITest.requireParticipant;
import static com.github.croesch.partimana.view.ParticipantListViewGUITest.requireParticipant;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.PartiManaDefaultGUITestCase;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.types.*;
import com.github.croesch.partimana.view.api.ICampEditView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.assertj.swing.core.MouseClickInfo;
import org.assertj.swing.data.TableCell;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.edt.GuiTask;
import org.assertj.swing.fixture.*;
import org.junit.BeforeClass;
import org.junit.Test;

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

  private Participant participant;

  @BeforeClass
  public static void first() {
    Locale.setDefault(Locale.GERMAN);
  }

  @Override
  protected void before() {
    final CampEditView view = GuiActionRunner.execute(new GuiQuery<CampEditView>() {
      @Override
      protected CampEditView executeInEDT() throws Throwable {
        return new CampEditView(null);
      }
    });

    camp = new Camp("OFZ", new Date(500000), new Date(1000000), "Raversbeuren", "1,50€");
    camp.setRatePerDayChildren("0,5€");

    participant = new Participant("Schmidt",
                                  "Hans",
                                  Gender.MALE,
                                  Denomination.NONE,
                                  new Date(1200),
                                  "Strasse 4",
                                  56789,
                                  "Stadt",
                                  CountyCouncil.CITY_NEUSTADT);
    camp.addParticipant(new CampParticipant(participant));

    view.setName("editView");
    editView = view;
    updateCamp();

    window = new FrameFixture(robot(), Containers.frameFor(view));
    window.show();
    testView = window.panel("editView");
  }

  private void updateCamp() {
    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setCamp(camp);
      }
    });
  }

  @Override
  protected void after() {
    window.cleanUp();
    cleanUp();
  }

  @Test
  public void testClear() {
    clear();
    testView.textBox("nameTF").requireEmpty();
    testView.textBox("locationTF").requireEmpty();
    testView.textBox("fromTF").requireEmpty();
    testView.textBox("untilTF").requireEmpty();
    testView.textBox("ratePerParticipantTF").requireEmpty();
    testView.textBox("ratePerDayTF").requireEmpty();
    testView.label("idLbl").requireText((String) null);
    testView.table("campParticipants").requireRowCount(0);
    testView.label("cancelledLbl").requireText((String) null);

    assertThat(editView.getNameOfCamp()).isEmpty();
    assertThat(editView.getLocationOfCamp()).isEmpty();
    assertThat(editView.getId()).isEqualTo(0);
    assertThat(editView.getFrom()).isNull();
    assertThat(editView.getUntil()).isNull();
    assertThat(editView.getRatePerParticipant()).isEmpty();
    assertThat(editView.getRatePerDay()).isEmpty();
    assertThat(editView.getCampParticipants()).isEmpty();
  }

  private void clear() {
    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.clear();
      }
    });
  }

  @Test
  public void testSetCamp() {
    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setCamp(null);
      }
    });
    testView.textBox("nameTF").requireEmpty();
    testView.textBox("locationTF").requireEmpty();
    testView.textBox("fromTF").requireEmpty();
    testView.textBox("untilTF").requireEmpty();
    testView.textBox("ratePerParticipantTF").requireEmpty();
    testView.textBox("ratePerDayTF").requireEmpty();
    testView.label("idLbl").requireText((String) null);

    assertThat(editView.getNameOfCamp()).isEmpty();
    assertThat(editView.getLocationOfCamp()).isEmpty();
    assertThat(editView.getId()).isEqualTo(0);
    assertThat(editView.getFrom()).isNull();
    assertThat(editView.getUntil()).isNull();
    assertThat(editView.getRatePerParticipant()).isEmpty();
    assertThat(editView.getRatePerDay()).isEmpty();
  }

  @Test
  public void testGetName() {
    final JTextComponentFixture testObj = testView.textBox("nameTF");

    testObj.requireText(camp.getName());
    testObj.deleteText();
    testObj.enterText("OFZ");
    assertThat(editView.getNameOfCamp()).isEqualTo("OFZ");
  }

  @Test
  public void testGetLocation() {
    final JTextComponentFixture testObj = testView.textBox("locationTF");

    testObj.requireText(camp.getLocation());
    testObj.deleteText();
    testObj.enterText("Raversbeuren");
    assertThat(editView.getLocationOfCamp()).isEqualTo("Raversbeuren");
  }

  @Test
  public final void testGetFrom() throws ParseException {
    final JTextComponentFixture testObj = testView.textBox("fromTF");
    final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    testObj.requireText(sdf.format(camp.getFromDate()));
    testObj.deleteText();
    testObj.enterText("24.03.2002");
    assertThat(editView.getFrom()).isEqualTo(sdf.parse("24.03.2002"));
  }

  @Test
  public final void testGetTo() throws ParseException {
    final JTextComponentFixture testObj = testView.textBox("untilTF");
    final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    testObj.requireText(sdf.format(camp.getUntilDate()));
    testObj.deleteText();
    testObj.enterText("14.04.2002");
    assertThat(editView.getUntil()).isEqualTo(sdf.parse("14.04.2002"));
  }

  @Test
  public final void testGetRatePerParticipant() {
    final JTextComponentFixture testObj = testView.textBox("ratePerParticipantTF");

    testObj.requireText(camp.getRatePerParticipant());
    testObj.deleteText();
    testObj.enterText("1,50 EUR");
    assertThat(editView.getRatePerParticipant()).isEqualTo("1,50 EUR");
  }

  @Test
  public final void testGetRatePerDay() {
    final JTextComponentFixture testObj = testView.textBox("ratePerDayTF");

    testObj.requireText(camp.getRatePerDayChildren());
    testObj.deleteText();
    testObj.enterText("2,50 EUR");
    assertThat(editView.getRatePerDay()).isEqualTo("2,50 EUR");
  }

  @Test
  public final void testGetID() {
    final JLabelFixture testObj = testView.label("idLbl");
    testObj.requireText(String.valueOf(camp.getId()));
    assertThat(editView.getId()).isEqualTo(camp.getId());
  }

  @Test
  public final void testCancelDate() {
    final JLabelFixture testObj = testView.label("cancelledLbl");
    testObj.requireText((String) null);

    camp.setCancelDate(new Date(1234));
    testObj.requireText((String) null);
    updateCamp();
    testObj.requireText(Text.CAMP_CANCELLED_ON.text(new SimpleDateFormat().format(new Date(1234))));

    camp.setCancelDate(null);
    clear();
    updateCamp();
    testObj.requireText((String) null);
  }

  @Test
  public final void testParticipantList() {

    final Participant participant1 = createParticipant1();
    final Participant participant2 = createParticipant2();
    final Participant participant3 = createParticipant3();
    final Participant participant4 = createParticipant4();
    final Participant participant5 = createParticipant5();

    final List<Participant> participants =
        Arrays.asList(participant1, participant2, participant3, participant4, participant5);
    update(participants);

    requireParticipant(testView.table("participants"), 0, participant1);
    requireParticipant(testView.table("participants"), 1, participant2);
    requireParticipant(testView.table("participants"), 2, participant3);
    requireParticipant(testView.table("participants"), 3, participant4);
    requireParticipant(testView.table("participants"), 4, participant5);
  }

  @Test
  public final void testParticipantAdding() {

    final Participant participant1 = createParticipant1();
    final Participant participant2 = createParticipant2();
    final Participant participant3 = createParticipant3();
    final Participant participant4 = createParticipant4();
    final Participant participant5 = createParticipant5();

    final List<Participant> participants =
        Arrays.asList(participant1, participant2, participant3, participant4, participant5);
    update(participants);

    requireParticipant(testView.table("participants"), 0, participant1);
    requireParticipant(testView.table("participants"), 1, participant2);
    requireParticipant(testView.table("participants"), 2, participant3);
    requireParticipant(testView.table("participants"), 3, participant4);
    requireParticipant(testView.table("participants"), 4, participant5);
    requireParticipant(testView.table("campParticipants"), 0, new CampParticipant(participant));
    assertThat(editView.getCampParticipants()).isEqualTo(Arrays.asList(new CampParticipant(participant)));

    testView.table("participants").click(TableCell.row(2).column(2), MouseClickInfo.leftButton().times(2));
    requireParticipant(testView.table("participants"), 0, participant1);
    requireParticipant(testView.table("participants"), 1, participant2);
    requireParticipant(testView.table("participants"), 2, participant4);
    requireParticipant(testView.table("participants"), 3, participant5);
    requireParticipant(testView.table("campParticipants"), 0, new CampParticipant(participant));
    requireParticipant(testView.table("campParticipants"), 1, new CampParticipant(participant3));
    assertThat(editView.getCampParticipants())
        .isEqualTo(Arrays.asList(new CampParticipant(participant), new CampParticipant(participant3)));

    testView.table("participants").click(TableCell.row(2).column(0), MouseClickInfo.leftButton().times(2));
    requireParticipant(testView.table("participants"), 0, participant1);
    requireParticipant(testView.table("participants"), 1, participant2);
    requireParticipant(testView.table("participants"), 2, participant5);
    requireParticipant(testView.table("campParticipants"), 0, new CampParticipant(participant));
    requireParticipant(testView.table("campParticipants"), 1, new CampParticipant(participant3));
    requireParticipant(testView.table("campParticipants"), 2, new CampParticipant(participant4));
    assertThat(editView.getCampParticipants()).isEqualTo(Arrays.asList(new CampParticipant(participant),
                                                                       new CampParticipant(participant3),
                                                                       new CampParticipant(participant4)));

    testView.table("participants").click(TableCell.row(2).column(1), MouseClickInfo.leftButton().times(2));
    requireParticipant(testView.table("participants"), 0, participant1);
    requireParticipant(testView.table("participants"), 1, participant2);
    requireParticipant(testView.table("campParticipants"), 0, new CampParticipant(participant));
    requireParticipant(testView.table("campParticipants"), 1, new CampParticipant(participant3));
    requireParticipant(testView.table("campParticipants"), 2, new CampParticipant(participant4));
    requireParticipant(testView.table("campParticipants"), 3, new CampParticipant(participant5));
    assertThat(editView.getCampParticipants()).isEqualTo(Arrays.asList(new CampParticipant(participant),
                                                                       new CampParticipant(participant3),
                                                                       new CampParticipant(participant4),
                                                                       new CampParticipant(participant5)));

    clear();
    requireParticipant(testView.table("participants"), 0, participant1);
    requireParticipant(testView.table("participants"), 1, participant2);
    requireParticipant(testView.table("participants"), 2, participant3);
    requireParticipant(testView.table("participants"), 3, participant4);
    requireParticipant(testView.table("participants"), 4, participant5);
    assertThat(editView.getCampParticipants()).isEmpty();
    testView.table("campParticipants").requireRowCount(0);
  }


  @Test
  public final void testParticipantRemoving() {
    final Participant participant1 = createParticipant1();
    final Participant participant2 = createParticipant2();
    final Participant participant3 = createParticipant3();
    final Participant participant4 = createParticipant4();
    final Participant participant5 = createParticipant5();

    final List<Participant> participants =
        Arrays.asList(participant, participant1, participant2, participant3, participant4, participant5);
    update(participants);
    camp.addParticipant(new CampParticipant(participant1));
    camp.addParticipant(new CampParticipant(participant2));
    camp.addParticipant(new CampParticipant(participant3));
    camp.addParticipant(new CampParticipant(participant5));
    updateCamp();

    requireParticipant(testView.table("participants"), 0, participant4);
    requireParticipant(testView.table("campParticipants"), 0, new CampParticipant(participant));
    requireParticipant(testView.table("campParticipants"), 1, new CampParticipant(participant1));
    requireParticipant(testView.table("campParticipants"), 2, new CampParticipant(participant2));
    requireParticipant(testView.table("campParticipants"), 3, new CampParticipant(participant3));
    requireParticipant(testView.table("campParticipants"), 4, new CampParticipant(participant5));
    assertThat(editView.getCampParticipants()).isEqualTo(Arrays.asList(new CampParticipant(participant),
                                                                       new CampParticipant(participant1),
                                                                       new CampParticipant(participant2),
                                                                       new CampParticipant(participant3),
                                                                       new CampParticipant(participant5)));

    testView.table("campParticipants").click(TableCell.row(2).column(2), MouseClickInfo.leftButton().times(2));
    testView.optionPane().requireTitle(Text.USER_WARNING.text())
            .requireMessage(Text.CONTINUE_REMOVES_PARTICIPANT_FROM_CAMP).okButton().click();
    requireParticipant(testView.table("participants"), 0, participant2);
    requireParticipant(testView.table("participants"), 1, participant4);
    requireParticipant(testView.table("campParticipants"), 0, new CampParticipant(participant));
    requireParticipant(testView.table("campParticipants"), 1, new CampParticipant(participant1));
    requireParticipant(testView.table("campParticipants"), 2, new CampParticipant(participant3));
    requireParticipant(testView.table("campParticipants"), 3, new CampParticipant(participant5));
    assertThat(editView.getCampParticipants()).isEqualTo(Arrays.asList(new CampParticipant(participant),
                                                                       new CampParticipant(participant1),
                                                                       new CampParticipant(participant3),
                                                                       new CampParticipant(participant5)));

    testView.table("campParticipants").click(TableCell.row(2).column(0), MouseClickInfo.leftButton().times(2));
    testView.optionPane().requireTitle(Text.USER_WARNING.text())
            .requireMessage(Text.CONTINUE_REMOVES_PARTICIPANT_FROM_CAMP).okButton().click();
    requireParticipant(testView.table("participants"), 0, participant2);
    requireParticipant(testView.table("participants"), 1, participant3);
    requireParticipant(testView.table("participants"), 2, participant4);
    requireParticipant(testView.table("campParticipants"), 0, new CampParticipant(participant));
    requireParticipant(testView.table("campParticipants"), 1, new CampParticipant(participant1));
    requireParticipant(testView.table("campParticipants"), 2, new CampParticipant(participant5));
    assertThat(editView.getCampParticipants()).isEqualTo(Arrays.asList(new CampParticipant(participant),
                                                                       new CampParticipant(participant1),
                                                                       new CampParticipant(participant5)));

    testView.table("campParticipants").click(TableCell.row(0).column(1), MouseClickInfo.leftButton().times(2));
    testView.optionPane().requireTitle(Text.USER_WARNING.text())
            .requireMessage(Text.CONTINUE_REMOVES_PARTICIPANT_FROM_CAMP).cancelButton().click();
    requireParticipant(testView.table("participants"), 0, participant2);
    requireParticipant(testView.table("participants"), 1, participant3);
    requireParticipant(testView.table("participants"), 2, participant4);
    requireParticipant(testView.table("campParticipants"), 0, new CampParticipant(participant));
    requireParticipant(testView.table("campParticipants"), 1, new CampParticipant(participant1));
    requireParticipant(testView.table("campParticipants"), 2, new CampParticipant(participant5));
    assertThat(editView.getCampParticipants()).isEqualTo(Arrays.asList(new CampParticipant(participant),
                                                                       new CampParticipant(participant1),
                                                                       new CampParticipant(participant5)));

    testView.table("campParticipants").click(TableCell.row(0).column(1), MouseClickInfo.leftButton().times(2));
    testView.optionPane().requireTitle(Text.USER_WARNING.text())
            .requireMessage(Text.CONTINUE_REMOVES_PARTICIPANT_FROM_CAMP).okButton().click();
    requireParticipant(testView.table("participants"), 0, participant);
    requireParticipant(testView.table("participants"), 1, participant2);
    requireParticipant(testView.table("participants"), 2, participant3);
    requireParticipant(testView.table("participants"), 3, participant4);
    requireParticipant(testView.table("campParticipants"), 0, new CampParticipant(participant1));
    requireParticipant(testView.table("campParticipants"), 1, new CampParticipant(participant5));
    assertThat(editView.getCampParticipants())
        .isEqualTo(Arrays.asList(new CampParticipant(participant1), new CampParticipant(participant5)));

    testView.table("campParticipants").click(TableCell.row(1).column(0), MouseClickInfo.leftButton().times(2));
    testView.optionPane().requireTitle(Text.USER_WARNING.text())
            .requireMessage(Text.CONTINUE_REMOVES_PARTICIPANT_FROM_CAMP).okButton().click();
    requireParticipant(testView.table("participants"), 0, participant);
    requireParticipant(testView.table("participants"), 1, participant2);
    requireParticipant(testView.table("participants"), 2, participant3);
    requireParticipant(testView.table("participants"), 3, participant4);
    requireParticipant(testView.table("participants"), 4, participant5);
    requireParticipant(testView.table("campParticipants"), 0, new CampParticipant(participant1));
    assertThat(editView.getCampParticipants()).isEqualTo(Arrays.asList(new CampParticipant(participant1)));

    testView.table("campParticipants").click(TableCell.row(0).column(0), MouseClickInfo.leftButton().times(2));
    testView.optionPane().requireTitle(Text.USER_WARNING.text())
            .requireMessage(Text.CONTINUE_REMOVES_PARTICIPANT_FROM_CAMP).okButton().click();
    requireParticipant(testView.table("participants"), 0, participant);
    requireParticipant(testView.table("participants"), 1, participant1);
    requireParticipant(testView.table("participants"), 2, participant2);
    requireParticipant(testView.table("participants"), 3, participant3);
    requireParticipant(testView.table("participants"), 4, participant4);
    requireParticipant(testView.table("participants"), 5, participant5);
    assertThat(editView.getCampParticipants()).isEmpty();

    clear();
    requireParticipant(testView.table("participants"), 0, participant);
    requireParticipant(testView.table("participants"), 1, participant1);
    requireParticipant(testView.table("participants"), 2, participant2);
    requireParticipant(testView.table("participants"), 3, participant3);
    requireParticipant(testView.table("participants"), 4, participant4);
    requireParticipant(testView.table("participants"), 5, participant5);
    assertThat(editView.getCampParticipants()).isEmpty();
    testView.table("campParticipants").requireRowCount(0);
  }

  @Test
  public final void testCampParticipantList() {

    final Participant participant1 = createParticipant1();
    final Participant participant2 = createParticipant2();
    final Participant participant3 = createParticipant3();
    final Participant participant4 = createParticipant4();
    final Participant participant5 = createParticipant5();

    final CampParticipant campParticipant = new CampParticipant(participant1);
    campParticipant.setRole(Role.DIRECTION);
    camp.addParticipant(campParticipant);
    camp.addParticipant(new CampParticipant(participant2));
    camp.addParticipant(new CampParticipant(participant3));
    camp.addParticipant(new CampParticipant(participant4));
    camp.addParticipant(new CampParticipant(participant5));

    setCamp();

    requireParticipant(testView.table("campParticipants"), 0, new CampParticipant(participant));
    requireParticipant(testView.table("campParticipants"), 1, campParticipant);
    requireParticipant(testView.table("campParticipants"), 2, new CampParticipant(participant2));
    requireParticipant(testView.table("campParticipants"), 3, new CampParticipant(participant3));
    requireParticipant(testView.table("campParticipants"), 4, new CampParticipant(participant4));
    requireParticipant(testView.table("campParticipants"), 5, new CampParticipant(participant5));

    final CampParticipant cParticipant = new CampParticipant(participant1);
    campParticipant.setRole(Role.STAFF);
    camp.removeParticipant(cParticipant);
    setCamp();
    requireParticipant(testView.table("campParticipants"), 0, new CampParticipant(participant));
    requireParticipant(testView.table("campParticipants"), 1, new CampParticipant(participant2));
    requireParticipant(testView.table("campParticipants"), 2, new CampParticipant(participant3));
    requireParticipant(testView.table("campParticipants"), 3, new CampParticipant(participant4));
    requireParticipant(testView.table("campParticipants"), 4, new CampParticipant(participant5));

    assertThat(editView.getCampParticipants()).isEqualTo(camp.getParticipants());
  }

  private void setCamp() {
    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setCamp(camp);
      }
    });
  }

  private void update(final List<Participant> participants) {
    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.update(participants);
      }
    });
  }

  private Participant createParticipant1() {
    final Participant participant1 = new Participant("Mustermann",
                                                     "Max",
                                                     Gender.MALE,
                                                     Denomination.OTHER,
                                                     new Date(),
                                                     "Musterstrasse 12",
                                                     12345,
                                                     "Musterhausen",
                                                     CountyCouncil.OTHER);

    final Date d1 = new Date(123);

    participant1.setBank("bank");
    participant1.setBankAccountNumber(1);
    participant1.setBankCodeNumber(12);
    participant1.setComment("comment");
    participant1.setDateSinceInDataBase(d1);
    participant1.setDateUpToInSystem(d1);
    participant1.setDenomination(Denomination.JEWISH);
    participant1.setFax("fax");
    participant1.setMailAddress("mail");
    participant1.setMobilePhone("mobile");
    participant1.setPhone("phone");
    participant1.setPhoneOfParents("phone");
    participant1.setCityPostal("city");
    participant1.setPostCodePostal(3124);
    participant1.setStreetPostal("street");
    return participant1;
  }

  private Participant createParticipant2() {
    return new Participant("Schmidt",
                           "Hans",
                           Gender.MALE,
                           Denomination.NONE,
                           new Date(1200),
                           "Strasse 4",
                           56789,
                           "Stadt",
                           CountyCouncil.CITY_NEUSTADT);
  }

  private Participant createParticipant3() {
    return new Participant("Müller",
                           "Jasmin",
                           Gender.FEMALE,
                           Denomination.CATHOLIC,
                           new Date(6789543),
                           "Mittelgasse 3",
                           54321,
                           "Schimmelhausen",
                           CountyCouncil.CITY_ZWEIBRUECKEN);
  }

  private Participant createParticipant4() {
    return new Participant("Mauer",
                           "Jaqueline",
                           Gender.FEMALE,
                           Denomination.JEWISH,
                           new Date(1297579),
                           "Hinterweg 12",
                           99384,
                           "Hilgen",
                           CountyCouncil.UNKNOWN);
  }

  private Participant createParticipant5() {
    return new Participant("Bauer",
                           "Andreas",
                           Gender.MALE,
                           Denomination.EVANGELIC,
                           new Date(9876543),
                           "Julgenweg 76",
                           21228,
                           "Mildeningen",
                           CountyCouncil.CITY_NEUSTADT);
  }
}
