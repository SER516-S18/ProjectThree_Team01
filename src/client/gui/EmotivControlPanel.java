package client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.websocket.DeploymentException;

import org.glassfish.tyrus.client.ClientManager;
import org.jfree.chart.ChartPanel;

import client.sys.ClientSubject;
import client.sys.ClientThread;
import client.sys.ClientWebSocket;
import client.sys.ClientWebSocketTest;
import data.EmotivData;
import server.sys.ServerThread;
import util.ConstantsTest;

import interfaces.*;

public class EmotivControlPanel extends JFrame implements ClientObserver{
  private static final long serialVersionUID = 8528760467775723790L;

  private JPanel contentPane;
  private JPanel facialExpressionPanel;
  private String uri = "localhost";
  private DisplayGraph displayGraph ;
  public static EmotivControlPanel clientInstance = null;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EmotivControlPanel frame = EmotivControlPanel.getInstance();
    frame.setVisible(true);
  //frame.setSize(1000,1000);
  }

  public static EmotivControlPanel getInstance() {
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
    setBounds(100, 100, 982, 919);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    facialExpressionPanel = new JPanel();
    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.setBounds(5, 13,959, 992);
    contentPane.add(tabbedPane);
    tabbedPane.setName("Facial Expressions");
    tabbedPane.addTab("Facial Expressions", facialExpressionPanel);
    facialExpressionPanel.setLayout(null);
    JPanel graphPanel = new JPanel();
    graphPanel.setBounds(456, 0, 500, 841);
   // graphPanel.setBackground(Color.GRAY);
    facialExpressionPanel.add(graphPanel); 
    
    displayGraph= new DisplayGraph();
    displayGraph.chartPanel= new ChartPanel(displayGraph.graph);
    
    displayGraph.chartPanel.setLocation(12, 26);
    displayGraph.chartPanel.setSize(new Dimension(500,800));
    facialExpressionPanel.setLayout(null);

    graphPanel.add(displayGraph.chartPanel);
    graphPanel.setLayout(null);

    // remove later
    ConnectToServer();
  }

  private void ConnectToServer() {
    ClientSubject.getInstance().addObserver(this);
    new Thread(new ClientThread(uri)).start();
  }

  @Override
  public void notifyObserver(EmotivData data) {
    // TODO Auto-generated method stub
    System.out.println("notified");
    
  }
}
