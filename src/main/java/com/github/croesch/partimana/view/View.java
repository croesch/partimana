package com.github.croesch.partimana.view;

import com.github.croesch.annotate.MayBeNull;
import com.github.croesch.annotate.NotNull;
import com.github.croesch.components.CButton;
import com.github.croesch.components.CFrame;
import com.github.croesch.components.CPanel;
import com.github.croesch.components.CTabbedPane;
import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IModel4View;
import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.types.Participant;
import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;
import com.github.croesch.partimana.view.api.*;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.miginfocom.swing.MigLayout;
import org.apache.log4j.Logger;

/**
 * The view of the program that is responsible to build the GUI.
 *
 * @author croesch
 * @since Date: May 29, 2011
 */
public final class View extends CFrame
    implements IView, IVersionView, IStatusView, IParticipantView, ICampView, ActionObserver {

  /** generated */
  private static final long serialVersionUID = -5242901770081600903L;

  /** logging class */
  private static final Logger LOGGER = Logger.getLogger(View.class);

  /** the connector to the model of the program to fetch updates */
  @NotNull
  private final IModel4View model;

  /** the observer that will handle user actions */
  @NotNull
  private final ActionObserver observer;

  /** the part of the view that is responsible for viewing the participants */
  @NotNull
  private final IParticipantView participantView;

  /** the part of the view that is responsible for viewing the camps */
  @NotNull
  private final ICampView campView;

  /** the part of the view that is responsible for viewing the status */
  @NotNull
  private final IStatusView statusView;

  /** the view to search for a camp */
  @MayBeNull
  private CampSearchView campSearchView = null;

  /** the view to search for a participant */
  @MayBeNull
  private ParticipantSearchView participantSearchView = null;

  /** the view to search for a participant of a camp. */
  @MayBeNull
  private CampParticipantSearchView campParticipantSearchView = null;

  /** the  camp that is currently being stored to a csv file or <code>null</code> if we're not in save state. */
  @MayBeNull
  private Camp campToSaveToCSV = null;

  /**
   * Constructs the view of the program with the given model.
   *
   * @param name the name of the view
   * @param m    the model to fetch data updates from.
   * @param o    the observer to notify about actions.
   * @throws RequiredFieldSetToNullException if the given model or the given observer is <code>null</code>
   * @since Date: Jun 30, 2011
   */
  public View(final String name, final IModel4View m, final ActionObserver o) throws RequiredFieldSetToNullException {
    super(name);

    if (m == null || o == null) {
      throw new RequiredFieldSetToNullException();
    }
    model = m;
    observer = o;
    Action.setObserver(this);

    setJMenuBar(new MenuBar("menuBar"));

    setTitle(Text.PARTIMANA.text());
    setLayout(new BorderLayout());

    final CTabbedPane pane = new CTabbedPane("partCampSwitcher");
    pane.addChangeListener(new ChangeListener() {

      @Override
      public void stateChanged(final ChangeEvent e) {
        if (e.getSource() instanceof JTabbedPane) {
          Action.getSearchParticipantAction().setEnabled(((JTabbedPane) e.getSource()).getSelectedIndex() == 0);
          final boolean campTab = ((JTabbedPane) e.getSource()).getSelectedIndex() == 1;
          Action.getSearchCampAction().setEnabled(campTab);
          Action.getSaveCampToCSVAction().setEnabled(campTab);
        }
      }
    });

    final CPanel partPanel = new CPanel("partPanel", new MigLayout("fill, wrap 1", "[grow, fill]", "[grow, fill][]"));

    final ParticipantView pv = new ParticipantView("participantView", model);
    participantView = pv;
    partPanel.add(pv, "top");

    final CPanel partButtonPanel = new CPanel("buttons");
    partButtonPanel.setLayout(new MigLayout("fill, ins 0 n 0 n", "[grow][]", "[]"));
    final CButton partSaveButton = new CButton("save", Action.getSaveParticipantAction());
    partSaveButton.setName("saveParticipant");
    partButtonPanel.add(partSaveButton, "cell 1 0");
    partPanel.add(partButtonPanel);

    pane.addTab(Text.PARTICIPANT.text(), partPanel);

    final CampView cv = new CampView("campView", model);
    campView = cv;

    final CPanel campPanel = new CPanel("campPanel", new MigLayout("fill, wrap 1", "[grow, fill]", "[grow, fill][]"));
    campPanel.add(cv, "top");

    final CPanel campButtonPanel = new CPanel("buttons");
    campButtonPanel.setLayout(new MigLayout("fill, ins 0 n 0 n", "[grow][]", "[]"));
    final CButton campSaveButton = new CButton("save", Action.getSaveCampAction());
    campSaveButton.setName("saveCamp");
    campButtonPanel.add(campSaveButton, "cell 1 0");
    campPanel.add(campButtonPanel);

    pane.addTab(Text.CAMP.text(), campPanel);

    add(pane);

    final StatusView sv = new StatusView("status");
    statusView = sv;
    add(sv, BorderLayout.SOUTH);

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(final WindowEvent e) {
        observer.performAction(UserAction.EXIT);
      }
    });

    pack();
    setLocationByPlatform(true);

    //TODO test
    update();
  }

  @Override
  @NotNull
  public IParticipantEditView getParticipantEditView() {
    return participantView.getParticipantEditView();
  }

  @Override
  @NotNull
  public IListView<Participant> getParticipantListView() {
    return participantView.getParticipantListView();
  }

  @Override
  public void showInformation(final Text info) {
    statusView.showInformation(info);
  }

  @Override
  public void showInformation(final Text info, final Object... args) {
    statusView.showInformation(info, args);
  }

  @Override
  public void showError(final Text error) {
    statusView.showError(error);
  }

  @Override
  public void performAction(final UserAction action) {
    LOGGER.debug("executing: " + action);
    switch (action) {
      case EXIT:
        dispose();
        break;

      case SAVE_PARTICIPANT:
      case DELETE_PARTICIPANT:
      case SAVE_CAMP:
      case DELETE_CAMP:
      case CANCEL_CAMP:
        observer.performAction(action);
        break;

      case CREATE_PARTICIPANT:
        createParticipant();
        break;

      case CREATE_CAMP:
        createCamp();
        break;

      case SEARCH_CAMP:
        openCampSearchView();
        break;

      case SAVE_CAMP_TO_CSV:
        selectCampsForCSVExport();
        break;

      // assert the event happened from the search view
      case CAMP_PARTICIPANT_SELECTED:
        campParticipantSearchView.dispose();
        saveCampToCSV();
        campParticipantSearchView = null;
        break;

      // assert the event happened from the search view
      case CAMP_SELECTED:
        campView.getCampEditView().setCamp(model.getCamp(campSearchView.getSelectedId()));
        closeCampSearchView();
        break;

      case SEARCH_PARTICIPANT:
        openParticipantSearchView();
        break;

      // assert the event happened from the search view
      case PARTICIPANT_SELECTED:
        participantView.getParticipantEditView()
                       .setParticipant(model.getParticipant(participantSearchView.getSelectedId()));
        closeParticipantSearchView();
        break;

      default:
        LOGGER.warn(Text.WARN_UNKNOWN_ACTION.text(action));
        break;
    }
  }

  private void selectCampsForCSVExport() {
    campToSaveToCSV = model.getCamp(campView.getCampListView().getSelectedElementId());
    if (campToSaveToCSV == null) {
      showInformation(Text.INFO_NO_CAMP_SELECTED);
      return;
    }
    campParticipantSearchView = new CampParticipantSearchView("asd", campToSaveToCSV.getParticipants(), this);
    campParticipantSearchView.setVisible(true);
    campParticipantSearchView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  /**
   * Writes the data of the currently selected camp to a csv file. The user can choose the file and then the content is
   * written to the file.
   *
   * @since Date: Jun 1, 2013
   */
  private void saveCampToCSV() {
    final JFileChooser fileChooser = new JFileChooser();
    if (fileChooser.showSaveDialog(View.this) == JFileChooser.APPROVE_OPTION) {
      final File selectedFile = fileChooser.getSelectedFile();
      if (!selectedFile.exists() || JOptionPane.showConfirmDialog(View.this,
                                                                  Text.CONTINUE_OVERRIDES_FILE
                                                                      .text(selectedFile.getAbsolutePath()),
                                                                  Text.USER_WARNING.text(),
                                                                  JOptionPane.OK_CANCEL_OPTION)
                                    == JOptionPane.OK_OPTION) {
        FileWriter fileWriter = null;
        try {
          fileWriter = new FileWriter(selectedFile);
          fileWriter.write(campToSaveToCSV.toCSV(campParticipantSearchView.getAllIds()));
          fileWriter.flush();
          showInformation(Text.INFO_CAMP_SAVED_TO_CSV, campToSaveToCSV.getId(), selectedFile.getAbsolutePath());
        } catch (final IOException e) {
          e.printStackTrace();
          showError(Text.ERROR_CAMP_NOT_SAVED);
        } finally {
          try {
            if (fileWriter != null) {
              fileWriter.close();
            }
          } catch (final IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  /**
   * Closes the opened participant search view.
   *
   * @since Date: Mar 3, 2013
   */
  private void closeParticipantSearchView() {
    participantSearchView.dispose();
    participantSearchView = null;
  }

  /**
   * Opens the search view for participants.
   *
   * @since Date: Mar 3, 2013
   */
  private void openParticipantSearchView() {
    participantSearchView = new ParticipantSearchView("participant-search", model.getListOfParticipants(), this);
    participantSearchView.setVisible(true);
    participantSearchView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  /**
   * Closes the opened camp search view.
   *
   * @since Date: Mar 3, 2013
   */
  private void closeCampSearchView() {
    campSearchView.dispose();
    campSearchView = null;
  }

  /**
   * Opens the search view for camps.
   *
   * @since Date: Mar 3, 2013
   */
  private void openCampSearchView() {
    campSearchView = new CampSearchView("camp-search", model.getListOfCamps(), this);
    campSearchView.setVisible(true);
    campSearchView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  @Override
  public void update() {
    getParticipantListView().update(model.getListOfParticipants());
    getCampEditView().update(model.getListOfParticipants());
    getCampListView().update(model.getListOfCamps());
  }

  @Override
  public void createParticipant() {
    participantView.createParticipant();
  }

  @Override
  public ICampEditView getCampEditView() {
    return campView.getCampEditView();
  }

  @Override
  public IListView<Camp> getCampListView() {
    return campView.getCampListView();
  }

  @Override
  public void createCamp() {
    campView.createCamp();
  }
}
