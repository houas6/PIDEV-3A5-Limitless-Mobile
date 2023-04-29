/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableModel;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.services.ServiceCategorie;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JScrollPane;

/**
 *
 * @author USER
 */
public class Listcategorie extends Form {
   public Listcategorie(Form previous) {
ServiceCategorie sp = new ServiceCategorie();
      
        ArrayList<Categorie> list1;
        list1 = ServiceCategorie.getInstance().affichageCategorie();
        
                        
          
for(Categorie cat : list1){
    Label b = new Label(cat.getNomcategorie());
   
    
    add(b);
    
    b.addPointerPressedListener(e -> {

        if (cat.getType() == b.getText())
        {
            new  catSerach(previous).show();
        }
        
});
}


       
       *

                        List<Categorie> listecat = sp.affichageCategory();
                        for(Categorie p : listecat)
                        {
                            
                            MultiButton m = new MultiButton();
                            
                            m.setTextLine1("Type:"+p.getType());
                            
                            
                            add(m);
Button btnModifier = new Button();
FontImage.setMaterialIcon(btnModifier, FontImage.MATERIAL_EDIT);
m.addComponent(BorderLayout.SOUTH, btnModifier);
btnModifier.addActionListener(e -> {
    new modifiercategorie(this).show();
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
        if (ServiceCategorie.getInstance().deletecategorie(p.getId())) {
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