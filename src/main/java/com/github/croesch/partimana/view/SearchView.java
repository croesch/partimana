package com.github.croesch.partimana.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;

import com.github.croesch.components.CButton;
import com.github.croesch.components.CFrame;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.FilterModel;
import com.github.croesch.partimana.types.api.IFilterable;
import com.github.croesch.partimana.view.api.IListView;

/**
 * A view to allow users to search the stored data.
 * 
 * @param <T> the type of the objects to search for
 * @author croesch
 * @since Date: Nov 2, 2012
 */
public class SearchView<T extends IFilterable> extends CFrame {

  /** generated serial version UID */
  private static final long serialVersionUID = -2064381374545329865L;

  /**
   * Constructs the search view with the given model. The model provides the different filters the user can use and
   * provides access to all objects that matches the settings the user set up.
   * 
   * @since Date: Nov 2, 2012
   * @param name the name of the view
   * @param model provides all filters the user can use and provides access to a list of elements that match the current
   *        filter settings
   * @param listView the view that visualizes the list of elements matching the filters
   */
  public SearchView(final String name, final FilterModel<T> model, final IListView<T> listView) {
    super(name);
    builUI(listView);
  }

  /**
   * Constructs the user interface of the search view.
   * 
   * @since Date: Nov 2, 2012
   * @param list the view that visualizes the list of elements matching the filters
   */
  private void builUI(final IListView<T> list) {
    setLayout(new MigLayout());

    add(list.toComponent());
    addButtons(list);

    pack();
  }

  /**
   * Adds the different buttons to the view.
   * 
   * @param list the view that visualizes the list of elements matching the filters
   * @since Date: Nov 10, 2012
   */
  private void addButtons(final IListView<T> list) {
    final CButton closeButton = new CButton("close", Text.CANCEL.text());
    closeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        dispose();
      }
    });
    add(closeButton);

    final CButton selectButton = new CButton("select", Text.SELECT.text());
    updateSelectButtonState(list, selectButton);
    selectButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        list.getActionObserver().performAction(list.getSelectionAction());
      }
    });
    list.addSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(final ListSelectionEvent e) {
        updateSelectButtonState(list, selectButton);
      }
    });
    add(selectButton);
  }

  /**
   * Updates the state of the select button. The given button (assumed to be the select button) will be enabled if the
   * given list has a selectable selection.
   * 
   * @since Date: Nov 10, 2012
   * @param list the list of elements
   * @param selectButton the button to enable, if the selection in the list is selectable or disable otherwise
   */
  private void updateSelectButtonState(final IListView<T> list, final CButton selectButton) {
    selectButton.setEnabled(list.getSelectedElementId() != 0);
  }
}
