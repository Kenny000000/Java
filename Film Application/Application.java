import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main class is called the application
 */
public class Application {

	static ArrayList<Film> allFilms = new ArrayList<Film>();
	static ArrayList<Director> allDirectors = new ArrayList<Director>();
	static Scanner input = new Scanner(System.in);
	static InputStreamReader textInput = new InputStreamReader(System.in);
	static BufferedReader reader = new BufferedReader(textInput);
	static DecimalFormat currency = new DecimalFormat("€0.00");
	static int centralFilmID = 200;
	static int centralDirectorID = 12;

	/**
	 * Main method to start the application.
	 * 
	 * @param args Command line arguments(not used in this application).
	 */
	public static void main(String[] args) {
		prePopulate();
		menu();
	}

	/**
	 * Pre-populates the application with some data (directors and films).
	 */
	public static void prePopulate() {
		Director mBay = new Director(10, "Micheal Bay");
		Director rRothman = new Director(11, "Rodney Rothman");

		Film transformers = new Film(196, "Transformers: Age of Extinction", mBay, 12.99, 20);
		Film spiderMan = new Film(197, "Spider-Man: Into the Spider-Verse", rRothman, 11.99, 15);
		Film jumpStreet = new Film(198, " 22 Jump Street", rRothman, 2.99, 10);
		Film pearlHarbor = new Film(199, "Pearl Harbor", mBay, 4.99, 5);

		mBay.addFilm(transformers);
		rRothman.addFilm(spiderMan);
		rRothman.addFilm(jumpStreet);
		mBay.addFilm(pearlHarbor);

		allFilms.add(transformers);
		allFilms.add(spiderMan);
		allFilms.add(jumpStreet);
		allFilms.add(pearlHarbor);

		allDirectors.add(mBay);
		allDirectors.add(rRothman);
	}

	/**
	 * Displays the main menu and prompts the user for their choice
	 */
	public static void menu() {
		System.out.println("---------------------------------");
		System.out.println("Press 1 for Director Menu");
		System.out.println("Press 2 for Film Menu");
		System.out.println("---------------------------------");
		

		String choice = input.next();
		switch (choice) {
		case "1": {
			directorMenu();
			break;
		}
		case "2": {
			filmMenu();
			break;
		}
		case "a", "A": {
			admin();
			break;
		}
		}
		menu();
	}

	/**
	 * Displays admin information like total number of films,
	 * and the overall stock value of films available.
	 */
	private static void admin() {
		// Output the total number of films
		System.out.println("Total Number of Films: " + allFilms.size());

		// Output the total number of directors
		System.out.println("Total Number of Directors: " + allDirectors.size());

		// initialize stockValue variable
		double stockValue = 0;

		// Loop through each films in allFilms
		for (Film f : allFilms) {
			stockValue = stockValue + (f.getFilmPrice() * f.getFilmQty());
		}

		// Output the calculated stock value with the currency formatted
		System.out.println("Stock Value: " + currency.format(stockValue));
	}

	/**
	 * Displays the film menu and prompts the user for their choice
	 */
	private static void filmMenu() {
		System.out.println("---------------------------------");
		System.out.println("Press 1 - Create New Film");
		System.out.println("Press 2 - Edit Film");
		System.out.println("Press 3 - Delete Film");
		System.out.println("Press 4 - View All Films");
		System.out.println("Press 5 - View Films By Directors");
		System.out.println("Press m - Return to Main Menu");
		System.out.println("---------------------------------");

		String choice = input.next();
		switch (choice) {
		case "1": {
			try {
				createFilm();
			} catch (Exception e) {
				System.out.println("Error creating new Film");
			}
			break;
		}
		case "2": {
			try {
				editFilm();
			} catch (Exception e) {
				System.out.println("Error editing film");
			}
			break;
		}
		case "3": {
			deleteFilm();
			break;
		}
		case "4": {
			viewAllFilms();
			break;
		}
		case "5": {
			printFilmsByDirector();
		}
		case "m", "M": {
			menu();
			break;
		}
		}

	}

	/**
	 * Prints the list of films directed by a specified director.
	 */
	private static void printFilmsByDirector() {

		viewDirectors();
		System.out.println("Choose Director by ID to view their Films:");
		int chosenDirector = input.nextInt();
		boolean foundDirector = false;
		
		for(Director d: allDirectors) {
			if(chosenDirector == d.getDirectorID()) {
				foundDirector = true;
				d.printFilms();
			}
		}
		if(foundDirector==false) {
			System.out.println("No Director found with this ID. Please try again");
		}
	}

	/**
	 * Displays details of all films
	 */
	private static void viewAllFilms() {
		for (Film f : allFilms) {
			f.printDetails();
		}
	}

	/**
	 * Deletes a film from this application after finding it by its ID.
	 */
	private static void deleteFilm() {
		Film toDelete = findFilm();
		if (toDelete == null) {
			System.out.println("No Film with ID number found");
		} else {
			allFilms.remove(toDelete);
			toDelete.getFilmDirector().removeFilm(toDelete);
		}

	}

	/**
	 * Edits the details of a film after finding it by its ID.
	 * 
	 * @throws Exception if an error occurs during input.
	 */
	private static void editFilm() throws Exception {
		Film toEdit = findFilm();
		if (toEdit == null) {
			System.out.println("No Film with ID found");
		} else {
			editFilmMenu(toEdit);
		}

	}

	/**
	 * Display a sub-menu for editing specified details of a film.
	 * 
	 * @param toEdit The film to be edited.
	 * @throws Exception if an error occurs during input.
	 */
	private static void editFilmMenu(Film toEdit) throws Exception {
		System.out.println("---------------------------------");
		System.out.println("Press 1 - Edit Film Title");
		System.out.println("Press 2 - Edit Film Price");
		System.out.println("Press 3 - Edit Film Quantity");
		System.out.println("Press m - Return to Menu");
		System.out.println("---------------------------------");

		String choice = input.next();

		switch (choice) {
		case "1": {
			System.out.println("Enter new Title");
			toEdit.setFilmTitle(reader.readLine());
			break;
		}
		case "2": {
			System.out.println("Enter Film Price");
			toEdit.setFilmPrice(input.nextDouble());
			break;
		}
		case "3": {
			System.out.println("Enter Film Quantity");
			toEdit.setFilmQty(input.nextInt());
		}
		case "m","M":{
			return;
		}
		}
		editFilmMenu(toEdit);
	}

	/**
	 * Finds a film by its ID and returns it.
	 * 
	 * @return The found film or null if not found.
	 */
	private static Film findFilm() {
		viewAllFilms();
		System.out.println("Select Film by ID number");
		int toFind = input.nextInt();
		Film foundFilm = null;

		for (Film f : allFilms) {
			if (f.getFilmID() == toFind) {
				foundFilm = f;
			}
		}
		return foundFilm;
	}

	/**
	 * Creates a new film by taking input from the user.
	 * 
	 * @throws Exception if an error occurs during input.
	 */
	private static void createFilm() throws Exception {
		Film f = new Film();
		f.setFilmID(centralFilmID);
		centralFilmID++;
		System.out.println("Enter Film Title:");
		f.setFilmTitle(reader.readLine());
		System.out.println("Enter Film Price:");
		f.setFilmPrice(input.nextDouble());
		System.out.println("Enter Film Quantity:");
		f.setFilmQty(input.nextInt());
		System.out.println("Choose Director:");
		Director chosenDirector = findDirector();

		if (chosenDirector == null) {
			System.out.println("No matching Director was found. Please try again");
		} else {
			f.setFilmDirector(chosenDirector);
			chosenDirector.addFilm(f);
			allFilms.add(f);
			System.out.println(f.getFilmTitle() + " added to " + chosenDirector.getDirectorName());
		}

	}

	/**
	 * Displays the director menu and prompts the user for their choice.
	 */
	private static void directorMenu() {
		System.out.println("---------------------------------");
		System.out.println("Press 1 - Create Director");
		System.out.println("Press 2 - Edit Director");
		System.out.println("Press 3 - Delete Director");
		System.out.println("Press 4 - View Director");
		System.out.println("Press 5 - View Films by Director");
		System.out.println("Press m - Return to menu");
		System.out.println("---------------------------------");
		String choice = input.next();

		switch (choice) {
		case "1": {
			try {
				createDirector();
			} catch (Exception e) {
				System.out.println("Error logged Creating an Director");
			}
			break;
		}
		case "2": {
			try {
				editDirector();
			} catch (Exception e) {
				System.out.println("Eror logged in Edit Director");
			}
			break;
		}
		case "3": {
			deleteDirectors();
			break;
		}
		case "4": {
			viewDirectors();
			break;
		}
		case "5": {
			viewFilmsByDirector();
			break;
		}
		case "m","M": {
			menu();
			break;
		}
		}
		directorMenu();

	}

	/**
	 * Prints the list of films directed by a chosen director.
	 */
	private static void viewFilmsByDirector() {
		Director toPrint = findDirector();
		if (toPrint == null) {
			System.out.println("No matching ID found");
		} else {
			toPrint.printFilms();
		}

	}

	/**
	 * Deletes a director from the application after finding it by its ID.
	 */
	private static void deleteDirectors() {
		Director toDelete = findDirector();

		if (toDelete == null) {
			System.out.println("No matching ID found");
		} else {
			System.out.println(toDelete.getDirectorName() + " has been succesfully deleted.");
			allDirectors.remove(toDelete);
		}

	}

	/**
	 * Finds a director by its ID and returns it.
	 * 
	 * @return The found director or null if not found.
	 */
	private static Director findDirector() {
		viewDirectors();
		Director foundDirector = null;
		System.out.println("Enter ID of Director:");
		int toFind = input.nextInt();
		
		for (Director d : allDirectors) {
			if (d.getDirectorID() == toFind) {
				foundDirector = d;
			}
		}
		return foundDirector;
	}

	/**
	 * Displays details of all directors in the application.
	 */
	private static void viewDirectors() {
		for (Director d : allDirectors) {
			System.out.println("ID: " + d.getDirectorID());
			System.out.println("Director: " + d.getDirectorName());
			System.out.println("----------------------------");
		}

	}

	/**
	 * Edits the details of a director after finding it by its ID.
	 * 
	 * @throws Exception if an error occurs during input.
	 */
	private static void editDirector() throws Exception {
		Director toEdit = findDirector();
		if (toEdit == null) {
			System.out.println("No matching ID found");
		} else {
			editDirectorMenu(toEdit);
		}

	}

	/**
	 * Displays a sub-menu for editing specific details of a director.
	 * 
	 * @param toEdit The director to be edited.
	 * @throws Exception Exception if an error occurs during input.
	 */
	private static void editDirectorMenu(Director toEdit) throws Exception {
		System.out.println("Press 1 to Edit Name:");

		String choice = input.next();
		switch (choice) {
		case "1": {
			System.out.println("Enter new name to replace " + toEdit.getDirectorName() + ":");
			toEdit.setDirectorName(reader.readLine());
			System.out.println("The Director has succesfully been replaced with " + toEdit.getDirectorName());
			break;
		}
		}

	}

	/**
	 * Creates a new director by taking input from the user.
	 * 
	 * @throws Exception if an error occurs during input.
	 */
	private static void createDirector() throws Exception {
		Director d = new Director();
		d.setDirectorID(centralDirectorID);
		centralDirectorID++;
		System.out.println("Enter Director name:");
		d.setDirectorName(reader.readLine());
		allDirectors.add(d);
		System.out.println("The Director has succesfully been added.");
	}

}
