package client.sys;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.json.JSONObject;

import data.EmotivData;

/**
 * This thread adds each value to the graph dynamically.
 * 
 * @author Jahnavi Bantupalli
 * @version 1.0
 * @since 2018-04-01
 */
public class PerformanceThread implements Runnable {
  private EmotivData data;
  private TimeSeries graphSeries[];
  private TimeSeriesCollection dataset;

  /*
   * Initializes the thread
   */
  public PerformanceThread(EmotivData data, TimeSeries[] graphSeries, TimeSeriesCollection dataset) {
    super();
    this.data = data;
    this.graphSeries = graphSeries;
    this.dataset = dataset;
  }

  /*
   * This function is called when data is received from the server
   * 
   */
  @Override
  public void run() {

    double values[];
    values = new double[6];
    JSONObject performanceData = data.getPerformance();
    values[0] = 1 + performanceData.getDouble("Interest");
    values[1] = 3 + performanceData.getDouble("Excitement");
    values[2] = 5 + performanceData.getDouble("Engagement");
    values[3] = 7 + performanceData.getDouble("Stress");
    values[4] = 9 + performanceData.getDouble("Relaxation");
    values[5] = 11 + performanceData.getDouble("Focus");
    for (int i = 0; i < 6; i++) {
      System.out.println(values[i]);
      graphSeries[i].add(new Millisecond(), values[i]);
      dataset.getSeries(i).addOrUpdate(new Millisecond(), values[i]);
    }
  }
}