package NonAnimals;

import Exceptions.WrongInstanceException;
import Interfaces.Nameable;

import java.util.Objects;

public class Vines implements Nameable {
    private String name;

    public String sprouted(Object obj) throws WrongInstanceException {
        if (obj instanceof Chimney) {
            return "проросли сквозь " + ((Chimney) obj).getName();
        } else throw new WrongInstanceException("This object is not a chimney.");
    }

    public String braided(Object obj) throws WrongInstanceException {
        if (obj instanceof Roof) {
            return "оплели " + ((Roof) obj).getName();
        } else throw new WrongInstanceException("This object is not a NonAnimals.Roof.");
    }

    public String enveloped(Object obj) throws WrongInstanceException{
        return "";
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
        Vines that = (Vines) o;
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
