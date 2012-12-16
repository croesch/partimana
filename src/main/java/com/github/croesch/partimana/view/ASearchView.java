package com.github.croesch.partimana.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;

import com.github.croesch.components.CButton;
import com.github.croesch.components.CComboBox;
import com.github.croesch.components.CFrame;
import com.github.croesch.components.CLabel;
import com.github.croesch.components.CPanel;
import com.github.croesch.components.CScrollPane;
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

  /** the number of filters currently active */
  private int filters = 0;

  /** the panel that holds the representation of the different filters */
  private final CPanel filterPanel = new CPanel("filters", new MigLayout("fill,wrap 4",
                                                                         "[grow,fill][40%][][fill,grow]",
                                                                         "[fill]"));

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
    super(name, Text.SEARCH_TITLE.text(Text.PARTIMANA, Text.SEARCH));
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
    setLayout(new MigLayout("fill,wrap 1"));

    addFilterComposition();
    addFilterRepresentation();
    add(getListView().toComponent());
    addButtons();

    pack();
  }

  /**
   * Adds the panel for representing the filters to the view.
   * 
   * @since Date: Nov 23, 2012
   */
  private void addFilterRepresentation() {
    add(new CScrollPane("filters", this.filterPanel), "grow");
  }

  /**
   * Adds components to create the next filter.
   * 
   * @since Date: Nov 11, 2012
   */
  private void addFilterComposition() {
    final CPanel panel = new CPanel("filterComposition", new MigLayout("fill"));
    this.categoryCBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        updateFilterTypeComboBox();
      }
    });
    panel.add(this.categoryCBox, "shrink");

    updateFilterTypeComboBox();
    panel.add(this.filterTypeCBox, "shrink");

    panel.add(this.filterValueTBox, "grow");

    final CPanel buttons = new CPanel("buttons");

    final CButton andButton = new CButton("and", Text.FILTER_AND.text());
    andButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final IFilter<T> filter = createAdministeredFilter();
        addRepresentation(filter, Text.FILTER_AND);
        ASearchView.this.filterModel.and(filter);
        updateListView();
      }
    });
    buttons.add(andButton);

    final CButton orButton = new CButton("or", Text.FILTER_OR.text());
    orButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final IFilter<T> filter = createAdministeredFilter();
        addRepresentation(filter, Text.FILTER_OR);
        ASearchView.this.filterModel.or(filter);
        updateListView();
      }
    });
    buttons.add(orButton);

    panel.add(buttons, "newline, span 3");
    add(panel, "grow");
  }

  /**
   * Adds the representation for the given filter to the view.
   * 
   * @since Date: Nov 23, 2012
   * @param filter the filter to represent
   * @param connection the text that visualizes the connection of this filter to the previous filter
   */
  private void addRepresentation(final IFilter<T> filter, final Text connection) {
    final String namePrefix = "f" + ++this.filters + "-";

    final CLabel connLabel = new CLabel(namePrefix + "connection");
    if (this.filters > 1) {
      connLabel.setText(connection.text());
    }

    this.filterPanel.add(connLabel);

    final CComboBox categoryBox = new CComboBox(namePrefix + "category", getPossibleCategories());
    categoryBox.setEnabled(false);
    categoryBox.setSelectedItem(filter.getCategory());
    this.filterPanel.add(categoryBox);

    final CComboBox filterTypeBox = new CComboBox(namePrefix + "filterType");
    for (int i = 0; i < this.filterTypeCBox.getItemCount(); ++i) {
      filterTypeBox.addItem(this.filterTypeCBox.getItemAt(i));
    }
    filterTypeBox.setEnabled(false);
    filterTypeBox.setSelectedItem(filter.getCategory().getFilter());
    this.filterPanel.add(filterTypeBox);

    final CTextField filterValueField = new CTextField(namePrefix + "filterValue", this.filterValueTBox.getText());
    filterValueField.setEnabled(false);
    this.filterPanel.add(filterValueField);
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
   * @param <OT> the type of the objects the filter type filters
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
    final CPanel buttonPanel = new CPanel("buttons");

    final CButton closeButton = new CButton("close", Text.CANCEL.text());
    closeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        dispose();
      }
    });
    buttonPanel.add(closeButton);

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
    buttonPanel.add(selectButton);

    add(buttonPanel);
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

  /**
   * Returns the id of the selected item.
   * 
   * @since Date: Dec 16, 2012
   * @return the id of the selected item,<br>
   *         or <code>0</code> if no element is selected.
   */
  public final long getSelectedId() {
    return getListView().getSelectedElementId();
  }
}
