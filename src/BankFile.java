import java.io.*;
import  java.util.*;


public class BankFile {
	Database db = new Database();

	String fname ="database.txt";

	/**
	 * Constructor.
	 * @param name Name of file
	 */
	public BankFile(String name) {
		fname=name;
	}

	/**
	 * Clear file records.
	 */
	public void clear () {	
		new File(fname).delete();
	}

	/**
	 * Load file into user profile.
	 * @param users User profiles
	 */

}