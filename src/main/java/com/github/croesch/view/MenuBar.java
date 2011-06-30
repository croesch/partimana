package com.github.croesch.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.github.croesch.actions.ActionObserver;
import com.github.croesch.actions.UserAction;
import com.github.croesch.i18n.Text;

/**
 * Menu bar of the program.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011 6:26:35 AM
 */
class MenuBar extends JMenuBar {

  /** generated */
  private static final long serialVersionUID = -2565222931422220272L;

  /**
   * Constructs the menu bar for this program with the observer to notify about actions of the menu.
   * 
   * @author croesch
   * @since Date: Jun 30, 2011
   * @param observer the {@link ActionObserver} that handles the menu actions.
   */
  public MenuBar(final ActionObserver observer) {

    final JMenu file = new JMenu(Text.FILE.text());
    final JMenuItem exit = new JMenuItem(new Action(observer, UserAction.EXIT, Text.EXIT));

    file.add(exit);

    add(file);

  }

}
