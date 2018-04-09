package server.gui.panels;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.ConsolePanel;

/**
 * This class's purpose is the upper section of the lower detection tab area
 * 
 * @author Cephas Armstrong-Mensah
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 07APR2018
 *
 */
public class TrainingResults extends JPanel {

  private static final long serialVersionUID = -283118474254668985L;
  private JLabel mentalCommandsLabel;
  private JComboBox<String> mentalCommandsComboBox;
  private JComboBox<String> perfomanceMetricsComboBox;
  private ConsolePanel consolePanel;
  private JLabel emoStateLabel;
  private JLabel performanceMetricsLabel;

  public TrainingResults() {
    setBounds(0, 0, 170, 195);
    setLayout(null);
    initialize();
  }

  private void initialize() {
    String comboData[] = new String[] { "OK", "Not Enough Data" };

    mentalCommandsLabel = new JLabel("Mental Commands");
    mentalCommandsLabel.setBounds(10, 50, 150, 25);

    mentalCommandsComboBox = new JComboBox<String>();
    mentalCommandsComboBox.setModel(new DefaultComboBoxModel<String>(comboData));
    mentalCommandsComboBox.setBounds(10, 75, 120, 30);

    perfomanceMetricsComboBox = new JComboBox<String>();
    perfomanceMetricsComboBox.setModel(new DefaultComboBoxModel<String>(comboData));
    perfomanceMetricsComboBox.setBounds(10, 150, 120, 30);

    consolePanel = new ConsolePanel();
    consolePanel.setBounds(5, 30, 430, 125);

    emoStateLabel = new JLabel("TRAINING RESULTS");
    emoStateLabel.setBounds(5, 5, 150, 30);
    add(emoStateLabel);
    add(mentalCommandsLabel);
    add(mentalCommandsComboBox);

    performanceMetricsLabel = new JLabel("Performance Metrics");
    performanceMetricsLabel.setBounds(10, 125, 160, 25);
    add(performanceMetricsLabel);
    add(perfomanceMetricsComboBox);
  }
}