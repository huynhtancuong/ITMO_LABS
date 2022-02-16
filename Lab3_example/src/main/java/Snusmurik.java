import java.util.Objects;

public class Snusmurik extends Characters {
    public String name;
    public String description;

    public Snusmurik(String name, String description) {
        setName(name);
        setDescription(description);
    }

    public Snusmurik(String name) {
        setName(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Snusmurik snusmurik = (Snusmurik) o;
        return Objects.equals(name, snusmurik.name) &&
                Objects.equals(description, snusmurik.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description);
    }

    @Override
    public String toString() {
        return "Snusmurik{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
