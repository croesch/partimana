package com.github.croesch.partimana;

import java.util.Locale;

import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.controller.Controller;
import com.github.croesch.partimana.i18n.Text;

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
 * @since Date: May 29, 2011
 */
public final class PartiMana implements ActionObserver {

  /** the time to sleep until the next check will happen if the program should exit */
  private static final int HEARTBEAT_INTERVALL = 1000;

  /** logging class */
  private static final Logger LOGGER = Logger.getLogger(PartiMana.class);

  /** indicator if the program should exit or is still running */
  private boolean running = true;

  /**
   * Starts the manager.
   * 
   * @author croesch
   * @since Date: May 29, 2011
   * @param args possible command line arguments
   */
  public static void main(final String[] args) {
    Locale.setDefault(Locale.GERMAN);

    // setting up the laf of the current system
    try {
      final String laf = UIManager.getSystemLookAndFeelClassName();
      LOGGER.debug(Text.DEBUG_SELECTED_LAF.text(laf));
      UIManager.setLookAndFeel(laf);
    } catch (final Exception e) {
      LOGGER.error(Text.ERROR_EXCEPTION.text(e.getClass().getName()), e);
    }

    new PartiMana(args);
  }

  /**
   * PartiMana main program.
   * 
   * @author croesch
   * @since Date: May 29, 2011
   * @param args arguments that came from command line
   */
  public PartiMana(final String[] args) {
    LOGGER.debug(Text.DEBUG_PROGRAM_STARTING.text());
    LOGGER.debug(Text.DEBUG_SELECTED_LANGUAGE.text(Text.LANGUAGE));

    // start 'real' program
    new Controller(this, args);

    while (this.running) {
      try {
        Thread.sleep(HEARTBEAT_INTERVALL);
      } catch (final InterruptedException e) {
        LOGGER.warn(Text.ERROR_EXCEPTION.text(e.getClass().getName()), e);
      }
    }
    LOGGER.debug(Text.DEBUG_PROGRAM_EXITING.text());
  }

  @Override
  public void performAction(final UserAction action) {
    if (action == UserAction.EXIT) {
      LOGGER.debug(Text.DEBUG_PROGRAM_EXIT_NOTIFICATION.text());
      this.running = false;
    } else {
      LOGGER.warn(Text.WARN_UNKNOWN_ACTION.text(action));
    }
  }

}
