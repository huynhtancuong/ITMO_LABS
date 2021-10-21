/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moves;

import ru.ifmo.se.pokemon.*;
/**
 *
 * @author huynh
 */
public class Thunderbolt extends SpecialMove {
    public Thunderbolt() {
        super(Type.ELECTRIC, 90, 1.0);
    }
    
   @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.1) Effect.paralyze(p);
    }
    @Override
    protected String describe() {
        return "used Thunderbolt";
    }
}
