package server.gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.websocket.DeploymentException;

import org.glassfish.tyrus.server.Server;

import server.gui.panels.EmoLogPanel;
import server.gui.panels.EmoStatePanel;
import server.gui.panels.FacialPanel;
import server.gui.panels.InteractivePanel;
import server.gui.panels.MenuBarPanel;
import server.sys.ServerThread;
import server.sys.ServerWebSocket;
import server.sys.WorkerThread;
import util.Constants;

/**
 * The purpose of this class is to provide the GUI handler for the server and
 * serves as the main interaction between the user, server and client.
 * 
 * @author Cephas Armstrong-Mensah
 *
 */
public class EmotivComposer extends JFrame implements WindowListener {

  private static final long serialVersionUID = 6196061116172281774L;
  private static EmotivComposer instance = null;
  private static WorkerThread worker;
  private static Thread workerThread;
  protected static boolean isStarted = false;

  private JTabbedPane tabbedPane;
  private JTabbedPane lowerTabbedPane;
  private JPanel contentPane;
  private JPanel startPanel;
  private JPanel interactive;
  private JPanel emostate;
  private JPanel lowerPanel;
  private JPanel qualityPanel;
  private JPanel detectionPanel;

  private static InteractivePanel ip;
  private static EmoStatePanel emoStatePanel;
  private static FacialPanel emoFacialPanel;
  private static MenuBarPanel menuBarPanel;
  private static EmoLogPanel emoLogPanel;

  public static boolean isAutoResetChecked = false;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EmotivComposer frame;
    try {
      for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          frame = EmotivComposer.getInstance();
          frame.setVisible(true);
          break;
        }
      }
    } catch (Exception e) {
      frame = EmotivComposer.getInstance();
      frame.setVisible(true);
    }
  }

  public static EmotivComposer getInstance() {
    if (instance == null) {
      instance = new EmotivComposer();
    }
    return instance;
  };

  private EmotivComposer() {
    addWindowListener(this);
    setTitle("Emotiv Composer Project 3");
    setBounds(100, 100, 450, 800);

    contentPane = new JPanel();
    contentPane.setBackground(Constants.PEACH);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    menuBarPanel = new MenuBarPanel();
    menuBarPanel.setBounds(0, 0, 440, 50);
    menuBarPanel.setLayout(null);
    contentPane.add(menuBarPanel);

    startPanel = new JPanel();
    startPanel.setBounds(2, 50, 440, 120);
    contentPane.add(startPanel);
    startPanel.setLayout(null);

    tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
    tabbedPane.setBounds(0, 0, 440, 120);
    startPanel.add(tabbedPane);

    emostate = new JPanel();
    tabbedPane.addTab("EMOSCRIPT", null, emostate, null);
    emostate.setLayout(null);

    interactive = new JPanel();
    tabbedPane.addTab("INTERACTIVE", null, interactive, null);
    ip = new InteractivePanel();
    ip.setBounds(0, 0, 440, 90);
    interactive.add(ip);
    interactive.setLayout(null);

    lowerPanel = new JPanel();
    lowerPanel.setBounds(2, 205, 440, 565);
    contentPane.add(lowerPanel);
    lowerPanel.setLayout(null);

    lowerTabbedPane = new JTabbedPane(JTabbedPane.TOP);
    lowerTabbedPane.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
    lowerTabbedPane.setBounds(0, 0, 440, 565);
    lowerPanel.add(lowerTabbedPane);

    qualityPanel = new JPanel();
    qualityPanel.setLayout(null);

    detectionPanel = new JPanel();
    detectionPanel.setLayout(null);

    emoStatePanel = new EmoStatePanel();

    emoFacialPanel = new FacialPanel();
    emoFacialPanel.setBounds(0, 175, 440, 150);

    emoLogPanel = new EmoLogPanel();
    emoLogPanel.setBounds(0, 325, 440, 205);

    detectionPanel.add(emoFacialPanel);
    detectionPanel.add(emoStatePanel);
    detectionPanel.add(emoLogPanel);

    lowerTabbedPane.addTab("Contact Quality", null, qualityPanel, null);
    lowerTabbedPane.addTab("Detection", null, detectionPanel, null);

    setResizable(false);
    tabbedPane.setSelectedIndex(1);
    lowerTabbedPane.setSelectedIndex(1);
    startServer();
  }

  /**
   * This starts the tyrus server using the websocket class ServerWebSocket
   */
  protected void startServer() {
    Server server = new Server(Constants.URI, Constants.PORT, Constants.LINK, ServerWebSocket.class);
    try {
      server.start();
      System.out.println("Server started successfully...");
      isStarted = true;
      new Thread(new ServerThread(server)).start();
    } catch (DeploymentException e) {
      throw new RuntimeException(e);
    }
  }

  public static void handleStartStopSend(String strText) {
    if (worker == null)
      worker = new WorkerThread(emoStatePanel.getTimeTrackerLabel(), emoLogPanel.getConsolePanel());

    worker.setButtonStatus(strText);
    worker.setInterval(Double.parseDouble(ip.getOutputText()));

    if (isAutoResetChecked) {
      if (strText.equalsIgnoreCase("Start")) {
        ip.setInteractiveFields("Stop", false);
      } else {
        ip.setInteractiveFields("Start", true);
      }
    }

    workerThread = new Thread(worker);
    workerThread.start();
  }

  public void setTimeTracker(String str) {
    emoStatePanel.setTimeTrackerLabelText(str);
  }

  private synchronized void closeThread() {
    notify();
  }

  @Override
  public void windowOpened(WindowEvent e) {
  }

  @Override
  public void windowClosing(WindowEvent e) {
    closeThread();
    System.out.println("Server is closing...");
    System.exit(0);
  }

  @Override
  public void windowClosed(WindowEvent e) {
  }

  @Override
  public void windowIconified(WindowEvent e) {
  }

  @Override
  public void windowDeiconified(WindowEvent e) {
  }

  @Override
  public void windowActivated(WindowEvent e) {
  }

  @Override
  public void windowDeactivated(WindowEvent e) {
  }
}
