package NonAnimals;

import Interfaces.Nameable;
import Interfaces.Pronounceable;

import java.util.Objects;

public class Earth implements Nameable, Pronounceable {
    private String name;
    private String pronoun;

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
        Earth that = (Earth) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(pronoun, that.pronoun);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pronoun);
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "name='" + name + '\'' +
                ", pronoun='" + pronoun + '\'' +
                '}';
    }
}
