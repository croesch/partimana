package com.github.croesch.partimana.view;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.PartiManaDefaultGUITestCase;
import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.model.api.IModel4View;
import com.github.croesch.partimana.types.*;
import java.util.ArrayList;
import java.util.Arrays;
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
 * Provides gui tests for {@link ParticipantView}.
 *
 * @author croesch
 * @since Date: Jul 11, 2011
 */
public class ParticipantViewGUITest extends PartiManaDefaultGUITestCase {

  private JPanelFixture testView;

  private Participant participant;

  private final IModel4View model = new IModel4View() {

    @Override
    public Participant getParticipant(final long id) {
      return null;
    }

    @Override
    public List<Participant> getListOfParticipants() {
      return Arrays.asList(participant);
    }

    @Override
    public Camp getCamp(final long id) {
      return null;
    }

    @Override
    public List<Camp> getListOfCamps() {
      return new ArrayList<Camp>();
    }
  };

  private ParticipantView pView;

  private boolean deleteActionPerformed;

  @Override
  protected void before() {
    Action.setObserver(new ActionObserver() {

      @Override
      public void performAction(final UserAction action) {
        if (action == UserAction.CREATE_PARTICIPANT) {
          pView.createParticipant();
        } else if (action == UserAction.DELETE_PARTICIPANT) {
          deleteActionPerformed = true;
        } else {
          Assert.fail();
        }
      }
    });

    participant = new Participant("Mustermann",
                                  "Max",
                                  Gender.FEMALE,
                                  Denomination.OTHER,
                                  new Date(),
                                  "Musterstrasse 12",
                                  12345,
                                  "Musterhausen",
                                  CountyCouncil.OTHER);

    final Date d1 = new Date(123);

    participant.setBank("bank");
    participant.setBankAccountNumber(1);
    participant.setBankCodeNumber(12);
    participant.setComment("comment");
    participant.setDateSinceInDataBase(d1);
    participant.setDateUpToInSystem(d1);
    participant.setDenomination(Denomination.JEWISH);
    participant.setFax("fax");
    participant.setMailAddress("mail");
    participant.setMobilePhone("mobile");
    participant.setPhone("phone");
    participant.setPhoneOfParents("phone");
    participant.setCityPostal("city");
    participant.setPostCodePostal(3124);
    participant.setStreetPostal("street");

    pView = GuiActionRunner.execute(new GuiQuery<ParticipantView>() {
      @Override
      protected ParticipantView executeInEDT() throws Throwable {
        return new ParticipantView(null, model);
      }
    });

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        pView.getParticipantEditView().setParticipant(participant);
      }
    });

    pView.setName("view");

    final FrameFixture window = new FrameFixture(robot(), Containers.frameFor(pView));
    window.show();
    testView = window.panel("view");

    deleteActionPerformed = false;
  }

  @Test
  public void testView() {
    testView.textBox("firstNameTF").requireText(participant.getForeName());
    testView.textBox("lastNameTF").requireText(participant.getLastName());

    ParticipantListViewGUITest.requireParticipant(testView.table(), 0, participant);
  }

  @Test
  public void testCreateNewParticipant_Button() {

    testView.button("newParticipant").click();

    testView.textBox("firstNameTF").requireEmpty();
    testView.textBox("lastNameTF").requireEmpty();
    testView.comboBox("genderCB").requireNoSelection();
    testView.textBox("birthTF").requireEmpty();
    testView.comboBox("denominationCB").requireNoSelection();
    testView.textBox("postalStreetTF").requireEmpty();
    testView.textBox("postalPostCodeTF").requireEmpty();
    testView.textBox("postalCityTF").requireEmpty();
    testView.comboBox("countyCouncilCB").requireNoSelection();
    testView.textBox("bankTF").requireEmpty();
    testView.textBox("bankAccountNumberTF").requireEmpty();
    testView.textBox("bankCodeNumberTF").requireEmpty();
    testView.textBox("commentTF").requireEmpty();
    testView.textBox("dateUpToInDBTF").requireEmpty();
    testView.textBox("faxTF").requireEmpty();
    testView.textBox("mailTF").requireEmpty();
    testView.textBox("mobilePhoneTF").requireEmpty();
    testView.textBox("phoneTF").requireEmpty();
    testView.textBox("phoneOfParentsTF").requireEmpty();
    testView.textBox("livingStreetTF").requireEmpty();
    testView.textBox("livingPostCodeTF").requireEmpty();
    testView.textBox("livingCityTF").requireEmpty();
    final String n = null;
    testView.label("idLbl").requireText(n);
    testView.label("dateSinceInDBLbl").requireText(n);
  }

  @Test
  public void testCreateNewParticipant_Method() {

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        pView.createParticipant();
      }
    });

    testView.textBox("firstNameTF").requireEmpty();
    testView.textBox("lastNameTF").requireEmpty();
    testView.comboBox("genderCB").requireNoSelection();
    testView.textBox("birthTF").requireEmpty();
    testView.comboBox("denominationCB").requireNoSelection();
    testView.textBox("postalStreetTF").requireEmpty();
    testView.textBox("postalPostCodeTF").requireEmpty();
    testView.textBox("postalCityTF").requireEmpty();
    testView.comboBox("countyCouncilCB").requireNoSelection();
    testView.textBox("bankTF").requireEmpty();
    testView.textBox("bankAccountNumberTF").requireEmpty();
    testView.textBox("bankCodeNumberTF").requireEmpty();
    testView.textBox("commentTF").requireEmpty();
    testView.textBox("dateUpToInDBTF").requireEmpty();
    testView.textBox("faxTF").requireEmpty();
    testView.textBox("mailTF").requireEmpty();
    testView.textBox("mobilePhoneTF").requireEmpty();
    testView.textBox("phoneTF").requireEmpty();
    testView.textBox("phoneOfParentsTF").requireEmpty();
    testView.textBox("livingStreetTF").requireEmpty();
    testView.textBox("livingPostCodeTF").requireEmpty();
    testView.textBox("livingCityTF").requireEmpty();
    final String n = null;
    testView.label("idLbl").requireText(n);
    testView.label("dateSinceInDBLbl").requireText(n);
  }

  @Test
  public void testDeleteParticipant() {
    assertThat(deleteActionPerformed).isFalse();
    testView.button("deleteParticipant").click();

    assertThat(deleteActionPerformed).isTrue();
  }
}
