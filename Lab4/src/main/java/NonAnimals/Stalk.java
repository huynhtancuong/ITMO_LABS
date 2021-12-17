package NonAnimals;

import Interfaces.Nameable;
import Interfaces.Pronounceable;

import java.util.Objects;

public class Stalk implements Nameable, Pronounceable {
    private String name;
    private String pronoun;

    public String wasElastic() {
        return "был упругий ";
    }
    public String likeRubber() {
        return "словно резиновый ";
    }
    public String wasNotPulled() {
        return "не вытаскивался ";
    }
    public String wrappedAround() {
        return "обвился вокруг ";
    }
    public String tookOff() {
        return "снял ";
    }
    public String chance() {
        return "Как бы невзначай ";
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
        Stalk that = (Stalk) o;
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
