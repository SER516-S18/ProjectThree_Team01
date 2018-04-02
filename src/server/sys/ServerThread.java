package server.sys;

import org.glassfish.tyrus.server.Server;

public class ServerThread implements Runnable {
  private Server server;
  public static volatile boolean isClosing = false;

  public ServerThread(Server server) {
    this.server = server;
  }

  @Override
  public void run() {
    while (!isClosing) {
    }
    server.stop();
  }
}
