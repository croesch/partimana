package com.github.croesch.partimana.view.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

/**
 * A mouse listener that selects the row of the table when right click happened.
 *
 * @author croesch
 * @since Date: Mar 17, 2013
 */
public final class TableSelectOnClickListener extends MouseAdapter {

  @Override
  public void mouseReleased(final MouseEvent e) {
    if (e.getSource() instanceof JTable) {
      final JTable table = (JTable) e.getSource();
      final int row = table.rowAtPoint(e.getPoint());

      table.setRowSelectionInterval(row, row);
    }
  }
}
