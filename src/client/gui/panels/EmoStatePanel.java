package client.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import client.gui.actions.*;

/*
 * This class is used to create a panel for the Color chooser section in the Performance Metrics frame
 * 
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 03APR2018
 *
 */

public class EmoStatePanel extends JPanel {
  public PerformanceMetricPanel parent;
  private int actionListenerFlag = 0;
  public int boxNumber;

 /* 
  * Constructor for BoxPanel class
  */
 public EmoStatePanel(PerformanceMetricPanel parent, String boxName,int boxNumber) {
    setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
    this.parent = parent;
    setLayout(new BorderLayout(0, 0));
    initializer(boxName);
    this.boxNumber= boxNumber;
    
  }

  /* 
  * Constructor for BoxPanel class
  */
  public void initializer(String boxName) {
    String initial = boxName.substring(0, 2);
    setSize(90, 90);
    JLabel lblShortName = new JLabel(initial);
    lblShortName.setVerticalAlignment(SwingConstants.TOP);
    lblShortName.setFont(new Font("Century", Font.BOLD, 30));
    lblShortName.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel lblFullName = new JLabel(boxName);
    lblFullName.setVerticalAlignment(SwingConstants.TOP);
    lblFullName.setFont(new Font("Century", Font.PLAIN, 14));
    lblFullName.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel lblDropDown = new JLabel("V    ");
    lblDropDown.addMouseListener(new ClientMouseEvents(this, "V"));
    lblDropDown.setSize(20, 20);
    lblDropDown.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 10));
    lblDropDown.setHorizontalAlignment(SwingConstants.RIGHT);

    add(lblDropDown, BorderLayout.NORTH);
    add(lblShortName, BorderLayout.CENTER);
    add(lblFullName, BorderLayout.SOUTH);
  }
  
  /*
   * Method to set the background of the panel
   */
  public void setBoxColor(Color color) {
    setBackground(color);
  }

  /*
   * Method to create a ColorChooserPanel class object with relative positioning 
   */
  public void createColorChooser() {
    ColorChooserPanel cc = new ColorChooserPanel(this);
    cc.setLocationRelativeTo(this);
    cc.setVisible(true);
  }
}