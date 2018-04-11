package client.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import client.gui.actions.ClientMouseEvents;

/**
 * This class is used to create a panel for the Color chooser section in the Performance
 * Metrics frame
 * 
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 03APR2018
 *
 */

public class EmoStatePanel extends JPanel {
  private static final long serialVersionUID = 8834818931023364414L;

  public PerformanceMetricPanel parent;
  public int boxNumber;

  /*
   * Constructor for BoxPanel class
   */
  public EmoStatePanel(PerformanceMetricPanel parent, String boxName, int boxNumber) {
    setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
    this.parent = parent;
    setLayout(new BorderLayout(0, 0));
    initializer(boxName);
    this.boxNumber = boxNumber;

  }

  /*
   * Constructor for BoxPanel class
   */
  public void initializer(String boxName) {
    String initial = boxName.substring(0, 2);
    setSize(90, 90);
    JLabel lblShortName = new JLabel(initial);
    lblShortName.setForeground(Color.WHITE);
    lblShortName.setVerticalAlignment(SwingConstants.TOP);
    lblShortName.setFont(new Font("Century", Font.BOLD, 30));
    lblShortName.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel lblFullName = new JLabel(boxName);
    lblFullName.setForeground(Color.WHITE);
    lblFullName.setVerticalAlignment(SwingConstants.TOP);
    lblFullName.setFont(new Font("Century", Font.PLAIN, 14));
    lblFullName.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel lblDropDown = new JLabel("V    ");
    lblDropDown.setForeground(Color.WHITE);
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
