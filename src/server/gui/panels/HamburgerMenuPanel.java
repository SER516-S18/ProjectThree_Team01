package server.gui.panels;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import util.Constants;

public class HamburgerMenuPanel extends JPanel {

  private static final long serialVersionUID = 744880228052339810L;

  private JPanel aboutPanel;
  private JPanel quitPanel;
  private JSeparator separator;

  private JLabel aboutLabel;
  private JLabel quitLabel;

  public HamburgerMenuPanel() {
    setBounds(0, 0, 200, 101);
    setLayout(null);
    initialize();
  }

  private void initialize() {
    aboutPanel = new JPanel();
    aboutPanel.setBackground(Constants.WHITE);
    aboutPanel.setSize(200, 50);
    aboutPanel.setLayout(null);

    quitPanel = new JPanel();
    quitPanel.setBackground(Constants.WHITE);
    quitPanel.setSize(200, 50);
    quitPanel.setLocation(0, 51);
    quitPanel.setLayout(null);

    separator = new JSeparator();
    separator.setAlignmentY(Component.TOP_ALIGNMENT);
    separator.setAlignmentX(Component.LEFT_ALIGNMENT);
    separator.setBounds(0, 50, 200, 1);

    aboutLabel = new JLabel("About Project 3");
    aboutLabel.setBounds(0, 0, 200, 50);
    aboutLabel.setAlignmentY(Component.TOP_ALIGNMENT);
    aboutLabel.setFont(new Font("Dialog", Font.BOLD, 16));
    aboutLabel.setHorizontalAlignment(SwingConstants.CENTER);
    aboutPanel.add(aboutLabel);

    quitLabel = new JLabel("Quit");
    quitLabel.setBounds(0, 0, 200, 50);
    quitLabel.setAlignmentY(Component.TOP_ALIGNMENT);
    quitLabel.setFont(new Font("Dialog", Font.BOLD, 16));
    quitLabel.setHorizontalAlignment(SwingConstants.CENTER);
    quitPanel.add(quitLabel);

    add(aboutPanel);
    add(separator);
    add(quitPanel);
  }
}