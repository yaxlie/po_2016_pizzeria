/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;
import java.io.Serializable;
import java.util.*;
/**
 * Wykorzystywany do zamówień.
 * @author Marcin
 */
public class Produkt  implements Serializable{
    public enum Porcja{Duża, Średnia, Mała};
    private Porcja porcja = Porcja.Średnia;
    private String nazwa;
    private String skladniki;
    private float cena;
    private int czasPrzygot;
    private boolean porcjowany = false;
    
    public Produkt(String nazwa, float cena, int czas){
        this.nazwa = nazwa;
        this.czasPrzygot = czas;
        this.cena = cena;
    }
     public Produkt(String nazwa, float cena, int czas, String skladniki, boolean porcjowany){
         if(czas > 1000 || czas < 0 )
             czas = 10;
         if(cena > 10000 || cena < 0 )
             cena = 10;
        this.nazwa = nazwa;
        this.czasPrzygot = czas;
        this.cena = cena;
        this.skladniki = skladniki;
        this.porcjowany = porcjowany;
    }

    public String getNazwa() {
        return nazwa;
    }

    public float getCena() {
        return cena;
    }

    public String getSkladniki() {
        return skladniki;
    }

    public int getCzasPrzygot() {
        return czasPrzygot;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public void setSkladniki(String skladniki) {
        this.skladniki = skladniki;
    }

    public void setCzasPrzygot(int czasPrzygot) {
        this.czasPrzygot = czasPrzygot;
    }

    public Porcja getPorcja() {
        return porcja;
    }

    public void setPorcja(Porcja porcja) {
        this.porcja = porcja;
    }

    public void setPorcjowany(boolean porcjowany) {
        this.porcjowany = porcjowany;
    }

    public boolean isPorcjowany() {
        return porcjowany;
    }
   
}
