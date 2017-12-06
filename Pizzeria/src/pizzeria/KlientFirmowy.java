/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

/**
 * Wyróżnia się dodatkowymi informacjami.
 * @author Marcin
 */
public class KlientFirmowy extends Klient{
    public KlientFirmowy(String imie, String nazwisko, int x, int y, Pizzeria pizzeria){
        super(imie, nazwisko, x, y, pizzeria);
    }
    private String adresKoresp;
    private String regon;
    private String nrKontaBank;

    public String getAdresKoresp() {
        return adresKoresp;
    }

    public String getRegon() {
        return regon;
    }

    public String getNrKontaBank() {
        return nrKontaBank;
    }

    public void setAdresKoresp(String adresKoresp) {
        this.adresKoresp = adresKoresp;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public void setNrKontaBank(String nrKontaBank) {
        this.nrKontaBank = nrKontaBank;
    }
    
    
}
