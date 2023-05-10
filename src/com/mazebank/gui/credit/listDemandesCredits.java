/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazebank.gui.credit;

import com.codename1.components.FloatingActionButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.eshop.gui.Accueil;
import com.mazebank.entities.Credit;
import com.mazebank.entities.DemandeCredit;
import com.mazebank.entities.Transaction;
import com.mazebank.gui.demande.ManageDemandeCarte;
import com.mazebank.services.serviceDemandes;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class listDemandesCredits extends Form {

Form previous; Form f;

public static DemandeCredit currentTransaction = null;

  public listDemandesCredits(Form previous) {

        setTitle("List of demandes");
        setLayout(BoxLayout.y());
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
        ArrayList<DemandeCredit> demandes = serviceDemandes.getInstance().getAllDemandes();
        if (demandes.size() > 0) {
             for (DemandeCredit d : demandes) {
            addElement(d);
        }
        } else {
            this.add(new Label("Aucune donnée"));
        }
        
      
        
 }


    public void addElement(DemandeCredit demandeCredit) {
         Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    container.getStyle().setBgColor(0xFFFFFF); // set background color to white
    container.getStyle().setPadding(0, 0, 5, 0); // add some padding to the bottom

    

    Label amountLabel = new Label("Amount: "+ demandeCredit.getAmount()+ " DT");
    amountLabel.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
    container.add(amountLabel);
    //container.add(new Label("Id: " + demandeCredit.getId()));
    Label noteLabel = new Label("Note: "+ demandeCredit.getNote());
    noteLabel.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
    container.add(noteLabel);
    container.add(new Label("id card first side: " + demandeCredit.getCin1()));
    container.add(new Label("id card second side:  " + demandeCredit.getCin2()));
    Button deleteButton = new Button("Delete demand");
    deleteButton.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, deleteButton.getUnselectedStyle()));

    deleteButton.addActionListener(evt -> {
         currentTransaction =demandeCredit;
          serviceDemandes.getInstance().delete(demandeCredit.getId());
            new Accueil().show();
          

    });
        container.add(deleteButton);

     Button editButton = new Button("edit");
         editButton.setIcon(FontImage.createMaterial(FontImage.MATERIAL_EDIT, editButton.getUnselectedStyle()));

    editButton.addActionListener(evt -> {
        currentTransaction =demandeCredit;
        new updateDemande(previous, demandeCredit).show();
      //    serviceDemandes.getInstance().modifier(demandeCredit);

    });
    container.add(editButton);
  
    add(container);
    }
    
    
}
 

