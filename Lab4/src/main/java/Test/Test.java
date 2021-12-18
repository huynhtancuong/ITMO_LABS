package Test;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        Class t = Class.forName("Doors");
        System.out.println(t.getName());
        try {
            chia2so(2, 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void chia2so(int a, int b) throws Exception {
        try {
            System.out.println(a/b);
        } catch (Exception e) {
            throw new Exception("Loi chia cho so 0.");
        }
    }
}


class MyException extends Exception {
    private String error;

    public MyException(String error) {
        super("Fuck");
        this.error = error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

}
