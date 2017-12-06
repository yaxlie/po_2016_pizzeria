/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

/**
 * Ma większą ładowność i pojemnośc baku, ale jest wolniejszy.
 * @author Marcin
 */
public class Samochod extends Pojazd{
    public Samochod(){
        setPredkosc(5);
        setSpalanie((float) 0.3);
        setPojemoscBaku(35);
        setPaliwo(getPojemoscBaku());
        setLadownosc(100);
    }
    
}
