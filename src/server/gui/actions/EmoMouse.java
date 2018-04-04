package server.gui.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import util.UpDownButton;

public class EmoMouse implements MouseListener {
  JLabel item;
  JPanel actionClass;

  public EmoMouse(JPanel actionClass, JLabel item) {
    this.actionClass = actionClass;
    this.item = item;
  }

  @Override
  public void mouseClicked(MouseEvent arg0) {
    if (actionClass instanceof UpDownButton) {
      JLabel label = (JLabel) arg0.getSource();
      if (label.getText().trim() == "v")
        ((UpDownButton) actionClass).decrementOutputText();
      else
        ((UpDownButton) actionClass).incrementOutputText();
    }
  }

  @Override
  public void mouseEntered(MouseEvent arg0) {
    if (actionClass instanceof UpDownButton) {
      JLabel label = (JLabel) arg0.getSource();
      ((UpDownButton) actionClass).showHideBorder(label, true);
    }
  }

  @Override
  public void mouseExited(MouseEvent arg0) {
    if (actionClass instanceof UpDownButton) {
      JLabel label = (JLabel) arg0.getSource();
      ((UpDownButton) actionClass).showHideBorder(label, false);
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
