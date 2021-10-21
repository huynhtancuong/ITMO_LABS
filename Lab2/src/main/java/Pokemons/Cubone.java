/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pokemons;
import ru.ifmo.se.pokemon.*;
import Moves.*;

/**
 *
 * @author huynh
 */
public class Cubone extends Pokemon{
     public Cubone(String name, int lvl) {
        super(name, lvl);
        setStats(50, 50, 95, 40, 50, 35);
        setType(Type.GROUND);
        setMove(new Rest(), new Swagger(), new Shadow_Claw());
    }
}
