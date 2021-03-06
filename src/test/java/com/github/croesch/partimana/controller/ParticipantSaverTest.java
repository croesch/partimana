package com.github.croesch.partimana.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IModel4View;
import com.github.croesch.partimana.model.api.IParticipantModel;
import com.github.croesch.partimana.types.*;
import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;
import com.github.croesch.partimana.view.View;
import com.github.croesch.partimana.view.api.IParticipantEditView;
import com.github.croesch.partimana.view.api.IStatusView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.edt.GuiTask;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Provides test methods for {@link ParticipantSaver}
 *
 * @author croesch
 * @since Date: Jul 9, 2011
 */
public class ParticipantSaverTest {

  private Participant stored;

  private static IParticipantEditView editView;

  private static IStatusView statusView;

  private IParticipantModel model;

  private Participant dummyParticipant;

  /**
   * Sets up the editView
   *
   * @since Date: Jul 9, 2011
   */
  @BeforeClass
  public static void setUpOnce() {

    editView = GuiActionRunner.execute(new GuiQuery<IParticipantEditView>() {
      @Override
      protected IParticipantEditView executeInEDT() throws Throwable {
        return new View(null, new IModel4View() {

          @Override
          public Participant getParticipant(final long id) {
            return null;
          }

          @Override
          public List<Participant> getListOfParticipants() {
            return new ArrayList<Participant>();
          }

          @Override
          public Camp getCamp(final long id) {
            return null;
          }

          @Override
          public List<Camp> getListOfCamps() {
            return new ArrayList<Camp>();
          }
        }, new ActionObserver() {

          @Override
          public void performAction(final UserAction action) {
            // do nothing
          }
        }
        ).getParticipantEditView();
      }
    });
    statusView = new IStatusView() {

      @Override
      public void showInformation(final Text info, final Object... args) {
      }

      @Override
      public void showInformation(final Text info) {
      }

      @Override
      public void showError(final Text error) {
      }
    };
  }

  /**
   * Sets up the model, the editView and the statusView
   *
   * @since Date: Jul 9, 2011
   */
  @Before
  public void setUp() {
    dummyParticipant = new Participant("",
                                       "",
                                       Gender.FEMALE,
                                       Denomination.ORTHODOX,
                                       new Date(82800000),
                                       "",
                                       0,
                                       "",
                                       CountyCouncil.COUNTY_KUSEL);

    model = new IParticipantModel() {

      @Override
      public Participant getParticipant(final long id) {
        return dummyParticipant;
      }

      @Override
      public List<Participant> getListOfParticipants() {
        return null;
      }

      @Override
      public Participant store(final Participant p) throws RequiredFieldSetToNullException {
        stored = p;
        return p;
      }

      @Override
      public void deleteParticipant(final long id) throws RequiredFieldSetToNullException {
        // do nothing
      }
    };

    stored = null;
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_ForeName() {
    final Participant p = new Participant(dummyParticipant);
    p.setForeName("fore-name");

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_LastName() {
    final Participant p = new Participant(dummyParticipant);
    p.setLastName("last-name");

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_Gender() {
    final Participant p = new Participant(dummyParticipant);
    p.setGender(Gender.FEMALE);

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_Denomination() {
    final Participant p = new Participant(dummyParticipant);
    p.setDenomination(Denomination.ORTHODOX);

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_BirthDate() {
    final Participant p = new Participant(dummyParticipant);
    p.setBirthDate(new Date(12000000000l));

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_Street() {
    final Participant p = new Participant(dummyParticipant);
    p.setStreet("street");

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_PostCode() {
    final Participant p = new Participant(dummyParticipant);
    p.setPostCode(12554);

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_City() {
    final Participant p = new Participant(dummyParticipant);
    p.setCity("city");

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_CountyCouncil() {
    final Participant p = new Participant(dummyParticipant);
    p.setCountyCouncil(CountyCouncil.COUNTY_KUSEL);

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_Bank() {
    final Participant p = new Participant(dummyParticipant);
    p.setBank("super bank");

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_BankAccountNumber() {
    final Participant p = new Participant(dummyParticipant);
    p.setBankAccountNumber(229678302);

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_BankCodeNumber() {
    final Participant p = new Participant(dummyParticipant);
    p.setBankCodeNumber(229678303);

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_Comment() {
    final Participant p = new Participant(dummyParticipant);
    p.setComment("Comment for this one..");

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_DateUpToInDataBase() {
    final Participant p = new Participant(dummyParticipant);
    p.setDateUpToInSystem(new Date());

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_Fax() {
    final Participant p = new Participant(dummyParticipant);
    p.setFax("fax-number");

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_Mail() {
    final Participant p = new Participant(dummyParticipant);
    p.setMailAddress("address@prov.com");

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_MobilePhone() {
    final Participant p = new Participant(dummyParticipant);
    p.setMobilePhone("mobile phone number");

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_Phone() {
    final Participant p = new Participant(dummyParticipant);
    p.setPhone("the phone number");

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_PhoneOfParents() {
    final Participant p = new Participant(dummyParticipant);
    p.setPhoneOfParents("the phone number of the parents");

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_PostalStreet() {
    final Participant p = new Participant(dummyParticipant);
    p.setStreetPostal("street for post");

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_PostalCity() {
    final Participant p = new Participant(dummyParticipant);
    p.setCityPostal("city for post");

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_PostalPostCode() {
    final Participant p = new Participant(dummyParticipant);
    p.setPostCodePostal(84472);

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        editView.setParticipant(p);
        ParticipantSaver.performSave(model, editView, statusView);
      }
    });

    assertThat(stored).isEqualTo(p);
  }
}
