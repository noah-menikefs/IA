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
public class AdminScreen extends JFrame {
	// Big idea: Since Login extends JFrame,
	// login inherits all attributes and behaviours of JFrame
	//"Login (Specific) is a JFrame (General)"
	
	//GUI ELEMENTS - Widgets that make up a window
	private JPanel panel = new JPanel(new GridBagLayout());
	private JPanel titlePanel = new JPanel();
	
	private JLabel titletext = new JLabel("Admin Screen");
	
	private JButton exitBTN = new JButton("EXIT");
	private JButton newStudent = new JButton("Add student");
	private JButton newPword = new JButton("Change student's password");
	private JButton movieCheck = new JButton("Signed-out movies");
	private JButton movieReturn = new JButton("Indicate returned movie");
	private JButton movieAdd = new JButton("Add movie");	

	private Controller c1;
	
	//LISTENERS - These are the functions called when something happens
	private ActionListener exitAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			c1.activateWindow(3);
		}
	};
	private ActionListener newStudentAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			c1.activateWindow(9);
		}
	}; 
	private ActionListener newPwordAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			c1.activateWindow(2);
		}
	};
	private ActionListener movieCheckAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			c1.signedOut.displayTable();
			c1.activateWindow(10);
		}	
	};
	private ActionListener movieReturnAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			c1.returns.setMovies();
			c1.activateWindow(8);
		}
	};
	private ActionListener movieAddAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			c1.activateWindow(11);
		}	
	};
	
	public AdminScreen(Controller c) {
		
		c1 = c;
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints titlePanelC = new GridBagConstraints();
		titlePanelC.gridx = 0;
		titlePanelC.gridy = 0;
		titlePanelC.gridwidth = 11;
		titlePanelC.anchor = GridBagConstraints.NORTH;
		
		titletext.setFont(c1.title);
		titletext.setForeground(c1.WHITE);
		titlePanel.setPreferredSize(new Dimension(615,60));
		
		titlePanel.add(titletext);
		titlePanel.setBackground(c1.GREEN);
		titlePanel.setBorder(new EmptyBorder(30,30,30,30));
		
		add(titlePanel,titlePanelC);
		
		//every widget needs to have a GridBagConstraints associated with it
		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridx = 0;
		c1.gridy = 0;
		c1.anchor = GridBagConstraints.WEST;
		
		exitBTN.addActionListener(exitAction);
		
		panel.add(exitBTN,c1);
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 1;
		c2.gridy = 0;
		//c2.gridwidth = 3;
		c2.anchor = GridBagConstraints.WEST;
		
		newStudent.addActionListener(newStudentAction);
		
		panel.add(newStudent,c2);
		
		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 0;
		c3.gridy = 1;
		//c3.gridwidth = 3;
		c3.anchor = GridBagConstraints.EAST;
		
		newPword.addActionListener(newPwordAction);
		
		panel.add(newPword,c3);
		
		GridBagConstraints c4 = new GridBagConstraints();
		c4.gridx = 1;
		c4.gridy = 1;
		//c4.gridwidth = 3;
		c4.anchor = GridBagConstraints.WEST;
		
		
		movieCheck.addActionListener(movieCheckAction);
		
		panel.add(movieCheck,c4);
		
		GridBagConstraints c5 = new GridBagConstraints();
		c5.gridx = 0;
		c5.gridy = 2;
		//c5.gridwidth = 3;
		c5.anchor = GridBagConstraints.EAST;
		
		movieReturn.addActionListener(movieReturnAction);
		
		panel.add(movieReturn,c5);
		
		GridBagConstraints c6 = new GridBagConstraints();
		c6.gridx = 1;
		c6.gridy = 2;
		//c6.gridwidth = 5;
		c6.anchor = GridBagConstraints.WEST;
		
		movieAdd.addActionListener(movieAddAction);
		
		panel.add(movieAdd,c6);
		
		
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
