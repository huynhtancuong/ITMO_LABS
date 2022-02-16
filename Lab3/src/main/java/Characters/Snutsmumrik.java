package Characters;

import java.util.Objects;

public class Snutsmumrik extends Characters{
    public Snutsmumrik(String name, String description) {
        setName(name);
        setDescription(description);
    }
    public Snutsmumrik(String name) {
        setName(name);
    }
    public String stepped_forward() {
        return "выступил вперед ";
    }
    public String inspect() {
        return "осматривать ";
    }
    public String grabbed() {
        return "ухватился ";
    }
    public String pulled() {
        return "отянул ";
    }
    public String began_to_examine_with_interest() {
        return "с интересом стал осматривать ";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Snutsmumrik snutsmumrik = (Snutsmumrik) o;
        return Objects.equals(name, snutsmumrik.name) &&
                Objects.equals(description, snutsmumrik.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description);
    }

}
