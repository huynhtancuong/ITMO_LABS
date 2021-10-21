/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ru.ifmo.se.pokemon.*;
import Pokemons.*;

/**
 *
 * @author huynh
 */
public class Battleground {
    
    public static void main(String[] args) {
	Battle field = new Battle();
        Pokemon p1 = new Kartana("Kartana", 1);
        Pokemon p2 = new Cubone("Cubone", 1);
        Pokemon p3 = new Marowak("Marowak", 1);
        Pokemon p4 = new Grubbin("Grubbin", 1);
        Pokemon p5 = new Charjabug("Charjabug", 1);
        Pokemon p6 = new Vikavolt("Vikavolt", 1);
        field.addAlly(p1);
        field.addAlly(p2);
        field.addAlly(p3);
        field.addFoe(p4);
        field.addFoe(p5);
        field.addFoe(p6);
        field.go();
    }
}
