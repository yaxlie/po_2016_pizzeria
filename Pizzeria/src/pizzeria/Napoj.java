/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

/**
 * Typ jedzenia restauracji.
 * @author Marcin
 */
public class Napoj extends Produkt{

    public Napoj(String nazwa, float cena, int czas) {
        super(nazwa, cena, czas);
    }
    public Napoj(String nazwa, float cena, int czas, String skladniki, boolean porcjowany) {
        super(nazwa, cena, czas, skladniki, porcjowany);
    }
    
}
