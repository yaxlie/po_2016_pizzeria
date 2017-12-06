/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 * Główna klasa projektu, zawiera informacje o restauracji.
 * @author Marcin
 */
public class Pizzeria extends Thread implements Serializable{
    public Pizzeria(){
        for (boolean[] row: dostepneWspolrz) 
        Arrays.fill(row, true);
    }
    
    private HashMap<String,Klient> klientL = new HashMap<>();
    private HashMap<String,Dostawca> dostawcaL = new HashMap<>();
    private volatile ArrayList<Zamowienie> zamowienieL = new ArrayList<>();
    //private final HashMap<String, Produkt> menu = new HashMap<>();
    private HashMap<String,Produkt> menu = new HashMap<>();
    private volatile ArrayList<Zamowienie> zamowieniePrzygotowane = new ArrayList<Zamowienie>();
    private transient Mapa mapa;
    private volatile boolean[][] dostepneWspolrz = new boolean[320][320];
    private Polozenie polozenie;
    private volatile float kasa = 0;
    public MMonitor gandalf = new MMonitor();
    //public MMonitor gandalf2 = new MMonitor();
    private transient PizzeriaJFrame pizzeriaJF;
    private int licznikLudzi = 0;

    public void setKasa(float kasa) {
        this.kasa = kasa;
    }

    public float getKasa() {
        return kasa;
    }

    public HashMap<String, Produkt> getMenu() {
        return menu;
    }

    public PizzeriaJFrame getPizzeriaJF() {
        return pizzeriaJF;
    }

    public void setPizzeriaJF(PizzeriaJFrame pizzeriaJF) {
        this.pizzeriaJF = pizzeriaJF;
    }

    public int getLicznikLudzi() {
        return licznikLudzi;
    }
    
    

    public Polozenie getPolozenie() {
        return polozenie;
    }

    public void setPolozenie(Polozenie polozenie) {
        this.polozenie = polozenie;
    }
    
   
    public void setDostepneWspolrz(int x, int y, boolean b) {
        synchronized(gandalf){
            for(int i = -3; i<4; i++){
                this.dostepneWspolrz[x][y+i] = b;
                dostepneWspolrz[polozenie.getX()][polozenie.getY()+i] = true;

                for(int j = -3; j<4; j++){
                    this.dostepneWspolrz[x+j][y] = b;
                    dostepneWspolrz[polozenie.getX()+j][polozenie.getY()] = true;
                }
            }
            //this.dostepneWspolrz[x][y] = b;
        }
    }

    public boolean[][] getDostepneWspolrz() {
        return dostepneWspolrz;
    }
    
    public void dodajKlienta(String rodzaj, String imie, String nazwisko, int x, int y, String tel,
    String mail) {
        Klient k;
        if("Stały".equals(rodzaj))
            k = new KlientStaly(imie, nazwisko, x, y, this);
        else if("Okazyjny".equals(rodzaj))
            k = new KlientOkazyjny(imie, nazwisko, x, y, this);
        else 
            k = new KlientFirmowy(imie, nazwisko, x, y, this);
        k.setTelefon(tel);
        k.setMail(mail);
        klientL.put(k.getImie() + " " + k.getNazwisko(),k);
        licznikLudzi++;
    }
    public void dodajKlienta(String imie, String nazwisko, int x, int y, String tel,
    String mail,String regon, String akor, String konto) {
        KlientFirmowy k;
        k = new KlientFirmowy(imie, nazwisko, x, y, this);
        k.setTelefon(tel);
        k.setMail(mail);
        k.setRegon(regon);
        k.setAdresKoresp(akor);
        k.setNrKontaBank(konto);
        klientL.put(k.getImie() + " " + k.getNazwisko(),k);
        licznikLudzi++;
    }
    public void dodajKlienta(String imie, String nazwisko, int x, int y) {
        
        Klient k = new KlientOkazyjny(imie, nazwisko, x, y, this);
        klientL.put(k.getImie() + " " + k.getNazwisko(),k);
        licznikLudzi++;
    }
    public void dodajDostawce(String imie, String nazwisko, int x, int y) {
        Dostawca d = new Dostawca(imie, nazwisko, x, y, this);
        dostawcaL.put(d.getImie() + " " + d.getNazwisko(),d);
        licznikLudzi++;
    }

    
    public HashMap<String,Dostawca> getDostawcaL() {
        return dostawcaL;
    }

    public synchronized ArrayList<Zamowienie> getZamowienieL() {
        return zamowienieL;
    }

    public synchronized ArrayList<Zamowienie> getZamowieniePrzygotowane() {
        return zamowieniePrzygotowane;
    }

    public void setDostawcaL(HashMap<String,Dostawca> dostawcaL) {
        this.dostawcaL = dostawcaL;
    }

    public synchronized void setZamowienieL(ArrayList<Zamowienie> zamowienieL) {
        this.zamowienieL = zamowienieL;
    }

    public synchronized void setZamowieniePrzygotowane(ArrayList<Zamowienie> zamowieniePrzygotowane) {
        this.zamowieniePrzygotowane = zamowieniePrzygotowane;
    }
    
    public HashMap<String,Klient> getKlientL() {
        return klientL;
    }

    public void setKlientL(HashMap<String,Klient> klientL) {
        this.klientL = klientL;
    }   

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }
    
    public void zapiszStan(){
        String nazwaPliku = "pizzeria.ser";
        //String nazwaPliku = "c:\\lista.ser";
    ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(nazwaPliku)));
        
            out.writeObject(this);
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Pizzeria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void wczytajStan(){ 
        //pizzeriaJF.setVisible(false);
        String nazwaPliku = "pizzeria.ser";
        try {
            ObjectInputStream in = new ObjectInputStream(
                                 new BufferedInputStream(
                                   new FileInputStream(nazwaPliku)));
            Pizzeria p = new Pizzeria();
            p = (Pizzeria) in.readObject();
            in.close();
            PizzeriaJFrame pizzeriaJF = new PizzeriaJFrame(p);
            Menu menu = new Menu();
            Mapa m = new Mapa(p, pizzeriaJF);
            p.setMapa(m);
            pizzeriaJF.setPizzeria(p);
            pizzeriaJF.setVisible(true);
            pizzeriaJF.add(m);
            m.repaint();
            p.setPizzeriaJF(pizzeriaJF);
           // System.out.println(Float.toString(p.getKasa()));
           HashMap<String, Czlowiek> l = new HashMap<>(p.getKlientL());
                    l.putAll(p.getDostawcaL());
                        for(Map.Entry<String, Czlowiek> entry : l.entrySet()) {
                            String key = entry.getKey();
                            Czlowiek c = entry.getValue();
                            new Thread(c).start();
                            if(c instanceof Klient){
                                for(Zamowienie z : c.getZamowienieL()){
                                    if(!z.isPrzygotowane()){
                                        new Thread(z).start();
                                    }
                                }   
                            }
                        
                        /*for(Zamowienie z : zamowienieL){
                            if(!z.isPrzygotowane()){
                                new Thread(z).start();
                                z.przygotuj();
                            }*/
                        }
            this.pizzeriaJF.setVisible(false);
            p.pizzeriaJF.getWczytajB().setVisible(false);
            HashMap<String, Czlowiek> l2 = new HashMap<>(klientL);
        }catch (IOException ex) {
            Logger.getLogger(Pizzeria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Pizzeria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        Pizzeria pizzeria = new Pizzeria();
        pizzeria.setPolozenie(new Polozenie(150, 150));
        PizzeriaJFrame pizzeriaJF = new PizzeriaJFrame(pizzeria);
        Menu menu = new Menu();
        Mapa m = new Mapa(pizzeria, pizzeriaJF);
        pizzeria.setMapa(m);
        pizzeriaJF.setPizzeria(pizzeria);
        pizzeriaJF.setVisible(true);
        pizzeriaJF.add(m);
        m.repaint();
        pizzeria.setPizzeriaJF(pizzeriaJF);
        
        pizzeria.getMenu().put("Pizza1", new Pizza("Pizza1", 10, 2));
        pizzeria.getMenu().put("Napój1", new Napoj("Napój1", 11, 4));
        pizzeria.getMenu().put("Deser1", new Deser("Deser1", 15, 6));
        pizzeria.getMenu().put("Zupa1", new Zupa("Zupa1", 6, 8));
        pizzeria.getMenu().put("Sos1", new Sos("Sos1", 3, 11));
        pizzeria.getMapa().repaint();
        
        /*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });*/
    }
}
