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

    // Funzione per aggiungere un elemento (Libro o Rivista) all'archivio
    public void addToCatalogueList(ElementoCatalogo elementoCatalogo) {
        this.getCatalogueList().add(elementoCatalogo);
        System.out.println(elementoCatalogo.getTitle() + " aggiunto con successo!");
    }

    // Funzione per rimuovere un dato elemento (se presente) ricevendo come parametro il suo codice ISBN
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

    // Funzione nella quale dato un codice ISBN restituisce l'elemento (se presente) con quel codice
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

    // Funzione che restituisce una lista di elementi (se presenti nell'archivio) in base al numero inserito come parametro
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

    // Funzione che restituisce una lista di elementi (se presenti nell'archivio) in base al nome dell'autore inserito come parametro
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

    // Funzione che salva l'archivio in un file JSON (/files/backups.json)
    public void saveOnDisk() {
        try {
            // SET UPS per l'objectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
            objectMapper.registerSubtypes(Libro.class, Rivista.class);

            // Creiamo il file JSON sfruttando writeValue dell'objectmapper
            objectMapper.writeValue(new File("src/main/java/alessiovulpinari/files/backup.json"), this.getCatalogueList());
            System.out.println("Salvataggio effettuato con successo!");

        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }

    // Funzione che restituisce un elenco di elementi in base agli oggetti salvati nel file JSON
    public List<ElementoCatalogo> loadFromDisk() {

        try {
            // SET UPS per l'objectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
            objectMapper.registerSubtypes(Libro.class, Rivista.class);

            // Leggiamo il file JSON e gli diciamo in che modo convertire gli oggetti salvati all'interno -> typeReference
            List<ElementoCatalogo> backupList = objectMapper.readValue(new File("src/main/java/alessiovulpinari/files/backup.json"), new TypeReference<List<ElementoCatalogo>>() {
            });

            // Ora ritorniamo la lista
            return backupList;

        } catch (IOException err) {
            System.out.println(err.getMessage());
            return null;
        }
    }
}


