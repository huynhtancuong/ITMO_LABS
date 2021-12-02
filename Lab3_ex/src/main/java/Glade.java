import java.util.Objects;

public class Glade extends Location {
    private String locacia;
    private String pronoun;

    public Glade(String locacia) {
        setLocacia(locacia);
    }

    public Glade(String locacia, String pronoun) {
        setLocacia(locacia);
        setPronoun(pronoun);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Glade glade = (Glade) o;
        return Objects.equals(locacia, glade.locacia) &&
                Objects.equals(pronoun, glade.pronoun);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locacia, pronoun);
    }

    @Override
    public String toString() {
        return "Glade{" +
                "locacia='" + locacia + '\'' +
                ", pronoun='" + pronoun + '\'' +
                '}';
    }
}
