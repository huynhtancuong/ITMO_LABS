import java.util.Objects;

public class Forest extends Location {
    private String locacia;
    private String pronoun;

    public Forest(String locacia) {
        setLocacia(locacia);
    }

    public Forest(String locacia, String pronoun) {
        setLocacia(locacia);
        setPronoun(pronoun);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forest forest = (Forest) o;
        return Objects.equals(locacia, forest.locacia) &&
                Objects.equals(pronoun, forest.pronoun);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locacia, pronoun);
    }

    @Override
    public String
    toString() {
        return "Forest{" +
                "locacia='" + locacia + '\'' +
                ", pronoun='" + pronoun + '\'' +
                '}';
    }
}
