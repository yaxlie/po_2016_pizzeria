/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
/**
 * Klasa odpowiedzialna za rysowanie mapy i położenia Ludzi.
 * @author Marcin
 */
public class Mapa extends JPanel{
    
    private ImageIcon klient;
    private ImageIcon dostawca;
    private ImageIcon pizzeriaIcon;
    private Polozenie polozenie;
    private Pizzeria pizzeria;
    private PizzeriaJFrame pizzeriaJF;
    //public Mapa(Polozenie polozenie)
    public Mapa(Pizzeria p, PizzeriaJFrame pJF){
        //this.polozenie = polozenie;
        pizzeria = p; 
        pizzeriaJF = pJF;
        klient = new ImageIcon(this.getClass().getResource("/images/klient.png"));
        dostawca = new ImageIcon(this.getClass().getResource("/images/dostawca.png"));
        pizzeriaIcon = new ImageIcon(this.getClass().getResource("/images/point.png"));
        this.setBounds(108, 20, 350, 350);
        
        addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Point me = e.getPoint();
                    HashMap<String, Czlowiek> l = new HashMap<>(pizzeria.getKlientL());
                    l.putAll(pizzeria.getDostawcaL());
                        for(Map.Entry<String, Czlowiek> entry : l.entrySet()) {
                            String key = entry.getKey();
                            Czlowiek c = entry.getValue();
                        Point imgPoint = new Point(c.getPolozenie().getX(),c.getPolozenie().getY());
                        Rectangle bounds = new Rectangle(imgPoint, 
                                new Dimension(klient.getIconWidth(), klient.getIconHeight()));
                        if (bounds.contains(me)) {
                            CzlowiekInfoJFrame jF = new CzlowiekInfoJFrame(pizzeria,c);
                            jF.setVisible(true);
                            jF.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        }
                    }
                }
            });
        } 
    @Override
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
                pizzeriaIcon.paintIcon(this, g, pizzeria.getPolozenie().getX(), pizzeria.getPolozenie().getY());
                for(Map.Entry<String, Klient> entry : pizzeria.getKlientL().entrySet()) {
                    String key = entry.getKey();
                    Klient k = entry.getValue();
                    klient.paintIcon(this, g, k.getPolozenie().getX(), k.getPolozenie().getY());
                    
                    g.drawString(k.getImie() + " (" + Integer.toString(liczbaZamowien(k)) + ")", 
                        k.getPolozenie().getX()-20, k.getPolozenie().getY()+20);
                }
                for(Map.Entry<String, Dostawca> entry : pizzeria.getDostawcaL().entrySet()) {
                    String key = entry.getKey();
                    Dostawca d = entry.getValue();    
                    dostawca.paintIcon(this, g, d.getPolozenie().getX(), d.getPolozenie().getY());
                    g.drawString(d.getImie() + " (" + Integer.toString(liczbaZamowien(d)) + ")", 
                            d.getPolozenie().getX()-20, d.getPolozenie().getY()+20);
                }
                pizzeriaJF.refresh();
                //g.drawImage(punkt, 100, 100, this);
	}
     
    public int liczbaZamowien(Czlowiek c){
        int n = 0;
        for(Zamowienie z : c.getZamowienieL()){
            if(!z.isDostarczone())
                n++;
        }
        return n;
    }    
    public void setPolozenie(Polozenie polozenie) {
        this.polozenie = polozenie;
    }

    public void setPizzeria(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }

    public Pizzeria getPizzeria() {
        return pizzeria;
    }   
}
