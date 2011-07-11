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

import com.github.croesch.i18n.Text;
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
  /**
   * TODO i18n of the labels <br>
   * TODO improve check of numeric text fields
   */

  /** generated */
  private static final long serialVersionUID = 5388465740510568296L;

  /** the date format to parse the dates entered in the text fields */
  private final DateFormat dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM);

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
  private final JCheckBox possParticipantCb = new JCheckBox(Text.PARTICIPANT_CAMP_PARTICIPANT.text());

  /** the check box to mark that this person can be staff */
  private final JCheckBox possStaffCb = new JCheckBox(Text.PARTICIPANT_STAFF_GENERAL.text());

  /** the check box to mark that this person can be staff youth */
  private final JCheckBox possStaffYouthCb = new JCheckBox(Text.PARTICIPANT_STAFF_YOUTH.text());

  /** the check box to mark that this person can be board member */
  private final JCheckBox possBoardCb = new JCheckBox(Text.PARTICIPANT_BOARD.text());

  /** the check box to mark that this person can be extended board member */
  private final JCheckBox possExtendedBoardCb = new JCheckBox(Text.PARTICIPANT_EXTENDED_BOARD.text());

  /** the check box to mark that this person can be MAK */
  private final JCheckBox possMakCb = new JCheckBox(Text.PARTICIPANT_MAK.text());

  /** the check box to mark that this person can be AGE */
  private final JCheckBox possAgeCb = new JCheckBox(Text.PARTICIPANT_AGE.text());

  /** the check box to mark that this person can be seminar member */
  private final JCheckBox possSeminarCb = new JCheckBox(Text.PARTICIPANT_SEMINAR.text());

  /** the check box to mark that this person can be kitchen member */
  private final JCheckBox possKitchenCb = new JCheckBox(Text.PARTICIPANT_CAMP_KITCHEN.text());

  /** the check box to mark that this person can be misc. */
  private final JCheckBox possMiscCb = new JCheckBox(Text.PARTICIPANT_MISC.text());

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

    final int space = 15;
    setLayout(new MigLayout("fill, insets 0",
                            "[110px!, sg t, align trailing][grow, fill, sg f][sg t, align trailing][grow, fill, sg f][sg t, align trailing][grow, fill, sg f]",
                            "[sg]" + space + "[sg][sg]" + space + "[sg][sg][sg]" + space + "[sg][sg]" + space + "[sg]"
                                    + space + "[sg][sg][sg]" + space + "[][grow]"));

    initNames();

    addComponents();

    clear();
  }

  /**
   * Adds all components to this panel.
   * 
   * @author croesch
   * @since Date: Jun 28, 2011
   */
  private void addComponents() {
    final JLabel idLbl = new JLabel(Text.PARTICIPANT_ID.text());
    add(idLbl, "cell 0 0");

    add(this.idValueLbl, "cell 1 0");

    final JLabel sinceInDbLbl = new JLabel(Text.PARTICIPANT_SINCE.text());
    add(sinceInDbLbl, "cell 2 0");

    add(this.sinceInDbValueLbl, "cell 3 0");

    final JLabel untilInDbLbl = new JLabel(Text.PARTICIPANT_UNTIL.text());
    add(untilInDbLbl, "cell 4 0");

    add(this.untilInDbTf, "cell 5 0");

    final JLabel firstNameLbl = new JLabel(Text.PARTICIPANT_FORENAME.text());
    add(firstNameLbl, "cell 0 1");

    add(this.firstNameTf, "cell 1 1");

    final JLabel lastNameLbl = new JLabel(Text.PARTICIPANT_LASTNAME.text());
    add(lastNameLbl, "cell 2 1");

    add(this.lastNameTf, "cell 3 1");

    final JLabel genderLbl = new JLabel(Text.PARTICIPANT_GENDER.text());
    add(genderLbl, "cell 4 1");

    add(this.genderCb, "cell 5 1");

    final JLabel birthDayLbl = new JLabel(Text.PARTICIPANT_BIRTHDAY.text());
    add(birthDayLbl, "cell 0 2");

    add(this.birthDayTf, "cell 1 2");

    final JLabel denominationLbl = new JLabel(Text.PARTICIPANT_DENOMINTAION.text());
    add(denominationLbl, "cell 2 2");

    add(this.denominationCb, "cell 3 2");

    final JLabel countyCouncilLbl = new JLabel(Text.PARTICIPANT_COUNTY_COUNCIL.text());
    add(countyCouncilLbl, "cell 4 2");

    add(this.countyCouncilCb, "cell 5 2");

    final JLabel streetLbl = new JLabel(Text.STREET.text());
    add(streetLbl, "cell 1 3, grow 0, alignx center");

    final JLabel postCodeLbl = new JLabel(Text.POST_CODE.text());
    add(postCodeLbl, "cell 3 3, grow 0, alignx center");

    final JLabel cityLbl = new JLabel(Text.CITY.text());
    add(cityLbl, "cell 5 3, grow 0, alignx center");

    final JLabel livingAddressLbl = new JLabel(Text.PARTICIPANT_ADDRESS_LIVING.text());
    add(livingAddressLbl, "cell 0 4");

    add(this.livStreetTf, "cell 1 4");

    add(this.livPostCodeTf, "cell 3 4");

    add(this.livCityTf, "cell 5 4");

    final JLabel postToAddressLbl = new JLabel(Text.PARTICIPANT_ADDRESS_POSTAL.text());
    add(postToAddressLbl, "cell 0 5");

    add(this.posStreetTf, "cell 1 5");

    add(this.posPostCodeTf, "cell 3 5");

    add(this.posCityTf, "cell 5 5");

    final JLabel phoneLbl = new JLabel(Text.PARTICIPANT_PHONE.text());
    add(phoneLbl, "cell 0 6");

    add(this.phoneTf, "cell 1 6");

    final JLabel faxLbl = new JLabel(Text.PARTICIPANT_FAX.text());
    add(faxLbl, "cell 2 6");

    add(this.faxTf, "cell 3 6");

    final JLabel mobilePhoneLbl = new JLabel(Text.PARTICIPANT_MOBILE_PHONE.text());
    add(mobilePhoneLbl, "cell 4 6");

    add(this.mobilePhoneTf, "cell 5 6");

    final JLabel phoneParentsLbl = new JLabel(Text.PARTICIPANT_PHONE_OF_PARENTS.text());
    add(phoneParentsLbl, "cell 0 7");

    add(this.phoneParentsTf, "cell 1 7");

    final JLabel mailAddressLbl = new JLabel(Text.PARTICIPANT_MAIL_ADDRESS.text());
    add(mailAddressLbl, "cell 2 7");

    add(this.mailAddressTf, "cell 3 7 3 1");

    final JLabel bankAccNumberLbl = new JLabel(Text.PARTICIPANT_BANK_ACCOUNT_NUMBER.text());
    add(bankAccNumberLbl, "cell 0 8");

    add(this.bankAccNumberTf, "cell 1 8");

    final JLabel bankCodNumberLbl = new JLabel(Text.PARTICIPANT_BANK_CODE_NUMBER.text());
    add(bankCodNumberLbl, "cell 2 8");

    add(this.bankCodeNumberTf, "cell 3 8");

    final JLabel bankLbl = new JLabel(Text.PARTICIPANT_BANK_NAME.text());
    add(bankLbl, "cell 4 8");

    add(this.bankTf, "cell 5 8");

    final JScrollPane commentScrollPane = new JScrollPane();
    add(commentScrollPane, "cell 1 9 5 3,grow");

    commentScrollPane.setViewportView(this.commentTa);

    final JLabel commentLbl = new JLabel(Text.PARTICIPANT_COMMENT.text());
    add(commentLbl, "cell 0 9");

    initPossibleFunctionsPanel();

    add(this.possibleFunctionsPanel, "cell 0 12 6 1, alignx leading");
  }

  /**
   * Initializes the {@link #possibleFunctionsPanel} and adds the components to it.
   * 
   * @author croesch
   * @since Date: Jun 28, 2011
   */
  private void initPossibleFunctionsPanel() {
    this.possibleFunctionsPanel = new JPanel();
    this.possibleFunctionsPanel.setLayout(new MigLayout("", "[110px!][][][][][]", "[sg][sg]"));

    final JLabel functionsLbl = new JLabel(Text.PARTICIPANT_FUNCTIONS.text());
    this.possibleFunctionsPanel.add(functionsLbl, "cell 0 0");

    this.possibleFunctionsPanel.add(this.possParticipantCb, "cell 1 0");

    this.possibleFunctionsPanel.add(this.possStaffCb, "cell 2 0");

    this.possibleFunctionsPanel.add(this.possStaffYouthCb, "cell 3 0");

    this.possibleFunctionsPanel.add(this.possBoardCb, "cell 4 0");

    this.possibleFunctionsPanel.add(this.possExtendedBoardCb, "cell 5 0");

    this.possibleFunctionsPanel.add(this.possMakCb, "cell 1 1");

    this.possibleFunctionsPanel.add(this.possAgeCb, "cell 2 1");

    this.possibleFunctionsPanel.add(this.possSeminarCb, "cell 3 1");

    this.possibleFunctionsPanel.add(this.possKitchenCb, "cell 4 1");

    this.possibleFunctionsPanel.add(this.possMiscCb, "cell 5 1");
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
  public final void clear() {
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
      this.birthDayTf.setText(this.dateFormat.format(participant.getBirthDate()));
      this.posStreetTf.setText(participant.getStreetPostal());
      this.posPostCodeTf.setText(String.valueOf(participant.getPostCodePostal()));
      this.posCityTf.setText(participant.getCityPostal());
      this.countyCouncilCb.setSelectedItem(participant.getCountyCouncil());
      this.bankTf.setText(participant.getBank());
      this.bankAccNumberTf.setText(String.valueOf(participant.getBankAccountNumber()));
      this.bankCodeNumberTf.setText(String.valueOf(participant.getBankCodeNumber()));
      this.commentTa.setText(participant.getComment());
      if (participant.getDateUpToInSystem() != null) {
        this.untilInDbTf.setText(this.dateFormat.format(participant.getDateUpToInSystem()));
      } else {
        this.untilInDbTf.setText(null);
      }
      if (participant.getDateSinceInDataBase() != null) {
        this.sinceInDbValueLbl.setText(this.dateFormat.format(participant.getDateSinceInDataBase()));
      } else {
        this.sinceInDbValueLbl.setText(null);
      }
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
      return this.dateFormat.parse(this.birthDayTf.getText());
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
      return this.dateFormat.parse(this.untilInDbTf.getText());
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
