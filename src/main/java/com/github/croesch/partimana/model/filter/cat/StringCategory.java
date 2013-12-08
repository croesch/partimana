package com.github.croesch.partimana.model.filter.cat;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.types.*;
import com.github.croesch.partimana.types.api.IFilterable;
import java.util.ArrayList;
import java.util.List;

/**
 * A category that describes attributes that have the object type {@link String}.
 *
 * @param <F> the {@link IFilterable} element that is filtered
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public abstract class StringCategory<F extends IFilterable> extends ACategory<F, String> {

  /**
   * Constructs this category with the given viewable description.
   *
   * @param descr the short description that can be shown to the user
   * @since Date: Oct 27, 2012
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
