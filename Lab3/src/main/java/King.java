public class King extends Human {
    public King(String name) {
        setName(name);
    }

    //@Override
    protected void lay() {
        System.out.print("лежал ");
    }
    protected void bliz() {
        System.out.print("близнец ");
    }
}