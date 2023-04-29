/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.services.ServiceCategorie;

/**
 *
 * @author USER
 */
public class modifiercategorie extends Form {
    public modifiercategorie(Form previous) {
        setTitle("Modifier Categorie");
        setLayout(BoxLayout.y());
        
     TextField idcategorie = new TextField("","id categorie");
        TextField tfnom = new TextField("","nom");
        
        Button btnValider = new Button("Modifier ");
          
         
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                   try {
                       int idcategoriee = Integer.parseInt(idcategorie.getText().toString());
                        Categorie c;
                        c = new Categorie((int) idcategoriee,tfnom.getText().toString());
                        if( ServiceCategorie.getInstance().modifierCategorie(c))
                        {
                           Dialog.show("Success","Modifier avec succée ",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
           }
        });
        
        addAll(idcategorie,tfnom,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
             
    }
}
