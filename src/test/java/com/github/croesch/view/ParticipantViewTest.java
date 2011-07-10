package com.github.croesch.view;

import org.junit.Test;

import com.github.croesch.model.api.IParticipantModel4View;
import com.github.croesch.types.exceptions.RequiredFieldSetToNullException;

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
    new ParticipantView(null);
  }

}