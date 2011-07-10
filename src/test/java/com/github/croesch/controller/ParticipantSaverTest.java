package com.github.croesch.controller;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.croesch.actions.ActionObserver;
import com.github.croesch.actions.UserAction;
import com.github.croesch.i18n.Text;
import com.github.croesch.model.api.IModel4View;
import com.github.croesch.model.api.IParticipantModel;
import com.github.croesch.types.CountyCouncil;
import com.github.croesch.types.Denomination;
import com.github.croesch.types.Gender;
import com.github.croesch.types.Participant;
import com.github.croesch.types.exceptions.RequiredFieldSetToNullException;
import com.github.croesch.view.View;
import com.github.croesch.view.api.IParticipantEditView;
import com.github.croesch.view.api.IStatusView;

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
    editView = new View(new IModel4View() {

      @Override
      public Participant getParticipant(final long id) {
        return null;
      }

      @Override
      public List<Participant> getListOfParticipants() {
        return null;
      }
    }, new ActionObserver() {

      @Override
      public void performAction(final UserAction action) {
        // do nothing
      }
    }).getParticipantEditView();

    statusView = new IStatusView() {

      @Override
      public void showInformation(final Text info, final Object ... args) {}

      @Override
      public void showInformation(final Text info) {}

      @Override
      public void showError(final Text error) {}
    };

  }

  /**
   * Sets up the model, the editView and the statusView
   * 
   * @since Date: Jul 9, 2011
   */
  @Before
  public void setUp() {
    this.dummyParticipant = new Participant("",
                                            "",
                                            Gender.FEMALE,
                                            Denomination.ORTHODOX,
                                            new Date(82800000),
                                            "",
                                            0,
                                            "",
                                            CountyCouncil.COUNTY_KUSEL);

    this.model = new IParticipantModel() {

      @Override
      public Participant getParticipant(final long id) {
        return ParticipantSaverTest.this.dummyParticipant;
      }

      @Override
      public List<Participant> getListOfParticipants() {
        return null;
      }

      @Override
      public void store(final Participant p) throws RequiredFieldSetToNullException {
        ParticipantSaverTest.this.stored = p;
      }

      @Override
      public void delete(final Participant p) throws RequiredFieldSetToNullException {
        // do nothing
      }
    };

    this.stored = null;
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_ForeName() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setForeName("fore-name");

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_LastName() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setLastName("last-name");

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_Gender() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setGender(Gender.FEMALE);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_Denomination() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setDenomination(Denomination.ORTHODOX);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_BirthDate() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setBirthDate(new Date(12000000000l));

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_Street() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setStreet("street");

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_PostCode() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setPostCode(12554);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_City() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setCity("city");

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_CountyCouncil() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setCountyCouncil(CountyCouncil.COUNTY_KUSEL);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_Bank() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setBank("super bank");

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_BankAccountNumber() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setBankAccountNumber(229678302);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_BankCodeNumber() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setBankCodeNumber(229678303);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_Comment() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setComment("Comment for this one..");

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_DateUpToInDataBase() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setDateUpToInSystem(new Date());

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_Fax() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setFax("fax-number");

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_Mail() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setMailAddress("address@prov.com");

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_MobilePhone() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setMobilePhone("mobile phone number");

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_Phone() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setPhone("the phone number");

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_PhoneOfParents() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setPhoneOfParents("the phone number of the parents");

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_PossibleAGE() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setPossibleAGE(true);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
    p.setPossibleAGE(false);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_PossibleBoard() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setPossibleBoard(true);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
    p.setPossibleBoard(false);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_PossibleExtendedBoard() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setPossibleExtendedBoard(true);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
    p.setPossibleExtendedBoard(false);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_Kitchen() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setPossibleKitchen(true);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
    p.setPossibleKitchen(false);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_MAK() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setPossibleMAK(true);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
    p.setPossibleMAK(false);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_Misc() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setPossibleMisc(true);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
    p.setPossibleMisc(false);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_Participant() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setPossibleParticipant(true);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
    p.setPossibleParticipant(false);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_Seminar() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setPossibleSeminar(true);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
    p.setPossibleSeminar(false);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_Staff() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setPossibleStaff(true);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
    p.setPossibleStaff(false);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }

  /**
   * Test method for {@link ParticipantSaver#performSave(IParticipantModel, IParticipantEditView, IStatusView)} .
   */
  @Test
  public final void testPerformSave_StaffYouth() {
    final Participant p = new Participant(this.dummyParticipant);
    p.setPossibleStaffYouth(true);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
    p.setPossibleStaffYouth(false);

    editView.setParticipant(p);
    ParticipantSaver.performSave(this.model, editView, statusView);

    assertThat(this.stored).isEqualTo(p);
  }
}
