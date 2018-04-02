package server.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalTime;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.websocket.DeploymentException;

import org.glassfish.tyrus.server.Server;

import server.sys.ServerThread;
import server.sys.ServerWebSocket;
import server.sys.WorkerThread;
import util.ConsolePanel;
import util.Constants;
import util.UpDownButton;

public class EmotivComposer extends JFrame implements WindowListener {

  private static final long serialVersionUID = 6196061116172281774L;
  
  private static final String URI = "localhost";
  private static EmotivComposer instance = null;

  private JPanel contentPane;
  private StartPanel startPanel;
  private MenuBarPanel menuBarPanel;
  private LowerPanel lowerPanel;
 
  private ConsolePanel consolePanel;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EmotivComposer frame = EmotivComposer.getInstance();
    frame.setVisible(true);
  }

  public static EmotivComposer getInstance() {
    if (instance == null) {
      instance = new EmotivComposer();
    }
    return instance;
  };

  /**
   * Create the frame.
   */
  private EmotivComposer() {
    addWindowListener(this);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Emotiv Composer Project 3");
    setBounds(100, 100, 450, 800);

    contentPane = new JPanel();
    contentPane.setBackground(Constants.PEACH);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(null);
    setContentPane(contentPane);
    

    startPanel = new StartPanel(444,125);
    contentPane.add(startPanel);

    menuBarPanel = new MenuBarPanel(444,50);
    contentPane.add(menuBarPanel);

    lowerPanel = new LowerPanel(444,550);
    contentPane.add(lowerPanel);

    setResizable(false);
    
   
    
    startServer();
  }

  private void startServer() {
    Server server = new Server(URI, Constants.PORT, Constants.LINK, ServerWebSocket.class);
    try {
      server.start();
      System.out.println("Server started successfully...");
      new Thread(new ServerThread(server)).start();
    } catch (DeploymentException e) {
      throw new RuntimeException(e);
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

 

   
 
 

  @Override
  public void windowOpened(WindowEvent e) {
    // TODO Auto-generated method stub
    System.out.println("Opened");

  }

  @Override
  public void windowClosing(WindowEvent e) {
    // TODO Auto-generated method stub
    System.out.println("Closing");
    ServerThread.isClosing = true;
    System.exit(0);
  }

  @Override
  public void windowClosed(WindowEvent e) {
    // TODO Auto-generated method stub
    System.out.println("closed");
    ServerThread.isClosing = true;
    System.exit(0);
  }

  @Override
  public void windowIconified(WindowEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public void windowDeiconified(WindowEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void windowActivated(WindowEvent e) {
    // TODO Auto-generated method stub
    System.out.println("Activated");

  }

  @Override
  public void windowDeactivated(WindowEvent e) {
    // TODO Auto-generated method stub
    System.out.println("Deactivated");
  }
}
