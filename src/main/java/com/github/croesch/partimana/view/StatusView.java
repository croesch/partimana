package com.github.croesch.partimana.view;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.components.CLabel;
import com.github.croesch.components.CPanel;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.view.api.IStatusView;
import java.awt.Color;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

/**
 * Component to visualise the state of the program.
 *
 * @author croesch
 * @since Date: Jun 8, 2011
 */
class StatusView extends CPanel implements IStatusView {

  /** generated */
  private static final long serialVersionUID = 5893311874886856920L;

  /** the label that will contain the messages */
  @NotNull
  private final CLabel label;

  /**
   * Constructs a new status view to visualise the state of the program.
   *
   * @param name the name of this component
   * @since Date: Jun 30, 2011
   */
  public StatusView(final String name) {
    super(name);
    setLayout(new MigLayout(new LC().fill()));

    label = new CLabel("statusTxt");

    add(label);
  }

  @Override
  public void showInformation(final Text info) {
    showInfo(info.text()); // FIXME null check?
  }

  @Override
  public void showError(final Text error) {
    label.setForeground(Color.RED);
    label.setText(error.text()); // FIXME null check?
  }

  @Override
  public void showInformation(final Text info, final Object... args) {
    showInfo(info.text(args)); //FIXME null check?
  }

  /**
   * Sets the color to black and visualises the given text.
   *
   * @param txt the text to display.
   * @since Date: Jul 1, 2011
   */
  private void showInfo(final String txt) {
    label.setForeground(Color.BLACK);
    label.setText(txt);
  }
}
