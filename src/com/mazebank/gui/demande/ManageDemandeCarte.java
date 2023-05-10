package com.mazebank.gui.demande;

import com.codename1.ui.*;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mazebank.entities.*;
import com.mazebank.services.*;


public class ManageDemandeCarte extends Form {
     Form previous; 
     //add  GUI
    public ManageDemandeCarte (Form previous) {        
            super("Ajouter demande", new BoxLayout(BoxLayout.Y_AXIS));           
             super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
      
            TextField email = new TextField("", "email");
            TextField identifier = new TextField("", "identifier");
            TextField description = new TextField("", "description");
            TextField cinS1 = new TextField("", "cinS1");
            
        
        Button btnValider = new Button("Add ");
        btnValider.setUIID("buttonWhiteCenter");
        Style ajouterStyle = new Style(btnValider.getUnselectedStyle());
        ajouterStyle.setFgColor(0x0000FF); // Couleur bleue pour l'icône Ajouter

        FontImage ajouterImage = FontImage.createMaterial(FontImage.MATERIAL_ADD, ajouterStyle); // Utilisation de l'icône Ajouter
        btnValider.setIcon(ajouterImage);
        btnValider.setTextPosition(RIGHT);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form f2 = new Form(BoxLayout.y());
                Label lemail=new Label("email: "+email.getText());
                Label lidentifier=new Label("identifier: "+identifier.getText());
                Label ldescription=new Label("description : "+description.getText());
                Label lcinS1=new Label("cinS1: "+cinS1.getText());
                
                f2.add(lemail);
                f2.add(lidentifier);
                f2.add(ldescription);
                f2.add(lcinS1);                
                f2.show();
                if ((email.getText().length()==0)&&(identifier.getText().length()==0)&&(description.getText().length()==0)&&(cinS1.getText().length()==0)){
                    //Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                   Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                   new ManageDemandeCarte(previous).show();
                } else{ 
                             CarteBancaire t = new CarteBancaire(email.getText(),identifier.getText(),description.getText(),cinS1.getText());
                            if( CarteBancaireService.getInstance().addTask(t))
                            {
                               Dialog.show("Success","Demande ajoutée",new Command("OK"));
                               new ShowAllDemandeCarte(previous).show();
                            }else
                                Dialog.show("ERROR", "Server error", new Command("OK"));
                    }

                 
            }
        });
        
        addAll(email,identifier,description,cinS1,btnValider);
                
    }
    
    
    
    
}