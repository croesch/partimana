package com.github.croesch.partimana.model.filter;

import java.util.ArrayList;
import java.util.List;

import com.github.croesch.partimana.model.api.IFilterCategory;
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
