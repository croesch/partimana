package com.github.croesch.partimana.model.filter.types;

import com.github.croesch.partimana.types.CountyCouncil;
import javax.swing.JComboBox;
import javax.swing.JComponent;

/**
 * Abstract county council filter that filters county-council-objects based on the filter value.
 *
 * @author croesch
 * @since Date: Nov 17, 2012
 */
public abstract class CountyCouncilFilterType extends AFilterType<CountyCouncil> {
  @Override
  public final boolean parseFilterValue(final JComponent value) {
    if (value instanceof JComboBox) {
      final Object cc = ((JComboBox<CountyCouncil>) value).getSelectedItem();
      if (cc instanceof CountyCouncil) {
        setFilterValue((CountyCouncil) cc);
        return true;
      }
    }
    return false;
  }
}
