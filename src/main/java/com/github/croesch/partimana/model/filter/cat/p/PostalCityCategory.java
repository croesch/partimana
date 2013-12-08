package com.github.croesch.partimana.model.filter.cat.p;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.cat.StringCategory;
import com.github.croesch.partimana.types.Participant;

/**
 * The category that describes the attribute <em>postal city</em> of a {@link Participant}.
 *
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class PostalCityCategory extends StringCategory<Participant> {

  /**
   * Constructs the category that describes the attribute <em>postal city</em> of a {@link Participant}.
   *
   * @since Date: Oct 27, 2012
   */
  public PostalCityCategory() {
    super(Text.FILTER_CAT_PARTICIPANT_POSTAL_CITY);
  }

  @Override
  protected String getValue(final Participant element) {
    return element.getCityPostal();
  }

  @Override
  public PostalCityCategory getCopy() {
    final PostalCityCategory copy = new PostalCityCategory();
    if (getFilter() != null) {
      copy.setFilter(getFilter().getCopy());
    }
    return copy;
  }
}
