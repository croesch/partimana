package com.github.croesch.partimana.i18n;

import com.github.croesch.annotate.MayBeNull;
import com.github.croesch.annotate.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Resource bundle from javadoc-example of {@link Control} for XML-based bundles.
 *
 * @author croesch
 * @since Date: Aug 17, 2011
 */
final class XMLResourceBundle extends ResourceBundle {

  /** the properties that contain the content of the xml file */
  @NotNull
  private final Properties props;

  /**
   * Constructs the resource bundle from javadoc-example of {@link Control} for XML-based bundles.
   *
   * @param stream the xml-stream to read the data from
   * @throws IOException in case of IO problem, or if stream is <code>null</code>
   * @since Date: Aug 17, 2011
   */
  XMLResourceBundle(final InputStream stream) throws IOException {
    if (stream == null) {
      throw new IOException();
    }
    props = new Properties();
    props.loadFromXML(stream);
  }

  @Override
  @MayBeNull
  protected Object handleGetObject(final String key) {
    return props.getProperty(key);
  }

  @Override
  @NotNull
  public Enumeration<String> getKeys() {
    return Collections.enumeration(props.stringPropertyNames());
  }
}
