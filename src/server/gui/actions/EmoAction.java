package server.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import server.gui.panels.InteractivePanel;

public class EmoAction implements ActionListener {

  private JPanel actionItem;

  public EmoAction(JPanel actionItem) {
    this.actionItem = actionItem;
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    if (actionItem instanceof InteractivePanel) {
      ((InteractivePanel) actionItem).triggerStartStopSend();
    }
  }

}
