package server.gui.panels;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import server.gui.EmotivComposer;
import server.gui.actions.MouseEvents;

/**
 * This class's purpose is to display an about box when the menu item is selected
 * 
 * @author Cephas Armstrong-Mensah
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 05APR2018
 *
 */
public class AboutPanel extends JDialog {
  private static final long serialVersionUID = -8957547638077368275L;

  private JPanel contentPane;
  private JScrollPane scrollPane;
  private JLabel aboutPanel;

  public AboutPanel(EmotivComposer parent) {
    super(parent);
    setUndecorated(true);
    setSize(350, 450);
    setLocationRelativeTo(parent);

    contentPane = new JPanel();
    contentPane.setLayout(null);
    setContentPane(contentPane);

    initialize();
  }

  private void initialize() {
    scrollPane = new JScrollPane();
    scrollPane.setBounds(5, 5, 340, 440);
    scrollPane.getViewport().setBackground(Color.WHITE);

    aboutPanel = new JLabel(getHtml());
    aboutPanel.setVerticalAlignment(SwingConstants.TOP);

    scrollPane.setViewportView(aboutPanel);
    contentPane.add(scrollPane);

    setVisible(true);
    addMouseListener(new MouseEvents(this, "about"));
  }

  public void disposeScrollPane() {
    dispose();
  }

  private String getHtml() {
    String html = "<html><body><h2>Project 3 - SER 516</h2><div><p><h3>Scrum Master</h3> Patrik Bartakke</p>"
        + "<p><h3>Development Teams</h3></p><p><h3>Client Side</h3></p><ul><li>Chetanya Ahuja</li><li>Nikita Bahl</li>"
        + "<li>Sanchari Banerjee</li><li>Jahnavi Bantupalli</li><li>Karansher Bhangal</li><li>Shilpa Bhat</li>"
        + "<li>Debarati Bhattacharyya</li></ul><p><h3>Server Side</h3></p><ul><li>Cephas Armstrong-Mensah</li>"
        + "<li>Vihar Bhatt</li><li>Zelin Bao</li> </ul></div><p><h3>Tests</h3></p><ul><li>Shibani Arvind Hegde</li>"
        + "<li>Kritika Bhat</li></ul></div></body></html>";

    return html;
  }
}
