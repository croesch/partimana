package com.github.croesch;

import org.apache.log4j.Logger;

import com.github.croesch.actions.ActionObserver;
import com.github.croesch.actions.UserAction;
import com.github.croesch.controller.CoreController;
import com.github.croesch.i18n.Text;

/**
 * partimana - a program to manage camps and their participants. <br>
 * <br>
 * Copyright (C) 2011 Christian Roesch <br>
 * <br>
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version. <br>
 * <br>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 * 
 * @author croesch
 * @since Date: May 29, 2011 12:09:44 PM
 */
public final class PartiMana implements ActionObserver {

  /** logging class */
  private final Logger log = Logger.getLogger(PartiMana.class);

  /** indicator if the program should exit or is still running */
  private boolean running = true;

  /**
   * Starts the manager.
   * 
   * @author croesch
   * @since Date: May 29, 2011 12:09:44 PM
   * @param args possible command line arguments
   */
  public static void main(final String[] args) {
    new PartiMana(args);
  }

  /**
   * PartiMana main program.
   * 
   * @author croesch
   * @since Date: May 29, 2011 3:41:57 PM
   * @param args arguments that came from command line
   */
  public PartiMana(final String[] args) {
    this.log.debug(Text.DEBUG_PROGRAM_STARTING.text());
    this.log.debug(Text.DEBUG_SELECTED_LANGUAGE.text(Text.LANGUAGE));

    final Runnable program = new CoreController(this, args);
    program.run();

    while (this.running) {
      try {
        Thread.sleep(1000);
      } catch (final InterruptedException e) {
        this.log.warn(e.getMessage(), e);
      }
    }
    this.log.debug(Text.DEBUG_PROGRAM_EXITING.text());
  }

  @Override
  public void performAction(final UserAction action) {
    if (action == UserAction.EXIT) {
      this.log.debug(Text.DEBUG_PROGRAM_EXIT_NOTIFICATION.text());
      this.running = false;
    }
  }

}
