package server.sys;

import org.glassfish.tyrus.server.Server;

import data.EmotivData;

public class ServerThread implements Runnable {
  private Server server;
  private ButtonStatus state;
  private EmotivRandomizer er;
  private EmotivData data;
  public static volatile boolean isClosing = false;

  private enum ButtonStatus {
    SEND, START, STOP;
  }

  public ServerThread(Server server) {
    this.server = server;
    data = new EmotivData();
    er = new EmotivRandomizer();
  }

  public void setButtonStatus(ButtonStatus val) {
    state = val;
  }

  @Override
  public void run() {
    while (!isClosing) {
    }
    server.stop();
    System.out.println("Server closing...");
  }
}
