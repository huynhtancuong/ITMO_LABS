package NonAnimals;

import Interfaces.Describable;
import Interfaces.Nameable;

public class Carpet implements Nameable, Describable {
    private String name;
    private String des;
    @Override
    public String getDescription() {
        return des;
    }

    @Override
    public void setDescription(String description) {
        this.des = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
