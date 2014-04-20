package com.github.croesch.partimana.view;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.components.CButton;
import com.github.croesch.components.CPanel;
import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.ICampModel4View;
import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;
import com.github.croesch.partimana.view.api.ICampEditView;
import com.github.croesch.partimana.view.api.ICampView;
import com.github.croesch.partimana.view.api.IListView;
import net.miginfocom.swing.MigLayout;
import org.apache.log4j.Logger;

/**
 * The implementation of {@link ICampView} that is able to view camps.
 *
 * @author croesch
 * @since Date: Jun 8, 2011
 */
class CampView extends CPanel implements ICampView, ActionObserver {

  /** generated */
  private static final long serialVersionUID = -9181462667623513795L;

  /** logging class */
  private static final Logger LOGGER = Logger.getLogger(CampView.class);

  /** the view to edit the camps */
  @NotNull
  private final ICampEditView editView;

  /** the view to show list of camps */
  @NotNull
  private final IListView<Camp> listView;

  /** the model to fetch camps from */
  @NotNull
  private final ICampModel4View model;

  /**
   * Constructs the view for camp.
   *
   * @param m    the model to fetch camp information from
   * @param name the name of this component
   * @throws RequiredFieldSetToNullException if the given model is <code>null</code>
   * @since Date: Sep 23, 2012
   */
  public CampView(final String name, final ICampModel4View m) throws RequiredFieldSetToNullException {
    super(name);
    if (m == null) {
      throw new RequiredFieldSetToNullException();
    }
    model = m;

    setLayout(new MigLayout("fill", "[][grow, fill]", "[grow, fill]"));

    // sic! because of the add methods below ..
    final CampEditView pev = new CampEditView("campEdit");
    final CampListView clv = new CampListView("campList", this);
    editView = pev;
    listView = clv;

    final CPanel buttonPanel = new CPanel("buttons", new MigLayout());
    final CButton createButton = new CButton("newCamp", Action.getCreateCampAction());
    final CButton deleteButton = new CButton("deleteCamp", Action.getDeleteCampAction());
    final CButton cancelButton = new CButton("cancelCamp", Action.getCancelCampAction());
    buttonPanel.add(createButton, "sg one");
    buttonPanel.add(deleteButton, "sg one");
    buttonPanel.add(cancelButton, "sg one");

    final CPanel leftPanel = new CPanel("campList", new MigLayout("fill", "[grow, fill]", "[][grow, fill]"));
    leftPanel.add(buttonPanel, "wrap");
    leftPanel.add(clv);

    add(leftPanel);
    add(pev);
  }

  @Override
  @NotNull
  public ICampEditView getCampEditView() {
    return editView;
  }

  @Override
  @NotNull
  public IListView<Camp> getCampListView() {
    return listView;
  }

  @Override
  public void performAction(final UserAction action) {
    if (action == UserAction.CAMP_SELECTED) {
      editView.setCamp(model.getCamp(listView.getSelectedElementId()));
    } else {
      LOGGER.warn(Text.WARN_UNKNOWN_ACTION.text(action));
    }
  }

  @Override
  public void createCamp() {
    editView.clear();
  }
}
