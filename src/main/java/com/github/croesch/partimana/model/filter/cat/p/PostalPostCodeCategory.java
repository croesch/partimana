package com.github.croesch.partimana.model.filter.cat.p;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.cat.IntegerCategory;
import com.github.croesch.partimana.types.Participant;

/**
 * The category that describes the attribute <em>postal postal code</em> of a {@link Participant}.
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class PostalPostCodeCategory extends IntegerCategory<Participant> {

  /**
   * Constructs the category that describes the attribute <em>postal postal code</em> of a {@link Participant}.
   * 
   * @since Date: Oct 27, 2012
   */
  public PostalPostCodeCategory() {
    super(Text.FILTER_CAT_PARTICIPANT_POSTAL_POSTAL_CODE);
  }

  @Override
  protected Integer getValue(final Participant element) {
    return element.getPostCodePostal();
  }

  @Override
  public PostalPostCodeCategory getCopy() {
    final PostalPostCodeCategory copy = new PostalPostCodeCategory();
    if (getFilter() != null) {
      copy.setFilter(getFilter().getCopy());
    }
    return copy;
  }
}
