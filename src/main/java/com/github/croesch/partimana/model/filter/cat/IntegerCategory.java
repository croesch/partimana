package com.github.croesch.partimana.model.filter.cat;

import java.util.ArrayList;
import java.util.List;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.types.GreaterThan;
import com.github.croesch.partimana.model.filter.types.IntegerEquals;
import com.github.croesch.partimana.model.filter.types.IntegerNotEquals;
import com.github.croesch.partimana.model.filter.types.LessThan;
import com.github.croesch.partimana.types.api.IFilterable;

/**
 * A category that describes attributes that have the object type {@link Integer}.
 * 
 * @author croesch
 * @param <F> the {@link IFilterable} element that is filtered
 * @since Date: Oct 20, 2012
 */
public abstract class IntegerCategory<F extends IFilterable> extends ACategory<F, Integer> {

  /**
   * Constructs this category with the given viewable description.
   * 
   * @since Date: Oct 27, 2012
   * @param descr the short description that can be shown to the user
   */
  public IntegerCategory(final Text descr) {
    super(descr);
  }

  @Override
  public final List<IFilterType<Integer>> getFilterTypes() {
    final List<IFilterType<Integer>> l = new ArrayList<IFilterType<Integer>>();
    l.add(new IntegerEquals());
    l.add(new IntegerNotEquals());
    l.add(new LessThan());
    l.add(new GreaterThan());
    return l;
  }
}
