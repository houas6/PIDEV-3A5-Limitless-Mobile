/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.services.ServiceCategorie;
import java.util.List;

/**
 *
 * @author USER
 */
public class catSerach extends Form {
     Form current;
    public  catSerach(Form previous) {
        current = this;
        
       
      ServiceCategorie sp = new ServiceCategorie();
      //La méthode new InfiniteProgress() crée une nouvelle instance de la classe 
      add(new InfiniteProgress());
                Display.getInstance().scheduleBackgroundTask(()-> {
                  //La classe Display est utilisée pour obtenir des informations sur l'écran  
                    Display.getInstance().callSerially(() -> {
                           
                        removeAll();
                      setLayout(BoxLayout.y());
                     Button searchButton = new Button();
FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, "Search Icon", 4);
searchButton.setIcon(searchIcon);
       
         
       add(searchButton);

       
       
             
      

                      
                      
                      
                      
                      
                
                      
                       
        Button refreshButton = new Button();
FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_REFRESH, UIManager.getInstance().getComponentStyle("Button"));
refreshButton.setIcon(icon1);

       
       refreshButton.addActionListener(e-> new Listcategorie(previous).show());
        add(refreshButton);

                        List<Categorie> listecat = sp.affichageCategorie();
                       
                            
                            
                   for (Categorie c : listecat) {
    MultiButton m = new MultiButton();
    
    m.setTextLine1("Nom: " + c.getNomcategorie());
   
    
    add(m);
                   }
                   
revalidate() ;   
                    });
                });
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
       
       
       
    
                        }
}
