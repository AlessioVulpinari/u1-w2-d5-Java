package alessiovulpinari.entities;

import java.util.List;

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
}
