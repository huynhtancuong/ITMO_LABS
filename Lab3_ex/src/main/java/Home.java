import java.util.Objects;

public class Home extends Location {

    public Home(String locacia) {
        setLocacia(locacia);
    }

    public Home(String locacia, String pronoun) {
        setLocacia(locacia);
        setPronoun(pronoun);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Home home = (Home) o;
        return Objects.equals(locacia, home.locacia) &&
                Objects.equals(pronoun, home.pronoun);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locacia, pronoun);
    }

    @Override
    public String toString() {
        return "Home{" +
                "locacia='" + locacia + '\'' +
                ", pronoun='" + pronoun + '\'' +
                '}';
    }
}
