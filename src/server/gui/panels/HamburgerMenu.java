package server.gui.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import server.gui.EmotivComposer;
import server.gui.actions.MouseEvents;
import util.Constants;

/**
 * This class's purpose is to display the drop down menu items for the hamburger
 * icon section. The menu items included are About and Quit.
 * 
 * @author Vihar Bhatt
 * @author Cephas Armstrong-Mensah
 * @version 1.0
 * @since 02APR2018
 *
 */
public class HamburgerMenu extends JDialog {

  private static final long serialVersionUID = 744880228052339810L;

  private EmotivComposer parent;
  private JPanel aboutPanel;
  private JPanel quitPanel;

  private JLabel aboutLabel;
  private JLabel quitLabel;

  private JPanel contentPane;

  public HamburgerMenu(EmotivComposer parent) {
    super(parent);
    this.parent = parent;
    setSize(200, 101);
    setUndecorated(true);

    contentPane = new JPanel();
    contentPane.setLayout(null);
    setContentPane(contentPane);
    contentPane.setBackground(Color.BLACK);

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

    aboutLabel = new JLabel("About Project 3");
    aboutLabel.setBounds(0, 0, 200, 50);
    aboutLabel.setAlignmentY(Component.TOP_ALIGNMENT);
    aboutLabel.setFont(new Font("Dialog", Font.BOLD, 16));
    aboutLabel.setHorizontalAlignment(SwingConstants.CENTER);
    // aboutLabel.addMouseListener(new EmoMouse(this));
    aboutPanel.add(aboutLabel);

    quitLabel = new JLabel("Quit");
    quitLabel.setBounds(0, 0, 200, 50);
    quitLabel.setAlignmentY(Component.TOP_ALIGNMENT);
    quitLabel.setFont(new Font("Dialog", Font.BOLD, 16));
    quitLabel.setHorizontalAlignment(SwingConstants.CENTER);
    // quitLabel.addMouseListener(new EmoMouse(this));
    quitPanel.add(quitLabel);

    contentPane.add(aboutPanel);
    contentPane.add(quitPanel);
    addMouseListener(new MouseEvents(this, "hamburger"));
  }

  public void setVisibleFalse(Component exited) {
    dispose();
  }

  public void triggerActionEvent(int yPosition) {
    if (yPosition > 51) {
      parent.closeThread();
    } else {
      new AboutPanel(EmotivComposer.getInstance());
    }
    dispose();
  }
}