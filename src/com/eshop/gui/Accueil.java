package com.eshop.gui;

import com.codename1.ui.*;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mazebank.gui.transaction.ShowAll;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.FontImage;
import com.codename1.ui.plaf.Style;
import com.mazebank.gui.credit.listDemandesCredits;
import com.mazebank.gui.credit.listOffreCredit;
import com.mazebank.gui.demande.ShowAllDemandeCarte;
import com.mazebank.gui.demande.ShowAllStatistiquesDemandeCarte;
import com.mazebank.gui.offre.AfficherToutOffreAssurance;
import com.mazebank.gui.offre.AfficherToutOffreEmbauche;
import com.mazebank.gui.offre.AfficherToutOffreInvestissement;
import com.mazebank.gui.transaction.ShowAllStatistiques;


public class Accueil extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    Label label;
    Resources res;

    Fragment fragment = new Fragment();

    public Accueil() {
        super("MazeBank", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
    }

    private void addGUIs() {

        label = new Label("Gestion Transaction");
        label.setUIID("links");

        Container userContainer = new Container(new BorderLayout());
        userContainer.setUIID("userContainer");
        userContainer.add(BorderLayout.CENTER, label);

        Container menuContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        menuContainer.addAll(
                userContainer,
                makeTransactionsButton(),
                makeDemandeCarteButton(),  
                makeListCreditsButton(),
                makeDemandesCreditsButton(),
                makeOffreAssurancesButton(),
                makeOffreEmbauchesButton(),
                makeOffreInvestissementsButton(),
                StatistiqueTransaction(),                
                makeStatistiqueDemandeCarte(), 
                btn
        );

        this.add(menuContainer);
    }

    private Button makeTransactionsButton() {
        Button buttonTransaction = new Button(" Mes Transaction");
        buttonTransaction.setUIID("buttonMenu");
        buttonTransaction.setUIID("buttonWhiteCenter");
        Style listStyle = new Style(buttonTransaction.getUnselectedStyle());
        listStyle.setFgColor(0x000000);
        FontImage listImage = FontImage.createMaterial(FontImage.MATERIAL_FORMAT_LIST_BULLETED, listStyle);
        buttonTransaction.setIcon(listImage);
        buttonTransaction.setTextPosition(RIGHT);
        buttonTransaction.setMaterialIcon(FontImage.MATERIAL_PAID);
        buttonTransaction.addActionListener(action -> new ShowAll(this).show());

        return buttonTransaction;

    }

    private Button makeDemandeCarteButton() {
        //Salma 
        Button buttonDemandes = new Button("Mes Demandes");
        buttonDemandes.setUIID("buttonMenu");
        buttonDemandes.setUIID("buttonWhiteCenter");
        Style listStyle = new Style(buttonDemandes.getUnselectedStyle());
        listStyle.setFgColor(0x000000);

        FontImage listImage = FontImage.createMaterial(FontImage.MATERIAL_FORMAT_LIST_BULLETED, listStyle);
        buttonDemandes.setIcon(listImage);
        buttonDemandes.setTextPosition(RIGHT);
        buttonDemandes.addActionListener(action -> new ShowAllDemandeCarte(this).show());

        return buttonDemandes;
    }

     private Button makeListCreditsButton() {
        //Salma 
        Button buttonDemandes = new Button("Mes Credits");
        buttonDemandes.setUIID("buttonMenu");
        buttonDemandes.setUIID("buttonWhiteCenter");
        Style listStyle = new Style(buttonDemandes.getUnselectedStyle());
        listStyle.setFgColor(0x000000);

        FontImage listImage = FontImage.createMaterial(FontImage.MATERIAL_FORMAT_LIST_BULLETED, listStyle);
        buttonDemandes.setIcon(listImage);
        buttonDemandes.setTextPosition(RIGHT);
        buttonDemandes.addActionListener(action -> new listOffreCredit(this).show());

        return buttonDemandes;
    }

      private Button makeDemandesCreditsButton() {
        //Salma 
        Button buttonDemandes = new Button("Mes Demandes Credits");
        buttonDemandes.setUIID("buttonMenu");
        buttonDemandes.setUIID("buttonWhiteCenter");
        Style listStyle = new Style(buttonDemandes.getUnselectedStyle());
        listStyle.setFgColor(0x000000);

        FontImage listImage = FontImage.createMaterial(FontImage.MATERIAL_FORMAT_LIST_BULLETED, listStyle);
        buttonDemandes.setIcon(listImage);
        buttonDemandes.setTextPosition(RIGHT);
        buttonDemandes.addActionListener(action -> new listDemandesCredits(this).show());

        return buttonDemandes;
    }

    
    
    Button btn = createStatisticsButton();

    private Button createStatisticsButton() {
        Image img = FontImage.createMaterial(FontImage.MATERIAL_ANALYTICS, "MyButton", 40);
        Button btn = new Button(img);        
        btn.addActionListener(action -> new ShowAllStatistiques(this).show());
//        //    
//        Button btn = new Button("statistique Transaction"); 
//        Style listStyle = new Style(btn.getUnselectedStyle());
//        listStyle.setFgColor(0x000000);
//        FontImage listImage = FontImage.createMaterial(FontImage.MATERIAL_FORMAT_LIST_BULLETED, listStyle);
//        
//        btn.setIcon(listImage);
//        btn.setTextPosition(RIGHT);
//        
//        btn.addActionListener(action -> new ShowAllStatistiques(this).show());
        return btn;
    }

    private Button StatistiqueTransaction() {
        Button button = new Button("Statistique Transaction");
        
        button.setUIID("buttonMenu");
        button.setUIID("buttonWhiteCenter");
        Style listStyle = new Style(button.getUnselectedStyle());
        listStyle.setFgColor(0x000000);

        FontImage listImage = FontImage.createMaterial(FontImage.MATERIAL_SHOW_CHART, listStyle);
        button.setIcon(listImage);
        button.setTextPosition(RIGHT);
        
        button.addActionListener(action -> new ShowAllStatistiques(this).show());
        return button;
    }
    
    
    private Button makeStatistiqueDemandeCarte() {
        Button button2 = new Button("Statistiques Carte");
        //button.setUIID("buttonMenu");
        button2.setUIID("buttonWhiteCenter");
        Style listStyle = new Style(button2.getUnselectedStyle());
        listStyle.setFgColor(0x000000);

        FontImage listImage = FontImage.createMaterial(FontImage.MATERIAL_SHOW_CHART, listStyle);
        button2.setIcon(listImage);
        button2.setTextPosition(RIGHT);
        button2.addActionListener(action -> new ShowAllStatistiquesDemandeCarte(this).show());
       
        return button2;
    }
    
    
    //hama
    private Button makeOffreAssurancesButton() {
        Button button = new Button("Offre Assurances");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.setUIID("buttonWhiteCenter");
        Style listStyle = new Style(button.getUnselectedStyle());
        listStyle.setFgColor(0x000000);
        FontImage listImage = FontImage.createMaterial(FontImage.MATERIAL_FORMAT_LIST_BULLETED, listStyle);
        button.setIcon(listImage);
        button.setTextPosition(RIGHT);
       // button.setMaterialIcon(FontImage.MATERIAL_PAID);
        button.addActionListener(action -> new AfficherToutOffreAssurance(this).show());
        return button;
    }
    private Button makeOffreEmbauchesButton() {
        Button button = new Button("Offre Embauches");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
             button.setUIID("buttonWhiteCenter");
        Style listStyle = new Style(button.getUnselectedStyle());
        listStyle.setFgColor(0x000000);
        FontImage listImage = FontImage.createMaterial(FontImage.MATERIAL_FORMAT_LIST_BULLETED, listStyle);
        button.setIcon(listImage);
        button.setTextPosition(RIGHT);
       // button.setMaterialIcon(FontImage.MATERIAL_PAID);
        button.addActionListener(action -> new AfficherToutOffreEmbauche(this).show());
        return button;
    }
    private Button makeOffreInvestissementsButton() {
        Button button = new Button("Offre Investissements");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
             button.setUIID("buttonWhiteCenter");
        Style listStyle = new Style(button.getUnselectedStyle());
        listStyle.setFgColor(0x000000);
        FontImage listImage = FontImage.createMaterial(FontImage.MATERIAL_FORMAT_LIST_BULLETED, listStyle);
        button.setIcon(listImage);
        button.setTextPosition(RIGHT);
     //   button.setMaterialIcon(FontImage.MATERIAL_PAID);
        button.addActionListener(action -> new AfficherToutOffreInvestissement(this).show());
        return button;
    }
}
