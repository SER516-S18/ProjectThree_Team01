package server.sys;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.websocket.Session;

import data.EmotivData;
import util.ConsolePanel;

/**
 * The purpose of this class is to implement a working thread to handle the
 * server interaction by sending random data to the client on the other end.
 * 
 * @author Cephas Armstrong-Mensah
 *
 */

public class WorkerThread implements Runnable {
  private static int INTERVAL = 1000;
  private static double timer = 0.0;

  private ButtonStatus state;
  private EmotivRandomizer er;
  private EmotivData data;
  private JLabel timeTracker;
  private ConsolePanel consolePanel;
  private Session session;

  private enum ButtonStatus {
    SEND, STARTED, STOPPED;
  }

  public WorkerThread(JLabel timeTracker, JPanel console) {
    data = new EmotivData();
    er = new EmotivRandomizer();
    state = ButtonStatus.STOPPED;
    this.timeTracker = timeTracker;
    this.consolePanel = (ConsolePanel) console;
  }

  public void setButtonStatus(String val) {
    if (val.equalsIgnoreCase("send")) {
      state = ButtonStatus.SEND;
    } else if (val.equalsIgnoreCase("start")) {
      state = ButtonStatus.STARTED;
    } else if (val.equalsIgnoreCase("stop")) {
      state = ButtonStatus.STOPPED;
    }
  }

  public void setInterval(double val) {
    INTERVAL = (int) (val * 1000);
  }

  @Override
  public void run() {
    switch (state) {
    case SEND:
      fetchRandomData();
      timer += (INTERVAL * 1.0 / 1000);
      state = ButtonStatus.STOPPED;
      timeTracker.setText(String.format("%.2f", timer));
      break;
    case STARTED:
      while (state != ButtonStatus.STOPPED) {
        fetchRandomData();
        timer += (INTERVAL * 1.0 / 1000);
        timeTracker.setText(String.format("%.2f", timer));
        try {
          Thread.sleep(INTERVAL);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      break;
    default:
      break;
    }
  }

  private void fetchRandomData() {
    data = er.getRandomData();
    Session temp = null;
    List<Session> clients = ServerWebSocket.getClients();

    try {
      temp = clients.get(0);
      ServerWebSocket.sendMessage(temp, data.toString());
      updateConsolePanel(String.format("Sent data to client: %s", temp.getId()));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NullPointerException e) {
      updateConsolePanel ("Session not bound to this connection...");
    } catch (IndexOutOfBoundsException e) {
      updateConsolePanel("Client not found...");
    }
  }

  /**
   * Updating the Console to output status message
   * 
   * @param message
   */
  private void updateConsolePanel(String message) {
    consolePanel.updateText(message + "&emsp;&emsp&lt;" + LocalTime.now() + "&gt;");
  }
}
