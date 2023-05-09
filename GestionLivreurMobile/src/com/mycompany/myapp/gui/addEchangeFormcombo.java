/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Echanges;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ServiceEchanges;
import com.mycompany.myapp.services.ServiceProduit;
import java.util.ArrayList;
/**
 *
 * @author amine
 */
public class addEchangeFormcombo extends Form{
    public addEchangeFormcombo(Form previous) {
    setTitle("Ajouter un nouveau échange");
    setLayout(BoxLayout.y());
    
    // Créer les champs de texte et la liste déroulante
    ComboBox<String> cbProduitEchange = new ComboBox<>();
    ComboBox<String> cbProduitOffert = new ComboBox<>();
    TextField tfSt = new TextField("En cours", "Statut");
    TextField tfCm = new TextField("", "Commentaire");
    Button btnValider = new Button("Ajouter");
    tfSt.setEditable(false);
    // Récupérer les produits disponibles à partir de la base de données
    ArrayList<Produit> produits = ServiceProduit.getInstance().affichageProduit();
    for (Produit p : produits) {
        cbProduitEchange.addItem(String.valueOf(p.getId_produit()));
        cbProduitOffert.addItem(String.valueOf(p.getId_produit()));
    }

    btnValider.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {

            int pe = Integer.parseInt(cbProduitEchange.getSelectedItem());
            int po = Integer.parseInt(cbProduitOffert.getSelectedItem());
            String st = tfSt.getText().toString();
            String cm = tfCm.getText().toString();

            if (/*pe == null || po == null ||*/ st.length() == 0) {
                Dialog.show("Alert", "Veuillez remplir tous les champs", new Command("OK"));
            } else {
                try {
                    Echanges e = new Echanges(pe, po, st, cm);
                    if (ServiceEchanges.getInstance().addEchanges(e)) {
                        Dialog.show("Success", "Ajouté avec succès", new Command("OK"));
                    } else {
                        Dialog.show("Error", "Erreur lors de l'ajout", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("Error", "Le statut doit être un nombre", new Command("OK"));
                }
            }
        }
    });

    addAll(cbProduitEchange, cbProduitOffert, tfSt, tfCm, btnValider);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
}
}
