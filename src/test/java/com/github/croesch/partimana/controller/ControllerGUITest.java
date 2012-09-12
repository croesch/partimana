package com.github.croesch.partimana.controller;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.Queue;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.finder.WindowFinder;
import org.fest.swing.fixture.FrameFixture;
import org.junit.Test;

import com.github.croesch.partimana.PartiManaDefaultGUITestCase;
import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.view.View;

/**
 * Provides test cases for {@link Controller}.
 * 
 * @author croesch
 * @since Date: Sep 12, 2012
 */
public class ControllerGUITest extends PartiManaDefaultGUITestCase implements ActionObserver {

  private Controller controller;

  private final Queue<UserAction> actions = new ArrayDeque<UserAction>();

  @Override
  protected void before() {
    this.controller = GuiActionRunner.execute(new GuiQuery<Controller>() {
      @Override
      protected Controller executeInEDT() throws Throwable {
        return new Controller(ControllerGUITest.this, null);
      }
    });
    this.actions.clear();
  }

  @Test
  public void testView() {
    final FrameFixture frame = WindowFinder.findFrame(View.class).using(robot());
    frame.label("statusTxt").requireText(Text.VERSION.text());
  }

  @Test
  public void testSaveParticipant() {
    final FrameFixture frame = WindowFinder.findFrame(View.class).using(robot());

    performActionInEDT(UserAction.SAVE_PARTICIPANT);

    frame.label("statusTxt").requireText(Text.ERROR_PARTICIPANT_NOT_SAVED.text());
    frame.close();
  }

  @Test
  public void testDeleteParticipant() {
    final FrameFixture frame = WindowFinder.findFrame(View.class).using(robot());

    performActionInEDT(UserAction.DELETE_PARTICIPANT);
    // TODO ...

    frame.close();
  }

  private void performActionInEDT(final UserAction action) {
    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        ControllerGUITest.this.controller.performAction(action);
      }
    });
  }

  @Test
  public void testClose() {
    final FrameFixture frame = WindowFinder.findFrame(View.class).using(robot());
    frame.close();

    assertThat(this.actions.poll()).isEqualTo(UserAction.EXIT);
    assertThat(this.actions).isEmpty();
  }

  @Override
  public void performAction(final UserAction action) {
    this.actions.add(action);
  }
}
