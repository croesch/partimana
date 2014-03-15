package com.github.croesch.partimana.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.PartiManaDefaultGUITestCase;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.helper.HashMapPersistenceModel;
import com.github.croesch.partimana.view.View;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.edt.GuiTask;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.Test;

/**
 * Provides test cases for {@link Controller}.
 *
 * @author croesch
 * @since Date: Sep 12, 2012
 */
public class ControllerGUITest extends PartiManaDefaultGUITestCase {

  private Controller controller;

  @Override
  protected void before() {
    this.controller = GuiActionRunner.execute(new GuiQuery<Controller>() {
      @Override
      protected Controller executeInEDT() throws Throwable {
        return new Controller(ControllerGUITest.this, new HashMapPersistenceModel());
      }
    });
  }

  @Test
  public void testView() {
    final FrameFixture frame = WindowFinder.findFrame(View.class).using(robot());
    frame.label("statusTxt").requireText(Text.VERSION.text());
    assertThat(frame.target().getName()).isEqualTo("view");
  }

  @Test
  public void testSaveCamp() {
    final FrameFixture frame = WindowFinder.findFrame(View.class).using(robot());

    performActionInEDT(UserAction.SAVE_CAMP);

    frame.label("statusTxt").requireText(Text.ERROR_CAMP_NOT_SAVED.text());
    frame.close();
    assertThat(poll()).isEqualTo(UserAction.EXIT);
  }

  @Test
  public void testDeleteCamp() {
    final FrameFixture frame = WindowFinder.findFrame(View.class).using(robot());

    performActionInEDT(UserAction.DELETE_CAMP);
    // TODO ...

    frame.close();
    assertThat(poll()).isEqualTo(UserAction.EXIT);
  }

  @Test
  public void testSaveParticipant() {
    final FrameFixture frame = WindowFinder.findFrame(View.class).using(robot());

    performActionInEDT(UserAction.SAVE_PARTICIPANT);

    frame.label("statusTxt").requireText(Text.ERROR_PARTICIPANT_NOT_SAVED.text());
    frame.close();
    assertThat(poll()).isEqualTo(UserAction.EXIT);
  }

  @Test
  public void testDeleteParticipant() {
    final FrameFixture frame = WindowFinder.findFrame(View.class).using(robot());

    performActionInEDT(UserAction.DELETE_PARTICIPANT);
    // TODO ...

    frame.close();
    assertThat(poll()).isEqualTo(UserAction.EXIT);
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

    assertThat(poll()).isEqualTo(UserAction.EXIT);
  }
}
