/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moves;
import ru.ifmo.se.pokemon.*;
import Pokemons.Cubone;

/**
 *
 * @author huynh
 */
public class Rest extends StatusMove{
    public Rest() {
        super(Type.PSYCHIC, 0, 0);
    }
    @Override
    protected void applySelfEffects(Pokemon p) {
        Effect e1 = new Effect().turns(0).stat(Stat.HP, -99);
        Effect e2 = new Effect().turns(2).condition(Status.SLEEP);
        p.addEffect(e1);
        p.setCondition(e2);
    }
    
    @Override
    protected java.lang.String describe() {
        return "used Rest";
    }
}
