/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.gui.Listproduit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author USER
 */
public class ServiceProduit {
    public static ServiceProduit instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public Produit p;
     public ArrayList<Produit> prod=new ArrayList<>();
    public ArrayList<Produit> produits=new ArrayList<>();

    public ServiceProduit() {
        req = new ConnectionRequest();
    }
    
    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }

    public boolean addProduit(Produit p) {

    String nom_produit = p.getNom_produit();
    String description = p.getDescription();
    float prix = p.getPrix();
    int id_user=p.getId_user();
    int idcategorie = p.getIdcategorie(); // use int instead of Categorie object
     String image=p.getImage();
   
     String url =  "http://127.0.0.1:8000/"+ "produit/AddjsonMp/" + id_user + "/" + idcategorie + "/" + nom_produit + "/"+ prix + "/"+description +"/"+image ;
    
    req.setUrl(url);
    req.setPost(false);
    
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK type ya true ya false
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}
public ArrayList<Produit> getproduit() {
    String url = "http://127.0.0.1:8000/" + "produit/afficheMobp/";
    req.setUrl(url);
    req.setPost(false);

    req.addRequestHeader("accept", "application/json");
   req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {

        byte[] responseData = req.getResponseData();
        if (responseData != null) {
            produits = parseproduit(new String(responseData));
            req.removeResponseListener(this);
//            String response = new String(responseData);
//            System.out.println(response);
        } else {
            System.out.println("Response data is null");
        }
    }
});

          NetworkManager.getInstance().addToQueueAndWait(req);
         
         
         return produits;
}



public ArrayList<Produit> parseproduit(String jsonText) {
         
         Produit p = new Produit();
         
        System.out.println("+++++++++++");
        System.out.println(jsonText);
        System.out.println("++++++++++++");
        try {
           
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) ReclamationsListJson.get("root");
            for (Map<String, Object> obj : list) {

            float Id_produit = Float.parseFloat(obj.get("id").toString());



            float id_user = Float.parseFloat(obj.get("idUser").toString());
            float idcategorie = Float.parseFloat(obj.get("idcategorie").toString());
           String nom_produit = obj.get("nom_produit").toString();
            float prix = Float.parseFloat(obj.get("prix").toString());
            String description = obj.get("description").toString();
            String image = obj.get("image").toString();
             p = new Produit((int)Id_produit, (int)id_user, (int)idcategorie, nom_produit, prix, description, image);

            prod.add(p);
        }
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return prod;
}



    
 /* public ArrayList<Produit> getAllProducts(){
        String url = "http://127.0.0.1:8000/" + "produit/afficheMobp/";
    req.setUrl(url);
    req.setPost(false);
       
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseproduit(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
    }*/


     public boolean modifierProduit(Produit p) {
        String nom_produit = p.getNom_produit();
    String description = p.getDescription();
    float prix = p.getPrix();
   int id_user=p.getId_user();
    int idcategorie = p.getIdcategorie(); // use int instead of Categorie object
     String image=p.getImage();
    String url= "http://127.0.0.1:8000/"+"produit/modifierMp?id_produit=" +p.getId_produit()+"/"+ id_user + "/" + idcategorie + "/" + nom_produit + "/"+ prix + "/"+description +"/"+image ;
        req.setUrl(url);
        //addresponselistnere bech yaamel add l new conect network type de retoure ya true ya false
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy 
    return resultOK;
        
    }
   public boolean deleteProduit(int id_produit) {
        String url = "http://127.0.0.1:8000/" +"produit/deleteM?id_produit="+id_produit;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
    }
}



    

