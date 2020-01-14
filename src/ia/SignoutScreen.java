package ia;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

public class SignoutScreen extends JFrame {

	//GUI Variables

	private String[] options = {"days","weeks","months"};
	
	private JPanel panel = new JPanel(new GridBagLayout());
	private JPanel titlePanel = new JPanel(); //constructed a JPanel object 
	
	private JButton exitBTN = new JButton("EXIT");
	private JButton submitBTN = new JButton("Submit");
	
	private String movieTitle;
	
	private JLabel titletext = new JLabel("Movie Sign Out");
	private JLabel movieLbl = new JLabel("Movie: "+movieTitle);
	private JLabel nameLbl = new JLabel("Full Name:");
	private JLabel lengthLbl = new JLabel("Intended sign out length:");
	private JLabel alert = new JLabel("");
	
	private JComboBox lengthField = new JComboBox(options);
	
	SpinnerModel lengthModel = new SpinnerNumberModel(1, 1, 100, 1);
	private JSpinner lengthNum = new JSpinner(lengthModel);
	
	private JTextField nameField = new JTextField("",20);
	
	private ActionListener exitAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			alert.setText("");
			alert.setVisible(false);
			submitBTN.setEnabled(true);
			c1.activateWindow(12);
		}
	};
	private ActionListener submitAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String name = nameField.getText();
			int num = (int) lengthNum.getValue();
			String option = (String) lengthField.getSelectedItem();
			String length = num+" "+option;
			String title = movieTitle;
			
			
			if (name.equals("")) {
				alert.setForeground(c1.RED);
				alert.setText("Please fill in all fields");
				alert.setVisible(true);
				pack();
			}
			else {
				alert.setForeground(c1.GREEN);
				alert.setText("Movie signed out");
				alert.setVisible(true);
				c1.movieSignout(name,length,title);
				lengthField.setSelectedIndex(0);
				lengthNum.setValue(1);
				nameField.setText("");
				submitBTN.setEnabled(false);
				pack();
			}
			
		}
	};
	//Data Variables
	private Controller c1;
	
	public SignoutScreen(Controller c) {
		
		c1 = c;
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints titlePanelC = new GridBagConstraints();
		titlePanelC.gridx = 0;
		titlePanelC.gridy = 0;
		titlePanelC.gridwidth = 11;
		titlePanelC.anchor = GridBagConstraints.NORTH;
		
		titletext.setFont(c1.title);
		titletext.setForeground(c1.WHITE);
		titlePanel.setPreferredSize(new Dimension(500,60));
		
		titlePanel.add(titletext);
		titlePanel.setBackground(c1.GREEN);
		titlePanel.setBorder(new EmptyBorder(30,30,30,30));
		
		add(titlePanel,titlePanelC);
		
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 1;
		c2.gridy = 0;
		
		exitBTN.addActionListener(exitAction);
		panel.add(exitBTN,c2);
		
		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 2;
		c3.gridy = 0;
		c3.gridwidth = 2;
		
		panel.add(movieLbl, c3);
		
		GridBagConstraints c4 = new GridBagConstraints();
		c4.gridx = 1;
		c4.gridy = 1;
		c4.anchor = GridBagConstraints.EAST;
		
		panel.add(nameLbl, c4);
		
		GridBagConstraints c5 = new GridBagConstraints();
		c5.gridx = 0;
		c5.gridy = 2;
		c5.gridwidth = 2;
		
		panel.add(lengthLbl, c5);
		
		GridBagConstraints c6 = new GridBagConstraints();
		c6.gridx = 2;
		c6.gridy = 1;
		c6.gridwidth = 2;
		
		panel.add(nameField, c6);
		
		GridBagConstraints c7 = new GridBagConstraints();
		c7.gridx = 2;
		c7.gridy = 2;
		
		panel.add(lengthNum, c7);

		GridBagConstraints c8 = new GridBagConstraints();
		c8.gridx = 3;
		c8.gridy = 2;
		c8.anchor = GridBagConstraints.WEST;
		
		panel.add(lengthField, c8);
		
		GridBagConstraints c11 = new GridBagConstraints();
		c11.gridx = 2;
		c11.gridy = 4;
		c11.gridwidth = 2;
		
		submitBTN.addActionListener(submitAction);
		
		panel.add(submitBTN, c11);
	
		GridBagConstraints c9 = new GridBagConstraints();
		c9.gridx = 2;
		c9.gridy = 5;
		c9.gridwidth = 2;
		
		alert.setVisible(false);
		
		panel.add(alert, c9);
		
		GridBagConstraints panel2 = new GridBagConstraints();
		panel2.gridx = 0;
		panel2.gridy = 1;
		panel2.gridwidth = 4;
		
		panel.setBorder(new EmptyBorder(0,30,30,30));
		
		add(panel,panel2);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.pack();
		this.setVisible(false);	
	}	
	
	public void displayTitle(String title) {
		movieTitle = title;
		movieLbl.setText("Movie: "+movieTitle);
		pack();
		
	}
}
