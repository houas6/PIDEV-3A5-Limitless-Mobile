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
import com.mycompany.myapp.entities.Produit;
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

public ArrayList<Produit> affichageProduit() {
    ArrayList<Produit> result = new ArrayList<>();

    String url ="http://127.0.0.1:8000/"+"produit/afficheMobp";
     req.setUrl(url);
       //est une interface de la bibliothèque de classes standard de Java, 
       //qui définit une méthode appelée actionPerformed() 
       //qui est invoquée lorsqu'un événement est déclenché.
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //parse conversion m json lel map
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try 
                {
                    //mapM les donnes recue mel json sous forme du map de type object string
                    //root howa key mteaa json //tochararray yarjaa aa json khatrou yekbel ken char
                    Map<String,Object>mapM = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) mapM.get("root");
                    System.out.println(mapM);
                 for(Map<String, Object> obj : ListOfMaps)
                    {
                        if(obj!=null){
                        System.out.println(obj);
                    Produit p = new Produit();
                    String nom = obj.get("nom_produit").toString();
                    float prix = Float.parseFloat(obj.get("prix").toString());
                    String description = obj.get("description").toString();
                    String image = obj.get("image").toString();
                    int idcategorie = Integer.parseInt(obj.get("idcategorie").toString());
                    int idu = Integer.parseInt(obj.get("id_user").toString());

                    p.setNom_produit(nom);
                    p.setPrix(prix);
                    p.setDescription(description);
                    p.setImage(image);
                    p.setId_user(idu);
                    p.setIdcategorie(idcategorie);
                    result.add(p);
                }}}
            
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
    
}   


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



    

