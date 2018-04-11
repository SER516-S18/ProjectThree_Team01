package server.gui.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import server.gui.EmotivComposer;
import server.gui.actions.MouseEvents;
import util.Constants;

/**
 * This class's purpose is to display the drop down menu items for the signals to either
 * denote good signal or weak signal and represented respectively by a wireless icon
 * showing signal strength
 * 
 * @author Vihar Bhatt
 * @author Cephas Armstrong-Mensah
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 02APR2018
 *
 */
public class SignalMenu extends JDialog {

  private static final long serialVersionUID = 744880228052339810L;

  private JPanel goodSignalPanel;
  private JPanel weakSignalPanel;
  private JPanel contentPane;

  private JLabel weakIcon;
  private JLabel weakSignalLabel;
  private JLabel goodIcon;
  private JLabel goodSignalLabel;

  private ClassLoader loader = getClass().getClassLoader();

  public SignalMenu(EmotivComposer parent) {
    super(parent);
    setUndecorated(true);
    setSize(200, 101);
    setLocationRelativeTo(parent);

    contentPane = new JPanel();
    contentPane.setBackground(Color.BLACK);
    contentPane.setLayout(null);
    setContentPane(contentPane);

    initialize();
  }

  private void initialize() {
    goodSignalPanel = new JPanel();
    goodSignalPanel.setBackground(Constants.WHITE);
    goodSignalPanel.setBounds(0, 0, 200, 50);
    goodSignalPanel.setLayout(null);

    goodIcon = new JLabel(new ImageIcon(loader.getResource("strong.png")));
    goodIcon.setBounds(1, 1, 48, 48);

    goodSignalLabel = new JLabel("Good Signal");
    goodSignalLabel.setBounds(50, 0, 150, 50);
    goodSignalLabel.setHorizontalAlignment(SwingConstants.CENTER);
    goodSignalLabel.setFont(new Font("Dialog", Font.BOLD, 16));
    goodSignalLabel.setAlignmentY(0.0f);

    goodSignalPanel.add(goodIcon);
    goodSignalPanel.add(goodSignalLabel);

    weakSignalPanel = new JPanel();
    weakSignalPanel.setBackground(Constants.WHITE);
    weakSignalPanel.setBounds(0, 51, 200, 50);
    weakSignalPanel.setLayout(null);

    weakIcon = new JLabel(new ImageIcon(loader.getResource("weak.png")));
    weakIcon.setBounds(1, 1, 48, 48);

    weakSignalLabel = new JLabel("Weak Signal");
    weakSignalLabel.setBounds(50, 0, 150, 50);
    weakSignalLabel.setAlignmentY(Component.TOP_ALIGNMENT);
    weakSignalLabel.setFont(new Font("Dialog", Font.BOLD, 16));
    weakSignalLabel.setHorizontalAlignment(SwingConstants.CENTER);

    weakSignalPanel.add(weakIcon);
    weakSignalPanel.add(weakSignalLabel);

    contentPane.add(goodSignalPanel);
    contentPane.add(weakSignalPanel);

    addMouseListener(new MouseEvents(this, "signal"));
  }

  public void setVisibleFalse(Component signal) {
    dispose();
  }

  public void triggerActionEvent(int yPosition) {
    if (yPosition < 51) {
      MenuBarPanel.setIconImage("strong.png");
    } else {
      MenuBarPanel.setIconImage("weak.png");
    }

    dispose();
  }
}