package com.github.croesch.partimana.view;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;
import java.util.Date;

import javax.swing.JFrame;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.junit.Test;

import com.github.croesch.partimana.PartiManaDefaultGUITestCase;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.FilterModel;
import com.github.croesch.partimana.types.Camp;

/**
 * Provides test cases for the {@link SearchView}.
 * 
 * @author croesch
 * @since Date: Nov 2, 2012
 */
public class SearchViewGuiTest extends PartiManaDefaultGUITestCase {

  private FrameFixture searchView;

  private FilterModel<Camp> campFilterModel;

  private Camp[] camps;

  @Override
  protected void before() {
    this.camps = new Camp[5];
    this.camps[0] = new Camp("OFZ", new Date(15000000), new Date(110000000), "Berlin", "20 USD");
    this.camps[1] = new Camp("HFZ", new Date(25000000), new Date(210000000), "Frankfurt", "2 EUR");
    this.camps[2] = new Camp("Freizeit", new Date(35000000), new Date(310000000), "Stuttgart", "2");
    this.camps[3] = new Camp("Lager", new Date(45000000), new Date(410000000), "Hannover", "10");
    this.camps[4] = new Camp("Camp", new Date(55000000), new Date(510000000), "München", "200");

    this.campFilterModel = new FilterModel<Camp>(Arrays.asList(this.camps));
    this.searchView = new FrameFixture(robot(), GuiActionRunner.execute(new GuiQuery<JFrame>() {
      @Override
      protected JFrame executeInEDT() throws Throwable {
        return new SearchView<Camp>("searchingView", SearchViewGuiTest.this.campFilterModel);
      }
    }));
    this.searchView.show();
  }

  @Test
  public void testView() {
    assertThat(this.searchView.component().getName()).isEqualTo("searchingView");
    this.searchView.button("close").requireText(Text.CLOSE.text());
  }
}
