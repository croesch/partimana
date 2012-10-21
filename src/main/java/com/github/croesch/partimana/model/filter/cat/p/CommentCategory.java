package com.github.croesch.partimana.model.filter.cat.p;

import com.github.croesch.partimana.model.filter.cat.StringCategory;
import com.github.croesch.partimana.types.Participant;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public class CommentCategory extends StringCategory<Participant> {

  public CommentCategory() {
    super(null);//TODO
  }

  @Override
  protected String getValue(final Participant element) {
    return element.getComment();
  }

}