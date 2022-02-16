import java.util.Objects;

public class Guests extends Characters {
    private String name;
    private String description;

    public Guests(String name) {
        setName(name);
    }

    public Guests(String name, String description) {
        setName(name);
        setDescription(description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Guests guests = (Guests) o;
        return Objects.equals(name, guests.name) &&
                Objects.equals(description, guests.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description);
    }

    @Override
    public String toString() {
        return "Guests{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
