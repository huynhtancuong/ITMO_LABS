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
public class Spark extends PhysicalMove{
    public Spark() {
        super(Type.ELECTRIC, 65, 1.0);
    }
    
    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() < 0.3) Effect.paralyze(p);
    }
    @Override
    protected String describe() {
        return "used Spark";
    }
}
