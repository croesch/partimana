package com.github.croesch.partimana.view;

import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiTask;
import org.junit.Test;

/**
 * Provides test methods for {@link ParticipantView}.
 *
 * @author croesch
 * @since Date: Jul 11, 2011
 */
public class ParticipantViewTest {

  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testParticipantView() {
    GuiActionRunner.execute(new GuiTask() {

      @Override
      protected void executeInEDT() throws Throwable {
        new ParticipantView(null, null);
      }
    });
  }
}
