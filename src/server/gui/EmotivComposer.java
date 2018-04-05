package server.gui;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.websocket.DeploymentException;

import org.glassfish.tyrus.server.Server;

import server.gui.actions.EmoWindow;
import server.gui.panels.EmoLogPanel;
import server.gui.panels.EmoStatePanel;
import server.gui.panels.FacialPanel;
import server.gui.panels.HamburgerMenu;
import server.gui.panels.InteractivePanel;
import server.gui.panels.MenuBarPanel;
import server.gui.panels.SignalMenu;
import server.sys.ServerThread;
import server.sys.ServerWebSocket;
import server.sys.WorkerThread;
import util.Constants;

/**
 * The purpose of this class is to provide the GUI handler for the server and serves as
 * the main interaction between the user, server and client.
 * 
 * @author Cephas Armstrong-Mensah
 * @version 1.0
 * @since 28MAR2018
 *
 */
public class EmotivComposer extends JFrame {

  private static final long serialVersionUID = 6196061116172281774L;
  private static EmotivComposer instance = null;
  private static WorkerThread worker;
  private static Thread workerThread;
  protected static boolean isStarted = false;

  private JTabbedPane tabbedPane;
  private JTabbedPane lowerTabbedPane;
  private JPanel contentPane;
  private JPanel interactive;
  private JPanel emostate;
  private JPanel lowerPanel;
  private JPanel qualityPanel;
  private JPanel detectionPanel;

  private static InteractivePanel interactivePanel;
  private static EmoStatePanel emoStatePanel;
  private static FacialPanel emoFacialPanel;
  private static MenuBarPanel menuBarPanel;
  private static EmoLogPanel emoLogPanel;
  private static HamburgerMenu exitMenu;
  private static SignalMenu signalMenu;
  private static JPanel startPanel;

  public static boolean isAutoResetChecked = false;

  public static EmotivComposer getInstance() {
    if (instance == null) {
      instance = new EmotivComposer();
    }
    return instance;
  };

  private EmotivComposer() {
    addWindowListener(new EmoWindow(this));
    setTitle("Emotiv Composer Project 3");
    setBounds(100, 100, 450, 800);

    contentPane = new JPanel();
    contentPane.setBounds(0, 0, 450, 800);
    contentPane.setBackground(Constants.PEACH);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(null);
    setContentPane(contentPane);
    initialize();
  }

  private void initialize() {
    menuBarPanel = new MenuBarPanel();
    startPanel = new JPanel();

    tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
    tabbedPane.setBounds(0, 0, 450, 120);

    lowerTabbedPane = new JTabbedPane(JTabbedPane.TOP);
    lowerTabbedPane.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
    lowerTabbedPane.setBounds(0, 0, 443, 555);

    emostate = new JPanel();
    emoFacialPanel = new FacialPanel();
    emoStatePanel = new EmoStatePanel();
    emoLogPanel = new EmoLogPanel();

    menuBarPanel.setSize(450, 50);
    menuBarPanel.setSize(450, 50);
    menuBarPanel.setBounds(0, 0, 450, 50);
    menuBarPanel.setLayout(null);

    startPanel.setBounds(2, 50, 443, 120);
    startPanel.setLayout(null);

    startPanel.add(tabbedPane);

    tabbedPane.addTab("EMOSCRIPT", null, emostate, null);
    emostate.setLayout(null);

    interactive = new JPanel();
    tabbedPane.addTab("INTERACTIVE", null, interactive, null);

    interactivePanel = new InteractivePanel();
    interactivePanel.setSize(444, 90);
    interactivePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    interactivePanel.setAlignmentY(Component.TOP_ALIGNMENT);
    interactivePanel.setSize(449, 90);
    interactivePanel.setBounds(0, 0, 444, 90);

    interactive.add(interactivePanel);
    interactive.setLayout(null);

    lowerPanel = new JPanel();
    lowerPanel.setBounds(2, 205, 443, 560);
    lowerPanel.setLayout(null);

    lowerPanel.add(lowerTabbedPane);

    qualityPanel = new JPanel();
    qualityPanel.setLayout(null);

    detectionPanel = new JPanel();
    detectionPanel.setLayout(null);

    emoStatePanel.setSize(440, 175);

    emoFacialPanel.setSize(443, 150);
    emoFacialPanel.setSize(448, 150);
    emoFacialPanel.setBounds(0, 175, 440, 150);

    emoLogPanel.setSize(443, 200);
    emoLogPanel.setSize(440, 210);
    emoLogPanel.setSize(443, 210);
    emoLogPanel.setSize(443, 210);
    emoLogPanel.setBounds(0, 325, 443, 200);

    detectionPanel.add(emoFacialPanel);
    detectionPanel.add(emoStatePanel);
    detectionPanel.add(emoLogPanel);

    lowerTabbedPane.addTab("Contact Quality", null, qualityPanel, null);
    lowerTabbedPane.addTab("Detection", null, detectionPanel, null);

    setResizable(false);
    tabbedPane.setSelectedIndex(1);
    lowerTabbedPane.setSelectedIndex(1);

    contentPane.add(lowerPanel);
    contentPane.add(startPanel);
    contentPane.add(menuBarPanel);

    startServer();
  }

  /**
   * This starts the tyrus server using the websocket class ServerWebSocket
   */
  private boolean startServer() {
    Server server = new Server(Constants.URI, Constants.PORT, Constants.LINK, ServerWebSocket.class);
    try {
      server.start();
      System.out.println("Server started successfully...");
      isStarted = true;
      new Thread(new ServerThread(server)).start();
    } catch (DeploymentException e) {
      throw new RuntimeException(e);
    }
    return isStarted;
  }

  public static void handleStartStopSend(String strText) {
    if (worker == null)
      worker = new WorkerThread(emoStatePanel.getTimeTrackerLabel(), emoLogPanel.getConsolePanel());

    worker.setButtonStatus(strText);
    worker.setInterval(Double.parseDouble(interactivePanel.getOutputText()));

    System.out.println("Getting here");

    if (isAutoResetChecked) {
      if (strText.equalsIgnoreCase("Start")) {
        interactivePanel.setInteractiveFields("Stop", false);
      } else {
        interactivePanel.setInteractiveFields("Start", true);
      }
    }

    workerThread = new Thread(worker);
    workerThread.start();
  }

  public void setTimeTracker(String str) {
    emoStatePanel.setTimeTrackerLabelText(str);
  }

  public synchronized void closeThread() {
    notify();
    System.out.println("Server is closing...");
    System.exit(0);
  }

  public static void showMenuItems() {
    exitMenu = new HamburgerMenu(instance);
    exitMenu.setSize(200, 101);
    exitMenu.setLocation(instance.getX() + 5, instance.getY() + 75);
    exitMenu.setVisible(true);
  }

  public static HamburgerMenu getExitMenu() {
    return exitMenu;
  }

  public static SignalMenu getSignalMenu() {
    return signalMenu;
  }

  public static void showSignalItems() {
    signalMenu = new SignalMenu(instance);
    signalMenu.setSize(200, 101);
    signalMenu.setLocation(instance.getX() + 250, instance.getY() + 75);
    signalMenu.setVisible(true);
  }
}
