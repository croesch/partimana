package com.github.croesch.view;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import com.github.croesch.actions.ActionObserver;
import com.github.croesch.actions.UserAction;
import com.github.croesch.i18n.Text;
import com.github.croesch.model.api.IModel4View;
import com.github.croesch.view.api.ICampView;
import com.github.croesch.view.api.IParticipantEditView;
import com.github.croesch.view.api.IParticipantView;
import com.github.croesch.view.api.IStatusView;
import com.github.croesch.view.api.IVersionView;
import com.github.croesch.view.api.IView;

/**
 * The view of the program that is responsible to build the GUI.
 * 
 * @author croesch
 * @since Date: May 29, 2011 12:27:41 PM
 */
public final class View extends JFrame implements IView, IVersionView, IStatusView, IParticipantView, ICampView,
        ActionObserver {

  /** generated */
  private static final long serialVersionUID = -5242901770081600903L;

  /** logging class */
  private static final Logger LOGGER = Logger.getLogger(View.class);

  /** the connector to the model of the program to fetch updates */
  private final IModel4View model;

  /** the observer that will handle user actions */
  private final ActionObserver observer;

  /** the part of the view that is responsible for viewing the participants */
  private final IParticipantView participantView;

  /** the part of the view that is responsible for viewing the status */
  private final IStatusView statusView;

  /**
   * Constructs the view of the program with the given model.
   * 
   * @author croesch
   * @since Date: Jun 30, 2011
   * @param m the model to fetch data updates from.
   * @param o the observer to notify about actions.
   */
  public View(final IModel4View m, final ActionObserver o) {
    this.model = m;
    this.observer = o;
    Action.setObserver(this);

    setJMenuBar(new MenuBar());

    // TODO change this
    setSize(new Dimension(1200, 650));
    setTitle(Text.PARTIMANA.text());
    setLayout(new MigLayout("fill, wrap 1", "[grow, fill]", "[grow, fill][]"));

    final ParticipantView pv = new ParticipantView();
    this.participantView = pv;
    add(pv, "top");

    final JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new MigLayout("fill, ins 0 n 0 n", "[grow][]", "[]"));
    buttonPanel.add(new JButton(Action.getSaveParticipantAction()), "cell 1 0");
    add(buttonPanel);

    final StatusView sv = new StatusView();
    this.statusView = sv;
    add(sv, "dock south");

    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(final WindowEvent e) {
        View.this.observer.performAction(UserAction.EXIT);
      }
    });
  }

  @Override
  public IParticipantEditView getParticipantEditView() {
    return this.participantView.getParticipantEditView();
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
        this.observer.performAction(action);
        break;

      default:
        LOGGER.warn(Text.WARN_UNKNOWN_ACTION.text(action));
        break;

    }
  }

}
