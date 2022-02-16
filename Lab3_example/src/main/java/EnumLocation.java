public enum EnumLocation implements Nameable{
    FOREST("лесом"),
    HOME("дома"),
    GLADE("лесную полянку"),
    ROOF1("крыше"),
    ROOF2("крышу");

    private String name;

    EnumLocation(String nameLocation) {
        name=nameLocation;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "EnumLocation{" +
                "name='" + name + '\'' +
                '}';
    }
}
