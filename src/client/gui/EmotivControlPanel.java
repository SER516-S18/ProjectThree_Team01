package client.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;

public class EmotivControlPanel extends JFrame {

  private JPanel contentPane;
  private JPanel facialExpressionPanel ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmotivControlPanel frame = new EmotivControlPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmotivControlPanel() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    facialExpressionPanel = new JPanel();
    facialExpressionPanel.setLayout(null);
    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.setBounds(5, 5, 422, 243);
    contentPane.add(tabbedPane);
    tabbedPane.setName("Facial Expressions");
    tabbedPane.addTab("Facial Expressions", facialExpressionPanel);
    JPanel graphPanel = new JPanel();
    graphPanel.setBounds(199, 0, 218, 213);
    facialExpressionPanel.add(graphPanel);
	}

}
