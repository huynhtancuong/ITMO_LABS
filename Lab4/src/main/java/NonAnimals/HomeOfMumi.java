package NonAnimals;

import Interfaces.Nameable;

public class HomeOfMumi implements Nameable {
    private String name;
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
