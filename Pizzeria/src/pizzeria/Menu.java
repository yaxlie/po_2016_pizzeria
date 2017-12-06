/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import java.io.Serializable;
import java.util.*;

/**
 * Spis dań i możliwość dodawania ich.
 * @author Marcin
 */
public class Menu  implements Serializable{
    public Menu(){
        lista = new ArrayList<Produkt>();
    }
    private ArrayList<Produkt> lista;

    public ArrayList<Produkt> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Produkt> lista) {
        this.lista = lista;
    }
    
    public void dodaj(Produkt produkt){
        lista.add(produkt);
    }
}
