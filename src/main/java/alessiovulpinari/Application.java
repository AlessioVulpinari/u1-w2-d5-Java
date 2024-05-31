package alessiovulpinari;

import alessiovulpinari.entities.*;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Application {

    public static void main(String[] args) {
        // Istanziamo un Archivio
        Archivio archivio = new Archivio(new ArrayList<>());

        // Istanziamo dei libri sfruttando un metodo che ci restituisce un libro con dati random
        Libro libro1 = createBook();
        Libro libro2 = createBook();
        Libro libro3 = createBook();

        // Identica cosa per le riviste
        Rivista rivista1 = createMagazine();
        Rivista rivista2 = createMagazine();

        // Aggiungiamo qualche elemento al catalogo
        archivio.addToCatalogueList(libro1);
        archivio.addToCatalogueList(rivista1);

        // Testiamo le funzionalità di salvataggio e di caricamento dei dati tramite file JSON
        archivio.saveOnDisk();
        List<ElementoCatalogo> backUp = archivio.loadFromDisk();
        // Li visualizziamo a schermo
        System.out.println(backUp);

        // Testiamo la funzionalità di ricerca per ISBN, con dati presenti e con dati non presenti nell'archivio
        ElementoCatalogo elementoCatalogo1 = archivio.searchByIsbn(rivista2.getIsbn());
        ElementoCatalogo elementoCatalogo2 = archivio.searchByIsbn(rivista1.getIsbn());

        // Testiamo la funzionalità di ricerca per ANNO
        List<ElementoCatalogo> searchByYearList = archivio.searchByYear(rivista1.getYearOfPublication());
        searchByYearList.forEach(System.out::println);

        // Testiamo la funzionalità di ricerca per AUTORE
        List<ElementoCatalogo> searchByAuthorList = archivio.searchByAuthor(libro1.getAuthor());
        searchByAuthorList.forEach(System.out::println);

        // Testiamo la funzionalità di rimozione degli elementi dato un codice ISBN,
        // con dati presenti e con dati non presenti nell'archivio
        archivio.removeCatalogueElement(rivista1.getIsbn());
        archivio.removeCatalogueElement(libro3.getIsbn());
    }

    // Funzione che restituisce un Libro con dati random sfruttando la libreria Faker.
    public static Libro createBook() {

        Supplier<Libro> bookSupplier = () -> {
            Faker faker = new Faker();
            Random random = new Random();
            return new Libro(faker.book().title(), LocalDate.now().minusYears(random.nextInt(0, 200)).getYear(),
                    random.nextInt(200, 600), faker.book().author(), faker.book().genre());
        };

        return bookSupplier.get();
    }

    // Funzione che restituisce una rivista con dati random sfruttando la libreria Faker.
    public static Rivista createMagazine() {

        Supplier<Rivista> magazineSupplier = () -> {
            Faker faker = new Faker();
            Random random = new Random();
            Periodicity[] periodicity = {Periodicity.MENSILE, Periodicity.SEMESTRALE, Periodicity.SETTIMANALE};
            return new Rivista(faker.book().title(), LocalDate.now().minusYears(random.nextInt(0, 200)).getYear(),
                    random.nextInt(200, 600), periodicity[random.nextInt(0, 3)]);
        };

        return magazineSupplier.get();
    }
}
