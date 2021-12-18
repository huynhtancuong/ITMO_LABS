package Story;

import Interfaces.Describable;
import Interfaces.Nameable;

import java.util.Objects;
import NonAnimals.*;
import Exceptions.*;
import Characters.*;
import Enum.*;


public class Main {
    //non-static inner class
    class Doors implements Nameable {
        private String name;

        public String dontExist() {
            return "Ни ";
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Doors that = (Doors) o;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return this.getClass() + "{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    //static inner class
    static class Carpet implements Nameable, Describable {
        private String name;
        private String description;
        @Override
        public String getDescription() {
            return description;
        }

        @Override
        public void setDescription(String description) {
            this.description = description;
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Carpet that = (Carpet) o;
            return Objects.equals(name, that.name) &&
                    Objects.equals(description, that.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, description);
        }

        @Override
        public String toString() {
            return  this.getClass() + "{" +
                    "name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {

        Vines vines = new Vines(){ //this is an anonymous class
            public String enveloped(Object obj) throws WrongInstanceException {
                if (obj instanceof HomeOfMumi) {
                    return "окутали весь " + ((HomeOfMumi) obj).getName();
                } else throw new WrongInstanceException("The object is not a NonAnimals.Roof.");
            }
        };
        vines.setName("Лианы ");
        Chimney chimney = new Chimney();
        chimney.setName("печную трубу ");
        Roof roof = new Roof();
        roof.setName("крышу ");
        System.out.print(vines.getName());
        HomeOfMumi homeOfMumi = new HomeOfMumi();
        homeOfMumi.setName("Муми-дом");
        try {   //try-catch
            homeOfMumi.isValid();
        } catch (WrongNameException e) {
            System.out.println(e.getMessage());
        }
        Main.Carpet carpet = new Main.Carpet();
        carpet.setName("ковром ");
        carpet.setDescription("пышным зеленым ");
        try{
            System.out.print(vines.sprouted(chimney));
        } catch (WrongInstanceException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.print(", " + vines.braided(roof));
        } catch (WrongInstanceException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.print("и " + vines.enveloped(homeOfMumi));
        } catch (WrongInstanceException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(carpet.getDescription() + carpet.getName());





        Location outdoor = new OutDoor(EnumLocation.OUTDOOR.getName(), "A на ");
        Weather rain = new Rain(EnumWeather.RAIN.getName(), "под ");
        Mumitroll mumi = new Mumitroll("Муми-тролль ");
        Hill hill = new Hill();
        hill.setName("холм ");
        hill.setDescription("высокий зеленый ");
        hill.setPronoun("на котором прямо ");
        System.out.println( outdoor.getPronoun() + outdoor.getLocation() + rain.getPronoun() + rain.getWeather()
                            + mumi.getName() + mumi.stand() + "и " + mumi.look_around_in_surprise()
                            + hill.getDescription() + hill.getName() + ", "+ hill.getPronoun()
                            );

        Eye eye = new Eye();
        eye.setName("глозах ");
        eye.setPronoun("на ");
        Flowers flowers = new Flowers();
        flowers.setName("цветы ");
        Fruits fruits = new Fruits();
        fruits.setName("плоды ");
        EnumColor green = EnumColor.GREEN;
        EnumColor yellow1 = EnumColor.YELLOW1;
        EnumColor yellow2 = EnumColor.YELLOW2;
        EnumColor red = EnumColor.RED;

        System.out.println( eye.getPronoun() + eye.getName() + flowers.bloom() + flowers.getName() + "и "
                            + fruits.matured() + fruits.getName() + "и " + fruits.changeColor(green, yellow1, yellow2, red)
                            + ". ");

        Snutsmumrik snut = new Snutsmumrik("Снусмумрик ");
        System.out.println( snut.getName() + snut.stepped_forward() + "и " + snut.began_to_examine_with_interest() + hill.getName() + ".");

        Windows windows = new Windows();
        windows.setName("окон ");

        Main main_ = new Main();    //uses of non static inner class
        Main.Doors doors = main_.new Doors();
        doors.setName("дверей ");
        System.out.println(windows.dontExist() + windows.getName() + ", " + doors.dontExist() + doors.getName() + ".");

        CarpetOfWildVegetation carpetOfWildVegetation = new CarpetOfWildVegetation();
        carpetOfWildVegetation.setName("ковер дикой растительности ");
        carpetOfWildVegetation.setDescription("Сплошной ");
        System.out.println(carpetOfWildVegetation.getDescription() + carpetOfWildVegetation.getName() + ".");

        Stalk stalk = new Stalk();
        stalk.setName("стебель ");
        stalk.setPronoun("за какой-то ");
        System.out.println(snut.getName() + snut.grabbed() + stalk.getPronoun() + stalk.getName() + "и " + snut.pulled() + ".");

        stalk.setName("Стебель ");
        Earth earth = new Earth();
        earth.setName("земли ");
        earth.setPronoun("из ");
        System.out.println(stalk.getName() + stalk.wasElastic() + ", " + stalk.likeRubber() + ", и " + stalk.wasNotPulled() + earth.getPronoun() + earth.getName() + "!");

        stalk.setName("он ");
        HatOfSnutsmumrik hatOfSnutsmumrik = new HatOfSnutsmumrik();
        hatOfSnutsmumrik.setName("шляпы Снусмумрика ");
        System.out.println(stalk.chance() + stalk.wrappedAround() + hatOfSnutsmumrik.getName() + stalk.tookOff() + hatOfSnutsmumrik.getName());

    }

}
