package com.github.croesch.partimana;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;

/**
 * Default gui test case for the partimana program.
 *
 * @author croesch
 * @since Date: Jul 11, 2011
 */
public abstract class PartiManaDefaultGUITestCase extends AssertJSwingJUnitTestCase implements ActionObserver {

  private final Queue<UserAction> actions = new ArrayDeque<UserAction>();

  protected static Queue<Integer> pIds = new ArrayDeque<Integer>(Arrays.asList(999, 9999));

  @Override
  protected final void onSetUp() {
    actions.clear();
    before();
  }

  protected abstract void before();

  @Override
  protected final void onTearDown() {
    assertNoActionPerformed();

    try {
      after();
    } finally {
      super.onTearDown();
    }
  }

  protected void after() {
  }

  @Override
  public final void performAction(final UserAction action) {
    actions.add(action);
  }

  protected UserAction poll() {
    return actions.poll();
  }

  protected void assertNoActionPerformed() {
    assertThat(actions).isEmpty();
  }
}
