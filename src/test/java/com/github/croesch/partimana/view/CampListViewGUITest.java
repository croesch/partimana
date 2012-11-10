package com.github.croesch.partimana.view;

import static org.fest.assertions.Assertions.assertThat;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fest.swing.core.MouseClickInfo;
import org.fest.swing.data.TableCell;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.fixture.Containers;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JPanelFixture;
import org.fest.swing.fixture.JTableFixture;
import org.junit.Test;

import com.github.croesch.partimana.PartiManaDefaultGUITestCase;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.types.Camp;

/**
 * Provides gui tests for {@link CampListView}
 * 
 * @author croesch
 * @since Date: Sep 12, 2012
 */
public class CampListViewGUITest extends PartiManaDefaultGUITestCase {

  private ListView listView;

  private JPanelFixture testView;

  private Camp camp1;

  private Camp camp2;

  private Camp camp3;

  private Camp camp4;

  private Camp camp5;

  @Override
  protected void before() {
    final ListView view = GuiActionRunner.execute(new GuiQuery<CampListView>() {
      @Override
      protected CampListView executeInEDT() throws Throwable {
        return new CampListView(null, CampListViewGUITest.this);
      }
    });

    this.camp1 = new Camp("OFZ", new Date(), new Date(), "Raversbeuren", "rate");
    this.camp2 = new Camp("Sommercamp", new Date(99999), new Date(88888888), "Spain", "5,0€");
    this.camp3 = new Camp("Wintercamp", new Date(444444), new Date(333333333), "Russia", "1,30 €");
    this.camp4 = new Camp("Ostercamp", new Date(444334), new Date(333113333), "France", "1,30 €");
    this.camp5 = new Camp("Herbstcamp", new Date(4422444), new Date(399333333), "Italy", "1,30 €");

    view.setName("listView");
    this.listView = view;

    final FrameFixture window = new FrameFixture(robot(), Containers.frameFor(view));
    window.show(new Dimension(800, 400));
    this.testView = window.panel("listView");
  }

  @Test
  public final void testUpdateListOfCamps() {
    final ArrayList<Camp> list = new ArrayList<Camp>();
    list.add(this.camp1);
    update(this.listView, list);
    requireCamp(this.testView.table(), 0, this.camp1);
  }

  @Test
  public final void testTableEditable() {
    final ArrayList<Camp> list = new ArrayList<Camp>();
    list.add(this.camp1);
    update(this.listView, list);
    this.testView.table().requireNotEditable(TableCell.row(0).column(0));
    this.testView.table().requireNotEditable(TableCell.row(0).column(1));
    this.testView.table().requireNotEditable(TableCell.row(0).column(2));
  }

  @Test
  public final void testSelection() {
    final ArrayList<Camp> list = new ArrayList<Camp>();
    list.add(this.camp1);
    update(this.listView, list);
    this.testView.table().selectRows(0);
    this.testView.table().requireSelectedRows(0);
    assertThat(this.listView.getSelectedElementId()).isEqualTo(this.camp1.getId());

    this.testView.table().selectCell(TableCell.row(0).column(0));
    assertThat(this.testView.table().component().isCellSelected(0, 1)).isTrue();
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
    update(this.listView, list);

    this.testView.table().requireRowCount(3);
    requireCamp(this.testView.table(), 0, c1);
    requireCamp(this.testView.table(), 1, c2);
    requireCamp(this.testView.table(), 2, c3);

    this.testView.table().tableHeader().clickColumn(0);
    requireCamp(this.testView.table(), 0, c3);
    requireCamp(this.testView.table(), 1, c2);
    requireCamp(this.testView.table(), 2, c1);

    this.testView.table().click();
    this.testView.table().tableHeader().clickColumn(0);
    requireCamp(this.testView.table(), 0, c1);
    requireCamp(this.testView.table(), 1, c2);
    requireCamp(this.testView.table(), 2, c3);
  }

  public static void update(final ListView lView, final List<Camp> list) {
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
    list.add(this.camp1);
    list.add(this.camp2);
    list.add(this.camp3);
    list.add(this.camp4);
    list.add(this.camp5);
    update(this.listView, list);

    this.testView.table().requireRowCount(5);
    requireCamp(this.testView.table(), 0, this.camp1);
    requireCamp(this.testView.table(), 1, this.camp2);
    requireCamp(this.testView.table(), 2, this.camp3);
    requireCamp(this.testView.table(), 3, this.camp4);
    requireCamp(this.testView.table(), 4, this.camp5);

    this.testView.table().tableHeader().clickColumn(1);
    requireCamp(this.testView.table(), 2, this.camp1);
    requireCamp(this.testView.table(), 4, this.camp2);
    requireCamp(this.testView.table(), 3, this.camp3);
    requireCamp(this.testView.table(), 0, this.camp4);
    requireCamp(this.testView.table(), 1, this.camp5);

    this.testView.table().click();
    this.testView.table().tableHeader().clickColumn(1);
    requireCamp(this.testView.table(), 2, this.camp1);
    requireCamp(this.testView.table(), 0, this.camp2);
    requireCamp(this.testView.table(), 1, this.camp3);
    requireCamp(this.testView.table(), 4, this.camp4);
    requireCamp(this.testView.table(), 3, this.camp5);
  }

  @Test
  public final void testSorting_Name() {
    final ArrayList<Camp> list = new ArrayList<Camp>();
    list.add(this.camp1);
    list.add(this.camp2);
    list.add(this.camp3);
    list.add(this.camp4);
    list.add(this.camp5);
    update(this.listView, list);

    this.testView.table().requireRowCount(5);
    requireCamp(this.testView.table(), 0, this.camp1);
    requireCamp(this.testView.table(), 1, this.camp2);
    requireCamp(this.testView.table(), 2, this.camp3);
    requireCamp(this.testView.table(), 3, this.camp4);
    requireCamp(this.testView.table(), 4, this.camp5);

    this.testView.table().tableHeader().clickColumn(2);
    requireCamp(this.testView.table(), 1, this.camp1);
    requireCamp(this.testView.table(), 3, this.camp2);
    requireCamp(this.testView.table(), 4, this.camp3);
    requireCamp(this.testView.table(), 2, this.camp4);
    requireCamp(this.testView.table(), 0, this.camp5);

    this.testView.table().click();
    this.testView.table().tableHeader().clickColumn(2);
    requireCamp(this.testView.table(), 3, this.camp1);
    requireCamp(this.testView.table(), 1, this.camp2);
    requireCamp(this.testView.table(), 0, this.camp3);
    requireCamp(this.testView.table(), 2, this.camp4);
    requireCamp(this.testView.table(), 4, this.camp5);
  }

  @Test
  public final void testSelection_CountOfRows() {
    final ArrayList<Camp> list = new ArrayList<Camp>();
    list.add(this.camp1);
    list.add(this.camp2);
    list.add(this.camp3);
    list.add(this.camp4);
    list.add(this.camp5);
    update(this.listView, list);

    this.testView.table().selectRows(1, 2, 3);
    this.testView.table().requireSelectedRows(3);
    assertThat(this.testView.table().target.getSelectedRowCount()).isEqualTo(1);
    assertThat(this.listView.getSelectedElementId()).isEqualTo(this.camp4.getId());
  }

  @Test
  public final void testSelection_MultipleUpdate() {
    final ArrayList<Camp> list = new ArrayList<Camp>();
    list.add(this.camp1);
    list.add(this.camp2);
    list.add(this.camp3);
    list.add(this.camp4);
    list.add(this.camp5);
    update(this.listView, list);

    this.testView.table().requireRowCount(5);
    requireCamp(this.testView.table(), 0, this.camp1);
    requireCamp(this.testView.table(), 1, this.camp2);
    requireCamp(this.testView.table(), 2, this.camp3);
    requireCamp(this.testView.table(), 3, this.camp4);
    requireCamp(this.testView.table(), 4, this.camp5);

    list.remove(this.camp4);
    list.remove(this.camp3);
    update(this.listView, list);

    this.testView.table().requireRowCount(3);
    requireCamp(this.testView.table(), 0, this.camp1);
    requireCamp(this.testView.table(), 1, this.camp2);
    requireCamp(this.testView.table(), 2, this.camp5);

    list.remove(this.camp1);
    list.remove(this.camp5);
    update(this.listView, list);

    this.testView.table().requireRowCount(1);
    requireCamp(this.testView.table(), 0, this.camp2);

    list.add(this.camp1);
    list.add(this.camp3);
    list.add(this.camp4);
    list.add(this.camp5);
    update(this.listView, list);

    this.testView.table().requireRowCount(5);
    requireCamp(this.testView.table(), 0, this.camp1);
    requireCamp(this.testView.table(), 1, this.camp2);
    requireCamp(this.testView.table(), 2, this.camp3);
    requireCamp(this.testView.table(), 3, this.camp4);
    requireCamp(this.testView.table(), 4, this.camp5);
  }

  @Test
  public final void testGetSelectedParticipantId() {
    final ArrayList<Camp> list = new ArrayList<Camp>();
    list.add(this.camp1);
    list.add(this.camp2);
    list.add(this.camp3);
    list.add(this.camp4);
    list.add(this.camp5);
    update(this.listView, list);

    this.testView.table().selectRows(1);
    assertThat(this.listView.getSelectedElementId()).isEqualTo(this.camp2.getId());

    this.testView.table().selectRows(2);
    assertThat(this.listView.getSelectedElementId()).isEqualTo(this.camp3.getId());

    this.testView.table().selectRows(4);
    assertThat(this.listView.getSelectedElementId()).isEqualTo(this.camp5.getId());

    this.testView.table()
                 .pressKey(KeyEvent.VK_CONTROL)
                 .pressAndReleaseKeys(KeyEvent.VK_SPACE)
                 .releaseKey(KeyEvent.VK_CONTROL);
    this.testView.table().requireNoSelection();
    assertThat(this.listView.getSelectedElementId()).isZero();
  }

  @Test
  public final void testSelection_EventAfterDoubleClick() {
    final ArrayList<Camp> list = new ArrayList<Camp>();
    list.add(this.camp1);
    list.add(this.camp2);
    list.add(this.camp3);
    list.add(this.camp4);
    list.add(this.camp5);
    update(this.listView, list);

    assertNoActionPerformed();
    this.testView.table().cell(TableCell.row(2).column(1)).click(MouseClickInfo.leftButton().times(2));
    assertThat(poll()).isEqualTo(UserAction.CAMP_SELECTED);

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
    assertThat(poll()).isEqualTo(UserAction.CAMP_SELECTED);

    assertNoActionPerformed();
    this.testView.table().cell(TableCell.row(2).column(2)).click(MouseClickInfo.leftButton().times(3));
    assertThat(poll()).isEqualTo(UserAction.CAMP_SELECTED);

    assertNoActionPerformed();
    this.testView.table().cell(TableCell.row(2).column(1)).click(MouseClickInfo.leftButton().times(16));
    assertThat(poll()).isEqualTo(UserAction.CAMP_SELECTED);

    assertNoActionPerformed();
    this.testView.table().cell(TableCell.row(4).column(0)).click(MouseClickInfo.leftButton().times(2));
    assertThat(poll()).isEqualTo(UserAction.CAMP_SELECTED);

    assertNoActionPerformed();
    this.testView.table().cell(TableCell.row(1).column(2)).click(MouseClickInfo.leftButton().times(2));
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
