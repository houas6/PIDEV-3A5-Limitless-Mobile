/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Produit;

import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class HomeForm extends Form {
    private static final char ADD_ICON_NAME = FontImage.MATERIAL_ADD_CIRCLE_OUTLINE;
    private static final char LIST_ICON_NAME = FontImage.MATERIAL_LIST_ALT;
    private static final String ADD_BUTTON_TEXT = "Ajouter categorie";
    private static final String LIST_BUTTON_TEXT = "Afficher Les categorie";
    private static final String UPDATE_BUTTON_TEXT = "Modifier categorie";
    private static final String ADD_BUTTON_TEXT1 = "Ajouter Produit";
    private static final String LIST_BUTTON_TEXTp = "Afficher Les produits";
    private static final String UPDATE_BUTTON_TEXTp = "Modifier produit";
    private Resources theme;private Resources res;
    ArrayList<Produit> produits;
    public Resources getTheme() {
        // return the theme you want to use
        return UIManager.initFirstTheme("/theme");
    }
  


    public HomeForm() {

        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button addButton = new Button("");
        addButton.setIcon(FontImage.createMaterial(ADD_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        addButton.setText(ADD_BUTTON_TEXT);
        addButton.addActionListener(e -> new AddcategorieForm(this).show());
        
        Button addButtonp = new Button("");
        addButtonp.setIcon(FontImage.createMaterial(ADD_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        addButtonp.setText(ADD_BUTTON_TEXT1);
        addButtonp.addActionListener(e -> new AddproduitForm(this).show());
        
        Button upButton = new Button("");
        upButton.setIcon(FontImage.createMaterial(ADD_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        upButton.setText(UPDATE_BUTTON_TEXT);
        upButton.addActionListener(e -> new modifiercategorie(this).show());
        
        Button listButton = new Button("");
        listButton.setIcon(FontImage.createMaterial(LIST_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        listButton.setText(LIST_BUTTON_TEXT);
        listButton.addActionListener(e -> new Listcategorie(this).show());
        
        Button listButtonp = new Button("");
    listButtonp.setIcon(FontImage.createMaterial(LIST_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
    listButtonp.setText(LIST_BUTTON_TEXTp);
    listButtonp.addActionListener(e ->new Listproduit(this).show());





        
        Button upButtonp = new Button("");
        upButtonp.setIcon(FontImage.createMaterial(ADD_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        upButtonp.setText(UPDATE_BUTTON_TEXTp);
        upButtonp.addActionListener(e -> new modifierProduit(this).show());
        addAll(addButton,listButton,upButton,addButtonp,listButtonp,upButtonp);
        

    }
    
} 
