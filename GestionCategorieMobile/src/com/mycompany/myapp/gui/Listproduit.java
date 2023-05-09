/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
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
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;


import com.mycompany.myapp.entities.Produit;


import com.mycompany.myapp.services.ServiceProduit;
import java.util.ArrayList;
import java.util.List;

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


       
                       
        
                        


