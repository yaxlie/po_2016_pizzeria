/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

/**
 * Jest szybszy, ale ma mniejszą ładowność i pojemność baku.
 * @author Marcin
 */
public class Skuter extends Pojazd{
    public Skuter(){
        setPredkosc(10);
        setSpalanie((float) 0.15);
        setPojemoscBaku(10);
        setPaliwo(getPojemoscBaku());
        setLadownosc(3);
    }
    
}
