package server.gui.panels;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.EmotivData;
import server.gui.actions.ItemEvents;
import server.sys.SubjectImplementation;
import server.sys.observer.EmotivObserver;
import server.sys.observer.PassedData;

/**
 * This class's purpose is the upper section of the lower detection tab area
 * 
 * @author Cephas Armstrong-Mensah
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 07APR2018
 *
 */
public class TrainingResults extends JPanel implements EmotivObserver {

  private static final long serialVersionUID = -283118474254668985L;
  private JComboBox<String> mentalCommandsComboBox;
  private JComboBox<String> performanceComboBox;
  private ComboControl performanceAdjuster;
  private JLabel emoStateLabel;
  private JLabel performanceMetricsLabel;
  private JLabel mentalCommandsLabel;
  private SubjectImplementation er;

  public TrainingResults(SubjectImplementation er) {
    setBounds(0, 0, 170, 195);
    setLayout(null);
    this.er = er;
    this.er.addToObserver(this);
    initialize();
  }

  private void initialize() {
    mentalCommandsLabel = new JLabel("Mental Commands");
    mentalCommandsLabel.setBounds(5, 50, 150, 25);

    mentalCommandsComboBox = new JComboBox<String>();
    mentalCommandsComboBox
        .setModel(new DefaultComboBoxModel<String>(new String[] { "Not Enough Data" }));
    mentalCommandsComboBox.setBounds(5, 75, 105, 30);
    mentalCommandsComboBox.setEnabled(false);

    performanceComboBox = new JComboBox<String>();
    performanceComboBox.setModel(new DefaultComboBoxModel<String>(performanceItems()));
    performanceComboBox.setBounds(5, 150, 105, 30);
    performanceComboBox.addItemListener(new ItemEvents(this));

    emoStateLabel = new JLabel("TRAINING RESULTS");
    emoStateLabel.setBounds(5, 5, 150, 30);

    performanceMetricsLabel = new JLabel("Performance Metrics");
    performanceMetricsLabel.setBounds(5, 125, 160, 25);

    performanceAdjuster = new ComboControl(er, 70, 0.1, false, "Performance");
    performanceAdjuster.setLocation(110, 150);
    performanceAdjuster.setOutputText("0.0");

    add(performanceAdjuster);
    add(performanceMetricsLabel);
    add(performanceComboBox);
    add(emoStateLabel);
    add(mentalCommandsLabel);
    add(mentalCommandsComboBox);
  }

  private String[] performanceItems() {
    String[] performance = new String[6];
    int index = 0;

    performance[index++] = "Interest";
    performance[index++] = "Engagement";
    performance[index++] = "Relaxation";
    performance[index++] = "Focus";
    performance[index++] = "Stress";
    performance[index] = "Excitement";

    return performance;
  }

  private void updatePerformance(String key, double value) {
    EmotivData data = er.getEmotivData();
    data.resetPerformanceMetrics();

    if (key.equals("Interest")) {
      data.setInterest(value);
    } else if (key.equals("Engagement")) {
      data.setEngagement(value);
    } else if (key.equals("Relaxation")) {
      data.setRelaxation(value);
    } else if (key.equals("Focus")) {
      data.setFocus(value);
    } else if (key.equals("Stress")) {
      data.setStress(value);
    } else if (key.equals("Excitement")) {
      data.setExcitement(value);
    }

    er.updatePerformance(false);
  }

  public void performanceSelector() {
    String item = performanceComboBox.getSelectedItem().toString();
    String lowerbuttonValue = performanceAdjuster.getOutputText();
    double lowerfaceValue = Double.parseDouble(lowerbuttonValue);
    updatePerformance(item, lowerfaceValue);
  }

  @Override
  public void update(PassedData passedData) {
    if (passedData.performance) {
      performanceSelector();
    }
  }
}