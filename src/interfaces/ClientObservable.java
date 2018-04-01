package interfaces;

import java.util.ArrayList;

public interface ClientObservable {
  void addObserver(ClientObserver observer);
  void removeObserver(ClientObserver observer);
  void notifyObserver(ArrayList<ClientObserver> observer);
}
