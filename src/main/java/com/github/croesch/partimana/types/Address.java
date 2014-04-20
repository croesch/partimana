package com.github.croesch.partimana.types;

import com.github.croesch.annotate.MayBeNull;
import com.github.croesch.annotate.NotNull;

/**
 * Represents an address. With post code, street and city.
 *
 * @author croesch
 * @since Date: Jun 18, 2011
 */
public final class Address {

  /** the street */
  @MayBeNull
  private String street = null;

  /** the post code */
  private int postCode = 0;

  /** the city */
  @MayBeNull
  private String city = null;

  /**
   * Constructs an address with all field set to <code>null</code> or zero.
   *
   * @since Date: Jun 18, 2011
   */
  Address() {
    super();
  }

  /**
   * Constructs a new address with the given values.
   *
   * @param str the street
   * @param pc  the post code
   * @param c   the city
   * @since Date: Jun 18, 2011
   */
  Address(final String str, final int pc, final String c) {
    setStreet(str);
    setPostCode(pc);
    setCity(c);
  }

  /**
   * Returns the street.
   *
   * @return the street
   * @since Date: Jun 18, 2011
   */
  @MayBeNull
  String getStreet() {
    return street;
  }

  /**
   * Sets the street.
   *
   * @param str the street.
   * @return the object itself
   * @since Date: Jun 18, 2011
   */
  @NotNull
  Address setStreet(final String str) {
    street = str;
    return this;
  }

  /**
   * Returns the post code.
   *
   * @return the post code
   * @since Date: Jun 18, 2011
   */
  int getPostCode() {
    return postCode;
  }

  /**
   * Sets the post code.
   *
   * @param pc the post code.
   * @return the object itself
   * @since Date: Jun 18, 2011
   */
  @NotNull
  Address setPostCode(final int pc) {
    postCode = pc;
    return this;
  }

  /**
   * Returns the city.
   *
   * @return the city
   * @since Date: Jun 18, 2011
   */
  @MayBeNull
  String getCity() {
    return city;
  }

  /**
   * Sets the city.
   *
   * @param c the city
   * @return the object itself
   * @since Date: Jun 18, 2011
   */
  @NotNull
  Address setCity(final String c) {
    city = c;
    return this;
  }

  @Override
  @NotNull
  public String toString() {
    return getStreet() + ", " + getPostCode() + " " + getCity();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result;
    if (city != null) {
      result += city.hashCode();
    }
    result = prime * result + postCode;
    result = prime * result;
    if (street != null) {
      result += street.hashCode();
    }
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Address)) {
      return false;
    }
    final Address other = (Address) obj;
    if (city == null) {
      if (other.city != null) {
        return false;
      }
    } else if (!city.equals(other.city)) {
      return false;
    }
    if (postCode != other.postCode) {
      return false;
    }
    if (street == null) {
      if (other.street != null) {
        return false;
      }
    } else if (!street.equals(other.street)) {
      return false;
    }
    return true;
  }
}
