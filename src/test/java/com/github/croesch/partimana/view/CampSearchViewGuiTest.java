package com.github.croesch.partimana.view;

import static org.fest.assertions.Assertions.assertThat;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JFrame;

import org.fest.swing.data.TableCell;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JButtonFixture;
import org.fest.swing.fixture.JComboBoxFixture;
import org.fest.swing.fixture.JTableFixture;
import org.junit.Test;

import com.github.croesch.partimana.PartiManaDefaultGUITestCase;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.types.Camp;

/**
 * Provides test cases for the {@link SearchView}.
 * 
 * @author croesch
 * @since Date: Nov 2, 2012
 */
public class CampSearchViewGuiTest extends PartiManaDefaultGUITestCase {

  private FrameFixture searchView;

  private Camp[] camps;

  @Override
  protected void before() {
    this.camps = new Camp[5];
    this.camps[0] = new Camp("OFZ", new Date(15000000), new Date(110000000), "Berlin", "20 USD");
    this.camps[1] = new Camp("HFZ", new Date(25000000), new Date(210000000), "Frankfurt", "2 EUR");
    this.camps[2] = new Camp("Freizeit", new Date(35000000), new Date(310000000), "Stuttgart", "2");
    this.camps[3] = new Camp("Lager", new Date(45000000), new Date(410000000), "Hannover", "10");
    this.camps[4] = new Camp("Camp", new Date(55000000), new Date(510000000), "München", "200");

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
    assertThat(this.searchView.component().getName()).isEqualTo("searchingView");
    this.searchView.button("close").requireText(Text.CANCEL.text()).click();
    this.searchView.requireNotVisible();
    assertThat(this.searchView.component().isDisplayable()).isFalse();
  }

  @Test
  public void testListInView() {
    this.searchView.panel("list").table("camps").requireRowCount(0);
    CampListViewGUITest.update(((CampSearchView) this.searchView.component()).getListView(), Arrays.asList(this.camps));
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

    CampListViewGUITest.update(((CampSearchView) this.searchView.component()).getListView(), Arrays.asList(this.camps));
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
  }

  @Test
  public void testFilterComposition() {
    final JComboBoxFixture categoryComboBox = this.searchView.panel("filterComposition").comboBox("category");
    final JComboBoxFixture filterComboBox = this.searchView.panel("filterComposition").comboBox("filterType");
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
    this.searchView.panel("list").table("camps").requireRowCount(0);

    this.searchView.button("and").requireText(Text.FILTER_AND.text()).click();
    final JTableFixture table = this.searchView.panel("list").table("camps");
    CampListViewGUITest.requireCamp(table, 0, this.camps[0]);
    CampListViewGUITest.requireCamp(table, 1, this.camps[1]);
    CampListViewGUITest.requireCamp(table, 2, this.camps[2]);
    CampListViewGUITest.requireCamp(table, 3, this.camps[4]);

    categoryComboBox.selectItem(Text.FILTER_CAT_CAMP_LOCATION.text());
    assertComboboxContainsStringFilterTypes(filterComboBox);

    filterComboBox.selectItem(Text.FILTER_TYPE_ENDS_WITH.text());
    this.searchView.textBox("filterValue").deleteText().enterText("t");
    CampListViewGUITest.requireCamp(table, 0, this.camps[0]);
    CampListViewGUITest.requireCamp(table, 1, this.camps[1]);
    CampListViewGUITest.requireCamp(table, 2, this.camps[2]);
    CampListViewGUITest.requireCamp(table, 3, this.camps[4]);
    this.searchView.button("and").requireText(Text.FILTER_AND.text()).click();
    CampListViewGUITest.requireCamp(table, 0, this.camps[1]);
    CampListViewGUITest.requireCamp(table, 1, this.camps[2]);

    categoryComboBox.selectItem(Text.FILTER_CAT_CAMP_NAME.text());
    assertComboboxContainsStringFilterTypes(filterComboBox);

    filterComboBox.selectItem(Text.FILTER_TYPE_NOT_EQUALS_IGNORE_CASE.text());
    this.searchView.textBox("filterValue").deleteText().enterText("caMp");
    CampListViewGUITest.requireCamp(table, 0, this.camps[1]);
    CampListViewGUITest.requireCamp(table, 1, this.camps[2]);
    this.searchView.button("or").requireText(Text.FILTER_OR.text()).click();
    CampListViewGUITest.requireCamp(table, 0, this.camps[0]);
    CampListViewGUITest.requireCamp(table, 1, this.camps[1]);
    CampListViewGUITest.requireCamp(table, 2, this.camps[2]);
    CampListViewGUITest.requireCamp(table, 3, this.camps[3]);

    categoryComboBox.selectItem(Text.FILTER_CAT_CAMP_FROM.text());
    assertComboboxContainsDateFilterTypes(filterComboBox);

    filterComboBox.selectItem(Text.FILTER_TYPE_AFTER.text());
    this.searchView.textBox("filterValue").deleteText().enterText("44999999");
    CampListViewGUITest.requireCamp(table, 0, this.camps[0]);
    CampListViewGUITest.requireCamp(table, 1, this.camps[1]);
    CampListViewGUITest.requireCamp(table, 2, this.camps[2]);
    CampListViewGUITest.requireCamp(table, 3, this.camps[3]);
    this.searchView.button("and").requireText(Text.FILTER_AND.text()).click();
    CampListViewGUITest.requireCamp(table, 0, this.camps[1]);
    CampListViewGUITest.requireCamp(table, 1, this.camps[2]);
    CampListViewGUITest.requireCamp(table, 2, this.camps[3]);
  }

  private void assertComboboxContainsDateFilterTypes(final JComboBoxFixture filterComboBox) {
    assertThat(filterComboBox.contents()).containsOnly(Text.FILTER_TYPE_AFTER.text(), Text.FILTER_TYPE_BEFORE.text(),
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
