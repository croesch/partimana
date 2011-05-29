package com.github.croesch.i18n;

import java.util.ResourceBundle;

/**
 * This class provides access to the text properties file.
 * 
 * @author $Author: croesch $
 * @version $Revision: 1.9 $ ($Date: 2011/03/07 20:44:01 $)
 */
@SuppressWarnings("nls")
public enum Text {

  /** the name of the selected language */
  LANGUAGE (0),

  /** the message to show which language is selected */
  DEBUG_SELECTED_LANGUAGE (1000),

  /** the message to indicate that program is starting */
  DEBUG_PROGRAM_STARTING (1005),

  /** the message to indicate that program is sending the message to exit */
  DEBUG_PROGRAM_EXIT_NOTIFICATION (1009),

  /** the message to indicate that program is exiting */
  DEBUG_PROGRAM_EXITING (1010);

  /** the value of this instance */
  private final String string;

  /**
   * Constructs a new instance of a text that is part of the i18n. Each key will be searched in the file
   * 'lang/text*.properties' (where '*' is a string build from the locales properties language, country and variant, so
   * there will be four file names and the specific will be searched first). The name of this enumeration is the suffix
   * of the key where underscores will be replaced by dots. The prefix is {@code tetris.txt.}.
   * 
   * @author croesch
   * @since Date: 20.02.2011 11:23:31
   * @param k the key number for the message to lookup
   */
  private Text(final int k) {
    final ResourceBundle b = ResourceBundle.getBundle("lang/text", new XMLBundleControl());
    final String key = String.valueOf(k);
    this.string = b.getString(key);
  }

  @Override
  public String toString() {
    return text();
  }

  /**
   * String representation of this object
   * 
   * @return the String that represents the object
   */
  public String text() {
    return this.string;
  }

  /**
   * String representation of this object, but {x} will be replaced by argument number x starting to count from 0.
   * 
   * @param s the replacements
   * @return the String that represents the object with replaced placeholders
   */
  public String text(final Object ... s) {
    String text = this.string;
    for (int i = 0; i < s.length; ++i) {
      // prevent exceptions with using $
      final String param = s[i].toString().replaceAll("\\$", "\\\\\\$");
      text = text.replaceAll("(^|[^{])\\{" + i + "\\}", "$1" + param);
      text = text.replaceAll("\\{\\{" + i + "\\}", "\\{" + i + "\\}");
    }
    return text;
  }
}
