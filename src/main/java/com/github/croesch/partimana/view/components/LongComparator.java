package com.github.croesch.partimana.view.components;

import java.io.Serializable;
import java.util.Comparator;

/**
 * {@link Comparator} for long values.
 *
 * @author croesch
 * @since Date: Sep 12, 2012
 */
public final class LongComparator implements Comparator<Long>, Serializable {

  /** generated */
  private static final long serialVersionUID = -3652009932640474700L;

  @Override
  public int compare(final Long o1, final Long o2) {
    if (o2.longValue() > o1.longValue()) {
      return -1;
    }
    if (o1.longValue() > o2.longValue()) {
      return 1;
    }
    return 0;
  }
}
