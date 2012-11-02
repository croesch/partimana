package com.github.croesch.partimana.view;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;

import com.github.croesch.components.CButton;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.FilterModel;
import com.github.croesch.partimana.types.api.IFilterable;

/**
 * A view to allow users to search the stored data.
 * 
 * @param <T> the type of the objects to search for
 * @author croesch
 * @since Date: Nov 2, 2012
 */
public class SearchView<T extends IFilterable> extends JFrame {

  /** generated serial version UID */
  private static final long serialVersionUID = -2064381374545329865L;

  /**
   * Constructs the search view with the given model. The model provides the different filters the user can use and
   * provides access to all objects that matches the settings the user set up.
   * 
   * @since Date: Nov 2, 2012
   * @param model provides all filters the user can use and provides access to a list of elements that match the current
   *        filter settings
   */
  public SearchView(final FilterModel<T> model) {
    builUI();
  }

  /**
   * Constructs the user interface of the search view.
   * 
   * @since Date: Nov 2, 2012
   */
  private void builUI() {
    setLayout(new MigLayout());
    final CButton button = new CButton(Text.CLOSE.text());
    button.setName("close");
    add(button);
  }
}
