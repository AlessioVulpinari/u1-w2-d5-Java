package alessiovulpinari.entities;

import java.time.LocalDate;

public class Libro extends ElementoCatalogo {

    private String author;
    private String genre;


    public Libro(String title, LocalDate yearOfPublication, int numberOfPages, String author, String genre) {
        super(title, yearOfPublication, numberOfPages);
        this.setAuthor(author);
        this.setGenre(genre);
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", title='" + super.getTitle() + '\'' +
                ", year of publication='" + super.getYearOfPublication() + '\'' +
                ", pages='" + super.getNumberOfPages() + '\'' +
                '}';
    }
}
