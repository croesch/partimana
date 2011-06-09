package com.github.croesch.types;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Jun 9, 2011 9:39:18 PM
 */
public class ArgumentListTest {

  @Test
  public final void testArgumentList() {
    new ArgumentList(new String[] { "" });
    new ArgumentList(new String[] {});
    new ArgumentList(new String[] { null });
    new ArgumentList(null);
  }

  @Test
  public final void testGetCurrentArgument() {
    ArgumentList l = new ArgumentList(new String[] { "" });
    assertThat(l.getCurrentArgument()).isEmpty();

    l = new ArgumentList(new String[] {});
    assertThat(l.getCurrentArgument()).isNull();

    l = new ArgumentList(new String[] { null });
    assertThat(l.getCurrentArgument()).isEmpty();

    l = new ArgumentList(null);
    assertThat(l.getCurrentArgument()).isNull();

    l = new ArgumentList(new String[] { "element" });
    assertThat(l.getCurrentArgument()).isEqualTo("element");
    assertThat(l.getCurrentArgument()).isEqualTo("element");
    assertThat(l.getCurrentArgument()).isEqualTo("element");
  }

  @Test
  public final void testPreviewNextArgument() {
    ArgumentList l = new ArgumentList(new String[] { "", "two" });
    assertThat(l.getCurrentArgument()).isEmpty();
    assertThat(l.previewNextArgument()).isEqualTo("two");

    l = new ArgumentList(new String[] { "" });
    assertThat(l.previewNextArgument()).isNull();

    l = new ArgumentList(new String[] { "", null });
    assertThat(l.previewNextArgument()).isEmpty();
  }

  @Test
  public final void testPopArgument() {
    ArgumentList l = new ArgumentList(new String[] { "", "two" });
    assertThat(l.getCurrentArgument()).isEmpty();
    assertThat(l.previewNextArgument()).isEqualTo("two");
    l.popArgument();
    assertThat(l.getCurrentArgument()).isEqualTo("two");
    assertThat(l.previewNextArgument()).isNull();
    l.popArgument();
    assertThat(l.getCurrentArgument()).isNull();
    assertThat(l.previewNextArgument()).isNull();

    l = new ArgumentList(new String[] { null, "one", "two" });
    assertThat(l.getCurrentArgument()).isEmpty();
    assertThat(l.previewNextArgument()).isEqualTo("one");
    l.popArgument();
    assertThat(l.getCurrentArgument()).isEqualTo("one");
    assertThat(l.previewNextArgument()).isEqualTo("two");
    l.popArgument();
    assertThat(l.getCurrentArgument()).isEqualTo("two");
    assertThat(l.previewNextArgument()).isNull();
    l.popArgument();
    assertThat(l.getCurrentArgument()).isNull();
    assertThat(l.previewNextArgument()).isNull();

    final String[] array = new String[] { null, "one", "two", "three" };
    l = new ArgumentList(array);
    assertThat(l.getCurrentArgument()).isEmpty();
    assertThat(l.previewNextArgument()).isEqualTo("one");
    l.popArgument();
    assertThat(l.getCurrentArgument()).isEqualTo("one");
    assertThat(l.previewNextArgument()).isEqualTo("two");
    assertThat(array[0]).isNull();
    l.popArgument();
    assertThat(l.getCurrentArgument()).isEqualTo("two");
    assertThat(l.previewNextArgument()).isEqualTo("three");
    assertThat(array[1]).isNull();
    l.popArgument();
    assertThat(l.getCurrentArgument()).isEqualTo("three");
    assertThat(l.previewNextArgument()).isNull();
    assertThat(array[2]).isNull();
    l.popArgument();
    assertThat(l.getCurrentArgument()).isNull();
    assertThat(l.previewNextArgument()).isNull();
    assertThat(array[3]).isNull();
    l.popArgument();
    assertThat(l.getCurrentArgument()).isNull();
    assertThat(l.previewNextArgument()).isNull();
  }

  @Test
  public final void testHasMore() {
    ArgumentList l = new ArgumentList(new String[] { "", "two" });
    assertThat(l.hasMore()).isTrue();
    assertThat(l.hasMore()).isTrue();
    l.popArgument();
    assertThat(l.hasMore()).isFalse();
    assertThat(l.hasMore()).isFalse();
    l.popArgument();
    assertThat(l.hasMore()).isFalse();
    assertThat(l.hasMore()).isFalse();

    l = new ArgumentList(new String[] { null, "one", "two" });
    assertThat(l.hasMore()).isTrue();
    assertThat(l.hasMore()).isTrue();
    l.popArgument();
    assertThat(l.hasMore()).isTrue();
    assertThat(l.hasMore()).isTrue();
    l.popArgument();
    assertThat(l.hasMore()).isFalse();
    assertThat(l.hasMore()).isFalse();
    l.popArgument();
    assertThat(l.hasMore()).isFalse();
    assertThat(l.hasMore()).isFalse();

    final String[] array = new String[] { null, "one", "two", "three" };
    l = new ArgumentList(array);
    assertThat(l.hasMore()).isTrue();
    assertThat(l.hasMore()).isTrue();
    l.popArgument();
    assertThat(l.hasMore()).isTrue();
    assertThat(l.hasMore()).isTrue();
    l.popArgument();
    assertThat(l.hasMore()).isTrue();
    assertThat(l.hasMore()).isTrue();
    l.popArgument();
    assertThat(l.hasMore()).isFalse();
    assertThat(l.hasMore()).isFalse();
    l.popArgument();
    assertThat(l.hasMore()).isFalse();
    assertThat(l.hasMore()).isFalse();
    l.popArgument();
    assertThat(l.hasMore()).isFalse();
    assertThat(l.hasMore()).isFalse();
  }

}
