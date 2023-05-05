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
import com.mycompany.myapp.entities.Livreur;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author amine
 */
public class ServiceLivreur {
    public static ServiceLivreur instance = null;  
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<Livreur> listLivreur=new ArrayList<>();
    //*******constructeur**********
    public ServiceLivreur() {
        req = new ConnectionRequest();
    }
    //************* private and waiting for const
   public static ServiceLivreur getInstance() {
        if (instance == null) {
            instance = new ServiceLivreur();
        }
        return instance;
    }
   //******** ajout****************
     public boolean addLivreur(Livreur l) {

        String nom = l.getNom();
        String mail = l.getMail();
        int telephone = l.getTelephone();
        String url =  "http://127.0.0.1:8000/"+ "livreurback/AddjsonM/" + nom +"/"+ mail +"/"+telephone;

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
     //************afficher**********************
     public ArrayList<Livreur> affichageLivreur()
    {
        ArrayList<Livreur> result = new ArrayList<>();  
        String url ="http://127.0.0.1:8000/"+"livreurback/afficheMob";
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
                       Livreur l = new Livreur();
                       System.out.println(obj);
                        String nom = obj.get("nom").toString();
                        String mail = obj.get("mail").toString();
                        float telephone = Float.parseFloat(obj.get("telephone").toString());
                       Object idObj = obj.get("ID_livreur");
                       float id = idObj != null ? Float.parseFloat(idObj.toString()) : 0f;  
                       
                        l.setID_livreur(Math.round(id));
                        l.setNom(nom);
                        l.setMail(mail);
                        l.setTelephone(Math.round(telephone));          
                        result.add(l);
                        System.out.println(l.getNom());
                        System.out.println(l.getMail());
                        System.out.println(l.getTelephone());
                        
                    }
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
     //***********modifier**************
       public boolean modifierLivreur(Livreur l) {
      //  String nom =l.getNom();
        String url= "http://127.0.0.1:8000/"+"livreurback/modifierM?ID_livreur="+l.getID_livreur()+"&nom="+l.getNom()+"&mail="+l.getMail()+"&telephone="+l.getTelephone();
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
     //*********** supprimer****************
         public boolean deleteLivreur(int idLivreur ) {
        String url = "http://127.0.0.1:8000/" +"livreurback/deleteM?ID_livreur="+idLivreur;
        
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
