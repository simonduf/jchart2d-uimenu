/*
 *  RangePanel.java of project jchart2d
 *  Copyright (c) 2007 - 2013 Achim Westermann, created on 09:50:20.
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 * 
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 * 
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 *  If you modify or optimize the code in a useful way please let me know.
 *  Achim.Westermann@gmx.de
 *
 */
package info.monitorenter.gui.chart.controls;

import info.monitorenter.gui.jcomponent.RangeSlider;
import info.monitorenter.util.Range;
import info.monitorenter.util.math.MathUtil;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * A panel that allows to choose a range from a special
 * {@link javax.swing.JSlider} with two sliders (dual Slider).
 * <p>
 * Credits go to the infovis project of Jean Daniel Fekete for the dual slider
 * comopenent: <a href="http://ivtk.sourceforge.net/"
 * target="_blank">http://ivtk.sourceforge.net/</a>.
 * <p>
 * 
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann </a>
 * @version $Revision: 1.15 $
 */
public class DoubleRangeChooserPanel extends JPanel {

  /**
   * Generated <code>serialVersionUID</code>.
   */
  private static final long serialVersionUID = 3258413911148410931L;

  /** The range slider to choose a range. */
  protected RangeSlider m_rangeSlider;
  protected final JTextField rangeMinView;
  protected final JTextField rangeMaxView;

  /** Used for formatting the numbers displayed in the text fields. */
  protected NumberFormat m_nf = NumberFormat.getNumberInstance(Locale.getDefault());

  /**
   * Creates an instance that works on the given range.
   * <p>
   * 
   * @param range
   *          defines the bounds of the current selection and the extension of
   *          these that is chooseable.
   */
  public DoubleRangeChooserPanel(final Range range) {

    super();
    this.m_nf.setMinimumFractionDigits(2);
    this.m_nf.setMaximumFractionDigits(10);

    double min = range.getMin();
    double max = range.getMax();
    double minBound;
    double  maxBound;
    if ((!MathUtil.isDouble(min)) || (min == Double.MIN_VALUE)) {
      min = -100;
    }

    if ((!MathUtil.isDouble(max)) || ((max == Double.MAX_VALUE))) {
      max = 100;
    }
    minBound = (min - (max - min) / 2);
    maxBound = (max + (max - min) / 2);

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

    //Range is hard-coded to be between -150 and 150
	// -100 = current min
    // 100 = current max
    
    
    this.m_rangeSlider = new RangeSlider();
    this.m_rangeSlider.setMinimum(-150);//minBound
    this.m_rangeSlider.setMaximum(150);//maxBound
    this.m_rangeSlider.setUpperValue(100);//max
    this.m_rangeSlider.setValue(-100);//min
    
    JPanel rangePanel = new JPanel();

    rangePanel.setLayout(new FlowLayout());
    // bisliderPanel.add(Box.createGlue());
    rangePanel.add(this.m_rangeSlider);
    // bisliderPanel.add(Box.createGlue());
    //this.add(rangePanel);

    this.add(Box.createVerticalStrut(10));
    // text field views to show the range values;
    // minimum value view:
    rangeMinView = new JTextField();
    rangeMinView.setText(this.m_nf.format(new Double(this.m_rangeSlider.getValue())));
    rangeMinView.setEditable(true);
    rangeMinView.setPreferredSize(new Dimension(120, 20));
    rangeMinView.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent ae) {
        JTextField textField = (JTextField) ae.getSource();
        try {
          Number entered = DoubleRangeChooserPanel.this.m_nf.parse(textField.getText());
          int low = entered.intValue();
          int high = DoubleRangeChooserPanel.this.m_rangeSlider.getUpperValue();
          int minSlider = low - (high - low) / 2;
          DoubleRangeChooserPanel.this.m_rangeSlider.setMinimum(minSlider);
          DoubleRangeChooserPanel.this.m_rangeSlider.setValue(low);
          
        } catch (ParseException e) {
          // TODO: maybe inform user of invalid input.
          textField.setText(DoubleRangeChooserPanel.this.m_nf.format(DoubleRangeChooserPanel.this.m_rangeSlider
              .getMinimum()));
          e.printStackTrace();
        }
      }
    });
    rangeMinView.setToolTipText("Enter a number and hit Return.");

    // maximum value view:
    rangeMaxView = new JTextField();
    rangeMaxView.setText(new Double(this.m_rangeSlider.getUpperValue()).toString());
    rangeMaxView.setEditable(true);

    rangeMaxView.setPreferredSize(new Dimension(120, 20));
    rangeMaxView.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent ae) {
        JTextField textField = (JTextField) ae.getSource();
        try {
          Number entered = DoubleRangeChooserPanel.this.m_nf.parse(textField.getText());
          int high = entered.intValue();
          int low = DoubleRangeChooserPanel.this.m_rangeSlider.getValue();
          int maxSlider =  + (high - low) / 2;
          DoubleRangeChooserPanel.this.m_rangeSlider.setMaximum(maxSlider);
          DoubleRangeChooserPanel.this.m_rangeSlider.setUpperValue(high);

        } catch (ParseException e) {
          // TODO: maybe inform user of invalid input.
          textField.setText(DoubleRangeChooserPanel.this.m_nf.format(DoubleRangeChooserPanel.this.m_rangeSlider
              .getMaximum()));
          e.printStackTrace();
        }
      }
    });
    rangeMaxView.setToolTipText("Enter a number and hit Return.");

    this.m_rangeSlider.addChangeListener(new ChangeListener() {

      /**
       * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
       */
      public void stateChanged(ChangeEvent e) {
        RangeSlider slider = (RangeSlider)e.getSource();
        int value = slider.getValue();
        String text = DoubleRangeChooserPanel.this.m_nf.format(value);
        rangeMinView.setText(text);
        value = slider.getUpperValue();
        text = DoubleRangeChooserPanel.this.m_nf.format(value);
        rangeMaxView.setText(text);
      }
    });

    // add range views to panel:
    JPanel rangeViewPanel = new JPanel();
    rangeViewPanel.setMaximumSize(new Dimension(300, 30));
    rangeViewPanel.setLayout(new BoxLayout(rangeViewPanel, BoxLayout.X_AXIS));
    rangeViewPanel.add(Box.createHorizontalGlue());
    rangeViewPanel.add(rangeMinView);
    rangeViewPanel.add(Box.createHorizontalStrut(10));
    rangeViewPanel.add(rangeMaxView);
    rangeViewPanel.add(Box.createHorizontalGlue());
    this.add(Box.createVerticalStrut(10));
    this.add(rangeViewPanel);
    this.add(Box.createVerticalGlue());
  }

  /**
   * Returns the current selected range.
   * <p>
   * 
   * @return the current selected range.
   */
  public Range getRange() {
	double minValue = 0;
	double maxValue = 0;
	try {
		minValue = (double) DoubleRangeChooserPanel.this.m_nf.parse(rangeMinView.getText());
		maxValue = (double) DoubleRangeChooserPanel.this.m_nf.parse(rangeMaxView.getText());
	} catch (ParseException e) {
		e.printStackTrace();
	}
	
    
	return new Range( minValue,maxValue );
  }
}
