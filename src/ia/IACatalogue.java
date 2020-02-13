package ia;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class IACatalogue extends JFrame {

	//GUI Variables
	private JPanel panel = new JPanel(new GridBagLayout());
	private JPanel titlePanel = new JPanel(); //constructed a JPanel object 
	
	private JButton exitBTN = new JButton("EXIT");
	private JButton searchBTN = new JButton("Search");
	private JButton backBTN = new JButton("\u25C0");
	private JButton fwdBTN = new JButton("\u25B6");
	
	private boolean displayed = false;
	private JLabel titletext = new JLabel("Catalogue");
	private JLabel searchLbl = new JLabel("Movie Search:");
	
	private int pageNum = 1;
	private JLabel pageNumLbl = new JLabel("Page: "+pageNum);
	private JLabel[] rating = new JLabel[10];
	private JLabel[] year = new JLabel[10];
	private JLabel[] runTime = new JLabel[10];
	private JLabel[] title = new JLabel[10];
	private JLabel[] image = new JLabel[10];
	private JLabel[] output = new JLabel[10];
	
	private ActionListener exitAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			c1.activateWindow(0);
		}
	};
	private ActionListener searchAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			c1.activateWindow(6);
		}
	};
	private ActionListener NextPageAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			displayCatalogue(pageNum);
		}
	};
	private ActionListener BackPageAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			displayCatalogue(pageNum - 2);
		}
	};
	private MouseListener movieAction = new MouseListener() {
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			String text = ((JLabel) e.getSource()).getText();
			String title;
			Movie movie = null;
			
			if (text.contains(".jpg")) {
				for (int i = 0; i < c1.movies.size(); i++) {
					if (c1.movies.get(i).getImg().equals(text)) {
						movie = c1.movies.get(i);
					}
				}
			}
			else if (text.contains("...")) {
				for (int i = 0; i < c1.movies.size(); i++) {
					title = c1.movies.get(i).getTitle();
					if (title.length() > 15) {
						if (title.contains(text.substring(0, text.length() - 3))) {
							movie = c1.movies.get(i);
						}
					}
				}
			}
			else {
				for (int i = 0; i < c1.movies.size(); i++) {
					title = c1.movies.get(i).getTitle();
					if (title.equals(text)) {
						movie = c1.movies.get(i);
					}
				}
			}
			
			c1.mScreen.displayMovies(movie);
			
			c1.activateWindow(12);
			
		}
		public void mousePressed(MouseEvent e) {	}
		public void mouseReleased(MouseEvent e) {	}
		public void mouseEntered(MouseEvent e) {
			((JLabel) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		public void mouseExited(MouseEvent e) {
			((JLabel) e.getSource()).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	};
	
	//Data Variables
	private Controller c1;
	
	public IACatalogue(Controller c) {
		
		c1 = c;
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints titlePanelC = new GridBagConstraints();
		titlePanelC.gridx = 0;
		titlePanelC.gridy = 0;
		titlePanelC.gridwidth = 11;
		titlePanelC.anchor = GridBagConstraints.NORTH;
		
		titletext.setFont(c1.title);
		titletext.setForeground(c1.WHITE);
		titlePanel.setPreferredSize(new Dimension(815,60));
		
		titlePanel.add(titletext);
		titlePanel.setBackground(c1.GREEN);
		titlePanel.setBorder(new EmptyBorder(30,30,30,30));
		
		add(titlePanel,titlePanelC);
		
		
		c.MoviePopulate("MovieList.txt");
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 1;
		
		exitBTN.addActionListener(exitAction);
		panel.add(exitBTN,c2);
		
		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 2;
		c3.gridy = 2;
		c3.gridwidth = 2;
		
		panel.add(searchLbl, c3);
		
		GridBagConstraints c4 = new GridBagConstraints();
		c4.gridx = 4;
		c4.gridy = 2;
		
		searchBTN.addActionListener(searchAction);
		panel.add(searchBTN, c4);
		
		GridBagConstraints c5 = new GridBagConstraints();
		c5.gridx = 6;
		c5.gridy = 2;
		c5.gridwidth = 2;
		
		panel.add(pageNumLbl, c5);
		
		GridBagConstraints c6 = new GridBagConstraints();
		c6.gridx = 8;
		c6.gridy = 2;
		
		backBTN.addActionListener(BackPageAction);
		panel.add(backBTN, c6);
		
		GridBagConstraints c7 = new GridBagConstraints();
		c7.gridx = 9;
		c7.gridy = 2;
		
		fwdBTN.addActionListener(NextPageAction);
		panel.add(fwdBTN, c7);
		
		
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
	
	public void displayCatalogue(int n) {
		backBTN.setEnabled(true);
		fwdBTN.setEnabled(true);
		pageNum = n+1;
		pageNumLbl.setText("Page: "+pageNum);
		if (n == 0) {
			backBTN.setEnabled(false);
		}		
		int start = n*10;
		int end = Math.min(c1.movies.size(),((n*10)+10));
		if (end == c1.movies.size()) {
			fwdBTN.setEnabled(false);
		}
		int diff = end - start;
		String[] ratings = new String[diff];
		String[] years = new String[diff];
		String[] runTimes = new String[diff];
		String[] titles = new String[diff];
		String[] images = new String[diff];

		//read values from movie arrayList
		for (int j = start; j < end; j++) {
			//Movie movie = new Movie(c1.movies.get(j));
			Movie movie = c1.movies.get(j);
			ratings[j-start] = movie.getRating() + "%";
			years[j-start] = movie.getYear();
			runTimes[j-start] = movie.getRunTime() + " minutes";
			titles[j-start] = movie.getTitle();
			images[j-start] = movie.getImg();
		}
		
		int ctr = 0;
		int ctr3 = 0;
		int ctr4 = 0;
		int ctr5 = 0;
		ImageIcon icon;
		if (displayed == true) {
			for (int i = 0; i < 10; i++) {
				if (i < diff) {
					output[i].setText("        "+ratings[i]+"       "+years[i]+"        ");
					runTime[i].setText(runTimes[i]);
					if (titles[i].length() > 15) {
						title[i].setText(titles[i].substring(0,15) + "...");
					}
					else {
						title[i].setText(titles[i]);
					}
					image[i].setText(images[i]);
					icon = new ImageIcon("Images/"+images[i]);
					image[i].setIcon(icon);
					image[i].setPreferredSize(new Dimension(100,150));
					
					title[i].addMouseListener(movieAction);
					image[i].addMouseListener(movieAction);
					
				}
				
				else {
					output[i].setText("");
					runTime[i].setText("");
					title[i].setText("");
					image[i].setText("");
					image[i].setIcon(null);
					image[i].setPreferredSize(null);
					
					title[i].removeMouseListener(movieAction);
					image[i].removeMouseListener(movieAction);
				}
				
				
			}
		}
		else {
			for (int i = 0; i < diff; i++) {
				output[i] = new JLabel("        "+ratings[i]+"       "+years[i]+"        ");
				runTime[i] = new JLabel(runTimes[i]);
				if (titles[i].length() > 15) {
					title[i] = new JLabel(titles[i].substring(0,15) + "...");
				}
				else {
					title[i] = new JLabel(titles[i]);
				}	
				image[i] = new JLabel(images[i]);
				icon = new ImageIcon("Images/"+images[i]);
				
				image[i].setIcon(icon);
				image[i].setPreferredSize(new Dimension(100,150));
				
				GridBagConstraints ctf = new GridBagConstraints();
				GridBagConstraints ctf3 = new GridBagConstraints();
				GridBagConstraints ctf4 = new GridBagConstraints();
				GridBagConstraints ctf5 = new GridBagConstraints();
				
				//adding the ratings and years
				if (ctr < 5) {
					ctf.gridy = 8;
					ctf.gridx = 2*i;
				}
				else {
					ctf.gridy = 16;
					ctf.gridx = 2*(i%5); 
				}
				ctf.gridwidth = 2;
				panel.add(output[i],ctf);
				ctr++;
				
				//adding the runtimes
				if (ctr3 < 5) {
					ctf3.gridy = 9;
					ctf3.gridx = 2*i;
				}
				else {
					ctf3.gridy = 17;
					ctf3.gridx = 2*(i%5); 
				}
				ctf3.gridwidth = 2;
	
				panel.add(runTime[i],ctf3);
				ctr3++;
				
				//adding the titles
				if (ctr4 < 5) {
					ctf4.gridy = 10;
					ctf4.gridx = 2*i;
				}
				else {
					ctf4.gridy = 18;
					ctf4.gridx = 2*(i%5); 
				}
				ctf4.gridwidth = 2;	
				title[i].addMouseListener(movieAction);
				title[i].setFont(c1.bold);
				panel.add(title[i],ctf4);
				ctr4++;
				
				//adding the images
				if (ctr5 < 5) {
					ctf5.gridy = 3;
					ctf5.gridx = 2*i;
				}
				else {
					ctf5.gridy = 11;
					ctf5.gridx = 2*(i%5); 
				}
				ctf5.gridwidth = 2;
				ctf5.gridheight = 5;
						
				image[i].addMouseListener(movieAction);
				panel.add(image[i],ctf5);
	
				ctr5++;	
			}
		}
		displayed = true;
		pack();
		
	}
}
