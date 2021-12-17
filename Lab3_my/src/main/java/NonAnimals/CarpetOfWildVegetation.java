package NonAnimals;

import Interfaces.Describable;
import Interfaces.Nameable;

import java.util.Objects;

public class CarpetOfWildVegetation implements Nameable, Describable {
    private String name;
    private String description;
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarpetOfWildVegetation that = (CarpetOfWildVegetation) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
