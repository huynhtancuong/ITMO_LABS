import java.util.Objects;

public class Poster implements Nameable {
    private String name;
    private String poster;
    private String pronoun;

    public String getPronoun() {
        return pronoun;
    }

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Poster(String poster) {
        setPoster(poster);
    }

    public Poster(String name, String poster,String pronoun) {
        setName(name);
        setPoster(poster);
        setPronoun(pronoun);
    }

    public void setName(String name) {
        this.name = name;
    }


    public static String planned() {
        return "спланировала";
    }

    public static String whirl(){
        return "продолжали кружиться";
    }

    public static String stick(){
        return "прилипла";
    }
    public static String was(){
        return "была";
    }
    public static String turnedInto(){
        return "тотчас превратилась";
    }
    public static String cutOnShards(){
        return "в двадцать четыре клочка";
    }
    public static String burn(){
        return "сгорело";
    }
    public static String stay(){
        return "а,все,что осталось от нее,";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poster poster1 = (Poster) o;
        return Objects.equals(name, poster1.name) &&
                Objects.equals(poster, poster1.poster) &&
                Objects.equals(pronoun, poster1.pronoun);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, poster, pronoun);
    }

    @Override
    public String toString() {
        return "Poster{" +
                "name='" + name + '\'' +
                ", poster='" + poster + '\'' +
                ", pronoun='" + pronoun + '\'' +
                '}';
    }

}
