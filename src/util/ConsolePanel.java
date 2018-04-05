package util;

import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

/**
 * The ConsolePanel implements a JPanel to create a console. The console will be
 * able to display any message passed to it.
 * 
 * @author Karansher Bhangal
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 2018-02-17
 *
 */
public class ConsolePanel extends JPanel {

  private static final long serialVersionUID = -4828155067278962729L;
  private static JScrollPane consoleScrollPane;
  private static JLabel consoleMessageLabel;
  private static String completeMessage = "<html>";
  private static String messageDisplay;
  private static int textColor = 0;
  private int horizontalScrollValue = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
  private int verticalScrollValue = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
  private int labelVerticalAlignment = SwingConstants.TOP;
  private GridLayout layout = new GridLayout(1, 1);

  /**
   * This function is the constructor of the console. It generates the header and
   * message label of the JScrollPane. It also sets the properties of the
   * JScrollPane.
   * 
   */
  public ConsolePanel() {
    consoleScrollPane = new JScrollPane();
    consoleScrollPane.setHorizontalScrollBarPolicy(horizontalScrollValue);
    consoleScrollPane.setVerticalScrollBarPolicy(verticalScrollValue);

    consoleMessageLabel = new JLabel("");
    consoleMessageLabel.setVerticalAlignment(labelVerticalAlignment);
    consoleScrollPane.setViewportView(consoleMessageLabel);

    this.add(consoleScrollPane);
    this.setLayout(layout);
  }

  /**
   * This function is used to add new message in the console and update the
   * position of vertical scroll to the bottom.
   * 
   * @param message. This is the message that needs to be displayed on the
   *          console.
   * @return void.
   */
  public void updateText(String message) {
    completeMessage = completeMessage + addColor(message) + "<br/>";
    messageDisplay = completeMessage + "</html>";
    consoleMessageLabel.setText(messageDisplay);

    int ht = consoleMessageLabel.getSize().height;
    consoleScrollPane.getViewport().setViewPosition(new Point(0, ht));
  }

  /**
   * This function is used to clear the JLabel
   * 
   * @return void.
   */
  public void clearText() {
    completeMessage = "<html>";
    textColor = 0;
    consoleMessageLabel.setText("");
  }

  /**
   * This function is used to change the color of each line.
   * 
   * @param message. This is the message that needs to be displayed on the
   *          console.
   * @return message. Message with color attribute.
   */
  private String addColor(String message) {
    if (textColor == 1) {
      message = "<font color=blue>" + message + "</font>";
      textColor = 0;
    } else {
      message = "<font color=black>" + message + "</font>";
      textColor = 1;
    }
    return message;
  }

}
