package server.gui.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import util.UpDownButton;

public class EmoMouse implements MouseListener {
  String item;
  JPanel actionClass;

  public EmoMouse(JPanel actionClass, String item) {
    this.actionClass = actionClass;
    this.item = item;
  }

  @Override
  public void mouseClicked(MouseEvent arg0) {
    System.out.println("Mouse Clicked");
    System.out.println(item);
    if (actionClass instanceof UpDownButton) {
      if (item == "increment")
        ((UpDownButton) actionClass).incrementOutputText();
      else
        ((UpDownButton) actionClass).decrementOutputText();
    }
  }

  @Override
  public void mouseEntered(MouseEvent arg0) {
    if (actionClass instanceof UpDownButton) {
    }
  }

  @Override
  public void mouseExited(MouseEvent arg0) {
    if (actionClass instanceof UpDownButton) {
      if (item == "increment")
        ((UpDownButton) actionClass).incrementOutputText();
      else
        ((UpDownButton) actionClass).decrementOutputText();
    }
  }

  @Override
  public void mousePressed(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseReleased(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

}
