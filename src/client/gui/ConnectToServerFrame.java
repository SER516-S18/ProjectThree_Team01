package client.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.sys.ClientThread;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Shilpa Bhat
 * @version 1.0
 * @since 2018-04-03
 * The frame to provide ipadress and the port number to connect to the
 * server.
 */
public class ConnectToServerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField ipAddressTextField;
	private JTextField portTextField;
	int portNumber;

	/**
	 * Default ipaddress : 127.0.0.1 Default port number : 10001
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
				if (connectToServer()) {
					setVisible(false);
					EmotivControlPanel.getInstance().updateSignalLabel(true);
				}
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

	/**
	 * ClientThread tries to connect to the server at ipaddress given
	 */
	private boolean connectToServer() {
		if (checkValidPortNumber() && checkValidIpAddress()) {
			new Thread(new ClientThread(ipAddressTextField.getText(), portNumber)).start();
			return true;
		}
		return false;
	}

	/**
	 * Checks if the port number is in the range 1024 - 65535
	 * @return True if port number entered is valid
	 */
	private Boolean checkValidPortNumber() {
		String portErrorMessage = "Please enter a port number between 1024 and 65535!";
		try {
			portNumber = Integer.parseInt(portTextField.getText());
			if (portNumber < 1024 || portNumber > 65535) {
				JOptionPane.showMessageDialog(new JFrame(), portErrorMessage);
				portTextField.setText("");
				return false;
			} else {
				return true;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(new JFrame(), portErrorMessage);
			return false;
		}
	}
	
	/**
	 * Checks if the ipaddress is in the correct format
	 * @return True if it is in the correct format , false otherwise.
	 */
	private boolean checkValidIpAddress() {
		Pattern p = Pattern.compile(
				"^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
		Matcher m = p.matcher(ipAddressTextField.getText());
		if (!m.find()) {
			JOptionPane.showMessageDialog(new JFrame(), "Please enter ipAddress in proper format");
			return false;
		}
		return true;
	}
}
