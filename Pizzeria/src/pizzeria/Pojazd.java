/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import java.io.Serializable;

/**
 * Klasa nadrzędna dla Skuter i Samochód.
 * @author Marcin
 */
public class Pojazd  implements Serializable{
    private int ladownosc;
    private int predkosc;  //pixele na sekundę
    private String rejestracja;
    private float pojemoscBaku;
    private float spalanie; //spalanie na sekundę
    private float paliwo;

    public float getSpalanie() {
        return spalanie;
    }

    public void setSpalanie(float spalanie) {
        this.spalanie = spalanie;
    }
    
    public void spalPaliwo(float ile){
        paliwo -= ile;
    }
    
    public void tankuj(){
        paliwo = pojemoscBaku;
    }
    public void tankuj(float ile){
        paliwo += ile;
    }

    public int getLadownosc() {
        return ladownosc;
    }

    public int getPredkosc() {
        return predkosc;
    }

    public String getRejestracja() {
        return rejestracja;
    }

    public float getPojemoscBaku() {
        return pojemoscBaku;
    }

    public float getPaliwo() {
        return paliwo;
    }

    public void setLadownosc(int ladownosc) {
        this.ladownosc = ladownosc;
    }

    public void setPredkosc(int predkosc) {
        this.predkosc = predkosc;
    }

    public void setRejestracja(String rejestracja) {
        this.rejestracja = rejestracja;
    }

    public void setPojemoscBaku(float pojemoscBaku) {
        this.pojemoscBaku = pojemoscBaku;
    }

    public void setPaliwo(float paliwo) {
        this.paliwo = paliwo;
    }
    
}
