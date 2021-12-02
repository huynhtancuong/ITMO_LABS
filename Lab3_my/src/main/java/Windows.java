public class Windows implements Nameable{
    private String name;

    public String dontExist() {
        return "Ни ";
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
