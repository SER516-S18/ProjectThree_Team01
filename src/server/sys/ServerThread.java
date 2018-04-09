package server.sys;

import org.glassfish.tyrus.server.Server;

/**
 * This thread's sole purpose is to close the server gracefully once @IsClosing
 * state is changed, until then it only stays idle
 * 
 * @author Cephas Armstrong-Mensah
 * @version 1.0
 * @since 28MAR2018
 * 
 */
public class ServerThread implements Runnable {
  private Server server;

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
