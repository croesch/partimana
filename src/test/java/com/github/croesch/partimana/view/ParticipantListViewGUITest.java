package com.github.croesch.partimana.view;

import static org.fest.assertions.Assertions.assertThat;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.SwingUtilities;

import org.fest.swing.core.MouseClickInfo;
import org.fest.swing.data.TableCell;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.Containers;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JPanelFixture;
import org.fest.swing.fixture.JTableFixture;
import org.junit.Test;

import com.github.croesch.partimana.PartiManaDefaultGUITestCase;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;
import com.github.croesch.partimana.view.api.IParticipantEditView;

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
   * @author croesch
   * @since Date: Jun 26, 2011
   */
  @Override
  protected void before() {
    final ParticipantListView view = GuiActionRunner.execute(new GuiQuery<ParticipantListView>() {
      @Override
      protected ParticipantListView executeInEDT() throws Throwable {
        return new ParticipantListView(ParticipantListViewGUITest.this);
      }
    });

    this.participant1 = new Participant("Mustermann",
                                        "Max",
                                        Gender.MALE,
                                        Denomination.OTHER,
                                        new Date(),
                                        "Musterstrasse 12",
                                        12345,
                                        "Musterhausen",
                                        CountyCouncil.OTHER);

    final Date d1 = new Date(123);

    this.participant1.setBank("bank");
    this.participant1.setBankAccountNumber(1);
    this.participant1.setBankCodeNumber(12);
    this.participant1.setComment("comment");
    this.participant1.setDateSinceInDataBase(d1);
    this.participant1.setDateUpToInSystem(d1);
    this.participant1.setDenomination(Denomination.JEWISH);
    this.participant1.setFax("fax");
    this.participant1.setMailAddress("mail");
    this.participant1.setMobilePhone("mobile");
    this.participant1.setPhone("phone");
    this.participant1.setPhoneOfParents("phone");
    this.participant1.setPossibleAGE(true);
    this.participant1.setPossibleBoard(true);
    this.participant1.setPossibleExtendedBoard(true);
    this.participant1.setPossibleKitchen(true);
    this.participant1.setPossibleMAK(true);
    this.participant1.setPossibleMisc(true);
    this.participant1.setPossibleParticipant(true);
    this.participant1.setPossibleSeminar(true);
    this.participant1.setPossibleStaff(true);
    this.participant1.setPossibleStaffYouth(true);
    this.participant1.setCityPostal("city");
    this.participant1.setPostCodePostal(3124);
    this.participant1.setStreetPostal("street");

    this.participant2 = new Participant("Schmidt",
                                        "Hans",
                                        Gender.MALE,
                                        Denomination.NONE,
                                        new Date(1200),
                                        "Strasse 4",
                                        56789,
                                        "Stadt",
                                        CountyCouncil.CITY_NEUSTADT);

    this.participant3 = new Participant("Müller",
                                        "Jasmin",
                                        Gender.FEMALE,
                                        Denomination.CATHOLIC,
                                        new Date(6789543),
                                        "Mittelgasse 3",
                                        54321,
                                        "Schimmelhausen",
                                        CountyCouncil.CITY_ZWEIBRUECKEN);

    this.participant4 = new Participant("Mauer",
                                        "Jaqueline",
                                        Gender.FEMALE,
                                        Denomination.JEWISH,
                                        new Date(1297579),
                                        "Hinterweg 12",
                                        99384,
                                        "Hilgen",
                                        CountyCouncil.UNKNOWN);

    this.participant5 = new Participant("Bauer",
                                        "Andreas",
                                        Gender.MALE,
                                        Denomination.EVANGELIC,
                                        new Date(9876543),
                                        "Julgenweg 76",
                                        21228,
                                        "Mildeningen",
                                        CountyCouncil.CITY_NEUSTADT);

    view.setName("listView");
    this.listView = view;

    final FrameFixture window = new FrameFixture(robot(), Containers.frameFor(view));
    window.target.setPreferredSize(new Dimension(800, 400));
    window.show();
    this.testView = window.panel("listView");
  }

  @Override
  protected void after() {
    cleanUp();
  }

  @Test
  public final void testUpdateListOfParticipant() throws InterruptedException, InvocationTargetException {
    final ArrayList<Participant> list = new ArrayList<Participant>();
    list.add(this.participant1);
    update(list);
    requireParticipant(this.testView.table(), 0, this.participant1);
  }

  @Test
  public final void testTableEditable() throws InterruptedException, InvocationTargetException {
    final ArrayList<Participant> list = new ArrayList<Participant>();
    list.add(this.participant1);
    update(list);
    this.testView.table().requireNotEditable(TableCell.row(0).column(0));
    this.testView.table().requireNotEditable(TableCell.row(0).column(1));
    this.testView.table().requireNotEditable(TableCell.row(0).column(2));
  }

  @Test
  public final void testSelection() throws InterruptedException, InvocationTargetException {
    final ArrayList<Participant> list = new ArrayList<Participant>();
    list.add(this.participant1);
    update(list);
    this.testView.table().selectRows(0);
    this.testView.table().requireSelectedRows(0);
    assertThat(this.listView.getSelectedParticipantId()).isEqualTo(this.participant1.getId());

    this.testView.table().selectCell(TableCell.row(0).column(0));
    assertThat(this.testView.table().component().isCellSelected(0, 1)).isTrue();
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
    update(list);

    this.testView.table().requireRowCount(3);
    requireParticipant(this.testView.table(), 0, p1);
    requireParticipant(this.testView.table(), 1, p2);
    requireParticipant(this.testView.table(), 2, p3);

    this.testView.table().tableHeader().clickColumn(0);
    requireParticipant(this.testView.table(), 0, p1);
    requireParticipant(this.testView.table(), 1, p2);
    requireParticipant(this.testView.table(), 2, p3);

    this.testView.table().click();
    this.testView.table().tableHeader().clickColumn(0);
    requireParticipant(this.testView.table(), 0, p3);
    requireParticipant(this.testView.table(), 1, p2);
    requireParticipant(this.testView.table(), 2, p1);
  }

  @Test
  public final void testSorting_FirstName() throws InterruptedException, InvocationTargetException {
    final ArrayList<Participant> list = new ArrayList<Participant>();
    list.add(this.participant1);
    list.add(this.participant2);
    list.add(this.participant3);
    list.add(this.participant4);
    list.add(this.participant5);
    update(list);

    this.testView.table().requireRowCount(5);
    requireParticipant(this.testView.table(), 0, this.participant1);
    requireParticipant(this.testView.table(), 1, this.participant2);
    requireParticipant(this.testView.table(), 2, this.participant3);
    requireParticipant(this.testView.table(), 3, this.participant4);
    requireParticipant(this.testView.table(), 4, this.participant5);

    this.testView.table().tableHeader().clickColumn(1);
    requireParticipant(this.testView.table(), 4, this.participant1);
    requireParticipant(this.testView.table(), 1, this.participant2);
    requireParticipant(this.testView.table(), 3, this.participant3);
    requireParticipant(this.testView.table(), 2, this.participant4);
    requireParticipant(this.testView.table(), 0, this.participant5);

    this.testView.table().click();
    this.testView.table().tableHeader().clickColumn(1);
    requireParticipant(this.testView.table(), 0, this.participant1);
    requireParticipant(this.testView.table(), 3, this.participant2);
    requireParticipant(this.testView.table(), 1, this.participant3);
    requireParticipant(this.testView.table(), 2, this.participant4);
    requireParticipant(this.testView.table(), 4, this.participant5);
  }

  @Test
  public final void testSorting_LastName() throws InterruptedException, InvocationTargetException {
    final ArrayList<Participant> list = new ArrayList<Participant>();
    list.add(this.participant1);
    list.add(this.participant2);
    list.add(this.participant3);
    list.add(this.participant4);
    list.add(this.participant5);
    update(list);

    this.testView.table().requireRowCount(5);
    requireParticipant(this.testView.table(), 0, this.participant1);
    requireParticipant(this.testView.table(), 1, this.participant2);
    requireParticipant(this.testView.table(), 2, this.participant3);
    requireParticipant(this.testView.table(), 3, this.participant4);
    requireParticipant(this.testView.table(), 4, this.participant5);

    this.testView.table().tableHeader().clickColumn(2);
    requireParticipant(this.testView.table(), 3, this.participant1);
    requireParticipant(this.testView.table(), 4, this.participant2);
    requireParticipant(this.testView.table(), 2, this.participant3);
    requireParticipant(this.testView.table(), 1, this.participant4);
    requireParticipant(this.testView.table(), 0, this.participant5);

    this.testView.table().click();
    this.testView.table().tableHeader().clickColumn(2);
    requireParticipant(this.testView.table(), 1, this.participant1);
    requireParticipant(this.testView.table(), 0, this.participant2);
    requireParticipant(this.testView.table(), 2, this.participant3);
    requireParticipant(this.testView.table(), 3, this.participant4);
    requireParticipant(this.testView.table(), 4, this.participant5);
  }

  @Test
  public final void testSelection_CountOfRows() throws InterruptedException, InvocationTargetException {
    final ArrayList<Participant> list = new ArrayList<Participant>();
    list.add(this.participant1);
    list.add(this.participant2);
    list.add(this.participant3);
    list.add(this.participant4);
    list.add(this.participant5);
    update(list);

    this.testView.table().selectRows(1, 2, 3);
    this.testView.table().requireSelectedRows(3);
    assertThat(this.testView.table().target.getSelectedRowCount()).isEqualTo(1);
    assertThat(this.listView.getSelectedParticipantId()).isEqualTo(this.participant4.getId());
  }

  @Test
  public final void testSelection_MultipleUpdate() throws InterruptedException, InvocationTargetException {
    final ArrayList<Participant> list = new ArrayList<Participant>();
    list.add(this.participant1);
    list.add(this.participant2);
    list.add(this.participant3);
    list.add(this.participant4);
    list.add(this.participant5);
    update(list);

    this.testView.table().requireRowCount(5);
    requireParticipant(this.testView.table(), 0, this.participant1);
    requireParticipant(this.testView.table(), 1, this.participant2);
    requireParticipant(this.testView.table(), 2, this.participant3);
    requireParticipant(this.testView.table(), 3, this.participant4);
    requireParticipant(this.testView.table(), 4, this.participant5);

    list.remove(this.participant4);
    list.remove(this.participant3);
    update(list);

    this.testView.table().requireRowCount(3);
    requireParticipant(this.testView.table(), 0, this.participant1);
    requireParticipant(this.testView.table(), 1, this.participant2);
    requireParticipant(this.testView.table(), 2, this.participant5);

    list.remove(this.participant1);
    list.remove(this.participant5);
    update(list);

    this.testView.table().requireRowCount(1);
    requireParticipant(this.testView.table(), 0, this.participant2);

    list.add(this.participant1);
    list.add(this.participant3);
    list.add(this.participant4);
    list.add(this.participant5);
    update(list);

    this.testView.table().requireRowCount(5);
    requireParticipant(this.testView.table(), 1, this.participant1);
    requireParticipant(this.testView.table(), 0, this.participant2);
    requireParticipant(this.testView.table(), 2, this.participant3);
    requireParticipant(this.testView.table(), 3, this.participant4);
    requireParticipant(this.testView.table(), 4, this.participant5);
  }

  @Test
  public final void testGetSelectedParticipantId() throws InterruptedException, InvocationTargetException {
    final ArrayList<Participant> list = new ArrayList<Participant>();
    list.add(this.participant1);
    list.add(this.participant2);
    list.add(this.participant3);
    list.add(this.participant4);
    list.add(this.participant5);
    update(list);

    this.testView.table().selectRows(1);
    assertThat(this.listView.getSelectedParticipantId()).isEqualTo(this.participant2.getId());

    this.testView.table().selectRows(2);
    assertThat(this.listView.getSelectedParticipantId()).isEqualTo(this.participant3.getId());

    this.testView.table().selectRows(4);
    assertThat(this.listView.getSelectedParticipantId()).isEqualTo(this.participant5.getId());

    this.testView.table()
                 .pressKey(KeyEvent.VK_CONTROL)
                 .pressAndReleaseKeys(KeyEvent.VK_SPACE)
                 .releaseKey(KeyEvent.VK_CONTROL);
    this.testView.table().requireNoSelection();
    assertThat(this.listView.getSelectedParticipantId()).isZero();
  }

  @Test
  public final void testSelection_EventAfterDoubleClick() throws InterruptedException, InvocationTargetException {
    final ArrayList<Participant> list = new ArrayList<Participant>();
    list.add(this.participant1);
    list.add(this.participant2);
    list.add(this.participant3);
    list.add(this.participant4);
    list.add(this.participant5);
    update(list);

    assertNoActionPerformed();
    this.testView.table().cell(TableCell.row(2).column(1)).doubleClick();
    assertThat(poll()).isEqualTo(UserAction.PARTICIPANT_SELECTED);

    assertNoActionPerformed();
    this.testView.table().cell(TableCell.row(0).column(0)).click(MouseClickInfo.middleButton().times(1));

    assertNoActionPerformed();
    this.testView.table().cell(TableCell.row(0).column(1)).click(MouseClickInfo.middleButton().times(2));

    assertNoActionPerformed();
    this.testView.table().cell(TableCell.row(0).column(2)).click(MouseClickInfo.middleButton().times(3));

    assertNoActionPerformed();
    this.testView.table().cell(TableCell.row(1).column(0)).click(MouseClickInfo.rightButton().times(1));

    assertNoActionPerformed();
    this.testView.table().cell(TableCell.row(1).column(1)).click(MouseClickInfo.rightButton().times(2));

    assertNoActionPerformed();
    this.testView.table().cell(TableCell.row(1).column(2)).click(MouseClickInfo.rightButton().times(3));

    assertNoActionPerformed();
    this.testView.table().cell(TableCell.row(2).column(0)).click(MouseClickInfo.leftButton().times(1));

    assertNoActionPerformed();
    this.testView.table().cell(TableCell.row(2).column(1)).click(MouseClickInfo.leftButton().times(2));
    assertThat(poll()).isEqualTo(UserAction.PARTICIPANT_SELECTED);

    assertNoActionPerformed();
    this.testView.table().cell(TableCell.row(2).column(2)).click(MouseClickInfo.leftButton().times(3));
    assertThat(poll()).isEqualTo(UserAction.PARTICIPANT_SELECTED);

    assertNoActionPerformed();
    this.testView.table().cell(TableCell.row(2).column(1)).click(MouseClickInfo.leftButton().times(16));
    assertThat(poll()).isEqualTo(UserAction.PARTICIPANT_SELECTED);

    assertNoActionPerformed();
    this.testView.table().cell(TableCell.row(4).column(0)).click(MouseClickInfo.leftButton().times(2));
    assertThat(poll()).isEqualTo(UserAction.PARTICIPANT_SELECTED);

    assertNoActionPerformed();
    this.testView.table().cell(TableCell.row(1).column(2)).click(MouseClickInfo.leftButton().times(2));
    assertThat(poll()).isEqualTo(UserAction.PARTICIPANT_SELECTED);
  }

  private void update(final ArrayList<Participant> list) throws InterruptedException, InvocationTargetException {
    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        ParticipantListViewGUITest.this.listView.update(list);
      }
    });
  }

  public static void requireParticipant(final JTableFixture table, final int row, final Participant p) {
    table.requireCellValue(TableCell.row(row).column(0), String.valueOf(p.getId()));
    requireParticipant(table, row, p.getForeName(), p.getLastName());
  }

  public static void requireParticipant(final JTableFixture table, final int row, final String fName, final String lName) {
    table.requireCellValue(TableCell.row(row).column(1), fName);
    table.requireCellValue(TableCell.row(row).column(2), lName);
  }
}
