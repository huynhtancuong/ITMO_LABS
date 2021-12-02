public class MagicManImpl extends Human implements Wizard {
    public MagicManImpl(String name) {
        setName(name);
    }
    @Override
    public void laugh() {
        System.out.print(getName()+ " хохотал");
    }

    @Override
    public void smile() {
        System.out.print(getName()+ " может улыбаться");
    }

    @Override
    public void happy() {
        System.out.print(getName()+ " был так счастлив");
    }

    @Override
    public void noWord() {
        System.out.print("не сказав ни слова, ");
    }

    @Override
    public void upHand() {
        System.out.print(getName()+ " взмахнул ");
    }
    @Override
    public void magic() {
        System.out.print(", и --" +" вот нате! "+"--");
    }

}
