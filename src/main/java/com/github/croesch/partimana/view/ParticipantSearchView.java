package com.github.croesch.partimana.view;

import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.model.api.IFilter;
import com.github.croesch.partimana.model.filter.FilterModel;
import com.github.croesch.partimana.model.filter.ParticipantFilter;
import com.github.croesch.partimana.model.filter.cat.p.*;
import com.github.croesch.partimana.types.Participant;
import java.util.List;

/**
 * The view that allows users to search the stored participants.
 *
 * @author croesch
 * @since Date: Jan 26, 2013
 */
public final class ParticipantSearchView extends ASingleSelectSearchView<Participant> {

  /** generated serial version UID */
  private static final long serialVersionUID = -855139359052994514L;

  /** the view that visualizes the filter matching participants */
  private AListView<Participant> lView;

  /** contains the possible categories the participants can be searched for */
  private Object[] objects;

  /**
   * Constructs the participant search view that allows the user to search stored participants.
   *
   * @param name     the name of this view
   * @param elements the elements that can be searched
   * @param o        the observer that will be notified, when a participant selection has been made
   * @since Date: Jan 26, 2013
   */
  public ParticipantSearchView(final String name, final List<Participant> elements, final ActionObserver o) {
    super(name, new FilterModel<Participant>(elements), o);
  }

  @Override
  protected AListView<Participant> getListView() {
    if (lView == null) {
      lView = new ParticipantListView("list", getObserver());
    }
    return lView;
  }

  @Override
  protected Object[] getPossibleCategories() {
    if (objects == null) {
      objects = new Object[] { new BankAccountNumberCategory(),
                                    new BankCodeNumberCategory(),
                                    new BankNameCategory(),
                                    new BirthdayCategory(),
                                    new CommentCategory(),
                                    new CountyCouncilCategory(),
                                    new DateSinceCategory(),
                                    new DateUntilCategory(),
                                    new DenominationCategory(),
                                    new FaxCategory(),
                                    new ForeNameCategory(),
                                    new GenderCategory(),
                                    new LastNameCategory(),
                                    new LivingCityCategory(),
                                    new LivingPostCodeCategory(),
                                    new LivingStreetCategory(),
                                    new MailAddressCategory(),
                                    new MobilePhoneCategory(),
                                    new PhoneCategory(),
                                    new PhoneOfParentsCategory(),
                                    new PostalCityCategory(),
                                    new PostalPostCodeCategory(),
                                    new PostalStreetCategory(), };
    }
    return objects;
  }

  @Override
  protected IFilter<Participant> createEmptyFilter() {
    return new ParticipantFilter();
  }
}
