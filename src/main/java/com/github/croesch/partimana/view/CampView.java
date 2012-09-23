package com.github.croesch.partimana.view;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.ICampModel4View;
import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;
import com.github.croesch.partimana.view.api.ICampEditView;
import com.github.croesch.partimana.view.api.ICampListView;
import com.github.croesch.partimana.view.api.ICampView;

/**
 * The implementation of {@link ICampView} that is able to view camps.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011
 */
class CampView extends JPanel implements ICampView, ActionObserver {

  /** generated */
  private static final long serialVersionUID = -9181462667623513795L;

  /** logging class */
  private static final Logger LOGGER = Logger.getLogger(CampView.class);

  /** the view to edit the camps */
  @NotNull
  private final ICampEditView editView;

  /** the view to show list of camps */
  @NotNull
  private final ICampListView listView;

  /** the model to fetch camps from */
  @NotNull
  private final transient ICampModel4View model;

  /**
   * Constructs the view for camp.
   * 
   * @since Date: Sep 23, 2012
   * @param m the model to fetch camp information from
   * @throws RequiredFieldSetToNullException if the given model is <code>null</code>
   */
  public CampView(final ICampModel4View m) throws RequiredFieldSetToNullException {
    if (m == null) {
      throw new RequiredFieldSetToNullException();
    }
    this.model = m;

    setLayout(new MigLayout("fill", "[][grow, fill]", "[grow, fill]"));

    // sic! because of the add methods below ..
    final CampEditView pev = new CampEditView();
    final CampListView plv = new CampListView(this);
    this.editView = pev;
    this.listView = plv;

    final JPanel buttonPanel = new JPanel(new MigLayout("fill"));
    final JButton createButton = new JButton(Action.getCreateCampAction());
    final JButton deleteButton = new JButton(Action.getDeleteCampAction());
    createButton.setName("newCamp");
    deleteButton.setName("deleteCamp");
    buttonPanel.add(createButton, "sg one");
    buttonPanel.add(deleteButton, "sg one");

    final JPanel leftPanel = new JPanel(new MigLayout("fill", "[grow, fill]", "[][grow, fill]"));
    leftPanel.add(buttonPanel, "wrap");
    leftPanel.add(plv);

    add(leftPanel);
    add(pev);
  }

  @Override
  @NotNull
  public ICampEditView getCampEditView() {
    return this.editView;
  }

  @Override
  @NotNull
  public ICampListView getCampListView() {
    return this.listView;
  }

  @Override
  public void performAction(final UserAction action) {
    if (action == UserAction.CAMP_SELECTED) {
      this.editView.setCamp(this.model.getCamp(this.listView.getSelectedCampId()));
    } else {
      LOGGER.warn(Text.WARN_UNKNOWN_ACTION.text(action));
    }
  }

  @Override
  public void createCamp() {
    this.editView.clear();
  }
}
