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
  private static volatile int interval = 1000;

  private volatile ButtonStatus state;
  private EmotivRandomizer er;
  private EmotivData data;

  private enum ButtonStatus {
    SEND, STARTED, STOPPED, SUSPEND;
  }

  public WorkerThread(EmotivRandomizer er) {
    this.er = er;
    er.addToObserver(this);
    state = ButtonStatus.STOPPED;
  }

  private void setButtonStatus(String val) {
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
    interval = (int) (val * 1000);
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
          er.sendButtonText(er.getSendButtonText(), "" + (interval / 1000.0));
          fetchRandomData();
        } else {
          interval = 5000;
          updateConsolePanel("Execution suspended, fix error to resume");
        }

        try {
          Thread.sleep(interval);
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
    } catch (IOException e) {
      updateConsolePanel("Server or Client unable to exchange details...");
    } catch (NullPointerException e) {
      updateConsolePanel("Session not bound to this connection...");
    } catch (IndexOutOfBoundsException e) {
      updateConsolePanel("Client not found...");
    }
  }

  private void updateConsolePanel(String message) {
    LogPanel.getConsolePanel().updateText(message + "&emsp;&emsp&lt;" + LocalTime.now() + "&gt;");
  }

  @Override
  public void update(PassedData passedData) {
    setInterval(passedData.interval);
    data = passedData.data;
    setButtonStatus(passedData.buttonText);
  }
}
