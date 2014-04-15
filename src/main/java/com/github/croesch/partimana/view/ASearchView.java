package com.github.croesch.partimana.view;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.components.*;
import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilter;
import com.github.croesch.partimana.model.api.IFilterCategory;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.FilterModel;
import com.github.croesch.partimana.model.filter.types.DateFilterType;
import com.github.croesch.partimana.types.api.IFilterable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;
import java.util.Locale;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.miginfocom.swing.MigLayout;

/**
 * A view to allow users to search the stored data.
 *
 * @param <T> the type of the objects to search for
 * @author croesch
 * @since Date: Nov 2, 2012
 */
abstract class ASearchView<T extends IFilterable> extends CFrame {

  /** generated serial version UID */
  private static final long serialVersionUID = -2064381374545329865L;

  /** the observer that will be notified when a selection has been made */
  private final ActionObserver observer;

  /** the combobox that holds the filter type */
  private final CComboBox filterTypeCBox = new CComboBox("filterType");

  /** the combobox that holds the filter category */
  private final CComboBox categoryCBox = new CComboBox("category", getPossibleCategories());

  /** the text box that holds the value for the filter */
  private final CTextField filterValueStringBox = new CTextField("filterValue");

  /** the text box that holds the value for the filter */
  private final CDateField filterValueDateBox = new CDateField(Locale.GERMANY);

  /** the text box that holds the value for the filter */
  private JTextField filterValueTBox = filterValueStringBox;

  /** the model that filters the stored data */
  private final FilterModel<T> filterModel;

  /** the button to select the current result. */
  @NotNull
  private final CButton selectButton = new CButton("select", Text.SELECT.text());

  /**
   * Constructs the search view with the given model. The model provides the different filters the user can use and
   * provides access to all objects that matches the settings the user set up.
   *
   * @param name  the name of the view
   * @param model provides all filters the user can use and provides access to a list of elements that match the current
   *              filter settings
   * @param o     the observer that will be notified when a selection has been made
   * @since Date: Nov 2, 2012
   */
  public ASearchView(final String name, final FilterModel<T> model, final ActionObserver o) {
    super(name, Text.SEARCH_TITLE.text(Text.PARTIMANA, Text.SEARCH));
    this.filterModel = model;
    this.observer = o;
    buildUI();
  }

  /**
   * Returns the observer that will be notified when a selection has been made
   *
   * @return the observer that will be notified when a selection has been made
   * @since Date: Nov 11, 2012
   */
  protected final ActionObserver getObserver() {
    return this.observer;
  }

  /**
   * Constructs the user interface of the search view.
   *
   * @since Date: Nov 2, 2012
   */
  private void buildUI() {
    setLayout(new MigLayout("fill,wrap 1"));

    addFilterComposition();
    add(getListView().toComponent());
    addButtons();

    pack();
    updateListView();
  }

  /**
   * Adds components to define the filter.
   *
   * @since Date: Nov 11, 2012
   */
  private void addFilterComposition() {
    final CPanel panel = new CPanel("filterComposition", new MigLayout("fill", "[shrink][shrink][grow]"));
    categoryCBox.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
          updateFilterTypeComboBox();
          updateListView();
        }
      }
    });
    panel.add(this.categoryCBox);

    updateFilterTypeComboBox();
    filterTypeCBox.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
          updateListView();
          updateTextComponent(panel);
        }
      }
    });
    panel.add(this.filterTypeCBox);

    updateTextComponent(panel);
    filterValueDateBox.setName("filterValue");
    addDocumentListener(filterValueDateBox);
    addDocumentListener(filterValueStringBox);
    add(panel, "grow");
  }

  private void addDocumentListener(JTextField field) {
    field.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent documentEvent) {
        updateListView();
      }

      @Override
      public void removeUpdate(DocumentEvent documentEvent) {
        updateListView();
      }

      @Override
      public void changedUpdate(DocumentEvent documentEvent) {
        updateListView();
      }
    });
  }

  private void updateTextComponent(CPanel panel) {
    panel.remove(filterValueTBox);
    if (filterTypeCBox.getSelectedItem() instanceof DateFilterType) {
      filterValueTBox = filterValueDateBox;
    } else {
      filterValueTBox = filterValueStringBox;
    }
    panel.add(filterValueTBox, "grow");
  }

  /**
   * Updates the contents of the list view with the current result of the filter model.
   *
   * @since Date: Nov 17, 2012
   */
  private void updateListView() {
    filterModel.setFilter(createAdministeredFilter());
    getListView().update(filterModel.getFilterMatchingElements());
  }

  /**
   * Creates and returns the filter based on the values of the text and combo boxes.
   *
   * @param <OT> the type of the objects the filter type filters
   * @return the filter based on the values of the text and combo boxes.
   * @since Date: Nov 17, 2012
   */
  private <OT> IFilter<T> createAdministeredFilter() {
    final IFilter<T> filter = createEmptyFilter();
    @SuppressWarnings("unchecked")
    final IFilterCategory<T, OT> category =
        ((IFilterCategory<T, OT>) ASearchView.this.categoryCBox.getSelectedItem()).getCopy();
    @SuppressWarnings("unchecked")
    final IFilterType<OT> filterType = ((IFilterType<OT>) ASearchView.this.filterTypeCBox.getSelectedItem()).getCopy();
    filterType.parseFilterValue(this.filterValueTBox);
    category.setFilter(filterType);
    filter.setCategory(category);
    return filter;
  }

  /**
   * Creates an empty new filter.
   *
   * @return an empty new filter.
   * @since Date: Nov 15, 2012
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
    for (final IFilterType<?> filterType : ((IFilterCategory<T, ?>) this.categoryCBox.getSelectedItem())
        .getFilterTypes()) {
      this.filterTypeCBox.addItem(filterType);
    }
  }

  /**
   * Returns all possible categories this filter can filter.
   *
   * @return all possible categories this filter can filter.
   * @since Date: Nov 11, 2012
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

    updateSelectButtonState();
    selectButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        getObserver().performAction(getListView().getSelectionAction());
      }
    });
    buttonPanel.add(selectButton);

    add(buttonPanel);
  }

  /**
   * Returns the view that visualizes the current filter matching elements.
   *
   * @return the view that visualizes the current filter matching elements.
   * @since Date: Nov 11, 2012
   */
  protected abstract AListView<T> getListView();

  /**
   * Updates the state of the select button. It will be enabled if {@link #shouldSelectButtonBeEnabled()} returns
   * <code>true</code>.
   *
   * @since Date: Nov 10, 2012
   */
  protected void updateSelectButtonState() {
    selectButton.setEnabled(shouldSelectButtonBeEnabled());
  }

  /**
   * @return <code>true</code> if the select button should be enabled. Called frequently.
   * @since Date: Apr 13, 2014
   */
  protected abstract boolean shouldSelectButtonBeEnabled();

  /**
   * @return the id of the selected item,<br> or <code>0</code> if no element is selected.
   * @since Date: Dec 16, 2012
   */
  public final long getSelectedId() {
    return getListView().getSelectedElementId();
  }

  /**
   * @return the ids of the items contained in the list.
   * @since Date: Apr 13, 2014
   */
  public final Collection<Long> getAllIds() {
    return getListView().getElementIds();
  }
}
