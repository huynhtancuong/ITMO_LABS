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
public class Ice_Fang extends PhysicalMove{
    public Ice_Fang() {
        super(Type.ICE, 65, 0.95);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() < 0.1) Effect.freeze(p);
        if (Math.random() < 0.1) Effect.flinch(p);
    }
    @Override
    protected String describe() {
        return "used Ice Fang";
    }
}
