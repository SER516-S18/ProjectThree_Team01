package client.sys;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import data.EmotivData;

/**
 * This thread adds each value to the graph dynamically.
 */
public class DisplayThread implements Runnable {
  private volatile boolean exit = false;
  EmotivData data;
  public TimeSeries graphSeries[];
  TimeSeriesCollection dataset;
  int value;

  public DisplayThread(EmotivData data, TimeSeries[] graphSeries, TimeSeriesCollection dataset) {
    super();
    this.data = data;
    this.graphSeries = graphSeries;
    this.dataset = dataset;
  }

  @Override
  public void run() {
    while (!exit) {
      double values[];
      values = new double[12];
      values[0] = 1 + data.getEyebrowRaise();
      values[1] = 2 + data.getEyebrowFurrow();
      values[2] = 3 + data.getSmile();
      values[3] = 4 + data.getClench();
      values[4] = 5 + data.getLaugh();
      values[5] = 6 + data.getSmirkRight();
      values[6] = 7 + data.getSmirkLeft();
      values[7] = 8 + data.getLookingRight();
      values[8] = 9 + data.getLookingLeft();
      values[9] = 10 + data.getRightWink();
      values[10] = 11 + data.getLeftWink();
      values[11] = 12 + data.getBlink();

      for (int i = 0; i < 12; i++) {
        System.out.println(values[i]);
        graphSeries[i].add(new Millisecond(), values[i]);
        dataset.getSeries(i).addOrUpdate(new Millisecond(), values[i]);
      }
      try {
        Thread.sleep(250); // Don't forget to match this frequency with the
                           // server frequency
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void stop() {
    exit = true;
  }
}