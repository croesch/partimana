package com.github.croesch.types;

/**
 * Represents a list of arguments.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011 6:17:04 AM
 */
public final class ArgumentList {

  /** the real list of arguments */
  private final String[] args;

  /** the current pointer in the list */
  private int pointer = 0;

  /**
   * Constructs a list of arguments
   * 
   * @author croesch
   * @since Date: Jun 16, 2011 9:09:31 PM
   * @param a the arguments
   */
  public ArgumentList(final String[] a) {
    if (a != null) {
      this.args = a.clone();
    } else {
      this.args = null;
    }
  }

  /**
   * Returns the argument at the current position. Doesn't change the pointer.
   * 
   * @author croesch
   * @since Date: Jun 16, 2011 9:09:51 PM
   * @return {@link String} at the current position in the argument list
   */
  public String getCurrentArgument() {
    return get(this.pointer);
  }

  /**
   * Returns the argument at the given position.
   * 
   * @author croesch
   * @since Date: Jun 16, 2011 9:10:23 PM
   * @param i the position of the argument to fetch
   * @return the argument at the given position, an empty {@link String} if the argument is <code>null</code> or
   *         <code>null</code> if there is no argument at that position (even if the list is <code>null</code>).
   */
  private String get(final int i) {
    if (this.args == null) {
      return null;
    }
    if (this.args.length > i) {
      if (this.args[i] == null) {
        return "";
      }
      return this.args[i];
    }
    return null;
  }

  /**
   * Returns the argument at the next position, but doesn't change the pointer.
   * 
   * @author croesch
   * @since Date: Jun 16, 2011 9:11:57 PM
   * @return the argument at the given position, or <code>null</code> if there is no such argument. Will return an empty
   *         {@link String} if the argument is <code>null</code>
   */
  public String previewNextArgument() {
    return get(this.pointer + 1);
  }

  /**
   * Increments the pointer by one position.
   * 
   * @author croesch
   * @since Date: Jun 16, 2011 9:12:36 PM
   */
  public void popArgument() {
    if (this.args.length > this.pointer) {
      this.args[this.pointer++] = null;
    }
  }

  /**
   * Returns whether there are more arguments.
   * 
   * @author croesch
   * @since Date: Jun 16, 2011 9:13:40 PM
   * @return <code>true</code>, if there are more arguments
   */
  public boolean hasMore() {
    return previewNextArgument() != null;
  }
}
