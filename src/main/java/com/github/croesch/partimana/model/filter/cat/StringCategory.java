package com.github.croesch.partimana.model.filter.cat;

import java.util.ArrayList;
import java.util.List;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.types.Contains;
import com.github.croesch.partimana.model.filter.types.EndsWith;
import com.github.croesch.partimana.model.filter.types.EqualsIgnoreCase;
import com.github.croesch.partimana.model.filter.types.NotEqualsIgnoreCase;
import com.github.croesch.partimana.model.filter.types.StartsWith;
import com.github.croesch.partimana.model.filter.types.StringEquals;
import com.github.croesch.partimana.model.filter.types.StringNotEquals;
import com.github.croesch.partimana.types.api.IFilterable;

/**
 * A category that describes attributes that have the object type {@link String}.
 * 
 * @author croesch
 * @param <F> the {@link IFilterable} element that is filtered
 * @since Date: Oct 20, 2012
 */
public abstract class StringCategory<F extends IFilterable> extends ACategory<F, String> {

  /**
   * Constructs this category with the given viewable description.
   * 
   * @since Date: Oct 27, 2012
   * @param descr the short description that can be shown to the user
   */
  public StringCategory(final Text descr) {
    super(descr);
  }

  @Override
  public final List<IFilterType<String>> getFilterTypes() {
    final List<IFilterType<String>> l = new ArrayList<IFilterType<String>>();
    l.add(new StringEquals());
    l.add(new StringNotEquals());
    l.add(new Contains());
    l.add(new EndsWith());
    l.add(new EqualsIgnoreCase());
    l.add(new NotEqualsIgnoreCase());
    l.add(new StartsWith());
    return l;
  }
}
