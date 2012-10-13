package com.github.croesch.partimana.types;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Provides test cases for {@link CountyCouncil}.
 * 
 * @author croesch
 * @since Date: Oct 14, 2012
 */
public class CountyCouncilTest {

  @Test
  public void testOf() {
    for (final CountyCouncil cc : CountyCouncil.values()) {
      assertThat(CountyCouncil.of(cc.getStorableRepresentation())).isEqualTo(cc);
    }

    assertThat(CountyCouncil.of(-42)).isNull();
  }

}
