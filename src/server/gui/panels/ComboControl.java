package server.gui.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import server.gui.actions.MouseEvents;

/**
 * This class is used as a general utility for a field needing an increment and
 * decrement with a text box
 * 
 * @author Cephas Armstrong-Mensah
 * @version 1.0
 * @since 29MAR2018
 *
 */

public class ComboControl extends JPanel {
  private static final long serialVersionUID = -7926485727775080081L;

  private JTextField outputTextBox;
  private JLabel incrementButton;
  private JLabel decrementButton;
  private JPanel panel;

  private double step;
  private boolean isFrequency;
  private int newWidth;

  /**
   * @param newWidth - the width for the entire panel
   * @param step - the next step size when button is clicked
   * @param isFrequency - determine if this field is for frequency
   */
  public ComboControl(int newWidth, double step, boolean isFrequency) {
    this.step = step;
    this.isFrequency = isFrequency;
    this.newWidth = newWidth;

    setLayout(null);
    this.setMinimumSize(new Dimension(70, 30));
    setBounds(0, 0, newWidth < 70 ? 70 : newWidth, 30);

    outputTextBox = new JTextField("0.0");
    outputTextBox.setBounds(0, 0, newWidth - 30, 30);
    add(outputTextBox);

    panel = new JPanel();
    panel.setBounds(newWidth - 28, 0, 28, 30);
    add(panel);
    panel.setLayout(null);

    incrementButton = new JLabel(Character.toString((char) 0x028C));
    incrementButton.setBounds(0, 0, 26, 15);
    incrementButton.setVerticalAlignment(SwingConstants.BOTTOM);
    incrementButton.setHorizontalTextPosition(SwingConstants.CENTER);
    incrementButton.setHorizontalAlignment(SwingConstants.CENTER);
    incrementButton.addMouseListener(new MouseEvents(this));

    decrementButton = new JLabel("v");
    decrementButton.setBounds(0, 15, 26, 15);
    decrementButton.setVerticalAlignment(SwingConstants.TOP);
    decrementButton.setHorizontalTextPosition(SwingConstants.CENTER);
    decrementButton.setHorizontalAlignment(SwingConstants.CENTER);
    decrementButton.addMouseListener(new MouseEvents(this));

    panel.add(decrementButton);
    panel.add(incrementButton);
  }

  private void formatDoubleFirst(double value) {
    DecimalFormat df = new DecimalFormat("0.00");
    this.outputTextBox.setText(df.format(value));
    this.outputTextBox.setForeground(Color.BLACK);
  }

  public void setOutputText(String txt) {
    this.outputTextBox.setText(txt);
  }

  public String getOutputText() {
    return outputTextBox.getText();
  }

  public void incrementOutputText() {
    try {
      double x = Double.parseDouble(outputTextBox.getText());
      double value = 0.0;
      if (!isFrequency && x + step > 1.0) {
        value = 1.0;
      } else {
        value = x + step;
      }

      formatDoubleFirst(value);
    } catch (NumberFormatException exception) {
      outputTextBox.setForeground(Color.RED);
      outputTextBox.selectAll();
    }
  }

  public void decrementOutputText() {
    try {
      double x = Double.parseDouble(outputTextBox.getText());

      double value = 0.00;
      if (!isFrequency && x - step > 0.0) {
        value = x - step;
      } else if (isFrequency) {
        value = 0.01;
        if (x - step > 0.01) {
          value = x - step;
        }
      }
      formatDoubleFirst(value);
    } catch (NumberFormatException exception) {
      outputTextBox.setForeground(Color.RED);
      outputTextBox.selectAll();
    }
  }

  public void showHideBorder(JLabel clickedButton, boolean needsBorder) {
    if (needsBorder) {
      clickedButton.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
    } else {
      clickedButton.setBorder(null);
    }
  }
}
