package com.mazebank.gui.demande;

import com.codename1.components.InteractionDialog;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mazebank.entities.CarteBancaire;
import com.mazebank.services.CarteBancaireService;
import java.util.ArrayList;

public class ShowAllStatistiquesDemandeCarte  extends Form {

    Form previous; 
    
    public static CarteBancaire currentDemande = null;

    public ShowAllStatistiquesDemandeCarte(Form previous) {
        super("Statistiques", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        addGUIs();
        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();        
        this.refreshTheme();
    }

    private void addGUIs() {
        int nb = CarteBancaireService.getInstance().getStat();

        if (nb!=0) {
                Component model = makeDemandeModel(nb);
                this.add(model);
       
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }
    // declarer lina bich injamo in 3ytoulhom fil model
    Label  lstat,lstatS;
    
    

    private Container makeModelWithoutButtons(int nb) {
        Container DemandeModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       DemandeModel.setUIID("containerRounded");


lstat = new Label("Nbre Demande: " + nb);
lstat.getAllStyles().setFgColor(0x0000FF); // Changer la couleur du texte
lstat.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM)); // Changer la police
lstat.getAllStyles().setPadding(2, 2, 2, 2); // Changer la marge intérieure
lstat.getAllStyles().setAlignment(CENTER); // Changer l'alignement
lstat.setUIID("labelDefault");
        
        DemandeModel.addAll(                
           lstat
        );

        return DemandeModel;
    }
    
    private Component makeDemandeModel(int nb) {

        Container DemandeModel = makeModelWithoutButtons(nb);


        return DemandeModel;
    }

    
}