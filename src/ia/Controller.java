package ia;


import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.SwingUtilities;

public class Controller {
	
	Font title = new Font("Courier",Font.BOLD,23); //Font for titles used at the top of screens
	Font bold = new Font("Lucida Grande",Font.BOLD,13); //Font for any bolded movie titles
	Font normal = new Font("Lucida Grande",Font.PLAIN,13); //Font for regular text
	static Color GREEN = new Color(45,179,0); //Green colour used for success alerts and title's background
	static Color WHITE = new Color(240, 240, 240); //White used for title's text
	static Color RED = new Color(255,0,0); //Red used for errors and alerts
	private IASystemData data; //Stores all student's usernames and passwords
	private IASystemData adminData; //Stores all admin's usernames and passwords
	private Scanner s;
	
	ArrayList<Movie> movies = new ArrayList<Movie>(); //List of movies in catalogue
	ArrayList<String> aLsignedoutTitles = new ArrayList<String>(); //Titles of signed out movies
	ArrayList<String> signedoutMovies = new ArrayList<String>();
	
	
	int ctr = 0;
	IALogin login = new IALogin(this); //Login screen
	AdminLogin aLogin = new AdminLogin(this); //Admin login screen
	AdminScreen aScreen = new AdminScreen(this); //Admin action screen
	
	IACatalogue catalogue = new IACatalogue(this); //Catalogue screen
	Search search = new Search(this); //Search field screen
	SearchResults results = new SearchResults(this); //Results from search screen
	NewPword nPword = new NewPword(this); //Screen to change passwords
	ReturnedMovies returns = new ReturnedMovies(this); //Screen to indicate a returned movie
	NewStudent nStudent = new NewStudent(this); //New student screen
	SignOutTable signedOut = new SignOutTable(this); //Screen of all borrowed movies
	NewMovie nMovie = new NewMovie(this); //New movie screen
	MovieScreen mScreen = new MovieScreen(this); //Movie screen
	SignoutScreen sScreen = new SignoutScreen(this); //Screen to borrow a movie
	
	public Controller() {
		data = new IASystemData("IAuserData.txt");
		System.out.println(data.getuserData());
		adminData = new IASystemData("AdminData.txt");
		System.out.println(adminData.getuserData());
		
		//MoviePopulate("MovieList.txt");
		System.out.println(movies);
	}
		
	public void activateWindow(int a) { //Used throughout the program to switch the visibility of screens
		
		login.setVisible(false);
		catalogue.setVisible(false);
		nPword.setVisible(false);
		aLogin.setVisible(false);
		aScreen.setVisible(false);
		search.setVisible(false);
		results.setVisible(false);
		returns.setVisible(false);
		nStudent.setVisible(false);
		signedOut.setVisible(false);
		nMovie.setVisible(false);
		mScreen.setVisible(false);
		sScreen.setVisible(false);
		
		if (a == 0) { login.setVisible(true); }
		else if (a == 1) { catalogue.setVisible(true); }
		else if (a == 2) { nPword.setVisible(true); }
		else if (a == 3) { aLogin.setVisible(true); }
		else if (a == 5) { aScreen.setVisible(true); }
		else if (a == 6) { search.setVisible(true); }
		else if (a == 7) { results.setVisible(true); }
		else if (a == 8) { returns.setVisible(true); }
		else if (a == 9) { nStudent.setVisible(true); }
		else if (a == 10) { signedOut.setVisible(true); }
		else if (a == 11) { nMovie.setVisible(true); }
		else if (a == 12) { mScreen.setVisible(true); }
		else if (a == 13) { sScreen.setVisible(true); }
		
	}
	/**
	 * This function returns true if the user's username and password are valid
	 * @param u – This is the inputted username
	 * @param pw - This is the inputted password
	 * @param dataset - This is the data which holds either the admin or students' usernames and passwords
	 * 
	 */
	public boolean checkPassword(String u, char[] pw, IASystemData dataset) {
		String str = new String(pw);
		for (int i = 0; i < dataset.getuserData().size(); i+=2) {
			if (dataset.getuserData().get(i).contentEquals(u) && dataset.getuserData().get(i+1).contentEquals(str)){
					return true;
				}
		}
		return false;
	}
	/**
	 * This method returns false if the inputted username is taken
	 * @param u - Inputted username
	 */
	public boolean checkUsername(String u) {
		for (int i = 0; i < data.getuserData().size(); i+=2) {
			if (data.getuserData().get(i).contentEquals(u)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This method returns true if the "password" field matches the "confirm password" field
	 * @param pw - Inputted password
	 * @param cpw - Secondary "confirmed" password
	 */
	public boolean pWordMatch(char[] pw, char[] cpw) {
		if (Arrays.equals(pw,cpw)) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method populates the list of movies from the text file which stores each film
	 * @param fileName - The name of the file which currently stores movies and their attributes
	 */
	public void MoviePopulate(String fileName) {
		try {
			s = new Scanner(new File(fileName));
		}
		catch (Exception e) {
			System.out.println("ERROR - FILE ACCESS");
		}
		while(s.hasNext()) {
			String tn = s.nextLine();
			String[] arrMovie = tn.split(",");
			movies.add(new Movie(arrMovie));
		}
		sortAL();
	}
	
	/**
	 * This method populates the list of signed out movies from its respective text file
	 */
	public void signoutPopulate() {
		try {
			s = new Scanner(new File("SignOut.txt"));
		}
		catch(Exception e) {
			System.out.println("ERROR - FILE ACCESS");
		}
		while(s.hasNext()) {
			String movie = s.nextLine();
			signedoutMovies.add(movie);
			String[] arrMovie = movie.split(",");
			aLsignedoutTitles.add(arrMovie[1]);
		}
	}
	/**
	 * This method writes onto the text file of signed out movies when a film is borrowed
	 * It appends the student's name, length of sign out and title of the borrowed movie
	 * Finally, it adds the title to the list of currently borrowed movies
	 * @param name - The student's name
	 * @param length - The borrowing length
	 * @param title - The title of the movie
	 */
	public void movieSignout(String name, String length, String title) {
		String date = java.time.LocalDate.now().toString();
		
		try {
		    if (aLsignedoutTitles.size() == 0) {
		    	Files.write(Paths.get("SignOut.txt"), (name+","+title+","+date+","+length).getBytes(), StandardOpenOption.APPEND);
		    }
		    else {
		    	Files.write(Paths.get("SignOut.txt"), ("\n"+name+","+title+","+date+","+length).getBytes(), StandardOpenOption.APPEND);	
		    }
		}catch (IOException e) {
		    System.out.println("ERROR");
		}
		signedoutMovies.add(name+","+title+","+date+","+length);
		aLsignedoutTitles.add(title);
		System.out.println(aLsignedoutTitles);
	}
	
	/**
	 * This method removes a movie from the list of borrowed films
	 * Additionally, the movie is removed from the text file of signed out films
	 * @param title - Title of returned movie
	 */
	public void movieReturn(String title) throws IOException {
		aLsignedoutTitles.remove(title);
		for (int i = 0; i < signedoutMovies.size(); i++) {
			String[] arrMovie = signedoutMovies.get(i).split(",");
			if (arrMovie[1].equals(title)) {
				signedoutMovies.remove(i);
				break;
			}
		}
		
		File iaFile = new File("SignOut.txt");
		File tempFile = new File("myTempFile.txt");
		
		if (signedoutMovies.size() != 0) {
			try {
				Files.write(Paths.get("myTempFile.txt"), (signedoutMovies.get(0)).getBytes(), StandardOpenOption.APPEND);
			}catch (IOException e) {
				System.out.println("ERROR");
			}
		
			for (int i = 1; i < signedoutMovies.size(); i++) {
				try {
					Files.write(Paths.get("myTempFile.txt"), ("\n"+signedoutMovies.get(i)).getBytes(), StandardOpenOption.APPEND);
				}catch (IOException e) {
					System.out.println("ERROR");
				}
			}
		
			tempFile.renameTo(iaFile);
			File newFile = new File("myTempFile.txt");
			if (newFile.createNewFile()) {
				System.out.println("File created");
			}
			else {
				System.out.println("File could not be created");
			}
		}	
	}

	/**
	 * This method adds the new student's credentials to the list of users and the text file which contains each user
	 * @param u - Username of new student
	 * @param pw - Password of new student
	 */
	public void addStudent(String u, char[] pw) {
		String str = new String(pw);
		File iaFile = new File("IAuserData.txt");
		try {
		    if (iaFile.length() == 0) {
		    	Files.write(Paths.get("IAuserData.txt"), (u+"\n"+str).getBytes(), StandardOpenOption.APPEND);
		    }
		    else {
		    	Files.write(Paths.get("IAuserData.txt"), ("\n"+u+"\n"+str).getBytes(), StandardOpenOption.APPEND);	
		    }
		}catch (IOException e) {
		    System.out.println("ERROR");
		}
		data.addU(u, str);
		System.out.println(data.getuserData());
	}
	
	/**
	 * This method adds the new movie to its respective text file and ArrayList
	 * @param title
	 * @param rating 
	 * @param year
	 * @param runTime
	 * @param img - The name of the image stored in the "images" folder
	 * @param genre
	 * @param language
	 * @param director
	 * @param summary - Brief plot summary of the movie
	 */
	public void addMovie(String title, String rating, String year, String runTime, String img, String genre, String language, String director, String summary) {
		String[] arrMovie = {title,rating,year,runTime,img,genre,language,director,summary};
		movies.add(new Movie(arrMovie));
		System.out.println(movies);
		File iaFile = new File("MovieList.txt");
		try {
		    if (iaFile.length() == 0) {
		    	Files.write(Paths.get("MovieList.txt"), (title+","+rating+","+year+","+runTime+","+img+","+genre+","+language+","+director+","+summary).getBytes(), StandardOpenOption.APPEND);
		    }
		    else {
		    	Files.write(Paths.get("MovieList.txt"), ("\n"+title+","+rating+","+year+","+runTime+","+img+","+genre+","+language+","+director+","+summary).getBytes(), StandardOpenOption.APPEND);	
		    }
		}catch (IOException e) {
		    System.out.println("ERROR");
		}
		
		sortAL();
		
	}
	/**
	 * This method changes a user's password in both the text file and ArrayList
	 * @param u - Username
	 * @param pw - New password
	 */
	public void changePword(String u, char[] pw) throws IOException{    
		String str = new String(pw);
		for (int n = 0; n < data.getuserData().size(); n+=2) {
        	if (data.getuserData().get(n).contentEquals(u)) {
        		data.getuserData().set(n+1, str);
        	}
        }
		System.out.println(data.getuserData());
		
		File iaFile = new File("IAuserData.txt");
		File tempFile = new File("myTempFile.txt");
	
		try {
		    Files.write(Paths.get("myTempFile.txt"), (data.getuserData().get(0)+"\n"+data.getuserData().get(1)).getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e) {
		    System.out.println("ERROR");
		}
		
		for (int i = 2; i < data.getuserData().size(); i+=2) {
			String uTemp = data.getuserData().get(i);
			String pTemp = data.getuserData().get(i+1);
			
			try {
			    Files.write(Paths.get("myTempFile.txt"), ("\n"+uTemp+"\n"+pTemp).getBytes(), StandardOpenOption.APPEND);
			}catch (IOException e) {
			    System.out.println("ERROR");
			}
		}
		
		tempFile.renameTo(iaFile);
		File newFile = new File("myTempFile.txt");
		if (newFile.createNewFile()) {
            System.out.println("File created");
        }
		else {
			System.out.println("File could not be created");
		}
    }
	/**
	 * This is an accessor method for the list of either students or admins 
	 * @param n 
	 */
	public IASystemData getData(int n) {
		if (n == 0) {
			return data;
		}
		else{
			return adminData;
		}
	}
	
	/**
	 * Alphabetically sorts each movie in the catalogue by title
	 * Selection sort of time complexity - O(n^2)
	 */
	public void sortAL() {
		int len = movies.size();
		for (int j = 0; j < (len - 1); j++) {
			int tempIndex = j;
			
			for (int i = (j+1); i < len; i++) {
				if (movies.get(i).getTitle().compareToIgnoreCase(movies.get(tempIndex).getTitle()) < 0) {
					tempIndex = i;
				}
			}
			Movie temp = movies.get(tempIndex);
			movies.set(tempIndex, movies.get(j));
			movies.set(j, temp);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controller c = new Controller();
	}
	
	
}
