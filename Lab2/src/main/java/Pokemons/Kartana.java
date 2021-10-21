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
public class Kartana extends Pokemon{
    public Kartana(String name, int lvl) {
        super(name, lvl);
        setStats(59, 181, 131, 59, 31, 109);
        setType(Type.STEEL, Type.GRASS);
        setMove(new Thunderbolt(), new Ice_Fang(), new Spark(), new Swagger());
    }
}
