package com.github.croesch.partimana.view;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.PartiManaDefaultGUITestCase;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.types.Camp;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
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
 * Provides gui tests for {@link CampListView}
 *
 * @author croesch
 * @since Date: Sep 12, 2012
 */
public class CampListViewGUITest extends PartiManaDefaultGUITestCase {

  private AListView<Camp> aListView;

  private JPanelFixture testView;

  private Camp camp1;

  private Camp camp2;

  private Camp camp3;

  private Camp camp4;

  private Camp camp5;

  @Override
  protected void before() {
    final AListView<Camp> view = GuiActionRunner.execute(new GuiQuery<CampListView>() {
      @Override
      protected CampListView executeInEDT() throws Throwable {
        return new CampListView(null, CampListViewGUITest.this);
      }
    });

    camp1 = new Camp("OFZ", new Date(), new Date(), "Raversbeuren", "rate");
    camp2 = new Camp("Sommercamp", new Date(99999), new Date(88888888), "Spain", "5,0€");
    camp3 = new Camp("Wintercamp", new Date(444444), new Date(333333333), "Russia", "1,30 €");
    camp4 = new Camp("Ostercamp", new Date(444334), new Date(333113333), "France", "1,30 €");
    camp5 = new Camp("Herbstcamp", new Date(4422444), new Date(399333333), "Italy", "1,30 €");

    view.setName("aListView");
    aListView = view;

    final FrameFixture window = new FrameFixture(robot(), Containers.frameFor(view));
    window.show(new Dimension(800, 400));
    testView = window.panel("aListView");
  }

  @Test
  public final void testUpdateListOfCamps() {
    final ArrayList<Camp> list = new ArrayList<Camp>();
    list.add(camp1);
    update(aListView, list);
    requireCamp(testView.table(), 0, camp1);
  }

  @Test
  public final void testTableEditable() {
    final ArrayList<Camp> list = new ArrayList<Camp>();
    list.add(camp1);
    update(aListView, list);
    testView.table().requireNotEditable(TableCell.row(0).column(0));
    testView.table().requireNotEditable(TableCell.row(0).column(1));
    testView.table().requireNotEditable(TableCell.row(0).column(2));
  }

  @Test
  public final void testSelection() {
    final ArrayList<Camp> list = new ArrayList<Camp>();
    list.add(camp1);
    update(aListView, list);
    testView.table().selectRows(0);
    testView.table().requireSelectedRows(0);
    assertThat(aListView.getSelectedElementId()).isEqualTo(camp1.getId());

    testView.table().selectCell(TableCell.row(0).column(0));
    assertThat(testView.table().target().isCellSelected(0, 1)).isTrue();
  }

  @Test
  public final void testSorting_Id() {
    final Camp c1 = new Camp(9999, "Wintercamp", new Date(444444), new Date(333333333), "Russia", "1,30 €");
    final Camp c2 = new Camp(10001, "Sommercamp", new Date(3333333), new Date(444333333), "Germany", "1,30 €");
    final Camp c3 = new Camp(10010, "Ostercamp", new Date(222222), new Date(555333333), "Denmark", "1,30 €");

    final ArrayList<Camp> list = new ArrayList<Camp>();
    list.add(c1);
    list.add(c2);
    list.add(c3);
    update(aListView, list);

    testView.table().requireRowCount(3);
    requireCamp(testView.table(), 0, c1);
    requireCamp(testView.table(), 1, c2);
    requireCamp(testView.table(), 2, c3);

    testView.table().tableHeader().clickColumn(0);
    requireCamp(testView.table(), 0, c3);
    requireCamp(testView.table(), 1, c2);
    requireCamp(testView.table(), 2, c1);

    testView.table().click();
    testView.table().tableHeader().clickColumn(0);
    requireCamp(testView.table(), 0, c1);
    requireCamp(testView.table(), 1, c2);
    requireCamp(testView.table(), 2, c3);
  }

  public static void update(final AListView<Camp> lView, final List<Camp> list) {
    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        lView.update(list);
      }
    });
  }

  @Test
  public final void testSorting_Location() {
    final ArrayList<Camp> list = new ArrayList<Camp>();
    list.add(camp1);
    list.add(camp2);
    list.add(camp3);
    list.add(camp4);
    list.add(camp5);
    update(aListView, list);

    testView.table().requireRowCount(5);
    requireCamp(testView.table(), 0, camp1);
    requireCamp(testView.table(), 1, camp2);
    requireCamp(testView.table(), 2, camp3);
    requireCamp(testView.table(), 3, camp4);
    requireCamp(testView.table(), 4, camp5);

    testView.table().tableHeader().clickColumn(1);
    requireCamp(testView.table(), 2, camp1);
    requireCamp(testView.table(), 4, camp2);
    requireCamp(testView.table(), 3, camp3);
    requireCamp(testView.table(), 0, camp4);
    requireCamp(testView.table(), 1, camp5);

    testView.table().click();
    testView.table().tableHeader().clickColumn(1);
    requireCamp(testView.table(), 2, camp1);
    requireCamp(testView.table(), 0, camp2);
    requireCamp(testView.table(), 1, camp3);
    requireCamp(testView.table(), 4, camp4);
    requireCamp(testView.table(), 3, camp5);
  }

  @Test
  public final void testSorting_Name() {
    final ArrayList<Camp> list = new ArrayList<Camp>();
    list.add(camp1);
    list.add(camp2);
    list.add(camp3);
    list.add(camp4);
    list.add(camp5);
    update(aListView, list);

    testView.table().requireRowCount(5);
    requireCamp(testView.table(), 0, camp1);
    requireCamp(testView.table(), 1, camp2);
    requireCamp(testView.table(), 2, camp3);
    requireCamp(testView.table(), 3, camp4);
    requireCamp(testView.table(), 4, camp5);

    testView.table().tableHeader().clickColumn(2);
    requireCamp(testView.table(), 1, camp1);
    requireCamp(testView.table(), 3, camp2);
    requireCamp(testView.table(), 4, camp3);
    requireCamp(testView.table(), 2, camp4);
    requireCamp(testView.table(), 0, camp5);

    testView.table().click();
    testView.table().tableHeader().clickColumn(2);
    requireCamp(testView.table(), 3, camp1);
    requireCamp(testView.table(), 1, camp2);
    requireCamp(testView.table(), 0, camp3);
    requireCamp(testView.table(), 2, camp4);
    requireCamp(testView.table(), 4, camp5);
  }

  @Test
  public final void testSelection_CountOfRows() {
    final ArrayList<Camp> list = new ArrayList<Camp>();
    list.add(camp1);
    list.add(camp2);
    list.add(camp3);
    list.add(camp4);
    list.add(camp5);
    update(aListView, list);

    testView.table().selectRows(1, 2, 3);
    testView.table().requireSelectedRows(3);
    assertThat(testView.table().target().getSelectedRowCount()).isEqualTo(1);
    assertThat(aListView.getSelectedElementId()).isEqualTo(camp4.getId());
  }

  @Test
  public final void testSelection_MultipleUpdate() {
    final ArrayList<Camp> list = new ArrayList<Camp>();
    list.add(camp1);
    list.add(camp2);
    list.add(camp3);
    list.add(camp4);
    list.add(camp5);
    update(aListView, list);

    testView.table().requireRowCount(5);
    requireCamp(testView.table(), 0, camp1);
    requireCamp(testView.table(), 1, camp2);
    requireCamp(testView.table(), 2, camp3);
    requireCamp(testView.table(), 3, camp4);
    requireCamp(testView.table(), 4, camp5);

    list.remove(camp4);
    list.remove(camp3);
    update(aListView, list);

    testView.table().requireRowCount(3);
    requireCamp(testView.table(), 0, camp1);
    requireCamp(testView.table(), 1, camp2);
    requireCamp(testView.table(), 2, camp5);

    list.remove(camp1);
    list.remove(camp5);
    update(aListView, list);

    testView.table().requireRowCount(1);
    requireCamp(testView.table(), 0, camp2);

    list.add(camp1);
    list.add(camp3);
    list.add(camp4);
    list.add(camp5);
    update(aListView, list);

    testView.table().requireRowCount(5);
    requireCamp(testView.table(), 0, camp1);
    requireCamp(testView.table(), 1, camp2);
    requireCamp(testView.table(), 2, camp3);
    requireCamp(testView.table(), 3, camp4);
    requireCamp(testView.table(), 4, camp5);
  }

  @Test
  public final void testGetSelectedParticipantId() {
    final ArrayList<Camp> list = new ArrayList<Camp>();
    list.add(camp1);
    list.add(camp2);
    list.add(camp3);
    list.add(camp4);
    list.add(camp5);
    update(aListView, list);

    testView.table().selectRows(1);
    assertThat(aListView.getSelectedElementId()).isEqualTo(camp2.getId());

    testView.table().selectRows(2);
    assertThat(aListView.getSelectedElementId()).isEqualTo(camp3.getId());

    testView.table().selectRows(4);
    assertThat(aListView.getSelectedElementId()).isEqualTo(camp5.getId());

    testView.table().pressKey(KeyEvent.VK_CONTROL).pressAndReleaseKeys(KeyEvent.VK_SPACE)
                 .releaseKey(KeyEvent.VK_CONTROL);
    testView.table().requireNoSelection();
    assertThat(aListView.getSelectedElementId()).isZero();
  }

  @Test
  public final void testSelection_EventAfterDoubleClick() {
    final ArrayList<Camp> list = new ArrayList<Camp>();
    list.add(camp1);
    list.add(camp2);
    list.add(camp3);
    list.add(camp4);
    list.add(camp5);
    update(aListView, list);

    assertNoActionPerformed();
    testView.table().cell(TableCell.row(2).column(1)).click(MouseClickInfo.leftButton().times(2));
    assertThat(poll()).isEqualTo(UserAction.CAMP_SELECTED);

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
    assertThat(poll()).isEqualTo(UserAction.CAMP_SELECTED);

    assertNoActionPerformed();
    testView.table().cell(TableCell.row(2).column(2)).click(MouseClickInfo.leftButton().times(3));
    assertThat(poll()).isEqualTo(UserAction.CAMP_SELECTED);

    assertNoActionPerformed();
    testView.table().cell(TableCell.row(2).column(1)).click(MouseClickInfo.leftButton().times(16));
    assertThat(poll()).isEqualTo(UserAction.CAMP_SELECTED);

    assertNoActionPerformed();
    testView.table().cell(TableCell.row(4).column(0)).click(MouseClickInfo.leftButton().times(2));
    assertThat(poll()).isEqualTo(UserAction.CAMP_SELECTED);

    assertNoActionPerformed();
    testView.table().cell(TableCell.row(1).column(2)).click(MouseClickInfo.leftButton().times(2));
    assertThat(poll()).isEqualTo(UserAction.CAMP_SELECTED);
  }

  public static void requireCamp(final JTableFixture table, final int row, final Camp c) {
    table.requireCellValue(TableCell.row(row).column(0), String.valueOf(c.getId()));
    requireCamp(table, row, c.getName(), c.getLocation());
  }

  public static void requireCamp(final JTableFixture table, final int row, final String name, final String location) {
    table.requireCellValue(TableCell.row(row).column(1), location);
    table.requireCellValue(TableCell.row(row).column(2), name);
  }
}
