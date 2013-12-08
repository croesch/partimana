package com.github.croesch.partimana.model.filter;

import com.github.croesch.partimana.model.api.IFilterCategory;
import com.github.croesch.partimana.model.filter.cat.p.*;
import com.github.croesch.partimana.types.Participant;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a filter for {@link Participant}-objects. Can have different {@link IFilterCategory}s to filter the
 * participants by different columns.
 *
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public class ParticipantFilter extends AFilter<Participant> {

  @Override
  public final List<IFilterCategory<Participant, ?>> getCategories() {
    final List<IFilterCategory<Participant, ?>> categories = new ArrayList<IFilterCategory<Participant, ?>>();
    categories.add(new ForeNameCategory());
    categories.add(new LastNameCategory());
    categories.add(new BankCodeNumberCategory());
    categories.add(new BankAccountNumberCategory());
    categories.add(new BankNameCategory());
    categories.add(new BirthdayCategory());
    categories.add(new CommentCategory());
    categories.add(new CountyCouncilCategory());
    categories.add(new DateSinceCategory());
    categories.add(new DateUntilCategory());
    categories.add(new DenominationCategory());
    categories.add(new FaxCategory());
    categories.add(new GenderCategory());
    categories.add(new LivingCityCategory());
    categories.add(new LivingStreetCategory());
    categories.add(new LivingPostCodeCategory());
    categories.add(new MailAddressCategory());
    categories.add(new MobilePhoneCategory());
    categories.add(new PhoneCategory());
    categories.add(new PhoneOfParentsCategory());
    categories.add(new PostalCityCategory());
    categories.add(new PostalStreetCategory());
    categories.add(new PostalPostCodeCategory());
    return categories;
  }
}
