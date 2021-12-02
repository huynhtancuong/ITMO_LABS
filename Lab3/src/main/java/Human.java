import java.util.Objects;

public abstract class Human {
    private String Name;
    public void setName(String name) {
        this.Name = name;
    }
    public String getName() {
        return this.Name;
    }
    protected void think() {
    }
    protected void lay(){
    }
    protected void bliz() {

    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != this.getClass())
            return false;
        if(this == obj)
            return true;
        Human other = (Human) obj;
        return Objects.equals(Name, other.Name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(Name);
    }
    @Override
    public String toString() {
        return this.Name;
    }
}


