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
public class Charjabug extends Grubbin{
    public Charjabug(String name, int lvl) {
        super(name, lvl);
        setStats(57, 82, 95, 55, 75, 36);
        addType(Type.ELECTRIC);
        addMove(new Scald());
    }
}
