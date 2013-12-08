package com.github.croesch.partimana.model.filter.cat.c;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.cat.DateCategory;
import com.github.croesch.partimana.types.Camp;
import java.util.Date;

/**
 * The category that describes the attribute <em>until</em> of a {@link Camp}.
 *
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class UntilCategory extends DateCategory<Camp> {

  /**
   * Constructs the category that describes the attribute <em>until</em> of a {@link Camp}.
   *
   * @since Date: Oct 27, 2012
   */
  public UntilCategory() {
    super(Text.FILTER_CAT_CAMP_UNTIL);
  }

  @Override
  protected Date getValue(final Camp element) {
    return element.getUntilDate();
  }

  @Override
  public UntilCategory getCopy() {
    final UntilCategory copy = new UntilCategory();
    if (getFilter() != null) {
      copy.setFilter(getFilter().getCopy());
    }
    return copy;
  }
}
