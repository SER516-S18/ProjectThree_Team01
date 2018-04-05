import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The FacePanel implements a JPanel to create a Panel for displaying
 * different facial expressions
 *
 * @author Sanchari Banerjee
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 2018-04-04
 *
 */

public class ClientFacialExpression extends JFrame {
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
                    ClientFacialExpression frame = new ClientFacialExpression();
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
    public ClientFacialExpression() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1295, 715);


        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        panel.setBackground(new Color(220,220,220));
        panel.setBounds(0, -20, 1295, 713);
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel panelFace = new JPanel();
        panelFace.setBounds(24, 45, 610, 587);
        panelFace.setBackground(new Color(192,192,192));

        JPanel panelSensitivity = new JPanel();
        panelSensitivity.setBackground(new Color(64, 64, 64));
        panelSensitivity.setBounds(660, 45, 610, 587);

        panel.add(panelFace);

        panelFace.setLayout(null);

        JPanel panelEpocHeading = new JPanel();
        panelEpocHeading.setBounds(0, 0, 610, 65);
        panelFace.add(panelEpocHeading);
        panelEpocHeading.setBackground(new Color(128,128,128));
        panelEpocHeading.setLayout(null);

        JLabel labelFace = new JLabel("Facial Expressions");
        labelFace.setBounds(17, 5, 385, 54);
        labelFace.setForeground(Color.WHITE);
        labelFace.setFont(new Font("Lucida Grande", Font.BOLD, 21));
        panelEpocHeading.add(labelFace);

        panel.add(panelSensitivity);

        panelSensitivity.setLayout(null);

        ImageIcon iconHead = new ImageIcon("img/face.jpg");
        JLabel labelTimerHead = new JLabel();
        labelTimerHead.setIcon(iconHead);
        labelTimerHead.setBounds(10, 71, 604, 516);
        panelFace.add(labelTimerHead);

        JPanel panelSetupHeading = new JPanel();
        panelSetupHeading.setBounds(0, 0, 610, 65);
        panelSensitivity.add(panelSetupHeading);
        panelSetupHeading.setBackground(Color.GRAY);
        panelSetupHeading.setLayout(null);

        JLabel labelSensitivity = new JLabel("Sensitivity");
        labelSensitivity.setFont(new Font("Lucida Grande", Font.BOLD, 21));
        labelSensitivity.setForeground(Color.WHITE);
        labelSensitivity.setBounds(16, 6, 195, 53);
        panelSetupHeading.add(labelSensitivity);


        JPanel sliderPanel = new JPanel(new GridBagLayout());
        sliderPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        sliderPanel.setBounds(10, 77, 600, 500);
        sliderPanel.setBackground(new Color(64, 64, 64));

        java.util.Hashtable<Integer,JLabel> labelTable = new java.util.Hashtable<Integer,JLabel>();
        labelTable.put(new Integer(100), new JLabel("1.0"));
        labelTable.put(new Integer(75), new JLabel("0.75"));
        labelTable.put(new Integer(50), new JLabel("0.50"));
        labelTable.put(new Integer(25), new JLabel("0.25"));
        labelTable.put(new Integer(0), new JLabel("0.0"));

        String[] txtLabel = {"Blink","Left Wink","Right Wink","Look L/R","Furrow Brow","Raise brow","Smile","Clench","Left Smirk","Right Smirk","Laugh"};

        int rows = 11;
        int count = 0;
        for(int i = 0;i< rows;i++){
            JSlider sldr = new JSlider(0,100,50);
            JLabel Lbl = new JLabel(txtLabel[i]);
            Lbl.setForeground(Color.WHITE);
            JTextField txt = new JTextField(4);
            sldr.setPaintTicks(true);
            sldr.setLabelTable(labelTable);
            sldr.setPaintLabels(true);
            sldr.setMajorTickSpacing(25);
            sldr.setBounds(220,5+count,300,40);
            count = count+50;
            sldr.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    txt.setText("" + ((JSlider)e.getSource()).getValue()/100.0);
                }
            });
            c.gridx = 0;
            c.gridy = i;
            c.weightx = 0.5;
            sliderPanel.add(Lbl,c);

            c.gridx = 1;
            c.gridy = i;
            c.weightx = 0.75;
            sliderPanel.add(sldr,c);

            c.gridx = 2;
            c.gridy = i;
            c.weightx = 0.5;
            sliderPanel.add(txt,c);

        }
        panelSensitivity.add(sliderPanel);

    }
}
