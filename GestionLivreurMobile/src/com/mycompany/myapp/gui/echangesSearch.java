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
import com.mycompany.myapp.entities.Echanges;
import com.mycompany.myapp.services.ServiceEchanges;
import java.util.List;
/**
 *
 * @author amine
 */
public class echangesSearch extends Form{
    Form current;
    public  echangesSearch(Form previous) {
        current = this;
        
       
      ServiceEchanges sp = new ServiceEchanges();
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

        refreshButton.addActionListener(e-> new ListLivreur(previous).show());
        add(refreshButton);
                   List<Echanges> listeech = sp.affichageEchanges();
                   for (Echanges e : listeech) {
                        MultiButton m = new MultiButton();
                        m.setTextLine1("statut: " + e.getStatut());
                        
                        add(m);
                   }
                   
revalidate() ;   
                    });
                });
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack()); 
                        }
}
