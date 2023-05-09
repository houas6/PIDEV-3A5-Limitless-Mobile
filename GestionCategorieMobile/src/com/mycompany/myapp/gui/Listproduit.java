/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;


import com.mycompany.myapp.entities.Produit;


import com.mycompany.myapp.services.ServiceProduit;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Listproduit extends Form {

    
Resources res;
    public Listproduit(Form previous) {
        
       ToastBar.showInfoMessage("Welcome");
      
        setTitle("List Products");

        SpanLabel sp = new SpanLabel();
       
        //sp.setText(ServiceUser.getInstance().getAllUsers().toString);
        
         ArrayList<Produit> list;
        list = new ArrayList<>();
        list = ServiceProduit.getInstance().getproduit();
         for ( Produit ev : list) {
             
             
             //Image im = MyApplication.theme.getImage(ev.getImage());
            SpanLabel spl = new SpanLabel("User: " + "  " + ev.getId_user() + "  " + "Categorie: " + "  " + ev.getIdcategorie() + "   Nom: " + " " + ev.getNom_produit() + " prix: " + ev.getPrix() + "   Description: " + "  " + ev.getDescription() );
String image=ev.getImage();
                          
                                    
               
           



      
          addAll(spl);
          

              
        
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        
    }
}


       
                       
        
                        


