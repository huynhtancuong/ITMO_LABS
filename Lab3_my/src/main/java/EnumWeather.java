public enum EnumWeather implements Nameable{
    RAIN("дождем ");

    private String name;

    EnumWeather(String name) {
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
        return "EnumWeather{" +
                "name='" + name + '\'' +
                '}';
    }
}
