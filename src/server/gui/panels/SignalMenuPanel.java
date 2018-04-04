package server.gui.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import server.gui.EmotivComposer;

public class SignalMenuPanel extends JPanel {

  private static final long serialVersionUID = 744880228052339810L;

  private JPanel goodSignalPanel;
  private JPanel weakSignalPanel;
  private JSeparator separator;

  private JLabel weakIcon;
  private JLabel weakSignalLabel;
  private JLabel goodIcon;
  private JLabel goodSignalLabel;

  private ClassLoader loader = getClass().getClassLoader();;

  public SignalMenuPanel() {
    setBounds(0, 0, 200, 101);
    setLayout(null);
    initialize();
  }

  private void initialize() {
    goodSignalPanel = new JPanel();
    goodSignalPanel.setBackground(Color.WHITE);
    goodSignalPanel.setBounds(0, 0, 200, 50);
    goodSignalPanel.setLayout(null);

    goodIcon = new JLabel(new ImageIcon(loader.getResource("strong.png")));
    goodIcon.setIcon(new ImageIcon(loader.getResource("strong.png")));
    goodIcon.setBounds(1, 1, 48, 48);

    goodSignalLabel = new JLabel("Good Signal");
    goodSignalLabel.setBounds(50, 0, 150, 50);
    goodSignalLabel.setHorizontalAlignment(SwingConstants.CENTER);
    goodSignalLabel.setFont(new Font("Dialog", Font.BOLD, 16));
    goodSignalLabel.setAlignmentY(0.0f);

    goodSignalPanel.add(goodIcon);
    goodSignalPanel.add(goodSignalLabel);

    separator = new JSeparator();
    separator.setAlignmentY(Component.TOP_ALIGNMENT);
    separator.setAlignmentX(Component.LEFT_ALIGNMENT);
    separator.setBounds(0, 50, 200, 1);

    weakSignalPanel = new JPanel();
    weakSignalPanel.setBackground(Color.WHITE);
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

    add(goodSignalPanel);
    add(separator);
    add(weakSignalPanel);
  }

  public void showSignalMenu() {
    EmotivComposer.showSignalItems();
  }
}