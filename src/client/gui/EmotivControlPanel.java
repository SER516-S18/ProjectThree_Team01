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
	private String uri = "localhost";
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

		performanceMetric = new PerformanceMetricPanel();
		performanceMetric.setBounds(0, 0, 959, 992);

		signalLabel = new JLabel(new ImageIcon(loader.getResource("weak.png")));
		signalLabel.setBounds(910, 0, 48, 48);
		contentPane.add(signalLabel);

		contentPane.add(tabbedPane);
		contentPane.add(performanceMetric);
		performanceMetric.setVisible(false);

	}

	public void showPerformanceMetric() {
		tabbedPane.setVisible(false);
		performanceMetric.setVisible(true);
		this.repaint();
	}

	public void showFacialGraph() {
		tabbedPane.setVisible(true);
		performanceMetric.setVisible(false);
		this.repaint();
	}

	public boolean getIsClosing() {
		return isClosing;
	}

	@Override
	public void updateObserver(EmotivData data) {
		// call graph and emotional expression methods
		System.out.println(data.getBlink());
		displayGraph.updateGraph(data);
		if (facePanel != null) {
			facePanel.updateFace(data);
		}
	}

	public void updateSignalLabel(boolean connected) {
		if (connected) {
			signalLabel.setIcon(new ImageIcon(loader.getResource("strong.png")));
		} else {
			signalLabel.setIcon(new ImageIcon(loader.getResource("weak.png")));
		}
	}
}
