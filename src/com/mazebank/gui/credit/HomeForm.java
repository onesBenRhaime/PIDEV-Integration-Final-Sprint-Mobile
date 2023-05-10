package com.mazebank.gui.credit;

import com.codename1.ui.Button;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.FontImage;

public class HomeForm extends Form {

    public HomeForm() {

        setTitle("Home");
        setLayout(new BorderLayout());
        getStyle().setBgColor(0x000000);

        // Create a label and add it to the top of the form
        Label titleLabel = new Label("Maze Bank");
        add(BorderLayout.NORTH, titleLabel);

        // Create a container for the buttons and add it to the center of the form
        Form buttonContainer = new Form(new FlowLayout());
        Button btnListCredits = new Button("List Credits");
        btnListCredits.setIcon(FontImage.createMaterial(FontImage.MATERIAL_LIST, btnListCredits.getStyle()));
        Button btnListDemandes = new Button("List Demandes Credits");
        btnListDemandes.setIcon(FontImage.createMaterial(FontImage.MATERIAL_LIST, btnListDemandes.getStyle()));
        btnListCredits.addActionListener(e -> new listOffreCredit(this).show());
        btnListDemandes.addActionListener(e -> new listDemandesCredits(this).show());
        buttonContainer.addAll(btnListCredits, btnListDemandes);
        add(BorderLayout.CENTER, buttonContainer);

        // Set the style of the label and buttons
        titleLabel.getAllStyles().setFgColor(0xffffff);
        titleLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        btnListCredits.getAllStyles().setBgColor(0x000000);
        btnListCredits.getAllStyles().setFgColor(0x000000);
        btnListCredits.getAllStyles().setPadding(TOP, 5);
        btnListCredits.getAllStyles().setPadding(BOTTOM, 5);
        btnListCredits.getAllStyles().setPadding(LEFT, 5);
        btnListCredits.getAllStyles().setPadding(RIGHT, 5);
        btnListDemandes.getAllStyles().setBgColor(0x000000);
        btnListDemandes.getAllStyles().setFgColor(0x000000);
        btnListDemandes.getAllStyles().setPadding(TOP, 5);
        btnListDemandes.getAllStyles().setPadding(BOTTOM, 5);
        btnListDemandes.getAllStyles().setPadding(LEFT, 5);
        btnListDemandes.getAllStyles().setPadding(RIGHT, 5);
    }

}
