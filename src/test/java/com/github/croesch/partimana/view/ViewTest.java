package com.github.croesch.partimana.view;

import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.model.api.IModel4View;
import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.types.Participant;
import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;
import java.util.List;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiTask;
import org.junit.Test;

/**
 * Provides test methods for {@link View}.
 *
 * @author croesch
 * @since Date: Jul 11, 2011
 */
public class ViewTest {

  /**
   * Test method for {@link View#View(IModel4View, ActionObserver)}.
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testView_ModelIsNull() {
    GuiActionRunner.execute(new GuiTask() {

      @Override
      protected void executeInEDT() throws Throwable {
        new View(null, null, new ActionObserver() {
          @Override
          public void performAction(final UserAction action) {
          }
        });
      }
    });
  }

  /**
   * Test method for {@link View#View(IModel4View, ActionObserver)}.
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testView_ObserverIsNull() {
    GuiActionRunner.execute(new GuiTask() {

      @Override
      protected void executeInEDT() throws Throwable {
        new View(null, new IModel4View() {

          @Override
          public Participant getParticipant(final long id) {
            return null;
          }

          @Override
          public List<Participant> getListOfParticipants() {
            return null;
          }

          @Override
          public Camp getCamp(final long id) {
            return null;
          }

          @Override
          public List<Camp> getListOfCamps() {
            return null;
          }
        }, null);
      }
    });
  }
}
