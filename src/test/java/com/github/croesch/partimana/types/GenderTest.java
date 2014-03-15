package com.github.croesch.partimana.types;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * Provides test cases for {@link Gender}.
 *
 * @author croesch
 * @since Date: Oct 14, 2012
 */
public class GenderTest {

  @Test
  public void testOf() {
    for (final Gender gn : Gender.values()) {
      assertThat(Gender.of(gn.getStorableString())).isEqualTo(gn);
    }

    assertThat(Gender.of(null)).isNull();
    assertThat(Gender.of("")).isNull();
    assertThat(Gender.of(" ")).isNull();
  }

  @Test
  public void testTechnicalRepresentation() {
    assertThat(Gender.MALE.getRepresentation()).isEqualTo("m");
    assertThat(Gender.FEMALE.getRepresentation()).isEqualTo("w");
  }
}
