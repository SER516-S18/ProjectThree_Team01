package server.sys;

import org.glassfish.tyrus.server.Server;

/**
 * This thread's sole purpose is to close the server gracefully once isClosing state is
 * changed, until then it only stays idle
 * 
 * @author carmstr7
 *
 */
public class ServerThread implements Runnable {
  private Server server;
  // public static volatile boolean isClosing = false;

  public ServerThread(Server server) {
    this.server = server;
  }

  @Override
  public void run() {
    synchronized (this) {
      try {
        wait();
        server.stop();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }
}
