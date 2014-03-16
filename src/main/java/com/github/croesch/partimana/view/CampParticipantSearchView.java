package com.github.croesch.partimana.view;

import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.model.api.IFilter;
import com.github.croesch.partimana.model.filter.CampParticipantFilter;
import com.github.croesch.partimana.model.filter.FilterModel;
import com.github.croesch.partimana.types.CampParticipant;
import java.util.List;

/**
 * The view that allows users to search a list of camp participants.
 *
 * @author croesch
 * @since Date: Mar 16, 2014
 */
public final class CampParticipantSearchView extends ASearchView<CampParticipant> {

  /** generated serial version UID */
  private static final long serialVersionUID = -855139359052994514L;

  /** the view that visualizes the filter matching camp participants */
  private AListView<CampParticipant> lView;

  /** contains the possible categories the camp participants can be searched for */
  private Object[] objects;

  /**
   * Constructs the camp participant search view that allows the user to search the camp participants.
   *
   * @param name     the name of this view
   * @param elements the elements that can be searched
   * @param o        the observer that will be notified, when a camp participant selection has been made
   * @since Date: Mar 16, 2014
   */
  public CampParticipantSearchView(final String name, final List<CampParticipant> elements, final ActionObserver o) {
    super(name, new FilterModel<CampParticipant>(elements), o);
  }

  @Override
  protected AListView<CampParticipant> getListView() {
    if (this.lView == null) {
      this.lView = new CampParticipantListView("list", getObserver());
    }
    return this.lView;
  }

  @Override
  protected Object[] getPossibleCategories() {
    if (this.objects == null) {
      objects = createEmptyFilter().getCategories().toArray();
    }
    return this.objects;
  }

  @Override
  protected IFilter<CampParticipant> createEmptyFilter() {
    return new CampParticipantFilter();
  }
}
