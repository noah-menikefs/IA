package ia;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class holds all the information about the
 * users in the system.
 * 
 * @author noah.menikefs
 *
 */
public class IASystemData {

	private Scanner data;
	private ArrayList<String> userData = new ArrayList<String>();

	//private ArrayList<String> loginInfo = new ArrayList<String>();
	
	public IASystemData(String fileName) {
		
		//Go to a file and access all the user data for the system
		try {
			//Code try			
			data = new Scanner(new File(fileName));
		}
		catch (Exception e) {
			//Code to run if all else fails.
			System.out.println("ERROR - FILE ACCESS");
		}
		
		//A file is a collection
		while (data.hasNext()){
			userData.add(data.nextLine());
		}
	}
	
	public ArrayList<String> getuserData(){
		return this.userData;
	}
	
	public void addU(String u, String pw) {
		userData.add(u);
		userData.add(pw);
	}
}
