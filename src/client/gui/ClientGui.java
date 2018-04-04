import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;

public class ClientGui extends JFrame {
	JFrame f = new JFrame();

	private JPanel contentPane;
	private final JPanel panel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGui frame = new ClientGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1295, 715);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("MENU");
		menu.setIcon(new ImageIcon("icons8-menu-10.png"));
		menuBar.add(menu);
		
		JMenu applicationsOption = new JMenu("APPLICATION");
		menu.add(applicationsOption);
		
		JMenuItem composerOption = new JMenuItem("EMOTIV Xavier Composer");
		applicationsOption.add(composerOption);
		
		JMenuItem screenshotOption = new JMenuItem("Save Current Screnshot");
		applicationsOption.add(screenshotOption);
		
		JMenu connectOption = new JMenu("CONNECT");
		menu.add(connectOption);
		
		JMenuItem connectComposer = new JMenuItem("Connect Composer");
		connectOption.add(connectComposer);
		
		JMenuItem reconnectComposer = new JMenuItem("Reconnect Composer");
		connectOption.add(reconnectComposer);
		
		JMenu detectionsOption = new JMenu("DETECTIONS");
		menu.add(detectionsOption);
		
		JMenuItem facialExpressions = new JMenuItem("Facial Expressions");
		detectionsOption.add(facialExpressions);
		
		JMenuItem performanceMetrices = new JMenuItem("Performance Metrics");
		detectionsOption.add(performanceMetrices);
		
		JMenu helpOption = new JMenu("HELP");
		menu.add(helpOption);
		
		JMenuItem emotivOnGithub = new JMenuItem("Emotiv On Github");
		helpOption.add(emotivOnGithub);
		
		JMenuItem aboutOption = new JMenuItem("About Xavier Control Panel");
		helpOption.add(aboutOption);
		
		JLabel labelEmotiv = new JLabel("EMOTIV");
		labelEmotiv.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		labelEmotiv.setBounds(618, 20, 88,19);
		panel.add(labelEmotiv);
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBackground(new Color(220,220,220));
		panel.setBounds(0, -20, 1295, 713);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panelEpocElectrodes = new JPanel();
		panelEpocElectrodes.setBounds(24, 45, 610, 587);
		panelEpocElectrodes.setBackground(new Color(192,192,192));
		
		JPanel panelSetupAndPairing = new JPanel();
		panelSetupAndPairing.setBackground(new Color(255, 255, 255));
		panelSetupAndPairing.setBounds(660, 45, 610, 587);
		
		
		panel.add(panelEpocElectrodes);
		ImageIcon iconHead = new ImageIcon("head.jpg");
	    	JLabel labelTimerHead = new JLabel();
	    	labelTimerHead.setIcon(iconHead);
	    	labelTimerHead.setBounds(10, 71, 604, 516);
	    	panelEpocElectrodes.add(labelTimerHead);
		
		panelEpocElectrodes.setLayout(null);
		
		JPanel panelEpocHeading = new JPanel();
		panelEpocHeading.setBounds(0, 0, 610, 65);
		panelEpocElectrodes.add(panelEpocHeading);
		panelEpocHeading.setBackground(new Color(128,128,128));
		panelEpocHeading.setLayout(null);
		
		JLabel labelEpocElectrodes = new JLabel("EPOC Electrodes Contact Quality");
		labelEpocElectrodes.setBounds(17, 5, 385, 54);
		labelEpocElectrodes.setForeground(Color.WHITE);
		labelEpocElectrodes.setFont(new Font("Lucida Grande", Font.BOLD, 21));
		panelEpocHeading.add(labelEpocElectrodes);
		
		panel.add(panelSetupAndPairing);
		
		panelSetupAndPairing.setLayout(null);
		
		ImageIcon iconStateSymbol = new ImageIcon("bottomRightText.jpg");
	  	JLabel labelTimerStateSymbol = new JLabel();
	  	labelTimerStateSymbol.setIcon(iconStateSymbol);
	  	labelTimerStateSymbol.setBounds(0, 352, 604, 229);
	   	panelSetupAndPairing.add(labelTimerStateSymbol);
		
		JPanel panelSetupHeading = new JPanel();
		panelSetupHeading.setBounds(0, 0, 610, 65);
		panelSetupAndPairing.add(panelSetupHeading);
		panelSetupHeading.setBackground(Color.GRAY);
		panelSetupHeading.setLayout(null);
		
		JLabel labelSetupAndPairing = new JLabel("Setup And Pairing");
		labelSetupAndPairing.setFont(new Font("Lucida Grande", Font.BOLD, 21));
		labelSetupAndPairing.setForeground(Color.WHITE);
		labelSetupAndPairing.setBounds(16, 6, 195, 53);
		panelSetupHeading.add(labelSetupAndPairing);
		
		JTextArea textHydratingTheSensors = new JTextArea();
		textHydratingTheSensors.setText("Hydrating the Sensors\nFully saturate the "
				+ "felt pads using saline solution . When the headset is placed on "
				+ "your scalp,\nthe pads should feel wet .\n\nTurning On the Headset"
				+ "\nThe power switch is at the rear of the headset .A light indicates"
				+ " when the headset is on.\n\nPairing the Headset\nDevices with "
				+ "Bluetooth Low Energy (BTLE):\nThe EPOC+ headset can pair using "
				+ "BTLE. Make sure that your device's bluetooth connection \nis "
				+ "turned on. The headset should pair automatically.\n\nUsing an "
				+ "Emotiv USB Dongle:\nPlug the dongle into your device's USB port "
				+ ".Dongles marked EPOC and EPOC+ have lights that\n indicate "
				+ "connection status. When a headset is paired, the indicator "
				+ "light should flicker rapidly. If it is blinking slowly or is"
				+ " not illuminated, reinsert the dongle\n and try again. You may "
				+ "also try turning the headset off and on again to establish a "
				+ "connection.");
		textHydratingTheSensors.setBounds(10, 77, 600, 263);
		panelSetupAndPairing.add(textHydratingTheSensors);
		
		JPanel panelTimer = new JPanel();
		panelTimer.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTimer.setBounds(1122, 20, 53, 25);
		panel.add(panelTimer);
		
		ImageIcon timerIcon = new ImageIcon("icons8-timer-19.png");
	    	JLabel labelTimerImage = new JLabel();
	    	labelTimerImage.setForeground(Color.BLACK);
	    	labelTimerImage.setIcon(timerIcon);
	    	labelTimerImage.setBounds(1076, 20, 30, 25);
	    	panel.add(labelTimerImage);
		JLabel labelTimer = new JLabel("00:00");
		panelTimer.add(labelTimer);
		labelTimer.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
	}
}
