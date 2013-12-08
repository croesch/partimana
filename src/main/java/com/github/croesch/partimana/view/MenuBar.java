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
   * @param name the name of the menu bar
   * @since Date: Jun 30, 2011
   */
  public MenuBar(final String name) {
    super(name);

    final CMenu file = new CMenu("file", Text.FILE.text());
    final CMenuItem camp2CSV = new CMenuItem("camp2csv", Action.getSaveCampToCSVAction());
    final CMenuItem exit = new CMenuItem("exit", Action.getExitAction());

    file.add(camp2CSV);
    file.addSeparator();
    file.add(exit);
    add(file);

    final CMenu search = new CMenu("search", Text.SEARCH.text());
    final CMenuItem camp = new CMenuItem("searchCamp", Action.getSearchCampAction());
    final CMenuItem participant = new CMenuItem("searchParticipant", Action.getSearchParticipantAction());

    search.add(camp);
    search.add(participant);
    add(search);
  }
}
