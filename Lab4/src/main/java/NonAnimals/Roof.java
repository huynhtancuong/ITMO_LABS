package NonAnimals;

import Exceptions.WrongNameException;
import Interfaces.Nameable;

import java.util.Objects;

public class Roof implements Nameable {
    private String name;
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void isValid() throws WrongNameException {
        if (this.name.equals("Муми-дом")) System.out.print("");
        else throw new WrongNameException("The name of NonAnimals.HomeOfMumi's instance must be \"Муми-дом\"!");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roof that = (Roof) o;
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
