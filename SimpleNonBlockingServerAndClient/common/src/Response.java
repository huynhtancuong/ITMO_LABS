public class Response {
    public String string;

    public Response (String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return "Response{" +
                "string='" + string + '\'' +
                '}';
    }
}
