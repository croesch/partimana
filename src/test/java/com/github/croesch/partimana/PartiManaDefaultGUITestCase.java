package com.github.croesch.partimana;

import org.fest.swing.junit.testcase.FestSwingJUnitTestCase;

/**
 * Default gui test case for the partimana program.
 * 
 * @author croesch
 * @since Date: Jul 11, 2011
 */
public abstract class PartiManaDefaultGUITestCase extends FestSwingJUnitTestCase {

  @Override
  protected final void onSetUp() {
    before();
  }

  protected abstract void before();

  @Override
  protected final void onTearDown() {
    try {
      after();
    } finally {
      super.onTearDown();
    }
  }

  protected void after() {};

}