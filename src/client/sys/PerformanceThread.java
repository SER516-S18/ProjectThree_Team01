package client.sys;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.json.JSONObject;

import data.EmotivData;

/**
 * This thread adds each value to the graph dynamically.
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
    while (true) {
      double values[];
      values = new double[6];
      JSONObject performanceData =data.getPerformance();
      values[0] = 1 + performanceData.getInt("Interest");
      values[1] = 2 + performanceData.getInt("Engagement");
      values[2] = 3 + performanceData.getInt("Stress");
      values[3] = 4 + performanceData.getInt("Relaxation");
      values[4] = 5 +performanceData.getInt("Excitement");
      values[5] = 6+ performanceData.getInt("Focus"); 
      for (int i = 0; i < 6; i++) {
        System.out.println(values[i]);
        graphSeries[i].add(new Millisecond(), values[i]);
        dataset.getSeries(i).addOrUpdate(new Millisecond(), values[i]);
      }
     
    }
  }


}