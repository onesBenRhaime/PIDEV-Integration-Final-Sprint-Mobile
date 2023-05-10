package com.mazebank.gui.offre;

import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.mazebank.entities.OffreAssurance;
import com.mazebank.services.OffreAssuranceService;

import java.util.*;

public class AfficherToutOffreAssurance extends Form {

    Form previous; 
    
    public static OffreAssurance currentOffreAssurance = null;

    TextField searchTF;
    ArrayList<Component> componentModels;
    

    public AfficherToutOffreAssurance(Form previous) {
        super("OffreAssurances", new BoxLayout(BoxLayout.Y_AXIS));
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
        

        ArrayList<OffreAssurance> listOffreAssurances = OffreAssuranceService.getInstance().getAll();
        componentModels = new ArrayList<>();
        
        searchTF = new TextField("", "Chercher offreAssurance par Libelle");
        searchTF.addDataChangedListener((d, t) -> {
            if (componentModels.size() > 0) {
                for (Component componentModel : componentModels) {
                    this.removeComponent(componentModel);
                }
            }
            componentModels = new ArrayList<>();
            for (OffreAssurance offreAssurance : listOffreAssurances) {
                if (offreAssurance.getLibelle().toLowerCase().startsWith(searchTF.getText().toLowerCase())) {
                    Component model = makeOffreAssuranceModel(offreAssurance);
                    this.add(model);
                    componentModels.add(model);
                }
            }
            this.revalidate();
        });
        this.add(searchTF);
        
        
        if (listOffreAssurances.size() > 0) {
            for (OffreAssurance offreAssurance : listOffreAssurances) {
                Component model = makeOffreAssuranceModel(offreAssurance);
                this.add(model);
                componentModels.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }
    Label libelleLabel   , imageLabel   , partenaireLabel   , typeLabel  ;
    

    private Container makeModelWithoutButtons(OffreAssurance offreAssurance) {
        Container offreAssuranceModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        offreAssuranceModel.setUIID("containerRounded");
        
        
        libelleLabel = new Label("Libelle : " + offreAssurance.getLibelle());
        libelleLabel.setUIID("labelDefault");
        
        imageLabel = new Label("Image : " + offreAssurance.getImage());
        imageLabel.setUIID("labelDefault");
        
        partenaireLabel = new Label("Partenaire : " + offreAssurance.getPartenaire());
        partenaireLabel.setUIID("labelDefault");
        
        typeLabel = new Label("Type : " + offreAssurance.getType());
        typeLabel.setUIID("labelDefault");
        

        offreAssuranceModel.addAll(
                
                libelleLabel, imageLabel, partenaireLabel, typeLabel
        );

        return offreAssuranceModel;
    }
    

    private Component makeOffreAssuranceModel(OffreAssurance offreAssurance) {

        Container offreAssuranceModel = makeModelWithoutButtons(offreAssurance);

        return offreAssuranceModel;
    }
    
}