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
import com.mycompany.myapp.entities.Echanges;
import com.mycompany.myapp.services.ServiceEchanges;
/**
 *
 * @author amine
 */
public class addEchangeForm extends Form{
    public addEchangeForm(Form previous) {
        setTitle("ajouter un nouveau echange");
        setLayout(BoxLayout.y());
        TextField tfpe = new TextField("","produit a echanger ");
        TextField tfpo = new TextField("","produit offert ");
        TextField tfst = new TextField("","statut ");
        TextField tfcm = new TextField("","commentaire ");
        Button btnValider = new Button("Ajouter ");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
               int pe = Integer.parseInt(tfpe.getText().toString());
               int po = Integer.parseInt(tfpo.getText().toString());
                if ((tfcm.getText().length()==0)&&(tfst.getText().length()==0))
                    Dialog.show("Alert", "VALIDER TOUT LES CHAMPS", new Command("OK"));
                else
                {
                    try {
                       
                        Echanges e = new Echanges(pe,po,tfst.getText().toString(),tfcm.getText().toString());
                        if( ServiceEchanges.getInstance().addEchanges(e))
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
        
        addAll(tfpe,tfpo,tfst,tfcm,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
