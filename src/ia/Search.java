package ia;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Search extends JFrame {

	
	private JPanel panel = new JPanel(new GridBagLayout());
	private JPanel titlePanel = new JPanel(); //constructed a JPanel object 
	
	private JLabel titletext = new JLabel("Movie Search");
	
	private JButton exitBTN = new JButton("EXIT");
	private JButton searchBTN = new JButton("Search");
	
	private JTextField searchField = new JTextField("",20);
	
	private ActionListener exitAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			c1.catalogue.displayCatalogue(0);
			c1.activateWindow(1);
		}
	};
	private ActionListener searchAction = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			c1.results.setSearch(searchField.getText());
			searchField.setText("");
			c1.results.displaySearch();
			
			//searchField.setText("");
			c1.activateWindow(7);
		}
		
	};
	
	
	private Controller c1;
	
	public Search(Controller c) {
		
		c1 = c;
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints titlePanelC = new GridBagConstraints();
		titlePanelC.gridx = 0;
		titlePanelC.gridy = 0;
		titlePanelC.gridwidth = 4;
		
		titlePanel.setPreferredSize(new Dimension(500,60));
		titletext.setFont(c1.title);
		titletext.setForeground(c1.WHITE);    //not background but uses color value of BACKGROUND
		
		titlePanel.add(titletext);
		titlePanel.setBackground(c1.GREEN);
		titlePanel.setBorder(new EmptyBorder(30,30,30,30));
		
		add(titlePanel,titlePanelC);
		
		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 0;
		c3.gridy = 0;
		
		exitBTN.addActionListener(exitAction);
		
		panel.add(exitBTN,c3);
		
		GridBagConstraints c4 = new GridBagConstraints();
		c4.gridx = 0;
		c4.gridy = 1;
		c4.gridwidth = 4;
		
		panel.add(searchField,c4);
		
		GridBagConstraints c5 = new GridBagConstraints();
		c5.gridx = 2;
		c5.gridy = 2;
		
		searchBTN.addActionListener(searchAction);
		
		panel.add(searchBTN,c5);
		
		GridBagConstraints panel2 = new GridBagConstraints();
		panel2.gridx = 0;
		panel2.gridy = 1;
		panel2.gridwidth = 4;
		
		panel.setBorder(new EmptyBorder(0,30,30,30));
		
		
		add(panel,panel2);
		

		
		
		pack();
		setVisible(false);
		
	}
	
	
	
}
