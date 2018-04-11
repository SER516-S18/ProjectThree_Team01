package client.sys;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import data.EmotivData;

/**
 * This thread adds each value to the graph dynamically.
 * @author Jahnavi Bantupalli
 * @version 1.0
 * @since 2018-04-01 
 */
public class DisplayThread implements Runnable {
  private volatile boolean exit = false;
  private EmotivData data;
  public TimeSeries graphSeries[];
  private TimeSeriesCollection dataset;

  /*
   * This function initializes the data
   */
  public DisplayThread(EmotivData data, TimeSeries[] graphSeries, TimeSeriesCollection dataset) {
    super();
    this.data = data;
    this.graphSeries = graphSeries;
    this.dataset = dataset;
  }

  /*
   * This function plots data to the graph
   */
  @Override
  public void run() {
    double values[];
    values = new double[12];
    
      values[0] = 1 + data.getEyebrowRaise();
      values[1] = 3 + data.getEyebrowFurrow();
      values[2] = 5 + data.getSmile();
      values[3] = 7 + data.getClench();
      values[4] = 9 + data.getLaugh();
      values[5] = 11 + data.getSmirkRight();
      values[6] = 13+ data.getSmirkLeft();
      values[7] = 15+ data.getLookingRight();
      values[8] = 17+ data.getLookingLeft();
      values[9] = 19 + data.getRightWink();
      values[10] = 21 + data.getLeftWink();
      values[11] = 23 + data.getBlink();

      for (int i = 0; i < 12; i++) {
        System.out.println(values[i]);
        graphSeries[i].add(new Millisecond(), values[i]);
        dataset.getSeries(i).addOrUpdate(new Millisecond(), values[i]);
      }
   
    
  }

}