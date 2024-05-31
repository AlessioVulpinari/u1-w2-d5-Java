package alessiovulpinari.entities;

public class Rivista extends ElementoCatalogo {

    private Periodicity periodicity;

    public Rivista() {
    }

    public Rivista(String title, int yearOfPublication, int numberOfPages, Periodicity periodicity) {
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
                ", title='" + super.getTitle() + '\'' +
                ", year of publication='" + super.getYearOfPublication() + '\'' +
                ", pages='" + super.getNumberOfPages() + '\'' +
                '}';
    }
}
