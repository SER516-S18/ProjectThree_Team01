package client.gui.panels;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartPanel;

import client.gui.DisplayGraph;
import client.gui.PerformanceGraph;

import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PerformanceMetricPanel extends JPanel {

	// private JPanel contentPane;
	private ColorChooserPanel colorChooser;
	private BoxesPanel boxesPanel;
	private JPanel graphPanel;
	private JLabel lblNewLabel;
	private JPanel metrixPanel;
	private PerformanceGraph performanceGraph;

	/**
	 * Create the frame.
	 */
	public PerformanceMetricPanel() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(0, 0, 959, 992);
		setLayout(null);
		
		graphPanel = new JPanel();
		graphPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		graphPanel.setBounds(10, 5, 690, 992);
		graphPanel.setLayout(null);
		
		
		
		performanceGraph = new PerformanceGraph();
		ChartPanel chartPanel=performanceGraph.getChartPanel();
		chartPanel= new ChartPanel(performanceGraph.getGraph());

		chartPanel.setLocation(12, 26);
		chartPanel.setSize(new Dimension(500, 500));
	    graphPanel.add(chartPanel);
		
		lblNewLabel = new JLabel("Graph");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 6, 690, 18);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		graphPanel.add(lblNewLabel);
		add(graphPanel);
		
		metrixPanel = new JPanel();
		metrixPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		metrixPanel.setBounds(705, 0, 250, 992);
		metrixPanel.setLayout(null);
		
		JLabel lblParameters = new JLabel("Parameters");
		lblParameters.setHorizontalTextPosition(SwingConstants.CENTER);
		lblParameters.setHorizontalAlignment(SwingConstants.CENTER);
		lblParameters.setBounds(0, 0, 250, 18);
		lblParameters.setFont(new Font("Arial", Font.PLAIN, 15));
		metrixPanel.add(lblParameters);
		
		BoxesPanel panel = new BoxesPanel(this, "Interest");
		panel.setBounds(24, 79, 90, 90);
		metrixPanel.add(panel);
		
		BoxesPanel panel_1 = new BoxesPanel(this, "Excitement");
		panel_1.setBounds(141, 79, 90, 90);
		metrixPanel.add(panel_1);
		
		JLabel lblDisplayLength = new JLabel("  Display Length:");
		lblDisplayLength.setFont(new Font("Arial", Font.PLAIN, 15));
		lblDisplayLength.setBounds(0, 525, 120, 32);
		metrixPanel.add(lblDisplayLength);
		
		BoxesPanel boxesPanel_1 = new BoxesPanel((PerformanceMetricPanel) null, "Interest");
		boxesPanel_1.setBounds(24, 223, 90, 90);
		metrixPanel.add(boxesPanel_1);
		
		BoxesPanel boxesPanel_2 = new BoxesPanel((PerformanceMetricPanel) null, "Interest");
		boxesPanel_2.setBounds(141, 223, 90, 90);
		metrixPanel.add(boxesPanel_2);
		
		BoxesPanel boxesPanel_3 = new BoxesPanel((PerformanceMetricPanel) null, "Interest");
		boxesPanel_3.setBounds(24, 367, 90, 90);
		metrixPanel.add(boxesPanel_3);
		
		BoxesPanel boxesPanel_4 = new BoxesPanel((PerformanceMetricPanel) null, "Interest");
		boxesPanel_4.setBounds(690, 367, 90, 90);
		metrixPanel.add(boxesPanel_4);
		
		add(metrixPanel);
	}
}
