package com.github.croesch.partimana.types;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.github.croesch.partimana.types.ArgumentList;

/**
 * Provides different test methods for {@link ArgumentList}
 * 
 * @author croesch
 * @since Date: Jun 9, 2011
 */
public class ArgumentListTest {

  /**
   * Tests the constructor of {@link ArgumentList}
   */
  @Test
  public final void testArgumentList() {
    new ArgumentList(new String[] { "" });
    new ArgumentList(new String[] {});
    new ArgumentList(new String[] { null });
    new ArgumentList(null);
  }

  /**
   * Test method that ensures, that the array is not stored by reference.
   */
  @Test
  public final void testReference() {
    final String[] a = new String[] { "a", "b", "c" };
    final ArgumentList l = new ArgumentList(a);
    assertThat(l.getCurrentArgument()).isEqualTo("a");
    a[0] = "c";
    assertThat(l.getCurrentArgument()).isEqualTo("a");
  }

  /**
   * Tests the behaviour of {@link ArgumentList#getCurrentArgument()}
   * 
   * @author croesch
   * @since Date: Jun 18, 2011
   */
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

  /**
   * Tests the behaviour of {@link ArgumentList#previewNextArgument()}
   * 
   * @author croesch
   * @since Date: Jun 18, 2011
   */
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

  /**
   * Tests the behaviour of {@link ArgumentList#popArgument()}
   * 
   * @author croesch
   * @since Date: Jun 18, 2011
   */
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
    l.popArgument();
    assertThat(l.getCurrentArgument()).isEqualTo("two");
    assertThat(l.previewNextArgument()).isEqualTo("three");
    l.popArgument();
    assertThat(l.getCurrentArgument()).isEqualTo("three");
    assertThat(l.previewNextArgument()).isNull();
    l.popArgument();
    assertThat(l.getCurrentArgument()).isNull();
    assertThat(l.previewNextArgument()).isNull();
    l.popArgument();
    assertThat(l.getCurrentArgument()).isNull();
    assertThat(l.previewNextArgument()).isNull();
  }

  /**
   * Tests the behaviour of {@link ArgumentList#hasMore()}
   * 
   * @author croesch
   * @since Date: Jun 18, 2011
   */
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