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
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ServiceProduit;
/**
 *
 * @author USER
 */
public class modifierProduit extends Form {
    public modifierProduit(Form previous) {
        setTitle("Modifier Produit");
        setLayout(BoxLayout.y());

        TextField idProduit = new TextField("", "id produit");
        TextField tfNom = new TextField("", "nom");
        TextField tfDesc = new TextField("", "description");
        TextField tfPrix = new TextField("", "prix");
        TextField tfImage = new TextField("", "image");
        TextField tfCategorie = new TextField("", "catégorie");
        TextField tfuser = new TextField("", "user");

        Button btnValider = new Button("Modifier ");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((idProduit.getText().length() == 0) || (tfNom.getText().length() == 0) || (tfDesc.getText().length() == 0) || (tfPrix.getText().length() == 0) || (tfCategorie.getText().length() == 0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else {
                    try {
                        Produit p = new Produit(Integer.parseInt(idProduit.getText()), Integer.parseInt(tfuser.getText()), Integer.parseInt(tfCategorie.getText()), tfNom.getText(), Float.parseFloat(tfPrix.getText()), tfDesc.getText(), tfImage.getText());
                        if (ServiceProduit.getInstance().modifierProduit(p)) {
                            Dialog.show("Success", "Modifier avec succée ", new Command("OK"));
                        } else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }
            }
        });

        addAll(idProduit,tfuser , tfCategorie ,tfNom,  tfPrix, tfDesc,tfImage, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}

