/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.services.ServiceCategorie;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class Listcategorie extends Form {
    
    public Listcategorie(Form previous) {
        ServiceCategorie sp = new ServiceCategorie();
        add(new InfiniteProgress());
        
        Display.getInstance().scheduleBackgroundTask(() -> {
            
            Display.getInstance().callSerially(() -> {
                
                removeAll();
                setLayout(BoxLayout.y());
                
                /*Button searchButton = new Button();
                FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, "Search Icon", 4);
                searchButton.setIcon(searchIcon);
                add(searchButton);
                */
                ArrayList<Categorie> list1;
                list1 = ServiceCategorie.getInstance().affichageCategorie();
                
                for (Categorie p : list1) {
                    MultiButton m = new MultiButton();
                    m.setTextLine1("Nom:" + p.getNomcategorie());
                    add(m);
                }
                
                revalidate();
                
            });
            
        });
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        
    }
                }
