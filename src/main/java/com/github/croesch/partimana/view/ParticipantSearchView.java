package com.github.croesch.partimana.view;

import java.util.List;

import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.model.api.IFilter;
import com.github.croesch.partimana.model.filter.FilterModel;
import com.github.croesch.partimana.model.filter.ParticipantFilter;
import com.github.croesch.partimana.model.filter.cat.p.BankAccountNumberCategory;
import com.github.croesch.partimana.model.filter.cat.p.BankCodeNumberCategory;
import com.github.croesch.partimana.model.filter.cat.p.BankNameCategory;
import com.github.croesch.partimana.model.filter.cat.p.BirthdayCategory;
import com.github.croesch.partimana.model.filter.cat.p.CommentCategory;
import com.github.croesch.partimana.model.filter.cat.p.CountyCouncilCategory;
import com.github.croesch.partimana.model.filter.cat.p.DateSinceCategory;
import com.github.croesch.partimana.model.filter.cat.p.DateUntilCategory;
import com.github.croesch.partimana.model.filter.cat.p.DenominationCategory;
import com.github.croesch.partimana.model.filter.cat.p.FaxCategory;
import com.github.croesch.partimana.model.filter.cat.p.ForeNameCategory;
import com.github.croesch.partimana.model.filter.cat.p.GenderCategory;
import com.github.croesch.partimana.model.filter.cat.p.LastNameCategory;
import com.github.croesch.partimana.model.filter.cat.p.LivingCityCategory;
import com.github.croesch.partimana.model.filter.cat.p.LivingPostCodeCategory;
import com.github.croesch.partimana.model.filter.cat.p.LivingStreetCategory;
import com.github.croesch.partimana.model.filter.cat.p.MailAddressCategory;
import com.github.croesch.partimana.model.filter.cat.p.MobilePhoneCategory;
import com.github.croesch.partimana.model.filter.cat.p.PhoneCategory;
import com.github.croesch.partimana.model.filter.cat.p.PhoneOfParentsCategory;
import com.github.croesch.partimana.model.filter.cat.p.PostalCityCategory;
import com.github.croesch.partimana.model.filter.cat.p.PostalPostCodeCategory;
import com.github.croesch.partimana.model.filter.cat.p.PostalStreetCategory;
import com.github.croesch.partimana.types.Participant;

/**
 * The view that allows users to search the stored participants.
 * 
 * @author croesch
 * @since Date: Jan 26, 2013
 */
public final class ParticipantSearchView extends ASearchView<Participant> {

  /** generated serial version UID */
  private static final long serialVersionUID = -855139359052994514L;

  /** the view that visualizes the filter matching participants */
  private AListView<Participant> lView;

  /** contains the possible categories the participants can be searched for */
  private Object[] objects;

  /**
   * Constructs the participant search view that allows the user to search stored participants.
   * 
   * @since Date: Jan 26, 2013
   * @param name the name of this view
   * @param elements the elements that can be searched
   * @param o the observer that will be notified, when a participant selection has been made
   */
  public ParticipantSearchView(final String name, final List<Participant> elements, final ActionObserver o) {
    super(name, new FilterModel<Participant>(elements), o);
  }

  @Override
  protected AListView<Participant> getListView() {
    if (this.lView == null) {
      this.lView = new ParticipantListView("list", getObserver());
    }
    return this.lView;
  }

  @Override
  protected Object[] getPossibleCategories() {
    if (this.objects == null) {
      this.objects = new Object[] { new BankAccountNumberCategory(),
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
    return this.objects;
  }

  @Override
  protected IFilter<Participant> createEmptyFilter() {
    return new ParticipantFilter();
  }
}
