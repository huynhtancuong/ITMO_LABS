import java.util.Objects;

public class Tea implements Nameable {
    private String name;
    private final String TEA = "чаем";

    public String getTEA() {
        return TEA;
    }

    public Tea() {
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tea(String name) {
        setName(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tea tea = (Tea) o;
        return Objects.equals(name, tea.name) &&
                Objects.equals(TEA, tea.TEA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, TEA);
    }

    @Override
    public String toString() {
        return "Tea{" +
                "name='" + name + '\'' +
                ", TEA='" + TEA + '\'' +
                '}';
    }
}
