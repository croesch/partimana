package com.github.croesch.partimana.view;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiTask;
import org.junit.Test;

import com.github.croesch.partimana.model.api.IParticipantModel4View;
import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;
import com.github.croesch.partimana.view.ParticipantView;

/**
 * Provides test methods for {@link ParticipantView}.
 * 
 * @author croesch
 * @since Date: Jul 11, 2011
 */
public class ParticipantViewTest {

  /**
   * Test method for {@link ParticipantView#ParticipantView(IParticipantModel4View)}.
   */
  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testParticipantView() {
    GuiActionRunner.execute(new GuiTask() {

      @Override
      protected void executeInEDT() throws Throwable {
        new ParticipantView(null);
      }
    });
  }

}
