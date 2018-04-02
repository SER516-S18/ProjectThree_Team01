package server.sys;

import java.io.IOException;
import java.util.List;

import javax.swing.JLabel;
import javax.websocket.Session;

import data.EmotivData;

public class WorkerThread implements Runnable {
  private static int INTERVAL = 1000;
  private static double timer = 0.0;

  private ButtonStatus state;
  private EmotivRandomizer er;
  private EmotivData data;
  private JLabel timeTracker;
  private Session session;

  private enum ButtonStatus {
    SEND, STARTED, STOPPED;
  }

  public WorkerThread(JLabel timeTracker) {
    data = new EmotivData();
    er = new EmotivRandomizer();
    state = ButtonStatus.STOPPED;
    this.timeTracker = timeTracker;
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
    temp = clients.get(0);

    try {
      ServerWebSocket.sendMessage(temp, data.toString());
    } catch (IOException | NullPointerException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
