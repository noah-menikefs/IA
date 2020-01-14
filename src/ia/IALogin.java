package ia;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;


//JFrame is a built in library used to make
//a standard window.
public class IALogin extends JFrame {
	// Big idea: Since Login extends JFrame,
	// login inherits all attributes and behaviours of JFrame
	//"Login (Specific) is a JFrame (General)"
	
	//GUI ELEMENTS - Widgets that make up a window
	private JPanel panel = new JPanel(new GridBagLayout());
	private JPanel titlePanel = new JPanel();
	
	private JLabel titletext = new JLabel("UCC Movie Catalogue Login");
	private JLabel uName = new JLabel("Username:");
	private JLabel pWord = new JLabel("Password:");
	private JLabel newUser = new JLabel("New User?");
	private JLabel fpWord = new JLabel("Forgot Password?");
	private JLabel msg = new JLabel("Messages: ");
	
	private JTextField userField = new JTextField("",20);
	private JPasswordField passField = new JPasswordField("",20);
	
	private JButton loginBTN = new JButton("Submit");
	private JButton newUserBTN = new JButton("Register");
	private JButton resetBTN = new JButton("Reset");
	private JButton adminBTN = new JButton("Admin Login");
	
	private JTextPane msgs = new JTextPane();
	private Controller c1;
	
	
	//LISTENERS - These are the functions called when something happens
	private ActionListener loginAction = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String u = userField.getText();
			char[] pw = passField.getPassword();
			
			if (c1.checkPassword(u,pw,c1.getData(0))) {
				msgs.setText("");
				userField.setText("");
				passField.setText("");
				c1.catalogue.displayCatalogue(0);
				c1.activateWindow(1);
			}
			else {
				msgs.setText("The username or password you entered is incorrect. Please try again!");
			}			
		}
		
	}; 
	
	private ActionListener adminloginAction = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			msgs.setText("");
			c1.activateWindow(3);
		}
	};
	
	private ActionListener registrationAction = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			msgs.setText("Please speak with Mr.Crawford to create your account or change your password.");
		}	
	};
	
	public IALogin(Controller c) {
		
		c1 = c;
		
		c1.signoutPopulate();
		System.out.println(c1.aLsignedoutTitles);
		System.out.println(c1.signedoutMovies);
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints titlePanelC = new GridBagConstraints();
		titlePanelC.gridx = 0;
		titlePanelC.gridy = 0;
		titlePanelC.gridwidth = 11;
		titlePanelC.anchor = GridBagConstraints.NORTH;
		
		titletext.setFont(c1.title);
		titletext.setForeground(c1.WHITE);
		titlePanel.setPreferredSize(new Dimension(715,60));
		
		titlePanel.add(titletext);
		titlePanel.setBackground(c1.GREEN);
		titlePanel.setBorder(new EmptyBorder(30,30,30,30));
		
		add(titlePanel,titlePanelC);
		
		//every widget needs to have a GridBagConstraints associated with it
		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridx = 0;
		c1.gridy = 3;
		
		panel.add(uName,c1);
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 4;
		
		panel.add(pWord,c2);
		
		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 1;
		c3.gridy = 3;
		c3.gridwidth = 2;
		
		panel.add(userField,c3);
		
		GridBagConstraints c4 = new GridBagConstraints();
		c4.gridx = 1;
		c4.gridy = 4;
		c4.gridwidth = 2;
		
		panel.add(passField,c4);
		
		GridBagConstraints c5 = new GridBagConstraints();
		c5.gridx = 0;
		c5.gridy = 8;
		
		adminBTN.addActionListener(adminloginAction);
		
		panel.add(adminBTN,c5);
		
		GridBagConstraints c6 = new GridBagConstraints();
		c6.gridx = 1;
		c6.gridy = 6;
		c6.gridwidth = 2;
		
		loginBTN.addActionListener(loginAction);
		
		panel.add(loginBTN,c6);
		
		GridBagConstraints c7 = new GridBagConstraints();
		c7.gridx = 1;
		c7.gridy = 7;
		
		newUserBTN.addActionListener(registrationAction);
		
		panel.add(newUserBTN,c7);
		
		GridBagConstraints c8 = new GridBagConstraints();
		c8.gridx = 0;
		c8.gridy = 7;
		
		panel.add(newUser, c8);
		
		GridBagConstraints c9 = new GridBagConstraints();
		c9.gridx = 2;
		c9.gridy = 7;
		
		panel.add(fpWord, c9);
		
		GridBagConstraints c10 = new GridBagConstraints();
		c10.gridx = 3;
		c10.gridy = 7;
		
		resetBTN.addActionListener(registrationAction);
		
		panel.add(resetBTN, c10);
		
		GridBagConstraints c11 = new GridBagConstraints();
		c11.gridx = 4;
		c11.gridy = 2;
		
		panel.add(msg, c11);
		
//		GridBagConstraints c12 = new GridBagConstraints();
//		c12.gridx = 0;
//		c12.gridy = 0;
//		c12.gridheight = 2;
//		c12.gridwidth = 4;
//		
//		panel.add(loginTitle, c12);
		
		GridBagConstraints c13 = new GridBagConstraints();
		c13.gridx = 4;
		c13.gridy = 3;
		c13.gridheight = 5;
		c13.gridwidth = 2;
		msgs.setPreferredSize(new Dimension(200,175));
		msgs.setEditable(false);
		msgs.setForeground(c.RED);
		msgs.setText("Welcome! Please sign in to use the catalogue. \n \nUsernames and passwords are case sensitive");
		panel.add(msgs, c13);
		
		
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
		this.setVisible(true); //last thing
		
	}
}//end class
