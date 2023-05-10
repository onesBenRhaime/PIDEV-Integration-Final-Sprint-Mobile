package com.mazebank.gui.transaction;

import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.*;
import com.mazebank.entities.Transaction;
import com.mazebank.services.TransactionService;
import com.codename1.ui.plaf.Style;
import java.text.SimpleDateFormat;
import java.util.*;

import com.codename1.ui.plaf.Style;
import com.eshop.gui.Accueil;
public class ShowAll extends Form {

    Form previous;  
    
    public static Transaction currentTransaction = null;
    Button addBtn;
     TextField searchField;
    public ShowAll(Form previous) {
        super("Transactions", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;
        addGUIs();
        addActions();
          FloatingActionButton.setIconDefaultSize(5);
         FloatingActionButton fa = FloatingActionButton.createFAB(FontImage.MATERIAL_STORE);
         fa.addActionListener(e -> new Accueil().show());
         fa.bindFabToContainer(previous.getContentPane());
        
        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        addActions();
        this.refreshTheme();
    }

    private void addGUIs() {
        addBtn = new Button("Ajouter");
        addBtn.setUIID("buttonWhiteCenter");
        this.add(addBtn);
        
        searchField = new TextField();
        searchField.getAllStyles().setBgColor(0xCF0000);
        searchField.setUIID("whiteSearchField");
        searchField.setHint("Search By ID ");
        this.add(searchField);
        searchField.addActionListener(e -> {
            String searchText = searchField.getText();
            // Do something with the search text, such as filtering a list of items
        });
        ArrayList<Transaction> listReclamations = TransactionService.getInstance().getAll();

        for ( Transaction rec : listReclamations) {
            System.out.println(" rec :"+ rec);
        };
            
        
        if (listReclamations.size() > 0) {
            for (Transaction transaction : listReclamations) {
                Component model = makeTransactionModelModel(transaction);
                this.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }
    private void addActions() {
        addBtn.addActionListener(action -> {
            currentTransaction = null;
            new AddTransaction(this).show();
        });
        
    }
    Label  lid, lrequestTo,lrequestFrom,lmontant,ltypeTransaction;
    
    

    private Container makeModelWithoutButtons(Transaction transaction) {
        Container transactionModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        transactionModel.setUIID("containerRounded");
        
        lid = new Label("id : " + transaction.getId());
        lid.setUIID("labelDefault");
        
        lrequestTo = new Label("request to : " + transaction.getRequestTo());
        lrequestTo.setUIID("labelDefault");
        
        lrequestFrom = new Label("request From : " + transaction.getRequestFrom());
        lrequestFrom.setUIID("labelDefault");
        
        lmontant   = new Label("Type : " + transaction.getMontant());
        lmontant.setUIID("labelDefault");
        
        ltypeTransaction = new Label("Type Transaction : " + transaction.getTypeTransaction());
        ltypeTransaction.setUIID("labelDefault");
        
      
        
       
        transactionModel.addAll(                
             lid, lrequestTo,lrequestFrom,lmontant,ltypeTransaction
        );

        return transactionModel;
    }
    
    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeTransactionModelModel(Transaction transaction) {

        Container transactionModel = makeModelWithoutButtons(transaction);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");
        
        editBtn = new Button("");
        editBtn.setUIID("buttonWhiteCenter");       
  
        Style supprmierStyle = new Style(editBtn.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, supprmierStyle);
        editBtn.setIcon(suprrimerImage);
        editBtn.setTextPosition(RIGHT);
        editBtn.addActionListener(action -> {
            currentTransaction = transaction;
            new Edit(previous, transaction).show();
        });
      
        
        deleteBtn = new Button("");
        deleteBtn.setUIID("buttonWhiteCenter");
        
        Style supprmierStyle1 = new Style(deleteBtn.getUnselectedStyle());
        supprmierStyle1.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage1 = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle1);
        deleteBtn.setIcon(suprrimerImage1);
        deleteBtn.setTextPosition(RIGHT);
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce Transaction ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            
            btnConfirm.addActionListener(actionConf -> {
             
              System.out.println("ID  ="+transaction.getId());
              TransactionService.getInstance().delete(transaction.getId());         
                new ShowAll(previous).show();
            });
            
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);
        
        
        transactionModel.add(btnsContainer);

        return transactionModel;
    }
    
}