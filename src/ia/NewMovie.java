package ia;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

public class NewMovie extends JFrame {
	
	private String[] genres = {"Action","Adventure","Comedy","Crime","Drama","Horror","Musical","Sci-Fi","War","Western"};
	
	private JPanel panel = new JPanel(new GridBagLayout());
	private JPanel titlePanel = new JPanel();
	
	private JLabel titletext = new JLabel("New Movie");
	private JLabel titleL = new JLabel("Title:");
	private JLabel genreL = new JLabel("Genre:");
	private JLabel runtimeL = new JLabel("Runtime:");
	private JLabel mins = new JLabel("mins.");
	private JLabel languageL = new JLabel("Language:");
	private JLabel directorL = new JLabel("Director:");
	private JLabel ratingL = new JLabel("Rotten Tomatoes Score:");
	private JLabel pcent = new JLabel("%");
	private JLabel descriptionL = new JLabel("Brief Description:");
	private JLabel popup = new JLabel();
	private JLabel yearL = new JLabel("Released Year:");
	private JLabel imgL = new JLabel("Image File:");
	
	private int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	
	SpinnerModel ratingModel = new SpinnerNumberModel(0, 0, 100, 1);
	SpinnerModel yearModel = new SpinnerNumberModel(currentYear,0,currentYear,1);
	
	private JTextField titleField = new JTextField("",20);
	private JComboBox genreField = new JComboBox(genres);
	private JSpinner runtimeField = new JSpinner();
	private JTextField languageField = new JTextField("",20);
	private JTextField directorField = new JTextField("",20);
	private JSpinner ratingField = new JSpinner(ratingModel);
	private JSpinner yearField = new JSpinner(yearModel);
	private JTextField imgField = new JTextField("",10);
	
	private JTextArea descriptionField = new JTextArea("",15,30);
	JScrollPane scrollPane = new JScrollPane(descriptionField);
	
	private JButton exitBTN = new JButton("EXIT");
	private JButton submitBTN = new JButton("Submit");


	
	private Controller c1;
	
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
			String title = titleField.getText();
			String genre = (String)genreField.getSelectedItem();
			int runtime = (int) runtimeField.getValue();
			String language = languageField.getText();
			String director = directorField.getText();
			int rating = (int) ratingField.getValue();
			String description = descriptionField.getText();
			int year = (int) yearField.getValue();
			String img = imgField.getText();
			
			description = description.replaceAll(",", "*");
			
			File imgFile = new File("Images/"+img);
			boolean exists = imgFile.exists();
			
			System.out.println(exists);
			
			if (title.equals("") || runtime < 0 || language.equals("") || director.equals("") || description.equals("") || img.equals("")) {
				popup.setText("Please correctly fill in all fields.");
				popup.setForeground(c1.RED);
				popup.setVisible(true);
				pack();
			}
			
			else if (exists == false) {
				popup.setText("The image you entered does not exist.");
				popup.setForeground(c1.RED);
				popup.setVisible(true);
				pack();
			}
			
			
			else {
				popup.setText("Success! Movie added.");
				popup.setForeground(c1.GREEN);
				popup.setVisible(true);
				titleField.setText("");
				runtimeField.setValue(0);
				languageField.setText("");
				directorField.setText("");
				ratingField.setValue(0);
				descriptionField.setText("");
				
				c1.addMovie(title, Integer.toString(rating), Integer.toString(year), Integer.toString(runtime), img, genre, language, director, description);
				
				pack();
			}
		}
	};
	
	public NewMovie(Controller c) {
		
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
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 0;
		
		exitBTN.addActionListener(exitAction);
		
		panel.add(exitBTN,c2);
		
		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 1;
		c3.gridy = 1;
		c3.anchor = GridBagConstraints.EAST;
		
		panel.add(titleL,c3);
		
		GridBagConstraints c4 = new GridBagConstraints();
		c4.gridx = 1;
		c4.gridy = 2;
		c4.anchor = GridBagConstraints.EAST;
		
		panel.add(genreL,c4);
		
		GridBagConstraints c5 = new GridBagConstraints();
		c5.gridx = 1;
		c5.gridy = 3;
		c5.anchor = GridBagConstraints.EAST;
		
		panel.add(runtimeL,c5);
		
		GridBagConstraints c6 = new GridBagConstraints();
		c6.gridx = 3;
		c6.gridy = 3;

		c6.anchor = GridBagConstraints.WEST;
		
		panel.add(mins,c6);
		
		GridBagConstraints c7 = new GridBagConstraints();
		c7.gridx = 1;
		c7.gridy = 4;

		c7.anchor = GridBagConstraints.EAST;
		
		panel.add(languageL,c7);
		
		GridBagConstraints c8 = new GridBagConstraints();
		c8.gridx = 1;
		c8.gridy = 5;

		c8.anchor = GridBagConstraints.EAST;
		
		panel.add(directorL,c8);
		
		GridBagConstraints c9 = new GridBagConstraints();
		c9.gridx = 0;
		c9.gridy = 6;
		c9.gridwidth = 2;

		c9.anchor = GridBagConstraints.EAST;
		
		panel.add(ratingL,c9);
		
		GridBagConstraints c10 = new GridBagConstraints();
		c10.gridx = 0;
		c10.gridy = 9;
		c10.gridwidth = 2;

		c10.anchor = GridBagConstraints.EAST;
		
		panel.add(descriptionL,c10);
		
		GridBagConstraints c11 = new GridBagConstraints();
		c11.gridx = 2;
		c11.gridy = 14;

		submitBTN.addActionListener(submitAction);
		
		panel.add(submitBTN,c11);
		
		GridBagConstraints c12 = new GridBagConstraints();
		c12.gridx = 2;
		c12.gridy = 1;
		c12.gridwidth = 2;

		c12.anchor = GridBagConstraints.WEST;
		panel.add(titleField,c12);
		
		GridBagConstraints c13 = new GridBagConstraints();
		c13.gridx = 2;
		c13.gridy = 2;
		c13.gridwidth = 2;

		c13.anchor = GridBagConstraints.WEST;
		panel.add(genreField,c13);
		
		GridBagConstraints c14 = new GridBagConstraints();
		c14.gridx = 2;
		c14.gridy = 3;
		runtimeField.setPreferredSize(new Dimension (50,20));

		c14.anchor = GridBagConstraints.EAST;
		panel.add(runtimeField,c14);
		
		GridBagConstraints c15 = new GridBagConstraints();
		c15.gridx = 2;
		c15.gridy = 4;
		c15.gridwidth = 2;

		c15.anchor = GridBagConstraints.WEST;
		panel.add(languageField,c15);
		
		GridBagConstraints c16 = new GridBagConstraints();
		c16.gridx = 2;
		c16.gridy = 5;
		c16.gridwidth = 2;

		c16.anchor = GridBagConstraints.WEST;
		panel.add(directorField,c16);
		
		GridBagConstraints c17 = new GridBagConstraints();
		c17.gridx = 2;
		c17.gridy = 6;
		ratingField.setPreferredSize(new Dimension (50,20));

		c17.anchor = GridBagConstraints.EAST;
		panel.add(ratingField,c17);
		
		GridBagConstraints c18 = new GridBagConstraints();
		c18.gridx = 3;
		c18.gridy = 6;

		c18.anchor = GridBagConstraints.WEST;
		
		panel.add(pcent,c18);
		
		GridBagConstraints c19 = new GridBagConstraints();
		c19.gridx = 2;
		c19.gridy = 9;
		c19.gridwidth = 4;
		c19.gridheight = 4;
		descriptionField.setLineWrap(true);
		descriptionField.setWrapStyleWord(true);

		c19.anchor = GridBagConstraints.WEST;
		panel.add(scrollPane,c19);
		
		GridBagConstraints c20 = new GridBagConstraints();
		c20.gridx = 2;
		c20.gridy = 15;
		c20.gridwidth = 2;
		
		popup.setVisible(false);
		panel.add(popup,c20);
	
		GridBagConstraints c21 = new GridBagConstraints();
		c21.gridx = 1;
		c21.gridy = 7;
		c21.anchor = GridBagConstraints.EAST;
		
		panel.add(yearL,c21);
		
		GridBagConstraints c22 = new GridBagConstraints();
		c22.gridx = 1;
		c22.gridy = 8;
		c22.anchor = GridBagConstraints.EAST;
		
		panel.add(imgL,c22);
		
		GridBagConstraints c23 = new GridBagConstraints();
		c23.gridx = 2;
		c23.gridy = 7;
		c23.anchor = GridBagConstraints.WEST;
		
		panel.add(yearField,c23);
		
		GridBagConstraints c24 = new GridBagConstraints();
		c24.gridx = 2;
		c24.gridy = 8;
		c24.anchor = GridBagConstraints.WEST;
		
		panel.add(imgField,c24);
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
