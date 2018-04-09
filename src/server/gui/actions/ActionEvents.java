package server.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import server.gui.panels.ComboControl;
import server.gui.panels.FacialPanel;
import server.gui.panels.InteractivePanel;
import server.gui.panels.LogPanel;

/**
 * This class is responsible for handling Action Events triggered from the
 * server
 * 
 * @author Cephas Armstrong-Mensah
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 03APR2018
 */
public class ActionEvents implements ActionListener {
  private JPanel actionClass;
  private String item;

  public ActionEvents(JPanel actionClass) {
    this.actionClass = actionClass;
  }

  public ActionEvents(JPanel actionClass, String item) {
    this.actionClass = actionClass;
    this.item = item;
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    if (actionClass instanceof ComboControl) {
      ((ComboControl) actionClass).validateOutputText();
    } else if (actionClass instanceof InteractivePanel) {
      ((InteractivePanel) actionClass).triggerStartStopSend();

    } else if (actionClass instanceof FacialPanel) {
      if (item == "upperfaceComboBox") {
        ((FacialPanel) actionClass).upperfaceAction();
      }

      else if (item == "lowerfaceComboBox") {
        ((FacialPanel) actionClass).lowerfaceAction();

      } else if (item == "eyeComboBox") {
        ((FacialPanel) actionClass).eyecomboBoxAction();

      } else if (item == "eyeActive") {
        ((FacialPanel) actionClass).eyeAction();
      }

    } else if (actionClass instanceof LogPanel) {
      ((LogPanel) actionClass).clearConsolePanel();
    }
  }
}
