import java.util.Objects;

public class Location {
    protected String locacia;
    protected String pronoun;

    public String getLocacia() {
        return locacia;
    }

    public void setLocacia(String locacia) {
        this.locacia = locacia;
    }

    public String getPronoun() {
        return pronoun;
    }

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(locacia, location.locacia) &&
                Objects.equals(pronoun, location.pronoun);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locacia, pronoun);
    }

    @Override
    public String toString() {
        return "Location{" +
                "locacia='" + locacia + '\'' +
                ", pronoun='" + pronoun + '\'' +
                '}';
    }
}
