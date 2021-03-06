/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Interfejs do ręcznego zamawiania.
 * @author Marcin
 */
public class ZamowJFrame extends javax.swing.JFrame {
    private Pizzeria pizzeria;
    private Zamowienie zamowienie;
    DefaultListModel listModel = new DefaultListModel();
    JList listaL = new JList(listModel);
    JScrollPane listaPane = new JScrollPane(listaL);
    /**
     * Creates new form ZamowJFrame
     */
    
    public void setPizzeria(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }

    public ZamowJFrame() {
        initComponents();
        
        listaL.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    Produkt p = pizzeria.getMenu().get(listaL.getSelectedIndex());
                }
            }
        });
        listaPane.setBounds(40, 120, 140, 140);
        add(listaPane);
    }

     public void wczytajKlientow(){
         if(!pizzeria.getKlientL().isEmpty()){
            for(Map.Entry<String, Klient> entry : pizzeria.getKlientL().entrySet()) {
                   String key = entry.getKey();
                   Klient k = entry.getValue();
               klientCB.addItem(k.getImie() + " " + k.getNazwisko());
           }
         }
     }
     public void wczytajMenu(){
         
        HashMap<String, Produkt> selects = pizzeria.getMenu();
        for(Map.Entry<String, Produkt> entry : selects.entrySet()) {
            String key = entry.getKey();
            Produkt p = entry.getValue();

            produktCB.addItem(p.getNazwa());
            if(!(p instanceof Deser) && !(p instanceof Napoj) && !(p instanceof Zupa))
                danieCB.addItem(p.getNazwa());
            if(p instanceof Zupa)
                zupaCB.addItem(p.getNazwa());
            if(p instanceof Napoj)
                napojCB.addItem(p.getNazwa());
        }
     }
     public void odswiezListe(){
        listModel.clear();
        for(Produkt p : zamowienie.getProdukt()){
            listModel.addElement(p.getNazwa());
        }       
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        zamowB = new javax.swing.JButton();
        klientCB = new javax.swing.JComboBox<>();
        produktCB = new javax.swing.JComboBox<>();
        dodajB = new javax.swing.JButton();
        danieCB = new javax.swing.JComboBox<>();
        zupaCB = new javax.swing.JComboBox<>();
        napojCB = new javax.swing.JComboBox<>();
        dodajZB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        zamowB.setText("Zamów");
        zamowB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                zamowBMouseClicked(evt);
            }
        });

        klientCB.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                klientCBPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        dodajB.setText("Dodaj");
        dodajB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dodajBMouseClicked(evt);
            }
        });

        dodajZB.setText("Zestaw");
        dodajZB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dodajZBMouseClicked(evt);
            }
        });
        dodajZB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajZBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(produktCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dodajB))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(klientCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(zupaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(danieCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(napojCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(zamowB)
                            .addComponent(dodajZB))))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(klientCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(produktCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dodajB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zupaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(napojCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dodajZB)
                    .addComponent(danieCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                .addComponent(zamowB)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void zamowBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_zamowBMouseClicked
        // TODO add your handling code here:
        try{
            if(zamowienie == null)
                throw new MyException("Nie dodałeś żadnego produktu!");
            pizzeria.getKlientL().get(klientCB.getSelectedItem()).zamow(zamowienie);
            this.setVisible(false);
        }
        catch(MyException e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_zamowBMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here
        wczytajKlientow();
        wczytajMenu();
    }//GEN-LAST:event_formWindowOpened

    private void dodajBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dodajBMouseClicked
        // TODO add your handling code here:
        if(!pizzeria.getKlientL().isEmpty()){
            if(zamowienie == null)
                zamowienie = new Zamowienie(pizzeria, pizzeria.getKlientL().get(klientCB.getSelectedItem()));
            zamowienie.getProdukt().add(pizzeria.getMenu().get(produktCB.getSelectedItem()));
            odswiezListe();
        }
    }//GEN-LAST:event_dodajBMouseClicked

    private void dodajZBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dodajZBMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dodajZBMouseClicked

    private void dodajZBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodajZBActionPerformed
        // TODO add your handling code here:
        try{
            if(danieCB.getSelectedItem() == null || zupaCB.getSelectedItem() == null ||
                    napojCB.getSelectedItem() == null)
                throw new MyException("Wybrakowane menu");
            else if(pizzeria.getKlientL().isEmpty())
                throw new MyException("Brak Klientów");
            else {
                Zestaw z = new Zestaw("Obiad", pizzeria.getMenu().get(zupaCB.getSelectedItem()), 
                    pizzeria.getMenu().get(danieCB.getSelectedItem()), 
                    pizzeria.getMenu().get(napojCB.getSelectedItem()));
        if(zamowienie == null)
            zamowienie = new Zamowienie(pizzeria, pizzeria.getKlientL().get(klientCB.getSelectedItem()));
                zamowienie.getProdukt().add(z);
                odswiezListe();
            }
        }
        catch(MyException e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_dodajZBActionPerformed

    private void klientCBPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_klientCBPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if(!pizzeria.getKlientL().isEmpty()){
            if(zamowienie == null)
                zamowienie = new Zamowienie(pizzeria, pizzeria.getKlientL().get(klientCB.getSelectedItem()));
            zamowienie.setKlient(pizzeria.getKlientL().get(klientCB.getSelectedItem()));
        }
    }//GEN-LAST:event_klientCBPopupMenuWillBecomeInvisible

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ZamowJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ZamowJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ZamowJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ZamowJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ZamowJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> danieCB;
    private javax.swing.JButton dodajB;
    private javax.swing.JButton dodajZB;
    private javax.swing.JComboBox<String> klientCB;
    private javax.swing.JComboBox<String> napojCB;
    private javax.swing.JComboBox<String> produktCB;
    private javax.swing.JButton zamowB;
    private javax.swing.JComboBox<String> zupaCB;
    // End of variables declaration//GEN-END:variables
}
