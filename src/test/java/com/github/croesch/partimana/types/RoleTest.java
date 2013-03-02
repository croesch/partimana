package com.github.croesch.partimana.types;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.github.croesch.partimana.i18n.Text;

/**
 * Provides test cases for {@link Role}.
 * 
 * @author croesch
 * @since Date: Mar 2, 2013
 */
public class RoleTest {

  @Test
  public void testValueOf() {
    assertThat(Role.valueOf(-1)).isNull();
    assertThat(Role.valueOf(0)).isEqualTo(Role.DIRECTION);
    assertThat(Role.valueOf(1)).isEqualTo(Role.STAFF);
    assertThat(Role.valueOf(2)).isEqualTo(Role.KITCHEN_STAFF);
    assertThat(Role.valueOf(3)).isEqualTo(Role.PARTICIPANT);
    assertThat(Role.valueOf(4)).isNull();
    assertThat(Role.valueOf(100)).isNull();
  }

  @Test
  public void testGetId() {
    assertThat(Role.DIRECTION.getId()).isEqualTo(0);
    assertThat(Role.STAFF.getId()).isEqualTo(1);
    assertThat(Role.KITCHEN_STAFF.getId()).isEqualTo(2);
    assertThat(Role.PARTICIPANT.getId()).isEqualTo(3);
  }

  @Test
  public void testToString() {
    assertThat(Role.DIRECTION.toString()).isEqualTo(Text.CAMP_PARTICIPANT_DIRECTION.text());
    assertThat(Role.STAFF.toString()).isEqualTo(Text.CAMP_PARTICIPANT_STAFF.text());
    assertThat(Role.KITCHEN_STAFF.toString()).isEqualTo(Text.CAMP_PARTICIPANT_KITCHEN_STAFF.text());
    assertThat(Role.PARTICIPANT.toString()).isEqualTo(Text.CAMP_PARTICIPANT_PARTICIPANT.text());
  }
}
