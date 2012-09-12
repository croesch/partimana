package com.github.croesch.partimana.view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.view.api.IStatusView;

/**
 * Component to visualise the state of the program.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011
 */
class StatusView extends JPanel implements IStatusView {

  /** generated */
  private static final long serialVersionUID = 5893311874886856920L;

  /** the label that will contain the messages */
  private final JLabel label;

  /**
   * Constructs a new status view to visualise the state of the program.
   * 
   * @author croesch
   * @since Date: Jun 30, 2011
   */
  public StatusView() {
    setLayout(new MigLayout(new LC().fill()));

    this.label = new JLabel();

    add(this.label);
  }

  @Override
  public void showInformation(final Text info) {
    showInfo(info.text());
  }

  @Override
  public void showError(final Text error) {
    this.label.setForeground(Color.RED);
    this.label.setText(error.text());
  }

  @Override
  public void showInformation(final Text info, final Object ... args) {
    showInfo(info.text(args));
  }

  /**
   * Sets the color to black and visualises the given text.
   * 
   * @author croesch
   * @since Date: Jul 1, 2011
   * @param txt the text to display.
   */
  private void showInfo(final String txt) {
    this.label.setForeground(Color.BLACK);
    this.label.setText(txt);
  }
}
