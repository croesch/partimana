package com.github.croesch.view;

import static org.fest.assertions.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.fixture.Containers;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JCheckBoxFixture;
import org.fest.swing.fixture.JComboBoxFixture;
import org.fest.swing.fixture.JLabelFixture;
import org.fest.swing.fixture.JPanelFixture;
import org.fest.swing.fixture.JTextComponentFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.croesch.types.CountyCouncil;
import com.github.croesch.types.Denomination;
import com.github.croesch.types.Gender;
import com.github.croesch.types.Participant;
import com.github.croesch.view.api.IParticipantEditView;

/**
 * Provides gui tests for {@link ParticipantEditView}
 * 
 * @author croesch
 * @since Date: Jun 26, 2011
 */
public class ParticipantEditViewGUITest {

  private IParticipantEditView editView;

  private JPanelFixture testView;

  private Participant participant;

  private FrameFixture window;

  @BeforeClass
  public static void first() {
    Locale.setDefault(Locale.GERMAN);
  }

  /**
   * Sets up the {@link IParticipantEditView} and the {@link JPanelFixture} to test it. Will add a participant to it to
   * test the functionality.
   * 
   * @author croesch
   * @since Date: Jun 26, 2011
   */
  @Before
  public void setUp() {
    final ParticipantEditView view = new ParticipantEditView();

    this.participant = new Participant("Mustermann",
                                       "Max",
                                       Gender.FEMALE,
                                       Denomination.OTHER,
                                       new Date(),
                                       "Musterstrasse 12",
                                       12345,
                                       "Musterhausen",
                                       CountyCouncil.OTHER);

    final Date d1 = new Date(123);

    this.participant.setBank("bank");
    this.participant.setBankAccountNumber(1);
    this.participant.setBankCodeNumber(12);
    this.participant.setComment("comment");
    this.participant.setDateSinceInDataBase(d1);
    this.participant.setDateUpToInSystem(d1);
    this.participant.setDenomination(Denomination.JEWISH);
    this.participant.setFax("fax");
    this.participant.setMailAddress("mail");
    this.participant.setMobilePhone("mobile");
    this.participant.setPhone("phone");
    this.participant.setPhoneOfParents("phone");
    this.participant.setPossibleAGE(true);
    this.participant.setPossibleBoard(true);
    this.participant.setPossibleExtendedBoard(true);
    this.participant.setPossibleKitchen(true);
    this.participant.setPossibleMAK(true);
    this.participant.setPossibleMisc(true);
    this.participant.setPossibleParticipant(true);
    this.participant.setPossibleSeminar(true);
    this.participant.setPossibleStaff(true);
    this.participant.setPossibleStaffYouth(true);
    this.participant.setCityPostal("city");
    this.participant.setPostCodePostal(3124);
    this.participant.setStreetPostal("street");

    view.setParticipant(this.participant);
    view.setName("editView");
    this.editView = view;

    this.window = Containers.frameFixtureFor(view);
    this.window.show();
    this.testView = this.window.panel("editView");
  }

  @After
  public void tearDown() {
    this.window.cleanUp();
  }

  /**
   * Test method for {@link IParticipantEditView#clear()}.
   */
  @Test
  public void testClear() {
    this.editView.clear();
    this.testView.textBox("firstNameTF").requireEmpty();
    this.testView.textBox("lastNameTF").requireEmpty();
    this.testView.comboBox("genderCB").requireNoSelection();
    this.testView.textBox("birthTF").requireEmpty();
    this.testView.comboBox("denominationCB").requireNoSelection();
    this.testView.textBox("postalStreetTF").requireEmpty();
    this.testView.textBox("postalPostCodeTF").requireEmpty();
    this.testView.textBox("postalCityTF").requireEmpty();
    this.testView.comboBox("countyCouncilCB").requireNoSelection();
    this.testView.textBox("bankTF").requireEmpty();
    this.testView.textBox("bankAccountNumberTF").requireEmpty();
    this.testView.textBox("bankCodeNumberTF").requireEmpty();
    this.testView.textBox("commentTF").requireEmpty();
    this.testView.textBox("dateUpToInDBTF").requireEmpty();
    this.testView.textBox("faxTF").requireEmpty();
    this.testView.textBox("mailTF").requireEmpty();
    this.testView.textBox("mobilePhoneTF").requireEmpty();
    this.testView.textBox("phoneTF").requireEmpty();
    this.testView.textBox("phoneOfParentsTF").requireEmpty();
    this.testView.checkBox("possibleAGECB").requireNotSelected();
    this.testView.checkBox("possibleBoardCB").requireNotSelected();
    this.testView.checkBox("possibleExtendedBoardCB").requireNotSelected();
    this.testView.checkBox("possibleKitchenCB").requireNotSelected();
    this.testView.checkBox("possibleMAKCB").requireNotSelected();
    this.testView.checkBox("possibleMiscCB").requireNotSelected();
    this.testView.checkBox("possibleParticipantCB").requireNotSelected();
    this.testView.checkBox("possibleSeminarCB").requireNotSelected();
    this.testView.checkBox("possibleStaffCB").requireNotSelected();
    this.testView.checkBox("possibleStaffYouthCB").requireNotSelected();
    this.testView.textBox("livingStreetTF").requireEmpty();
    this.testView.textBox("livingPostCodeTF").requireEmpty();
    this.testView.textBox("livingCityTF").requireEmpty();
    final String n = null;
    this.testView.label("idLbl").requireText(n);
    this.testView.label("dateSinceInDBLbl").requireText(n);
    assertThat(this.editView.getBank()).isEmpty();
    assertThat(this.editView.getBankAccountNumber()).isEqualTo(0);
    assertThat(this.editView.getBankCodeNumber()).isEqualTo(0);
    assertThat(this.editView.getBirthDate()).isNull();
    assertThat(this.editView.getComment()).isEmpty();
    assertThat(this.editView.getCountyCouncil()).isNull();
    assertThat(this.editView.getDateUpToInDataBase()).isNull();
    assertThat(this.editView.getDenomination()).isNull();
    assertThat(this.editView.getFax()).isEmpty();
    assertThat(this.editView.getFirstName()).isEmpty();
    assertThat(this.editView.getGender()).isNull();
    assertThat(this.editView.getId()).isEqualTo(0);
    assertThat(this.editView.getLastName()).isEmpty();
    assertThat(this.editView.getLivingCity()).isEmpty();
    assertThat(this.editView.getLivingPostCode()).isEqualTo(0);
    assertThat(this.editView.getLivingStreet()).isEmpty();
    assertThat(this.editView.getMailAddress()).isEmpty();
    assertThat(this.editView.getMobilePhone()).isEmpty();
    assertThat(this.editView.getPhone()).isEmpty();
    assertThat(this.editView.getPhoneOfParents()).isEmpty();
    assertThat(this.editView.getPossibleAGE()).isFalse();
    assertThat(this.editView.getPossibleBoard()).isFalse();
    assertThat(this.editView.getPossibleExtendedBoard()).isFalse();
    assertThat(this.editView.getPossibleKitchen()).isFalse();
    assertThat(this.editView.getPossibleMAK()).isFalse();
    assertThat(this.editView.getPossibleMisc()).isFalse();
    assertThat(this.editView.getPossibleParticipant()).isFalse();
    assertThat(this.editView.getPossibleSeminar()).isFalse();
    assertThat(this.editView.getPossibleStaff()).isFalse();
    assertThat(this.editView.getPossibleStaffYouth()).isFalse();
    assertThat(this.editView.getPostalCity()).isEmpty();
    assertThat(this.editView.getPostalPostCode()).isEqualTo(0);
    assertThat(this.editView.getPostalStreet()).isEmpty();
  }

  /**
   * Test method for {@link IParticipantEditView#setParticipant(Participant)}.
   */
  @Test
  public void testSetParticipant() {
    this.editView.setParticipant(null);
    this.testView.textBox("firstNameTF").requireEmpty();
    this.testView.textBox("lastNameTF").requireEmpty();
    this.testView.comboBox("genderCB").requireNoSelection();
    this.testView.textBox("birthTF").requireEmpty();
    this.testView.comboBox("denominationCB").requireNoSelection();
    this.testView.textBox("postalStreetTF").requireEmpty();
    this.testView.textBox("postalPostCodeTF").requireEmpty();
    this.testView.textBox("postalCityTF").requireEmpty();
    this.testView.comboBox("countyCouncilCB").requireNoSelection();
    this.testView.textBox("bankTF").requireEmpty();
    this.testView.textBox("bankAccountNumberTF").requireEmpty();
    this.testView.textBox("bankCodeNumberTF").requireEmpty();
    this.testView.textBox("commentTF").requireEmpty();
    this.testView.textBox("dateUpToInDBTF").requireEmpty();
    this.testView.textBox("faxTF").requireEmpty();
    this.testView.textBox("mailTF").requireEmpty();
    this.testView.textBox("mobilePhoneTF").requireEmpty();
    this.testView.textBox("phoneTF").requireEmpty();
    this.testView.textBox("phoneOfParentsTF").requireEmpty();
    this.testView.checkBox("possibleAGECB").requireNotSelected();
    this.testView.checkBox("possibleBoardCB").requireNotSelected();
    this.testView.checkBox("possibleExtendedBoardCB").requireNotSelected();
    this.testView.checkBox("possibleKitchenCB").requireNotSelected();
    this.testView.checkBox("possibleMAKCB").requireNotSelected();
    this.testView.checkBox("possibleMiscCB").requireNotSelected();
    this.testView.checkBox("possibleParticipantCB").requireNotSelected();
    this.testView.checkBox("possibleSeminarCB").requireNotSelected();
    this.testView.checkBox("possibleStaffCB").requireNotSelected();
    this.testView.checkBox("possibleStaffYouthCB").requireNotSelected();
    this.testView.textBox("livingStreetTF").requireEmpty();
    this.testView.textBox("livingPostCodeTF").requireEmpty();
    this.testView.textBox("livingCityTF").requireEmpty();
    final String n = null;
    this.testView.label("idLbl").requireText(n);
    this.testView.label("dateSinceInDBLbl").requireText(n);
    assertThat(this.editView.getBank()).isEmpty();
    assertThat(this.editView.getBankAccountNumber()).isEqualTo(0);
    assertThat(this.editView.getBankCodeNumber()).isEqualTo(0);
    assertThat(this.editView.getBirthDate()).isNull();
    assertThat(this.editView.getComment()).isEmpty();
    assertThat(this.editView.getCountyCouncil()).isNull();
    assertThat(this.editView.getDateUpToInDataBase()).isNull();
    assertThat(this.editView.getDenomination()).isNull();
    assertThat(this.editView.getFax()).isEmpty();
    assertThat(this.editView.getFirstName()).isEmpty();
    assertThat(this.editView.getGender()).isNull();
    assertThat(this.editView.getId()).isEqualTo(0);
    assertThat(this.editView.getLastName()).isEmpty();
    assertThat(this.editView.getLivingCity()).isEmpty();
    assertThat(this.editView.getLivingPostCode()).isEqualTo(0);
    assertThat(this.editView.getLivingStreet()).isEmpty();
    assertThat(this.editView.getMailAddress()).isEmpty();
    assertThat(this.editView.getMobilePhone()).isEmpty();
    assertThat(this.editView.getPhone()).isEmpty();
    assertThat(this.editView.getPhoneOfParents()).isEmpty();
    assertThat(this.editView.getPossibleAGE()).isFalse();
    assertThat(this.editView.getPossibleBoard()).isFalse();
    assertThat(this.editView.getPossibleExtendedBoard()).isFalse();
    assertThat(this.editView.getPossibleKitchen()).isFalse();
    assertThat(this.editView.getPossibleMAK()).isFalse();
    assertThat(this.editView.getPossibleMisc()).isFalse();
    assertThat(this.editView.getPossibleParticipant()).isFalse();
    assertThat(this.editView.getPossibleSeminar()).isFalse();
    assertThat(this.editView.getPossibleStaff()).isFalse();
    assertThat(this.editView.getPossibleStaffYouth()).isFalse();
    assertThat(this.editView.getPostalCity()).isEmpty();
    assertThat(this.editView.getPostalPostCode()).isEqualTo(0);
    assertThat(this.editView.getPostalStreet()).isEmpty();
  }

  /**
   * Test method for {@link IParticipantEditView#getFirstName()}.
   */
  @Test
  public void testGetFirstName() {
    final JTextComponentFixture testObj = this.testView.textBox("firstNameTF");

    testObj.requireText(this.participant.getForeName());
    testObj.deleteText();
    testObj.enterText("Heinz");
    assertThat(this.editView.getFirstName()).isEqualTo("Heinz");
  }

  /**
   * Test method for {@link IParticipantEditView#getLastName()}.
   */
  @Test
  public void testGetLastName() {
    final JTextComponentFixture testObj = this.testView.textBox("lastNameTF");

    testObj.requireText(this.participant.getLastName());
    testObj.deleteText();
    testObj.enterText("Becker");
    assertThat(this.editView.getLastName()).isEqualTo("Becker");
  }

  /**
   * Test method for {@link IParticipantEditView#getGender()}.
   */
  @Test
  public void testGetGender() {
    final JComboBoxFixture testObj = this.testView.comboBox("genderCB");

    testObj.requireSelection(this.participant.getGender().toString());
    testObj.selectItem(Gender.MALE.toString());
    assertThat(this.editView.getGender()).isEqualTo(Gender.MALE);
  }

  /**
   * Test method for {@link IParticipantEditView#getDenomination()}.
   */
  @Test
  public final void testGetDenomination() {
    final JComboBoxFixture testObj = this.testView.comboBox("denominationCB");

    testObj.requireSelection(this.participant.getDenomination().toString());
    testObj.selectItem(Denomination.MUSLIM.toString());
    assertThat(this.editView.getDenomination()).isEqualTo(Denomination.MUSLIM);
  }

  /**
   * Test method for {@link IParticipantEditView#getBirthDate()}.
   * 
   * @throws ParseException
   */
  @Test
  public final void testGetBirthDate() throws ParseException {
    final JTextComponentFixture testObj = this.testView.textBox("birthTF");
    final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    testObj.requireText(sdf.format(this.participant.getBirthDate()));
    testObj.deleteText();
    testObj.enterText("24.03.1978");
    assertThat(this.editView.getBirthDate()).isEqualTo(sdf.parse("24.03.1978"));
  }

  /**
   * Test method for {@link IParticipantEditView#getPostalStreet()}.
   */
  @Test
  public final void testGetPostalStreet() {
    final JTextComponentFixture testObj = this.testView.textBox("postalStreetTF");
    final String expected = "Postal street 1";

    testObj.requireText(this.participant.getStreetPostal());
    testObj.deleteText();
    testObj.enterText(expected);
    assertThat(this.editView.getPostalStreet()).isEqualTo(expected);
  }

  /**
   * Test method for {@link IParticipantEditView#getPostalPostCode()}.
   */
  @Test
  public final void testGetPostalPostCode() {
    final JTextComponentFixture testObj = this.testView.textBox("postalPostCodeTF");
    final int expected = 88897;

    testObj.requireText(String.valueOf(this.participant.getPostCodePostal()));
    testObj.deleteText();
    testObj.enterText(String.valueOf(expected));
    assertThat(this.editView.getPostalPostCode()).isEqualTo(expected);
  }

  /**
   * Test method for {@link IParticipantEditView#getPostalCity()}.
   */
  @Test
  public final void testGetPostalCity() {
    final JTextComponentFixture testObj = this.testView.textBox("postalCityTF");
    final String expected = "Postal city";

    testObj.requireText(this.participant.getCityPostal());
    testObj.deleteText();
    testObj.enterText(expected);
    assertThat(this.editView.getPostalCity()).isEqualTo(expected);
  }

  /**
   * Test method for {@link IParticipantEditView#getCountyCouncil()}.
   */
  @Test
  public final void testGetCountyCouncil() {
    final JComboBoxFixture testObj = this.testView.comboBox("countyCouncilCB");

    testObj.requireSelection(this.participant.getCountyCouncil().toString());
    testObj.selectItem(CountyCouncil.CITY_SPEYER.toString());
    assertThat(this.editView.getCountyCouncil()).isEqualTo(CountyCouncil.CITY_SPEYER);
  }

  /**
   * Test method for {@link IParticipantEditView#getBank()}.
   */
  @Test
  public final void testGetBank() {
    final JTextComponentFixture testObj = this.testView.textBox("bankTF");
    final String expected = "BANK OF PERSONS";

    testObj.requireText(this.participant.getBank());
    testObj.deleteText();
    testObj.enterText(expected);
    assertThat(this.editView.getBank()).isEqualTo(expected);
  }

  /**
   * Test method for {@link IParticipantEditView#getBankAccountNumber()}.
   */
  @Test
  public final void testGetBankAccountNumber() {
    final JTextComponentFixture testObj = this.testView.textBox("bankAccountNumberTF");
    final int expected = 987654321;

    testObj.requireText(String.valueOf(this.participant.getBankAccountNumber()));
    testObj.deleteText();
    testObj.enterText(String.valueOf(expected));
    assertThat(this.editView.getBankAccountNumber()).isEqualTo(expected);
  }

  /**
   * Test method for {@link IParticipantEditView#getBankCodeNumber()}.
   */
  @Test
  public final void testGetBankCodeNumber() {
    final JTextComponentFixture testObj = this.testView.textBox("bankCodeNumberTF");
    final int expected = 123456789;

    testObj.requireText(String.valueOf(this.participant.getBankCodeNumber()));
    testObj.deleteText();
    testObj.enterText(String.valueOf(expected));
    assertThat(this.editView.getBankCodeNumber()).isEqualTo(expected);
  }

  /**
   * Test method for {@link IParticipantEditView#getComment()}.
   */
  @Test
  public final void testGetComment() {
    final JTextComponentFixture testObj = this.testView.textBox("commentTF");
    final String expected = "This is a cool comment\n..";

    testObj.requireText(this.participant.getComment());
    testObj.deleteText();
    testObj.enterText(expected);
    assertThat(this.editView.getComment()).isEqualTo(expected);
  }

  /**
   * Test method for {@link IParticipantEditView#getDateUpToInDataBase()}.
   * 
   * @throws ParseException
   */
  @Test
  public final void testGetDateUpToInDataBase() throws ParseException {
    final JTextComponentFixture testObj = this.testView.textBox("dateUpToInDBTF");
    final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    testObj.requireText(sdf.format(this.participant.getDateUpToInSystem()));
    testObj.deleteText();
    testObj.enterText("14.03.1978");
    assertThat(this.editView.getDateUpToInDataBase()).isEqualTo(sdf.parse("14.03.1978"));
  }

  /**
   * Test method for {@link IParticipantEditView}.
   * 
   * @throws ParseException
   */
  @Test
  public final void testGetDateSinceInDataBase() throws ParseException {
    final JLabelFixture testObj = this.testView.label("dateSinceInDBLbl");
    final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    testObj.requireText(sdf.format(this.participant.getDateSinceInDataBase()));
  }

  /**
   * Test method for {@link IParticipantEditView#getFax()}.
   */
  @Test
  public final void testGetFax() {
    final JTextComponentFixture testObj = this.testView.textBox("faxTF");
    final String expected = "1234, fax number";

    testObj.requireText(this.participant.getFax());
    testObj.deleteText();
    testObj.enterText(expected);
    assertThat(this.editView.getFax()).isEqualTo(expected);
  }

  /**
   * Test method for {@link IParticipantEditView#getMailAddress()}.
   * 
   * @throws Throwable
   */
  @Test
  public final void testGetMailAddress() throws Throwable {
    final JTextComponentFixture testObj = this.testView.textBox("mailTF");

    testObj.requireText(this.participant.getMailAddress());
    testObj.deleteText();
    testObj.enterText("mail");
    copy("@");
    GuiActionRunner.execute(new GuiTask() {

      @Override
      protected void executeInEDT() throws Throwable {
        testObj.target.paste();
      }
    });
    testObj.robot.waitForIdle();
    testObj.enterText("address.com");
    assertThat(this.editView.getMailAddress()).isEqualTo("mail@address.com");
  }

  private static final void copy(final String toCopy) throws Throwable {
    GuiActionRunner.execute(new GuiTask() {

      @Override
      protected void executeInEDT() throws Throwable {
        final JTextComponent txt = new JTextArea();
        txt.setText(toCopy);
        txt.selectAll();
        txt.copy();
      }
    });
  }

  /**
   * Test method for {@link IParticipantEditView#getMobilePhone()}.
   */
  @Test
  public final void testGetMobilePhone() {
    final JTextComponentFixture testObj = this.testView.textBox("mobilePhoneTF");
    final String expected = "4433, mobile phone";

    testObj.requireText(this.participant.getMobilePhone());
    testObj.deleteText();
    testObj.enterText(expected);
    assertThat(this.editView.getMobilePhone()).isEqualTo(expected);
  }

  /**
   * Test method for {@link IParticipantEditView#getPhone()}.
   */
  @Test
  public final void testGetPhone() {
    final JTextComponentFixture testObj = this.testView.textBox("phoneTF");
    final String expected = "7722, phone";

    testObj.requireText(this.participant.getPhone());
    testObj.deleteText();
    testObj.enterText(expected);
    assertThat(this.editView.getPhone()).isEqualTo(expected);
  }

  /**
   * Test method for {@link IParticipantEditView#getPhoneOfParents()}.
   */
  @Test
  public final void testGetPhoneOfParents() {
    final JTextComponentFixture testObj = this.testView.textBox("phoneOfParentsTF");
    final String expected = "1133, phone of parents";

    testObj.requireText(this.participant.getPhoneOfParents());
    testObj.deleteText();
    testObj.enterText(expected);
    assertThat(this.editView.getPhoneOfParents()).isEqualTo(expected);
  }

  /**
   * Test method for {@link IParticipantEditView#getPossibleAGE()}.
   */
  @Test
  public final void testGetPossibleAGE() {
    final JCheckBoxFixture testObj = this.testView.checkBox("possibleAGECB");

    requireCheckBoxSelected(testObj, this.participant.isPossibleAGE());
    testObj.uncheck();
    assertThat(this.editView.getPossibleAGE()).isFalse();
    testObj.check();
    assertThat(this.editView.getPossibleAGE()).isTrue();
  }

  private static void requireCheckBoxSelected(final JCheckBoxFixture cb, final boolean selected) {
    if (selected) {
      cb.requireSelected();
    } else {
      cb.requireNotSelected();
    }
  }

  /**
   * Test method for {@link IParticipantEditView#getPossibleBoard()}.
   */
  @Test
  public final void testGetPossibleBoard() {
    final JCheckBoxFixture testObj = this.testView.checkBox("possibleBoardCB");

    requireCheckBoxSelected(testObj, this.participant.isPossibleBoard());
    testObj.uncheck();
    assertThat(this.editView.getPossibleBoard()).isFalse();
    testObj.check();
    assertThat(this.editView.getPossibleBoard()).isTrue();
  }

  /**
   * Test method for {@link IParticipantEditView#getPossibleExtendedBoard()}.
   */
  @Test
  public final void testGetPossibleExtendedBoard() {
    final JCheckBoxFixture testObj = this.testView.checkBox("possibleExtendedBoardCB");

    requireCheckBoxSelected(testObj, this.participant.isPossibleExtendedBoard());
    testObj.uncheck();
    assertThat(this.editView.getPossibleExtendedBoard()).isFalse();
    testObj.check();
    assertThat(this.editView.getPossibleExtendedBoard()).isTrue();
  }

  /**
   * Test method for {@link IParticipantEditView#getPossibleKitchen()}.
   */
  @Test
  public final void testGetPossibleKitchen() {
    final JCheckBoxFixture testObj = this.testView.checkBox("possibleKitchenCB");

    requireCheckBoxSelected(testObj, this.participant.isPossibleKitchen());
    testObj.uncheck();
    assertThat(this.editView.getPossibleKitchen()).isFalse();
    testObj.check();
    assertThat(this.editView.getPossibleKitchen()).isTrue();
  }

  /**
   * Test method for {@link IParticipantEditView#getPossibleMAK()}.
   */
  @Test
  public final void testGetPossibleMAK() {
    final JCheckBoxFixture testObj = this.testView.checkBox("possibleMAKCB");

    requireCheckBoxSelected(testObj, this.participant.isPossibleMAK());
    testObj.uncheck();
    assertThat(this.editView.getPossibleMAK()).isFalse();
    testObj.check();
    assertThat(this.editView.getPossibleMAK()).isTrue();
  }

  /**
   * Test method for {@link IParticipantEditView#getPossibleMisc()}.
   */
  @Test
  public final void testGetPossibleMisc() {
    final JCheckBoxFixture testObj = this.testView.checkBox("possibleMiscCB");

    requireCheckBoxSelected(testObj, this.participant.isPossibleMisc());
    testObj.uncheck();
    assertThat(this.editView.getPossibleMisc()).isFalse();
    testObj.check();
    assertThat(this.editView.getPossibleMisc()).isTrue();
  }

  /**
   * Test method for {@link IParticipantEditView#getPossibleParticipant()}.
   */
  @Test
  public final void testGetPossibleParticipant() {
    final JCheckBoxFixture testObj = this.testView.checkBox("possibleParticipantCB");

    requireCheckBoxSelected(testObj, this.participant.isPossibleParticipant());
    testObj.uncheck();
    assertThat(this.editView.getPossibleParticipant()).isFalse();
    testObj.check();
    assertThat(this.editView.getPossibleParticipant()).isTrue();
  }

  /**
   * Test method for {@link IParticipantEditView#getPossibleSeminar()}.
   */
  @Test
  public final void testGetPossibleSeminar() {
    final JCheckBoxFixture testObj = this.testView.checkBox("possibleSeminarCB");

    requireCheckBoxSelected(testObj, this.participant.isPossibleSeminar());
    testObj.uncheck();
    assertThat(this.editView.getPossibleSeminar()).isFalse();
    testObj.check();
    assertThat(this.editView.getPossibleSeminar()).isTrue();
  }

  /**
   * Test method for {@link IParticipantEditView#getPossibleStaff()}.
   */
  @Test
  public final void testGetPossibleStaff() {
    final JCheckBoxFixture testObj = this.testView.checkBox("possibleStaffCB");

    requireCheckBoxSelected(testObj, this.participant.isPossibleStaff());
    testObj.uncheck();
    assertThat(this.editView.getPossibleStaff()).isFalse();
    testObj.check();
    assertThat(this.editView.getPossibleStaff()).isTrue();
  }

  /**
   * Test method for {@link IParticipantEditView#getPossibleStaffYouth()}.
   */
  @Test
  public final void testGetPossibleStaffYouth() {
    final JCheckBoxFixture testObj = this.testView.checkBox("possibleStaffYouthCB");

    requireCheckBoxSelected(testObj, this.participant.isPossibleStaffYouth());
    testObj.uncheck();
    assertThat(this.editView.getPossibleStaffYouth()).isFalse();
    testObj.check();
    assertThat(this.editView.getPossibleStaffYouth()).isTrue();
  }

  /**
   * Test method for {@link IParticipantEditView#getLivingStreet()}.
   */
  @Test
  public final void testGetLivingStreet() {
    final JTextComponentFixture testObj = this.testView.textBox("livingStreetTF");
    final String expected = "Living street 1";

    testObj.requireText(this.participant.getStreet());
    testObj.deleteText();
    testObj.enterText(expected);
    assertThat(this.editView.getLivingStreet()).isEqualTo(expected);
  }

  /**
   * Test method for {@link IParticipantEditView#getLivingPostCode()}.
   */
  @Test
  public final void testGetLivingPostCode() {
    final JTextComponentFixture testObj = this.testView.textBox("livingPostCodeTF");
    final int expected = 12897;

    testObj.requireText(String.valueOf(this.participant.getPostCode()));
    testObj.deleteText();
    testObj.enterText(String.valueOf(expected));
    assertThat(this.editView.getLivingPostCode()).isEqualTo(expected);
  }

  /**
   * Test method for {@link IParticipantEditView#getLivingCity()}.
   */
  @Test
  public final void testGetLivingCity() {
    final JTextComponentFixture testObj = this.testView.textBox("livingCityTF");
    final String expected = "Living city";

    testObj.requireText(this.participant.getCity());
    testObj.deleteText();
    testObj.enterText(expected);
    assertThat(this.editView.getLivingCity()).isEqualTo(expected);
  }

  /**
   * Test method for {@link IParticipantEditView#getID()}.
   */
  @Test
  public final void testGetID() {
    final JLabelFixture testObj = this.testView.label("idLbl");
    testObj.requireText(String.valueOf(this.participant.getId()));
    assertThat(this.editView.getId()).isEqualTo(this.participant.getId());
  }

}