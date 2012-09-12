package com.github.croesch.partimana.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import com.github.croesch.partimana.annotation.MayBeNull;
import com.github.croesch.partimana.annotation.NotNull;

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
   * @since Date: Aug 17, 2011
   * @param stream the xml-stream to read the data from
   * @throws IOException in case of IO problem, or if stream is <code>null</code>
   */
  XMLResourceBundle(final InputStream stream) throws IOException {
    if (stream == null) {
      throw new IOException();
    }
    this.props = new Properties();
    this.props.loadFromXML(stream);
  }

  @Override
  @MayBeNull
  protected Object handleGetObject(final String key) {
    return this.props.getProperty(key);
  }

  @Override
  @NotNull
  public Enumeration<String> getKeys() {
    return Collections.enumeration(this.props.stringPropertyNames());
  }
}
