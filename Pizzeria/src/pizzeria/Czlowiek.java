/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * Człowiek, to klasa nadrzędna dla Klienta i Dostawcy.
 * @author Marcin
 */
public class Czlowiek extends Thread implements Serializable{
    private String imie;
    private String nazwisko;
    private String pesel;
    private Polozenie polozenie;
    private Pizzeria pizzeria;
    private ArrayList<Zamowienie> zamowienieL;
    private boolean zyj = true;
    
    public Czlowiek(String imie, String nazwisko, int x, int y, Pizzeria pizzeria ){
        if(x > 300)
            x = 300;
        if(y > 300)
            y = 300;
        if(x < 5)
            x = 5;
        if(y < 5)
            y = 5;
        polozenie = new Polozenie(x,y);
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pizzeria=pizzeria;
        new Thread(this).start();
        zamowienieL = new ArrayList<Zamowienie>();  
        pizzeria.getMapa().repaint();
    }
    public void run() { 
        System.out.println("Powstał nowy czlowiek! " + this.getImie() + " " + this.getNazwisko());
        //mapa.repaint();
        //rysuj();
    }
    public void przemiesc(){}
    
    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public Polozenie getPolozenie() {
        return polozenie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setPolozenie(Polozenie polozenie) {
        this.polozenie = polozenie;
    }

    public void setPizzeria(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }

    public void setZamowienieL(ArrayList<Zamowienie> zamowienieL) {
        this.zamowienieL = zamowienieL;
    }
    
    public void dodajZamowienie(Zamowienie zamowienie){
        zamowienieL.add(zamowienie);
    }
    
    public Pizzeria getPizzeria() {
        return pizzeria;
    }

    public ArrayList<Zamowienie> getZamowienieL() {
        return zamowienieL;
    }

    public boolean isZyj() {
        return zyj;
    }

    public void setZyj(boolean zyj) {
        this.zyj = zyj;
    }
    
}
    
