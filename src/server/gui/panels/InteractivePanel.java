package server.gui.panels;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import server.gui.actions.ActionEvents;
import server.gui.actions.ItemEvents;
import server.sys.SubjectImplementation;
import server.sys.WorkerThread;
import server.sys.observer.EmotivObserver;
import server.sys.observer.PassedData;

/**
 * This class's purpose is to display the interactive tab section items
 * 
 * @author Vihar Bhatt
 * @author Cephas Armstrong-Mensah
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 02APR2018
 *
 */
public class InteractivePanel extends JPanel implements EmotivObserver {

  private static final long serialVersionUID = 8249772239761365173L;

  private JLabel playerLabel;
  private JLabel emoStateLabel;
  private JLabel secLabel;

  private JButton sendButton;
  private JCheckBox autoResetCheckBox;
  private JComboBox<Integer> playerComboBox;
  private ComboControl emoStateInterval;

  private SubjectImplementation er;

  private static boolean isAutoResetChecked;
  private static WorkerThread worker;
  public static Thread workerThread;

  public InteractivePanel(SubjectImplementation er) {
    setBounds(0, 0, 450, 90);
    setLayout(null);
    this.er = er;
    this.er.addToObserver(this);
    isAutoResetChecked = false;

    initialize();
  }

  private void initialize() {
    playerLabel = new JLabel("Player");
    playerLabel.setVerticalAlignment(SwingConstants.TOP);
    playerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    playerLabel.setHorizontalAlignment(SwingConstants.LEFT);
    playerLabel.setBounds(10, 10, 55, 30);

    emoStateLabel = new JLabel("<html>EmoState<br>Interval:</html>");
    emoStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
    emoStateLabel.setBounds(180, 10, 60, 30);

    secLabel = new JLabel("Sec");
    secLabel.setVerticalAlignment(SwingConstants.TOP);
    secLabel.setBounds(370, 15, 30, 30);

    sendButton = new JButton("Send");
    sendButton.setBounds(330, 55, 100, 30);
    sendButton.addActionListener(new ActionEvents(this));

    autoResetCheckBox = new JCheckBox("Auto Reset");
    autoResetCheckBox.setVerticalAlignment(SwingConstants.TOP);
    autoResetCheckBox.setBounds(170, 55, 115, 30);
    autoResetCheckBox.addItemListener(new ItemEvents(this));

    playerComboBox = new JComboBox<Integer>();
    playerComboBox.setModel(new DefaultComboBoxModel<Integer>(new Integer[] { 0, 1 }));
    playerComboBox.setBounds(65, 10, 80, 30);

    emoStateInterval = new ComboControl(er, 110, 0.5, true, "Frequency");
    emoStateInterval.setLocation(250, 10);
    emoStateInterval.setOutputText("0.25");

    add(secLabel);
    add(playerLabel);
    add(emoStateLabel);
    add(sendButton);
    add(autoResetCheckBox);
    add(playerComboBox);
    add(emoStateInterval);
  }

  private void setInteractiveFields(String text, boolean isAutoReset) {
    autoResetCheckBox.setEnabled(isAutoReset);
    sendButton.setText(text);
  }

  public void triggerStartStopSend() {
    if (worker == null)
      worker = new WorkerThread(er);

    er.sendButtonText(sendButton.getText(), emoStateInterval.getOutputText());
    workerThread = new Thread(worker);
    workerThread.start();
  }

  private void setSendButtonText(String strText) {
    if (isAutoResetChecked) {
      if (strText.equalsIgnoreCase("Start")) {
        setInteractiveFields("Stop", false);
      } else if (strText.equalsIgnoreCase("Suspend")) {
        setInteractiveFields("Stop", false);
      } else {
        setInteractiveFields("Start", true);
      }
    }
  }

  public void itemStateAction() {
    if (autoResetCheckBox.isSelected()) {
      isAutoResetChecked = true;
      sendButton.setText("Start");
    } else {
      isAutoResetChecked = false;
      sendButton.setText("Send");
    }

    er.setInteractiveReset(isAutoResetChecked);
  }

  @Override
  public void update(PassedData passedData) {
    setSendButtonText(passedData.buttonText);
  }
}