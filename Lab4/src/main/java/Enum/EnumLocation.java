package Enum;

import Interfaces.Nameable;

public enum EnumLocation implements Nameable {
    OUTDOOR("дворе ");

    private String name;

    EnumLocation(String name) {
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
        return "Enum.EnumLocation{" +
                "name='" + name + '\'' +
                '}';
    }
}
