package com.github.croesch.partimana.view.api;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.i18n.Text;

/**
 * Interface to visualise the state of the program.
 *
 * @author croesch
 * @since Date: Jun 8, 2011
 */
public interface IStatusView {

  /**
   * Visualises the given information.
   *
   * @param info the information to visualise.
   * @since Date: Jun 30, 2011
   */
  void showInformation(@NotNull Text info);

  /**
   * Visualises the given information.
   *
   * @param info the information to visualise.
   * @param args the arguments to put into the information.
   * @since Date: Jul 1, 2011
   */
  void showInformation(@NotNull Text info, @NotNull Object... args);

  /**
   * Visualises the given error.
   *
   * @param error the error to visualise.
   * @since Date: Jun 30, 2011
   */
  void showError(@NotNull Text error);
}
