package NonAnimals;

import Exceptions.WrongNameException;
import Interfaces.Nameable;

public class HomeOfMumi implements Nameable {
    protected String name;
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
        else throw new WrongNameException("The name of HomeOfMumi's instance must be \"Муми-дом\"!");
    }
}
