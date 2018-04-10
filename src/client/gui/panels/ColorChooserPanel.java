package client.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;

import client.gui.actions.*;
import util.Constants;

/*
 * This class is used to create a color choice palette as a drop down panel
 * 
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 03APR2018
 *
 */

public class ColorChooserPanel extends JDialog {
  private JPanel contentPane;
  public BoxesPanel box;
  private List<JPanel> palettes = new ArrayList<JPanel>();
  public List<Color> colorArray = new ArrayList<Color>();
  private JPanel colorPanel;
  
  /*
   * Constructor for the class
   */
  public ColorChooserPanel(BoxesPanel box) {
	    this.box = box;
	    contentPane = new JPanel();
	    setSize(195, 100);
	    setContentPane(contentPane);
	    setColors();
	    colorChooser();
  }

  /*
   * Method that calls the setColor method which creates the palette and the colorChooser method which creates the entire panel
   */
  public ColorChooserPanel() {
    contentPane = new JPanel();
    setSize(195, 100);
    setContentPane(contentPane);
    setColors();
    colorChooser();
  }

  /*
   * Method to create and add colors to the palette
   */
  private void setColors() {
	  
    setUndecorated(true);
    colorArray.add(Constants.GREEN);
    colorArray.add(Constants.RED);
    colorArray.add(Constants.PINK);
    colorArray.add(Constants.LIGHTBLUE);
    colorArray.add(Constants.PEACH);
    colorArray.add(Constants.YELLOW);
    colorArray.add(Constants.CYAN);
    colorArray.add(Constants.BLUE);
    colorArray.add(Constants.LIGHTPINK);
    colorArray.add(Constants.MAGENTA);
    colorArray.add(Constants.ORANGE);
    colorArray.add(Constants.BROWN);
    colorArray.add(Constants.MAROON);
    colorArray.add(Constants.LIGHTGREEN);
    colorArray.add(Constants.WHITE);
    colorArray.add(Constants.BLACK);
    colorArray.add(Constants.VIOLET);
    colorArray.add(Constants.CREAM);
    
  }

 /*
  * Method that creates the entire panel along with the palette
  */
  public void colorChooser() {
	  
    int x = 8;
	int y = 8;
	    
    setBounds(0, 0, 125, 68);
    contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
    contentPane.setLayout(null);
    contentPane.setLayout(new BorderLayout(0, 0));
    
    JPanel outerPanel = new JPanel();
    outerPanel.setBounds(97, 6, 1, 1);
    contentPane.add(outerPanel);
    colorPanel = new JPanel();
    Color defaultColor = new Color(255, 0, 0);
    colorPanel.setBackground(defaultColor);
    
    for (int i = 0; i < 18; i++) {
      colorPanel = new JPanel();
      colorPanel.setBackground(colorArray.get(i));
      colorPanel.addMouseListener(new ClientMouseEvents(this, "Color Panel"));

      if (i > 0 && i % 6 == 0) {
        y += 8 + 10;
        x = 8;
      }
      colorPanel.setBounds(x, y, 16, 16);
      palettes.add(colorPanel);
      x += 8 + 10;
      outerPanel.add(colorPanel);
    }
    
    contentPane.add(outerPanel);
    outerPanel.setLayout(null);
    JLabel lblClose = new JLabel("X");
    lblClose.setFont(new Font("Tahoma", Font.BOLD, 10));
    lblClose.setHorizontalAlignment(SwingConstants.CENTER);
    lblClose.setHorizontalTextPosition(SwingConstants.CENTER);
    lblClose.setVerticalAlignment(SwingConstants.CENTER);
    lblClose.setBounds(0, 0, 16, 16);
    lblClose.addMouseListener(new ClientMouseEvents(this, "Close"));
    palettes.get(palettes.size() - 1).add(lblClose, BorderLayout.CENTER);
    setVisible(true);
  }
}
