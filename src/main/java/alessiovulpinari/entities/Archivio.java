package alessiovulpinari.entities;

import java.util.List;
import java.util.Objects;

public class Archivio {
    List<ElementoCatalogo> catalogueList;

    public Archivio(List<ElementoCatalogo> catalogueList) {
        this.catalogueList = catalogueList;
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
            ElementoCatalogo elementToRemove = this.getCatalogueList().stream().filter(elementoCatalogo -> Objects.equals(elementoCatalogo.getIsbn(), isbn)).toList().getFirst();

            if (elementToRemove != null) {
                this.getCatalogueList().remove(elementToRemove);
                System.out.println(elementToRemove.getTitle() + " rimosso con successo!");
            } else {
                System.out.println("Nessun elemento con isbn: " + isbn + " è presente nella tua lista Archivio");
            }

        }
    }
}
