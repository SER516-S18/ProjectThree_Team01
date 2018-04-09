package server.sys;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import javax.websocket.Session;

import data.EmotivData;
import server.gui.panels.LogPanel;
import server.sys.observer.EmotivObserver;
import server.sys.observer.PassedData;

/**
 * The purpose of this class is to implement a working thread to handle the
 * server interaction by sending random data to the client on the other end.
 * 
 * @author Cephas Armstrong-Mensah
 * @author Group 1 #001 - #013
 * @since 28MAR2018
 *
 */
public class WorkerThread implements Runnable, EmotivObserver {
  private static int INTERVAL = 1000;

  private volatile ButtonStatus state;
  private EmotivRandomizer er;
  private EmotivData data;
  private String buttonText;

  private enum ButtonStatus {
    SEND, STARTED, STOPPED, SUSPEND;
  }

  public WorkerThread(EmotivRandomizer er) {
    this.er = er;
    er.addToObserver(this);
    state = ButtonStatus.STOPPED;
  }

  private void setButtonStatus(String val) {
    this.buttonText = val;

    if (val.equalsIgnoreCase("send")) {
      state = ButtonStatus.SEND;
    } else if (val.equalsIgnoreCase("start")) {
      state = ButtonStatus.STARTED;
    } else if (val.equalsIgnoreCase("stop")) {
      state = ButtonStatus.STOPPED;
    } else if (val.equalsIgnoreCase("suspend")) {
      state = ButtonStatus.SUSPEND;
    }
  }

  private void setInterval(double val) {
    INTERVAL = (int) (val * 1000);
  }

  @Override
  public void run() {

    switch (state) {
    case SEND:
      fetchRandomData();
      state = ButtonStatus.STOPPED;
      break;
    case STARTED:
      while (state != ButtonStatus.STOPPED) {
        if (state != ButtonStatus.SUSPEND) {
          er.sendButtonText(er.getSendButtonText(), "" + (INTERVAL / 1000.0));
          fetchRandomData();
        } else {
          INTERVAL = 5000;

          LogPanel.getConsolePanel().updateText("Execution suspended, fix error to resume");
        }

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
    er.getRandomData();
    Session temp = null;
    List<Session> clients = ServerWebSocket.getClients();

    try {
      temp = clients.get(0);
      ServerWebSocket.sendMessage(temp, data.toString());
      updateConsolePanel(String.format("Sent data to client: %s", temp.getId()));

      System.out.println(String.format("Sent data to client: %s", data));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NullPointerException e) {
      updateConsolePanel("Session not bound to this connection...");
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
    LogPanel.getConsolePanel().updateText(message + "&emsp;&emsp&lt;" + LocalTime.now() + "&gt;");
  }

  @Override
  public void update(PassedData passedData) {
    setInterval(passedData.interval);
    this.data = passedData.data;
    setButtonStatus(passedData.buttonText);
  }
}
