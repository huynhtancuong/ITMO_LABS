public class AllImpl extends Human implements All {
    public AllImpl(String name) {
        setName(name);
    }

    @Override
    public void see() {
        System.out.print(getName()+Th.NOW.getName()+ "видели");
    }

    @Override
    public void inFrontOf() {
        System.out.print("перед " +getName()+"ми ");
    }

    @Override
    public void absSee() {
        System.out.print(", что это было сразу видно по его ");
    }

}