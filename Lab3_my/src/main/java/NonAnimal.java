import java.util.Objects;

public abstract class NonAnimal implements Nameable, Describable{
    protected String name;
    protected String description;

    @Override
    public void setName(String name) {
        this.name = name;
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
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NonAnimal that = (NonAnimal) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return "NonAnimal{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
