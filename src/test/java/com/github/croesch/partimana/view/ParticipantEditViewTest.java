package com.github.croesch.partimana.view;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;
import com.github.croesch.partimana.view.api.IParticipantEditView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.edt.GuiTask;
import org.junit.Before;
import org.junit.Test;

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
    testView = GuiActionRunner.execute(new GuiQuery<ParticipantEditView>() {
      @Override
      protected ParticipantEditView executeInEDT() throws Throwable {
        return new ParticipantEditView(null);
      }
    });

    participant = new Participant("Mustermann",
                                  "Max",
                                  Gender.MALE,
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

    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        testView.setParticipant(participant);
      }
    });
  }

  @Test
  public void testSetParticipant() {
    participant = new Participant("Mustermann",
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
        testView.setParticipant(participant);
      }
    });
  }

  @Test
  public void testGetFirstName() {
    assertThat(testView.getFirstName()).isEqualTo(participant.getForeName());
  }

  @Test
  public void testGetLastName() {
    assertThat(testView.getLastName()).isEqualTo(participant.getLastName());
  }

  @Test
  public void testGetGender() {
    assertThat(testView.getGender()).isEqualTo(participant.getGender());
  }

  @Test
  public final void testGetDenomination() {
    assertThat(testView.getDenomination()).isEqualTo(participant.getDenomination());
  }

  @Test
  public final void testGetBirthDate() throws ParseException {
    final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    final Date date = sdf.parse(sdf.format(participant.getBirthDate()));
    assertThat(testView.getBirthDate()).isEqualTo(date);
  }

  @Test
  public final void testGetPostalStreet() {
    assertThat(testView.getPostalStreet()).isEqualTo(participant.getStreetPostal());
  }

  @Test
  public final void testGetPostalPostCode() {
    assertThat(testView.getPostalPostCode()).isEqualTo(participant.getPostCodePostal());
  }

  @Test
  public final void testGetPostalCity() {
    assertThat(testView.getPostalCity()).isEqualTo(participant.getCityPostal());
  }

  @Test
  public final void testGetCountyCouncil() {
    assertThat(testView.getCountyCouncil()).isEqualTo(participant.getCountyCouncil());
  }

  @Test
  public final void testGetBank() {
    assertThat(testView.getBank()).isEqualTo(participant.getBank());
  }

  @Test
  public final void testGetBankAccountNumber() {
    assertThat(testView.getBankAccountNumber()).isEqualTo(participant.getBankAccountNumber());
  }

  @Test
  public final void testGetBankCodeNumber() {
    assertThat(testView.getBankCodeNumber()).isEqualTo(participant.getBankCodeNumber());
  }

  @Test
  public final void testGetComment() {
    assertThat(testView.getComment()).isEqualTo(participant.getComment());
  }

  @Test
  public final void testGetDateUpToInDataBase() throws ParseException {
    final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    final Date date = sdf.parse(sdf.format(participant.getDateUpToInSystem()));
    assertThat(testView.getDateUpToInDataBase()).isEqualTo(date);
  }

  @Test
  public final void testGetFax() {
    assertThat(testView.getFax()).isEqualTo(participant.getFax());
  }

  @Test
  public final void testGetMailAddress() {
    assertThat(testView.getMailAddress()).isEqualTo(participant.getMailAddress());
  }

  @Test
  public final void testGetMobilePhone() {
    assertThat(testView.getMobilePhone()).isEqualTo(participant.getMobilePhone());
  }

  @Test
  public final void testGetPhone() {
    assertThat(testView.getPhone()).isEqualTo(participant.getPhone());
  }

  @Test
  public final void testGetPhoneOfParents() {
    assertThat(testView.getPhoneOfParents()).isEqualTo(participant.getPhoneOfParents());
  }

  @Test
  public final void testGetLivingStreet() {
    assertThat(testView.getLivingStreet()).isEqualTo(participant.getStreet());
  }

  @Test
  public final void testGetLivingPostCode() {
    assertThat(testView.getLivingPostCode()).isEqualTo(participant.getPostCode());
  }

  @Test
  public final void testGetLivingCity() {
    assertThat(testView.getLivingCity()).isEqualTo(participant.getCity());
  }
}
