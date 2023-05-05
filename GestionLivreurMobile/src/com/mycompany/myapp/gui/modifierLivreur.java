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
import com.mycompany.myapp.entities.Livreur;
import com.mycompany.myapp.services.ServiceLivreur;
/**
 *
 * @author amine
 */
public class modifierLivreur extends Form{
    public modifierLivreur(Form previous) {
        setTitle("Modifier Livreur");
        setLayout(BoxLayout.y());
        
        TextField idLivreur = new TextField("","id Livreur");
        TextField tfnom = new TextField("","nom");
        TextField tfmail = new TextField("","mail");
        TextField tftel = new TextField("","telephone");
        Button btnValider = new Button("Modifier ");
          
         
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom.getText().length()==0)&&(tfmail.getText().length()==0)&&(tftel.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                   try {
                       int livreur = Integer.parseInt(idLivreur.getText().toString());
                       int telephone = Integer.parseInt(tftel.getText().toString());
                        Livreur liv;
                        liv = new Livreur((int)livreur,tfnom.getText().toString(),tfmail.getText().toString(),(int) telephone);
                        if( ServiceLivreur.getInstance().modifierLivreur(liv))
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
        
        addAll(idLivreur,tfnom,tfmail,tftel,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
             
    }
}
