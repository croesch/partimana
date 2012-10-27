package com.github.croesch.partimana.model.filter.cat;

import java.util.ArrayList;
import java.util.List;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.types.Equals;
import com.github.croesch.partimana.model.filter.types.NotEquals;
import com.github.croesch.partimana.types.api.IFilterable;

/**
 * A category that describes attributes that have the object type {@link Boolean}.
 * 
 * @author croesch
 * @param <F> the {@link IFilterable} element that is filtered
 * @since Date: Oct 20, 2012
 */
public abstract class BooleanCategory<F extends IFilterable> extends ACategory<F, Boolean> {

  /**
   * Constructs this category with the given viewable description.
   * 
   * @since Date: Oct 27, 2012
   * @param descr the short description that can be shown to the user
   */
  public BooleanCategory(final Text descr) {
    super(descr);
  }

  @Override
  public final List<IFilterType<Boolean>> getFilterTypes() {
    final List<IFilterType<Boolean>> l = new ArrayList<IFilterType<Boolean>>();
    l.add(new Equals<Boolean>());
    l.add(new NotEquals<Boolean>());
    return l;
  }
}
