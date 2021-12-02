public class Main {

    public static void main(String [] args){

        String earsName = Th.EARS.getName();
        String hatName = Th.HAT.getName();
        String grassName = Th.GRASS.getName();
        String bootsName = Th.BOOTS.getName();
        String coatName = Th.COAT.getName();
        String butName = Th.BUT.getName();
        String now2Name = Th.NOW2.getName();
        String againName = Th.AGAIN.getName();
        String likeName = Th.LIKE.getName();

        AllImpl allImpl = new AllImpl("все");
        Face face = new Face();
        face.setName("лицо ");
        Garden gr = new Garden();
        gr.setName(" сад ");
        King king = new King("Короля Рубинов");
        MagicManImpl wiz = new MagicManImpl("Волшебник");
        NoBody nb = new NoBody(" никто ");
        Queen queen = new Queen("Королева Рубинов");
        Smile smile = new Smile();
        smile.setName(" улыбкой");

        allImpl.see();
        System.out.print(likeName);
        wiz.laugh();
        System.out.print(butName);
        nb.think();
        wiz.smile();
        System.out.println(". ");
        System.out.print(now2Name);
        System.out.print(face.getName()+wiz.getName()+"a");
        face.shineFace();
        System.out.print(smile.getName());
        System.out.println(". ");
        wiz.happy();
        allImpl.absSee();
        System.out.print(earsName+", "+ hatName+", и "+bootsName);
        System.out.println("! ");
        wiz.noWord();
        wiz.upHand();
        System.out.print(coatName);
        wiz.magic();
        System.out.print(gr.getName()+againName);
        gr.shine();
        System.out.println(". ");
        System.out.print(grassName);
        allImpl.inFrontOf();
        king.lay();
        king.bliz();
        System.out.print(king.getName()+" - "+queen.getName());
        System.out.println("... ");
    }
}