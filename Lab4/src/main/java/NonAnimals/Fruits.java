package NonAnimals;

import Interfaces.Nameable;

import java.util.Objects;
import Enum.*;

public class Fruits implements Nameable {
    private String name;

    public String matured() {
        return "созревали ";
    }

    public String changeColor(EnumColor from1, EnumColor to1, EnumColor from2, EnumColor to2) {
        return "меняя цвет из " + from1.getName() + "в " + to1.getName() + ", из " + from2.getName() + "в " + to2.getName();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fruits that = (Fruits) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "name='" + name + '\'' +
                '}';
    }
}
