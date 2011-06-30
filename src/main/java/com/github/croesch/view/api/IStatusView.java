package com.github.croesch.view.api;

import com.github.croesch.i18n.Text;

/**
 * Interface to visualise the state of the program.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011 6:19:09 AM
 */
public interface IStatusView {

  /**
   * Visualises the given information.
   * 
   * @author croesch
   * @since Date: Jun 30, 2011
   * @param info the information to visualise.
   */
  void showInformation(Text info);

  /**
   * Visualises the given error.
   * 
   * @author croesch
   * @since Date: Jun 30, 2011
   * @param error the error to visualise.
   */
  void showError(Text error);

}
