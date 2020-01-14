package ia;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class ReturnedMovies extends JFrame {
	// Big idea: Since Login extends JFrame,
	// login inherits all attributes and behaviours of JFrame
	//"Login (Specific) is a JFrame (General)"
	
	//GUI ELEMENTS - Widgets that make up a window
	private JPanel panel = new JPanel(new GridBagLayout());
	private JPanel titlePanel = new JPanel();
	
	private JLabel titletext = new JLabel("Movie Return");
	private JLabel titleL = new JLabel("Title:");
	
	private JButton exitBTN = new JButton("EXIT");
	private JButton submitBTN = new JButton("Submit");

	private Controller c1;
	
	JComboBox titleField;
	
	//LISTENERS - These are the functions called when something happens
	private ActionListener exitAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			c1.activateWindow(5);
		}	
	};
	private ActionListener submitAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				c1.movieReturn((String)titleField.getSelectedItem());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("Error");
			}
			System.out.println(c1.aLsignedoutTitles);
			titleField.setModel(new DefaultComboBoxModel(c1.aLsignedoutTitles.toArray()));
		}	
	};
	
	
	public ReturnedMovies(Controller c) {
		
		c1 = c;
		
		titleField = new JComboBox(c1.aLsignedoutTitles.toArray());
		
		
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
		
		//every widget needs to have a GridBagConstraints associated with it
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;
		
		exitBTN.addActionListener(exitAction);
		
		panel.add(exitBTN,c2);
		
		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 0;
		c3.gridy = 1;
		c3.anchor = GridBagConstraints.EAST;
		
		panel.add(titleL,c3);
		
		GridBagConstraints c4 = new GridBagConstraints();
		c4.gridx = 1;
		c4.gridy = 1;
		c4.anchor = GridBagConstraints.WEST;
		
		panel.add(titleField,c4);
		
		GridBagConstraints c5 = new GridBagConstraints();
		c5.gridx = 1;
		c5.gridy = 2;
		
		submitBTN.addActionListener(submitAction);
		
		panel.add(submitBTN,c5);
				
		
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
	
	public void setMovies() {
		titleField.setModel(new DefaultComboBoxModel(c1.aLsignedoutTitles.toArray()));
	}

	
	
}//end class
