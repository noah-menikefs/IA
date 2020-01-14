package ia;
public class Movie {

	private String title = "";
	private String rating = "";
	private String year = "";
	private String runTime = "";
	private String img = "";
	private String genre = "";
	private String language = "";
	private String director = "";
	private String summary = "";

	
	public Movie(String[] arrMovie) {
		title = arrMovie[0];
		rating = arrMovie[1];
		year = arrMovie[2];
		runTime = arrMovie[3];
		img = arrMovie[4];
		genre = arrMovie[5];
		language = arrMovie[6];
		director = arrMovie[7];
		summary = arrMovie[8];
	}
	
	public Movie(Movie movie) {
		title = movie.title;
		rating = movie.rating;
		year = movie.year;
		runTime = movie.runTime;
		img = movie.img;
		genre = movie.genre;
		language = movie.language;
		director = movie.director;
		summary = movie.summary;
	}
	
	public String getTitle() { return title; }
	public String getRating() { return rating; }
	public String getYear() { return year; }
	public String getRunTime() { return runTime; }
	public String getImg() { return img; }
	public String getGenre() { return genre; }
	public String getLanguage() { return language; }
	public String getDirector() { return director; }
	public String getSummary() { return summary; }
	
	public String toString() {
		return (title);
	}
	
}
