package com.github.croesch.partimana.model.filter.cat;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.types.GreaterThan;
import com.github.croesch.partimana.model.filter.types.IntegerEquals;
import com.github.croesch.partimana.model.filter.types.IntegerNotEquals;
import com.github.croesch.partimana.model.filter.types.LessThan;
import com.github.croesch.partimana.types.api.IFilterable;
import java.util.ArrayList;
import java.util.List;

/**
 * A category that describes attributes that have the object type {@link Integer}.
 *
 * @param <F> the {@link IFilterable} element that is filtered
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public abstract class IntegerCategory<F extends IFilterable> extends ACategory<F, Integer> {

  /**
   * Constructs this category with the given viewable description.
   *
   * @param description the short description that can be shown to the user
   * @since Date: Oct 27, 2012
   */
  public IntegerCategory(final Text description) {
    super(description);
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
