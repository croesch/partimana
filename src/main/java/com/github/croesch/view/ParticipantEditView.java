package com.github.croesch.view;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import com.github.croesch.types.CountyCouncil;
import com.github.croesch.types.Denomination;
import com.github.croesch.types.Gender;
import com.github.croesch.types.Participant;
import com.github.croesch.view.api.IParticipantEditView;

/**
 * A panel to edit an {@link Participant}. Provides text fields to edit the different attributes.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011 6:23:15 AM
 */
class ParticipantEditView extends JPanel implements IParticipantEditView {

  /** generated */
  private static final long serialVersionUID = 5388465740510568296L;

  /** the date format to parse the dates entered in the text fields */
  private static final DateFormat DATE_FORMAT = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM);

  /** the text field to edit the persons name */
  private final JTextField firstNameTf = new JTextField();

  /** the text field to edit the persons name */
  private final JTextField lastNameTf = new JTextField();

  /** the text field to edit the persons birth day */
  private final JTextField birthDayTf = new JTextField();

  /** the text field to edit the persons living street */
  private final JTextField livStreetTf = new JTextField();

  /** the text field to edit the persons living post code */
  private final JTextField livPostCodeTf = new JTextField();

  /** the text field to edit the persons living city */
  private final JTextField livCityTf = new JTextField();

  /** the text field to edit the persons postal street */
  private final JTextField posStreetTf = new JTextField();

  /** the text field to edit the persons postal post code */
  private final JTextField posPostCodeTf = new JTextField();

  /** the text field to edit the persons postal city */
  private final JTextField posCityTf = new JTextField();

  /** the text field to edit the persons phone number */
  private final JTextField phoneTf = new JTextField();

  /** the text field to edit the persons fax number */
  private final JTextField faxTf = new JTextField();

  /** the text field to edit the persons mobile phone number */
  private final JTextField mobilePhoneTf = new JTextField();

  /** the text field to edit the phone of the persons parents */
  private final JTextField phoneParentsTf = new JTextField();

  /** the text field to edit the persons mail address */
  private final JTextField mailAddressTf = new JTextField();

  /** the text field to edit the persons bank account number */
  private final JTextField bankAccNumberTf = new JTextField();

  /** the text field to edit the persons bank code number */
  private final JTextField bankCodeNumberTf = new JTextField();

  /** the text field to edit the persons bank */
  private final JTextField bankTf = new JTextField();

  /** the text field to edit the persons until-in-db-date */
  private final JTextField untilInDbTf = new JTextField();

  /** the combo box to edit the persons gender */
  private final JComboBox genderCb = new JComboBox(Gender.values());

  /** the combo box to edit the persons denomination */
  private final JComboBox denominationCb = new JComboBox(Denomination.values());

  /** the combo box to edit the persons county council */
  private final JComboBox countyCouncilCb = new JComboBox(CountyCouncil.values());;

  /** the text area to edit the comment about the person */
  private final JTextArea commentTa = new JTextArea();

  /** the check box to mark that this person can be participant */
  private final JCheckBox possParticipantCb = new JCheckBox("Teilnehmer");

  /** the check box to mark that this person can be staff */
  private final JCheckBox possStaffCb = new JCheckBox("Mitarbeiter");

  /** the check box to mark that this person can be staff youth */
  private final JCheckBox possStaffYouthCb = new JCheckBox("Mitarbeiter-Jugend");

  /** the check box to mark that this person can be board member */
  private final JCheckBox possBoardCb = new JCheckBox("Vorstand");

  /** the check box to mark that this person can be extended board member */
  private final JCheckBox possExtendedBoardCb = new JCheckBox("erweiterter Vorstand");

  /** the check box to mark that this person can be MAK */
  private final JCheckBox possMakCb = new JCheckBox("MAK");

  /** the check box to mark that this person can be AGE */
  private final JCheckBox possAgeCb = new JCheckBox("AGE");

  /** the check box to mark that this person can be seminar member */
  private final JCheckBox possSeminarCb = new JCheckBox("Seminar");

  /** the check box to mark that this person can be kitchen member */
  private final JCheckBox possKitchenCb = new JCheckBox("K\u00FCche");

  /** the check box to mark that this person can be misc. */
  private final JCheckBox possMiscCb = new JCheckBox("Sonstiges");

  /** the label that contains the id of the person */
  private final JLabel idValueLbl = new JLabel("12345");

  /** the label that contains the date since the person is in data base */
  private final JLabel sinceInDbValueLbl = new JLabel("12.12.2003");

  /** panel that contains components to edit the different functions of the person */
  private JPanel possibleFunctionsPanel;

  /**
   * Constructs a new panel, that contains all necessary components to edit the attributes of a {@link Participant}.
   * Also it is able to fill the content of the different components with the values fetched from a given
   * {@link Participant}. Therefore see {@link #setParticipant(Participant)}.
   * 
   * @author croesch
   * @since Date: Jun 28, 2011
   * @see #setParticipant(Participant)
   */
  public ParticipantEditView() {

    setLayout(new MigLayout("", "[][grow][][grow][][grow]", "[][][][][][][][][][][][][][][][]"));

    initNames();

    addComponents();
  }

  /**
   * Adds all components to this panel.
   * 
   * @author croesch
   * @since Date: Jun 28, 2011
   */
  private void addComponents() {
    final JLabel idLbl = new JLabel("ID:");
    add(idLbl, "cell 0 0,alignx trailing");

    add(this.idValueLbl, "cell 1 0");

    final JLabel sinceInDbLbl = new JLabel("Seit");
    add(sinceInDbLbl, "cell 2 0,alignx trailing");

    add(this.sinceInDbValueLbl, "cell 3 0");

    final JLabel untilInDbLbl = new JLabel("Bis");
    add(untilInDbLbl, "cell 4 0,alignx trailing");

    add(this.untilInDbTf, "cell 5 0,growx");

    final JLabel firstNameLbl = new JLabel("Vorname");
    add(firstNameLbl, "cell 0 1,alignx trailing");

    add(this.firstNameTf, "cell 1 1,growx");

    final JLabel lastNameLbl = new JLabel("Nachname");
    add(lastNameLbl, "cell 2 1,alignx trailing");

    add(this.lastNameTf, "cell 3 1,growx");

    final JLabel genderLbl = new JLabel("Geschlecht");
    add(genderLbl, "cell 4 1,alignx trailing");

    add(this.genderCb, "cell 5 1,growx");

    final JLabel birthDayLbl = new JLabel("Geburtstag");
    add(birthDayLbl, "cell 0 2,alignx trailing");

    add(this.birthDayTf, "cell 1 2,growx");

    final JLabel denominationLbl = new JLabel("Konfession");
    add(denominationLbl, "cell 2 2,alignx trailing");

    add(this.denominationCb, "cell 3 2,growx");

    final JLabel countyCouncilLbl = new JLabel("Kreisverwaltung");
    add(countyCouncilLbl, "cell 4 2,alignx trailing");

    add(this.countyCouncilCb, "cell 5 2,growx");

    final JLabel streetLbl = new JLabel("Stra\u00DFe");
    add(streetLbl, "cell 1 3,alignx center");

    final JLabel postCodeLbl = new JLabel("PLZ");
    add(postCodeLbl, "cell 3 3,alignx center");

    final JLabel cityLbl = new JLabel("Ort");
    add(cityLbl, "cell 5 3,alignx center");

    final JLabel livingAddressLbl = new JLabel("Wohnanschrift");
    add(livingAddressLbl, "cell 0 4");

    add(this.livStreetTf, "cell 1 4,growx");

    add(this.livPostCodeTf, "cell 3 4,growx");

    add(this.livCityTf, "cell 5 4,growx");

    final JLabel postToAddressLbl = new JLabel("Postanschrift");
    add(postToAddressLbl, "cell 0 5");

    add(this.posStreetTf, "cell 1 5,growx");

    add(this.posPostCodeTf, "cell 3 5,growx");

    add(this.posCityTf, "cell 5 5,growx");

    final JLabel phoneLbl = new JLabel("Telefon");
    add(phoneLbl, "cell 0 7,alignx trailing");

    add(this.phoneTf, "cell 1 7,growx");

    final JLabel faxLbl = new JLabel("Fax");
    add(faxLbl, "cell 2 7,alignx trailing");

    add(this.faxTf, "cell 3 7,growx");

    final JLabel mobilePhoneLbl = new JLabel("Handy");
    add(mobilePhoneLbl, "cell 4 7,alignx trailing");

    add(this.mobilePhoneTf, "cell 5 7,growx");

    final JLabel phoneParentsLbl = new JLabel("Telefon Eltern");
    add(phoneParentsLbl, "cell 0 8,alignx trailing");

    add(this.phoneParentsTf, "cell 1 8,growx");

    final JLabel mailAddressLbl = new JLabel("Mail");
    add(mailAddressLbl, "cell 2 8,alignx trailing");

    add(this.mailAddressTf, "cell 3 8 3 1,growx");

    final JLabel bankAccNumberLbl = new JLabel("Kontonummer");
    add(bankAccNumberLbl, "cell 0 10,alignx trailing");

    add(this.bankAccNumberTf, "cell 1 10,growx");

    final JLabel bankCodNumberLbl = new JLabel("BLZ");
    add(bankCodNumberLbl, "cell 2 10,alignx trailing");

    add(this.bankCodeNumberTf, "cell 3 10,growx");

    final JLabel bankLbl = new JLabel("Bank");
    add(bankLbl, "cell 4 10,alignx trailing");

    add(this.bankTf, "cell 5 10,growx");

    final JScrollPane commentScrollPane = new JScrollPane();
    add(commentScrollPane, "cell 1 12 5 3,grow");

    commentScrollPane.setViewportView(this.commentTa);

    final JLabel commentLbl = new JLabel("Kommentar");
    add(commentLbl, "cell 0 12,alignx trailing");

    initPossibleFunctionsPanel();

    add(this.possibleFunctionsPanel, "cell 0 15 6 1,growx");
  }

  /**
   * Initializes the {@link #possibleFunctionsPanel} and adds the components to it.
   * 
   * @author croesch
   * @since Date: Jun 28, 2011
   */
  private void initPossibleFunctionsPanel() {
    this.possibleFunctionsPanel = new JPanel();
    this.possibleFunctionsPanel.setLayout(new MigLayout("", "[100px][][][][]", "[][][]"));

    final JLabel functionsLbl = new JLabel("Funktionen");
    this.possibleFunctionsPanel.add(functionsLbl, "cell 0 0,alignx trailing");

    this.possibleFunctionsPanel.add(this.possParticipantCb, "cell 1 0");

    this.possibleFunctionsPanel.add(this.possStaffCb, "cell 2 0");

    this.possibleFunctionsPanel.add(this.possStaffYouthCb, "cell 3 0");

    this.possibleFunctionsPanel.add(this.possBoardCb, "cell 4 0");

    this.possibleFunctionsPanel.add(this.possExtendedBoardCb, "cell 1 1");

    this.possibleFunctionsPanel.add(this.possMakCb, "cell 2 1");

    this.possibleFunctionsPanel.add(this.possAgeCb, "cell 3 1");

    this.possibleFunctionsPanel.add(this.possSeminarCb, "cell 4 1");

    this.possibleFunctionsPanel.add(this.possKitchenCb, "cell 1 2");

    this.possibleFunctionsPanel.add(this.possMiscCb, "cell 2 2");
  }

  /**
   * Initializes the names of all components.
   * 
   * @author croesch
   * @since Date: Jun 28, 2011
   */
  private void initNames() {
    this.firstNameTf.setName("firstNameTF");
    this.lastNameTf.setName("lastNameTF");
    this.genderCb.setName("genderCB");
    this.birthDayTf.setName("birthTF");
    this.denominationCb.setName("denominationCB");
    this.posStreetTf.setName("postalStreetTF");
    this.posPostCodeTf.setName("postalPostCodeTF");
    this.posCityTf.setName("postalCityTF");
    this.countyCouncilCb.setName("countyCouncilCB");
    this.bankTf.setName("bankTF");
    this.bankAccNumberTf.setName("bankAccountNumberTF");
    this.bankCodeNumberTf.setName("bankCodeNumberTF");
    this.commentTa.setName("commentTF");
    this.untilInDbTf.setName("dateUpToInDBTF");
    this.sinceInDbValueLbl.setName("dateSinceInDBLbl");
    this.faxTf.setName("faxTF");
    this.mailAddressTf.setName("mailTF");
    this.mobilePhoneTf.setName("mobilePhoneTF");
    this.phoneTf.setName("phoneTF");
    this.phoneParentsTf.setName("phoneOfParentsTF");
    this.possAgeCb.setName("possibleAGECB");
    this.possBoardCb.setName("possibleBoardCB");
    this.possExtendedBoardCb.setName("possibleExtendedBoardCB");
    this.possKitchenCb.setName("possibleKitchenCB");
    this.possMakCb.setName("possibleMAKCB");
    this.possMiscCb.setName("possibleMiscCB");
    this.possParticipantCb.setName("possibleParticipantCB");
    this.possSeminarCb.setName("possibleSeminarCB");
    this.possStaffCb.setName("possibleStaffCB");
    this.possStaffYouthCb.setName("possibleStaffYouthCB");
    this.livStreetTf.setName("livingStreetTF");
    this.livPostCodeTf.setName("livingPostCodeTF");
    this.livCityTf.setName("livingCityTF");
    this.idValueLbl.setName("idLbl");
  }

  @Override
  public void clear() {
    this.firstNameTf.setText(null);
    this.lastNameTf.setText(null);
    this.genderCb.setSelectedItem(null);
    this.denominationCb.setSelectedItem(null);
    this.birthDayTf.setText(null);
    this.posStreetTf.setText(null);
    this.posPostCodeTf.setText(null);
    this.posCityTf.setText(null);
    this.countyCouncilCb.setSelectedItem(null);
    this.bankTf.setText(null);
    this.bankAccNumberTf.setText(null);
    this.bankCodeNumberTf.setText(null);
    this.commentTa.setText(null);
    this.untilInDbTf.setText(null);
    this.sinceInDbValueLbl.setText(null);
    this.faxTf.setText(null);
    this.mailAddressTf.setText(null);
    this.mobilePhoneTf.setText(null);
    this.phoneTf.setText(null);
    this.phoneParentsTf.setText(null);
    this.possAgeCb.setSelected(false);
    this.possBoardCb.setSelected(false);
    this.possExtendedBoardCb.setSelected(false);
    this.possKitchenCb.setSelected(false);
    this.possMakCb.setSelected(false);
    this.possMiscCb.setSelected(false);
    this.possParticipantCb.setSelected(false);
    this.possSeminarCb.setSelected(false);
    this.possStaffCb.setSelected(false);
    this.possStaffYouthCb.setSelected(false);
    this.livCityTf.setText(null);
    this.livPostCodeTf.setText(null);
    this.livStreetTf.setText(null);
    this.idValueLbl.setText(null);
  }

  @Override
  public void setParticipant(final Participant participant) {
    if (participant == null) {
      clear();
    } else {
      this.firstNameTf.setText(participant.getForeName());
      this.lastNameTf.setText(participant.getLastName());
      this.genderCb.setSelectedItem(participant.getGender());
      this.denominationCb.setSelectedItem(participant.getDenomination());
      this.birthDayTf.setText(DATE_FORMAT.format(participant.getBirthDate()));
      this.posStreetTf.setText(participant.getStreetPostal());
      this.posPostCodeTf.setText(String.valueOf(participant.getPostCodePostal()));
      this.posCityTf.setText(participant.getCityPostal());
      this.countyCouncilCb.setSelectedItem(participant.getCountyCouncil());
      this.bankTf.setText(participant.getBank());
      this.bankAccNumberTf.setText(String.valueOf(participant.getBankAccountNumber()));
      this.bankCodeNumberTf.setText(String.valueOf(participant.getBankCodeNumber()));
      this.commentTa.setText(participant.getComment());
      this.untilInDbTf.setText(DATE_FORMAT.format(participant.getDateUpToInSystem()));
      this.sinceInDbValueLbl.setText(DATE_FORMAT.format(participant.getDateSinceInDataBase()));
      this.faxTf.setText(participant.getFax());
      this.mailAddressTf.setText(participant.getMailAddress());
      this.mobilePhoneTf.setText(participant.getMobilePhone());
      this.phoneTf.setText(participant.getPhone());
      this.phoneParentsTf.setText(participant.getPhoneOfParents());
      this.possAgeCb.setSelected(participant.isPossibleAGE());
      this.possBoardCb.setSelected(participant.isPossibleBoard());
      this.possExtendedBoardCb.setSelected(participant.isPossibleExtendedBoard());
      this.possKitchenCb.setSelected(participant.isPossibleKitchen());
      this.possMakCb.setSelected(participant.isPossibleMAK());
      this.possMiscCb.setSelected(participant.isPossibleMisc());
      this.possParticipantCb.setSelected(participant.isPossibleParticipant());
      this.possSeminarCb.setSelected(participant.isPossibleSeminar());
      this.possStaffCb.setSelected(participant.isPossibleStaff());
      this.possStaffYouthCb.setSelected(participant.isPossibleStaffYouth());
      this.livCityTf.setText(participant.getCity());
      this.livPostCodeTf.setText(String.valueOf(participant.getPostCode()));
      this.livStreetTf.setText(participant.getStreet());
      this.idValueLbl.setText(String.valueOf(participant.getId()));
    }
  }

  @Override
  public String getFirstName() {
    return this.firstNameTf.getText();
  }

  @Override
  public String getLastName() {
    return this.lastNameTf.getText();
  }

  @Override
  public Gender getGender() {
    return (Gender) this.genderCb.getSelectedItem();
  }

  @Override
  public Denomination getDenomination() {
    return (Denomination) this.denominationCb.getSelectedItem();
  }

  @Override
  public Date getBirthDate() {
    try {
      return DATE_FORMAT.parse(this.birthDayTf.getText());
    } catch (final ParseException e) {
      return null;
    }
  }

  @Override
  public String getPostalStreet() {
    return this.posStreetTf.getText();
  }

  @Override
  public int getPostalPostCode() {
    final String text = this.posPostCodeTf.getText();
    if (text == null || text.trim().equals("")) {
      return 0;
    }
    return Integer.parseInt(text);
  }

  @Override
  public String getPostalCity() {
    return this.posCityTf.getText();
  }

  @Override
  public CountyCouncil getCountyCouncil() {
    return (CountyCouncil) this.countyCouncilCb.getSelectedItem();
  }

  @Override
  public String getBank() {
    return this.bankTf.getText();
  }

  @Override
  public int getBankAccountNumber() {
    final String text = this.bankAccNumberTf.getText();
    if (text == null || text.trim().equals("")) {
      return 0;
    }
    return Integer.parseInt(text);
  }

  @Override
  public int getBankCodeNumber() {
    final String text = this.bankCodeNumberTf.getText();
    if (text == null || text.trim().equals("")) {
      return 0;
    }
    return Integer.parseInt(text);
  }

  @Override
  public String getComment() {
    return this.commentTa.getText();
  }

  @Override
  public Date getDateUpToInDataBase() {
    try {
      return DATE_FORMAT.parse(this.untilInDbTf.getText());
    } catch (final ParseException e) {
      return null;
    }
  }

  @Override
  public String getFax() {
    return this.faxTf.getText();
  }

  @Override
  public String getMailAddress() {
    return this.mailAddressTf.getText();
  }

  @Override
  public String getMobilePhone() {
    return this.mobilePhoneTf.getText();
  }

  @Override
  public String getPhone() {
    return this.phoneTf.getText();
  }

  @Override
  public String getPhoneOfParents() {
    return this.phoneParentsTf.getText();
  }

  @Override
  public boolean getPossibleAGE() {
    return this.possAgeCb.isSelected();
  }

  @Override
  public boolean getPossibleBoard() {
    return this.possBoardCb.isSelected();
  }

  @Override
  public boolean getPossibleExtendedBoard() {
    return this.possExtendedBoardCb.isSelected();
  }

  @Override
  public boolean getPossibleKitchen() {
    return this.possKitchenCb.isSelected();
  }

  @Override
  public boolean getPossibleMAK() {
    return this.possMakCb.isSelected();
  }

  @Override
  public boolean getPossibleMisc() {
    return this.possMiscCb.isSelected();
  }

  @Override
  public boolean getPossibleParticipant() {
    return this.possParticipantCb.isSelected();
  }

  @Override
  public boolean getPossibleSeminar() {
    return this.possSeminarCb.isSelected();
  }

  @Override
  public boolean getPossibleStaff() {
    return this.possStaffCb.isSelected();
  }

  @Override
  public boolean getPossibleStaffYouth() {
    return this.possStaffYouthCb.isSelected();
  }

  @Override
  public String getLivingStreet() {
    return this.livStreetTf.getText();
  }

  @Override
  public int getLivingPostCode() {
    final String text = this.livPostCodeTf.getText();
    if (text == null || text.trim().equals("")) {
      return 0;
    }
    return Integer.parseInt(text);
  }

  @Override
  public String getLivingCity() {
    return this.livCityTf.getText();
  }

  @Override
  public long getId() {
    final String text = this.idValueLbl.getText();
    if (text == null || text.trim().equals("")) {
      return 0;
    }
    return Long.parseLong(text);
  }

}
