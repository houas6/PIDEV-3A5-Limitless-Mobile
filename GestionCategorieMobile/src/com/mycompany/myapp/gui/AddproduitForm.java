/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Form;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ServiceProduit;
/**
 *
 * @author USER
 */
public class AddproduitForm extends Form {
     public AddproduitForm(Form previous) {
        setTitle("Add a new Product");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","Nom ");
        TextField tfDesc = new TextField("","Description");
        TextField tfPrix = new TextField("","Prix",4,TextField.NUMERIC);
        TextField tfImage = new TextField("","Image");
        TextField tfCategorie = new TextField("","Catégorie");
        TextField tfIdUser = new TextField("", "Id User", 4, TextField.NUMERIC);
        
        Button btnValider = new Button("Add ");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0) || (tfDesc.getText().length()==0) || (tfPrix.getText().length()==0) || (tfCategorie.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                       
                        Produit p = new Produit(Integer.parseInt(tfIdUser.getText()),Integer.parseInt(tfCategorie.getText()),tfName.getText().toString(),Float.parseFloat(tfPrix.getText()),tfDesc.getText().toString(),tfImage.getText().toString());
                        if( ServiceProduit.getInstance().addProduit(p))
                        {
                           Dialog.show("Success","Ajouter avec succée ",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfName,tfDesc,tfPrix,tfImage,tfCategorie, tfIdUser, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}

