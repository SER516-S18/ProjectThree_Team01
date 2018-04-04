package server.gui.actions;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;

import server.gui.panels.InteractivePanel;

public class EmoItemListener implements ItemListener {

  JPanel actionItem;

  public EmoItemListener(JPanel actionItem) {
    this.actionItem = actionItem;
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    if (actionItem instanceof InteractivePanel) {
      ((InteractivePanel) actionItem).itemStateAction();
    }
  }
}
