package com.github.croesch.model.api;

import com.github.croesch.types.Camp;
import com.github.croesch.types.exceptions.RequiredFieldSetToNullException;

/**
 * Interface for the camp-model.
 * 
 * @author croesch
 * @since Date: May 29, 2011
 */
public interface ICampModel extends ICampModel4View {

  /**
   * Creates the given {@link Camp} and stores it, if it doesn't exist already. If it exists already the existing camp
   * will be updated with the information fetched from the given camp.
   * 
   * @author croesch
   * @since Date: Aug 8, 2011
   * @param c the {@link Camp} to store
   * @throws RequiredFieldSetToNullException if the given {@link Camp} is <code>null</code>
   */
  void store(Camp c) throws RequiredFieldSetToNullException;

  /**
   * Tries to delete the {@link Camp} from the stored data. If it doesn't exist nothing will be done. So after calling
   * this method the camp with the given number won't be stored, no matter if it existed before.
   * 
   * @author croesch
   * @since Date: Aug 8, 2011
   * @param id the number of the {@link Camp} to delete
   */
  void deleteCamp(long id);

}
