public class Flowers implements Nameable {
    private String name;

    public String bloom() {
        return "распускались ";
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
