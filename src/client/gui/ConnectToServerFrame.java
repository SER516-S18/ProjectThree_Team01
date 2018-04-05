package client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.sys.ClientSubject;
import client.sys.ClientThread;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConnectToServerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField ipAddressTextField;
	private JTextField portTextField;

	/**
	 * Create the frame.
	 */
	public ConnectToServerFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 404, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel hostAddresslabel = new JLabel("HOST ADDRESS");
		hostAddresslabel.setBounds(35, 16, 123, 39);
		contentPane.add(hostAddresslabel);
		
		JLabel portLabel = new JLabel("PORT");
		portLabel.setBounds(35, 86, 69, 20);
		contentPane.add(portLabel);
		
		ipAddressTextField = new JTextField();
		ipAddressTextField.setText("127.0.0.1");
		ipAddressTextField.setColumns(10);
		ipAddressTextField.setBounds(185, 22, 146, 26);
		contentPane.add(ipAddressTextField);
		
		portTextField = new JTextField();
		portTextField.setText("10001");
		portTextField.setColumns(10);
		portTextField.setBounds(185, 83, 146, 26);
		contentPane.add(portTextField);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    ConnectToServer();
			    setVisible(false);
			}
		});
		okButton.setBounds(35, 138, 115, 29);
		contentPane.add(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		cancelButton.setBounds(216, 138, 115, 29);
		contentPane.add(cancelButton);
	}
	
	private void ConnectToServer() {
	    new Thread(new ClientThread(ipAddressTextField.getText())).start();
	  }
}
