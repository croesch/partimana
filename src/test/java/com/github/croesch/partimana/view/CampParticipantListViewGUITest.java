package com.github.croesch.partimana.view;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.PartiManaDefaultGUITestCase;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.types.*;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.SwingUtilities;
import org.assertj.swing.core.MouseClickInfo;
import org.assertj.swing.data.TableCell;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.Containers;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.assertj.swing.fixture.JTableFixture;
import org.junit.Test;

/**
 * Provides gui tests for {@link CampParticipantListView}
 *
 * @author croesch
 * @since Date: Sep 16, 2012
 */
public class CampParticipantListViewGUITest extends PartiManaDefaultGUITestCase {

  private CampParticipantListView listView;

  private JPanelFixture testView;

  private CampParticipant campParticipant1;

  private CampParticipant campParticipant2;

  private CampParticipant campParticipant3;

  private CampParticipant campParticipant4;

  private CampParticipant campParticipant5;

  @Override
  protected void before() {
    final CampParticipantListView view = GuiActionRunner.execute(new GuiQuery<CampParticipantListView>() {
      @Override
      protected CampParticipantListView executeInEDT() throws Throwable {
        return new CampParticipantListView(null, CampParticipantListViewGUITest.this);
      }
    });

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

    final Participant p2 = new Participant("Schmidt",
                                           "Hans",
                                           Gender.MALE,
                                           Denomination.NONE,
                                           new Date(1200),
                                           "Strasse 4",
                                           56789,
                                           "Stadt",
                                           CountyCouncil.CITY_NEUSTADT);
    campParticipant2 = new CampParticipant(p2);

    final Participant p3 = new Participant("Müller",
                                           "Jasmin",
                                           Gender.FEMALE,
                                           Denomination.CATHOLIC,
                                           new Date(6789543),
                                           "Mittelgasse 3",
                                           54321,
                                           "Schimmelhausen",
                                           CountyCouncil.CITY_ZWEIBRUECKEN);
    campParticipant3 = new CampParticipant(p3);

    final Participant p4 = new Participant("Mauer",
                                           "Jaqueline",
                                           Gender.FEMALE,
                                           Denomination.JEWISH,
                                           new Date(1297579),
                                           "Hinterweg 12",
                                           99384,
                                           "Hilgen",
                                           CountyCouncil.UNKNOWN);
    campParticipant4 = new CampParticipant(p4);

    final Participant p5 = new Participant("Bauer",
                                           "Andreas",
                                           Gender.MALE,
                                           Denomination.EVANGELIC,
                                           new Date(9876543),
                                           "Julgenweg 76",
                                           21228,
                                           "Mildeningen",
                                           CountyCouncil.CITY_NEUSTADT);
    campParticipant5 = new CampParticipant(p5);

    campParticipant1 = new CampParticipant(participant1);

    view.setName("listView");
    listView = view;

    final FrameFixture window = new FrameFixture(robot(), Containers.frameFor(view));
    window.target().setPreferredSize(new Dimension(800, 400));
    window.show();
    testView = window.panel("listView");
  }

  @Override
  protected void after() {
    cleanUp();
  }

  @Test
  public final void testUpdateListOfParticipant() throws InterruptedException, InvocationTargetException {
    final ArrayList<CampParticipant> list = new ArrayList<CampParticipant>();
    list.add(campParticipant1);
    update(list);
    requireParticipant(testView.table("campParticipants"), 0, campParticipant1);
  }

  private void update(final ArrayList<CampParticipant> list) throws InterruptedException, InvocationTargetException {
    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        listView.update(list);
      }
    });
  }

  @Test
  public final void testTableEditable() throws InterruptedException, InvocationTargetException {
    final ArrayList<CampParticipant> list = new ArrayList<CampParticipant>();
    list.add(campParticipant1);
    update(list);
    testView.table("campParticipants").requireNotEditable(TableCell.row(0).column(0));
    testView.table("campParticipants").requireNotEditable(TableCell.row(0).column(1));
    testView.table("campParticipants").requireNotEditable(TableCell.row(0).column(2));
  }

  @Test
  public final void testSelection() throws InterruptedException, InvocationTargetException {
    final ArrayList<CampParticipant> list = new ArrayList<CampParticipant>();
    list.add(campParticipant1);
    update(list);
    testView.table("campParticipants").selectRows(0);
    testView.table("campParticipants").requireSelectedRows(0);
    assertThat(listView.getSelectedElementId()).isEqualTo(campParticipant1.getId());

    testView.table("campParticipants").selectCell(TableCell.row(0).column(0));
    assertThat(testView.table("campParticipants").target().isCellSelected(0, 1)).isTrue();
  }

  @Test
  public final void testSorting_Id() throws InterruptedException, InvocationTargetException {
    final int id = pIds.poll();
    final Participant p1 = new Participant(id,
                                           "Schmidt",
                                           "Hans",
                                           Gender.MALE,
                                           Denomination.NONE,
                                           new Date(1200),
                                           "Strasse 4",
                                           56789,
                                           "Stadt",
                                           CountyCouncil.CITY_NEUSTADT);
    final Participant p2 = new Participant(id + 1,
                                           "Müller",
                                           "Jasmin",
                                           Gender.FEMALE,
                                           Denomination.CATHOLIC,
                                           new Date(6789543),
                                           "Mittelgasse 3",
                                           54321,
                                           "Schimmelhausen",
                                           CountyCouncil.CITY_ZWEIBRUECKEN);
    final Participant p3 = new Participant(id + 2,
                                           "Mauer",
                                           "Jaqueline",
                                           Gender.FEMALE,
                                           Denomination.JEWISH,
                                           new Date(1297579),
                                           "Hinterweg 12",
                                           99384,
                                           "Hilgen",
                                           CountyCouncil.UNKNOWN);

    final CampParticipant cp1 = new CampParticipant(p1);
    final CampParticipant cp2 = new CampParticipant(p2);
    final CampParticipant cp3 = new CampParticipant(p3);

    final ArrayList<CampParticipant> list = new ArrayList<CampParticipant>();
    list.add(cp1);
    list.add(cp2);
    list.add(cp3);
    update(list);

    testView.table("campParticipants").requireRowCount(3);
    requireParticipant(testView.table("campParticipants"), 0, cp1);
    requireParticipant(testView.table("campParticipants"), 1, cp2);
    requireParticipant(testView.table("campParticipants"), 2, cp3);

    testView.table("campParticipants").tableHeader().clickColumn(0);
    requireParticipant(testView.table("campParticipants"), 0, cp3);
    requireParticipant(testView.table("campParticipants"), 1, cp2);
    requireParticipant(testView.table("campParticipants"), 2, cp1);

    testView.table("campParticipants").click();
    testView.table("campParticipants").tableHeader().clickColumn(0);
    requireParticipant(testView.table("campParticipants"), 0, cp1);
    requireParticipant(testView.table("campParticipants"), 1, cp2);
    requireParticipant(testView.table("campParticipants"), 2, cp3);
  }

  @Test
  public final void testSorting_FirstName() throws InterruptedException, InvocationTargetException {
    final ArrayList<CampParticipant> list = new ArrayList<CampParticipant>();
    list.add(campParticipant1);
    list.add(campParticipant2);
    list.add(campParticipant3);
    list.add(campParticipant4);
    list.add(campParticipant5);
    update(list);

    testView.table("campParticipants").requireRowCount(5);
    requireParticipant(testView.table("campParticipants"), 0, campParticipant1);
    requireParticipant(testView.table("campParticipants"), 1, campParticipant2);
    requireParticipant(testView.table("campParticipants"), 2, campParticipant3);
    requireParticipant(testView.table("campParticipants"), 3, campParticipant4);
    requireParticipant(testView.table("campParticipants"), 4, campParticipant5);

    testView.table("campParticipants").tableHeader().clickColumn(1);
    requireParticipant(testView.table("campParticipants"), 4, campParticipant1);
    requireParticipant(testView.table("campParticipants"), 1, campParticipant2);
    requireParticipant(testView.table("campParticipants"), 3, campParticipant3);
    requireParticipant(testView.table("campParticipants"), 2, campParticipant4);
    requireParticipant(testView.table("campParticipants"), 0, campParticipant5);

    testView.table("campParticipants").click();
    testView.table("campParticipants").tableHeader().clickColumn(1);
    requireParticipant(testView.table("campParticipants"), 0, campParticipant1);
    requireParticipant(testView.table("campParticipants"), 3, campParticipant2);
    requireParticipant(testView.table("campParticipants"), 1, campParticipant3);
    requireParticipant(testView.table("campParticipants"), 2, campParticipant4);
    requireParticipant(testView.table("campParticipants"), 4, campParticipant5);
  }

  @Test
  public final void testSorting_LastName() throws InterruptedException, InvocationTargetException {
    final ArrayList<CampParticipant> list = new ArrayList<CampParticipant>();
    list.add(campParticipant1);
    list.add(campParticipant2);
    list.add(campParticipant3);
    list.add(campParticipant4);
    list.add(campParticipant5);
    update(list);

    testView.table("campParticipants").requireRowCount(5);
    requireParticipant(testView.table("campParticipants"), 0, campParticipant1);
    requireParticipant(testView.table("campParticipants"), 1, campParticipant2);
    requireParticipant(testView.table("campParticipants"), 2, campParticipant3);
    requireParticipant(testView.table("campParticipants"), 3, campParticipant4);
    requireParticipant(testView.table("campParticipants"), 4, campParticipant5);

    testView.table("campParticipants").tableHeader().clickColumn(2);
    requireParticipant(testView.table("campParticipants"), 3, campParticipant1);
    requireParticipant(testView.table("campParticipants"), 4, campParticipant2);
    requireParticipant(testView.table("campParticipants"), 2, campParticipant3);
    requireParticipant(testView.table("campParticipants"), 1, campParticipant4);
    requireParticipant(testView.table("campParticipants"), 0, campParticipant5);

    testView.table("campParticipants").click();
    testView.table("campParticipants").tableHeader().clickColumn(2);
    requireParticipant(testView.table("campParticipants"), 1, campParticipant1);
    requireParticipant(testView.table("campParticipants"), 0, campParticipant2);
    requireParticipant(testView.table("campParticipants"), 2, campParticipant3);
    requireParticipant(testView.table("campParticipants"), 3, campParticipant4);
    requireParticipant(testView.table("campParticipants"), 4, campParticipant5);
  }

  @Test
  public final void testValueChanging() throws InterruptedException, InvocationTargetException {
    final ArrayList<CampParticipant> list = new ArrayList<CampParticipant>();
    list.add(campParticipant1);
    list.add(campParticipant2);
    list.add(campParticipant3);
    list.add(campParticipant4);
    list.add(campParticipant5);
    update(list);

    testView.table("campParticipants").requireRowCount(5);
    slct(testView.table("campParticipants"), 0, campParticipant1);
    slct(testView.table("campParticipants"), 1, campParticipant2);
    slct(testView.table("campParticipants"), 2, campParticipant3);
    slct(testView.table("campParticipants"), 3, campParticipant4);
    slct(testView.table("campParticipants"), 4, campParticipant5);

    testView.table("campParticipants").tableHeader().clickColumn(1);
    slct(testView.table("campParticipants"), 4, campParticipant1);
    slct(testView.table("campParticipants"), 1, campParticipant2);
    slct(testView.table("campParticipants"), 3, campParticipant3);
    slct(testView.table("campParticipants"), 2, campParticipant4);
    slct(testView.table("campParticipants"), 0, campParticipant5);

    testView.table("campParticipants").click();
    testView.table("campParticipants").tableHeader().clickColumn(1);
    slct(testView.table("campParticipants"), 0, campParticipant1);
    slct(testView.table("campParticipants"), 3, campParticipant2);
    slct(testView.table("campParticipants"), 1, campParticipant3);
    slct(testView.table("campParticipants"), 2, campParticipant4);
    slct(testView.table("campParticipants"), 4, campParticipant5);
  }

  private void slct(final JTableFixture table, final int row, final CampParticipant cp) {
    requireParticipant(table, row, cp);

    table.enterValue(TableCell.row(row).column(3), Role.DIRECTION.toString());
    assertThat(cp.getRole()).isEqualTo(Role.DIRECTION);
    table.enterValue(TableCell.row(row).column(3), Role.KITCHEN_STAFF.toString());
    assertThat(cp.getRole()).isEqualTo(Role.KITCHEN_STAFF);
    table.enterValue(TableCell.row(row).column(3), Role.STAFF.toString());
    assertThat(cp.getRole()).isEqualTo(Role.STAFF);
    table.enterValue(TableCell.row(row).column(3), Role.PARTICIPANT.toString());
    assertThat(cp.getRole()).isEqualTo(Role.PARTICIPANT);
  }

  @Test
  public final void testSelection_CountOfRows() throws InterruptedException, InvocationTargetException {
    final ArrayList<CampParticipant> list = new ArrayList<CampParticipant>();
    list.add(campParticipant1);
    list.add(campParticipant2);
    list.add(campParticipant3);
    list.add(campParticipant4);
    list.add(campParticipant5);
    update(list);

    testView.table("campParticipants").selectRows(1, 2, 3);
    testView.table("campParticipants").requireSelectedRows(3);
    assertThat(testView.table("campParticipants").target().getSelectedRowCount()).isEqualTo(1);
    assertThat(listView.getSelectedElementId()).isEqualTo(campParticipant4.getId());
  }

  @Test
  public final void testSelection_MultipleUpdate() throws InterruptedException, InvocationTargetException {
    final ArrayList<CampParticipant> list = new ArrayList<CampParticipant>();
    list.add(campParticipant1);
    list.add(campParticipant2);
    list.add(campParticipant3);
    list.add(campParticipant4);
    list.add(campParticipant5);
    update(list);

    testView.table("campParticipants").requireRowCount(5);
    requireParticipant(testView.table("campParticipants"), 0, campParticipant1);
    requireParticipant(testView.table("campParticipants"), 1, campParticipant2);
    requireParticipant(testView.table("campParticipants"), 2, campParticipant3);
    requireParticipant(testView.table("campParticipants"), 3, campParticipant4);
    requireParticipant(testView.table("campParticipants"), 4, campParticipant5);

    list.remove(campParticipant4);
    list.remove(campParticipant3);
    update(list);

    testView.table("campParticipants").requireRowCount(3);
    requireParticipant(testView.table("campParticipants"), 0, campParticipant1);
    requireParticipant(testView.table("campParticipants"), 1, campParticipant2);
    requireParticipant(testView.table("campParticipants"), 2, campParticipant5);

    list.remove(campParticipant1);
    list.remove(campParticipant5);
    update(list);

    testView.table("campParticipants").requireRowCount(1);
    requireParticipant(testView.table("campParticipants"), 0, campParticipant2);

    list.add(campParticipant1);
    list.add(campParticipant3);
    list.add(campParticipant4);
    list.add(campParticipant5);
    update(list);

    testView.table("campParticipants").requireRowCount(5);
    requireParticipant(testView.table("campParticipants"), 0, campParticipant1);
    requireParticipant(testView.table("campParticipants"), 1, campParticipant2);
    requireParticipant(testView.table("campParticipants"), 2, campParticipant3);
    requireParticipant(testView.table("campParticipants"), 3, campParticipant4);
    requireParticipant(testView.table("campParticipants"), 4, campParticipant5);
  }

  @Test
  public final void testGetSelectedParticipantId() throws InterruptedException, InvocationTargetException {
    final ArrayList<CampParticipant> list = new ArrayList<CampParticipant>();
    list.add(campParticipant1);
    list.add(campParticipant2);
    list.add(campParticipant3);
    list.add(campParticipant4);
    list.add(campParticipant5);
    update(list);

    testView.table("campParticipants").selectRows(1);
    assertThat(listView.getSelectedElementId()).isEqualTo(campParticipant2.getId());

    testView.table("campParticipants").selectRows(2);
    assertThat(listView.getSelectedElementId()).isEqualTo(campParticipant3.getId());

    testView.table("campParticipants").selectRows(4);
    assertThat(listView.getSelectedElementId()).isEqualTo(campParticipant5.getId());

    testView.table("campParticipants").pressKey(KeyEvent.VK_CONTROL).pressAndReleaseKeys(KeyEvent.VK_SPACE)
            .releaseKey(KeyEvent.VK_CONTROL);
    testView.table("campParticipants").requireNoSelection();
    assertThat(listView.getSelectedElementId()).isZero();
  }

  @Test
  public final void testSelection_EventAfterDoubleClick() throws InterruptedException, InvocationTargetException {
    final ArrayList<CampParticipant> list = new ArrayList<CampParticipant>();
    list.add(campParticipant1);
    list.add(campParticipant2);
    list.add(campParticipant3);
    list.add(campParticipant4);
    list.add(campParticipant5);
    update(list);

    assertNoActionPerformed();
    testView.table("campParticipants").cell(TableCell.row(2).column(1)).doubleClick();
    assertThat(poll()).isEqualTo(UserAction.CAMP_PARTICIPANT_SELECTED);

    assertNoActionPerformed();
    testView.table("campParticipants").cell(TableCell.row(0).column(0)).click(MouseClickInfo.middleButton().times(1));

    assertNoActionPerformed();
    testView.table("campParticipants").cell(TableCell.row(0).column(1)).click(MouseClickInfo.middleButton().times(2));

    assertNoActionPerformed();
    testView.table("campParticipants").cell(TableCell.row(0).column(2)).click(MouseClickInfo.middleButton().times(3));

    assertNoActionPerformed();
    testView.table("campParticipants").cell(TableCell.row(1).column(0)).click(MouseClickInfo.rightButton().times(1));

    assertNoActionPerformed();
    testView.table("campParticipants").cell(TableCell.row(1).column(1)).click(MouseClickInfo.rightButton().times(2));

    assertNoActionPerformed();
    testView.table("campParticipants").cell(TableCell.row(1).column(2)).click(MouseClickInfo.rightButton().times(3));

    assertNoActionPerformed();
    testView.table("campParticipants").cell(TableCell.row(2).column(0)).click(MouseClickInfo.leftButton().times(1));

    assertNoActionPerformed();
    testView.table("campParticipants").cell(TableCell.row(2).column(1)).click(MouseClickInfo.leftButton().times(2));
    assertThat(poll()).isEqualTo(UserAction.CAMP_PARTICIPANT_SELECTED);

    assertNoActionPerformed();
    testView.table("campParticipants").cell(TableCell.row(2).column(2)).click(MouseClickInfo.leftButton().times(3));
    assertThat(poll()).isEqualTo(UserAction.CAMP_PARTICIPANT_SELECTED);

    assertNoActionPerformed();
    testView.table("campParticipants").cell(TableCell.row(2).column(1)).click(MouseClickInfo.leftButton().times(16));
    assertThat(poll()).isEqualTo(UserAction.CAMP_PARTICIPANT_SELECTED);

    assertNoActionPerformed();
    testView.table("campParticipants").cell(TableCell.row(4).column(0)).click(MouseClickInfo.leftButton().times(2));
    assertThat(poll()).isEqualTo(UserAction.CAMP_PARTICIPANT_SELECTED);

    assertNoActionPerformed();
    testView.table("campParticipants").cell(TableCell.row(1).column(2)).click(MouseClickInfo.leftButton().times(2));
    assertThat(poll()).isEqualTo(UserAction.CAMP_PARTICIPANT_SELECTED);
  }

  public static void requireParticipant(final JTableFixture table, final int row, final CampParticipant p) {
    table.requireCellValue(TableCell.row(row).column(0), String.valueOf(p.getId()));
    requireParticipant(table, row, p.getForeName(), p.getLastName());
    table.requireCellValue(TableCell.row(row).column(3), String.valueOf(p.getRole()));
  }

  public static void requireParticipant(final JTableFixture table,
                                        final int row,
                                        final String fName,
                                        final String lName) {
    table.requireCellValue(TableCell.row(row).column(1), fName);
    table.requireCellValue(TableCell.row(row).column(2), lName);
  }

  @Test
  public final void testGetElementIds() throws InterruptedException, InvocationTargetException {
    assertThat(listView.getElementIds()).isEmpty();

    final ArrayList<CampParticipant> list = new ArrayList<CampParticipant>();
    list.add(campParticipant1);
    list.add(campParticipant2);
    list.add(campParticipant3);
    list.add(campParticipant4);
    list.add(campParticipant5);
    update(list);

    assertThat(listView.getElementIds()).containsOnly(campParticipant1.getId(),
                                                      campParticipant2.getId(),
                                                      campParticipant3.getId(),
                                                      campParticipant4.getId(),
                                                      campParticipant5.getId());

    list.remove(campParticipant4);
    update(list);

    assertThat(listView.getElementIds()).containsOnly(campParticipant1.getId(),
                                                      campParticipant2.getId(),
                                                      campParticipant3.getId(),
                                                      campParticipant5.getId());
  }
}
