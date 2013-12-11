package com.github.croesch.partimana.view.api;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.types.Camp;

/**
 * TODO Comment here ...
 *
 * @author croesch
 * @since Date: Jun 8, 2011
 */
public interface ICampView {
  /**
   * Returns the instance of {@link ICampEditView} that is responsible for editing the {@link
   * com.github.croesch.partimana.types.Camp}s.
   *
   * @return the {@link ICampEditView}
   * @since Date: Sep 23, 2012
   */
  @NotNull
  ICampEditView getCampEditView();

  /**
   * Returns the instance of {@link IListView} that is responsible for viewing the {@link
   * com.github.croesch.partimana.types.Camp}s.
   *
   * @return the {@link IListView}
   * @since Date: Sep 23, 2012
   */
  @NotNull
  IListView<Camp> getCampListView();

  /**
   * Performs creation of a new {@link com.github.croesch.partimana.types.Camp}. Means that the view will clear all
   * fields and give the user the possibility to fill the fields and store the information as a new camp. Won't have an
   * effect on the data.
   *
   * @since Date: Sep 23, 2012
   */
  void createCamp();
}
