package server.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.time.LocalTime;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerEndpoint;

import util.ConsolePanel;
import util.Constants;

public class EmotivComposer extends JFrame {

  private static final long serialVersionUID = 6196061116172281774L;
  private JPanel contentPane;
  private ConsolePanel consolePanel;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          EmotivComposer frame = new EmotivComposer();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        } 
      }
    });
  }

  /**
   * Create the frame.
   */
  public EmotivComposer() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);
    
    consolePanel = new ConsolePanel();
    
    startServer();
  }
  
  private void startServer() {
    org.glassfish.tyrus.server.Server server = new org.glassfish.tyrus.server.Server("localhost", Constants.PORT,
        Constants.LINK, ServerEndpoint.class);
    
    try {
      server.start();
    } catch (DeploymentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  /**
   * Updating the Console to output status message
   * @param message
   */
  private void updateConsolePanel(String message) {
    consolePanel.updateText(message + "&emsp;&emsp&lt;" + LocalTime.now() + "&gt;");
  }

}
