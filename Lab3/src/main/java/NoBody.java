public class NoBody extends Human {
    public NoBody(String name) {
        setName(name);
    }

    //@Override
    public void think() {

        System.out.print(getName()+"не мог подумать, что ");
    }
}
// Book b1 = new Book("Java. Complete Referense.", "H. Shildt");
//        b1.print();