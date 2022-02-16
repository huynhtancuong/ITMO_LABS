public class Main {
    public static void main(String[] args) {

        Hemuliha hem = new Hemuliha("Маленькая", "Хемулиха");
        Guests guest =new Guests("гостей");
        Tea tea=new Tea();
        Location home=new Home(EnumLocation.HOME.getName());
        home.setPronoun("у себя");
        System.out.println(hem.getDescription() + ' ' + hem.getName() + ' ' + Hemuliha.treated()+' '+guest.getName()+' ' +tea.getTEA()+' '+home.getPronoun()+' '+home.getLocacia()+',');

        Poster poster=new Poster("афиши");
        poster.setName("театральные");
        Location forest=new Forest(EnumLocation.FOREST.getName());
        forest.setPronoun("над");
        System.out.println(poster.getName()+' '+poster.getPoster()+' '+Poster.whirl()+' '+ forest.getPronoun()+' '+forest.getLocacia()+'.');

        poster.setName("Одна из них");
        Location glade=new Glade(EnumLocation.GLADE.getName());
        glade.setPronoun("на");
        Location roof=new Roof(EnumLocation.ROOF1.getName());
        roof.setPronoun("к");
        System.out.println(poster.getName()+' '+Poster.planned()+' '+glade.getPronoun()+' '+glade.getLocacia()+" и "+Poster.stick()+' '+roof.getPronoun()+' '+roof.getLocacia()+',');

        roof.setPronoun("которую только что");
        System.out.println(roof.getPronoun()+' '+Roof.stick()+'.');

        Childrens child=new Childrens("малыша");
        child.setDescription("двадцать четыре");
        child.setPronoun("В тот же миг");
        roof.setPronoun("на");
        roof.setLocacia(EnumLocation.ROOF2.getName());
        System.out.println(child.getPronoun()+' '+child.getDescription()+' '+child.getName()+' '+Childrens.climb(roof));

        poster.setName("листок");
        child.setPronoun("чтобы");
        System.out.println(child.getPronoun()+' '+Childrens.pickUp()+' '+poster.getName()+'.');

        Childrens child2=new Childrens("Каждый из них");
        child2.setDescription("первым");
        Snusmurik snus=new Snusmurik("Снусмумрику");
        snus.setDescription("его");
        System.out.println(child2.getName()+' '+Childrens.want()+' '+child2.getDescription()+' '+Childrens.handOver()+' '+snus.getDescription()+' '+snus.getName()+'.');

        poster.setPoster("афиша");
        poster.setPronoun("А поскольку");
        Poster paper=new Poster("бумаги");
        paper.setName("тонкой");
        paper.setPronoun("из");
        System.out.println(poster.getPronoun()+' '+poster.getPoster()+' '+Poster.was()+' '+paper.getPronoun()+' '+paper.getName()+' '+paper.getPoster()+',');

        poster.setName("она");
        System.out.println(poster.getName()+' '+Poster.turnedInto()+' '+Poster.cutOnShards());

        Pipe pipe=new Pipe("трубу");
        pipe.setPronoun("в");
        System.out.println('('+Poster.stay()+' '+Pipe.fell()+" и "+Poster.burn()+')');
        System.out.println();


    }
}
