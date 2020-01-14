package ia;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


//JFrame is a built in library used to make
//a standard window.
public class SignOutTable extends JFrame {
	// Big idea: Since Login extends JFrame,
	// login inherits all attributes and behaviours of JFrame
	//"Login (Specific) is a JFrame (General)"
	
	//GUI ELEMENTS - Widgets that make up a window
	private JPanel panel = new JPanel(new GridBagLayout());
	private JPanel titlePanel = new JPanel();
	
	private JLabel titletext = new JLabel("Signed-Out Movies");
	
	private JButton exitBTN = new JButton("EXIT");
	
	private JTable signedoutTable = new JTable(1,4);
	TableColumnModel columnModel = signedoutTable.getColumnModel();
	DefaultTableModel model = (DefaultTableModel) signedoutTable.getModel();
	

	private Controller c1;
	
	
	//LISTENERS - These are the functions called when something happens
	private ActionListener exitAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			c1.activateWindow(5);
		}
	}; 
	
	public SignOutTable(Controller c) {
		
		c1 = c;
		
		for (int i = 0; i < c1.aLsignedoutTitles.size(); i++) {
			String[] arrMovie = c1.signedoutMovies.get(i).split(",");
			model.addRow(arrMovie);
		}
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints titlePanelC = new GridBagConstraints();
		titlePanelC.gridx = 0;
		titlePanelC.gridy = 0;
		titlePanelC.gridwidth = 11;
		titlePanelC.anchor = GridBagConstraints.NORTH;
		
		titletext.setFont(c1.title);
		titletext.setForeground(c1.WHITE);
		titlePanel.setPreferredSize(new Dimension(660,60));
		
		titlePanel.add(titletext);
		titlePanel.setBackground(c1.GREEN);
		titlePanel.setBorder(new EmptyBorder(30,30,30,30));
		
		add(titlePanel,titlePanelC);
		
		//every widget needs to have a GridBagConstraints associated with it
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;
		c2.anchor = GridBagConstraints.WEST;
		
		exitBTN.addActionListener(exitAction);
		
		panel.add(exitBTN,c2);
		
		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 0;
		c3.gridy = 1;
		
		
		//MAKE TABLE WIDER
		//MAKE TABLE UNEDITABLE
		
		signedoutTable.setRowHeight(0, 35);
		signedoutTable.setValueAt("Full Name", 0, 0);
		signedoutTable.setValueAt("Movie", 0, 1);
		signedoutTable.setValueAt("Date Borrowed", 0, 2);
		signedoutTable.setValueAt("Intended sign-out length", 0, 3);
		
		columnModel.getColumn(0).setPreferredWidth(150);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(150);
		columnModel.getColumn(3).setPreferredWidth(150);
		
		
		panel.add(signedoutTable,c3);
		
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
	
	public void displayTable() {
	
		model.setRowCount(1);
		
		for (int i = 0; i < c1.aLsignedoutTitles.size(); i++) {
			String[] arrMovie = c1.signedoutMovies.get(i).split(",");
			model.addRow(arrMovie);
		}

		pack();
	}
	
	
}//end class
