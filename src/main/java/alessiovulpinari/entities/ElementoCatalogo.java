package alessiovulpinari.entities;

import com.github.javafaker.Faker;

import java.time.LocalDate;

public abstract class ElementoCatalogo {
    private String isbn;
    private String title;
    private LocalDate yearOfPublication;
    private int numberOfPages;

    public ElementoCatalogo(String title, LocalDate yearOfPublication, int numberOfPages) {
        this.setIsbn(generateIsbn());
        this.setTitle(title);
        this.setYearOfPublication(yearOfPublication);
        this.setNumberOfPages(numberOfPages);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(LocalDate yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    private String generateIsbn() {
        Faker faker = new Faker();
        return faker.code().isbn10();
    }

}
