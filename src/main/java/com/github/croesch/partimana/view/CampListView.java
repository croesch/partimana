package com.github.croesch.partimana.view;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.components.CPanel;
import com.github.croesch.components.CScrollPane;
import com.github.croesch.components.CTable;
import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.view.api.ICampListView;
import com.github.croesch.partimana.view.components.DataTable;

/**
 * Implementation of the table that views the table of camps.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011
 */
class CampListView extends CPanel implements ICampListView {

  /** generated */
  private static final long serialVersionUID = -96888415800702415L;

  /** the table to display the different camps */
  @NotNull
  private final CTable table;

  /**
   * Constructs a new {@link CampListView} that is able to visualise a table of camps. The observer will be notified
   * about selection changes.
   * 
   * @since Date: Sep 27, 2011
   * @param name the name of this component
   * @param o the {@link ActionObserver} that listens for the selection change event.
   */
  public CampListView(final String name, final ActionObserver o) {
    super(name);
    setLayout(new MigLayout("fill"));

    final Object[] columnIdentifiers = new Object[] { Text.CAMP_ID.text(),
                                                     Text.CAMP_LOCATION.text(),
                                                     Text.CAMP_NAME.text() };
    this.table = new DataTable("camps", o, columnIdentifiers, UserAction.CAMP_SELECTED);

    add(new CScrollPane("camps", this.table), "grow");
  }

  @Override
  public void update(final List<Camp> camps) {
    while (this.table.getRowCount() > 0) {
      ((DefaultTableModel) this.table.getModel()).removeRow(0);
    }
    for (final Camp c : camps) {
      ((DefaultTableModel) this.table.getModel()).addRow(new Object[] { c.getId(), c.getLocation(), c.getName() });
    }
  }

  @Override
  public long getSelectedCampId() {
    if (this.table.getSelectedRowCount() == 0) {
      return 0;
    }
    return Long.parseLong(this.table.getValueAt(this.table.getSelectedRow(), 0).toString());
  }
}
