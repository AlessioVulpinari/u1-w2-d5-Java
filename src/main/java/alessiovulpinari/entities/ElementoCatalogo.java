package alessiovulpinari.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.javafaker.Faker;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Libro.class, name = "alessiovulpinari.entities.Libro"),
        @JsonSubTypes.Type(value = Rivista.class, name = "alessiovulpinari.entities.Rivista")
})

public abstract class ElementoCatalogo {
    private String isbn;
    private String title;
    private int yearOfPublication;
    private int numberOfPages;

    public ElementoCatalogo() {
    }

    public ElementoCatalogo(String title, int yearOfPublication, int numberOfPages) {
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

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
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
