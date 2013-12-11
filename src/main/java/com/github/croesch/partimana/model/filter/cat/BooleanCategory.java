package com.github.croesch.partimana.model.filter.cat;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.types.BooleanEquals;
import com.github.croesch.partimana.model.filter.types.BooleanNotEquals;
import com.github.croesch.partimana.types.api.IFilterable;
import java.util.ArrayList;
import java.util.List;

/**
 * A category that describes attributes that have the object type {@link Boolean}.
 *
 * @param <F> the {@link IFilterable} element that is filtered
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public abstract class BooleanCategory<F extends IFilterable> extends ACategory<F, Boolean> {

  /**
   * Constructs this category with the given viewable description.
   *
   * @param description the short description that can be shown to the user
   * @since Date: Oct 27, 2012
   */
  public BooleanCategory(final Text description) {
    super(description);
  }

  @Override
  public final List<IFilterType<Boolean>> getFilterTypes() {
    final List<IFilterType<Boolean>> l = new ArrayList<IFilterType<Boolean>>();
    l.add(new BooleanEquals());
    l.add(new BooleanNotEquals());
    return l;
  }
}
