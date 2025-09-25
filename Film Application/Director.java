
import java.util.ArrayList;

public class Director {
	private int directorID;
	private String directorName;
	private ArrayList<Film> releasedFilms = new ArrayList<Film>();

	public Director() {

	}

	public Director(int directorID, String directorName) {
		this.directorID = directorID;
		this.directorName = directorName;
	}

	public Director(int directorID, String directorName, ArrayList<Film> releasedFilms) {
		this.directorID = directorID;
		this.directorName = directorName;
		this.releasedFilms = releasedFilms;
	}

	public void addFilm(Film f) {
		System.out.println("----------------------------------------------------------------");
		System.out.println(f.getFilmTitle() + " has been added.");
		releasedFilms.add(f);
	}

	public void removeFilm(Film f) {
		System.out.println("----------------------------------------------------------------");
		System.out.println(f.getFilmTitle() + " has been removed.");
		releasedFilms.remove(f);
	}

	public void printFilms() {
		System.out.println("----" + directorName + "--------");

		for (Film f : releasedFilms) {
			System.out.print(f.getFilmID() + "\t");
			System.out.print(f.getFilmTitle() + "\t");
			System.out.print(" Ticket Quantity: " + f.getFilmQty() + "\t");
			System.out.println("€"+f.getFilmPrice());
		}

		System.out.println("-------------------------------");

	}

	public void printDirectorDetails() {
		System.out.println(directorID + "\t Name: " + directorName);
	}

	public int getDirectorID() {
		return directorID;
	}

	public void setDirectorID(int directorID) {
		this.directorID = directorID;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public ArrayList<Film> getReleasedFilms() {
		return releasedFilms;
	}

	public void setReleasedFilms(ArrayList<Film> releasedFilms) {
		this.releasedFilms = releasedFilms;
	}

}
