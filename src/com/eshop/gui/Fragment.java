package com.eshop.gui;


import com.codename1.charts.views.LineChart;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.mazebank.gui.transaction.ShowAllStatistiques;
//import com.codename1.maps.MapContainer;

public class Fragment extends Container {
    
   public Fragment() {
       Button btn = createStatisticsButton();
      addComponent(btn);
}
   private Button createStatisticsButton() {
    Image img = FontImage.createMaterial(FontImage.MATERIAL_ANALYTICS, "MyButton", 40);
    Button btn = new Button(img);
  //  btn.addActionListener(action -> new ShowAllStatistiques(this).show());
    return btn;
}
   
    
}





