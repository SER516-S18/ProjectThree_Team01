package client.gui.panels;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class ColorChooserPanel extends JDialog{
	JPanel contentPane;
	BoxesPanel box;
	List<JPanel> palettes = new ArrayList<JPanel>();
	List<Color> colorArray = new ArrayList<Color>();
	JPanel colorPanel;
	

	public ColorChooserPanel (PerformanceMetricPanel parent, BoxesPanel box) {
		// super(parent);
		this.box = box;
		
		contentPane = new JPanel();
		setSize(195, 100);
		setContentPane(contentPane);
		setColors();
		colorChooser();
	}

	public ColorChooserPanel () {
		contentPane = new JPanel();
		setSize(195, 100);
		setContentPane(contentPane);
		setColors();
		colorChooser();
	}
	
	private void setColors() {
		setUndecorated(true); 
		colorArray.add(new Color(255,0,0));
		colorArray.add(new Color(244, 164, 96));
		colorArray.add(new Color(255, 69, 0));
		colorArray.add(Color.YELLOW);
		colorArray.add(Color.CYAN);
		colorArray.add(Color.BLUE);
		
		colorArray.add(new Color(135, 206, 250));
		colorArray.add(new Color(0, 191, 255));
		colorArray.add(new Color(0, 0, 128));
		colorArray.add(new Color(204, 204, 255));
		colorArray.add(new Color(255, 20, 147));
		colorArray.add(new Color(165, 42, 42));
		
		colorArray.add(new Color(154, 205, 50));
		colorArray.add(new Color(34, 139, 34));
		colorArray.add(new Color(210, 180, 140));
		colorArray.add(new Color(255, 255, 240));
		colorArray.add(new Color(255, 228, 225));
		colorArray.add(new Color(169,169,169));
	}

	public void colorChooser() {
		setBounds(0, 0, 125, 68);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.setLayout(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel outerPanel = new JPanel();
		outerPanel.setBounds(97, 6, 1, 1);
		contentPane.add(outerPanel);
		colorPanel = new JPanel();
		Color color1=new Color(255,0,0);
		colorPanel.setBackground(color1);
		int x = 8;
		int y = 8;
		
		for (int i = 0; i < 18; i++) {
			colorPanel = new JPanel();
			colorPanel.setBackground(colorArray.get(i));
			colorPanel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println(e);
					box.setBoxColor(((JPanel)e.getSource()).getBackground());
					setVisible(false);
					dispose();
				}
			});
			
			if (i > 0 && i % 6 == 0) {
				y += 8 + 10;
				x = 8;
			}
			colorPanel.setBounds(x, y, 16, 16);
			palettes.add(colorPanel);
			x += 8 + 10;
			outerPanel.add(colorPanel);
		}
		contentPane.add(outerPanel);
		outerPanel.setLayout(null);
		
		JLabel lblX = new JLabel("X");		
		lblX.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setHorizontalTextPosition(SwingConstants.CENTER);
		lblX.setVerticalAlignment(SwingConstants.CENTER);
		lblX.setBounds(0, 0, 16, 16);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				box.setBoxColor(colorArray.get(colorArray.size() - 1));
				setVisible(false);
				dispose();
			}
		});
		palettes.get(palettes.size() - 1).add(lblX, BorderLayout.CENTER);
		setVisible(true);
	}
}
