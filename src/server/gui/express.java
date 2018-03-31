package server.gui;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.DropMode;
import util.UpDownButtonExpress;

public class express {

	private JFrame frame;

	  private static JPanel contentPane;
	
	  private Thread clientThread;
	 // private Client client;
	  JComboBox<String> comboBox;
	  private JLabel actualLowLabel;
	  private JLabel actualHighLabel;
	  private JLabel actualAverageLabel;
	  private JLabel actualFrequencyLabel;
	  private JPanel containerPanel;
	  private JPanel graphPanel;
	  
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					express window = new express();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public express() {
		initialize();

	    
	    
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 496, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel epressPanel = new JPanel();
		epressPanel.setBounds(10, 305, 460, 184);
		frame.getContentPane().add(epressPanel);
		
		UpDownButtonExpress upperfaceupdownButton = new UpDownButtonExpress(30, 0.1, true);
		upperfaceupdownButton.setBounds(341, 42, 98, 51);
		upperfaceupdownButton.setVisible(true);
		
		UpDownButtonExpress lowerfaceupdownButton = new UpDownButtonExpress(30, 0.1, true);
		lowerfaceupdownButton.setBounds(110, 42, 98, 51);
		lowerfaceupdownButton.setVisible(true);
		
		
		JComboBox upperfaceComboBox = new JComboBox();
		upperfaceComboBox.setBounds(10, 56, 99, 30);
		upperfaceComboBox.setModel(new DefaultComboBoxModel(new String[] {"Raise Brow", "Furrow Brow"}));
		
		JComboBox lowerfaceComboBox = new JComboBox();
		lowerfaceComboBox.setBounds(243, 56, 99, 30);
		lowerfaceComboBox.setModel(new DefaultComboBoxModel(new String[] {"Simile", "Clench", "Smirk Left", "Smirk Right", "Laugh"}));
		
		JLabel upperfaceLabel = new JLabel("Upperface");
		upperfaceLabel.setBounds(10, 25, 99, 30);
		upperfaceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lowerfaceLabel = new JLabel("Lowerface");
		lowerfaceLabel.setBounds(243, 25, 99, 30);
		lowerfaceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel eyeLabel = new JLabel("Eye");
		eyeLabel.setBounds(10, 111, 99, 31);
		eyeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JComboBox eyecomboBox = new JComboBox();
		eyecomboBox.setBounds(10, 139, 99, 34);
		eyecomboBox.setModel(new DefaultComboBoxModel(new String[] {"Blink", "Wink Left", "Wink Right", "Look Left", "Look Right"}));
		
		epressPanel.setLayout(null);
		epressPanel.add(upperfaceupdownButton);
		epressPanel.add(upperfaceLabel);
		epressPanel.add(lowerfaceLabel);
		epressPanel.add(eyeLabel);
		epressPanel.add(upperfaceComboBox);
		epressPanel.add(eyecomboBox);
		epressPanel.add(lowerfaceComboBox);
		epressPanel.add(lowerfaceupdownButton);
		
		JRadioButton eyeActive = new JRadioButton("Active");
		eyeActive.setBounds(129, 145, 63, 23);
		epressPanel.add(eyeActive);
		
		
		//get number
		//double number = Double.parseDouble(x);
		//System.out.println(x);
		//System.out.println(number);
	}
}
