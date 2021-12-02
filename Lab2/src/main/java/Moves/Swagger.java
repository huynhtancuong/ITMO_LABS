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
public class Swagger extends StatusMove{
    int raised_stats = 0;
    public Swagger() {
        super(Type.NORMAL, 0, 0.85);
    }
    @Override
    protected void applySelfEffects(Pokemon p) {
        Effect.confuse(p);
        if (raised_stats < 6) {
            p.setMod(Stat.ATTACK, +2);
            raised_stats +=2;
        }
        
    }
    @Override
    protected String describe() {
        return "used Swagger";
    }
}
