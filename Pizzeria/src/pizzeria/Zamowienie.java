/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Tworzone na życzenie Klienta.
 * @author Marcin
 */
public class Zamowienie extends Thread implements Serializable{
    public Zamowienie(Pizzeria pizzeria, Klient k){
        klient = k;
        polozenie = k.getPolozenie();
        this.pizzeria = pizzeria;
        produkt = new ArrayList<>();
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        Date now = new Date();
        data = (sdfTime.format(now));
        //this.pizzeria.getZamowienieL().add(this);
    }
    private Pizzeria pizzeria;
    private int godzinaDostawy;
    private Polozenie polozenie;
    private Klient klient;
    private boolean przygotowane = false;
    private boolean dostarczone = false;
    private ArrayList<Produkt> produkt;
    private float znizka = 0;
    private float bon = 0;
    private float minCenaDD = 50; //minimalna cena do darmowej dostawy
    private double maxOdlegloscDD = 100;
    private float cenaDostawy = 10;
    private String data;
    
    @Override
    public void run(){
        super.run();
        przygotuj();
    }
    
    public void dokonaj(){
        System.out.println(klient.getImie() + " składa zamówienie!");
        synchronized(pizzeria){
            pizzeria.getZamowienieL().add(this);
        }
        new Thread(this).start();
    }
    
    public float cena(){
        float koszt = 0;
        for(Produkt p : produkt)
        {
           koszt += p.getCena();
        }
        if(koszt < minCenaDD || Math.sqrt(Math.pow((double)klient.getPolozenie().getX() - (double)pizzeria.getPolozenie().getX(),2)
                + Math.pow((double)klient.getPolozenie().getX() - (double)pizzeria.getPolozenie().getX(),2)) > maxOdlegloscDD)
            koszt += cenaDostawy;
        koszt -= bon;
        if(koszt < 0)
            koszt =0;
        return koszt - koszt*znizka;
    }
    
    public void przygotuj(){
        
            System.out.println(this.data + " Zamówienie jest przygotowywane");
            int czasCzekania = 0;
            for(Produkt p : produkt){
                if(p.getCzasPrzygot() > czasCzekania)
                    czasCzekania = p.getCzasPrzygot();
            }
            try {
                Thread.sleep(1000*czasCzekania);
            }
            catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
        synchronized(pizzeria){
            System.out.println("Zamowienie gotowe!");
            pizzeria.getZamowieniePrzygotowane().add(this);
            przygotowane = true;
        }
        pizzeria.getMapa().repaint();
    }

    public int getGodzinaDostawy() {
        return godzinaDostawy;
    }

    public Polozenie getPolozenie() {
        return polozenie;
    }

    public boolean isDostarczone() {
        return dostarczone;
    }

    public void setGodzinaDostawy(int godzinaDostawy) {
        this.godzinaDostawy = godzinaDostawy;
    }

    public void setPolozenie(Polozenie polozenie) {
        this.polozenie = polozenie;
    }

    public void setDostarczone(boolean dostarczone) {
        this.dostarczone = dostarczone;
    }

    public ArrayList<Produkt> getProdukt() {
        return produkt;
    }
    
    public void dodaj(Produkt p){
        produkt.add(p);
    }

    public Klient getKlient() {
        return klient;
    }

    public void setZnizka(float znizka) {
        this.znizka = znizka;
    }

    public void setBon(float bon) {
        this.bon = bon;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public boolean isPrzygotowane() {
        return przygotowane;
    }
    
    
}
