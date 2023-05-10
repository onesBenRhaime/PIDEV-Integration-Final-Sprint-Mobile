package com.mazebank.gui.offre;

import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.mazebank.entities.OffreInvestissement;
import com.mazebank.services.OffreInvestissementService;

import java.util.*;

public class AfficherToutOffreInvestissement extends Form {

    Form previous; 
    
    public static OffreInvestissement currentOffreInvestissement = null;

    
    

    public AfficherToutOffreInvestissement(Form previous) {
        super("OffreInvestissements", new BoxLayout(BoxLayout.Y_AXIS));
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

        ArrayList<OffreInvestissement> listOffreInvestissements = OffreInvestissementService.getInstance().getAll();
        
        
        if (listOffreInvestissements.size() > 0) {
            for (OffreInvestissement offreInvestissement : listOffreInvestissements) {
                Component model = makeOffreInvestissementModel(offreInvestissement);
                this.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }
    Label minBudgetLabel  ;
    

    private Container makeModelWithoutButtons(OffreInvestissement offreInvestissement) {
        Container offreInvestissementModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        offreInvestissementModel.setUIID("containerRounded");
        
        
        minBudgetLabel = new Label("MinBudget : " + offreInvestissement.getMinBudget());
        minBudgetLabel.setUIID("labelDefault");
        

        offreInvestissementModel.addAll(
                
                minBudgetLabel
        );

        return offreInvestissementModel;
    }
    

    private Component makeOffreInvestissementModel(OffreInvestissement offreInvestissement) {

        Container offreInvestissementModel = makeModelWithoutButtons(offreInvestissement);

        return offreInvestissementModel;
    }
    
}