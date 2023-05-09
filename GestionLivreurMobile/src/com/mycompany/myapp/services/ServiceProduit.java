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
                    Object idObj = obj.get("id_produit");
                    float id = idObj != null ? Float.parseFloat(idObj.toString()) : 0;  
                    float prix = Float.parseFloat(obj.get("prix").toString());
                    p.setId_produit(Math.round(id));
                    p.setPrix(prix);
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


    
}



    

