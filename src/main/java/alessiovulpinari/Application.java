package alessiovulpinari;

import alessiovulpinari.entities.*;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Application {

    public static void main(String[] args) {
        Archivio archivio = new Archivio();

        Libro libro1 = createBook();
        Libro libro2 = createBook();
        Libro libro3 = createBook();

        Rivista rivista1 = createMagazine();
        Rivista rivista2 = createMagazine();

        archivio.addToCatalogueList(libro1);
        archivio.addToCatalogueList(rivista1);

        ElementoCatalogo elementoCatalogo1 = archivio.searchByIsbn(rivista2.getIsbn());
        ElementoCatalogo elementoCatalogo2 = archivio.searchByIsbn(rivista1.getIsbn());

        List<ElementoCatalogo> elementoCatalogoList = archivio.searchByYear(rivista1.getYearOfPublication());
        elementoCatalogoList.forEach(System.out::println);

        archivio.removeCatalogueElement(rivista1.getIsbn());
        archivio.removeCatalogueElement(libro3.getIsbn());
    }

    public static Libro createBook() {

        Supplier<Libro> bookSupplier = () -> {
            Faker faker = new Faker();
            Random random = new Random();
            return new Libro(faker.book().title(), LocalDate.now().minusYears(random.nextInt(0, 200)).getYear(), random.nextInt(200, 600), faker.book().author(), faker.book().genre());
        };

        return bookSupplier.get();
    }

    public static Rivista createMagazine() {

        Supplier<Rivista> magazineSupplier = () -> {
            Faker faker = new Faker();
            Random random = new Random();
            Periodicity[] periodicity = {Periodicity.MENSILE, Periodicity.SEMESTRALE, Periodicity.SETTIMANALE};
            return new Rivista(faker.book().title(), LocalDate.now().minusYears(random.nextInt(0, 200)).getYear(), random.nextInt(200, 600), periodicity[random.nextInt(0, 3)]);
        };

        return magazineSupplier.get();
    }
}
