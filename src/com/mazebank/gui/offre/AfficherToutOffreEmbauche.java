package com.mazebank.gui.offre;

import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.util.*;
import com.codename1.io.*;
import com.mazebank.entities.OffreEmbauche;
import com.mazebank.services.OffreEmbaucheService;
import com.mazebank.utils.Statics;

import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

public class AfficherToutOffreEmbauche extends Form {

    Form previous; 
    
    public static OffreEmbauche currentOffreEmbauche = null;

    
    PickerComponent sortPicker;
    ArrayList<Component> componentModels;

    public AfficherToutOffreEmbauche(Form previous) {
        super("OffreEmbauches", new BoxLayout(BoxLayout.Y_AXIS));
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
        

        ArrayList<OffreEmbauche> listOffreEmbauches = OffreEmbaucheService.getInstance().getAll();
        
        componentModels = new ArrayList<>();
        
        sortPicker = PickerComponent.createStrings("Poste", "DateEmbauche", "Salaire", "Duree").label("Trier par");
        sortPicker.getPicker().setSelectedString("");
        sortPicker.getPicker().addActionListener((l) -> {
            if (componentModels.size() > 0) {
                for (Component componentModel : componentModels) {
                    this.removeComponent(componentModel);
                }
            }
            componentModels = new ArrayList<>();
            Statics.compareVar = sortPicker.getPicker().getSelectedString();
            Collections.sort(listOffreEmbauches);
            for (OffreEmbauche offreEmbauche : listOffreEmbauches) {
                Component model = makeOffreEmbaucheModel(offreEmbauche);
                this.add(model);
                componentModels.add(model);
            }
            this.revalidate();
        });
        this.add(sortPicker);
        
        if (listOffreEmbauches.size() > 0) {
            for (OffreEmbauche offreEmbauche : listOffreEmbauches) {
                Component model = makeOffreEmbaucheModel(offreEmbauche);
                this.add(model);
                componentModels.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }
    Label posteLabel   , dateEmbaucheLabel   , salaireLabel   , dureeLabel  ;
    

    private Container makeModelWithoutButtons(OffreEmbauche offreEmbauche) {
        Container offreEmbaucheModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        offreEmbaucheModel.setUIID("containerRounded");
        
        
        posteLabel = new Label("Poste : " + offreEmbauche.getPoste());
        posteLabel.setUIID("labelDefault");
        
        dateEmbaucheLabel = new Label("DateEmbauche : " + new SimpleDateFormat("dd-MM-yyyy").format(offreEmbauche.getDateEmbauche()));
        dateEmbaucheLabel.setUIID("labelDefault");
        
        salaireLabel = new Label("Salaire : " + offreEmbauche.getSalaire());
        salaireLabel.setUIID("labelDefault");
        
        dureeLabel = new Label("Duree : " + offreEmbauche.getDuree());
        dureeLabel.setUIID("labelDefault");
        

        offreEmbaucheModel.addAll(
                
                posteLabel, dateEmbaucheLabel, salaireLabel, dureeLabel
        );

        return offreEmbaucheModel;
    }
    
    Container btnsContainer;

    private Component makeOffreEmbaucheModel(OffreEmbauche offreEmbauche) {

        Container offreEmbaucheModel = makeModelWithoutButtons(offreEmbauche);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");
        
        
        
        Button btnAfficherScreenshot = new Button("Partager");
        btnAfficherScreenshot.addActionListener(listener -> share(offreEmbauche));

        btnsContainer.add(BorderLayout.CENTER, btnAfficherScreenshot);
        
        offreEmbaucheModel.add(btnsContainer);

        return offreEmbaucheModel;
    }
    
    private void share(OffreEmbauche offreEmbauche) {
        Form form = new Form(new BoxLayout(BoxLayout.Y_AXIS));
        form.add(makeModelWithoutButtons(offreEmbauche));
        String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
        Image screenshot = Image.createImage(
                com.codename1.ui.Display.getInstance().getDisplayWidth(),
                com.codename1.ui.Display.getInstance().getDisplayHeight()
        );
        form.revalidate();
        form.setVisible(true);
        form.paintComponent(screenshot.getGraphics(), true);
        form.removeAll();
        try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
            ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
        } catch (IOException err) {
            Log.e(err);
        }
        Form screenShotForm = new Form("Partager offreEmbauche", new BoxLayout(BoxLayout.Y_AXIS));
        ImageViewer screenshotViewer = new ImageViewer(screenshot.fill(1000, 2000));
        screenshotViewer.setFocusable(false);
        screenshotViewer.setUIID("screenshot");
        ShareButton btnPartager = new ShareButton();
        btnPartager.setText("Partager ");
        btnPartager.setTextPosition(LEFT);
        btnPartager.setImageToShare(imageFile, "image/png");
        btnPartager.setTextToShare(offreEmbauche.toString());
        screenShotForm.addAll(screenshotViewer, btnPartager);
        screenShotForm.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        screenShotForm.show();
    }
    
}