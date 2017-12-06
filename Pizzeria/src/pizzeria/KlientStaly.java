/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

/**
 * Ma różne zniżki i udogodnienia.
 * @author Marcin
 */
public class KlientStaly extends Klient{
    public KlientStaly(String imie, String nazwisko, int x, int y, Pizzeria pizzeria){
        super(imie, nazwisko, x, y, pizzeria);
    }
    private float pLojal = 0;
    private float pLojalLimit = 500;
    private float stalaZnizka = (float) 0.2;
    private float bon=0;
    private float wartoscBonu = 100; //na jaka wartosc ma sie ustawic po zdobyciu punktow
    
    public void wydajPunkty(int ile){}
    public void dodajPunkty(int ile){}

    public float getpLojal() {
        return pLojal;
    }

    public float getStalaZnizka() {
        return stalaZnizka;
    }

    public void setpLojal(int pLojal) {
        this.pLojal = pLojal;
    }

    public void setStalaZnizka(float stalaZnizka) {
        this.stalaZnizka = stalaZnizka;
    }
    
    /**
     * Klient firmowy dodatkowo zdobywa punkty przy zamówieniu.
     * 
     */
    @Override
    public void zamow(Zamowienie z){
        z.setZnizka(stalaZnizka);
        pLojal += z.cena();
        z.setBon(bon);
        super.zamow(z);
        if(pLojal >= pLojalLimit){
            bon = wartoscBonu;
            pLojal = 0;
        }
        else
            bon = 0;
    }
    
}
