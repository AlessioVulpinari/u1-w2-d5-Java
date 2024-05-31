package alessiovulpinari.entities;

import java.time.LocalDate;

public class Rivista extends ElementoCatalogo {

    private Periodicity periodicity;

    public Rivista(String title, LocalDate yearOfPublication, int numberOfPages, Periodicity periodicity) {
        super(title, yearOfPublication, numberOfPages);
        this.setPeriodicity(periodicity);

    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "periodicity=" + periodicity +
                '}';
    }
}
