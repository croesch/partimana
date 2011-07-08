package com.github.croesch.types;

import static org.fest.assertions.Assertions.assertThat;

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
   * @author croesch
   * @since Date: Jun 18, 2011
   */
  @Before
  public final void setUp() {
    this.address = new Address();
  }

  /**
   * Test method for {@link Address#Address()}.
   */
  @Test
  public final void testAddress() {
    assertThat(this.address.getCity()).isNull();
    assertThat(this.address.getStreet()).isNull();
    assertThat(this.address.getPostCode()).isZero();
  }

  /**
   * Test method for {@link Address#Address(String, int, String)}.
   */
  @Test
  public final void testAddressStringIntString() {
    this.address = new Address(null, 1, "two");
    assertThat(this.address.getStreet()).isNull();
    assertThat(this.address.getPostCode()).isEqualTo(1);
    assertThat(this.address.getCity()).isEqualTo("two");
  }

  /**
   * Test method for {@link Address#getStreet()} and {@link Address#setStreet()}.
   */
  @Test
  public final void testSetAndGetStreet() {
    this.address.setStreet("...");
    assertThat(this.address.getStreet()).isEqualTo("...");

    this.address.setStreet(null);
    assertThat(this.address.getStreet()).isNull();

    this.address.setStreet("street");
    assertThat(this.address.getStreet()).isEqualTo("street");
  }

  /**
   * Test method for {@link Address#getPostCode()} and {@link Address#setPostCode()}.
   */
  @Test
  public final void testSetAndGetPostCode() {
    this.address.setPostCode(0);
    assertThat(this.address.getPostCode()).isZero();

    this.address.setPostCode(1);
    assertThat(this.address.getPostCode()).isEqualTo(1);

    final int pc3 = 17836;
    this.address.setPostCode(pc3);
    assertThat(this.address.getPostCode()).isEqualTo(Integer.valueOf(pc3).intValue());
  }

  /**
   * Test method for {@link Address#getCity()} and {@link Address#setCity()}.
   */
  @Test
  public final void testSetAndGetCity() {
    this.address.setCity("...");
    assertThat(this.address.getCity()).isEqualTo("...");

    this.address.setCity(null);
    assertThat(this.address.getCity()).isNull();

    this.address.setCity("city");
    assertThat(this.address.getCity()).isEqualTo("city");
  }

  /**
   * Test method for {@link Address#equals(Object)} nad {@link Address#hashCode()}
   */
  @Test
  public final void testEqualsAndHashCode() {
    final int postCode = 22222;
    this.address = new Address("street", postCode, "city");
    final Address copy = new Address("street", postCode, "city");
    assertThat(this.address).isEqualTo(copy);

    assertThat(this.address.hashCode()).isEqualTo(copy.hashCode());
    copy.setStreet("stree");
    assertThat(this.address.hashCode()).isNotEqualTo(copy.hashCode());
    assertThat(this.address).isNotEqualTo(copy);

    copy.setCity(this.address.getCity());
    copy.setPostCode(this.address.getPostCode());
    copy.setStreet(this.address.getStreet());

    assertThat(this.address).isEqualTo(this.address);
    assertThat(this.address).isNotEqualTo(null);
    assertThat(this.address).isNotEqualTo("address");

    this.address.setCity(null);
    assertThat(this.address).isNotEqualTo(copy);
    this.address.setCity("other");
    assertThat(this.address).isNotEqualTo(copy);
    this.address.setCity(copy.getCity());

    this.address.setStreet(null);
    assertThat(this.address).isNotEqualTo(copy);
    this.address.setStreet("other");
    assertThat(this.address).isNotEqualTo(copy);
    this.address.setStreet(copy.getStreet());

    this.address.setPostCode(-1);
    assertThat(this.address).isNotEqualTo(copy);
    this.address.setPostCode(copy.getPostCode());

    assertThat(this.address).isEqualTo(copy);
  }

  /**
   * Test method for {@link Address#toString()}.
   */
  @Test
  public final void testToString() {
    this.address = new Address("street", 1223, "city");

    assertThat(this.address.toString()).contains(this.address.getCity());
    assertThat(this.address.toString()).contains(this.address.getStreet());
    assertThat(this.address.toString()).contains(String.valueOf(this.address.getPostCode()));
  }
}
