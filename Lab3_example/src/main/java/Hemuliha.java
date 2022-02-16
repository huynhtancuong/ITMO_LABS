import java.util.Objects;

public class Hemuliha extends Characters {
    private String name;
    private String description;

    public Hemuliha(String name) {
        setName(name);
    }

    public Hemuliha(String description, String name) {
        setDescription(description);
        setName(name);
    }

    public static String treated() {
        return "угощает";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Hemuliha hemuliha = (Hemuliha) o;
        return Objects.equals(name, hemuliha.name) &&
                Objects.equals(description, hemuliha.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description);
    }

    @Override
    public String toString() {
        return "Hemuliha{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
