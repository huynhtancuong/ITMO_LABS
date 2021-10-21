/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pokemons;
import Moves.*;
import ru.ifmo.se.pokemon.*;


/**
 *
 * @author huynh
 */
public class Grubbin extends Pokemon{
    public Grubbin(String name, int lvl) {
        super(name, lvl);
        setStats(47, 62, 45, 55, 45, 46);
        setType(Type.BUG);
        setMove(new Facade(), new Rest());
    }
}
