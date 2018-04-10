package client.gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;

import client.gui.panels.PerformanceMetricPanel;
import client.sys.ClientSubject;
import data.EmotivData;
import interfaces.ClientObserver;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;

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
	private ClassLoader loader = getClass().getClassLoader();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EmotivControlPanel frame = EmotivControlPanel.getInstance();
		frame.setVisible(true);
		// frame.setSize(1000,1000);
	}
	
	/**
	 * To implement Singleton Instance of EmotivControlPanel
	 * @return singleton Instance of the class
	 */
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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				isClosing = true;
				System.exit(0);
			}
		});
		setBounds(100, 100, 982, 919);
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
		facialExpressionPanel.add(facePanel);

		displayGraph = new DisplayGraph();
		displayGraph.chartPanel = new ChartPanel(displayGraph.getGraph());
		displayGraph.chartPanel.setLocation(12, 26);
		displayGraph.chartPanel.setSize(new Dimension(400, 400));
		facialExpressionPanel.setLayout(null);

		graphPanel.add(displayGraph.chartPanel);
		graphPanel.setLayout(null);

		ClientSubject.getInstance().addObserver(this);

		performanceMetric = new PerformanceMetricPanel();
		performanceMetric.setBounds(-12, 0, 971, 992);

		signalLabel = new JLabel(new ImageIcon(loader.getResource("weak.png")));
		signalLabel.setBounds(910, 0, 48, 48);
		contentPane.add(signalLabel);

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
	 * To display the graph that plots the values
	 * and display facial expressions.
	 */
	public void showFacialGraph() {
		tabbedPane.setVisible(true);
		performanceMetric.setVisible(false);
		this.repaint();
	}
	
	/**
	 * Notifies when the client application is 
	 * closing
	 * @return true when client is closing
	 * and false otherwise
	 */
	public boolean getIsClosing() {
		return isClosing;
	}

	/**
	 * Update the subscribed observers (display graph,
	 * face panel and performanceMetric panel and pass
	 * the data received)
	 */
	@Override
	public void updateObserver(EmotivData data) {
		System.out.println(data.getBlink());
		displayGraph.updateGraph(data);
		performanceMetric.performanceGraph.updateGraph(data);
		if (facePanel != null) {
			facePanel.updateFace(data);
		}
	}
	
	/**
	 * @param connected - Updates the icon 
	 * for signal label when client connects to the 
	 * server
	 */
	public void updateSignalLabel(boolean connected) {
		if (connected) {
			signalLabel.setIcon(new ImageIcon(loader.getResource("strong.png")));
		} else {
			signalLabel.setIcon(new ImageIcon(loader.getResource("weak.png")));
		}
	}
}
