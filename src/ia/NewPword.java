package ia;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class NewPword extends JFrame {

	private JPanel panel = new JPanel(new GridBagLayout());
	private JPanel titlePanel = new JPanel();
	
	private JLabel titletext = new JLabel("New Password");
	
	private JLabel uName = new JLabel("Username: ");
	private JLabel pWord = new JLabel("New Password: ");
	private JLabel cpWord = new JLabel("Confirm New Password: ");
	private JLabel notFound = new JLabel("This username is not in the system");
	private JLabel mismatch = new JLabel("The passwords do not match");
	private JLabel success = new JLabel("Success! The password has been changed");
	
	private JTextField userField = new JTextField("",20);
	private JPasswordField passField = new JPasswordField("",20);
	private JPasswordField cpassField = new JPasswordField("",20);
	
	
	private JButton exitBTN = new JButton("EXIT");
	private JButton submitBTN = new JButton("Submit");

	private Controller c1;
	
	
	//LISTENERS - These are the functions called when something happens
	private ActionListener exitAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			success.setVisible(false);
			notFound.setVisible(false);
			mismatch.setVisible(false);
			pack();
			
			userField.setText("");
			passField.setText("");
			cpassField.setText("");
			
			c1.activateWindow(5);
		}	
	};
	private ActionListener submitAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			success.setVisible(false);
			notFound.setVisible(false);
			mismatch.setVisible(false);
			pack();
			
			String u = userField.getText();
			char[] pw = passField.getPassword();
			char[] cpw = cpassField.getPassword();
			boolean good = true;
			
			if (c1.checkUsername(u)) {
				good = false;
				notFound.setVisible(true);
				pack();
			}
			if (c1.pWordMatch(pw, cpw) == false) {
				good = false;
				mismatch.setVisible(true);
				pack();
			}
			if (good == true) {
				try {
					c1.changePword(u,pw);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("Error when creating new txt");
				}
				success.setVisible(true);
				userField.setText("");
				passField.setText("");
				cpassField.setText("");
				pack();
			}
			
		}	
	};
	
	public NewPword(Controller c) {
		
		c1 = c;
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints titlePanelC = new GridBagConstraints();
		titlePanelC.gridx = 0;
		titlePanelC.gridy = 0;
		titlePanelC.gridwidth = 11;
		titlePanelC.anchor = GridBagConstraints.NORTH;
		
		titletext.setFont(c1.title);
		titletext.setForeground(c1.WHITE);
		titlePanel.setPreferredSize(new Dimension(675,60));
		
		titlePanel.add(titletext);
		titlePanel.setBackground(c1.GREEN);
		titlePanel.setBorder(new EmptyBorder(30,30,30,30));
		
		add(titlePanel,titlePanelC);
		
		//every widget needs to have a GridBagConstraints associated with it
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;
		
		exitBTN.addActionListener(exitAction);
		
		panel.add(exitBTN,c2);
		
		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 1;
		c3.gridy = 1;
		
		panel.add(uName,c3);
		
		GridBagConstraints c4 = new GridBagConstraints();
		c4.gridx = 2;
		c4.gridy = 1;
		c4.gridwidth = 2;
		
		panel.add(userField,c4);
		
		GridBagConstraints c5 = new GridBagConstraints();
		c5.gridx = 1;
		c5.gridy = 2;
		
		panel.add(pWord,c5);
		
		GridBagConstraints c6 = new GridBagConstraints();
		c6.gridx = 2;
		c6.gridy = 2;
		c6.gridwidth = 2;
		
		panel.add(passField,c6);
		
		GridBagConstraints c7 = new GridBagConstraints();
		c7.gridx = 2;
		c7.gridy = 4;
		c7.gridwidth = 2;

		submitBTN.addActionListener(submitAction);
		panel.add(submitBTN,c7);
		
		GridBagConstraints c8 = new GridBagConstraints();
		c8.gridx = 1;
		c8.gridy = 3;
		
		panel.add(cpWord,c8);
		
		GridBagConstraints c9 = new GridBagConstraints();
		c9.gridx = 2;
		c9.gridy = 3;
		c9.gridwidth = 2;
		
		panel.add(cpassField,c9);
		
		GridBagConstraints c10 = new GridBagConstraints();
		c10.gridx = 1;
		c10.gridy = 5;
		c10.gridwidth = 2;
		success.setForeground(c.GREEN);
		success.setVisible(false);
		
		panel.add(success,c10);
		
		GridBagConstraints c11 = new GridBagConstraints();
		c11.gridx = 1;
		c11.gridy = 6;
		c11.gridwidth = 2;
		notFound.setForeground(c.RED);
		notFound.setVisible(false);
		
		panel.add(notFound,c11);
		
		GridBagConstraints c12 = new GridBagConstraints();
		c12.gridx = 1;
		c12.gridy = 7;
		c12.gridwidth = 2;
		mismatch.setForeground(c.RED);
		mismatch.setVisible(false);
		
		panel.add(mismatch,c12);
		
		//Last Thing you have to do is
		//Set Default Close operation on Frame.
		GridBagConstraints panel2 = new GridBagConstraints();
		panel2.gridx = 0;
		panel2.gridy = 1;
		panel2.gridwidth = 4;
		
		panel.setBorder(new EmptyBorder(0,30,30,30));
		
		add(panel,panel2);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack(); //Auto Adjusts everything
		this.setVisible(false); //last thing
		
	}
}//end class
