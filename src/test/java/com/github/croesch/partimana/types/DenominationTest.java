package com.github.croesch.partimana.types;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Provides test cases for {@link Denomination}.
 * 
 * @author croesch
 * @since Date: Oct 14, 2012
 */
public class DenominationTest {

  @Test
  public void testOf() {
    for (final Denomination dn : Denomination.values()) {
      assertThat(Denomination.of(dn.getStorableRepresentation())).isEqualTo(dn);
    }

    assertThat(Denomination.of(-42)).isNull();
  }

}
