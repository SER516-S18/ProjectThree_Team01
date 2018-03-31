package client.gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class EmotivControlPanel extends JFrame {
  private static final long serialVersionUID = 8528760467775723790L;
  private JPanel contentPane;
  private JPanel facialExpressionPanel;

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
    setContentPane(contentPane);
    contentPane.setLayout(null);
    facialExpressionPanel = new JPanel();
    facialExpressionPanel.setLayout(null);
    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.setBounds(5, 5, 422, 243);
    contentPane.add(tabbedPane);
    tabbedPane.setName("Facial Expressions");
    tabbedPane.addTab("Facial Expressions", facialExpressionPanel);
    JPanel graphPanel = new JPanel();
    graphPanel.setBounds(199, 0, 218, 213);
    graphPanel.setBackground(Color.GRAY);
    facialExpressionPanel.add(graphPanel);
  }
}
