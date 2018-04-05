package server.gui.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import server.gui.EmotivComposer;
import server.gui.actions.EmoMouse;

public class SignalMenu extends JDialog {

  private static final long serialVersionUID = 744880228052339810L;

  private JPanel goodSignalPanel;
  private JPanel weakSignalPanel;
  private JSeparator separator;

  private JLabel weakIcon;
  private JLabel weakSignalLabel;
  private JLabel goodIcon;
  private JLabel goodSignalLabel;

  private JPanel contentPane;
  private EmotivComposer parent;

  private ClassLoader loader = getClass().getClassLoader();

  public SignalMenu(EmotivComposer parent) {
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
    goodSignalPanel = new JPanel();
    goodSignalPanel.setBackground(Color.WHITE);
    goodSignalPanel.setBounds(0, 0, 200, 50);
    goodSignalPanel.setLayout(null);

    goodIcon = new JLabel(new ImageIcon(loader.getResource("strong.png")));
    goodIcon.setBounds(1, 1, 48, 48);
    goodIcon.addMouseListener(new EmoMouse(this));

    goodSignalLabel = new JLabel("Good Signal");
    goodSignalLabel.setBounds(50, 0, 150, 50);
    goodSignalLabel.setHorizontalAlignment(SwingConstants.CENTER);
    goodSignalLabel.setFont(new Font("Dialog", Font.BOLD, 16));
    goodSignalLabel.setAlignmentY(0.0f);
    goodSignalLabel.addMouseListener(new EmoMouse(this));

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
    weakIcon.addMouseListener(new EmoMouse(this));

    weakSignalLabel = new JLabel("Weak Signal");
    weakSignalLabel.setBounds(50, 0, 150, 50);
    weakSignalLabel.setAlignmentY(Component.TOP_ALIGNMENT);
    weakSignalLabel.setFont(new Font("Dialog", Font.BOLD, 16));
    weakSignalLabel.setHorizontalAlignment(SwingConstants.CENTER);
    weakSignalLabel.addMouseListener(new EmoMouse(this));

    weakSignalPanel.add(weakIcon);
    weakSignalPanel.add(weakSignalLabel);

    contentPane.add(goodSignalPanel);
    contentPane.add(separator);
    contentPane.add(weakSignalPanel);

    addMouseListener(new EmoMouse(this));
  }

  public void setVisibleFalse(Component signal) {
    System.out.println("Component: " + signal.getClass());

    if (!(signal instanceof SignalMenu) || !(signal instanceof MenuBarPanel))
      dispose();
  }

  public void triggerActionEvent(Component source) {
    String strItem = null;
    JLabel item = (JLabel) source;
    strItem = item.getText();
    if (strItem == null) {
      strItem = item.getIcon().toString();
    }
    System.out.println("Source: " + strItem);

    if (strItem.contains("strong") || strItem.contains("Good")) {
      MenuBarPanel.setIconImage("strong.png");
    } else {
      MenuBarPanel.setIconImage("weak.png");
    }

    dispose();
  }
}