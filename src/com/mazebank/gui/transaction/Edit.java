

package com.mazebank.gui.transaction;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mazebank.entities.Transaction;
import com.mazebank.services.TransactionService;

public class Edit  extends Form {
    
    //Modif
      Form previous; 
      public Edit (Form previous, Transaction r ) {
        
            super("Modifier transaction", new BoxLayout(BoxLayout.Y_AXIS));           
            super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
      
            TextField requestTo = new TextField(r.getRequestTo(), "requestTo");
            TextField montant = new TextField(r.getMontant(), "montant");          
            TextField requestFrom = new TextField(r.getRequestFrom(), "requestFrom");
            TextField typeTransaction = new TextField(r.getTypeTransaction(), "typeTransaction");
            Button btnModifier = new Button("Modifier");         
            btnModifier.setUIID("buttonCenter"); 
            Button btnAnnuler = new Button("Annuler");         
            btnAnnuler.setUIID("buttonCenter");  
         
         
        Style editStyle = new Style(btnModifier.getUnselectedStyle());
        editStyle.setFgColor(0x00FF00);
        
        FontImage editImage = FontImage.createMaterial(FontImage.MATERIAL_CHECK_CIRCLE, editStyle,10);
        btnModifier.setIcon(editImage);
        btnModifier.setTextPosition(RIGHT);
        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form f2 = new Form(BoxLayout.y());
                Label lrequestTo=new Label("request To: "+requestTo.getText());
                Label lrequestFrom=new Label("request From: "+requestFrom.getText());
                Label lmontant=new Label("Montant : "+montant.getText());
                Label ltypeTransaction=new Label("Type Transaction: "+typeTransaction.getText());
                
                f2.add(lrequestTo);
                f2.add(lrequestFrom);
                f2.add(lmontant);
                f2.add(ltypeTransaction);                         
                f2.show();
                      
              r.setRequestTo(requestTo.getText());
              r.setMontant(montant.getText());     
     
              if(TransactionService.getInstance().modifier(r)) { 
                new ShowAll(previous).show();
              }            
            }
        });
            addAll(requestTo,requestFrom,montant,typeTransaction,btnAnnuler,btnModifier);
      //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
       
       
       Style annulerStyle = new Style(btnModifier.getUnselectedStyle());
        annulerStyle.setFgColor(0xf21f1f);
        
        FontImage annulerImage = FontImage.createMaterial(FontImage.MATERIAL_CANCEL, annulerStyle,10);
        btnAnnuler.setIcon(annulerImage);
        btnAnnuler.setTextPosition(RIGHT);
        btnAnnuler.addActionListener(e -> {
           new ShowAll(previous).show();
        });
    }
  
}
