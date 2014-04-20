package com.github.croesch.partimana.types;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * Provides tests for {@link Address}
 *
 * @author croesch
 * @since Date: Jun 18, 2011
 */
public class AddressTest {

  /** object to test. */
  private Address address;

  /**
   * Sets up the test object.
   *
   * @since Date: Jun 18, 2011
   */
  @Before
  public final void setUp() {
    address = new Address();
  }

  /**
   * Test method for {@link Address#Address()}.
   */
  @Test
  public final void testAddress() {
    assertThat(address.getCity()).isNull();
    assertThat(address.getStreet()).isNull();
    assertThat(address.getPostCode()).isZero();
  }

  /**
   * Test method for {@link Address#Address(String, int, String)}.
   */
  @Test
  public final void testAddressStringIntString() {
    address = new Address(null, 1, "two");
    assertThat(address.getStreet()).isNull();
    assertThat(address.getPostCode()).isEqualTo(1);
    assertThat(address.getCity()).isEqualTo("two");
  }

  /**
   * Test method for {@link Address#getStreet()} and {@link Address#setStreet(String)}.
   */
  @Test
  public final void testSetAndGetStreet() {
    address.setStreet("...");
    assertThat(address.getStreet()).isEqualTo("...");

    address.setStreet(null);
    assertThat(address.getStreet()).isNull();

    address.setStreet("street");
    assertThat(address.getStreet()).isEqualTo("street");
  }

  /**
   * Test method for {@link Address#getPostCode()} and {@link Address#setPostCode(int)}.
   */
  @Test
  public final void testSetAndGetPostCode() {
    address.setPostCode(0);
    assertThat(address.getPostCode()).isZero();

    address.setPostCode(1);
    assertThat(address.getPostCode()).isEqualTo(1);

    final int pc3 = 17836;
    address.setPostCode(pc3);
    assertThat(address.getPostCode()).isEqualTo(Integer.valueOf(pc3).intValue());
  }

  /**
   * Test method for {@link Address#getCity()} and {@link Address#setCity(String)}.
   */
  @Test
  public final void testSetAndGetCity() {
    address.setCity("...");
    assertThat(address.getCity()).isEqualTo("...");

    address.setCity(null);
    assertThat(address.getCity()).isNull();

    address.setCity("city");
    assertThat(address.getCity()).isEqualTo("city");
  }

  /**
   * Test method for {@link Address#equals(Object)} nad {@link Address#hashCode()}
   */
  @Test
  public final void testEqualsAndHashCode() {
    final int postCode = 22222;
    address = new Address("street", postCode, "city");
    final Address copy = new Address("street", postCode, "city");
    assertThat(address).isEqualTo(copy);

    assertThat(address.hashCode()).isEqualTo(copy.hashCode());
    copy.setStreet("stree");
    assertThat(address.hashCode()).isNotEqualTo(copy.hashCode());
    assertThat(address).isNotEqualTo(copy);

    copy.setCity(address.getCity());
    copy.setPostCode(address.getPostCode());
    copy.setStreet(address.getStreet());

    assertThat(address).isEqualTo(address);
    assertThat(address).isNotEqualTo(null);
    assertThat(address).isNotEqualTo("address");

    address.setCity(null);
    assertThat(address).isNotEqualTo(copy);
    address.setCity("other");
    assertThat(address).isNotEqualTo(copy);
    address.setCity(copy.getCity());

    address.setStreet(null);
    assertThat(address).isNotEqualTo(copy);
    address.setStreet("other");
    assertThat(address).isNotEqualTo(copy);
    address.setStreet(copy.getStreet());

    address.setPostCode(-1);
    assertThat(address).isNotEqualTo(copy);
    address.setPostCode(copy.getPostCode());

    assertThat(address).isEqualTo(copy);
  }

  /**
   * Test method for {@link Address#toString()}.
   */
  @Test
  public final void testToString() {
    address = new Address("street", 1223, "city");

    assertThat(address.toString()).contains(address.getCity());
    assertThat(address.toString()).contains(address.getStreet());
    assertThat(address.toString()).contains(String.valueOf(address.getPostCode()));
  }
}
