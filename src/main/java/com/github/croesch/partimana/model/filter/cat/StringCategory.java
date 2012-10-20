package com.github.croesch.partimana.model.filter.cat;

import java.util.ArrayList;
import java.util.List;

import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.types.Contains;
import com.github.croesch.partimana.model.filter.types.EndsWith;
import com.github.croesch.partimana.model.filter.types.Equals;
import com.github.croesch.partimana.model.filter.types.EqualsIgnoreCase;
import com.github.croesch.partimana.model.filter.types.NotEquals;
import com.github.croesch.partimana.model.filter.types.NotEqualsIgnoreCase;
import com.github.croesch.partimana.model.filter.types.StartsWith;
import com.github.croesch.partimana.types.api.IFilterable;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public abstract class StringCategory<F extends IFilterable> extends ACategory<F, String> {

  private final String description;

  public StringCategory(final String descr) {
    this.description = descr;
  }

  @Override
  public final String getShortDescription() {
    return this.description;
  }

  @Override
  public final List<IFilterType<String>> getFilterTypes() {
    final List<IFilterType<String>> l = new ArrayList<IFilterType<String>>();
    l.add(new Equals<String>());
    l.add(new NotEquals<String>());
    l.add(new Contains());
    l.add(new EndsWith());
    l.add(new EqualsIgnoreCase());
    l.add(new NotEqualsIgnoreCase());
    l.add(new StartsWith());
    return l;
  }
}
