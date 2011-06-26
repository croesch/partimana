package com.github.croesch.view;

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
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Jun 8, 2011 6:23:15 AM
 */
class ParticipantEditView extends JPanel implements IParticipantEditView {

  private final JTextField firstNameTf;

  private final JTextField lastNameTf;

  private final JTextField birthDayTf;

  private final JTextField livStreetTf;

  private final JTextField livPostCodeTf;

  private final JTextField livCityTf;

  private final JTextField posStreetTf;

  private final JTextField posPostCodeTf;

  private final JTextField posCityTf;

  private final JTextField phoneTf;

  private final JTextField faxTf;

  private final JTextField mobilePhoneTf;

  private final JTextField phoneParentsTf;

  private final JTextField mailAddressTf;

  private final JTextField bankAccNumberTf;

  private final JTextField bankCodNumberTf;

  private final JTextField bankTf;

  private final JTextField untilInDbTf;

  public ParticipantEditView() {

    setLayout(new MigLayout("", "[][grow][][grow][][grow]", "[][][][][][][][][][][][][][][][]"));

    final JLabel idLbl = new JLabel("ID:");
    add(idLbl, "cell 0 0,alignx trailing");

    final JLabel idValueLbl = new JLabel("12345");
    add(idValueLbl, "cell 1 0");

    final JLabel sinceInDbLbl = new JLabel("Seit");
    add(sinceInDbLbl, "cell 2 0,alignx trailing");

    final JLabel sinceInDbValueLbl = new JLabel("12.12.2003");
    add(sinceInDbValueLbl, "cell 3 0");

    final JLabel untilInDbLbl = new JLabel("Bis");
    add(untilInDbLbl, "cell 4 0,alignx trailing");

    this.untilInDbTf = new JTextField();
    add(this.untilInDbTf, "cell 5 0,growx");
    this.untilInDbTf.setColumns(10);

    final JLabel firstNameLbl = new JLabel("Vorname");
    add(firstNameLbl, "cell 0 1,alignx trailing");

    this.firstNameTf = new JTextField();
    add(this.firstNameTf, "cell 1 1,growx");
    this.firstNameTf.setColumns(10);

    final JLabel lastNameLbl = new JLabel("Nachname");
    add(lastNameLbl, "cell 2 1,alignx trailing");

    this.lastNameTf = new JTextField();
    add(this.lastNameTf, "cell 3 1,growx");
    this.lastNameTf.setColumns(10);

    final JLabel genderLbl = new JLabel("Geschlecht");
    add(genderLbl, "cell 4 1,alignx trailing");

    final JComboBox genderCb = new JComboBox(Gender.values());
    add(genderCb, "cell 5 1,growx");

    final JLabel birthDayLbl = new JLabel("Geburtstag");
    add(birthDayLbl, "cell 0 2,alignx trailing");

    this.birthDayTf = new JTextField();
    add(this.birthDayTf, "cell 1 2,growx");
    this.birthDayTf.setColumns(10);

    final JLabel denominationLbl = new JLabel("Konfession");
    add(denominationLbl, "cell 2 2,alignx trailing");

    final JComboBox denominationCb = new JComboBox(Denomination.values());
    add(denominationCb, "cell 3 2,growx");

    final JLabel countyCouncilLbl = new JLabel("Kreisverwaltung");
    add(countyCouncilLbl, "cell 4 2,alignx trailing");

    final JComboBox countyCouncilCb = new JComboBox(CountyCouncil.values());
    add(countyCouncilCb, "cell 5 2,growx");

    final JLabel streetLbl = new JLabel("Stra\u00DFe");
    add(streetLbl, "cell 1 3,alignx center");

    final JLabel postCodeLbl = new JLabel("PLZ");
    add(postCodeLbl, "cell 3 3,alignx center");

    final JLabel cityLbl = new JLabel("Ort");
    add(cityLbl, "cell 5 3,alignx center");

    final JLabel livingAddressLbl = new JLabel("Wohnanschrift");
    add(livingAddressLbl, "cell 0 4");

    this.livStreetTf = new JTextField();
    add(this.livStreetTf, "cell 1 4,growx");
    this.livStreetTf.setColumns(10);

    this.livPostCodeTf = new JTextField();
    add(this.livPostCodeTf, "cell 3 4,growx");
    this.livPostCodeTf.setColumns(10);

    this.livCityTf = new JTextField();
    add(this.livCityTf, "cell 5 4,growx");
    this.livCityTf.setColumns(10);

    final JLabel postToAddressLbl = new JLabel("Postanschrift");
    add(postToAddressLbl, "cell 0 5");

    this.posStreetTf = new JTextField();
    add(this.posStreetTf, "cell 1 5,growx");
    this.posStreetTf.setColumns(10);

    this.posPostCodeTf = new JTextField();
    add(this.posPostCodeTf, "cell 3 5,growx");
    this.posPostCodeTf.setColumns(10);

    this.posCityTf = new JTextField();
    add(this.posCityTf, "cell 5 5,growx");
    this.posCityTf.setColumns(10);

    final JLabel phoneLbl = new JLabel("Telefon");
    add(phoneLbl, "cell 0 7,alignx trailing");

    this.phoneTf = new JTextField();
    add(this.phoneTf, "cell 1 7,growx");
    this.phoneTf.setColumns(10);

    final JLabel faxLbl = new JLabel("Fax");
    add(faxLbl, "cell 2 7,alignx trailing");

    this.faxTf = new JTextField();
    add(this.faxTf, "cell 3 7,growx");
    this.faxTf.setColumns(10);

    final JLabel mobilePhoneLbl = new JLabel("Handy");
    add(mobilePhoneLbl, "cell 4 7,alignx trailing");

    this.mobilePhoneTf = new JTextField();
    add(this.mobilePhoneTf, "cell 5 7,growx");
    this.mobilePhoneTf.setColumns(10);

    final JLabel phoneParentsLbl = new JLabel("Telefon Eltern");
    add(phoneParentsLbl, "cell 0 8,alignx trailing");

    this.phoneParentsTf = new JTextField();
    add(this.phoneParentsTf, "cell 1 8,growx");
    this.phoneParentsTf.setColumns(10);

    final JLabel mailAddressLbl = new JLabel("Mail");
    add(mailAddressLbl, "cell 2 8,alignx trailing");

    this.mailAddressTf = new JTextField();
    add(this.mailAddressTf, "cell 3 8 3 1,growx");
    this.mailAddressTf.setColumns(10);

    final JLabel bankAccNumberLbl = new JLabel("Kontonummer");
    add(bankAccNumberLbl, "cell 0 10,alignx trailing");

    this.bankAccNumberTf = new JTextField();
    add(this.bankAccNumberTf, "cell 1 10,growx");
    this.bankAccNumberTf.setColumns(10);

    final JLabel bankCodNumberLbl = new JLabel("BLZ");
    add(bankCodNumberLbl, "cell 2 10,alignx trailing");

    this.bankCodNumberTf = new JTextField();
    add(this.bankCodNumberTf, "cell 3 10,growx");
    this.bankCodNumberTf.setColumns(10);

    final JLabel bankLbl = new JLabel("Bank");
    add(bankLbl, "cell 4 10,alignx trailing");

    this.bankTf = new JTextField();
    add(this.bankTf, "cell 5 10,growx");
    this.bankTf.setColumns(10);

    final JScrollPane commentScrollPane = new JScrollPane();
    add(commentScrollPane, "cell 1 12 5 3,grow");

    final JTextArea commentTa = new JTextArea();
    commentScrollPane.setViewportView(commentTa);

    final JLabel commentLbl = new JLabel("Kommentar");
    add(commentLbl, "cell 0 12,alignx trailing");

    final JPanel possibleFunctionsPanel = new JPanel();
    add(possibleFunctionsPanel, "cell 0 15 6 1,growx");
    possibleFunctionsPanel.setLayout(new MigLayout("", "[100px][][][][]", "[][][]"));

    final JLabel functionsLbl = new JLabel("Funktionen");
    possibleFunctionsPanel.add(functionsLbl, "cell 0 0,alignx trailing");

    final JCheckBox possParticipantCb = new JCheckBox("Teilnehmer");
    possibleFunctionsPanel.add(possParticipantCb, "cell 1 0");

    final JCheckBox possStaffCb = new JCheckBox("Mitarbeiter");
    possibleFunctionsPanel.add(possStaffCb, "cell 2 0");

    final JCheckBox possStaffYouthCb = new JCheckBox("Mitarbeiter-Jugend");
    possibleFunctionsPanel.add(possStaffYouthCb, "cell 3 0");

    final JCheckBox possBoardCb = new JCheckBox("Vorstand");
    possibleFunctionsPanel.add(possBoardCb, "cell 4 0");

    final JCheckBox possExtendedBoardCb = new JCheckBox("erweiterter Vorstand");
    possibleFunctionsPanel.add(possExtendedBoardCb, "cell 1 1");

    final JCheckBox possMakCb = new JCheckBox("MAK");
    possibleFunctionsPanel.add(possMakCb, "cell 2 1");

    final JCheckBox possAgeCb = new JCheckBox("AGE");
    possibleFunctionsPanel.add(possAgeCb, "cell 3 1");

    final JCheckBox possSeminarCb = new JCheckBox("Seminar");
    possibleFunctionsPanel.add(possSeminarCb, "cell 4 1");

    final JCheckBox possKitchenCb = new JCheckBox("K\u00FCche");
    possibleFunctionsPanel.add(possKitchenCb, "cell 1 2");

    final JCheckBox possMiscCb = new JCheckBox("Sonstiges");
    possibleFunctionsPanel.add(possMiscCb, "cell 2 2");
  }

  @Override
  public void setParticipant(final Participant participant) {
    // TODO Auto-generated method stub

  }

  @Override
  public String getFirstName() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getLastName() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Gender getGender() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Denomination getDenomination() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Date getBirthDate() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getPostalStreet() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getPostalPostCode() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String getPostalCity() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CountyCouncil getCountyCouncil() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getBank() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getBankAccountNumber() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getBankCodeNumber() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String getComment() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Date getDateUpToInDataBase() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getFax() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getMailAddress() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getMobilePhone() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getPhone() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getPhoneOfParents() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean getPossibleAGE() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean getPossibleBoard() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean getPossibleExtendedBoard() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean getPossibleKitchen() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean getPossibleMAK() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean getPossibleMisc() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean getPossibleParticipant() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean getPossibleSeminar() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean getPossibleStaff() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean getPossibleStaffYouth() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String getLivingStreet() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getLivingPostCode() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String getLivingCity() {
    // TODO Auto-generated method stub
    return null;
  }

}
