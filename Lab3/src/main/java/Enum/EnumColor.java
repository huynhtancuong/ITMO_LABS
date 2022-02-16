package Enum;

import Interfaces.Nameable;

public enum EnumColor implements Nameable {
    GREEN("зеленого "),
    YELLOW1("желтый "),
    YELLOW2("желтого "),
    RED("красный ");


    private String name;

    EnumColor(String name) {
        this.name = name;
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
    public String toString() {
        return "Enum.EnumColor{" +
                "name='" + name + '\'' +
                '}';
    }
}
