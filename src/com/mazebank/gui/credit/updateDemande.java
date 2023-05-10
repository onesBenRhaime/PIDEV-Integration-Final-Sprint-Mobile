/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazebank.gui.credit;

    import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mazebank.entities.DemandeCredit;
import com.mazebank.services.serviceDemandes;

/**
 *
 * @author ASUS
 */
public class updateDemande extends Form {
    
    //Modif
      Form previous; 
      public updateDemande (Form previous, DemandeCredit r ) {
            TextField note = new TextField(r.getNote(), "note");          
           
            Button btnModifier = new Button("Modifier");         
            btnModifier.setUIID("buttonCenter"); 
            Button btnAnnuler = new Button("Annuler");         
            btnAnnuler.setUIID("buttonCenter");  
         
         
        
        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form f2 = new Form(BoxLayout.y());
                Label lnote=new Label(" "+note.getText());
               
                f2.add(lnote);
                        f2.show();
                        r.setNote(note.getText());
              if(serviceDemandes.getInstance().modifier(r)) { 
                new listDemandesCredits(previous).show();
              }            
            }
        });
            addAll(note,btnAnnuler,btnModifier);
        btnAnnuler.addActionListener(e -> {
           new listDemandesCredits(previous).show();
        });
    }
  
}