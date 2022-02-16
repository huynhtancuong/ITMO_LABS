package NonAnimals;

import java.util.Objects;

public class Rain extends Weather {
    public Rain() {
        super();
    }
    public Rain(String weather) {
        super(weather);
    }
    public Rain(String weather, String pronoun) {
        super(weather, pronoun);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rain rain = (Rain) o;
        return Objects.equals(weather, rain.weather) &&
                Objects.equals(pronoun, rain.pronoun);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weather, pronoun);
    }
}
