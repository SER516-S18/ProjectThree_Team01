package client.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;

import client.sys.ClientSubject;
import data.EmotivData;
import interfaces.ClientObserver;

public class EmotivControlPanel extends JFrame implements ClientObserver {
  private static final long serialVersionUID = 8528760467775723790L;

  private JPanel contentPane;
  private JPanel facialExpressionPanel;
  private String uri = "localhost";
  private DisplayGraph displayGraph;
  public static EmotivControlPanel clientInstance = null;
  private FacePanel facePanel = null;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EmotivControlPanel frame = EmotivControlPanel.getInstance();
    frame.setVisible(true);
    // frame.setSize(1000,1000);
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
    ClientHamburgerMenu menu = new ClientHamburgerMenu();
    setJMenuBar(menu);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    facialExpressionPanel = new JPanel();
    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.setBounds(5, 13, 959, 992);
    contentPane.add(tabbedPane);
    tabbedPane.setName("Facial Expressions");
    tabbedPane.addTab("Facial Expressions", facialExpressionPanel);
    facialExpressionPanel.setLayout(null);
    JPanel graphPanel = new JPanel();
    graphPanel.setBounds(456, 0, 500, 841);
    // graphPanel.setBackground(Color.GRAY);
    facialExpressionPanel.add(graphPanel);

    facePanel = new FacePanel();
    facePanel.setLocation(0, 200);
    facePanel.setSize(450, 384);
    facialExpressionPanel.add(facePanel);

    displayGraph = new DisplayGraph();
    displayGraph.chartPanel = new ChartPanel(displayGraph.graph);

    displayGraph.chartPanel.setLocation(12, 26);
    displayGraph.chartPanel.setSize(new Dimension(500, 500));
    facialExpressionPanel.setLayout(null);

    graphPanel.add(displayGraph.chartPanel);
    graphPanel.setLayout(null);

    ClientSubject.getInstance().addObserver(this);

  }

  @Override
  public void notifyObserver(EmotivData data) {
    // call graph and emotional expression methods
    System.out.println(data.getBlink());
    displayGraph.updateGraph(data);
    if (facePanel != null) {
      facePanel.updateFace(data);
    }
  }
}
