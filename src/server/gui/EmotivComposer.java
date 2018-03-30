package server.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.time.LocalTime;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerEndpoint;

import util.ConsolePanel;
import util.Constants;

public class EmotivComposer extends JFrame {

  private static final long serialVersionUID = 6196061116172281774L;
  private JPanel contentPane;
  private ConsolePanel consolePanel;
  private JTabbedPane tabbedPane;

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
    setBounds(100, 100, 450, 550);

    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    JMenu mnDropDown = new JMenu("Drop Down");
    menuBar.add(mnDropDown);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JPanel topSection = new JPanel();
    topSection.setBorder(new LineBorder(Color.RED));
    topSection.setBounds(2, 0, 444, 125);
    contentPane.add(topSection);

    tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
    topSection.add(tabbedPane);

    JPanel emoScript = new JPanel();
    tabbedPane.addTab("EMOSCRIPT", null, emoScript, null);
    emoScript.setLayout(null);

    JPanel interactive = new JPanel();
    tabbedPane.addTab("INTERACTIVE", null, interactive, null);
    interactive.setLayout(null);

    JPanel panel = new JPanel();
    panel.setBorder(new LineBorder(Color.RED));
    panel.setBounds(2, 150, 444, 338);
    contentPane.add(panel);

    consolePanel = new ConsolePanel();

    startServer();
  }

  private void startServer() {
    org.glassfish.tyrus.server.Server server = new org.glassfish.tyrus.server.Server("localhost",
        Constants.PORT, Constants.LINK, ServerEndpoint.class);

    try {
      server.start();
    } catch (DeploymentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Updating the Console to output status message
   * 
   * @param message
   */
  private void updateConsolePanel(String message) {
    consolePanel.updateText(message + "&emsp;&emsp&lt;" + LocalTime.now() + "&gt;");
  }
}
