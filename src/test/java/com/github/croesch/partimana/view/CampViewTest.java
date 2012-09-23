package com.github.croesch.partimana.view;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiTask;
import org.junit.Test;

import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;

/**
 * Provides test methods for {@link CampView}.
 * 
 * @author croesch
 * @since Date: Sep 23, 2012
 */
public class CampViewTest {

  @Test(expected = RequiredFieldSetToNullException.class)
  public final void testCampView() {
    GuiActionRunner.execute(new GuiTask() {

      @Override
      protected void executeInEDT() throws Throwable {
        new CampView(null);
      }
    });
  }

}
