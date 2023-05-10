/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazebank.gui.credit;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
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
public class addDemandeCredit extends Form{

    private int creditId;


    public addDemandeCredit(Form previous,int creditId) {
        setTitle("Add a new demande ");
        setLayout(BoxLayout.y());
        this.creditId = creditId;

        TextField tfAmount = new TextField("","amount");
        TextField tfNote = new TextField("","note");
        TextField tfCin1 = new TextField("","id card 1");
        TextField tfCin2 = new TextField("","id card 2");

        Button btnValider = new Button("Add demande");
        
    
    btnValider.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (tfAmount.getText().isEmpty() || tfNote.getText().isEmpty() || tfCin1.getText().isEmpty() || tfCin2.getText().isEmpty()) {
            Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
        } else {
            try {
                // Code to add the credit demand
                DemandeCredit demande = new DemandeCredit();
                demande.setAmount(Integer.parseInt(tfAmount.getText()));
                demande.setNote(tfNote.getText());
                demande.setCin1(tfCin1.getText());
                demande.setCin2(tfCin2.getText());
                serviceDemandes.getInstance().addDemande(demande, creditId); 
                Dialog.show("Success", "Credit demand added successfully", new Command("OK"));
            } catch (NumberFormatException e) {
                Dialog.show("ERROR", "Amount must be a number", new Command("OK"));
            }
        }
    }
});


    addAll(tfAmount, tfNote, tfCin1, tfCin2, btnValider);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
}

   
    
    
}

    



