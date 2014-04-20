package com.github.croesch.partimana.view;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.PartiManaDefaultGUITestCase;
import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.model.api.IModel4View;
import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.types.Participant;
import java.util.Date;
import java.util.List;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.edt.GuiTask;
import org.assertj.swing.fixture.Containers;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.junit.Assert;
import org.junit.Test;

/**
 * Provides gui tests for {@link CampView}.
 *
 * @author croesch
 * @since Date: Sep 23, 2012
 */
public class CampViewGUITest extends PartiManaDefaultGUITestCase {

  private JPanelFixture testView;

  private Camp camp;

  private final IModel4View model = new IModel4View() {

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
  };

  private CampView cView;

  private boolean deleteActionPerformed;

  private boolean cancelActionPerformed;

  @Override
  protected void before() {
    Action.setObserver(new ActionObserver() {

      @Override
      public void performAction(final UserAction action) {
        if (action == UserAction.CREATE_CAMP) {
          cView.createCamp();
        } else if (action == UserAction.DELETE_CAMP) {
          deleteActionPerformed = true;
        } else if (action == UserAction.CANCEL_CAMP) {
          cancelActionPerformed = true;
        } else {
          Assert.fail();
        }
      }
    });

    cView = GuiActionRunner.execute(new GuiQuery<CampView>() {
      @Override
      protected CampView executeInEDT() throws Throwable {
        return new CampView(null, model);
      }
    });

    camp = new Camp("Freizeit", new Date(123456788), new Date(987654322), "Dort", "1€");

    GuiActionRunner.execute(new GuiTask() {

      @Override
      protected void executeInEDT() throws Throwable {
        cView.getCampEditView().setCamp(camp);
      }
    });
    cView.setName("view");

    final FrameFixture window = new FrameFixture(robot(), Containers.frameFor(cView));
    window.show();
    testView = window.panel("view");

    deleteActionPerformed = false;
    cancelActionPerformed = false;
  }

  @Test
  public void testCreateNewCamp_Button() {

    testView.button("newCamp").click();

    testView.textBox("nameTF").requireEmpty();
    testView.textBox("locationTF").requireEmpty();
    testView.textBox("fromTF").requireEmpty();
    testView.textBox("untilTF").requireEmpty();
    testView.textBox("ratePerParticipantTF").requireEmpty();
    testView.textBox("ratePerDayTF").requireEmpty();
    testView.label("idLbl").requireText((String) null);
    testView.table("campParticipants").requireRowCount(0);
  }

  @Test
  public void testCreateNewCamp_Method() {

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        cView.createCamp();
      }
    });

    testView.textBox("nameTF").requireEmpty();
    testView.textBox("locationTF").requireEmpty();
    testView.textBox("fromTF").requireEmpty();
    testView.textBox("untilTF").requireEmpty();
    testView.textBox("ratePerParticipantTF").requireEmpty();
    testView.textBox("ratePerDayTF").requireEmpty();
    testView.label("idLbl").requireText((String) null);
    testView.table("campParticipants").requireRowCount(0);
  }

  @Test
  public void testDeleteCamp() {
    assertThat(deleteActionPerformed).isFalse();
    testView.button("deleteCamp").click();

    assertThat(deleteActionPerformed).isTrue();
  }

  @Test
  public void testCancelCamp() {
    assertThat(cancelActionPerformed).isFalse();
    testView.button("cancelCamp").click();

    assertThat(cancelActionPerformed).isTrue();
  }
}
