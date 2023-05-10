

package com.mazebank.gui.demande;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mazebank.entities.CarteBancaire;
import com.mazebank.services.CarteBancaireService;

public class EditDemandeCarte  extends Form {
    
    //Modif
      Form previous; 
    public EditDemandeCarte (Form previous, CarteBancaire r ) {
        
            super("Modifier Demande", new BoxLayout(BoxLayout.Y_AXIS));           
            super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
            
            TextField email = new TextField(r.getEmail(), "email");    
            TextField identifier = new TextField(r.getIdentifier(), "Identifier"); 
            TextField description = new TextField(r.getDescription(), "description");
            TextField cinS1 = new TextField(r.getCinS1(), "cinS1");
        
         Button btnModifier = new Button("Edit");
        btnModifier.setUIID("buttonWhiteCenter");
        Style modifierStyle = new Style(btnModifier.getUnselectedStyle());
       modifierStyle.setFgColor(0x008000); // Couleur verte pour l'icône Modifier

        FontImage modifierImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle); // Utilisation de l'icône Modifier
        btnModifier.setIcon(modifierImage);
        btnModifier.setTextPosition(RIGHT);
        
        
        
         Button btnAnnuler = new Button("Annuler");
         btnAnnuler.setUIID("buttonWhiteCenter");
       Style annulerStyle = new Style(btnAnnuler.getUnselectedStyle());
        annulerStyle.setFgColor(0x2196F3); // Couleur bleue pour l'icône Annuler

        FontImage annulerImage = FontImage.createMaterial(FontImage.MATERIAL_CANCEL, annulerStyle); // Utilisation de l'icône Annuler
        btnAnnuler.setIcon(annulerImage);
        btnAnnuler.setTextPosition(RIGHT); 
         btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form f2 = new Form(BoxLayout.y());
                Label lemail=new Label("email : "+email.getText());
                Label lidentifier=new Label("identifier : "+identifier.getText());
                Label ldescription=new Label("description : "+description.getText());
                Label lcinS1=new Label("Cin : "+cinS1.getText());
                
                f2.add(lemail);
                f2.add(lidentifier);
                f2.add(ldescription);
                f2.add(lcinS1);                         
                f2.show();
                      
                
              r.setEmail(email.getText());   
              r.setIdentifier(identifier.getText()); 
              r.setDescription(description.getText()); 
              r.setCinS1(cinS1.getText()); 
     
              if(CarteBancaireService.getInstance().modifier(r)) { 
                new ShowAllDemandeCarte(previous).show();
              }            
            }
        });
            addAll(email,identifier,description,cinS1,btnAnnuler,btnModifier);
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
       
         btnAnnuler.addActionListener(e -> {
           new ShowAllDemandeCarte(previous).show();
         });
    }
  
}
