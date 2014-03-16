package com.github.croesch.partimana.model.filter.cat.cp;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.cat.DateCategory;
import com.github.croesch.partimana.types.CampParticipant;
import java.util.Date;

/**
 * The category that describes the attribute <em>signed in</em> of a {@link CampParticipant}.
 *
 * @author croesch
 * @since Date: Mar 16, 2014
 */
public final class SignedInCategory extends DateCategory<CampParticipant> {

  /**
   * Constructs the category that describes the attribute <em>signed in</em> of a {@link CampParticipant}.
   *
   * @since Date: Mar 16, 2014
   */
  public SignedInCategory() {
    super(Text.FILTER_CAT_CAMP_PARTICIPANT_SIGNED_IN);
  }

  @Override
  protected Date getValue(final CampParticipant element) {
    return element.getSignedIn();
  }

  @Override
  public SignedInCategory getCopy() {
    final SignedInCategory copy = new SignedInCategory();
    if (getFilter() != null) {
      copy.setFilter(getFilter().getCopy());
    }
    return copy;
  }
}
