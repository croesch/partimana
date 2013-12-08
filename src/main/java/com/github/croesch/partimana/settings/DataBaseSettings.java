/*
 * Copyright (C) 2011-2012  Christian Roesch
 * 
 * This file is part of partimana.
 * 
 * partimana is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * partimana is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with partimana.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.croesch.partimana.settings;

import com.github.croesch.properties.PropertiesProvider;

/**
 * An enumeration of some database settings that are made in a property-file.<br>
 *
 * @author croesch
 * @since Date: Jan 14, 2012
 */
public enum DataBaseSettings {

  /** the jdbc url for database connection */
  DB_URL,
  /** the jdbc user for database connection */
  DB_USER,
  /** the jdbc password for database connection */
  DB_PASSWORD;

  /** the value set up in the properties file */
  private String value;

  /**
   * Constructs this setting. Loads the properties from file, if not yet done and fetches the value for this setting.
   * The key is the name of the setting.
   *
   * @since Date: Jan 15, 2012
   */
  private DataBaseSettings() {
    this.value = PropertiesProvider.getInstance().get("db", name());
  }

  /**
   * Returns the value of this setting.
   *
   * @return the value of this setting, read from the properties file.
   * @since Date: Jan 15, 2012
   */
  public String value() {
    return this.value;
  }
}
