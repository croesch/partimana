package com.github.croesch.partimana.types;

import static org.fest.assertions.Assertions.assertThat;

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

}
