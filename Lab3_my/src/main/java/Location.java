import java.util.Objects;

public class Location implements Pronounceable{
    protected String location;
    protected String pronoun;

    public Location(){
        this("", "");
    }
    public Location(String location) {
        this.location = location;
        this.pronoun = "";
    }
    public Location(String location, String pronoun) {
        this(location);
        this.pronoun = pronoun;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation() {
        this.location =  location;
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
        Location loc = (Location) o;
        return Objects.equals(location, loc.location) &&
                Objects.equals(pronoun, loc.pronoun);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, pronoun);
    }

    @Override
    public String toString() {
        return this.getClass()+ "{" +
                "location='" + location + '\'' +
                ", pronoun='" + pronoun + '\'' +
                '}';
    }
}
