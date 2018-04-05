package client.gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;

import client.sys.ClientSubject;
import client.sys.ClientThread;
import data.EmotivData;
import interfaces.ClientObserver;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
    
    //here
    JMenuBar menuBar = new JMenuBar();
	setJMenuBar(menuBar);
	
	JMenu menu = new JMenu("MENU");
	menu.setIcon(new ImageIcon("img/icons8-menu-10.png"));
	menuBar.add(menu);
	
	JMenu applicationsOption = new JMenu("APPLICATION");
	menu.add(applicationsOption);
	
	JMenuItem composerOption = new JMenuItem("EMOTIV Xavier Composer");
	applicationsOption.add(composerOption);
	
	JMenuItem screenshotOption = new JMenuItem("Save Current Screnshot");
	applicationsOption.add(screenshotOption);
	
	JMenu connectOption = new JMenu("CONNECT");
	menu.add(connectOption);
	
	JMenuItem connectComposer = new JMenuItem("Connect Composer");
	connectComposer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			ConnectToServerFrame connectFrame = new ConnectToServerFrame();
			connectFrame.setVisible(true);
		}
	});
	connectOption.add(connectComposer);
	
	JMenuItem reconnectComposer = new JMenuItem("Reconnect Composer");
	connectOption.add(reconnectComposer);
	
	JMenu detectionsOption = new JMenu("DETECTIONS");
	menu.add(detectionsOption);
	
	JMenuItem facialExpressions = new JMenuItem("Facial Expressions");
	detectionsOption.add(facialExpressions);
	
	JMenuItem performanceMetrices = new JMenuItem("Performance Metrics");
	detectionsOption.add(performanceMetrices);
	
	JMenu helpOption = new JMenu("HELP");
	menu.add(helpOption);
	
	JMenuItem emotivOnGithub = new JMenuItem("Emotiv On Github");
	helpOption.add(emotivOnGithub);
	
	JMenuItem aboutOption = new JMenuItem("About Xavier Control Panel");
	helpOption.add(aboutOption);
    //here

    
    //ClientHamburgerMenu menu = new ClientHamburgerMenu();
    //setJMenuBar(menu);
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
    displayGraph.chartPanel.setSize(new Dimension(500, 800));
    facialExpressionPanel.setLayout(null);

    graphPanel.add(displayGraph.chartPanel);
    graphPanel.setLayout(null);
    
    ClientSubject.getInstance().addObserver(this);
  }

  

  @Override
  public void notifyObserver(EmotivData data) {
    // call graph and emotional expression methods
    // System.out.println(data.getBlink());
    displayGraph.updateGraph(data);
    if(facePanel!=null) {
    	facePanel.updateFace(data);
    }
  }
}
