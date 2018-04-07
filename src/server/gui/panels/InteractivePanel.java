package server.gui.panels;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import data.EmotivData;
import server.gui.EmotivComposer;
import server.gui.actions.ActionEvents;
import server.gui.actions.ItemEvents;
import server.sys.EmotivRandomizer;
import server.sys.WorkerThread;
import server.sys.observer.EmotivObserver;

/**
 * This class's purpose is to display the interactive tab section items
 * 
 * @author Vihar Bhatt
 * @author Cephas Armstrong-Mensah
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
  private JComboBox<String> playerComboBox;
  private ComboControl emoStateInterval;

  private EmotivRandomizer er;

  private static boolean isAutoResetChecked;
  private static WorkerThread worker;
  private static Thread workerThread;

  public InteractivePanel(EmotivRandomizer er) {
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

    playerComboBox = new JComboBox<String>();
    playerComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "0", "1" }));
    playerComboBox.setBounds(65, 10, 80, 30);

    emoStateInterval = new ComboControl(110, 0.5, true);
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

    workerThread = new Thread(worker);
    workerThread.start();

    er.sendButtonText(sendButton.getText(), emoStateInterval.getOutputText());

    // for eye section auto reset button
    if (EmotivComposer.getemoFacialPanel().ischckbxNewCheckBoxSelected()) {
      EmotivComposer.getemoFacialPanel().getEyeActive().doClick();
      EmotivComposer.getemoFacialPanel().seteyeActiveValue(0);
    }
  }

  private void setSendButtonText(String strText) {
    if (isAutoResetChecked) {
      if (strText.equalsIgnoreCase("Start")) {
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
      //
      // EmotivComposer.getemoFacialPanel().replaceRadioButton();
    } else {
      isAutoResetChecked = false;
      sendButton.setText("Send");
    }

    // replace a eye button with radiobutton
    if (autoResetCheckBox.isSelected()) {
      EmotivComposer.getemoFacialPanel().replaceRadioButton();
    } else {
      EmotivComposer.getemoFacialPanel().replacebackRadio();
    }
  }

  @Override
  public void updateAll(EmotivData data, double interval, String sendButtonText) {
    this.setSendButtonText(sendButtonText);
  }
}