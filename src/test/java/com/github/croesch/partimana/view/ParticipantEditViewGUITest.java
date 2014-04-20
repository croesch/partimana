package com.github.croesch.partimana.view;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.PartiManaDefaultGUITestCase;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;
import com.github.croesch.partimana.view.api.IParticipantEditView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.edt.GuiTask;
import org.assertj.swing.fixture.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Provides gui tests for {@link ParticipantEditView}
 *
 * @author croesch
 * @since Date: Jun 26, 2011
 */
public class ParticipantEditViewGUITest extends PartiManaDefaultGUITestCase {

  private IParticipantEditView editView;

  private JPanelFixture testView;

  private Participant participant;

  private FrameFixture window;

  @BeforeClass
  public static void first() {
    Locale.setDefault(Locale.GERMAN);
  }

  @Override
  protected void before() {
    final ParticipantEditView view = GuiActionRunner.execute(new GuiQuery<ParticipantEditView>() {
      @Override
      protected ParticipantEditView executeInEDT() throws Throwable {
        return new ParticipantEditView(null);
      }
    });

    participant = new Participant("Mustermann",
                                  "Max",
                                  Gender.FEMALE,
                                  Denomination.OTHER,
                                  new Date(),
                                  "Musterstrasse 12",
                                  12345,
                                  "Musterhausen",
                                  CountyCouncil.OTHER);

    final Date d1 = new Date(123);

    participant.setBank("bank");
    participant.setBankAccountNumber(1);
    participant.setBankCodeNumber(12);
    participant.setComment("comment");
    participant.setDateSinceInDataBase(d1);
    participant.setDateUpToInSystem(d1);
    participant.setDenomination(Denomination.JEWISH);
    participant.setFax("fax");
    participant.setMailAddress("mail");
    participant.setMobilePhone("mobile");
    participant.setPhone("phone");
    participant.setPhoneOfParents("phone");
    participant.setCityPostal("city");
    participant.setPostCodePostal(3124);
    participant.setStreetPostal("street");

    GuiActionRunner.execute(new GuiTask() {

      @Override
      protected void executeInEDT() throws Throwable {
        view.setParticipant(participant);
      }
    });
    view.setName("editView");
    editView = view;

    window = new FrameFixture(robot(), Containers.frameFor(view));
    window.show();
    testView = window.panel("editView");
  }

  @Override
  protected void after() {
    window.cleanUp();
    cleanUp();
  }

  @Test
  public void testClear() {
    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.clear();
      }
    });
    testView.textBox("firstNameTF").requireEmpty();
    testView.textBox("lastNameTF").requireEmpty();
    testView.comboBox("genderCB").requireNoSelection();
    testView.textBox("birthTF").requireEmpty();
    testView.comboBox("denominationCB").requireNoSelection();
    testView.textBox("postalStreetTF").requireEmpty();
    testView.textBox("postalPostCodeTF").requireEmpty();
    testView.textBox("postalCityTF").requireEmpty();
    testView.comboBox("countyCouncilCB").requireNoSelection();
    testView.textBox("bankTF").requireEmpty();
    testView.textBox("bankAccountNumberTF").requireEmpty();
    testView.textBox("bankCodeNumberTF").requireEmpty();
    testView.textBox("commentTF").requireEmpty();
    testView.textBox("dateUpToInDBTF").requireEmpty();
    testView.textBox("faxTF").requireEmpty();
    testView.textBox("mailTF").requireEmpty();
    testView.textBox("mobilePhoneTF").requireEmpty();
    testView.textBox("phoneTF").requireEmpty();
    testView.textBox("phoneOfParentsTF").requireEmpty();
    testView.textBox("livingStreetTF").requireEmpty();
    testView.textBox("livingPostCodeTF").requireEmpty();
    testView.textBox("livingCityTF").requireEmpty();
    final String n = null;
    testView.label("idLbl").requireText(n);
    testView.label("dateSinceInDBLbl").requireText(n);
    assertThat(editView.getBank()).isEmpty();
    assertThat(editView.getBankAccountNumber()).isEqualTo(0);
    assertThat(editView.getBankCodeNumber()).isEqualTo(0);
    assertThat(editView.getBirthDate()).isNull();
    assertThat(editView.getComment()).isEmpty();
    assertThat(editView.getCountyCouncil()).isNull();
    assertThat(editView.getDateUpToInDataBase()).isNull();
    assertThat(editView.getDenomination()).isNull();
    assertThat(editView.getFax()).isEmpty();
    assertThat(editView.getFirstName()).isEmpty();
    assertThat(editView.getGender()).isNull();
    assertThat(editView.getId()).isEqualTo(0);
    assertThat(editView.getLastName()).isEmpty();
    assertThat(editView.getLivingCity()).isEmpty();
    assertThat(editView.getLivingPostCode()).isEqualTo(0);
    assertThat(editView.getLivingStreet()).isEmpty();
    assertThat(editView.getMailAddress()).isEmpty();
    assertThat(editView.getMobilePhone()).isEmpty();
    assertThat(editView.getPhone()).isEmpty();
    assertThat(editView.getPhoneOfParents()).isEmpty();
    assertThat(editView.getPostalCity()).isEmpty();
    assertThat(editView.getPostalPostCode()).isEqualTo(0);
    assertThat(editView.getPostalStreet()).isEmpty();
  }

  @Test
  public void testSetParticipant() {
    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(null);
      }
    });
    testView.textBox("firstNameTF").requireEmpty();
    testView.textBox("lastNameTF").requireEmpty();
    testView.comboBox("genderCB").requireNoSelection();
    testView.textBox("birthTF").requireEmpty();
    testView.comboBox("denominationCB").requireNoSelection();
    testView.textBox("postalStreetTF").requireEmpty();
    testView.textBox("postalPostCodeTF").requireEmpty();
    testView.textBox("postalCityTF").requireEmpty();
    testView.comboBox("countyCouncilCB").requireNoSelection();
    testView.textBox("bankTF").requireEmpty();
    testView.textBox("bankAccountNumberTF").requireEmpty();
    testView.textBox("bankCodeNumberTF").requireEmpty();
    testView.textBox("commentTF").requireEmpty();
    testView.textBox("dateUpToInDBTF").requireEmpty();
    testView.textBox("faxTF").requireEmpty();
    testView.textBox("mailTF").requireEmpty();
    testView.textBox("mobilePhoneTF").requireEmpty();
    testView.textBox("phoneTF").requireEmpty();
    testView.textBox("phoneOfParentsTF").requireEmpty();
    testView.textBox("livingStreetTF").requireEmpty();
    testView.textBox("livingPostCodeTF").requireEmpty();
    testView.textBox("livingCityTF").requireEmpty();
    final String n = null;
    testView.label("idLbl").requireText(n);
    testView.label("dateSinceInDBLbl").requireText(n);
    assertThat(editView.getBank()).isEmpty();
    assertThat(editView.getBankAccountNumber()).isEqualTo(0);
    assertThat(editView.getBankCodeNumber()).isEqualTo(0);
    assertThat(editView.getBirthDate()).isNull();
    assertThat(editView.getComment()).isEmpty();
    assertThat(editView.getCountyCouncil()).isNull();
    assertThat(editView.getDateUpToInDataBase()).isNull();
    assertThat(editView.getDenomination()).isNull();
    assertThat(editView.getFax()).isEmpty();
    assertThat(editView.getFirstName()).isEmpty();
    assertThat(editView.getGender()).isNull();
    assertThat(editView.getId()).isEqualTo(0);
    assertThat(editView.getLastName()).isEmpty();
    assertThat(editView.getLivingCity()).isEmpty();
    assertThat(editView.getLivingPostCode()).isEqualTo(0);
    assertThat(editView.getLivingStreet()).isEmpty();
    assertThat(editView.getMailAddress()).isEmpty();
    assertThat(editView.getMobilePhone()).isEmpty();
    assertThat(editView.getPhone()).isEmpty();
    assertThat(editView.getPhoneOfParents()).isEmpty();
    assertThat(editView.getPostalCity()).isEmpty();
    assertThat(editView.getPostalPostCode()).isEqualTo(0);
    assertThat(editView.getPostalStreet()).isEmpty();
  }

  @Test
  public void testGetFirstName() {
    final JTextComponentFixture testObj = testView.textBox("firstNameTF");

    testObj.requireText(participant.getForeName());
    testObj.deleteText();
    testObj.enterText("Heinz");
    assertThat(editView.getFirstName()).isEqualTo("Heinz");
  }

  @Test
  public void testGetLastName() {
    final JTextComponentFixture testObj = testView.textBox("lastNameTF");

    testObj.requireText(participant.getLastName());
    testObj.deleteText();
    testObj.enterText("Becker");
    assertThat(editView.getLastName()).isEqualTo("Becker");
  }

  @Test
  public void testGetGender() {
    final JComboBoxFixture testObj = testView.comboBox("genderCB");

    testObj.requireSelection(participant.getGender().toString());
    testObj.selectItem(Gender.MALE.toString());
    assertThat(editView.getGender()).isEqualTo(Gender.MALE);
  }

  @Test
  public final void testGetDenomination() {
    final JComboBoxFixture testObj = testView.comboBox("denominationCB");

    testObj.requireSelection(participant.getDenomination().toString());
    testObj.selectItem(Denomination.MUSLIM.toString());
    assertThat(editView.getDenomination()).isEqualTo(Denomination.MUSLIM);
  }

  @Test
  public final void testGetBirthDate() throws ParseException {
    final JTextComponentFixture testObj = testView.textBox("birthTF");
    final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    testObj.requireText(sdf.format(participant.getBirthDate()));
    testObj.deleteText();
    testObj.enterText("24.03.1978");
    assertThat(editView.getBirthDate()).isEqualTo(sdf.parse("24.03.1978"));
  }

  @Test
  public final void testGetPostalStreet() {
    final JTextComponentFixture testObj = testView.textBox("postalStreetTF");
    final String expected = "Postal street 1";

    testObj.requireText(participant.getStreetPostal());
    testObj.deleteText();
    testObj.enterText(expected);
    assertThat(editView.getPostalStreet()).isEqualTo(expected);
  }

  @Test
  public final void testGetPostalPostCode() {
    final JTextComponentFixture testObj = testView.textBox("postalPostCodeTF");
    final int expected = 88897;

    testObj.requireText(String.valueOf(participant.getPostCodePostal()));
    testObj.deleteText();
    testObj.enterText(String.valueOf(expected));
    assertThat(editView.getPostalPostCode()).isEqualTo(expected);
  }

  @Test
  public final void testGetPostalCity() {
    final JTextComponentFixture testObj = testView.textBox("postalCityTF");
    final String expected = "Postal city";

    testObj.requireText(participant.getCityPostal());
    testObj.deleteText();
    testObj.enterText(expected);
    assertThat(editView.getPostalCity()).isEqualTo(expected);
  }

  @Test
  public final void testGetCountyCouncil() {
    final JComboBoxFixture testObj = testView.comboBox("countyCouncilCB");

    testObj.requireSelection(participant.getCountyCouncil().toString());
    testObj.selectItem(CountyCouncil.CITY_SPEYER.toString());
    assertThat(editView.getCountyCouncil()).isEqualTo(CountyCouncil.CITY_SPEYER);
  }

  @Test
  public final void testGetBank() {
    final JTextComponentFixture testObj = testView.textBox("bankTF");
    final String expected = "BANK OF PERSONS";

    testObj.requireText(participant.getBank());
    testObj.deleteText();
    testObj.enterText(expected);
    assertThat(editView.getBank()).isEqualTo(expected);
  }

  @Test
  public final void testGetBankAccountNumber() {
    final JTextComponentFixture testObj = testView.textBox("bankAccountNumberTF");
    final int expected = 987654321;

    testObj.requireText(String.valueOf(participant.getBankAccountNumber()));
    testObj.deleteText();
    testObj.enterText(String.valueOf(expected));
    assertThat(editView.getBankAccountNumber()).isEqualTo(expected);
  }

  @Test
  public final void testGetBankCodeNumber() {
    final JTextComponentFixture testObj = testView.textBox("bankCodeNumberTF");
    final int expected = 123456789;

    testObj.requireText(String.valueOf(participant.getBankCodeNumber()));
    testObj.deleteText();
    testObj.enterText(String.valueOf(expected));
    assertThat(editView.getBankCodeNumber()).isEqualTo(expected);
  }

  @Test
  public final void testGetComment() {
    final JTextComponentFixture testObj = testView.textBox("commentTF");
    final String expected = "This is a cool comment\n..";

    testObj.requireText(participant.getComment());
    testObj.deleteText();
    testObj.enterText(expected);
    assertThat(editView.getComment()).isEqualTo(expected);
  }

  @Test
  public final void testGetDateUpToInDataBase() throws ParseException {
    final JTextComponentFixture testObj = testView.textBox("dateUpToInDBTF");
    final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    testObj.requireText(sdf.format(participant.getDateUpToInSystem()));
    testObj.deleteText();
    testObj.enterText("14.03.1978");
    assertThat(editView.getDateUpToInDataBase()).isEqualTo(sdf.parse("14.03.1978"));
  }

  @Test
  public final void testGetDateSinceInDataBase() throws ParseException {
    final JLabelFixture testObj = testView.label("dateSinceInDBLbl");
    final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    testObj.requireText(sdf.format(participant.getDateSinceInDataBase()));
  }

  @Test
  public final void testGetFax() {
    final JTextComponentFixture testObj = testView.textBox("faxTF");
    final String expected = "1234, fax number";

    testObj.requireText(participant.getFax());
    testObj.deleteText();
    testObj.enterText(expected);
    assertThat(editView.getFax()).isEqualTo(expected);
  }

  @Test
  public final void testGetMailAddress() throws Throwable {
    final JTextComponentFixture testObj = testView.textBox("mailTF");

    testObj.requireText(participant.getMailAddress());
    testObj.deleteText();
    testObj.enterText("mail");
    copy("@");
    GuiActionRunner.execute(new GuiTask() {

      @Override
      protected void executeInEDT() throws Throwable {
        testObj.target().paste();
      }
    });
    robot().waitForIdle();
    testObj.enterText("address.com");
    assertThat(editView.getMailAddress()).isEqualTo("mail@address.com");
  }

  private static void copy(final String toCopy) throws Throwable {
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

  @Test
  public final void testGetMobilePhone() {
    final JTextComponentFixture testObj = testView.textBox("mobilePhoneTF");
    final String expected = "4433, mobile phone";

    testObj.requireText(participant.getMobilePhone());
    testObj.deleteText();
    testObj.enterText(expected);
    assertThat(editView.getMobilePhone()).isEqualTo(expected);
  }

  @Test
  public final void testGetPhone() {
    final JTextComponentFixture testObj = testView.textBox("phoneTF");
    final String expected = "7722, phone";

    testObj.requireText(participant.getPhone());
    testObj.deleteText();
    testObj.enterText(expected);
    assertThat(editView.getPhone()).isEqualTo(expected);
  }

  @Test
  public final void testGetPhoneOfParents() {
    final JTextComponentFixture testObj = testView.textBox("phoneOfParentsTF");
    final String expected = "1133, phone of parents";

    testObj.requireText(participant.getPhoneOfParents());
    testObj.deleteText();
    testObj.enterText(expected);
    assertThat(editView.getPhoneOfParents()).isEqualTo(expected);
  }

  @Test
  public final void testGetLivingStreet() {
    final JTextComponentFixture testObj = testView.textBox("livingStreetTF");
    final String expected = "Living street 1";

    testObj.requireText(participant.getStreet());
    testObj.deleteText();
    testObj.enterText(expected);
    assertThat(editView.getLivingStreet()).isEqualTo(expected);
  }

  @Test
  public final void testGetLivingPostCode() {
    final JTextComponentFixture testObj = testView.textBox("livingPostCodeTF");
    final int expected = 12897;

    testObj.requireText(String.valueOf(participant.getPostCode()));
    testObj.deleteText();
    testObj.enterText(String.valueOf(expected));
    assertThat(editView.getLivingPostCode()).isEqualTo(expected);
  }

  @Test
  public final void testGetLivingCity() {
    final JTextComponentFixture testObj = testView.textBox("livingCityTF");
    final String expected = "Living city";

    testObj.requireText(participant.getCity());
    testObj.deleteText();
    testObj.enterText(expected);
    assertThat(editView.getLivingCity()).isEqualTo(expected);
  }

  @Test
  public final void testGetID() {
    final JLabelFixture testObj = testView.label("idLbl");
    testObj.requireText(String.valueOf(participant.getId()));
    assertThat(editView.getId()).isEqualTo(participant.getId());
  }
}
