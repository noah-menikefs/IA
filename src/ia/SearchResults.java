package ia;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SearchResults extends JFrame {

	private int resultsNum = 0;
	private String search;
	private int pageNum = 1;
	private int titleNum = 0;
	private boolean searched = false;
	
	private JPanel panel = new JPanel(new GridBagLayout());
	private JPanel titlePanel = new JPanel(); //constructed a JPanel object 
	
	private JLabel titletext = new JLabel("Search Results");
	private JLabel yourSearch = new JLabel("Your Search: "+search);
	private JLabel results1 = new JLabel("Results: "+resultsNum);
	private JLabel[] title = new JLabel[10];
	
	private JButton exitBTN = new JButton("EXIT");
	
	private JTextField searchField = new JTextField("",20);
	
	
	
	private ActionListener exitAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			c1.activateWindow(6);
		}
	};
	
	private MouseListener movieAction = new MouseListener() {
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			String title = ((JLabel) e.getSource()).getText();
			Movie movie = null;
			
			for (int i = 0; i < c1.movies.size(); i++) {
				if (title.equals(c1.movies.get(i).getTitle())) {
					movie = new Movie(c1.movies.get(i));
					break;
				}
			}
			
			System.out.println(movie);
			
			c1.mScreen.displayMovies(movie);
			
			c1.activateWindow(12);
			
		}
		public void mousePressed(MouseEvent e) {	}
		public void mouseReleased(MouseEvent e) {
		}
		public void mouseEntered(MouseEvent e) {
			((JLabel) e.getSource()).setFont(c1.bold);
			((JLabel) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		public void mouseExited(MouseEvent e) {
			((JLabel) e.getSource()).setFont(c1.normal);
			((JLabel) e.getSource()).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	};
	
	private Controller c1;
	
	public SearchResults(Controller c) {
		
		c1 = c;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		//titlePanel.setBorder(new EmptyBorder(10,150,10,150));
		titlePanel.setBorder(new EmptyBorder(30,30,30,30));
		
		
		add(titlePanel,titlePanelC);
		
		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridx = 2;
		c1.gridy = 0;
		c1.gridwidth = 4;
		
		panel.add(yourSearch,c1);
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 1;
		c2.gridwidth = 3;
		
		panel.add(results1,c2);
		
		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 0;
		c3.gridy = 0;
		
		exitBTN.addActionListener(exitAction);
		
		panel.add(exitBTN,c3);		
				
		GridBagConstraints panel2 = new GridBagConstraints();
		panel2.gridx = 0;
		panel2.gridy = 1;
		panel2.gridwidth = 4;
		
		panel.setBorder(new EmptyBorder(0,30,30,30));
		
		
		add(panel,panel2);
		
		//this.setPreferredSize(new Dimension(500,330));
		
		pack();
		setVisible(false);
		
	}

	public void setSearch(String s) {
		search = s;
		yourSearch.setText("Your Search: "+search);
	}
	
	public void displaySearch() {
		String[] titles = new String[c1.movies.size()];
		ArrayList<String> sTitles = new ArrayList<String>();
		System.out.println(search);
			
		//populating all movie titles
		for (int j = 0; j < titles.length; j++) {
			titles[j] = c1.movies.get(j).getTitle();
		}
		System.out.println(Arrays.toString(titles));
		
		resultsNum = 0;
		//populating searched movie titles
		for (int i = 0; i < titles.length; i++) { 
			if (titles[i].toUpperCase().contains(search.toUpperCase())) {
				sTitles.add(titles[i]);
				resultsNum++;
			}
		}
		results1.setText("Results: "+resultsNum);
		System.out.println(sTitles);
		
		//Resets all labels and sets created labels to searched titles
		for (int n = 0; n < 10; n++) {
			if (searched == false) {
				title[n] = new JLabel("");
			}
			if (n < sTitles.size()) {
				title[n].setText(sTitles.get(n));
				title[n].addMouseListener(movieAction);
			}
			else {
				title[n].removeMouseListener(movieAction);
				title[n].setText("");
			}
		}
		
		//displaying first 10 searched titles
		if (resultsNum > 0) {
			if (searched == false) {
				for (int m = 0; m < 10; m++) { //can also be < title.length
					GridBagConstraints ctf4 = new GridBagConstraints();
				
					ctf4.gridy = m+2;
					ctf4.gridx = 2;
				
					ctf4.gridwidth = 9;
					ctf4.anchor = GridBagConstraints.WEST;

					title[m].addMouseListener(movieAction);
					panel.add(title[m],ctf4);
				
					}
			}
			searched = true;
		}
		pack();
	}
}

