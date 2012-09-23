package com.github.croesch.partimana.view;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.fixture.Containers;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JPanelFixture;
import org.junit.Assert;
import org.junit.Test;

import com.github.croesch.partimana.PartiManaDefaultGUITestCase;
import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.model.api.IModel4View;
import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.types.Participant;

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

  @Override
  protected void before() {
    Action.setObserver(new ActionObserver() {

      @Override
      public void performAction(final UserAction action) {
        if (action == UserAction.CREATE_CAMP) {
          CampViewGUITest.this.cView.createCamp();
        } else if (action == UserAction.DELETE_CAMP) {
          CampViewGUITest.this.deleteActionPerformed = true;
        } else {
          Assert.fail();
        }
      }
    });

    this.cView = GuiActionRunner.execute(new GuiQuery<CampView>() {
      @Override
      protected CampView executeInEDT() throws Throwable {
        return new CampView(CampViewGUITest.this.model);
      }
    });

    this.camp = new Camp("Freizeit", new Date(123456788), new Date(987654322), "Dort", "1€");

    GuiActionRunner.execute(new GuiTask() {

      @Override
      protected void executeInEDT() throws Throwable {
        CampViewGUITest.this.cView.getCampEditView().setCamp(CampViewGUITest.this.camp);
      }
    });
    this.cView.setName("view");

    final FrameFixture window = new FrameFixture(robot(), Containers.frameFor(this.cView));
    window.show();
    this.testView = window.panel("view");

    this.deleteActionPerformed = false;
  }

  @Test
  public void testCreateNewCamp_Button() {

    this.testView.button("newCamp").click();

    this.testView.textBox("nameTF").requireEmpty();
    this.testView.textBox("locationTF").requireEmpty();
    this.testView.textBox("fromTF").requireEmpty();
    this.testView.textBox("untilTF").requireEmpty();
    this.testView.textBox("ratePerParticipantTF").requireEmpty();
    this.testView.textBox("ratePerDayTF").requireEmpty();
    this.testView.label("idLbl").requireText((String) null);
    this.testView.table("campParticipants").requireRowCount(0);
  }

  @Test
  public void testCreateNewCamp_Method() {

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        CampViewGUITest.this.cView.createCamp();
      }
    });

    this.testView.textBox("nameTF").requireEmpty();
    this.testView.textBox("locationTF").requireEmpty();
    this.testView.textBox("fromTF").requireEmpty();
    this.testView.textBox("untilTF").requireEmpty();
    this.testView.textBox("ratePerParticipantTF").requireEmpty();
    this.testView.textBox("ratePerDayTF").requireEmpty();
    this.testView.label("idLbl").requireText((String) null);
    this.testView.table("campParticipants").requireRowCount(0);
  }

  @Test
  public void testDeleteCamp() {
    assertThat(this.deleteActionPerformed).isFalse();
    this.testView.button("deleteCamp").click();

    assertThat(this.deleteActionPerformed).isTrue();
  }
}
