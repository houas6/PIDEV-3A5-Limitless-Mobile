/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import DB.DB;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import models.panier;
import models.produit;


/**
 *
 * @author rassa
 */
public class ServicePanier {
    public  ArrayList<panier> paniers =new ArrayList();
      public static ServicePanier instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<panier> listCategorie=new ArrayList<>();

    public ServicePanier() {
        req = new ConnectionRequest();
    }
//rendre l constructeur prive w tkhalik testaana 
    public static ServicePanier getInstance() {
        if (instance == null) {
            instance = new ServicePanier();
        }
        return instance;
    }

    
    
     public boolean addpanier(panier panier,int id_produit) {

      //String description=t.getDescription();
        
       
         String url =DB.BASE_URL+"/addcart?iduser="+panier.getId_user()+"&idprod="+id_produit+"&qt="+1;

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
     
     
     
     
      
     public ArrayList<panier> parseReclamations(String jsonText) {
         float totale=0;
         panier t = new panier();
        System.out.println("+++++++++++");
        System.out.println(jsonText);
        System.out.println("++++++++++++");
        try {
            paniers = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) ReclamationsListJson.get("root");
            for (Map<String, Object> obj : list) {
                
               float id = Float.parseFloat(obj.get("idpanier").toString());
              
               //float iduser=Float.parseFloat(obj.get("iduser").toString());
                 float quantite=Float.parseFloat(obj.get("quantite").toString());
                String nomproduit = obj.get("nomproduit").toString();
                 float prix=Float.parseFloat(obj.get("prix").toString());
                  t.setQuantite((int)quantite);
                 totale+=prix*quantite;
                 
                t.setId_panier((int) id);
               
                // t.setId_user((int)iduser);
               // t.setStatus(((int) Float.parseFloat(obj.get("status").toString())));
               
                
                paniers.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
      //  t.setTotal_panier(totale);
        //paniers.add(t);
        return paniers;
    }
    public ArrayList<panier> getAllReclamations(int id){
//          String url = Statics.BASE_URL+"/mobile/listReclamtion/";
          String url =DB.BASE_URL+"/affcart?iduser="+id;
          req.setUrl(url);
          req.setPost(false);
          req.addRequestHeader("accept", "application/json");
          req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {

        byte[] responseData = req.getResponseData();
        if (responseData != null) {
            paniers = parseReclamations(new String(responseData));
            req.removeResponseListener(this);
//            String response = new String(responseData);
//            System.out.println(response);
        } else {
            System.out.println("Response data is null");
        }
    }
});

          NetworkManager.getInstance().addToQueueAndWait(req);
         
         
         return paniers;
     }
    
     
     
     
     
     
     
     
     
     public boolean deletepanier(int id) {
        System.out.println("********");
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url =DB.BASE_URL+"/deletepanier?id="+id;
        req.setUrl(url);
        req.setPost(false);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     
     
     
     
       public boolean increment(int id) {
        System.out.println("********");
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url =DB.BASE_URL+"/increment?id="+id;
        req.setUrl(url);
        req.setPost(false);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     
     
     
     
     
     
     
         public boolean decrement(int id) {
        System.out.println("********");
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url =DB.BASE_URL+"/decrement?id="+id;
        req.setUrl(url);
        req.setPost(false);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    
    
}
