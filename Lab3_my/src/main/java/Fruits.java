public class Fruits implements Nameable{
    private String name;

    public String matured() {
        return "созревали ";
    }

    public String changeColor(EnumColor from1, EnumColor to1, EnumColor from2, EnumColor to2) {
        return "меняя цвет из " + from1.getName() + "в " + to1.getName() + ", из " + from2.getName() + "в " + to2.getName();
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
