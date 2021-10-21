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
public class Marowak extends Cubone{
    public Marowak(String name, int lvl) {
        super(name, lvl);
        setStats(60, 80, 110, 50, 80, 45);
        addMove(new Waterfall());
    }
}
