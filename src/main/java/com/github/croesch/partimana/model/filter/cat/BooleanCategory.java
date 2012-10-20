package com.github.croesch.partimana.model.filter.cat;

import java.util.ArrayList;
import java.util.List;

import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.types.Equals;
import com.github.croesch.partimana.model.filter.types.NotEquals;
import com.github.croesch.partimana.types.api.IFilterable;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public abstract class BooleanCategory<F extends IFilterable> extends ACategory<F, Boolean> {

  private final String description;

  public BooleanCategory(final String descr) {
    this.description = descr;
  }

  @Override
  public final String getShortDescription() {
    return this.description;
  }

  @Override
  public final List<IFilterType<Boolean>> getFilterTypes() {
    final List<IFilterType<Boolean>> l = new ArrayList<IFilterType<Boolean>>();
    l.add(new Equals<Boolean>());
    l.add(new NotEquals<Boolean>());
    return l;
  }
}
