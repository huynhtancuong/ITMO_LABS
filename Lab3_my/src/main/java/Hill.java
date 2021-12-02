import java.util.Objects;

public class Hill implements Nameable, Pronounceable, Describable{
    private String name;
    private String pronoun;
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
    public String getPronoun() {
        return pronoun;
    }

    @Override
    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hill that = (Hill) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(pronoun, that.pronoun) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pronoun);
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pronoun='" + pronoun + '\'' +
                '}';
    }
}
