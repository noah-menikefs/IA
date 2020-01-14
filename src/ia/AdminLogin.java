package ia;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class AdminLogin extends JFrame {

	//GUI Variables
	private JPanel panel = new JPanel(new GridBagLayout());
	private JPanel titlePanel = new JPanel(); //constructed a JPanel object 
	
	private JLabel titletext = new JLabel("Admin Login");
	private JLabel uName = new JLabel("Username: ");
	private JLabel pWord = new JLabel("Password: ");
	private JLabel wrong = new JLabel("The username or password is incorrect.");
	
	private JTextField userField = new JTextField("",20);
	private JPasswordField passField = new JPasswordField("",20);
	
	
	private JButton exitBTN = new JButton("EXIT");
	private JButton loginBTN = new JButton("Submit");
	
	private ActionListener exitAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			userField.setText("");
			passField.setText("");
			wrong.setVisible(false);
			pack();
			c1.activateWindow(0);
		}
	};
	private ActionListener loginAction = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String u = userField.getText();
			char[] pw = passField.getPassword();
			if (c1.checkPassword(u,pw,c1.getData(1))) {
				userField.setText("");
				passField.setText("");
				wrong.setVisible(false);
				pack();
				c1.activateWindow(5);
			}
			else {
				wrong.setVisible(true);
				pack();
			}
			
		}
		
	};
	
	//Data Variables
	private Controller c1;
	
	public AdminLogin(Controller c) {
	
		c1 = c;
		
this.setLayout(new GridBagLayout());
		
		GridBagConstraints titlePanelC = new GridBagConstraints();
		titlePanelC.gridx = 0;
		titlePanelC.gridy = 0;
		titlePanelC.gridwidth = 11;
		titlePanelC.anchor = GridBagConstraints.NORTH;
		
		titletext.setFont(c1.title);
		titletext.setForeground(c1.WHITE);
		titlePanel.setPreferredSize(new Dimension(450,60));
		
		titlePanel.add(titletext);
		titlePanel.setBackground(c1.GREEN);
		titlePanel.setBorder(new EmptyBorder(30,30,30,30));
		
		add(titlePanel,titlePanelC);
		
		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridx = 0;
		c1.gridy = 2;
		
		exitBTN.addActionListener(exitAction);
		
		panel.add(exitBTN,c1);

		
		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 0;
		c3.gridy = 4;

		panel.add(uName,c3);
		
		GridBagConstraints c4 = new GridBagConstraints();
		c4.gridx = 0;
		c4.gridy = 5;

		panel.add(pWord,c4);
		
		GridBagConstraints c5 = new GridBagConstraints();
		c5.gridx = 1;
		c5.gridy = 4;
		c5.gridwidth = 2;

		panel.add(userField,c5);
		
		GridBagConstraints c6 = new GridBagConstraints();
		c6.gridx = 1;
		c6.gridy = 5;

		panel.add(passField,c6);
		
		GridBagConstraints c7 = new GridBagConstraints();
		c7.gridx = 1;
		c7.gridy = 6;
		c7.gridwidth = 2;

		loginBTN.addActionListener(loginAction);
		panel.add(loginBTN,c7);
		
		GridBagConstraints c8 = new GridBagConstraints();
		c8.gridx = 1;
		c8.gridy = 8;
		//c8.gridwidth = 2;
		
		wrong.setForeground(c.RED);
		wrong.setVisible(false);

		panel.add(wrong,c8);
		
		
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
	
}
