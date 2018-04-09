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

/*
 * This class is used to create a color choice palette as a drop down panel
 * 
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 03APR2018
 *
 */

public class ColorChooserPanel extends JDialog {
  JPanel contentPane;
  BoxesPanel box;
  List<JPanel> palettes = new ArrayList<JPanel>();
  List<Color> colorArray = new ArrayList<Color>();
  JPanel colorPanel;

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
    colorArray.add(new Color(255, 0, 0));
    colorArray.add(new Color(244, 164, 96));
    colorArray.add(new Color(255, 69, 0));
    colorArray.add(Color.YELLOW);
    colorArray.add(Color.CYAN);
    colorArray.add(Color.BLUE);
    colorArray.add(new Color(135, 206, 250));
    colorArray.add(new Color(0, 191, 255));
    colorArray.add(new Color(0, 0, 128));
    colorArray.add(new Color(204, 204, 255));
    colorArray.add(new Color(255, 20, 147));
    colorArray.add(new Color(165, 42, 42));
    colorArray.add(new Color(154, 205, 50));
    colorArray.add(new Color(34, 139, 34));
    colorArray.add(new Color(210, 180, 140));
    colorArray.add(new Color(255, 255, 240));
    colorArray.add(new Color(255, 228, 225));
    colorArray.add(new Color(169, 169, 169));
    
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
      colorPanel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
	          box.setBoxColor(((JPanel) e.getSource()).getBackground());
	          setVisible(false);
	          dispose();
          }
       });

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
    lblClose.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        box.setBoxColor(colorArray.get(colorArray.size() - 1));
        setVisible(false);
        dispose();
      }
    });
    palettes.get(palettes.size() - 1).add(lblClose, BorderLayout.CENTER);
    setVisible(true);
  }
}
