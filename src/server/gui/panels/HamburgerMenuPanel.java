package server.gui.panels;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import util.Constants;

public class HamburgerMenuPanel extends JPanel {

  private static final long serialVersionUID = 744880228052339810L;

  private ClassLoader loader = getClass().getClassLoader();
  private JLabel aboutLabel;
  private JSeparator separator;
  private JLabel quitLabel;

  public HamburgerMenuPanel() {
    setBackground(Constants.WHITE);
    setBounds(0, 0, 200, 101);
    setLayout(null);
    setBorder(new LineBorder(Constants.BLACK));
    initialize();
  }

  private void initialize() {
    aboutLabel = new JLabel("About Project 3");
    aboutLabel.setAlignmentY(Component.TOP_ALIGNMENT);
    aboutLabel.setBounds(0, 0, 200, 50);
    aboutLabel.setFont(new Font("Dialog", Font.BOLD, 16));
    aboutLabel.setHorizontalAlignment(SwingConstants.CENTER);

    separator = new JSeparator();
    separator.setAlignmentY(Component.TOP_ALIGNMENT);
    separator.setAlignmentX(Component.LEFT_ALIGNMENT);
    separator.setBounds(0, 50, 200, 1);

    quitLabel = new JLabel("Quit");
    quitLabel.setAlignmentY(Component.TOP_ALIGNMENT);
    quitLabel.setBounds(0, 51, 200, 50);
    quitLabel.setFont(new Font("Dialog", Font.BOLD, 16));
    quitLabel.setHorizontalAlignment(SwingConstants.CENTER);

    add(aboutLabel);
    add(separator);
    add(quitLabel);
  }
}