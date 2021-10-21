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
public class Vikavolt extends Charjabug {
    public Vikavolt(String name, int lvl) {
        super(name, lvl);
        setStats(77, 70, 90, 145, 75, 43);
        addMove(new Charge_Beam());
    }
}
