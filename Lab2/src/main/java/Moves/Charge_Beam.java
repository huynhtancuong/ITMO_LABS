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
public class Charge_Beam extends SpecialMove {
    int raised_stats = 0;
    public Charge_Beam() {
        super(Type.ELECTRIC, 50, 0.9);
    }
    @Override
    protected void applySelfEffects(Pokemon p) {
        if (Math.random() <= 0.7) {
            if (raised_stats < 6) {
                p.setMod(Stat.ATTACK, +1);
                raised_stats+=2;
            }
        }
    }
    @Override
    protected String describe() {
        return "used Charge Beam";
    }
}
