package com.github.croesch.partimana.view;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;

/**
 * A panel to edit a {@link Camp}. Provides text fields to edit the different attributes.
 *
 * @author croesch
 * @since Date: Sep 13, 2012
 */
final class CampEditView extends CPanel implements ICampEditView, ActionObserver {

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

  /** the label to show the date when a camp has been cancelled */
  @NotNull
  private final CLabel cancelledLbl = new CLabel("cancelledLbl", "12345");

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
   * @param name the name of this component
   * @see #setCamp(Camp)
   * @since Date: Sep 13, 2012
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
   * @since Date: Jun 28, 2011
   */
  private void addComponents() {
    final CLabel idLbl = new CLabel("id", Text.CAMP_ID.text());
    add(idLbl, "cell 0 0");

    add(idValueLbl, "cell 1 0");

    add(cancelledLbl, "cell 2 0, span 2");

    final CLabel nameLbl = new CLabel("name", Text.CAMP_NAME.text());
    add(nameLbl, "cell 0 1");

    add(nameTf, "cell 1 1");

    final CLabel locationLbl = new CLabel("location", Text.CAMP_LOCATION.text());
    add(locationLbl, "cell 2 1");

    add(locationTf, "cell 3 1");

    final CLabel fromLbl = new CLabel("from", Text.CAMP_DATE_FROM.text());
    add(fromLbl, "cell 0 2");

    add(fromTf, "cell 1 2");

    final CLabel toLbl = new CLabel("until", Text.CAMP_DATE_TO.text());
    add(toLbl, "cell 2 2");

    add(untilTf, "cell 3 2");

    final CLabel perParticipantLbl = new CLabel("perParticipant", Text.CAMP_RATE_PER_PARTICIPANT.text());
    add(perParticipantLbl, "cell 0 3");

    add(ratePerParticipantTf, "cell 1 3");

    final CLabel perDayLbl = new CLabel("perDay", Text.CAMP_RATE_PER_DAY.text());
    add(perDayLbl, "cell 2 3");

    add(ratePerDayTf, "cell 3 3");

    add(participantList, "cell 0 4, span 2, grow");
    add(campParticipantList, "cell 2 4, span 2, grow");
  }

  /**
   * Initializes the names of all components.
   *
   * @since Date: Jun 28, 2011
   */
  private void initNames() {

    fromTf.setName("fromTF");
    untilTf.setName("untilTF");
  }

  @Override
  public void clear() {
    idValueLbl.setText(null);
    cancelledLbl.setText(null);

    nameTf.setText(null);
    locationTf.setText(null);
    fromTf.setText(null);
    untilTf.setText(null);
    ratePerDayTf.setText(null);
    ratePerParticipantTf.setText(null);
    setCampParticipants(null);
    update(allParticipants);
  }

  @Override
  public void setCamp(final Camp camp) {
    if (camp == null) {
      clear();
    } else {

      idValueLbl.setText(String.valueOf(camp.getId()));

      if (camp.getCancelDate() != null) {
        cancelledLbl.setText(Text.CAMP_CANCELLED_ON.text(new SimpleDateFormat().format(camp.getCancelDate())));
      }

      nameTf.setText(camp.getName());
      locationTf.setText(camp.getLocation());
      fromTf.setDateAndDisplay(camp.getFromDate());
      untilTf.setDateAndDisplay(camp.getUntilDate());
      ratePerDayTf.setText(camp.getRatePerDayChildren());
      ratePerParticipantTf.setText(camp.getRatePerParticipant());
      setCampParticipants(camp.getParticipants());
      update(allParticipants);
    }
  }

  /**
   * Sets the given list of {@link CampParticipant}s.
   *
   * @param cp the list of {@link CampParticipant}s to visualize.
   * @since Date: Sep 23, 2012
   */
  private void setCampParticipants(final List<CampParticipant> cp) {
    campParticipants.clear();
    if (cp != null) {
      for (final CampParticipant part : cp) {
        campParticipants.add(part);
      }
    }
    campParticipantList.update(campParticipants);
  }

  @Override
  public String getNameOfCamp() {
    return nameTf.getText();
  }

  @Override
  public String getLocationOfCamp() {
    return locationTf.getText();
  }

  @Override
  @MayBeNull
  public Date getFrom() {
    return fromTf.getDateWithoutTimeOrNull();
  }

  @Override
  @MayBeNull
  public Date getUntil() {
    return untilTf.getDateWithoutTimeOrNull();
  }

  @Override
  public String getRatePerDay() {
    return ratePerDayTf.getText();
  }

  @Override
  public String getRatePerParticipant() {
    return ratePerParticipantTf.getText();
  }

  @Override
  public long getId() {
    final String text = idValueLbl.getText();
    if (text == null || text.trim().equals("")) {
      return 0; //FIXME should that -1? See also ParticipantEditView!
    }
    return Long.parseLong(text);
  }

  @Override
  public void performAction(final UserAction action) {
    if (action == UserAction.PARTICIPANT_SELECTED) {
      final long id = participantList.getSelectedElementId();
      for (final Participant p : participants) {
        if (p.getId() == id) {
          participants.remove(p);
          campParticipants.add(new CampParticipant(p));
          participantList.update(participants);
          campParticipantList.update(campParticipants);
          break;
        }
      }
    } else if (action == UserAction.CAMP_PARTICIPANT_SELECTED) {
      if (JOptionPane.showConfirmDialog(this,
                                        Text.CONTINUE_REMOVES_PARTICIPANT_FROM_CAMP,
                                        Text.USER_WARNING.text(),
                                        JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) {
        return;
      }
      final long id = campParticipantList.getSelectedElementId();
      for (final CampParticipant cp : campParticipants) {
        if (cp.getId() == id) {
          for (Participant p : allParticipants) {
            if (p.getId() == id) {
              participants.add(p);
              break;
            }
          }
          campParticipants.remove(cp);
          participantList.update(participants);
          campParticipantList.update(campParticipants);
          break;
        }
      }
    }
  }

  @Override
  public void update(final List<Participant> p) {
    allParticipants = p;
    participants.clear();

    for (final Participant part : p) {
      if (!campParticipants.contains(new CampParticipant(part))) {
        participants.add(part);
      }
    }

    participantList.update(participants);
  }

  @Override
  public List<CampParticipant> getCampParticipants() {
    return campParticipants;
  }
}
