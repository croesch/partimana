package com.github.croesch.partimana.view;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.PartiManaDefaultGUITestCase;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.types.Camp;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JFrame;
import org.assertj.swing.data.TableCell;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JComboBoxFixture;
import org.assertj.swing.fixture.JTableFixture;
import org.junit.Test;

/**
 * Provides test cases for the {@link CampSearchView}.
 *
 * @author croesch
 * @since Date: Nov 2, 2012
 */
public class CampSearchViewGuiTest extends PartiManaDefaultGUITestCase {

  private static final String FC = "filterComposition";

  private FrameFixture searchView;

  private Camp[] camps;

  @Override
  protected void before() {
    this.camps = new Camp[5];
    this.camps[0] = new Camp("OFZ", new Date(15000000000L), new Date(110000000000L), "Berlin", "20 USD");
    this.camps[1] = new Camp("HFZ", new Date(25000000000L), new Date(210000000000L), "Frankfurt", "2 EUR");
    this.camps[2] = new Camp("Freizeit", new Date(35000000000L), new Date(310000000000L), "Stuttgart", "2");
    this.camps[3] = new Camp("Lager", new Date(45000000000L), new Date(410000000000L), "Hannover", "10");
    this.camps[4] = new Camp("Camp", new Date(55000000000L), new Date(510000000000L), "München", "200");

    this.searchView = new FrameFixture(robot(), GuiActionRunner.execute(new GuiQuery<JFrame>() {
      @Override
      protected JFrame executeInEDT() throws Throwable {
        return new CampSearchView("searchingView",
                                  Arrays.asList(CampSearchViewGuiTest.this.camps),
                                  CampSearchViewGuiTest.this);
      }
    }));
    this.searchView.show();
  }

  @Test
  public void testClosingView() {
    assertThat(this.searchView.target().getName()).isEqualTo("searchingView");
    this.searchView.button("close").requireText(Text.CANCEL.text()).click();
    this.searchView.requireNotVisible();
    assertThat(this.searchView.target().isDisplayable()).isFalse();
  }

  @Test
  public void testListInView() {
    this.searchView.panel("list").table("camps").requireRowCount(0);
    CampListViewGUITest.update(((CampSearchView) this.searchView.target()).getListView(), Arrays.asList(this.camps));
    CampListViewGUITest.requireCamp(this.searchView.panel("list").table("camps"), 0, this.camps[0]);
    CampListViewGUITest.requireCamp(this.searchView.panel("list").table("camps"), 1, this.camps[1]);
    CampListViewGUITest.requireCamp(this.searchView.panel("list").table("camps"), 2, this.camps[2]);
    CampListViewGUITest.requireCamp(this.searchView.panel("list").table("camps"), 3, this.camps[3]);
    CampListViewGUITest.requireCamp(this.searchView.panel("list").table("camps"), 4, this.camps[4]);
  }

  @Test
  public void testSelectItem() {
    final JTableFixture table = this.searchView.panel("list").table("camps");
    final JButtonFixture button = this.searchView.button("select");

    table.requireRowCount(0);
    button.requireText(Text.SELECT.text()).requireDisabled();

    CampListViewGUITest.update(((CampSearchView) this.searchView.target()).getListView(), Arrays.asList(this.camps));
    CampListViewGUITest.requireCamp(table, 0, this.camps[0]);
    CampListViewGUITest.requireCamp(table, 1, this.camps[1]);
    CampListViewGUITest.requireCamp(table, 2, this.camps[2]);
    CampListViewGUITest.requireCamp(table, 3, this.camps[3]);
    CampListViewGUITest.requireCamp(table, 4, this.camps[4]);
    table.requireNoSelection();
    button.requireDisabled();

    table.selectRows(1);
    button.requireEnabled();

    table.selectRows(2, 3);
    table.requireSelectedRows(3);
    button.requireEnabled();

    robot().waitForIdle();

    try {
      table.pressKey(KeyEvent.VK_CONTROL);
      table.cell(TableCell.row(3).column(0)).click();
    } finally {
      table.releaseKey(KeyEvent.VK_CONTROL);
    }
    table.requireNoSelection();
    button.requireDisabled();

    table.selectRows(0);
    button.click();
    assertThat(poll()).isEqualTo(UserAction.CAMP_SELECTED);

    assertThat(this.searchView.target()).isInstanceOf(CampSearchView.class);
    assertThat(((CampSearchView) this.searchView.target()).getSelectedId()).isEqualTo(this.camps[0].getId());

    table.selectRows(2);
    assertThat(((CampSearchView) this.searchView.target()).getSelectedId()).isEqualTo(this.camps[2].getId());

    try {
      table.pressKey(KeyEvent.VK_CONTROL);
      table.cell(TableCell.row(2).column(1)).click();
    } finally {
      table.releaseKey(KeyEvent.VK_CONTROL);
    }
    table.requireSelectedRows();
    button.requireDisabled();
    assertThat(((CampSearchView) this.searchView.target()).getSelectedId()).isZero();
  }

  @Test
  public void testFilterTwoFirstCamps() {
    final JComboBoxFixture categoryComboBox = this.searchView.panel(FC).comboBox("category");
    final JComboBoxFixture filterComboBox = this.searchView.panel(FC).comboBox("filterType");
    categoryComboBox.selectItem(Text.FILTER_CAT_CAMP_NAME.text());

    assertComboboxContainsStringFilterTypes(filterComboBox);
    filterComboBox.selectItem(Text.FILTER_TYPE_STARTS_WITH.text());
    this.searchView.textBox("filterValue").enterText("O");

    final JTableFixture table = this.searchView.panel("list").table("camps");
    table.requireRowCount(1);
    CampListViewGUITest.requireCamp(table, 0, this.camps[0]);

    this.searchView.panel(FC).textBox("filterValue").deleteText().enterText("H");
    table.requireRowCount(1);
    CampListViewGUITest.requireCamp(table, 0, this.camps[1]);
  }

  @Test
  public void testFilterComposition() {
    final JComboBoxFixture categoryComboBox = this.searchView.panel(FC).comboBox("category");
    final JComboBoxFixture filterComboBox = this.searchView.panel(FC).comboBox("filterType");
    assertThat(categoryComboBox.contents()).containsOnly(Text.FILTER_CAT_CAMP_FROM.text(),
                                                         Text.FILTER_CAT_CAMP_LOCATION.text(),
                                                         Text.FILTER_CAT_CAMP_NAME.text(),
                                                         Text.FILTER_CAT_CAMP_RATE_PER_DAY.text(),
                                                         Text.FILTER_CAT_CAMP_RATE_PER_PART.text(),
                                                         Text.FILTER_CAT_CAMP_UNTIL.text());
    categoryComboBox.selectItem(Text.FILTER_CAT_CAMP_FROM.text());

    assertComboboxContainsDateFilterTypes(filterComboBox);

    categoryComboBox.selectItem(Text.FILTER_CAT_CAMP_RATE_PER_PART.text());
    assertComboboxContainsStringFilterTypes(filterComboBox);

    filterComboBox.selectItem(Text.FILTER_TYPE_CONTAINS.text());
    this.searchView.textBox("filterValue").enterText("2");

    final JTableFixture table = this.searchView.panel("list").table("camps");
    table.requireRowCount(4);
    CampListViewGUITest.requireCamp(table, 0, this.camps[0]);
    CampListViewGUITest.requireCamp(table, 1, this.camps[1]);
    CampListViewGUITest.requireCamp(table, 2, this.camps[2]);
    CampListViewGUITest.requireCamp(table, 3, this.camps[4]);

    categoryComboBox.selectItem(Text.FILTER_CAT_CAMP_LOCATION.text());
    assertComboboxContainsStringFilterTypes(filterComboBox);

    filterComboBox.selectItem(Text.FILTER_TYPE_ENDS_WITH.text());
    this.searchView.panel(FC).textBox("filterValue").deleteText().enterText("t");
    table.requireRowCount(2);
    CampListViewGUITest.requireCamp(table, 0, this.camps[1]);
    CampListViewGUITest.requireCamp(table, 1, this.camps[2]);

    categoryComboBox.selectItem(Text.FILTER_CAT_CAMP_NAME.text());
    assertComboboxContainsStringFilterTypes(filterComboBox);

    filterComboBox.selectItem(Text.FILTER_TYPE_NOT_EQUALS_IGNORE_CASE.text());
    this.searchView.panel(FC).textBox("filterValue").deleteText().enterText("caMp");
    table.requireRowCount(4);
    CampListViewGUITest.requireCamp(table, 0, this.camps[0]);
    CampListViewGUITest.requireCamp(table, 1, this.camps[1]);
    CampListViewGUITest.requireCamp(table, 2, this.camps[2]);
    CampListViewGUITest.requireCamp(table, 3, this.camps[3]);

    categoryComboBox.selectItem(Text.FILTER_CAT_CAMP_FROM.text());
    assertComboboxContainsDateFilterTypes(filterComboBox);

    filterComboBox.selectItem(Text.FILTER_TYPE_AFTER.text());
    this.searchView.panel(FC).textBox("filterValue").deleteText().enterText("05.06.1971");
    table.requireRowCount(2);
    CampListViewGUITest.requireCamp(table, 0, this.camps[3]);
    CampListViewGUITest.requireCamp(table, 1, this.camps[4]);
  }

  private void assertComboboxContainsDateFilterTypes(final JComboBoxFixture filterComboBox) {
    assertThat(filterComboBox.contents()).containsOnly(Text.FILTER_TYPE_AFTER.text(),
                                                       Text.FILTER_TYPE_BEFORE.text(),
                                                       Text.FILTER_TYPE_EQUALS.text(),
                                                       Text.FILTER_TYPE_NOT_EQUALS.text());
  }

  private void assertComboboxContainsStringFilterTypes(final JComboBoxFixture filterComboBox) {
    assertThat(filterComboBox.contents()).containsOnly(Text.FILTER_TYPE_EQUALS.text(),
                                                       Text.FILTER_TYPE_CONTAINS.text(),
                                                       Text.FILTER_TYPE_EQUALS_IGNORE_CASE.text(),
                                                       Text.FILTER_TYPE_NOT_EQUALS.text(),
                                                       Text.FILTER_TYPE_STARTS_WITH.text(),
                                                       Text.FILTER_TYPE_ENDS_WITH.text(),
                                                       Text.FILTER_TYPE_NOT_EQUALS_IGNORE_CASE.text());
  }
}
