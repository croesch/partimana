package com.github.croesch.view;

import java.util.List;

import org.junit.Test;

import com.github.croesch.actions.ActionObserver;
import com.github.croesch.actions.UserAction;
import com.github.croesch.model.api.IModel4View;
import com.github.croesch.types.Participant;
import com.github.croesch.types.exceptions.RequiredFieldSetToNullException;

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
    new View(null, new ActionObserver() {
      @Override
      public void performAction(final UserAction action) {}
    });
  }

  /**
   * Test method for {@link View#View(IModel4View, ActionObserver)}.
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testView_ObserverIsNull() {
    new View(new IModel4View() {

      @Override
      public Participant getParticipant(final long id) {
        return null;
      }

      @Override
      public List<Participant> getListOfParticipants() {
        return null;
      }
    }, null);
  }

}
