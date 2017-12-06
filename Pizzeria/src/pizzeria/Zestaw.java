/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;
import java.util.*;
/**
 * Szczególny przypadek zamówienia, zawiera 3 produkty ze specjalną zniżką.
 * @author Marcin
 */
public class Zestaw extends Produkt{
    private Produkt[] produkt = new Produkt[3];
    private float znizka = (float) 0.1;
    
    public Zestaw(String nazwa, Produkt p1, Produkt p2, Produkt p3){
        super(nazwa, 0, 0);
        produkt[0] = p1;
        produkt[1] = p2;
        produkt[2] = p3;
    }

    public void setZnizka(float znizka) {
        if(znizka>0 && znizka<1)
            this.znizka = znizka;
        else
            this.znizka = 1;
    }
    

    public float getZnizka() {
        return znizka;
    }
    

    @Override
    public float getCena() {
        float koszt = 0;
        for(Produkt p : produkt)
            koszt += p.getCena();
        koszt = koszt - znizka*koszt;
        return koszt;
    }
    
    @Override
    public int getCzasPrzygot() {
        int czas = 0;
        for(Produkt p : produkt)
            if(p.getCzasPrzygot() > czas)
            czas = p.getCzasPrzygot();
        return czas;
    }
    
}
