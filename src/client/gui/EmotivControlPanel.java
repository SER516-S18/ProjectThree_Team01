package client.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EmotivControlPanel extends JFrame {

  private static final long serialVersionUID = 8528760467775723790L;
  private JPanel contentPane;

  public static EmotivControlPanel clientInstance = null;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EmotivControlPanel frame = new EmotivControlPanel();
    frame.setVisible(true);
  }

  public EmotivControlPanel getInstance() {
    if (clientInstance == null) {
      clientInstance = new EmotivControlPanel();
    }
    return clientInstance;
  }

  /**
   * Create the frame.
   */
  private EmotivControlPanel() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);
  }

}
