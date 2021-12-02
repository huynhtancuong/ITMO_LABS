import java.util.Objects;

public class Roof extends Location {
    private String locacia;
    private String pronoun;

    public Roof(String locacia) {
        setLocacia(locacia);
    }

    public Roof(String locacia, String pronoun) {
        setLocacia(locacia);
        setPronoun(pronoun);
    }
    public static String stick(){
        return "просмолили";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Roof roof = (Roof) o;
        return Objects.equals(locacia, roof.locacia) &&
                Objects.equals(pronoun, roof.pronoun);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), locacia, pronoun);
    }

    @Override
    public String toString() {
        return "Roof{" +
                "locacia='" + locacia + '\'' +
                ", pronoun='" + pronoun + '\'' +
                '}';
    }
}
