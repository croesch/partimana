package com.github.croesch.partimana.model.filter.cat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.types.After;
import com.github.croesch.partimana.model.filter.types.Before;
import com.github.croesch.partimana.model.filter.types.Equals;
import com.github.croesch.partimana.model.filter.types.NotEquals;
import com.github.croesch.partimana.types.api.IFilterable;

/**
 * A category that describes attributes that have the object type {@link Date}.
 * 
 * @author croesch
 * @param <F> the {@link IFilterable} element that is filtered
 * @since Date: Oct 20, 2012
 */
public abstract class DateCategory<F extends IFilterable> extends ACategory<F, Date> {

  public DateCategory(final String descr) {
    super(descr);
  }

  @Override
  public final List<IFilterType<Date>> getFilterTypes() {
    final List<IFilterType<Date>> l = new ArrayList<IFilterType<Date>>();
    l.add(new Equals<Date>());
    l.add(new NotEquals<Date>());
    l.add(new Before());
    l.add(new After());
    return l;
  }
}
