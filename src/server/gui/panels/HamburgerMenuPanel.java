package server.gui.panels;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import util.Constants;

public class HamburgerMenuPanel extends JPanel {

  private static final long serialVersionUID = 744880228052339810L;
  private JPanel contentPane;
  ClassLoader cl = getClass().getClassLoader();

  public HamburgerMenuPanel() {
    setBackground(Constants.WHITE);
    setBounds(0, 0, 200, 101);
    setLayout(null);
    initialize();
  }

  private void initialize() {
    contentPane = new JPanel();
    contentPane.setVisible(false);
    contentPane.setAlignmentY(Component.TOP_ALIGNMENT);
    contentPane.setAlignmentX(Component.LEFT_ALIGNMENT);
    contentPane.setBorder(new LineBorder(Constants.BLACK));
    contentPane.setBackground(Constants.WHITE);
    contentPane.setBounds(0, 0, 200, 101);

    add(contentPane);
    contentPane.setLayout(null);

    JLabel aboutLabel = new JLabel("About Project 3");
    aboutLabel.setAlignmentY(Component.TOP_ALIGNMENT);
    aboutLabel.setBounds(0, 0, 200, 50);
    contentPane.add(aboutLabel);
    aboutLabel.setFont(new Font("Dialog", Font.BOLD, 16));
    aboutLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JSeparator separator = new JSeparator();
    separator.setAlignmentY(Component.TOP_ALIGNMENT);
    separator.setAlignmentX(Component.LEFT_ALIGNMENT);
    separator.setBounds(0, 50, 200, 1);
    contentPane.add(separator);

    JLabel quitLabel = new JLabel("Quit");
    quitLabel.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        quitLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
      }

      @Override
      public void focusLost(FocusEvent e) {
        quitLabel.setBorder(null);
      }
    });
    quitLabel.setAlignmentY(Component.TOP_ALIGNMENT);
    quitLabel.setBounds(0, 51, 200, 50);
    quitLabel.setFont(new Font("Dialog", Font.BOLD, 16));
    quitLabel.setHorizontalAlignment(SwingConstants.CENTER);
    contentPane.add(quitLabel);
    setVisible(true);
  }
}