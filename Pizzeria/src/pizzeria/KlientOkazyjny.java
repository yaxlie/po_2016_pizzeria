/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;


/**
 * Podstawowy Klient.
 * @author Marcin
 */
public class KlientOkazyjny extends Klient{
    public KlientOkazyjny(String imie, String nazwisko, int x, int y, Pizzeria pizzeria){
        super(imie, nazwisko, x, y, pizzeria);
    }
    
}
