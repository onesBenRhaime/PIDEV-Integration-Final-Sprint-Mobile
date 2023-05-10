package com.mazebank.gui.transaction;

import com.codename1.components.InteractionDialog;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mazebank.entities.Transaction;
import com.mazebank.services.TransactionService;
import java.util.ArrayList;

public class ShowAllStatistiques  extends Form {

    Form previous; 
    
    public static Transaction currentReclamation = null;

    public ShowAllStatistiques(Form previous) {
        super("Transactions Statistiques", new BoxLayout(BoxLayout.Y_AXIS));
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
        int nb = TransactionService.getInstance().getStat();

        if (nb!=0) {
                Component model = makeReclamationModel(nb);
                this.add(model);
       
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }
    // declarer lina bich injamo in 3ytoulhom fil model
    Label  lstat,lstatS;    
    

    private Container makeModelWithoutButtons(int nb) {
        Container reclamationModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));        
        reclamationModel.setUIID("containerRounded");   
              Button btn = new Button("");
              Style editStyle = new Style(btn.getUnselectedStyle());
              editStyle.setFgColor(0xf21f1f);
        
              FontImage chartIcon =  FontImage.createMaterial(FontImage.MATERIAL_SHOW_CHART, editStyle,40);
              btn.setIcon(chartIcon);
                    
              lstat = new Label("" + nb);
              
             
              lstat.setUIID("labelDefault");        
              reclamationModel.addAll(                
              lstat,btn
              );

        return reclamationModel;
    }
    
    private Component makeReclamationModel(int nb) {

        Container reclamationModel = makeModelWithoutButtons(nb);


        return reclamationModel;
    }

    
}