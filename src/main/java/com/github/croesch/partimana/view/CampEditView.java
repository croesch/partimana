package com.github.croesch.partimana.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import net.miginfocom.swing.MigLayout;

import com.github.croesch.annotate.MayBeNull;
import com.github.croesch.annotate.NotNull;
import com.github.croesch.components.CDateField;
import com.github.croesch.components.CLabel;
import com.github.croesch.components.CPanel;
import com.github.croesch.components.CTextField;
import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.types.CampParticipant;
import com.github.croesch.partimana.types.Participant;
import com.github.croesch.partimana.view.api.ICampEditView;

/**
 * A panel to edit a {@link Camp}. Provides text fields to edit the different attributes.
 * 
 * @author croesch
 * @since Date: Sep 13, 2012
 */
class CampEditView extends CPanel implements ICampEditView, ActionObserver {

  /** generated */
  private static final long serialVersionUID = 2952537980601243913L;

  /** the text field to edit the camps name */
  @NotNull
  private final CTextField nameTf = new CTextField("nameTF");

  /** the text field to edit the camps location */
  @NotNull
  private final CTextField locationTf = new CTextField("locationTF");

  /** the text field to edit the camps begin date */
  @NotNull
  private final CDateField fromTf = new CDateField(Locale.GERMAN);

  /** the text field to edit the camps end date */
  @NotNull
  private final CDateField untilTf = new CDateField(Locale.GERMAN);

  /** the text field to edit the camps per-day-rate */
  @NotNull
  private final CTextField ratePerDayTf = new CTextField("ratePerDayTF");

  /** the text field to edit the camps rate */
  @NotNull
  private final CTextField ratePerParticipantTf = new CTextField("ratePerParticipantTF");

  /** the label to show the camps id */
  @NotNull
  private final CLabel idValueLbl = new CLabel("idLbl", "12345");

  /** reference to the list of {@link CampParticipant}s viewed by the {@link CampParticipantListView} */
  @NotNull
  private final List<CampParticipant> campParticipants = new ArrayList<CampParticipant>();

  /** reference to the list of {@link Participant}s viewed by the {@link ParticipantListView} */
  @NotNull
  private final List<Participant> participants = new ArrayList<Participant>();

  /** reference to the list of all {@link Participant}s available */
  @NotNull
  private List<Participant> allParticipants = new ArrayList<Participant>();

  /** the list of all possible participants for this camp */
  @NotNull
  private final ParticipantListView participantList = new ParticipantListView("participants", this);

  /** the list of all participants for this camp */
  @NotNull
  private final CampParticipantListView campParticipantList = new CampParticipantListView("camps", this);

  /**
   * Initializes the panel to edit a {@link Camp}.
   * 
   * @since Date: Sep 13, 2012
   * @param name the name of this component
   * @see #setCamp(Camp)
   */
  public CampEditView(final String name) {
    super(name);

    final int space = 15;
    setLayout(new MigLayout("fill, insets 0",
                            "[sg t, align trailing][grow, fill, sg f][sg t, align trailing][grow, fill, sg f]",
                            "[sg]" + space + "[sg][sg]" + space + "[sg][grow]"));

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
    final CLabel idLbl = new CLabel("id", Text.CAMP_ID.text());
    add(idLbl, "cell 0 0");

    add(this.idValueLbl, "cell 1 0");

    final CLabel nameLbl = new CLabel("name", Text.CAMP_NAME.text());
    add(nameLbl, "cell 0 1");

    add(this.nameTf, "cell 1 1");

    final CLabel locationLbl = new CLabel("location", Text.CAMP_LOCATION.text());
    add(locationLbl, "cell 2 1");

    add(this.locationTf, "cell 3 1");

    final CLabel fromLbl = new CLabel("from", Text.CAMP_DATE_FROM.text());
    add(fromLbl, "cell 0 2");

    add(this.fromTf, "cell 1 2");

    final CLabel toLbl = new CLabel("until", Text.CAMP_DATE_TO.text());
    add(toLbl, "cell 2 2");

    add(this.untilTf, "cell 3 2");

    final CLabel perParticipantLbl = new CLabel("perParticipant", Text.CAMP_RATE_PER_PARTICIPANT.text());
    add(perParticipantLbl, "cell 0 3");

    add(this.ratePerParticipantTf, "cell 1 3");

    final CLabel perDayLbl = new CLabel("perDay", Text.CAMP_RATE_PER_DAY.text());
    add(perDayLbl, "cell 2 3");

    add(this.ratePerDayTf, "cell 3 3");

    add(this.participantList, "cell 0 4, span 2, grow");
    add(this.campParticipantList, "cell 2 4, span 2, grow");
  }

  /**
   * Initializes the names of all components.
   * 
   * @author croesch
   * @since Date: Jun 28, 2011
   */
  private void initNames() {

    this.fromTf.setName("fromTF");
    this.untilTf.setName("untilTF");
  }

  @Override
  public final void clear() {
    this.idValueLbl.setText(null);

    this.nameTf.setText(null);
    this.locationTf.setText(null);
    this.fromTf.setText(null);
    this.untilTf.setText(null);
    this.ratePerDayTf.setText(null);
    this.ratePerParticipantTf.setText(null);
    setCampParticipants(null);
    update(this.allParticipants);
  }

  @Override
  public void setCamp(final Camp camp) {
    if (camp == null) {
      clear();
    } else {

      this.idValueLbl.setText(String.valueOf(camp.getId()));

      this.nameTf.setText(camp.getName());
      this.locationTf.setText(camp.getLocation());
      this.fromTf.setDateAndDisplay(camp.getFromDate());
      this.untilTf.setDateAndDisplay(camp.getUntilDate());
      this.ratePerDayTf.setText(camp.getRatePerDayChildren());
      this.ratePerParticipantTf.setText(camp.getRatePerParticipant());
      setCampParticipants(camp.getParticipants());
      update(this.allParticipants);
    }
  }

  /**
   * Sets the given list of {@link CampParticipant}s.
   * 
   * @since Date: Sep 23, 2012
   * @param cp the list of {@link CampParticipant}s to visualize.
   */
  private void setCampParticipants(final List<CampParticipant> cp) {
    this.campParticipants.clear();
    if (cp != null) {
      for (final CampParticipant part : cp) {
        this.campParticipants.add(part);
      }
    }
    this.campParticipantList.update(this.campParticipants);
  }

  @Override
  public String getNameOfCamp() {
    return this.nameTf.getText();
  }

  @Override
  public String getLocationOfCamp() {
    return this.locationTf.getText();
  }

  @Override
  @MayBeNull
  public Date getFrom() {
    return this.fromTf.getDateWithoutTimeOrNull();
  }

  @Override
  @MayBeNull
  public Date getUntil() {
    return this.untilTf.getDateWithoutTimeOrNull();
  }

  @Override
  public String getRatePerDay() {
    return this.ratePerDayTf.getText();
  }

  @Override
  public String getRatePerParticipant() {
    return this.ratePerParticipantTf.getText();
  }

  @Override
  public long getId() {
    final String text = this.idValueLbl.getText();
    if (text == null || text.trim().equals("")) {
      return 0; //FIXME should that -1? See also ParticipantEditView!
    }
    return Long.parseLong(text);
  }

  @Override
  public void performAction(final UserAction action) {
    if (action == UserAction.PARTICIPANT_SELECTED) {
      final long id = this.participantList.getSelectedElementId();
      for (final Participant p : this.participants) {
        if (p.getId() == id) {
          this.participants.remove(p);
          this.campParticipants.add(new CampParticipant(p));
          this.participantList.update(this.participants);
          this.campParticipantList.update(this.campParticipants);
          break;
        }
      }
    }
  }

  @Override
  public void update(final List<Participant> p) {
    this.allParticipants = p;
    this.participants.clear();

    for (final Participant part : p) {
      if (!this.campParticipants.contains(new CampParticipant(part))) {
        this.participants.add(part);
      }
    }

    this.participantList.update(this.participants);
  }

  @Override
  public List<CampParticipant> getCampParticipants() {
    return this.campParticipants;
  }
}
