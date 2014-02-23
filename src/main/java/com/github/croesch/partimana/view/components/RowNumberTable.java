package com.github.croesch.partimana.view.components;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 * Use a JTable as a renderer for row numbers of a given main table. This table must be added to the row header of the
 * scrollpane that contains the main table.
 * <p/>
 * Source: http://tips4java.wordpress.com/2008/11/18/row-number-table/
 *
 * @since Date: Feb 23, 2014
 */
public class RowNumberTable extends JTable implements ChangeListener, PropertyChangeListener, TableModelListener {
  private JTable main;

  public RowNumberTable(JTable table) {
    main = table;
    main.getModel().addTableModelListener(this);

    setFocusable(false);
    setAutoCreateColumnsFromModel(false);
    setSelectionModel(main.getSelectionModel());
    setShowGrid(false);

    TableColumn column = new TableColumn();
    column.setHeaderValue(" ");
    addColumn(column);
    column.setCellRenderer(new JTableHeader().getDefaultRenderer());

    getColumnModel().getColumn(0).setPreferredWidth(50);
    setPreferredScrollableViewportSize(getPreferredSize());
  }

  @Override
  public void addNotify() {
    super.addNotify();

    Component c = getParent();

    //  Keep scrolling of the row table in sync with the main table.
    if (c instanceof JViewport) {
      JViewport viewport = (JViewport) c;
      viewport.addChangeListener(this);
    }
  }

  @Override
  public int getRowCount() {
    return main.getRowCount();
  }

  @Override
  public int getRowHeight(int row) {
    int rowHeight = main.getRowHeight(row);

    if (rowHeight != super.getRowHeight(row)) {
      super.setRowHeight(row, rowHeight);
    }

    return rowHeight;
  }

  @Override
  public Object getValueAt(int row, int column) {
    return Integer.toString(row + 1);
  }

  @Override
  public boolean isCellEditable(int row, int column) {
    return false;
  }

  @Override
  public void setValueAt(Object value, int row, int column) {
    //  Do nothing since the table ignores the model
  }

  public void stateChanged(ChangeEvent e) {
    //  Keep the scrolling of the row table in sync with main table

    JViewport viewport = (JViewport) e.getSource();
    JScrollPane scrollPane = (JScrollPane) viewport.getParent();
    scrollPane.getVerticalScrollBar().setValue(viewport.getViewPosition().y);
  }

  public void propertyChange(PropertyChangeEvent e) {
    //  Keep the row table in sync with the main table

    if ("selectionModel".equals(e.getPropertyName())) {
      setSelectionModel(main.getSelectionModel());
    } else if ("rowHeight".equals(e.getPropertyName())) {
      repaint();
    }
  }
}
