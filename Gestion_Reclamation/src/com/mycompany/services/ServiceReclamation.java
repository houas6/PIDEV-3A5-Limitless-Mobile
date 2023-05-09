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
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
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
    


    

//        
//        
//        
//    }
    
     private static final String ACCOUNT_SID = "AC0103800e0a30303037bd18bdc1111caa";
  private static final String AUTH_TOKEN = "9224ef57494f5118ce1087746493e16b";
  private static final String TWILIO_PHONE_NUMBER = "+12765979278";

  // Define the method to send the SMS message
  public void sendSms(String toPhoneNumber, String messageText) {
    // Initialize the Twilio API client
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    // Set the phone numbers for the SMS message
    PhoneNumber to = new PhoneNumber(toPhoneNumber);
    PhoneNumber from = new PhoneNumber(TWILIO_PHONE_NUMBER);

    // Use the Message creator to send the SMS message
    com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(to, from, messageText).create();
  }
    
    //methode d'ajout
    public boolean addReclamations(Reclamation e){     
        String description=e.getDescription();
        String etat=e.getEtat(); 
        String id_user="1";
//        String url = Statics.URL+"reclamations/createReclamations/"+e.getEtat()+"/"+e.getDescription();
        String url = "http://127.0.0.1:8000/reclamations/createReclamations/" +etat+"/"+description+"/"+id_user+"/";
        
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
    public ArrayList<Reclamation> getAllReclamationsByEtat(String etat){
//          String url = Statics.BASE_URL+"/mobile/listReclamtion/";
          String url ="http://127.0.0.1:8000/reclamations/mobile/listReclamtion/etat/"+etat;
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
    public boolean deleteReclamation(int id) {
    String url =  Statics.URL+"/reclamations/mobile/deleteReclamation/" + id + "/";
    ConnectionRequest request = new ConnectionRequest(url);
    request.setHttpMethod("DELETE");

    request.addResponseListener(e -> {
      
        resultOk = request.getResponseCode() == 200;
        
       
    });

    NetworkManager.getInstance().addToQueue(request);
            return resultOk;

}
    public boolean UpdateReclamation(Reclamation e){
        String description=e.getDescription();
        String etat=e.getEtat();
        int id = e.getId();
        
        String url = Statics.URL+"/reclamations/mobile/updateReclamation/"+id+"/"+description+"/"+etat+"/";
        
        
        req.setUrl(url);
        //GET =>
        req.setPost(false);
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
