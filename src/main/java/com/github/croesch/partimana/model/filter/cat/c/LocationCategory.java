package com.github.croesch.partimana.model.filter.cat.c;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.cat.StringCategory;
import com.github.croesch.partimana.types.Camp;

/**
 * The category that describes the attribute <em>location</em> of a {@link Camp}.
 *
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class LocationCategory extends StringCategory<Camp> {

  /**
   * Constructs the category that describes the attribute <em>location</em> of a {@link Camp}.
   *
   * @since Date: Oct 27, 2012
   */
  public LocationCategory() {
    super(Text.FILTER_CAT_CAMP_LOCATION);
  }

  @Override
  protected String getValue(final Camp element) {
    return element.getLocation();
  }

  @Override
  public LocationCategory getCopy() {
    final LocationCategory copy = new LocationCategory();
    if (getFilter() != null) {
      copy.setFilter(getFilter().getCopy());
    }
    return copy;
  }
}
