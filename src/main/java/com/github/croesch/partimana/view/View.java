package com.github.croesch.partimana.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.components.CButton;
import com.github.croesch.components.CPanel;
import com.github.croesch.components.CTabbedPane;
import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IModel4View;
import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;
import com.github.croesch.partimana.view.api.ICampEditView;
import com.github.croesch.partimana.view.api.ICampListView;
import com.github.croesch.partimana.view.api.ICampView;
import com.github.croesch.partimana.view.api.IParticipantEditView;
import com.github.croesch.partimana.view.api.IParticipantListView;
import com.github.croesch.partimana.view.api.IParticipantView;
import com.github.croesch.partimana.view.api.IStatusView;
import com.github.croesch.partimana.view.api.IVersionView;
import com.github.croesch.partimana.view.api.IView;

/**
 * The view of the program that is responsible to build the GUI.
 * 
 * @author croesch
 * @since Date: May 29, 2011
 */
public final class View extends JFrame implements IView, IVersionView, IStatusView, IParticipantView, ICampView,
        ActionObserver {

  /** generated */
  private static final long serialVersionUID = -5242901770081600903L;

  /** logging class */
  private static final Logger LOGGER = Logger.getLogger(View.class);

  /** the connector to the model of the program to fetch updates */
  @NotNull
  private final transient IModel4View model;

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

  /**
   * Constructs the view of the program with the given model.
   * 
   * @author croesch
   * @since Date: Jun 30, 2011
   * @param m the model to fetch data updates from.
   * @param o the observer to notify about actions.
   * @throws RequiredFieldSetToNullException if the given model or the given observer is <code>null</code>
   */
  public View(final IModel4View m, final ActionObserver o) throws RequiredFieldSetToNullException {
    if (m == null || o == null) {
      throw new RequiredFieldSetToNullException();
    }
    this.model = m;
    this.observer = o;
    Action.setObserver(this);

    setJMenuBar(new MenuBar());

    // TODO change the default size of the view.
    setSize(new Dimension(1200, 650));
    setTitle(Text.PARTIMANA.text());
    setLayout(new BorderLayout());

    final CTabbedPane pane = new CTabbedPane("partCampSwitcher");
    final CPanel partPanel = new CPanel("partPanel", new MigLayout("fill, wrap 1", "[grow, fill]", "[grow, fill][]"));

    final ParticipantView pv = new ParticipantView("participantView", this.model);
    this.participantView = pv;
    partPanel.add(pv, "top");

    final CPanel partButtonPanel = new CPanel("buttons");
    partButtonPanel.setLayout(new MigLayout("fill, ins 0 n 0 n", "[grow][]", "[]"));
    final CButton partSaveButton = new CButton("save", Action.getSaveParticipantAction());
    partSaveButton.setName("saveParticipant");
    partButtonPanel.add(partSaveButton, "cell 1 0");
    partPanel.add(partButtonPanel);

    pane.addTab(Text.PARTICIPANT.text(), partPanel);

    final CampView cv = new CampView("campView", this.model);
    this.campView = cv;

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
    this.statusView = sv;
    add(sv, BorderLayout.SOUTH);

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(final WindowEvent e) {
        View.this.observer.performAction(UserAction.EXIT);
      }
    });
  }

  @Override
  @NotNull
  public IParticipantEditView getParticipantEditView() {
    return this.participantView.getParticipantEditView();
  }

  @Override
  @NotNull
  public IParticipantListView getParticipantListView() {
    return this.participantView.getParticipantListView();
  }

  @Override
  public void showInformation(final Text info) {
    this.statusView.showInformation(info);
  }

  @Override
  public void showInformation(final Text info, final Object ... args) {
    this.statusView.showInformation(info, args);
  }

  @Override
  public void showError(final Text error) {
    this.statusView.showError(error);
  }

  @Override
  public void performAction(final UserAction action) {
    switch (action) {
      case EXIT:
        dispose();
        break;

      case SAVE_PARTICIPANT:
      case DELETE_PARTICIPANT:
      case SAVE_CAMP:
      case DELETE_CAMP:
        this.observer.performAction(action);
        break;

      case CREATE_PARTICIPANT:
        createParticipant();
        break;

      case CREATE_CAMP:
        createCamp();
        break;

      default:
        LOGGER.warn(Text.WARN_UNKNOWN_ACTION.text(action));
        break;

    }
  }

  @Override
  public void update() {
    getParticipantListView().update(this.model.getListOfParticipants());
    getCampEditView().update(this.model.getListOfParticipants());
    getCampListView().update(this.model.getListOfCamps());
  }

  @Override
  public void createParticipant() {
    this.participantView.createParticipant();
  }

  @Override
  public ICampEditView getCampEditView() {
    return this.campView.getCampEditView();
  }

  @Override
  public ICampListView getCampListView() {
    return this.campView.getCampListView();
  }

  @Override
  public void createCamp() {
    this.campView.createCamp();
  }
}
