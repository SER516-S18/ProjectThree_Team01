package server.gui;

import java.time.LocalTime;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.websocket.DeploymentException;

import org.glassfish.tyrus.server.Server;

import server.gui.actions.WindowEvents;
import server.gui.panels.HamburgerMenu;
import server.gui.panels.LogPanel;
import server.gui.panels.MenuBarPanel;
import server.gui.panels.SignalMenu;
import server.gui.tabs.LowerTabbedPane;
import server.gui.tabs.UpperTabbedPane;
import server.sys.ServerThread;
import server.sys.ServerWebSocket;
import server.sys.SubjectImplementation;
import util.Constants;

/**
 * The purpose of this class is to provide the GUI handler for the server and serves as the
 * main interaction between the user, server and client.
 * 
 * @author Cephas Armstrong-Mensah
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 28MAR2018
 *
 */
public class EmotivComposer extends JFrame {

  private static final long serialVersionUID = 6196061116172281774L;
  private static EmotivComposer instance = null;

  private UpperTabbedPane upperTabbedPane;
  private LowerTabbedPane lowerTabbedPane;
  private JPanel contentPane;

  private static MenuBarPanel menuBarPanel;
  private static HamburgerMenu exitMenu;
  private static SignalMenu signalMenu;
  private static SubjectImplementation er;

  public static EmotivComposer getInstance() {
    if (instance == null) {
      instance = new EmotivComposer();
      instance.setVisible(true);
    }
    return instance;
  };

  private EmotivComposer() {
    addWindowListener(new WindowEvents(this));
    setTitle("Emotiv Composer Project 3");
    setBounds(100, 100, 450, 800);
    setResizable(false);

    contentPane = new JPanel();
    contentPane.setBounds(0, 0, 450, 800);
    contentPane.setBackground(Constants.PEACH);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(null);

    setContentPane(contentPane);
    er = new SubjectImplementation();

    initialize();
  }

  private void initialize() {
    menuBarPanel = new MenuBarPanel();
    menuBarPanel.setSize(450, 50);
    menuBarPanel.setSize(450, 50);
    menuBarPanel.setBounds(0, 0, 450, 50);
    menuBarPanel.setLayout(null);

    upperTabbedPane = new UpperTabbedPane(er);
    upperTabbedPane.setBounds(2, 50, 443, 120);

    lowerTabbedPane = new LowerTabbedPane(er);
    lowerTabbedPane.setBounds(2, 205, 443, 560);

    contentPane.add(menuBarPanel);
    contentPane.add(upperTabbedPane);
    contentPane.add(lowerTabbedPane);

    startServer();
  }

  /**
   * This starts the tyrus server using the websocket class ServerWebSocket
   */
  private void startServer() {
    Server server = new Server(Constants.URI, Constants.PORT, Constants.LINK, ServerWebSocket.class);
    try {
      server.start();
      updateConsolePanel("Server started successfully...", false);
      new Thread(new ServerThread(server)).start();
    } catch (DeploymentException e) {
      updateConsolePanel(e.getLocalizedMessage(), true);
      throw new RuntimeException(e);
    }
  }

  private void updateConsolePanel(String message, boolean isError) {
    if (isError) {
      LogPanel.getConsolePanel().updateError(message + "&emsp;&emsp&lt;" + LocalTime.now() + "&gt;");
    } else {
      LogPanel.getConsolePanel().updateText(message + "&emsp;&emsp&lt;" + LocalTime.now() + "&gt;");
    }
  }

  public synchronized void closeThread() {
    notify();
    updateConsolePanel("Server is closing...", true);
    System.exit(0);
  }

  public static void showMenuItems() {
    exitMenu = new HamburgerMenu(instance);
    exitMenu.setSize(200, 101);
    exitMenu.setLocation(instance.getX() + 5, instance.getY() + 70);
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
