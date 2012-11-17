package com.github.croesch.partimana.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;

import com.github.croesch.components.CButton;
import com.github.croesch.components.CComboBox;
import com.github.croesch.components.CFrame;
import com.github.croesch.components.CPanel;
import com.github.croesch.components.CTextField;
import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilter;
import com.github.croesch.partimana.model.api.IFilterCategory;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.FilterModel;
import com.github.croesch.partimana.types.api.IFilterable;

/**
 * A view to allow users to search the stored data.
 * 
 * @param <T> the type of the objects to search for
 * @author croesch
 * @since Date: Nov 2, 2012
 */
public abstract class ASearchView<T extends IFilterable> extends CFrame {

  /** generated serial version UID */
  private static final long serialVersionUID = -2064381374545329865L;

  /** the observer that will be notified when a selection has been made */
  private final ActionObserver observer;

  /** the combobox that holds the filter type */
  private final CComboBox filterTypeCBox = new CComboBox("filterType");

  /** the combobox that holds the filter category */
  private final CComboBox categoryCBox = new CComboBox("category", getPossibleCategories());

  /** the text box that holds the value for the filter */
  private final CTextField filterValueTBox = new CTextField("filterValue");

  /** the model that filters the stored data */
  private final FilterModel<T> filterModel;

  /**
   * Constructs the search view with the given model. The model provides the different filters the user can use and
   * provides access to all objects that matches the settings the user set up.
   * 
   * @since Date: Nov 2, 2012
   * @param name the name of the view
   * @param model provides all filters the user can use and provides access to a list of elements that match the current
   *        filter settings
   * @param o the observer that will be notified when a selection has been made
   */
  public ASearchView(final String name, final FilterModel<T> model, final ActionObserver o) {
    super(name);
    this.filterModel = model;
    this.observer = o;
    builUI();
  }

  /**
   * Returns the observer that will be notified when a selection has been made
   * 
   * @since Date: Nov 11, 2012
   * @return the observer that will be notified when a selection has been made
   */
  protected final ActionObserver getObserver() {
    return this.observer;
  }

  /**
   * Constructs the user interface of the search view.
   * 
   * @since Date: Nov 2, 2012
   */
  private void builUI() {
    setLayout(new MigLayout());

    addFilterComposition();
    add(getListView().toComponent());
    addButtons();

    pack();
  }

  /**
   * Adds components to create the next filter.
   * 
   * @since Date: Nov 11, 2012
   */
  private void addFilterComposition() {
    final CPanel panel = new CPanel("filterComposition");
    this.categoryCBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        updateFilterTypeComboBox();
      }
    });
    panel.add(this.categoryCBox);

    updateFilterTypeComboBox();
    panel.add(this.filterTypeCBox);

    panel.add(this.filterValueTBox);

    final CButton andButton = new CButton("and", Text.FILTER_AND.text());
    andButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final IFilter<T> filter = createAdministeredFilter();
        ASearchView.this.filterModel.and(filter);
        updateListView();
      }
    });
    panel.add(andButton);

    final CButton orButton = new CButton("or", Text.FILTER_OR.text());
    orButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final IFilter<T> filter = createAdministeredFilter();
        ASearchView.this.filterModel.or(filter);
        updateListView();
      }
    });
    panel.add(orButton);

    add(panel);
  }

  /**
   * Updates the contents of the list view with the current result of the filter model.
   * 
   * @since Date: Nov 17, 2012
   */
  private void updateListView() {
    getListView().update(ASearchView.this.filterModel.getFilterMatchingElements());
  }

  /**
   * Creates and returns the filter based on the values of the text and combo boxes.
   * 
   * @since Date: Nov 17, 2012
   * @return the filter based on the values of the text and combo boxes.
   */
  private <OT extends Object> IFilter<T> createAdministeredFilter() {
    final IFilter<T> filter = createEmptyFilter();
    final IFilterCategory<T, OT> category = (IFilterCategory<T, OT>) ASearchView.this.categoryCBox.getSelectedItem();
    final IFilterType<OT> filterType = (IFilterType<OT>) ASearchView.this.filterTypeCBox.getSelectedItem();
    filterType.parseFilterValue(this.filterValueTBox.getText());
    category.setFilter(filterType);
    filter.setCategory(category);
    return filter;
  }

  /**
   * Creates an empty new filter.
   * 
   * @since Date: Nov 15, 2012
   * @return an empty new filter.
   */
  protected abstract IFilter<T> createEmptyFilter();

  /**
   * Updates the combobox that holds the filter types base on the currently selected filter category.
   * 
   * @since Date: Nov 11, 2012
   */
  @SuppressWarnings("unchecked")
  private void updateFilterTypeComboBox() {
    this.filterTypeCBox.removeAllItems();
    for (final IFilterType<?> filterType : ((IFilterCategory<T, ?>) this.categoryCBox.getSelectedItem()).getFilterTypes()) {
      this.filterTypeCBox.addItem(filterType);
    }
  }

  /**
   * Returns all possible categories this filter can filter.
   * 
   * @since Date: Nov 11, 2012
   * @return all possible categories this filter can filter.
   */
  protected abstract Object[] getPossibleCategories();

  /**
   * Adds the different buttons to the view.
   * 
   * @since Date: Nov 10, 2012
   */
  private void addButtons() {
    final CButton closeButton = new CButton("close", Text.CANCEL.text());
    closeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        dispose();
      }
    });
    add(closeButton);

    final CButton selectButton = new CButton("select", Text.SELECT.text());
    updateSelectButtonState(selectButton);
    selectButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        getObserver().performAction(getListView().getSelectionAction());
      }
    });
    getListView().addSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(final ListSelectionEvent e) {
        updateSelectButtonState(selectButton);
      }
    });
    add(selectButton);
  }

  /**
   * Returns the view that visualizes the current filter matching elements.
   * 
   * @since Date: Nov 11, 2012
   * @return the view that visualizes the current filter matching elements.
   */
  protected abstract AListView<T> getListView();

  /**
   * Updates the state of the select button. The given button (assumed to be the select button) will be enabled if the
   * given list has a selectable selection.
   * 
   * @since Date: Nov 10, 2012
   * @param selectButton the button to enable, if the selection in the list is selectable or disable otherwise
   */
  private void updateSelectButtonState(final CButton selectButton) {
    selectButton.setEnabled(getListView().getSelectedElementId() != 0);
  }
}
