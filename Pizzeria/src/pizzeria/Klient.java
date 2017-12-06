/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *  Człowiek, który zamawia Produkty, Osobny wątek.
 * @author Marcin
 */
public class Klient extends Czlowiek{
    public Klient(String imie, String nazwisko, int x, int y, Pizzeria pizzeria){
        super(imie, nazwisko, x, y, pizzeria);
    }
    
    private String telefon;
    private String adres;
    private String mail;
    
    @Override
    public void run() {
        super.run();
        while(true)
        {
            try {
                int rand = ThreadLocalRandom.current().nextInt(5000, 100000 + 1);
                Thread.sleep(rand);
            }
            catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            if(!isZyj())
                break;
            this.zamow(stworzZamowienie());
        }
    }
        /**
         * Dokonuje zamówienia.
         * 
         */
    public void zamow(Zamowienie z){
        getZamowienieL().add(z);
        z.dokonaj();
    }

    public String getTelefon() {
        return telefon;
    }

    public String getAdres() {
        return adres;
    }

    public String getMail() {
        return mail;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public Zamowienie stworzZamowienie(){
       // if(getPizzeria().getMenu().isEmpty()) throw...
            
        Zamowienie z = new Zamowienie(getPizzeria(), this);
        int rand = ThreadLocalRandom.current().nextInt(1, 10);
        
        for(int i = 0; i < rand; i++){
            Random generator = new Random();
            Object[] values = getPizzeria().getMenu().values().toArray();
            Object randomValue = values[generator.nextInt(values.length)];
            z.getProdukt().add((Produkt) randomValue);
        }
        return z;
    }
    
}
