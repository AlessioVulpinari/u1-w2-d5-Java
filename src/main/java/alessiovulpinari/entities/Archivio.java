package alessiovulpinari.entities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Archivio {
    List<ElementoCatalogo> catalogueList;

    public Archivio() {
    }

    public Archivio(List<ElementoCatalogo> elementoCatalogoList) {
        this.catalogueList = elementoCatalogoList;
    }

    public List<ElementoCatalogo> getCatalogueList() {
        return catalogueList;
    }

    public void setCatalogueList(List<ElementoCatalogo> catalogueList) {
        this.catalogueList = catalogueList;
    }

    @Override
    public String toString() {
        return "Archivio{" +
                "catalogueList=" + catalogueList +
                '}';
    }

    public void addToCatalogueList(ElementoCatalogo elementoCatalogo) {
        this.getCatalogueList().add(elementoCatalogo);
        System.out.println(elementoCatalogo.getTitle() + " aggiunto con successo!");
    }

    public void removeCatalogueElement(String isbn) {
        if (this.getCatalogueList().isEmpty()) {
            System.out.println("Il tuo archivio è vuoto!");
        } else {
            try {
                ElementoCatalogo elementToRemove = this.getCatalogueList().stream().filter(elementoCatalogo -> Objects.equals(elementoCatalogo.getIsbn(), isbn)).toList().getFirst();
                this.getCatalogueList().remove(elementToRemove);
                System.out.println(elementToRemove.getTitle() + " rimosso con successo!");
            } catch (NoSuchElementException err) {
                System.out.println("Nessun elemento con isbn: " + isbn + " è presente nella tua lista Archivio");
            }
        }
    }

    public ElementoCatalogo searchByIsbn(String isbn) {
        if (this.getCatalogueList().isEmpty()) {
            System.out.println("Il tuo archivio è vuoto!");
            return null;
        } else {
            try {
                ElementoCatalogo elementSearched = this.getCatalogueList().stream()
                        .filter(elementoCatalogo -> Objects.equals(elementoCatalogo.getIsbn(), isbn)).toList().getFirst();
                System.out.println("Elemento trovato: " + elementSearched.getTitle());
                return elementSearched;
            } catch (NoSuchElementException err) {
                System.out.println("Nessun elemento con isbn: " + isbn + " è presente nella tua lista Archivio");
                return null;
            }
        }
    }

    public List<ElementoCatalogo> searchByYear(int year) {
        if (this.getCatalogueList().isEmpty()) {
            System.out.println("Il tuo archivio è vuoto!");
            return null;
        } else {
            List<ElementoCatalogo> elementoCatalogoList = this.getCatalogueList().stream()
                    .filter(elementoCatalogo -> Objects.equals(elementoCatalogo.getYearOfPublication(), year)).toList();
            if (elementoCatalogoList.isEmpty()) {
                System.out.println("Nessun elemento uscito nel: " + year + " è presente nella tua lista Archivio.");
            }
            return elementoCatalogoList;
        }
    }

    public List<ElementoCatalogo> searchByAuthor(String author) {
        if (this.getCatalogueList().isEmpty()) {
            System.out.println("Il tuo archivio è vuoto!");
            return null;
        } else {
            List<ElementoCatalogo> searchByAuthorList = this.getCatalogueList().stream()
                    .filter(elementoCatalogo -> elementoCatalogo instanceof Libro)
                    .filter(libro -> Objects.equals(((Libro) libro).getAuthor(), author)).toList();
            if (searchByAuthorList.isEmpty()) {
                System.out.println("Nessun libro di: " + author + " è presente nella tua lista Archivio.");
            }

            return searchByAuthorList;
        }
    }

    public void saveOnDisk() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
            objectMapper.registerSubtypes(Libro.class, Rivista.class);
            objectMapper.writeValue(new File("src/main/java/alessiovulpinari/files/backup.json"), this.getCatalogueList());
            System.out.println("Salvataggio effettuato con successo!");

        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }

    public List<ElementoCatalogo> loadFromDisk() {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
            objectMapper.registerSubtypes(Libro.class, Rivista.class);

            List<ElementoCatalogo> backupList = objectMapper.readValue(new File("src/main/java/alessiovulpinari/files/backup.json"), new TypeReference<List<ElementoCatalogo>>() {
            });

            return backupList;

        } catch (IOException err) {
            System.out.println(err.getMessage());
            return null;
        }
    }
}


