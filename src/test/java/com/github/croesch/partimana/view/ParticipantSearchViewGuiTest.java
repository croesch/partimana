package com.github.croesch.partimana.view;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.PartiManaDefaultGUITestCase;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
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
 * Provides test cases for the {@link ParticipantSearchView}.
 *
 * @author croesch
 * @since Date: Nov 2, 2012
 */
public class ParticipantSearchViewGuiTest extends PartiManaDefaultGUITestCase {

  private static final String FC = "filterComposition";

  private FrameFixture searchView;

  private Participant[] participants;

  @Override
  protected void before() {
    this.participants = new Participant[5];
    this.participants[0] = new Participant("Schmidt",
                                           "Hans",
                                           Gender.MALE,
                                           Denomination.NONE,
                                           new Date(1200),
                                           "Strasse 4",
                                           56789,
                                           "Stadt",
                                           CountyCouncil.CITY_NEUSTADT);
    this.participants[1] = new Participant("Mustermann",
                                           "Max",
                                           Gender.MALE,
                                           Denomination.CATHOLIC,
                                           new Date(1200000000),
                                           "Musterstraße 1",
                                           12345,
                                           "Musterhausen",
                                           CountyCouncil.OTHER);
    this.participants[2] = new Participant("Becker",
                                           "Hilde",
                                           Gender.FEMALE,
                                           Denomination.EVANGELIC,
                                           new Date(11200000000L),
                                           "Hauptstraße 41",
                                           64829,
                                           "Saarbrücken",
                                           CountyCouncil.UNKNOWN);
    this.participants[3] = new Participant("Paulson",
                                           "Paula",
                                           Gender.FEMALE,
                                           Denomination.ORTHODOX,
                                           new Date(111200000000L),
                                           "Hinterweg 3",
                                           87663,
                                           "Oslo",
                                           CountyCouncil.CITY_LUDWIGSHAFEN);
    this.participants[4] = new Participant("Hauser",
                                           "Peter",
                                           Gender.MALE,
                                           Denomination.MUSLIM,
                                           new Date(12200000000L),
                                           "Ruhrgasse 124",
                                           34429,
                                           "Ludwigshafen",
                                           CountyCouncil.CITY_LANDAU);

    this.searchView = new FrameFixture(robot(), GuiActionRunner.execute(new GuiQuery<JFrame>() {
      @Override
      protected JFrame executeInEDT() throws Throwable {
        return new ParticipantSearchView("searchingView",
                                         Arrays.asList(ParticipantSearchViewGuiTest.this.participants),
                                         ParticipantSearchViewGuiTest.this);
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
  public void testListInView() throws InterruptedException, InvocationTargetException {
    this.searchView.panel("list").table("participants").requireRowCount(0);
    ParticipantListViewGUITest
        .update(((ParticipantSearchView) this.searchView.target()).getListView(), Arrays.asList(this.participants));
    ParticipantListViewGUITest
        .requireParticipant(this.searchView.panel("list").table("participants"), 0, this.participants[0]);
    ParticipantListViewGUITest
        .requireParticipant(this.searchView.panel("list").table("participants"), 1, this.participants[1]);
    ParticipantListViewGUITest
        .requireParticipant(this.searchView.panel("list").table("participants"), 2, this.participants[2]);
    ParticipantListViewGUITest
        .requireParticipant(this.searchView.panel("list").table("participants"), 3, this.participants[3]);
    ParticipantListViewGUITest
        .requireParticipant(this.searchView.panel("list").table("participants"), 4, this.participants[4]);
  }

  @Test
  public void testSelectItem() throws InterruptedException, InvocationTargetException {
    final JTableFixture table = this.searchView.panel("list").table("participants");
    final JButtonFixture button = this.searchView.button("select");

    table.requireRowCount(0);
    button.requireText(Text.SELECT.text()).requireDisabled();

    ParticipantListViewGUITest
        .update(((ParticipantSearchView) this.searchView.target()).getListView(), Arrays.asList(this.participants));
    ParticipantListViewGUITest.requireParticipant(table, 0, this.participants[0]);
    ParticipantListViewGUITest.requireParticipant(table, 1, this.participants[1]);
    ParticipantListViewGUITest.requireParticipant(table, 2, this.participants[2]);
    ParticipantListViewGUITest.requireParticipant(table, 3, this.participants[3]);
    ParticipantListViewGUITest.requireParticipant(table, 4, this.participants[4]);
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
    assertThat(poll()).isEqualTo(UserAction.PARTICIPANT_SELECTED);

    assertThat(this.searchView.target()).isInstanceOf(ParticipantSearchView.class);
    assertThat(((ParticipantSearchView) this.searchView.target()).getSelectedId())
        .isEqualTo(this.participants[0].getId());

    table.selectRows(2);
    assertThat(((ParticipantSearchView) this.searchView.target()).getSelectedId())
        .isEqualTo(this.participants[2].getId());

    try {
      table.pressKey(KeyEvent.VK_CONTROL);
      table.cell(TableCell.row(2).column(1)).click();
    } finally {
      table.releaseKey(KeyEvent.VK_CONTROL);
    }
    table.requireSelectedRows();
    button.requireDisabled();
    assertThat(((ParticipantSearchView) this.searchView.target()).getSelectedId()).isZero();
  }

  @Test
  public void testFilterComposition() {
    final JComboBoxFixture categoryComboBox = this.searchView.panel(FC).comboBox("category");
    final JComboBoxFixture filterComboBox = this.searchView.panel(FC).comboBox("filterType");
    assertThat(categoryComboBox.contents()).containsOnly(Text.FILTER_CAT_PARTICIPANT_BANK_ACC_NUM.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_BANK_CODE_NUM.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_BANK_NAME.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_BIRTHDAY.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_COMMENT.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_COUNTY_COUNCIL.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_DATE_SINCE.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_DATE_UNTIL.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_DENOMINATION.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_FAX.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_FORE_NAME.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_GENDER.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_LAST_NAME.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_LIVING_CITY.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_LIVING_POSTAL_CODE.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_LIVING_STREET.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_MAIL_ADDRESS.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_MOBILE_PHONE.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_PHONE.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_PHONE_OF_PARENTS.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_POSTAL_CITY.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_POSTAL_POSTAL_CODE.text(),
                                                         Text.FILTER_CAT_PARTICIPANT_POSTAL_STREET.text());
    categoryComboBox.selectItem(Text.FILTER_CAT_PARTICIPANT_BIRTHDAY.text());

    assertComboboxContainsDateFilterTypes(filterComboBox);

    categoryComboBox.selectItem(Text.FILTER_CAT_PARTICIPANT_LAST_NAME.text());
    assertComboboxContainsStringFilterTypes(filterComboBox);

    filterComboBox.selectItem(Text.FILTER_TYPE_CONTAINS.text());
    this.searchView.textBox("filterValue").enterText("a");

    final JTableFixture table = this.searchView.panel("list").table("participants");
    this.searchView.panel("list").table("participants").requireRowCount(3);
    ParticipantListViewGUITest.requireParticipant(table, 0, this.participants[1]);
    ParticipantListViewGUITest.requireParticipant(table, 1, this.participants[3]);
    ParticipantListViewGUITest.requireParticipant(table, 2, this.participants[4]);

    categoryComboBox.selectItem(Text.FILTER_CAT_PARTICIPANT_FORE_NAME.text());
    assertComboboxContainsStringFilterTypes(filterComboBox);

    filterComboBox.selectItem(Text.FILTER_TYPE_STARTS_WITH.text());
    this.searchView.panel(FC).textBox("filterValue").deleteText().enterText("P");
    this.searchView.panel("list").table("participants").requireRowCount(2);
    ParticipantListViewGUITest.requireParticipant(table, 0, this.participants[3]);
    ParticipantListViewGUITest.requireParticipant(table, 1, this.participants[4]);

    categoryComboBox.selectItem(Text.FILTER_CAT_PARTICIPANT_LIVING_CITY.text());
    assertComboboxContainsStringFilterTypes(filterComboBox);

    filterComboBox.selectItem(Text.FILTER_TYPE_NOT_EQUALS_IGNORE_CASE.text());
    this.searchView.panel(FC).textBox("filterValue").deleteText().enterText("mUsTerHaUsEN");
    this.searchView.panel("list").table("participants").requireRowCount(4);
    ParticipantListViewGUITest.requireParticipant(table, 0, this.participants[0]);
    ParticipantListViewGUITest.requireParticipant(table, 1, this.participants[2]);
    ParticipantListViewGUITest.requireParticipant(table, 2, this.participants[3]);
    ParticipantListViewGUITest.requireParticipant(table, 3, this.participants[4]);

    categoryComboBox.selectItem(Text.FILTER_CAT_PARTICIPANT_BIRTHDAY.text());
    assertComboboxContainsDateFilterTypes(filterComboBox);

    filterComboBox.selectItem(Text.FILTER_TYPE_AFTER.text());
    this.searchView.panel(FC).textBox("filterValue").deleteText().enterText("11000000000");
    this.searchView.panel("list").table("participants").requireRowCount(3);
    ParticipantListViewGUITest.requireParticipant(table, 0, this.participants[2]);
    ParticipantListViewGUITest.requireParticipant(table, 1, this.participants[3]);
    ParticipantListViewGUITest.requireParticipant(table, 2, this.participants[4]);
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
