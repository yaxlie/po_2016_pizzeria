/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Człowiek odpowiedzialny za roznoszenie Zamówień, osobny wątek.
 * @author Marcin
 */
public class Dostawca extends Czlowiek{
    public Dostawca(String imie, String nazwisko, int x, int y, Pizzeria pizzeria){
        super(imie, nazwisko, x, y, pizzeria);
        pojazd = new Samochod();
        godzinyPracy.add(GodzinyPracy.PoPoludniu);
        godzinyPracy.add(GodzinyPracy.PrzedPoludniem);
        godzinyPracy.add(GodzinyPracy.Wieczorem);
        godzinyPracy.add(GodzinyPracy.WNocy);
        dniPracy.add(DniPracy.Poniedzialek);
        dniPracy.add(DniPracy.Wtorek);
        dniPracy.add(DniPracy.Sroda);
        dniPracy.add(DniPracy.Czwartek);
        dniPracy.add(DniPracy.Piatek);
        dniPracy.add(DniPracy.Sobota);
        dniPracy.add(DniPracy.Niedziela);
    }
 
    protected enum GodzinyPracy {PrzedPoludniem, PoPoludniu, Wieczorem, WNocy};
    protected enum DniPracy {Poniedzialek, Wtorek, Sroda, Czwartek, Piatek, Sobota, Niedziela};
    protected enum UprPojazd {Skuter, Samochod, Wszystkie};
    private UprPojazd uprawnieniaPojazd = UprPojazd.Wszystkie;
    private ArrayList <GodzinyPracy> godzinyPracy = new ArrayList<>();
    private ArrayList<DniPracy> dniPracy = new ArrayList<>();
    private Pojazd pojazd;
    private boolean zajety=false;
    private boolean zmienPojazd = false;
    private boolean zwolniony = false;
    private boolean awaryjnyPow = false;
    
    @Override
    public void run() {
        super.run();
        while(true)
        {
            if(zwolniony){
                zwolnionyDzialania();
                break;
            }
            try {
                if(!zajety){
                    Thread.sleep(5000);
                }
            }
            catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            if(!isZyj())
                break;
            if(zmienPojazd){
                if(pojazd instanceof Samochod)
                    pojazd = new Skuter();
                else
                    pojazd = new Samochod();
                zmienPojazd = false;
            }
           if(dzienPracy()){
            awaryjnyPow = false;
            zbierzZamowienia();
            dostarcz();
            setZajety(false);
           }
           //else
               // System.out.println("nie pracuje");
        }
    }
    
    public void zbierzZamowienia(){
        synchronized(getPizzeria())
        {
            int i = 0;
            if(!zajety && !getPizzeria().getZamowieniePrzygotowane().isEmpty()){
                    for(Zamowienie z : getPizzeria().getZamowieniePrzygotowane()){
                        i++;
                        if(!z.isDostarczone())
                            getZamowienieL().add(z); 
                        if(i == getPojazd().getLadownosc())
                            break;
                    }
                    for(int a=0; a<getPojazd().getLadownosc();a++){
                        if(!getPizzeria().getZamowieniePrzygotowane().isEmpty())
                            getPizzeria().getZamowieniePrzygotowane().remove(0);
                    }
                    System.out.println(getImie() + " zabiera zamówienia! ");
                    setZajety(true);
                }
        }
    }
    
    public void dostarcz(){
        for(Zamowienie z : getZamowienieL()){
            if(!z.isDostarczone()){
                if(!czyStarczyPaliwa(z.getPolozenie()))
                    powrot();      
                przemiesc(z.getPolozenie());
                z.setDostarczone(true);
                synchronized(getPizzeria()){
                    if(getPizzeria().getKlientL().get(z.getKlient().getImie() + " " 
                            + z.getKlient().getNazwisko())!=null){
                        getPizzeria().setKasa(getPizzeria().getKasa() + z.cena());
                    }
                }
                if(zwolniony || awaryjnyPow)
                    break;
            }
        }
        powrot();
        
        if(!zwolniony && !awaryjnyPow){   
            getZamowienieL().clear();
        }
    }
public boolean przemiescSimple(Polozenie cel){
        cel.setX(cel.getX() - cel.getX()%getPojazd().getPredkosc());
        cel.setY(cel.getY() - cel.getY()%getPojazd().getPredkosc());
        int x = getPolozenie().getX();
        boolean b = false;
        int y = getPolozenie().getY();
                synchronized(getPizzeria().gandalf){
                if(getPolozenie().getX() > cel.getX() && 
                        getPizzeria().getDostepneWspolrz()[x - pojazd.getPredkosc()][y]){
                    //synchronized(getPizzeria().gandalf){
                        getPolozenie().setX(getPolozenie().getX() - pojazd.getPredkosc());
                        getPizzeria().setDostepneWspolrz(x,y,true);
                        getPizzeria().setDostepneWspolrz(x - pojazd.getPredkosc(),y,false);
                  //  }
                    b = true;
                }
                else if(getPolozenie().getX() < cel.getX() && 
                        getPizzeria().getDostepneWspolrz()[x + pojazd.getPredkosc()][y]){
                   // synchronized(getPizzeria().gandalf){
                        getPolozenie().setX(getPolozenie().getX() + pojazd.getPredkosc());
                        getPizzeria().setDostepneWspolrz(x,y,true);
                        getPizzeria().setDostepneWspolrz(x + pojazd.getPredkosc(),y,false);
                   // }
                    b = true;
                }
                x = getPolozenie().getX();
                y = getPolozenie().getY();
                if(getPolozenie().getY() > cel.getY() && 
                        getPizzeria().getDostepneWspolrz()[x][y - pojazd.getPredkosc()]){
                    //synchronized(getPizzeria().gandalf){
                        getPolozenie().setY(getPolozenie().getY() - pojazd.getPredkosc());
                        getPizzeria().setDostepneWspolrz(x,y,true);
                        getPizzeria().setDostepneWspolrz(x,y - pojazd.getPredkosc(),false);
                  //  }
                    b = true;
                }
                else if(getPolozenie().getY() < cel.getY() && 
                        getPizzeria().getDostepneWspolrz()[x][y + pojazd.getPredkosc()]){
                    //synchronized(getPizzeria().gandalf){
                        getPolozenie().setY(getPolozenie().getY() + pojazd.getPredkosc());
                        getPizzeria().setDostepneWspolrz(x,y,true);
                        getPizzeria().setDostepneWspolrz(x,y + pojazd.getPredkosc(),false);
                    //}
                    b = true;
                } 
                }
        return b;
    }
    
    public void wymin(){
        synchronized(getPizzeria().gandalf){
            int x = getPolozenie().getX();
            int y = getPolozenie().getY();
            if(getPizzeria().getDostepneWspolrz()[x + pojazd.getPredkosc()][y]){
                //synchronized(getPizzeria().gandalf){
                    getPolozenie().setX(getPolozenie().getX() + pojazd.getPredkosc());
                    getPizzeria().setDostepneWspolrz(x,y,true);
                    getPizzeria().setDostepneWspolrz(x + pojazd.getPredkosc(),y,false);
                //}
            }
            else if(getPizzeria().getDostepneWspolrz()[x - pojazd.getPredkosc()][y]){
                //synchronized(getPizzeria().gandalf){
                    getPolozenie().setX(getPolozenie().getX() - pojazd.getPredkosc());
                    getPizzeria().setDostepneWspolrz(x,y,true);
                    getPizzeria().setDostepneWspolrz(x - pojazd.getPredkosc(),y,false);
                //}
            }
            else if(getPizzeria().getDostepneWspolrz()[x][y + pojazd.getPredkosc()]){
                //synchronized(getPizzeria().gandalf){
                    getPolozenie().setX(getPolozenie().getY() + pojazd.getPredkosc());
                    getPizzeria().setDostepneWspolrz(x,y,true);
                    getPizzeria().setDostepneWspolrz(x,y + pojazd.getPredkosc(),false);
                //}    
            }
            else if(getPizzeria().getDostepneWspolrz()[x][y - pojazd.getPredkosc()]){
                //synchronized(getPizzeria().gandalf){
                    getPolozenie().setX(getPolozenie().getY() - pojazd.getPredkosc());
                    getPizzeria().setDostepneWspolrz(x,y,true);
                    getPizzeria().setDostepneWspolrz(x,y - pojazd.getPredkosc(),false);
               // }
            }
        }
    }

    
    public void przemiesc(Polozenie cel){
            while(Math.abs(getPolozenie().getX() - cel.getX()) > getPojazd().getPredkosc() || 
                    Math.abs(getPolozenie().getY() - cel.getY()) > getPojazd().getPredkosc()){
                if(!przemiescSimple(cel)){
                    wymin();
                }
                getPojazd().setPaliwo(getPojazd().getPaliwo() - getPojazd().getSpalanie());
                getPizzeria().getMapa().repaint();

                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                /*
                if(zwolniony){
                    zwolnionyDzialania();
                    break;
                }
                if(awaryjnyPow){
                    awaryjnyPowrot();
                    break;
                }*/
            }
            synchronized(getPizzeria().gandalf){
            getPizzeria().setDostepneWspolrz(getPolozenie().getX(),getPolozenie().getY(),true);
            }
            //setPolozenie(cel);
    }
    public void zwolnionyDzialania(){
        awaryjnyPowrot();
        getPizzeria().getDostawcaL().remove(getImie() + " " + getNazwisko());
        getPizzeria().getMapa().repaint();
        setZyj(false);
    }
    
    public void awaryjnyPowrot(){
        
        powrot();
        for(Zamowienie z : getZamowienieL()){
            synchronized(getPizzeria()){
                getPizzeria().getZamowieniePrzygotowane().add(z);
            }
        }
        getZamowienieL().clear();
    }
    public void powrot(){
        przemiesc(getPizzeria().getPolozenie());
        pojazd.tankuj();
    }
    
    public boolean czyStarczyPaliwa(Polozenie cel){
        int czas;
        if(Math.abs(cel.getX() - getPolozenie().getX()) > Math.abs(cel.getY() - getPolozenie().getY())){
            czas = Math.abs(cel.getX() - getPolozenie().getX());
        }
        else{
            czas = Math.abs(cel.getY() - getPolozenie().getY());
        }
        if(Math.abs(cel.getX() - getPizzeria().getPolozenie().getX()) > 
                Math.abs(cel.getY() - getPizzeria().getPolozenie().getY())){
            czas += Math.abs(cel.getX() - getPizzeria().getPolozenie().getX());
        }
        else{
            czas += Math.abs(cel.getY() - getPizzeria().getPolozenie().getY());
        }
       
        return pojazd.getPaliwo()/pojazd.getSpalanie() >= (float)czas * pojazd.getSpalanie();
    }
    

    public Pojazd getPojazd() {
        return pojazd;
    }

    public boolean isZajety() {
        return zajety;
    }


    public void setPojazd(Pojazd pojazd) {
        this.pojazd = pojazd;
    }

    public void setZajety(boolean zajety) {
        this.zajety = zajety;
    }

    public boolean isZwolniony() {
        return zwolniony;
    }

    public void setZwolniony(boolean zwolniony) {
        this.zwolniony = zwolniony;
    }

    public UprPojazd getUprawnieniaPojazd() {
        return uprawnieniaPojazd;
    }

    public void setUprawnieniaPojazd(UprPojazd uprawnieniaPojazd) {
        this.uprawnieniaPojazd = uprawnieniaPojazd;
    }

    public ArrayList<DniPracy> getDniPracy() {
        return dniPracy;
    }

    public void setDniPracy(ArrayList<DniPracy> dniPracy) {
        this.dniPracy = dniPracy;
    }

    public ArrayList<GodzinyPracy> getGodzinyPracy() {
        return godzinyPracy;
    }

    public void setGodzinyPracy(ArrayList<GodzinyPracy> godzinyPracy) {
        this.godzinyPracy = godzinyPracy;
    }

    public void setZmienPojazd(boolean zmienPojazd) {
        this.zmienPojazd = zmienPojazd;
    }

    public boolean isZmienPojazd() {
        return zmienPojazd;
    }
    private boolean dzienPracy(){
        boolean boolD = false;
        boolean boolG = false;
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        DniPracy dzien = null;
        GodzinyPracy godzina = null;
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH");
        Date now = new Date();
         switch(dayOfWeek){
            case 1:    
                 dzien = DniPracy.Niedziela;
                 break;
            case 2:    
                 dzien = DniPracy.Poniedzialek;
                 break;
            case 3:    
                 dzien = DniPracy.Wtorek;
                 break;
            case 4:    
                 dzien = DniPracy.Sroda;
                 break;
            case 5:    
                 dzien = DniPracy.Czwartek;
                 break;
            case 6:    
                 dzien = DniPracy.Piatek;
                 break;
            case 7:    
                 dzien = DniPracy.Sobota;
                 break;         
         }
         
         int g = Integer.parseInt(sdfTime.format(now));
         if(g > 17 && g <= 25)
             godzina = GodzinyPracy.Wieczorem;
         else
             if(g >= 0 && g <= 6)
             godzina = GodzinyPracy.WNocy;
         else
             if(g > 6 && g <= 12)
             godzina = GodzinyPracy.PrzedPoludniem;
         else
             if(g > 12 && g < 17)
             godzina = GodzinyPracy.PoPoludniu;
         
         for(DniPracy d : dniPracy){
             if (d == dzien)
                     boolD = true;
         }
         for(GodzinyPracy h : godzinyPracy){
             if (h == godzina)
                     boolG = true;
         }
        return boolD&&boolG;
        
    }

    public void setAwaryjnyPow(boolean awaryjnyPow) {
        this.awaryjnyPow = awaryjnyPow;
    }
    
}
