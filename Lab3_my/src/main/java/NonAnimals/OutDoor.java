package NonAnimals;

import java.util.Objects;

public class OutDoor extends Location {
    public OutDoor() {
        super();
    }
    public OutDoor(String location) {
        super(location);
    }
    public OutDoor(String location, String pronoun) {
        super(location, pronoun);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutDoor home = (OutDoor) o;
        return Objects.equals(location, home.location) &&
                Objects.equals(pronoun, home.pronoun);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, pronoun);
    }

}
