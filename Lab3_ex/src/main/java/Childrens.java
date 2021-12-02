import java.util.Objects;

public class Childrens extends Characters {
    private String name;
    private String description;
    private String pronoun;

    public String getPronoun() {
        return pronoun;
    }

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }

    public Childrens(String name) {
        setName(name);
    }

    public Childrens(String name, String description, String pronoun) {
        setName(name);
        setDescription(description);
        setPronoun(pronoun);
    }
    public static String climb(Location s){
        return "вскарабкались "+s.getPronoun()+' '+s.getLocacia();
    }
    public static String pickUp(){
        return "подобрать";
    }
    public static String want(){
        return "хотел";
    }
    public static String handOver(){
        return "вручить";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Childrens childrens = (Childrens) o;
        return Objects.equals(name, childrens.name) &&
                Objects.equals(description, childrens.description) &&
                Objects.equals(pronoun, childrens.pronoun);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, pronoun);
    }

    @Override
    public String toString() {
        return "Childrens{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pronoun='" + pronoun + '\'' +
                '}';
    }
}
