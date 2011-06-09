package com.github.croesch.types;

/**
 * Represents a list of arguments.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011 6:17:04 AM
 */
public class ArgumentList {

  private final String[] args;

  private int pointer = 0;

  public ArgumentList(final String[] a) {
    this.args = a;
  }

  public String getCurrentArgument() {
    return get(this.pointer);
  }

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

  public String previewNextArgument() {
    return get(this.pointer + 1);
  }

  public void popArgument() {
    if (this.args.length > this.pointer) {
      this.args[this.pointer++] = null;
    }
  }

  public boolean hasMore() {
    return previewNextArgument() != null;
  }
}
