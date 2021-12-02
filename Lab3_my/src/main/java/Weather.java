import java.util.Objects;

public class Weather implements Pronounceable{
    protected String weather;
    protected String pronoun;

    public Weather(){
        this("", "");
    }
    public Weather(String weather) {
        this.weather = weather;
        this.pronoun = "";
    }
    public Weather(String weather, String pronoun) {
        this(weather);
        this.pronoun = pronoun;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather() {
        this.weather =  weather;
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
        Weather loc = (Weather) o;
        return Objects.equals(weather, loc.weather) &&
                Objects.equals(pronoun, loc.pronoun);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weather, pronoun);
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "weather='" + weather + '\'' +
                ", pronoun='" + pronoun + '\'' +
                '}';
    }
}
