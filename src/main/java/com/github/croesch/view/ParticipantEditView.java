package com.github.croesch.view;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import com.github.croesch.view.api.IParticipantEditView;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Jun 8, 2011 6:23:15 AM
 */
class ParticipantEditView extends JPanel implements IParticipantEditView {
  private final JTextField textField;

  private final JTextField textField_1;

  private final JTextField textField_2;

  private final JTextField textField_3;

  private final JTextField textField_4;

  private final JTextField textField_5;

  private final JTextField textField_6;

  private final JTextField textField_7;

  private final JTextField textField_8;

  private final JTextField textField_9;

  private final JTextField textField_10;

  private final JTextField textField_11;

  private final JTextField textField_12;

  private final JTextField textField_13;

  private final JTextField textField_14;

  private final JTextField textField_15;

  private final JTextField textField_16;

  private final JTextField textField_17;

  public ParticipantEditView() {

    setLayout(new MigLayout("", "[][grow][][grow][][grow]", "[][][][][][][][][][][][][][][][]"));

    final JLabel lblId = new JLabel("ID:");
    add(lblId, "cell 0 0,alignx trailing");

    final JLabel label = new JLabel("12345");
    add(label, "cell 1 0");

    final JLabel lblSeit = new JLabel("Seit");
    add(lblSeit, "cell 2 0,alignx trailing");

    final JLabel label_1 = new JLabel("12.12.2003");
    add(label_1, "cell 3 0");

    final JLabel lblBis = new JLabel("Bis");
    add(lblBis, "cell 4 0,alignx trailing");

    this.textField_17 = new JTextField();
    add(this.textField_17, "cell 5 0,growx");
    this.textField_17.setColumns(10);

    final JLabel lblVorname = new JLabel("Vorname");
    add(lblVorname, "cell 0 1,alignx trailing");

    this.textField = new JTextField();
    add(this.textField, "cell 1 1,growx");
    this.textField.setColumns(10);

    final JLabel lblNachname = new JLabel("Nachname");
    add(lblNachname, "cell 2 1,alignx trailing");

    this.textField_1 = new JTextField();
    add(this.textField_1, "cell 3 1,growx");
    this.textField_1.setColumns(10);

    final JLabel lblGeschlecht = new JLabel("Geschlecht");
    add(lblGeschlecht, "cell 4 1,alignx trailing");

    final JComboBox comboBox = new JComboBox();
    add(comboBox, "cell 5 1,growx");

    final JLabel lblGeburtstag = new JLabel("Geburtstag");
    add(lblGeburtstag, "cell 0 2,alignx trailing");

    this.textField_2 = new JTextField();
    add(this.textField_2, "cell 1 2,growx");
    this.textField_2.setColumns(10);

    final JLabel lblKonfession = new JLabel("Konfession");
    add(lblKonfession, "cell 2 2,alignx trailing");

    final JComboBox comboBox_1 = new JComboBox();
    add(comboBox_1, "cell 3 2,growx");

    final JLabel lblKreisverwaltung = new JLabel("Kreisverwaltung");
    add(lblKreisverwaltung, "cell 4 2,alignx trailing");

    final JComboBox comboBox_2 = new JComboBox();
    add(comboBox_2, "cell 5 2,growx");

    final JLabel lblStrae = new JLabel("Stra\u00DFe");
    add(lblStrae, "cell 1 3,alignx center");

    final JLabel lblPlz = new JLabel("PLZ");
    add(lblPlz, "cell 3 3,alignx center");

    final JLabel lblOrt = new JLabel("Ort");
    add(lblOrt, "cell 5 3,alignx center");

    final JLabel lblWohnanschrift = new JLabel("Wohnanschrift");
    add(lblWohnanschrift, "cell 0 4");

    this.textField_3 = new JTextField();
    add(this.textField_3, "cell 1 4,growx");
    this.textField_3.setColumns(10);

    this.textField_4 = new JTextField();
    add(this.textField_4, "cell 3 4,growx");
    this.textField_4.setColumns(10);

    this.textField_5 = new JTextField();
    add(this.textField_5, "cell 5 4,growx");
    this.textField_5.setColumns(10);

    final JLabel lblPostanschriftwennAbweichend = new JLabel("Postanschrift");
    add(lblPostanschriftwennAbweichend, "cell 0 5");

    this.textField_6 = new JTextField();
    add(this.textField_6, "cell 1 5,growx");
    this.textField_6.setColumns(10);

    this.textField_7 = new JTextField();
    add(this.textField_7, "cell 3 5,growx");
    this.textField_7.setColumns(10);

    this.textField_8 = new JTextField();
    add(this.textField_8, "cell 5 5,growx");
    this.textField_8.setColumns(10);

    final JLabel lblTelefon = new JLabel("Telefon");
    add(lblTelefon, "cell 0 7,alignx trailing");

    this.textField_9 = new JTextField();
    add(this.textField_9, "cell 1 7,growx");
    this.textField_9.setColumns(10);

    final JLabel lblFax = new JLabel("Fax");
    add(lblFax, "cell 2 7,alignx trailing");

    this.textField_10 = new JTextField();
    add(this.textField_10, "cell 3 7,growx");
    this.textField_10.setColumns(10);

    final JLabel lblHandy = new JLabel("Handy");
    add(lblHandy, "cell 4 7,alignx trailing");

    this.textField_11 = new JTextField();
    add(this.textField_11, "cell 5 7,growx");
    this.textField_11.setColumns(10);

    final JLabel lblTelefonEltern = new JLabel("Telefon Eltern");
    add(lblTelefonEltern, "cell 0 8,alignx trailing");

    this.textField_12 = new JTextField();
    add(this.textField_12, "cell 1 8,growx");
    this.textField_12.setColumns(10);

    final JLabel lblMail = new JLabel("Mail");
    add(lblMail, "cell 2 8,alignx trailing");

    this.textField_13 = new JTextField();
    add(this.textField_13, "cell 3 8 3 1,growx");
    this.textField_13.setColumns(10);

    final JLabel lblKontonummer = new JLabel("Kontonummer");
    add(lblKontonummer, "cell 0 10,alignx trailing");

    this.textField_14 = new JTextField();
    add(this.textField_14, "cell 1 10,growx");
    this.textField_14.setColumns(10);

    final JLabel lblBlz = new JLabel("BLZ");
    add(lblBlz, "cell 2 10,alignx trailing");

    this.textField_15 = new JTextField();
    add(this.textField_15, "cell 3 10,growx");
    this.textField_15.setColumns(10);

    final JLabel lblBank = new JLabel("Bank");
    add(lblBank, "cell 4 10,alignx trailing");

    this.textField_16 = new JTextField();
    add(this.textField_16, "cell 5 10,growx");
    this.textField_16.setColumns(10);

    final JScrollPane scrollPane = new JScrollPane();
    add(scrollPane, "cell 1 12 5 3,grow");

    final JTextArea textArea = new JTextArea();
    scrollPane.setViewportView(textArea);

    final JLabel lblKommentar = new JLabel("Kommentar");
    add(lblKommentar, "cell 0 12,alignx trailing");

    final JPanel panel = new JPanel();
    add(panel, "cell 0 15 6 1,growx");
    panel.setLayout(new MigLayout("", "[100px][][][][]", "[][][]"));

    final JLabel lblFunktionen = new JLabel("Funktionen");
    panel.add(lblFunktionen, "cell 0 0,alignx trailing");

    final JCheckBox chckbxTeilnehmer = new JCheckBox("Teilnehmer");
    panel.add(chckbxTeilnehmer, "cell 1 0");

    final JCheckBox chckbxMitarbeiter = new JCheckBox("Mitarbeiter");
    panel.add(chckbxMitarbeiter, "cell 2 0");

    final JCheckBox chckbxMitarbeiterjugend = new JCheckBox("Mitarbeiter-Jugend");
    panel.add(chckbxMitarbeiterjugend, "cell 3 0");

    final JCheckBox chckbxVorstand = new JCheckBox("Vorstand");
    panel.add(chckbxVorstand, "cell 4 0");

    final JCheckBox chckbxErweiterterVorstand = new JCheckBox("erweiterter Vorstand");
    panel.add(chckbxErweiterterVorstand, "cell 1 1");

    final JCheckBox chckbxMak = new JCheckBox("MAK");
    panel.add(chckbxMak, "cell 2 1");

    final JCheckBox chckbxAge = new JCheckBox("AGE");
    panel.add(chckbxAge, "cell 3 1");

    final JCheckBox chckbxSeminar = new JCheckBox("Seminar");
    panel.add(chckbxSeminar, "cell 4 1");

    final JCheckBox chckbxKche = new JCheckBox("K\u00FCche");
    panel.add(chckbxKche, "cell 1 2");

    final JCheckBox chckbxSonstiges = new JCheckBox("Sonstiges");
    panel.add(chckbxSonstiges, "cell 2 2");
  }

}
