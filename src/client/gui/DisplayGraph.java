package client.gui;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


/*This panel represents the graph panel
 * It contains the graph, dataset and other chart related parameters
 * 
 */

public class DisplayGraph extends JPanel {
  JFreeChart graph;
  int channel;
  int value;
  XYSeriesCollection dataset ;
  public XYSeries series;
  public XYSeries graphSeries[];
  ChartPanel chartPanel  ;
  ArrayList<Integer> time = new ArrayList<Integer>();
  
  static DisplayThread c ;


  public DisplayGraph() {
    super();
    graphSeries= new XYSeries[11];
    dataset= new XYSeriesCollection();
    for(int i=0;i<11;i++)
    {
    	int channelno=i+1;
    graphSeries[i] = new XYSeries("Channel"+channelno);
    dataset.addSeries(graphSeries[i]);
    }
    graph= createChart(dataset);


  }
  /*
   * This function creates the chart with necessary parameters.
   */
  private JFreeChart createChart(final XYDataset dataset) {
    final JFreeChart result = ChartFactory.createTimeSeriesChart(
        "Plot", 
        "Time", 
        "Value",
        dataset, 
        true, 
        true, 
        false
        );
    final XYPlot plot = result.getXYPlot();
    ValueAxis axis = plot.getDomainAxis();
    axis.setAutoRange(true);
    axis.setFixedAutoRange(60000.0);
  //  axis.setRange(0.0,1.0); 

    axis = plot.getRangeAxis();
    return result;
  }

  /*
   * This method starts the thread each time a value is received
   */

  public void updateGraph(int channel,int value){
    this.channel=channel;
    this.value=value;
    if (c == null) {
      c = new DisplayThread();
      new Thread(c).start();
    }
    

  }

  /*
   * This thread adds each value to the graph dynamically.
   */
  public class DisplayThread implements Runnable {
	  private volatile boolean exit=false;
    @Override
    public void run() {
      while (!exit) {
    	  
 
				for(int i=0;i<channel;i++)
				{
						Random random = new Random();
					
				
						graphSeries[i].add(random.nextInt(), random.nextInt());
						dataset.getSeries(i).add(random.nextInt(), random.nextInt());
					
					
					
    	        }
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
    public void stop()
    {
    	exit=true;
    }

  }

}