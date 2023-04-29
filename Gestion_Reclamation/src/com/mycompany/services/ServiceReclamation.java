/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Reclamation;
import com.mycompany.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author achra
 */
public class ServiceReclamation {
    ConnectionRequest req;
    public ArrayList<Reclamation> reclamations;

//    public ArrayList<Reclamation> reclamtions;
    
    public boolean resultOk;
    //2  creer un attribut de type de la classe en question (static)
    public static ServiceReclamation instance = null;
    
    
    //Singleton => Design Pattern qui permet de creer une seule instance d'un objet 
    
    
    //1 rendre le constructeur private
    private ServiceReclamation() {
        req = new ConnectionRequest();
    }
    
    
    //3 la methode qu'elle va ramplacer le constructeur 
    public static ServiceReclamation getinstance(){
        if(instance == null){
            instance = new ServiceReclamation();    
        }
        return instance;
    }
    


    
//        public boolean updateReclamations(Reclamation e){     
//        String url = Statics.URL+"exercice/mobile/updaterating?idexercice="+e.getIdexercice()+"&iduser="+e.getIduser()+"&rating="+e.getReclamation();
//        
//        
//        req.setUrl(url);
//        //GET =>
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOk = req.getResponseCode() == 200; //si le code return 200 
//                //
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOk;
//        
//        
//        
//    }
    
    
    
    //methode d'ajout
    public boolean addReclamations(Reclamation e){     
        String description=e.getDescription();
        String etat=e.getEtat(); 
//        String url = Statics.URL+"reclamations/createReclamations/"+e.getEtat()+"/"+e.getDescription();
        String url = "http://127.0.0.1:8000/reclamations/createReclamations/" +etat+"/"+description+"/" ;
        
        req.setUrl(url);
        //GET =>
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200; //si le code return 200 
                //
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
        
        
        
    }
    public ArrayList<Reclamation> parseReclamations(String jsonText) {
        System.out.println("+++++++++++");
        System.out.println(jsonText);
        System.out.println("++++++++++++");
        try {
            reclamations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) ReclamationsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Reclamation t = new Reclamation();
                float id = Float.parseFloat(obj.get("idReclamation").toString());
                t.setId((int) id);
               // t.setStatus(((int) Float.parseFloat(obj.get("status").toString())));
                if (obj.get("etat") == null) {
                    t.setEtat("null");
                } else {
                    t.setEtat(obj.get("etat").toString());
                }
                  if (obj.get("description") == null) {
                    t.setDescription("null");
                } else {
                    t.setDescription(obj.get("description").toString());
                }
                
                reclamations.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations;
    }
    public ArrayList<Reclamation> getAllReclamations(){
//          String url = Statics.BASE_URL+"/mobile/listReclamtion/";
          String url ="http://127.0.0.1:8000/reclamations/mobile/listReclamtion/";
          req.setUrl(url);
          req.setPost(false);
          req.addRequestHeader("accept", "application/json");
          req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {

        byte[] responseData = req.getResponseData();
        if (responseData != null) {
            reclamations = parseReclamations(new String(responseData));
            req.removeResponseListener(this);
//            String response = new String(responseData);
//            System.out.println(response);
        } else {
            System.out.println("Response data is null");
        }
    }
});

          NetworkManager.getInstance().addToQueueAndWait(req);
         
         
         return reclamations;
     }
    
 
//        public ArrayList<Reclamation> parseallIDReclamations(String jsonText){
//                try {
//
//                    System.out.println(jsonText);
//            reclamtions=new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)tasksListJson.get("root");
//            for(Map<String,Object> obj : list){
//                Reclamation a = new Reclamation();
//                  
//                
//                
//                if (obj.get("idReclamation").toString() != null) {
//                   a.setIdexercice((int)Float.parseFloat(obj.get("idReclamation").toString()));
//                }
//                
//                
//                        if (obj.get("rating").toString() != null) {
//                   a.setReclamation((int)Float.parseFloat(obj.get("rating").toString()));
//                }
//                        
//                                                if (obj.get("iduser").toString() != null) {
//                   a.setIduser((int)Float.parseFloat(obj.get("iduser").toString()));
//                }
//
//
//             
//                reclamtions.add(a);
//            }
//        } catch (IOException ex) {
//            
//        }
//        return reclamtions;
//    }
//    public ArrayList<Reclamation> getAllidReclamations(int idUser){
//    String url = Statics.URL + "exercice/mobile/check_rating?iduser=" + idUser;
//        req.setUrl(url);
//        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                     if (req.getResponseData() != null) {
//                reclamtions = parseallIDReclamations(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//            }
//        });
//        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
//        
//            if (reclamtions == null) {
//        reclamtions = new ArrayList<>();
//    }
//        return reclamtions;
//    }
//}
  
}
