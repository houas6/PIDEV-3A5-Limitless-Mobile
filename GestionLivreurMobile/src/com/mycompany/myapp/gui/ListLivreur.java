/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Livreur;
import com.mycompany.myapp.services.ServiceLivreur;
import java.util.ArrayList;
import java.util.List;
import static jdk.nashorn.internal.objects.NativeArray.map;
import static jdk.nashorn.internal.objects.NativeDebug.map;
/**
 *
 * @author amine
 */
public class ListLivreur extends Form {
    public ListLivreur(Form previous) {
ServiceLivreur sp = new ServiceLivreur();
      add(new InfiniteProgress());
                Display.getInstance().scheduleBackgroundTask(()-> {           
                    Display.getInstance().callSerially(() -> {                        
                        removeAll();
                      setLayout(BoxLayout.y());
                     Button searchButton = new Button();
FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, "Search Icon", 4);
searchButton.setIcon(searchIcon);
       
         
        // back = Image.createImage("/logo.png");
         //back.scaled(1000, 1000);
        add(searchButton);
        Style s = UIManager.getInstance().getComponentStyle("Contenu");
        Form hi = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
        searchButton.addActionListener(e-> { hi.show();});
        Button gui_Button_12 = new Button();
        gui_Button_12.setText("search");
        gui_Button_12.setUIID("Label");
        gui_Button_12.setName("Button_12");
        FontImage.setMaterialIcon(gui_Button_12, FontImage.MATERIAL_CIRCLE);
        TextField searchField = new TextField("", "Toolbar Search"); // <1>
        searchField.getHintLabel().setUIID("Title");
        searchField.setUIID("Title");
        searchField.getAllStyles().setAlignment(Component.LEFT);
        hi.getToolbar().setTitleComponent(searchField);
       // FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
        ArrayList<Livreur> list1;
        list1 = ServiceLivreur.getInstance().affichageLivreur();
        //hi.add(gui_Button_12);
            searchField.addDataChangeListener((i1, i2) -> { // <2>
            String t = searchField.getText();
            if(t.length() < 1) {
                for(Component cmp : hi.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
            } else {
                t = t.toLowerCase();
                for(Component cmp : hi.getContentPane()) {
                    String val = null;
                    //hi.add(gui_Button_12);
                    if(cmp instanceof Label) {
                        val = ((Label)cmp).getText();
                    } else {
                        if(cmp instanceof TextArea) {
                            val = ((TextArea)cmp).getText();
                        } else {
                            val = (String)cmp.getPropertyValue("text");
                        }
                    }
                    boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
                    cmp.setHidden(!show); // <3>
                    cmp.setVisible(show);
                    //hi.add(gui_Button_12);
                }
            }
            hi.getContentPane().animateLayout(250);
          //  hi.add(gui_Button_12);
        });
        hi.getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
            searchField.startEditingAsync(); // <4>
        //    hi.add(gui_Button_12);
        });

for(Livreur liv : list1){
    Label b = new Label(liv.getNom());
    Label b2 = new Label(liv.getMail());
    Label b3 = new Label(String.valueOf(liv.getTelephone()));

    hi.add(b);
    
    b.addPointerPressedListener(e -> {

        if (liv.getNom() == b.getText())
        {
           new  livreurSearch(previous).show();
        }
        
     });
}
/*Button mapButton = new Button("Afficher la carte");
mapButton.addActionListener(e -> {
    map mapForm = new map();
  mapForm.show();
});
add(mapButton);*/
        Button refreshButton = new Button();
FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_REFRESH, UIManager.getInstance().getComponentStyle("Button"));
refreshButton.setIcon(icon1);

       
       refreshButton.addActionListener(e-> new ListLivreur(previous).show());
        add(refreshButton);

                        List<Livreur> listeliv = sp.affichageLivreur();
                        for(Livreur liv : listeliv)
                        {     
                            MultiButton m = new MultiButton();                            
                            m.setTextLine1("NomLivreur:"+liv.getNom()); 
                           
                            add(m);
        Button btnModifier = new Button();
        FontImage.setMaterialIcon(btnModifier, FontImage.MATERIAL_EDIT);
        m.addComponent(BorderLayout.SOUTH, btnModifier);
        btnModifier.addActionListener(e -> {
        new modifierLivreur(this).show();
        });

        Button btnSupprimer = new Button();
        Image icon = FontImage.createMaterial(FontImage.MATERIAL_DELETE, "ButtonIcon", 5);
        btnSupprimer.setIcon(icon);
        m.addComponent(BorderLayout.WEST, btnSupprimer);
        btnSupprimer.addActionListener(e -> {
            Dialog dig = new Dialog("Suppression");
    if (dig.show("Suppression", "Êtes-vous sûr de vouloir supprimer cet élément ?", "Annuler", "Oui")) {
        dig.dispose();
    } else {
        dig.dispose();
        if (ServiceLivreur.getInstance().deleteLivreur(liv.getID_livreur())) {
            // Élément supprimé avec succès
        }
    }
});
                        }
                       
                        
                             revalidate() ;   
                      });
                });
               
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
       
       
          
                        
                    } 
}
