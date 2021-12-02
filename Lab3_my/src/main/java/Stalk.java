public class Stalk implements Nameable, Pronounceable {
    private String name;
    private String pronoun;

    public String wasElastic() {
        return "был упругий ";
    }
    public String likeRubber() {
        return "словно резиновый ";
    }
    public String wasNotPulled() {
        return "не вытаскивался ";
    }
    public String wrappedAround() {
        return "обвился вокруг ";
    }
    public String tookOff() {
        return "снял ";
    }
    public String chance() {
        return "Как бы невзначай ";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPronoun() {
        return pronoun;
    }

    @Override
    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }
}
