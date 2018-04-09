package client.sys;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

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
      values[0] = 1 + data.getEyebrowRaise();
      values[1] = 2 + data.getEyebrowFurrow();
      values[2] = 3 + data.getSmile();
      values[3] = 4 + data.getClench();
      values[4] = 5 + data.getLaugh();
      values[5] = 6+ data.getLaugh();
      for (int i = 0; i < 6; i++) {
        System.out.println(values[i]);
        graphSeries[i].add(new Millisecond(), values[i]);
        dataset.getSeries(i).addOrUpdate(new Millisecond(), values[i]);
      }
     
    }
  }


}