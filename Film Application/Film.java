
public class Film {
	private int filmID;
	private String filmTitle;
	private Director filmDirector;
	private double filmPrice;
	private int filmQty;

	public Film() {

	}

	public Film(int filmID, String filmTitle, Director filmDirector, double filmPrice, int filmQty) {
		this.filmID = filmID;
		this.filmTitle = filmTitle;
		this.filmDirector = filmDirector;
		this.filmPrice = filmPrice;
		this.filmQty = filmQty;
	}

	public int getFilmID() {
		return filmID;
	}

	public void setFilmID(int filmID) {
		this.filmID = filmID;
	}

	public String getFilmTitle() {
		return filmTitle;
	}

	public void setFilmTitle(String filmTitle) {
		this.filmTitle = filmTitle;
	}

	public Director getFilmDirector() {
		return filmDirector;
	}

	public void setFilmDirector(Director filmDirector) {
		this.filmDirector = filmDirector;
	}

	public double getFilmPrice() {
		return filmPrice;
	}

	public void setFilmPrice(double filmPrice) {
		this.filmPrice = filmPrice;
	}

	public int getFilmQty() {
		return filmQty;
	}

	public void setFilmQty(int filmQty) {
		this.filmQty = filmQty;
	}

	public void printDetails() {
		System.out.println("--------------------------------------------------------");
		System.out.println("Film ID: " + filmID + "\t");
		System.out.println("Film Name: " + filmTitle + "\t");
		System.out.println("Directed by: " + filmDirector.getDirectorName() + "\t");
		System.out.println("FIlm Price: €" +filmPrice+"\t");
		System.out.println("Quantity: " + filmQty + "\t");
		System.out.println("--------------------------------------------------------");
	}

}
