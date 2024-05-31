package alessiovulpinari.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Archivio {
    List<ElementoCatalogo> catalogueList;

    public Archivio() {
        this.catalogueList = new ArrayList<>();
    }

    public List<ElementoCatalogo> getCatalogueList() {
        return catalogueList;
    }

    public void setCatalogueList(List<ElementoCatalogo> catalogueList) {
        this.catalogueList = catalogueList;
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
}
