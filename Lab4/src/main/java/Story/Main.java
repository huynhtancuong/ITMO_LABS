package Story;

import Characters.Mumitroll;
import Characters.Snutsmumrik;
import Enum.*;
import Exceptions.WrongNameException;
import Exceptions.WrongInstanceException;
import NonAnimals.*;

public class Main {
    public static void main(String[] args) {

        Vines vines = new Vines(){ //this is an anonymous class
            public String enveloped(Object obj) throws WrongInstanceException {
                if (obj instanceof HomeOfMumi) {
                    return "окутали весь " + ((HomeOfMumi) obj).getName();
                } else throw new WrongInstanceException("The object is not a Roof.");
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
        try {
            homeOfMumi.isValid();
        } catch (WrongNameException e) {
            System.out.println(e.getMessage());
        }
        Carpet carpet = new Carpet();
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
        Doors doors = new Doors();
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
