package client.gui.panels;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import org.jfree.chart.ChartPanel;
import util.Constants;
import client.gui.PerformanceGraph;
/*
 * This class is used to create the Performance Metrics section of the main frame in the application
 * 
 * @author Jahnavi Bantupalli
 * @version 1.0
 * @since 03APR2018
 *
 */

public class PerformanceMetricPanel extends JPanel {

  private ColorChooserPanel colorChooser;
  private EmoStatePanel boxesPanel;
  private JPanel graphPanel;
  private JLabel lblHeaderName;
  private JPanel metrixPanel;
  public PerformanceGraph performanceGraph;

  /**
   * Constructor that creates the Performance metrics panel
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
    ChartPanel chartPanelObject = performanceGraph.getChartPanel();
    chartPanelObject = new ChartPanel(performanceGraph.getGraph());

    chartPanelObject.setLocation(12, 26);
    chartPanelObject.setSize(new Dimension(500, 500));
    graphPanel.add(chartPanelObject);
    

    lblHeaderName = new JLabel("Graph");
    lblHeaderName.setHorizontalTextPosition(SwingConstants.CENTER);
    lblHeaderName.setHorizontalAlignment(SwingConstants.CENTER);
    lblHeaderName.setBounds(0, 6, 690, 18);
    lblHeaderName.setFont(new Font("Arial", Font.PLAIN, 15));
    graphPanel.add(lblHeaderName);
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

    JLabel lblDisplayLength = new JLabel("  Display Length:");
    lblDisplayLength.setFont(new Font("Arial", Font.PLAIN, 15));
    lblDisplayLength.setBounds(0, 525, 130, 32);
    metrixPanel.add(lblDisplayLength);

    JLabel lblTime = new JLabel("2");
    lblTime.setHorizontalAlignment(SwingConstants.CENTER);
    lblTime.setFont(new Font("Arial", Font.PLAIN, 15));
    lblTime.setBounds(130, 525, 35, 32);
    metrixPanel.add(lblTime);

    JLabel lblTimeMeasure = new JLabel(" seconds");
    lblTimeMeasure.setFont(new Font("Arial", Font.PLAIN, 15));
    lblTimeMeasure.setBounds(165, 525, 80, 32);
    metrixPanel.add(lblTimeMeasure);

    EmoStatePanel interestPanel = new EmoStatePanel(this, "Interest",0);
    interestPanel.setBounds(24, 79, 90, 90);
    interestPanel.setBackground(Constants.DEEPBLUE);
    metrixPanel.add(interestPanel);

    EmoStatePanel excitementPanel = new EmoStatePanel(this, "Excitement",1);
    excitementPanel.setBounds(141, 79, 90, 90);
    excitementPanel.setBackground(Constants.ORANGE);
    metrixPanel.add(excitementPanel);

    EmoStatePanel engagementPanel = new EmoStatePanel(this, "Engagement",2);
    engagementPanel.setBounds(24, 223, 90, 90);
    engagementPanel.setBackground(Constants.MAROON);;
    metrixPanel.add(engagementPanel);

    EmoStatePanel stressPanel = new EmoStatePanel(this, "Stress",3);
    stressPanel.setBounds(141, 223, 90, 90);
    stressPanel.setBackground(Constants.GREEN);
    metrixPanel.add(stressPanel);

    EmoStatePanel relaxationPanel = new EmoStatePanel(this, "Relaxation",4);
    relaxationPanel.setBounds(24, 367, 90, 90);
    relaxationPanel.setBackground(Constants.CORAL);
    metrixPanel.add(relaxationPanel);

    EmoStatePanel focusPanel = new EmoStatePanel(this, "Focus",5);
    focusPanel.setBounds(141, 367, 90, 90);
    focusPanel.setBackground(Constants.PINK);
    metrixPanel.add(focusPanel);

    add(metrixPanel);
  }
}
