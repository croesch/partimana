package com.github.croesch.view;

import java.awt.Color;

import javax.swing.JFrame;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.fixture.JLabelFixture;
import org.fest.swing.fixture.JPanelFixture;
import org.junit.Test;

import com.github.croesch.PartiManaDefaultGUITestCase;
import com.github.croesch.i18n.Text;

/**
 * Provides GUI tests for {@link StatusView}.
 * 
 * @author croesch
 * @since Date: Sep 12, 2012
 */
public class StatusViewGUITest extends PartiManaDefaultGUITestCase {

  private StatusView statusView;

  @Override
  protected void before() {
    this.statusView = GuiActionRunner.execute(new GuiQuery<StatusView>() {
      @Override
      protected StatusView executeInEDT() throws Throwable {
        return new StatusView();
      }
    });
    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        final JFrame f = new JFrame();
        f.add(StatusViewGUITest.this.statusView);
        f.setVisible(true);
      }
    });
  }

  @Test
  public void testShowInformation() {
    final JLabelFixture statusFixture = new JPanelFixture(robot(), this.statusView).label();

    showInformation(Text.APPLY);
    statusFixture.foreground().requireEqualTo(Color.BLACK);
    statusFixture.requireText(Text.APPLY.text());

    showInformation(Text.CAMP);
    statusFixture.foreground().requireEqualTo(Color.BLACK);
    statusFixture.requireText(Text.CAMP.text());

    showInformation(Text.WARN_UNKNOWN_ACTION, "aktion");
    statusFixture.foreground().requireEqualTo(Color.BLACK);
    statusFixture.requireText(Text.WARN_UNKNOWN_ACTION.text("aktion"));

    showError(Text.CANCEL);
    statusFixture.foreground().requireEqualTo(Color.RED);
    statusFixture.requireText(Text.CANCEL.text());

    showInformation(Text.CATHOLIC);
    statusFixture.foreground().requireEqualTo(Color.BLACK);
    statusFixture.requireText(Text.CATHOLIC.text());
  }

  private void showError(final Text txt) {
    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        StatusViewGUITest.this.statusView.showError(txt);
      }
    });
  }

  private void showInformation(final Text txt) {
    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        StatusViewGUITest.this.statusView.showInformation(txt);
      }
    });
  }

  private void showInformation(final Text txt, final Object ... args) {
    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        StatusViewGUITest.this.statusView.showInformation(txt, args);
      }
    });
  }
}
