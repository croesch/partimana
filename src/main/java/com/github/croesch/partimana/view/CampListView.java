package com.github.croesch.partimana.view;

import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.types.Camp;
import javax.swing.table.DefaultTableModel;

/**
 * Implementation of the table that views the table of camps.
 *
 * @author croesch
 * @since Date: Jun 8, 2011
 */
class CampListView extends AListView<Camp> {

  /** generated */
  private static final long serialVersionUID = -96888415800702415L;

  /**
   * Constructs a new {@link CampListView} that is able to visualise a table of camps. The observer will be notified
   * about selection changes.
   *
   * @param name the name of this component
   * @param o    the {@link ActionObserver} that listens for the selection change event.
   * @since Date: Sep 27, 2011
   */
  public CampListView(final String name, final ActionObserver o) {
    super(name, "camps", o, new CampTableModel(), UserAction.CAMP_SELECTED);
  }

  /**
   * Model for a table holding camp entries.
   *
   * @author croesch
   * @since Date: Nov 11, 2012
   */
  private static class CampTableModel extends DefaultTableModel {

    /** generated serial version UID */
    private static final long serialVersionUID = 1213076314951448073L;

    /**
     * Constructs a model for a table holding camp entries.
     *
     * @since Date: Nov 11, 2012
     */
    public CampTableModel() {
      setColumnIdentifiers(new Object[] { Text.CAMP_ID.text(), Text.CAMP_LOCATION.text(), Text.CAMP_NAME.text() });
    }

    @Override
    public boolean isCellEditable(final int row, final int column) {
      return false;
    }
  }

  @Override
  protected Object[] getRowArray(final Camp c) {
    return new Object[] { c.getId(), c.getLocation(), c.getName() };
  }
}
