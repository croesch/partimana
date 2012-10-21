package com.github.croesch.partimana.model.filter.cat;

import java.util.ArrayList;
import java.util.List;

import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.types.Equals;
import com.github.croesch.partimana.model.filter.types.GreaterThan;
import com.github.croesch.partimana.model.filter.types.LessThan;
import com.github.croesch.partimana.model.filter.types.NotEquals;
import com.github.croesch.partimana.types.api.IFilterable;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public abstract class IntegerCategory<F extends IFilterable> extends ACategory<F, Integer> {

  public IntegerCategory(final String descr) {
    super(descr);
  }

  @Override
  public final List<IFilterType<Integer>> getFilterTypes() {
    final List<IFilterType<Integer>> l = new ArrayList<IFilterType<Integer>>();
    l.add(new Equals<Integer>());
    l.add(new NotEquals<Integer>());
    l.add(new LessThan());
    l.add(new GreaterThan());
    return l;
  }
}
