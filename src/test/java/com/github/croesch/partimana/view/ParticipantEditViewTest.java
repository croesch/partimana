package com.github.croesch.partimana.view;

import static org.fest.assertions.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.junit.Before;
import org.junit.Test;

import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;
import com.github.croesch.partimana.view.api.IParticipantEditView;

/**
 * Provides tests for {@link IParticipantEditView}
 * 
 * @author croesch
 * @since Date: Jun 26, 2011
 */
public class ParticipantEditViewTest {

  private IParticipantEditView testView;

  private Participant participant;

  @Before
  public void setUp() {
    this.testView = GuiActionRunner.execute(new GuiQuery<ParticipantEditView>() {
      @Override
      protected ParticipantEditView executeInEDT() throws Throwable {
        return new ParticipantEditView();
      }
    });

    this.participant = new Participant("Mustermann",
                                       "Max",
                                       Gender.MALE,
                                       Denomination.OTHER,
                                       new Date(),
                                       "Musterstrasse 12",
                                       12345,
                                       "Musterhausen",
                                       CountyCouncil.OTHER);

    final Date d1 = new Date(123);

    this.participant.setBank("bank");
    this.participant.setBankAccountNumber(1);
    this.participant.setBankCodeNumber(12);
    this.participant.setComment("comment");
    this.participant.setDateSinceInDataBase(d1);
    this.participant.setDateUpToInSystem(d1);
    this.participant.setDenomination(Denomination.JEWISH);
    this.participant.setFax("fax");
    this.participant.setMailAddress("mail");
    this.participant.setMobilePhone("mobile");
    this.participant.setPhone("phone");
    this.participant.setPhoneOfParents("phone");
    this.participant.setPossibleAGE(true);
    this.participant.setPossibleBoard(false);
    this.participant.setPossibleExtendedBoard(false);
    this.participant.setPossibleKitchen(true);
    this.participant.setPossibleMAK(true);
    this.participant.setPossibleMisc(false);
    this.participant.setPossibleParticipant(false);
    this.participant.setPossibleSeminar(false);
    this.participant.setPossibleStaff(false);
    this.participant.setPossibleStaffYouth(false);
    this.participant.setCityPostal("city");
    this.participant.setPostCodePostal(3124);
    this.participant.setStreetPostal("street");

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        ParticipantEditViewTest.this.testView.setParticipant(ParticipantEditViewTest.this.participant);
      }
    });
  }

  @Test
  public void testSetParticipant() {
    this.participant = new Participant("Mustermann",
                                       "Max",
                                       Gender.MALE,
                                       Denomination.OTHER,
                                       new Date(),
                                       "Musterstrasse 12",
                                       12345,
                                       "Musterhausen",
                                       CountyCouncil.OTHER);
    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        ParticipantEditViewTest.this.testView.setParticipant(ParticipantEditViewTest.this.participant);
      }
    });
  }

  @Test
  public void testGetFirstName() {
    assertThat(this.testView.getFirstName()).isEqualTo(this.participant.getForeName());
  }

  @Test
  public void testGetLastName() {
    assertThat(this.testView.getLastName()).isEqualTo(this.participant.getLastName());
  }

  @Test
  public void testGetGender() {
    assertThat(this.testView.getGender()).isEqualTo(this.participant.getGender());
  }

  @Test
  public final void testGetDenomination() {
    assertThat(this.testView.getDenomination()).isEqualTo(this.participant.getDenomination());
  }

  @Test
  public final void testGetBirthDate() throws ParseException {
    final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    final Date date = sdf.parse(sdf.format(this.participant.getBirthDate()));
    assertThat(this.testView.getBirthDate()).isEqualTo(date);
  }

  @Test
  public final void testGetPostalStreet() {
    assertThat(this.testView.getPostalStreet()).isEqualTo(this.participant.getStreetPostal());
  }

  @Test
  public final void testGetPostalPostCode() {
    assertThat(this.testView.getPostalPostCode()).isEqualTo(this.participant.getPostCodePostal());
  }

  @Test
  public final void testGetPostalCity() {
    assertThat(this.testView.getPostalCity()).isEqualTo(this.participant.getCityPostal());
  }

  @Test
  public final void testGetCountyCouncil() {
    assertThat(this.testView.getCountyCouncil()).isEqualTo(this.participant.getCountyCouncil());
  }

  @Test
  public final void testGetBank() {
    assertThat(this.testView.getBank()).isEqualTo(this.participant.getBank());
  }

  @Test
  public final void testGetBankAccountNumber() {
    assertThat(this.testView.getBankAccountNumber()).isEqualTo(this.participant.getBankAccountNumber());
  }

  @Test
  public final void testGetBankCodeNumber() {
    assertThat(this.testView.getBankCodeNumber()).isEqualTo(this.participant.getBankCodeNumber());
  }

  @Test
  public final void testGetComment() {
    assertThat(this.testView.getComment()).isEqualTo(this.participant.getComment());
  }

  @Test
  public final void testGetDateUpToInDataBase() throws ParseException {
    final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    final Date date = sdf.parse(sdf.format(this.participant.getDateUpToInSystem()));
    assertThat(this.testView.getDateUpToInDataBase()).isEqualTo(date);
  }

  @Test
  public final void testGetFax() {
    assertThat(this.testView.getFax()).isEqualTo(this.participant.getFax());
  }

  @Test
  public final void testGetMailAddress() {
    assertThat(this.testView.getMailAddress()).isEqualTo(this.participant.getMailAddress());
  }

  @Test
  public final void testGetMobilePhone() {
    assertThat(this.testView.getMobilePhone()).isEqualTo(this.participant.getMobilePhone());
  }

  @Test
  public final void testGetPhone() {
    assertThat(this.testView.getPhone()).isEqualTo(this.participant.getPhone());
  }

  @Test
  public final void testGetPhoneOfParents() {
    assertThat(this.testView.getPhoneOfParents()).isEqualTo(this.participant.getPhoneOfParents());
  }

  @Test
  public final void testGetPossibleAGE() {
    assertThat(this.testView.getPossibleAGE()).isEqualTo(this.participant.isPossibleAGE());
  }

  @Test
  public final void testGetPossibleBoard() {
    assertThat(this.testView.getPossibleBoard()).isEqualTo(this.participant.isPossibleBoard());
  }

  @Test
  public final void testGetPossibleExtendedBoard() {
    assertThat(this.testView.getPossibleExtendedBoard()).isEqualTo(this.participant.isPossibleExtendedBoard());
  }

  @Test
  public final void testGetPossibleKitchen() {
    assertThat(this.testView.getPossibleKitchen()).isEqualTo(this.participant.isPossibleKitchen());
  }

  @Test
  public final void testGetPossibleMAK() {
    assertThat(this.testView.getPossibleMAK()).isEqualTo(this.participant.isPossibleMAK());
  }

  @Test
  public final void testGetPossibleMisc() {
    assertThat(this.testView.getPossibleMisc()).isEqualTo(this.participant.isPossibleMisc());
  }

  @Test
  public final void testGetPossibleParticipant() {
    assertThat(this.testView.getPossibleParticipant()).isEqualTo(this.participant.isPossibleParticipant());
  }

  @Test
  public final void testGetPossibleSeminar() {
    assertThat(this.testView.getPossibleSeminar()).isEqualTo(this.participant.isPossibleSeminar());
  }

  @Test
  public final void testGetPossibleStaff() {
    assertThat(this.testView.getPossibleStaff()).isEqualTo(this.participant.isPossibleStaff());
  }

  @Test
  public final void testGetPossibleStaffYouth() {
    assertThat(this.testView.getPossibleStaffYouth()).isEqualTo(this.participant.isPossibleStaffYouth());
  }

  @Test
  public final void testGetLivingStreet() {
    assertThat(this.testView.getLivingStreet()).isEqualTo(this.participant.getStreet());
  }

  @Test
  public final void testGetLivingPostCode() {
    assertThat(this.testView.getLivingPostCode()).isEqualTo(this.participant.getPostCode());
  }

  @Test
  public final void testGetLivingCity() {
    assertThat(this.testView.getLivingCity()).isEqualTo(this.participant.getCity());
  }

}
