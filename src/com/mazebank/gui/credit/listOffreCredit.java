/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazebank.gui.credit;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mazebank.entities.Credit;
import com.mazebank.services.serviceCredit;
import java.util.ArrayList;


/**
 *
 * @author ASUS
 */
public class listOffreCredit extends Form {
  public listOffreCredit(Form previous) {
        setTitle("List of Credits");
        setLayout(BoxLayout.y());

        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
        ArrayList<Credit> credits = serviceCredit.getInstance().getAllCredits();
        for (Credit t : credits) {
            addElement(t);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

   

   public void addElement(Credit credit) {
    Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    container.getStyle().setBgColor(0xFFFFFF); // set background color to white
    container.getStyle().setPadding(0, 0, 5, 0); // add some padding to the bottom

    Label categoryLabel = new Label("Category: " +credit.getCategory().getName());
    categoryLabel.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
    container.add(categoryLabel);

    Label amountLabel = new Label("Amount: " + credit.getMinAmount() + " - " + credit.getMaxAmount() + " DT");
    amountLabel.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
    container.add(amountLabel);

    Label monthlyLabel = new Label("Withdrawals Monthly: " + credit.getWithdrawMonthly());
    monthlyLabel.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
    container.add(monthlyLabel);

    Label monthsLabel = new Label("Months: " + credit.getMonths());
    monthsLabel.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
    container.add(monthsLabel);

    Label rateLabel = new Label("Loan Rate: " + credit.getLoanRate() + " %");
    rateLabel.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
    container.add(rateLabel);

    Button addButton = new Button("Add Demand");
    addButton.getStyle().setBgColor(0x4CAF50); // set background color to green
    addButton.getStyle().setFgColor(0x880808); // set text color to white
    addButton.getStyle().setPadding(3, 3, 3, 3); // add some padding to the button
    addButton.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ADD, addButton.getStyle())); // set the icon to a plus sign
    addButton.addActionListener(evt -> {
        // Open the addDemandeCredit form and pass the credit ID
        Form addDemandeCredit = new addDemandeCredit(this, credit.getId());
        addDemandeCredit.show();
    });
    container.add(addButton);

    add(container);
}

}
