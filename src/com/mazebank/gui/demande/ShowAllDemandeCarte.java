package com.mazebank.gui.demande;

import com.codename1.components.*;
import static com.codename1.io.Log.e;
import com.codename1.ui.*;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.Style;
import com.mazebank.entities.CarteBancaire;
import com.eshop.gui.Accueil;
import com.mazebank.services.CarteBancaireService;

import java.text.SimpleDateFormat;
import java.util.*;

public class ShowAllDemandeCarte extends Form {

    Form previous; 
    Form f;

    
    public static CarteBancaire currentDemande = null;
    Button addBtn;

    public ShowAllDemandeCarte(Form previous) {
        
        
        super("Liste Demande", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;
        f=this;
        addGUIs();
        addActions();
        
         FloatingActionButton.setIconDefaultSize(5);
         FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
         fab.addActionListener(e -> new ManageDemandeCarte( previous).show());
         fab.bindFabToContainer(f.getContentPane());
         
         FloatingActionButton.setIconDefaultSize(5);
         FloatingActionButton fa = FloatingActionButton.createFAB(FontImage.MATERIAL_STORE);
         fa.addActionListener(e -> new Accueil().show());
         fa.bindFabToContainer(f.getContentPane());
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
        //addBtn.setUIID("buttonWhiteCenter");
        addBtn.setUIID("buttonWhiteCenter");
        Style ajouterStyle = new Style(addBtn.getUnselectedStyle());
        ajouterStyle.setFgColor(0x0000FF); // Couleur bleue pour l'icône Ajouter

        FontImage ajouterImage = FontImage.createMaterial(FontImage.MATERIAL_ADD, ajouterStyle); // Utilisation de l'icône Ajouter
        addBtn.setIcon(ajouterImage);
        addBtn.setTextPosition(RIGHT);
        this.add(addBtn);

        ArrayList<CarteBancaire> listDemande = CarteBancaireService.getInstance().getAll();

        for ( CarteBancaire rec : listDemande) {
            System.out.println(" rec :"+ rec);
        };
            
        
        if (listDemande.size() > 0) {
            for (CarteBancaire cartebancaire : listDemande) {
                Component model = makeDemandeenModel(cartebancaire);
                this.add(model);
            }
        } else {
            this.add(new Label("Liste vide"));
        }
    }
    private void addActions() {
        
        addBtn.addActionListener(action -> {
            currentDemande = null;
            new ManageDemandeCarte(this).show();
        });
        
    }
    Label  lid, lidentifier,lemail,ldescription,lcinS1;
    
    

    private Container makeModelWithoutButtons(CarteBancaire cartebancaire) {
        Container DemandeModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        DemandeModel.setUIID("containerRounded");
        
        lid = new Label("id : " + cartebancaire.getId());
        lid.setUIID("labelDefault");
        
        
        lemail = new Label("email : " + cartebancaire.getEmail());
        lemail.setUIID("labelDefault");
        
         lidentifier = new Label("identifier : " + cartebancaire.getIdentifier());
        lidentifier.setUIID("labelDefault");
        
        ldescription   = new Label("description : " + cartebancaire.getDescription());
        ldescription.setUIID("labelDefault");
        
        lcinS1 = new Label("cinS1: " + cartebancaire.getCinS1());
        lcinS1.setUIID("labelDefault");
        
      
        
       
        DemandeModel.addAll(                
             lid,lemail,lidentifier,ldescription,lcinS1
        );

        return DemandeModel;
    }
    
    Button editBtn, deleteBtn;
    Container btnsContainer;

    private Component makeDemandeenModel(CarteBancaire cartebancaire) {

        Container DemandeModel = makeModelWithoutButtons(cartebancaire);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");
        
        editBtn = new Button("Modifier");
       editBtn.setUIID("buttonWhiteCenter");
       Style modifierStyle = new Style(editBtn.getUnselectedStyle());
       modifierStyle.setFgColor(0x008000); // Couleur verte pour l'icône Modifier

        FontImage modifierImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle); // Utilisation de l'icône Modifier
        editBtn.setIcon(modifierImage);
        editBtn.setTextPosition(RIGHT);
        
        editBtn.addActionListener(action -> {
            currentDemande = cartebancaire;
            new EditDemandeCarte(previous, cartebancaire).show();
        });
      
        
        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonWhiteCenter");
        //deleteBtn.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(deleteBtn.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        deleteBtn.setIcon(suprrimerImage);
        deleteBtn.setTextPosition(RIGHT);
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ?"));
            Button btnClose = new Button("Annuler");
             btnClose.setUIID("buttonWhiteCenter");
            btnClose.setUIID("buttonWhiteCenter");
       Style annulerStyle = new Style(btnClose.getUnselectedStyle());
        annulerStyle.setFgColor(0x2196F3); // Couleur bleue pour l'icône Annuler

        FontImage annulerImage = FontImage.createMaterial(FontImage.MATERIAL_CANCEL, annulerStyle); // Utilisation de l'icône Annuler
        btnClose.setIcon(annulerImage);
        btnClose.setTextPosition(RIGHT); 
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.setUIID("buttonWhiteCenter");
Style confirmerStyle = new Style(btnConfirm.getUnselectedStyle());
confirmerStyle.setFgColor(0x4CAF50); // Couleur verte pour l'icône Confirmer

FontImage confirmerImage = FontImage.createMaterial(FontImage.MATERIAL_CHECK, confirmerStyle); // Utilisation de l'icône Confirmer
btnConfirm.setIcon(confirmerImage);
btnConfirm.setTextPosition(RIGHT);
            btnConfirm.addActionListener(actionConf -> {
              System.out.println("reclamation ="+cartebancaire);
              System.out.println("ID  ="+cartebancaire.getId());
              CarteBancaireService.getInstance().delete(cartebancaire.getId());         
                new ShowAllDemandeCarte(previous).show();
            });
            
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);
        
        
        DemandeModel.add(btnsContainer);

        return DemandeModel;
    }
    
    
}