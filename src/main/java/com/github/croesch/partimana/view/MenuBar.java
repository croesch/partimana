package com.github.croesch.partimana.view;

import com.github.croesch.components.CMenu;
import com.github.croesch.components.CMenuBar;
import com.github.croesch.components.CMenuItem;
import com.github.croesch.partimana.i18n.Text;

/**
 * Menu bar of the program.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011
 */
class MenuBar extends CMenuBar {

  /** generated */
  private static final long serialVersionUID = -2565222931422220272L;

  /**
   * Constructs the menu bar for this program with the observer to notify about actions of the menu.
   * 
   * @author croesch
   * @since Date: Jun 30, 2011
   * @param name the name of the menu bar
   */
  public MenuBar(final String name) {
    super(name);

    final CMenu file = new CMenu("file", Text.FILE.text());
    final CMenuItem exit = new CMenuItem("exit", Action.getExitAction());

    file.add(exit);

    add(file);

  }

}
