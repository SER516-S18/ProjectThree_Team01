package client.gui;
import java.awt.Color;

import client.gui.panels.EmoStatePanel;
import client.sys.*;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import data.EmotivData;
import util.Constants;

/**
 * This class represents the graph panel It contains the graph, dataset and
 * other chart related parameters
 * 
 */
public class PerformanceGraph extends JPanel {
  private JFreeChart graph;
  private TimeSeriesCollection dataset;
  private TimeSeries graphSeries[];
  private ChartPanel chartPanel;
  private String channelNames[];
  private static PerformanceThread performanceThread;
  
  /*
   * This function returns the performance graph
   */
  public JFreeChart getGraph() {
	return graph;
}
  /*
   * This function returns the chart panel
   */
  public ChartPanel getChartPanel() {
		return chartPanel;
	}

  /*
  * Intializing the performance graph parameters
  */
  public PerformanceGraph() {
    super();
    graphSeries = new TimeSeries[6];
    dataset = new TimeSeriesCollection();
    channelNames = new String[6];

    channelNames[0] = new String("Interest");
    channelNames[1] = new String("Excitement");
    channelNames[2] = new String("Engagement");
    channelNames[3] = new String("Stress");
    channelNames[4] = new String("Relaxation");
    channelNames[5] = new String("Focus");

    for (int i = 0; i < 6; i++) {
      graphSeries[i] = new TimeSeries(channelNames[i]);
      dataset.addSeries(graphSeries[i]);
    }
       graph = createChart(dataset);
  }

  /**
   * This function creates the chart with necessary parameters.
   */
  private JFreeChart createChart(final XYDataset dataset) {
    graph = ChartFactory.createTimeSeriesChart("Performance Plot", "Time", "Value", dataset, true,
        true, false);
    final XYPlot plot = graph.getXYPlot();
    ValueAxis axis = plot.getDomainAxis();
    axis.setAutoRange(true);
    axis = plot.getRangeAxis();
    axis.setTickLabelsVisible(false);
    plot.getRenderer().setSeriesPaint(0, Constants.DEEPBLUE);
    plot.getRenderer().setSeriesPaint(1, Constants.ORANGE);
    plot.getRenderer().setSeriesPaint(2, Constants.MAROON);
    plot.getRenderer().setSeriesPaint(3, Constants.GREEN);
    plot.getRenderer().setSeriesPaint(4, Constants.CORAL);
    plot.getRenderer().setSeriesPaint(5, Constants.PINK);

    
    
    return graph;
  }

  /**
   * This method starts the thread each time a value is received
   */
  public void updateGraph(EmotivData data) {
	    if (performanceThread == null) {
	        performanceThread = new PerformanceThread(data, graphSeries, dataset);
	      }
        new Thread(performanceThread).start();

    }
  }
