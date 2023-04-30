/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
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
import com.mycompany.myapp.entities.Livreur;
import com.mycompany.myapp.services.ServiceLivreur;
/**
 *
 * @author amine
 */
public class addlivreurForm extends Form{
     public addlivreurForm(Form previous) {
        setTitle("ajouter un nouveau livreur");
        setLayout(BoxLayout.y());
        TextField tfName = new TextField("","Nom ");
        TextField tfmail = new TextField("","Mail ");
        TextField tftelephone = new TextField("","Telephone ");
        Button btnValider = new Button("Ajouter ");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int telephone = 0;
                telephone = Integer.parseInt(tftelephone.getText().toString());
                if ((tfName.getText().length()==0)&&(tfmail.getText().length()==0))
                    Dialog.show("Alert", "VALIDER TOUT LES CHAMPS", new Command("OK"));
                else
                {
                    try {
                       
                        Livreur l = new Livreur(tfName.getText().toString(),tfmail.getText().toString(),telephone);
                        if( ServiceLivreur.getInstance().addLivreur(l))
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
        
        addAll(tfName,tfmail,tftelephone,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
