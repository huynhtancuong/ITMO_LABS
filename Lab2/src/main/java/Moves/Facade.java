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
public class Facade extends PhysicalMove {
    public Facade() {
        super(Type.NORMAL, 70, 1.0);
    }
    @Override
    protected void applyOppDamage(Pokemon p, double damage) {
        if(p.getCondition()==Status.BURN || p.getCondition()==Status.POISON || p.getCondition()==Status.PARALYZE) {
            p.setMod(Stat.HP, (int)damage*2);
        }
    }
    @Override
    protected String describe() {
        return "used Facade";
    }
}
