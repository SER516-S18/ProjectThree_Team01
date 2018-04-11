package client.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartPanel;
import client.gui.actions.ClientWindowEvents;
import client.gui.panels.PerformanceMetricPanel;
import client.sys.ClientSubject;
import data.EmotivData;
import interfaces.ClientObserver;

/*
 * This is the class that contains the main and launches the entire application
 * 
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 2018-04-04
 *
 */

public class EmotivControlPanel extends JFrame implements ClientObserver {
  private static final long serialVersionUID = 8528760467775723790L;
  private JPanel contentPane;
  private JPanel facialExpressionPanel;
  private DisplayGraph displayGraph;
  public static EmotivControlPanel clientInstance = null;
  private FacePanel facePanel = null;
  private PerformanceMetricPanel performanceMetric;

  private JTabbedPane tabbedPane;
  private JPanel graphPanel;
  private ClientHamburgerMenu menu;

  private volatile boolean isClosing = false;
  private JLabel signalLabel;
  private JLabel timerLabel, timerValue;
  private ClassLoader loader = getClass().getClassLoader();

  /**
   * To implement Singleton Instance of EmotivControlPanel
   * 
   * @return singleton Instance of the class
   */
  public static EmotivControlPanel getInstance() {
    if (clientInstance == null) {
      clientInstance = new EmotivControlPanel();
    }
    return clientInstance;
  }

  private EmotivControlPanel() {

	this.setTitle("Emotiv Xavier Control Panel");
    addWindowListener(new ClientWindowEvents(this));
    setBounds(100, 100, 982, 650);
    menu = new ClientHamburgerMenu();
    setJMenuBar(menu);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);

    contentPane.setLayout(null);
    facialExpressionPanel = new JPanel();
    tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.setBounds(5, 30, 959, 992);
    tabbedPane.setName("Facial Expressions");
    tabbedPane.addTab("Facial Expressions", facialExpressionPanel);
    facialExpressionPanel.setLayout(null);
    graphPanel = new JPanel();
    graphPanel.setBounds(462, 0, 480, 790);
    facialExpressionPanel.add(graphPanel);

    facePanel = new FacePanel();
    facePanel.setLocation(12, 0);
    facePanel.setSize(438, 790);
    facePanel.setBackground(Color.lightGray);
    facialExpressionPanel.add(facePanel);

    displayGraph = new DisplayGraph();

    facialExpressionPanel.setLayout(null);

    ChartPanel chartPanelObject = displayGraph.getChartPanel();
    chartPanelObject = new ChartPanel(displayGraph.getGraph());
    chartPanelObject.setSize(new Dimension(480, 500));
    chartPanelObject.setOpaque(true);
    chartPanelObject.setBackground(Color.lightGray);
    displayGraph.getGraph().getPlot().setBackgroundPaint(Color.gray);
    displayGraph.getGraph().setBackgroundPaint(Color.lightGray);
    graphPanel.setBackground(Color.lightGray);
    graphPanel.add(chartPanelObject);
    graphPanel.setLayout(null);

    ClientSubject.getInstance().addObserver(this);

    performanceMetric = new PerformanceMetricPanel();
    performanceMetric.setBounds(-12, 0, 971, 992);

    signalLabel = new JLabel(new ImageIcon(loader.getResource("weak.png")));
    signalLabel.setBounds(910, 0, 48, 48);
    contentPane.add(signalLabel);

    timerLabel = new JLabel();
    ImageIcon timerIcon = new ImageIcon("img/icons8-timer-19.png");
    timerLabel.setIcon(timerIcon);
    timerLabel.setBounds(800, 0, 50, 50);
    contentPane.add(timerLabel);

    timerValue = new JLabel();
    timerValue.setFont(new Font("Lucida Grande", Font.BOLD, 17));
    timerValue.setText("0");
    timerValue.setBounds(830, 0, 80, 50);
    timerValue.setForeground(Color.LIGHT_GRAY);
    contentPane.add(timerValue);
    timerValue.setVisible(false);

    contentPane.add(tabbedPane);
    contentPane.add(performanceMetric);
    performanceMetric.setVisible(false);
  }

  /**
   * Panel to Show the performance metric graph
   */
  public void showPerformanceMetric() {
    tabbedPane.setVisible(false);
    performanceMetric.setVisible(true);
    this.repaint();
  }

  /**
   * To display the graph that plots the values and display facial expressions.
   */
  public void showFacialGraph() {
    tabbedPane.setVisible(true);
    performanceMetric.setVisible(false);
    this.repaint();
  }

  /**
   * Notifies when the client application is closing
   * 
   * @return true when client is closing and false otherwise
   */
  public boolean getIsClosing() {
    return isClosing;
  }
  
  public void setIsClosing(boolean val) {
	  isClosing = val;
  }
  /**
   * Update the subscribed observers (display graph, face panel and performanceMetric panel
   * and pass the data received)
   */
  @Override
  public void updateObserver(EmotivData data) {
    System.out.println(data.getBlink());
    displayGraph.updateGraph(data);
    performanceMetric.performanceGraph.updateGraph(data);
    if (facePanel != null) {
      facePanel.updateFace(data);
    }
    updateTimerValue(data);
  }

  /**
   * @param connected - Updates the icon for signal label when client connects to the server
   */
  public void updateSignalLabel(boolean connected) {
    if (connected) {
      signalLabel.setIcon(new ImageIcon(loader.getResource("strong.png")));
      timerValue.setVisible(true);
    } else {
      signalLabel.setIcon(new ImageIcon(loader.getResource("weak.png")));
    }
  }

  /**
   * @param data - gets the data from server for the interval values on the Client panel
   */
  private void updateTimerValue(EmotivData data) {
	DecimalFormat dc = new DecimalFormat("#.00"); 
	timerValue.setText(dc.format(data.getTimer()));
    repaint();
  }
}
