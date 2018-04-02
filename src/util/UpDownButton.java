package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

/**
 * This class is used as a general utility for a field needing an increment and
 * decrement with a text box
 * 
 * @author Cephas Armstrong-Mensah
 *
 */

public class UpDownButton extends JPanel {
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
  public UpDownButton(int newWidth, double step, boolean isFrequency) {
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
    incrementButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        try {
          double x = Double.parseDouble(outputTextBox.getText());
          double value = 0.1;
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

      @Override
      public void mouseEntered(MouseEvent e) {
        incrementButton.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
      }

      @Override
      public void mouseExited(MouseEvent e) {
        incrementButton.setBorder(null);
      }
    });
    incrementButton.setBounds(0, 0, 26, 15);
    panel.add(incrementButton);
    incrementButton.setVerticalAlignment(SwingConstants.BOTTOM);
    incrementButton.setHorizontalTextPosition(SwingConstants.CENTER);
    incrementButton.setHorizontalAlignment(SwingConstants.CENTER);

    decrementButton = new JLabel("v");
    decrementButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        try {
          double x = Double.parseDouble(outputTextBox.getText());

          double value = 0.01;
          if (x - step > 0.0) {
            value = x - step;
          }

          formatDoubleFirst(value);
        } catch (NumberFormatException exception) {
          outputTextBox.setForeground(Color.RED);
          outputTextBox.selectAll();
        }
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        decrementButton.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
      }

      @Override
      public void mouseExited(MouseEvent e) {
        decrementButton.setBorder(null);
      }
    });
    decrementButton.setBounds(0, 15, 26, 15);
    panel.add(decrementButton);
    decrementButton.setVerticalAlignment(SwingConstants.TOP);
    decrementButton.setHorizontalTextPosition(SwingConstants.CENTER);
    decrementButton.setHorizontalAlignment(SwingConstants.CENTER);
  }

  private void formatDoubleFirst(double value) {
    DecimalFormat df = new DecimalFormat("0.00");
    this.outputTextBox.setText(df.format(value));
    this.outputTextBox.setForeground(Color.BLACK);
  }

  public void setOutputText(String txt) {
    this.outputTextBox.setText(txt);
  }

  public String getText() {
    return outputTextBox.getText();
  }
}
