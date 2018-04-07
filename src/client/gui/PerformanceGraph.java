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
import data.EmotivData;

/**
 * This class represents the graph panel It contains the graph, dataset and
 * other chart related parameters
 * 
 */
public class PerformanceGraph extends JPanel {

  private static final long serialVersionUID = 7028325762888041341L;
  JFreeChart graph;
  TimeSeriesCollection dataset;
  TimeSeries graphSeries[];
  public ChartPanel chartPanel;
  String channelNames[];
  public JFreeChart getGraph() {
	return graph;
}
  






  public ChartPanel getChartPanel() {
		// TODO Auto-generated method stub
		return chartPanel;
	}


  public PerformanceGraph() {
    super();
    graphSeries = new TimeSeries[6];
    dataset = new TimeSeriesCollection();
    channelNames = new String[6];

    channelNames[0] = new String("");
    channelNames[1] = new String("");
    channelNames[2] = new String("");
    channelNames[3] = new String("");
    channelNames[4] = new String("");
    channelNames[5] = new String("");

    for (int i = 0; i < 6; i++) {
      graphSeries[i] = new TimeSeries(channelNames[i]);
      // (Deprecated) new TimeSeries(channelNames[i], Millisecond.class);
      dataset.addSeries(graphSeries[i]);
    }
    graph = createChart(dataset);
  }

  /**
   * This function creates the chart with necessary parameters.
   */
  private JFreeChart createChart(final XYDataset dataset) {
    final JFreeChart result = ChartFactory.createTimeSeriesChart("Performance Plot", "Time", "Value", dataset, true,
        true, false);
    final XYPlot plot = result.getXYPlot();
    ValueAxis axis = plot.getDomainAxis();
    axis.setAutoRange(true);
    axis = plot.getRangeAxis();
    return result;
  }

  /**
   * This method starts the thread each time a value is received
   */
  public void updateGraph(EmotivData data) {

    }


  }
