package com.github.croesch.partimana.view;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.PartiManaDefaultGUITestCase;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;
import com.github.croesch.partimana.view.api.IParticipantEditView;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.assertj.swing.core.MouseClickInfo;
import org.assertj.swing.data.TableCell;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.edt.GuiTask;
import org.assertj.swing.fixture.Containers;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.assertj.swing.fixture.JTableFixture;
import org.junit.Test;

/**
 * Provides gui tests for {@link ParticipantListView}
 *
 * @author croesch
 * @since Date: Jul 10, 2011
 */
public class ParticipantListViewGUITest extends PartiManaDefaultGUITestCase {

  private ParticipantListView listView;

  private JPanelFixture testView;

  private Participant participant1;

  private Participant participant2;

  private Participant participant3;

  private Participant participant4;

  private Participant participant5;

  /**
   * Sets up the {@link IParticipantEditView} and the {@link JPanelFixture} to test it. Will add a participant1 to it to
   * test the functionality.
   *
   * @since Date: Jun 26, 2011
   */
  @Override
  protected void before() {
    final ParticipantListView view = GuiActionRunner.execute(new GuiQuery<ParticipantListView>() {
      @Override
      protected ParticipantListView executeInEDT() throws Throwable {
        return new ParticipantListView(null, ParticipantListViewGUITest.this);
      }
    });

    participant1 = new Participant("Mustermann",
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

    participant2 = new Participant("Schmidt",
                                        "Hans",
                                        Gender.MALE,
                                        Denomination.NONE,
                                        new Date(1200),
                                        "Strasse 4",
                                        56789,
                                        "Stadt",
                                        CountyCouncil.CITY_NEUSTADT);

    participant3 = new Participant("Müller",
                                        "Jasmin",
                                        Gender.FEMALE,
                                        Denomination.CATHOLIC,
                                        new Date(6789543),
                                        "Mittelgasse 3",
                                        54321,
                                        "Schimmelhausen",
                                        CountyCouncil.CITY_ZWEIBRUECKEN);

    participant4 = new Participant("Mauer",
                                        "Jaqueline",
                                        Gender.FEMALE,
                                        Denomination.JEWISH,
                                        new Date(1297579),
                                        "Hinterweg 12",
                                        99384,
                                        "Hilgen",
                                        CountyCouncil.UNKNOWN);

    participant5 = new Participant("Bauer",
                                        "Andreas",
                                        Gender.MALE,
                                        Denomination.EVANGELIC,
                                        new Date(9876543),
                                        "Julgenweg 76",
                                        21228,
                                        "Mildeningen",
                                        CountyCouncil.CITY_NEUSTADT);

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
    final ArrayList<Participant> list = new ArrayList<Participant>();
    list.add(participant1);
    update(listView, list);
    requireParticipant(testView.table(), 0, participant1);
  }

  @Test
  public final void testTableEditable() throws InterruptedException, InvocationTargetException {
    final ArrayList<Participant> list = new ArrayList<Participant>();
    list.add(participant1);
    update(listView, list);
    testView.table().requireNotEditable(TableCell.row(0).column(0));
    testView.table().requireNotEditable(TableCell.row(0).column(1));
    testView.table().requireNotEditable(TableCell.row(0).column(2));
  }

  @Test
  public final void testSelection() throws InterruptedException, InvocationTargetException {
    final ArrayList<Participant> list = new ArrayList<Participant>();
    list.add(participant1);
    update(listView, list);
    testView.table().selectRows(0);
    testView.table().requireSelectedRows(0);
    assertThat(listView.getSelectedElementId()).isEqualTo(participant1.getId());

    testView.table().selectCell(TableCell.row(0).column(0));
    assertThat(testView.table().target().isCellSelected(0, 1)).isTrue();
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

    final ArrayList<Participant> list = new ArrayList<Participant>();
    list.add(p1);
    list.add(p2);
    list.add(p3);
    update(listView, list);

    testView.table().requireRowCount(3);
    requireParticipant(testView.table(), 0, p1);
    requireParticipant(testView.table(), 1, p2);
    requireParticipant(testView.table(), 2, p3);

    testView.table().tableHeader().clickColumn(0);
    requireParticipant(testView.table(), 0, p3);
    requireParticipant(testView.table(), 1, p2);
    requireParticipant(testView.table(), 2, p1);

    testView.table().click();
    testView.table().tableHeader().clickColumn(0);
    requireParticipant(testView.table(), 0, p1);
    requireParticipant(testView.table(), 1, p2);
    requireParticipant(testView.table(), 2, p3);
  }

  @Test
  public final void testSorting_FirstName() throws InterruptedException, InvocationTargetException {
    final ArrayList<Participant> list = new ArrayList<Participant>();
    list.add(participant1);
    list.add(participant2);
    list.add(participant3);
    list.add(participant4);
    list.add(participant5);
    update(listView, list);

    testView.table().requireRowCount(5);
    requireParticipant(testView.table(), 0, participant1);
    requireParticipant(testView.table(), 1, participant2);
    requireParticipant(testView.table(), 2, participant3);
    requireParticipant(testView.table(), 3, participant4);
    requireParticipant(testView.table(), 4, participant5);

    testView.table().tableHeader().clickColumn(1);
    requireParticipant(testView.table(), 4, participant1);
    requireParticipant(testView.table(), 1, participant2);
    requireParticipant(testView.table(), 3, participant3);
    requireParticipant(testView.table(), 2, participant4);
    requireParticipant(testView.table(), 0, participant5);

    testView.table().click();
    testView.table().tableHeader().clickColumn(1);
    requireParticipant(testView.table(), 0, participant1);
    requireParticipant(testView.table(), 3, participant2);
    requireParticipant(testView.table(), 1, participant3);
    requireParticipant(testView.table(), 2, participant4);
    requireParticipant(testView.table(), 4, participant5);
  }

  @Test
  public final void testSorting_LastName() throws InterruptedException, InvocationTargetException {
    final ArrayList<Participant> list = new ArrayList<Participant>();
    list.add(participant1);
    list.add(participant2);
    list.add(participant3);
    list.add(participant4);
    list.add(participant5);
    update(listView, list);

    testView.table().requireRowCount(5);
    requireParticipant(testView.table(), 0, participant1);
    requireParticipant(testView.table(), 1, participant2);
    requireParticipant(testView.table(), 2, participant3);
    requireParticipant(testView.table(), 3, participant4);
    requireParticipant(testView.table(), 4, participant5);

    testView.table().tableHeader().clickColumn(2);
    requireParticipant(testView.table(), 3, participant1);
    requireParticipant(testView.table(), 4, participant2);
    requireParticipant(testView.table(), 2, participant3);
    requireParticipant(testView.table(), 1, participant4);
    requireParticipant(testView.table(), 0, participant5);

    testView.table().click();
    testView.table().tableHeader().clickColumn(2);
    requireParticipant(testView.table(), 1, participant1);
    requireParticipant(testView.table(), 0, participant2);
    requireParticipant(testView.table(), 2, participant3);
    requireParticipant(testView.table(), 3, participant4);
    requireParticipant(testView.table(), 4, participant5);
  }

  @Test
  public final void testSelection_CountOfRows() throws InterruptedException, InvocationTargetException {
    final ArrayList<Participant> list = new ArrayList<Participant>();
    list.add(participant1);
    list.add(participant2);
    list.add(participant3);
    list.add(participant4);
    list.add(participant5);
    update(listView, list);

    testView.table().selectRows(1, 2, 3);
    testView.table().requireSelectedRows(3);
    assertThat(testView.table().target().getSelectedRowCount()).isEqualTo(1);
    assertThat(listView.getSelectedElementId()).isEqualTo(participant4.getId());
  }

  @Test
  public final void testSelection_MultipleUpdate() throws InterruptedException, InvocationTargetException {
    final ArrayList<Participant> list = new ArrayList<Participant>();
    list.add(participant1);
    list.add(participant2);
    list.add(participant3);
    list.add(participant4);
    list.add(participant5);
    update(listView, list);

    testView.table().requireRowCount(5);
    requireParticipant(testView.table(), 0, participant1);
    requireParticipant(testView.table(), 1, participant2);
    requireParticipant(testView.table(), 2, participant3);
    requireParticipant(testView.table(), 3, participant4);
    requireParticipant(testView.table(), 4, participant5);

    list.remove(participant4);
    list.remove(participant3);
    update(listView, list);

    testView.table().requireRowCount(3);
    requireParticipant(testView.table(), 0, participant1);
    requireParticipant(testView.table(), 1, participant2);
    requireParticipant(testView.table(), 2, participant5);

    list.remove(participant1);
    list.remove(participant5);
    update(listView, list);

    testView.table().requireRowCount(1);
    requireParticipant(testView.table(), 0, participant2);

    list.add(participant1);
    list.add(participant3);
    list.add(participant4);
    list.add(participant5);
    update(listView, list);

    testView.table().requireRowCount(5);
    requireParticipant(testView.table(), 0, participant1);
    requireParticipant(testView.table(), 1, participant2);
    requireParticipant(testView.table(), 2, participant3);
    requireParticipant(testView.table(), 3, participant4);
    requireParticipant(testView.table(), 4, participant5);
  }

  @Test
  public final void testGetSelectedParticipantId() throws InterruptedException, InvocationTargetException {
    final ArrayList<Participant> list = new ArrayList<Participant>();
    list.add(participant1);
    list.add(participant2);
    list.add(participant3);
    list.add(participant4);
    list.add(participant5);
    update(listView, list);

    testView.table().selectRows(1);
    assertThat(listView.getSelectedElementId()).isEqualTo(participant2.getId());

    testView.table().selectRows(2);
    assertThat(listView.getSelectedElementId()).isEqualTo(participant3.getId());

    testView.table().selectRows(4);
    assertThat(listView.getSelectedElementId()).isEqualTo(participant5.getId());

    testView.table().pressKey(KeyEvent.VK_CONTROL).pressAndReleaseKeys(KeyEvent.VK_SPACE)
                 .releaseKey(KeyEvent.VK_CONTROL);
    testView.table().requireNoSelection();
    assertThat(listView.getSelectedElementId()).isZero();
  }

  @Test
  public final void testSelection_EventAfterDoubleClick() throws InterruptedException, InvocationTargetException {
    final ArrayList<Participant> list = new ArrayList<Participant>();
    list.add(participant1);
    list.add(participant2);
    list.add(participant3);
    list.add(participant4);
    list.add(participant5);
    update(listView, list);

    assertNoActionPerformed();
    testView.table().cell(TableCell.row(2).column(1)).doubleClick();
    assertThat(poll()).isEqualTo(UserAction.PARTICIPANT_SELECTED);

    assertNoActionPerformed();
    testView.table().cell(TableCell.row(0).column(0)).click(MouseClickInfo.middleButton().times(1));

    assertNoActionPerformed();
    testView.table().cell(TableCell.row(0).column(1)).click(MouseClickInfo.middleButton().times(2));

    assertNoActionPerformed();
    testView.table().cell(TableCell.row(0).column(2)).click(MouseClickInfo.middleButton().times(3));

    assertNoActionPerformed();
    testView.table().cell(TableCell.row(1).column(0)).click(MouseClickInfo.rightButton().times(1));

    assertNoActionPerformed();
    testView.table().cell(TableCell.row(1).column(1)).click(MouseClickInfo.rightButton().times(2));

    assertNoActionPerformed();
    testView.table().cell(TableCell.row(1).column(2)).click(MouseClickInfo.rightButton().times(3));

    assertNoActionPerformed();
    testView.table().cell(TableCell.row(2).column(0)).click(MouseClickInfo.leftButton().times(1));

    assertNoActionPerformed();
    testView.table().cell(TableCell.row(2).column(1)).click(MouseClickInfo.leftButton().times(2));
    assertThat(poll()).isEqualTo(UserAction.PARTICIPANT_SELECTED);

    assertNoActionPerformed();
    testView.table().cell(TableCell.row(2).column(2)).click(MouseClickInfo.leftButton().times(3));
    assertThat(poll()).isEqualTo(UserAction.PARTICIPANT_SELECTED);

    assertNoActionPerformed();
    testView.table().cell(TableCell.row(2).column(1)).click(MouseClickInfo.leftButton().times(16));
    assertThat(poll()).isEqualTo(UserAction.PARTICIPANT_SELECTED);

    assertNoActionPerformed();
    testView.table().cell(TableCell.row(4).column(0)).click(MouseClickInfo.leftButton().times(2));
    assertThat(poll()).isEqualTo(UserAction.PARTICIPANT_SELECTED);

    assertNoActionPerformed();
    testView.table().cell(TableCell.row(1).column(2)).click(MouseClickInfo.leftButton().times(2));
    assertThat(poll()).isEqualTo(UserAction.PARTICIPANT_SELECTED);
  }

  public static void update(final AListView<Participant> lView, final List<Participant> list) {
    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        lView.update(list);
      }
    });
  }

  public static void requireParticipant(final JTableFixture table, final int row, final Participant p) {
    table.requireCellValue(TableCell.row(row).column(0), String.valueOf(p.getId()));
    requireParticipant(table, row, p.getForeName(), p.getLastName());
  }

  public static void requireParticipant(final JTableFixture table,
                                        final int row,
                                        final String fName,
                                        final String lName) {
    table.requireCellValue(TableCell.row(row).column(1), fName);
    table.requireCellValue(TableCell.row(row).column(2), lName);
  }
}
