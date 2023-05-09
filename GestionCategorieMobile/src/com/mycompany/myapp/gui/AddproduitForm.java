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
        TextField tfIdUser = new TextField("", "Id User", 4, TextField.NUMERIC);
        TextField tfCategorie = new TextField("","idcategorie");
        TextField tfName = new TextField("","Nom ");
        TextField tfPrix = new TextField("","Prix",4,TextField.NUMERIC);
        TextField tfDesc = new TextField("","Description");
        
        TextField tfImage = new TextField("","Image");
        
        
        
        Button btnValider = new Button("Add ");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0) || (tfDesc.getText().length()==0) || (tfPrix.getText().length()==0) || (tfCategorie.getText().length()==0)|| (tfIdUser.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                       
                        Produit p = new Produit(Integer.parseInt(tfIdUser.getText()),Integer.parseInt(tfCategorie.getText()),tfName.getText(),Float.parseFloat(tfPrix.getText()),tfDesc.getText(),tfImage.getText());
                        
                        if( ServiceProduit.getInstance().addProduit(p))
                        {
                           Dialog.show("Success","Ajouter avec succée ",new Command("OK"));
                           ServiceProduit.getInstance().sendSms("+21651092218", "Un produit a été ajoutée.");
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfIdUser,tfCategorie,tfName,tfPrix,tfDesc,tfImage, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}

