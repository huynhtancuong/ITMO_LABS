import java.util.Objects;

public class Mumitroll extends Characters{
    public Mumitroll(String name, String description) {
        setName(name);
        setDescription(description);
    }
    public Mumitroll(String name) {
        setName(name);
    }

    public String stand() {
        return "стоял ";
    }
    public String look_around_in_surprise() {
        return "удивленно озирал ";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Mumitroll mumitroll = (Mumitroll) o;
        return Objects.equals(name, mumitroll.name) &&
                Objects.equals(description, mumitroll.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description);
    }

}
