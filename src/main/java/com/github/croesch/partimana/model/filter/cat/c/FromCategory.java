package com.github.croesch.partimana.model.filter.cat.c;

import java.util.Date;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.cat.DateCategory;
import com.github.croesch.partimana.types.Camp;

/**
 * The category that describes the attribute <em>from</em> of a {@link Camp}.
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class FromCategory extends DateCategory<Camp> {

  /**
   * Constructs the category that describes the attribute <em>from</em> of a {@link Camp}.
   * 
   * @since Date: Oct 22, 2012
   */
  public FromCategory() {
    super(Text.FILTER_CAT_CAMP_FROM.text());
  }

  @Override
  protected Date getValue(final Camp element) {
    return element.getFromDate();
  }
}
