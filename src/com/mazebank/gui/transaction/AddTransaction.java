package com.mazebank.gui.transaction;

import com.mazebank.entities.Transaction;
import com.mazebank.services.TransactionService;
import com.codename1.ui.*;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;


public class AddTransaction extends Form {
     Form previous; 
     //add  GUI
    public AddTransaction (Form previous) {        
            super("Ajouter transaction", new BoxLayout(BoxLayout.Y_AXIS));           
            super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
      
           
            TextField requestTo = new TextField("", "requestTo");
            TextField requestFrom = new TextField("", "requestFrom");
            TextField montant = new TextField("", "montant");
            TextField typeTransaction = new TextField("", "typeTransaction");
            
        
        Button btnValider = new Button("Ajouter");
        Style validerStyle = new Style(btnValider.getUnselectedStyle());
        validerStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_CHECK_CIRCLE, validerStyle,10);
        btnValider.setIcon(suprrimerImage);
       // btnValider.setTextPosition(RIGHT);        

        
        btnValider.addActionListener(new ActionListener() {
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
                if ((requestTo.getText().length()==0)&&(requestFrom.getText().length()==0)&&(montant.getText().length()==0)&&(typeTransaction.getText().length()==0)){
                     Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                     new AddTransaction(previous).show();                
                }else{ 
                     Transaction t = new Transaction(requestTo.getText(),lrequestFrom.getText(),montant.getText(),typeTransaction.getText());
                    if( TransactionService.getInstance().addTask(t)){
                        Dialog.show("Success","Transaction added",new Command("OK"));
                        new ShowAll(previous).show();
                    }else
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
            }
        });        
        addAll(requestTo,requestFrom,montant,typeTransaction,btnValider);                
    }
    
    
    
    
}