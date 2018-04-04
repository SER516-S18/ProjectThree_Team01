package server.gui.panels;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import server.gui.EmotivComposer;
import server.gui.actions.EmoMouse;
import util.Constants;

public class HamburgerMenu extends JDialog {

  private static final long serialVersionUID = 744880228052339810L;

  private EmotivComposer parent;
  private JPanel aboutPanel;
  private JPanel quitPanel;
  private JSeparator separator;

  private JLabel aboutLabel;
  private JLabel quitLabel;

  private JPanel contentPane;

  public HamburgerMenu(EmotivComposer parent) {
    super(parent);
    this.parent = parent;
    setLocationRelativeTo(parent);
    setUndecorated(true);

    contentPane = new JPanel();
    contentPane.setBounds(0, 0, 200, 101);
    contentPane.setLayout(null);
    setContentPane(contentPane);

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
    aboutLabel.addMouseListener(new EmoMouse(this));
    aboutPanel.add(aboutLabel);

    quitLabel = new JLabel("Quit");
    quitLabel.setBounds(0, 0, 200, 50);
    quitLabel.setAlignmentY(Component.TOP_ALIGNMENT);
    quitLabel.setFont(new Font("Dialog", Font.BOLD, 16));
    quitLabel.setHorizontalAlignment(SwingConstants.CENTER);
    quitLabel.addMouseListener(new EmoMouse(this));
    quitPanel.add(quitLabel);

    contentPane.add(aboutPanel);
    contentPane.add(separator);
    contentPane.add(quitPanel);

    addMouseListener(new EmoMouse(this));
  }

  public void setVisibleFalse(Component exited) {
    System.out.println("Component: " + exited.getClass());
    if (!(exited instanceof JPanel) && !(exited instanceof JLabel) && !(exited instanceof JSeparator)) {
      EmotivComposer.hideMenuItems();
    } else {
      System.out.println("Something else");
    }
  }

  public void triggerActionEvent(JLabel source) {
    if (source.getText().equalsIgnoreCase("quit")) {
      parent.closeThread();
    } else {
      System.out.println("About Action Happened");
    }
  }
}