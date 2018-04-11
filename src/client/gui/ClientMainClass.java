package client.gui;

/**
 * This class launches the client application
 * 
 * @author Nikita Bahl
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 09APRIL2018
 *
 */
public class ClientMainClass {

  public static void main(String[] args) {
    EmotivControlPanel frame = EmotivControlPanel.getInstance();
    frame.setVisible(true);
  }
}
