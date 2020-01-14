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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class MovieScreen extends JFrame {

	//GUI Variables
	private JPanel panel = new JPanel(new GridBagLayout());
	private JPanel titlePanel = new JPanel(); //constructed a JPanel object 
	
	private JButton exitBTN = new JButton("EXIT");
	private JButton signoutBTN = new JButton("Sign Out");
	
	private String movieTitle;
	private String movieGenre;
	private String rating;
	private String runtime;
	private String language;
	private String description;
	private String director;
	private String imageFile;
	
	private JLabel titletext = new JLabel(movieTitle);
	private JLabel genreLbl = new JLabel("Genre: "+movieGenre);
	private JLabel ratingLbl = new JLabel("Rating: "+rating+"%");
	private JLabel runtimeLbl = new JLabel("Running Time: "+runtime+" minutes");
	private JLabel languageLbl = new JLabel("Language: "+language);
	private JLabel descriptionLbl = new JLabel("Brief Description:");
	private JTextArea summaryLbl = new JTextArea();
	private JLabel image = new JLabel();
	private JLabel directorLbl = new JLabel("Director: "+director);
	private JLabel error = new JLabel("This movie is currently signed out.");
	
	
	private ActionListener exitAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			error.setVisible(false);
			c1.catalogue.displayCatalogue(0);
			c1.activateWindow(1);
		}
	};
	private ActionListener signoutAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			boolean available = true;
			
			for (int i = 0; i < c1.aLsignedoutTitles.size(); i++) {
				if (c1.aLsignedoutTitles.get(i).equals(movieTitle)){
					available = false;
				}
			}
			
			if (available == true) {
				c1.sScreen.displayTitle(movieTitle);
				c1.activateWindow(13);
			}
			else {
				error.setVisible(true);
				pack();
			}
			
			
		}
	};
	//Data Variables
	private Controller c1;
	
	public MovieScreen(Controller c) {
		
		c1 = c;
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints titlePanelC = new GridBagConstraints();
		titlePanelC.gridx = 0;
		titlePanelC.gridy = 0;
		titlePanelC.gridwidth = 11;
		titlePanelC.anchor = GridBagConstraints.NORTH;
		
		titletext.setFont(c1.title);
		titletext.setForeground(c1.WHITE);
		titlePanel.setPreferredSize(new Dimension(625,60));
		
		titlePanel.add(titletext);
		titlePanel.setBackground(c1.GREEN);
		titlePanel.setBorder(new EmptyBorder(30,30,30,30));
		
		add(titlePanel,titlePanelC);
		
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;
		
		exitBTN.addActionListener(exitAction);
		panel.add(exitBTN,c2);
		
		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 1;
		c3.gridy = 0;
		c3.gridwidth = 4;
		c3.gridheight = 12;
		
		
		ImageIcon icon;
		
		//icon = new ImageIcon("Images/"+imagePath);
		icon = new ImageIcon("Images/TheShawshankRedemptionBIG.jpg");
		image.setIcon(icon);
		image.setPreferredSize(new Dimension(150,225));
		
		panel.add(image, c3);
		
		GridBagConstraints c4 = new GridBagConstraints();
		c4.gridx = 5;
		c4.gridy = 0;
		c4.gridwidth = 2;
		
		panel.add(genreLbl, c4);
		
		GridBagConstraints c5 = new GridBagConstraints();
		c5.gridx = 7;
		c5.gridy = 0;
		c5.gridwidth = 2;
		c5.anchor = GridBagConstraints.EAST;
		
		panel.add(ratingLbl, c5);
		
		GridBagConstraints c6 = new GridBagConstraints();
		c6.gridx = 5;
		c6.gridy = 1;
		c6.gridwidth = 4;
		
		panel.add(runtimeLbl, c6);
		
		GridBagConstraints c7 = new GridBagConstraints();
		c7.gridx = 5;
		c7.gridy = 2;
		c7.gridwidth = 4;
		
		panel.add(languageLbl, c7);
		
		GridBagConstraints c8 = new GridBagConstraints();
		c8.gridx = 5;
		c8.gridy = 3;
		c8.gridwidth = 4;
		c8.anchor = GridBagConstraints.WEST;
		
		panel.add(descriptionLbl, c8);
		
		GridBagConstraints c9 = new GridBagConstraints();
		c9.gridx = 5;
		c9.gridy = 4;
		c9.gridwidth = 4;
		c9.gridheight = 6;
		c9.anchor = GridBagConstraints.WEST;
		
		panel.add(summaryLbl, c9);
		
		GridBagConstraints c10 = new GridBagConstraints();
		c10.gridx = 5;
		c10.gridy = 10;
		c10.gridwidth = 4;
		
		panel.add(directorLbl, c10);
		
		GridBagConstraints c11 = new GridBagConstraints();
		c11.gridx = 7;
		c11.gridy = 11;
		c11.gridwidth = 2;
		
		signoutBTN.addActionListener(signoutAction);
		
		panel.add(signoutBTN, c11);
		
		GridBagConstraints c12 = new GridBagConstraints();
		c12.gridx = 7;
		c12.gridy = 12;
		c12.gridwidth = 2;
		error.setForeground(c1.RED);
		error.setVisible(false);
		
		panel.add(error, c12);
		
		
	
		
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
	
	public void displayMovies(Movie movie) {
		movieTitle = movie.getTitle();
		movieGenre = movie.getGenre();
		rating = movie.getRating();
		runtime = movie.getRunTime();
		language = movie.getLanguage();
		description = movie.getSummary();
		director = movie.getDirector();
		imageFile = movie.getImg();
		
		description = description.replace('*', ',');
		
		int index = imageFile.indexOf(".");
		String front = imageFile.substring(0,index);
		String end = imageFile.substring(index);
		
		ImageIcon icon = new ImageIcon("Images/"+front+"BIG"+end);
		
		titletext.setText(movieTitle);
		genreLbl.setText("Genre: "+movieGenre);
		ratingLbl.setText("Rating: "+rating+"%");
		runtimeLbl.setText("Running Time: "+runtime+" minutes");
		languageLbl.setText("Language: "+language);
		summaryLbl.setText("");
		image.setIcon(icon);
		directorLbl.setText("Director: "+director);
	
		for (int i = 0; i < description.length(); i+=50) {
			if ((i + 50)< description.length()){
				summaryLbl.setText(summaryLbl.getText()+description.substring(i,i+50)+"\n");
			}
			else {
				summaryLbl.setText(summaryLbl.getText()+description.substring(i));
			}
		}
		
		pack();
		
	}
}
