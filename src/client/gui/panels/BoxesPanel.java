package client.gui.panels;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class BoxesPanel extends JPanel {
	PerformanceMetricPanel parent;
	int actionListenerFlag = 0;
	public BoxesPanel(PerformanceMetricPanel parent, String boxName) {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.parent = parent;
		setLayout(new BorderLayout(0,0));
		initializer(boxName);
	}
	
	public void initializer(String boxName) {
		String initial = boxName.substring(0, 2);
		setSize(90, 90);
		JLabel lblNewLabel_1 = new JLabel(initial);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setFont(new Font("Century", Font.BOLD, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblInterest = new JLabel(boxName);
		lblInterest.setVerticalAlignment(SwingConstants.TOP);
		lblInterest.setFont(new Font("Century", Font.PLAIN, 14));
		lblInterest.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_2 = new JLabel("V    ");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createColorChooser();
			}
		});
		lblNewLabel_2.setSize(20, 20);
		lblNewLabel_2.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 10));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		add(lblNewLabel_2, BorderLayout.NORTH);
		add(lblNewLabel_1, BorderLayout.CENTER);
		add(lblInterest, BorderLayout.SOUTH);
	}
	
	public void setBoxColor(Color color) {
		setBackground(color);
	}
	
	private void createColorChooser() {
		System.out.println("Creating Chooser");
		ColorChooserPanel cc = new ColorChooserPanel(parent, this);
		cc.setLocationRelativeTo(this);
		cc.setVisible(true);
		add(cc);
	}
}
