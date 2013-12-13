package com.github.croesch.partimana.model.filter.cat;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.types.After;
import com.github.croesch.partimana.model.filter.types.Before;
import com.github.croesch.partimana.model.filter.types.DateEquals;
import com.github.croesch.partimana.model.filter.types.DateNotEquals;
import com.github.croesch.partimana.types.api.IFilterable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A category that describes attributes that have the object type {@link Date}.
 *
 * @param <F> the {@link IFilterable} element that is filtered
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public abstract class DateCategory<F extends IFilterable> extends ACategory<F, Date> {

  /**
   * Constructs this category with the given viewable description.
   *
   * @param description the short description that can be shown to the user
   * @since Date: Oct 27, 2012
   */
  public DateCategory(final Text description) {
    super(description);
  }

  @Override
  public final List<IFilterType<Date>> getFilterTypes() {
    final List<IFilterType<Date>> l = new ArrayList<IFilterType<Date>>();
    l.add(new DateEquals());
    l.add(new DateNotEquals());
    l.add(new Before());
    l.add(new After());
    return l;
  }
}
