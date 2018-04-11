package client.gui;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import client.sys.DisplayThread;
import data.EmotivData;

/**
 * This class represents the graph panel It contains the graph, dataset and
 * other chart related parameters
 *
 * @author Jahnavi Bantupalli
 * @version 1.0
 * @since 2018-04-04
 * 
 */
public class DisplayGraph extends JPanel {
  private JFreeChart graph;
  private TimeSeriesCollection dataset;
  private TimeSeries graphSeries[];
  ChartPanel chartPanel;
  private String channelNames[];
  private static DisplayThread displayThread;

  /*Initializing the channels for the plot of facial expressions.
   * 
   */
  public DisplayGraph() {
    super();
    graphSeries = new TimeSeries[12];
    dataset = new TimeSeriesCollection();
    channelNames = new String[12];
    channelNames[0] = new String("Eyebrow Raise");
    channelNames[1] = new String("Eyebrow Furrow");
    channelNames[2] = new String("Smile");
    channelNames[3] = new String("Clench");
    channelNames[4] = new String("Laugh");
    channelNames[5] = new String("Smirk Right");
    channelNames[6] = new String("Smirk Left");
    channelNames[7] = new String("Looking Right");
    channelNames[8] = new String("Looking Left");
    channelNames[9] = new String("Right Wink");
    channelNames[10] = new String("Left Wink");
    channelNames[11] = new String("Blink");
    for (int i = 0; i < 12; i++) {
      graphSeries[i] = new TimeSeries(channelNames[i]);
      dataset.addSeries(graphSeries[i]);
    }
    graph = createChart(dataset);
  }
/*This function returns the graph.
 * 
 */
  public JFreeChart getGraph() {
	return graph;
  }
  
  /*This function returns the chart panel. 
   * 
   */
  public ChartPanel getChartPanel() {
		// TODO Auto-generated method stub
		
		return chartPanel;
	}


/**
   * This function creates the chart with necessary parameters.
*/
  private JFreeChart createChart(final XYDataset dataset) {
    final JFreeChart result = ChartFactory.createTimeSeriesChart("Plot", "Time", "Value", dataset, true,
        true, false);
    final XYPlot plot = result.getXYPlot();
    ValueAxis axis = plot.getDomainAxis();
    axis.setAutoRange(true);
    axis = plot.getRangeAxis();
    axis.setTickLabelsVisible(false);
    return result;
  }

  /**
   * This method starts the thread each time a value is received
   */
  public void updateGraph(EmotivData data) {
    if (displayThread == null) {
      displayThread = new DisplayThread(data, graphSeries, dataset);
    }
    new Thread(displayThread).start();

  }

}
