package server.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerEndpoint;

public class EmotivComposer extends JFrame {

  private static final long serialVersionUID = 6196061116172281774L;
  private JPanel contentPane;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    org.glassfish.tyrus.server.Server server = new org.glassfish.tyrus.server.Server("localhost", 10001,
        "/pro3", ServerEndpoint.class);
    try {
      server.start();
      System.out.println("Press any key to stop the server..");
      new Scanner(System.in).nextLine();
    } catch (DeploymentException e) {
      throw new RuntimeException(e);
    } finally {
      server.stop();
    }

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
  }

}
