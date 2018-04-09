package server.gui.tabs;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;

import server.gui.panels.InteractivePanel;
import server.sys.EmotivRandomizer;

/**
 * This purpose of this class is to abstract the Tab implementation and its component
 * 
 * @author Cephas Armstrong-Mensah
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 09APR2018
 *
 */
public class UpperTabbedPane extends JTabbedPane {

  private static final long serialVersionUID = -3848367030632409535L;
  private InteractivePanel interactivePanel;
  private EmotivRandomizer er;
  private JPanel emostate;

  public UpperTabbedPane(EmotivRandomizer er) {
    setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
    setBounds(0, 0, 450, 120);
    this.er = er;

    initialize();
  }

  private void initialize() {
    interactivePanel = new InteractivePanel(er);
    interactivePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    interactivePanel.setAlignmentY(Component.TOP_ALIGNMENT);

    emostate = new JPanel();
    emostate.setLayout(null);

    addTab("EMOSCRIPT", null, emostate, null);
    addTab("INTERACTIVE", null, interactivePanel, null);

    setSelectedIndex(1);
    setEnabledAt(0, false);
  }
}
