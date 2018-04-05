package server.gui.actions;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import server.gui.panels.HamburgerMenu;
import server.gui.panels.MenuBarPanel;
import server.gui.panels.SignalMenu;
import util.UpDownButton;

/**
 * This class is responsible for handling Mouse Events triggered from the server
 * 
 * @author Cephas Armstrong-Mensah
 *
 */
public class EmoMouse implements MouseListener {
  Component actionClass;

  public EmoMouse(Component actionClass) {
    this.actionClass = actionClass;
  }

  @Override
  public void mouseClicked(MouseEvent arg0) {
    JLabel label = null;
    if (arg0.getSource() instanceof JLabel) {
      label = (JLabel) arg0.getSource();
    }

    System.out.println("Get source instance: " + arg0.getSource());
    if (actionClass instanceof UpDownButton) {
      if (label.getText().trim() == "v")
        ((UpDownButton) actionClass).decrementOutputText();
      else
        ((UpDownButton) actionClass).incrementOutputText();
    } else if (actionClass instanceof MenuBarPanel) {
      System.out.println("MenuBarPanel instance");
      String icon = label.getIcon().toString();
      if (icon.contains("menu.png")) {
        ((MenuBarPanel) actionClass).showExitMenu();
      } else if (icon.contains("strong.png") || icon.contains("weak.png")) {
        ((MenuBarPanel) actionClass).showSignalMenu();
      }
    } else if (actionClass instanceof HamburgerMenu) {
      System.out.println("Hamburger instance");
      ((HamburgerMenu) actionClass).triggerActionEvent(label);
    } else if (actionClass instanceof SignalMenu) {
      System.out.println("SignalMenu instance");
      ((SignalMenu) actionClass).triggerActionEvent(arg0.getComponent());
    }
  }

  @Override
  public void mouseEntered(MouseEvent arg0) {
    JLabel label = null;
    if (arg0.getSource() instanceof JLabel) {
      label = (JLabel) arg0.getSource();
    }

    if (actionClass instanceof UpDownButton) {
      ((UpDownButton) actionClass).showHideBorder(label, true);
    } else if (actionClass instanceof MenuBarPanel) {
      ((MenuBarPanel) actionClass).showHideBorder(label, true);
    }
  }

  @Override
  public void mouseExited(MouseEvent arg0) {
    JLabel label = null;
    if (arg0.getSource() instanceof JLabel) {
      label = (JLabel) arg0.getSource();
    }

    if (actionClass instanceof UpDownButton) {
      ((UpDownButton) actionClass).showHideBorder(label, false);
    } else if (actionClass instanceof MenuBarPanel) {
      ((MenuBarPanel) actionClass).showHideBorder(label, false);
    } else if (actionClass instanceof HamburgerMenu) {
      ((HamburgerMenu) actionClass).setVisibleFalse(arg0.getComponent());
    } else if (actionClass instanceof SignalMenu) {
      ((SignalMenu) actionClass).setVisibleFalse(arg0.getComponent());
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
