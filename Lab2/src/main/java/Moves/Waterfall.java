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
public class Waterfall extends PhysicalMove {
    public Waterfall() {
        super(Type.WATER, 80, 1.0);
    }
    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.2) Effect.flinch(p);
    }
    @Override
    protected String describe() {
        return "used Waterfall";
    }
}
