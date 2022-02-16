import java.util.Objects;

public class Pipe implements Nameable {
    private String name;
    private String pronoun;

    public void setName(String name) {
        this.name = name;
    }

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }

    public String getPronoun() {
        return pronoun;
    }

    @Override
    public String getName() {
        return name;
    }

    public Pipe(String name, String pronoun) {
        setName(name);
        setPronoun(pronoun);
    }

    public Pipe(String name) {
        setName(name);
    }
    public static String fell(){
        return "упало";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pipe pipe = (Pipe) o;
        return Objects.equals(name, pipe.name) &&
                Objects.equals(pronoun, pipe.pronoun);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pronoun);
    }

    @Override
    public String toString() {
        return "Pipe{" +
                "name='" + name + '\'' +
                ", pronoun='" + pronoun + '\'' +
                '}';
    }
}
