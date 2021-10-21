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
public class Shadow_Claw extends PhysicalMove {
    public Shadow_Claw() {
        super(Type.GHOST, 70, 1.0);
    }
    @Override
    protected String describe() {
        return "used Shadow Claw";
    }
}
