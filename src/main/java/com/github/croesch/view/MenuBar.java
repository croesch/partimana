package com.github.croesch.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
   */
  public MenuBar() {

    final JMenu file = new JMenu(Text.FILE.text());
    final JMenuItem exit = new JMenuItem(Action.getExitAction());

    file.add(exit);

    add(file);

  }

}
