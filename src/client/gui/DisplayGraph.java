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
 */
public class DisplayGraph extends JPanel {

  private static final long serialVersionUID = 7028325762888041341L;
  JFreeChart graph;
  TimeSeriesCollection dataset;
  TimeSeries graphSeries[];
  ChartPanel chartPanel;
  String channelNames[];

  static DisplayThread c;

  public DisplayGraph() {
    super();
    graphSeries = new TimeSeries[12];
    dataset = new TimeSeriesCollection();
    channelNames = new String[12];
    channelNames[0] = new String("Looking Right");
    channelNames[1] = new String("Eyebrow Raise");
    channelNames[2] = new String("Looking Left");
    channelNames[3] = new String("Eyebrow Furrow");
    channelNames[4] = new String("Looking Down");
    channelNames[5] = new String("Looking Up");
    channelNames[6] = new String("Right Wink");
    channelNames[7] = new String("Left Wink");
    channelNames[8] = new String("Blink");
    channelNames[9] = new String("EyesOpen");
    channelNames[10] = new String("Smile");
    channelNames[11] = new String("Clench");

    for (int i = 0; i < 12; i++) {
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
    final JFreeChart result = ChartFactory.createTimeSeriesChart("Plot", "Time", "Value", dataset, true,
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
    if (c == null) {
      c = new DisplayThread(data, graphSeries, dataset);
      new Thread(c).start();
    }
  }
}