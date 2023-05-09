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
import com.mycompany.myapp.entities.Echanges;
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
public class ServiceEchanges {
      public static ServiceEchanges instance = null;  
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<Echanges> listEchanges=new ArrayList<>();
    //*******constructeur**********
    public ServiceEchanges() {
        req = new ConnectionRequest();
    }
    //************* private and waiting for const
   public static ServiceEchanges getInstance() {
        if (instance == null) {
            instance = new ServiceEchanges();
        }
        return instance;
    }
   //******** ajout****************
     public boolean addEchanges(Echanges e) {

        int pe = e.getProduit_echange();
        int po = e.getProduit_offert();
        String st = e.getStatut();
        String cm = e.getCommentaire();
        String url =  "http://127.0.0.1:8000/"+ "echanges/AddjsonMe/" + pe +"/"+ po +"/"+st+"/"+cm;

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
     public ArrayList<Echanges> affichageEchanges()
    {
        ArrayList<Echanges> result = new ArrayList<>();  
        String url ="http://127.0.0.1:8000/"+"echanges/afficheMobe";
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
                    for(Map<String, Object> obj : ListOfMaps) {
                    if(obj != null) {
                        System.out.println(obj);
                        Echanges e = new Echanges();

                        String st = obj.get("statut").toString();
                        String cm = obj.get("commentaire").toString();
                        Object idObj = obj.get("idEchange");
                        float id = idObj != null ? Float.parseFloat(idObj.toString()) : 0;  
                        
                        e.setId_echange(Math.round(id));
                        e.setStatut(st);
                        e.setCommentaire(cm);         
                        result.add(e);
                        System.out.println("id_echange: " + e.getId_echange());
                        System.out.println("statut: " + e.getStatut());
                        System.out.println("commentaire: " + e.getCommentaire());

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
       public boolean modifierEchanges(Echanges e) {
      //  String nom =l.getNom();
        String url= "http://127.0.0.1:8000/"+"echanges/modifierMe?id_echange="+e.getId_echange()+"&produit_echange="+e.getProduit_echange()+"&produit_offert="+e.getProduit_offert()+"&statut="+e.getStatut()+"&commentaire="+e.getCommentaire();
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
         public boolean deleteEchanges(int ide ) {
        String url = "http://127.0.0.1:8000/" +"echanges/deleteMe?id_echange="+ide;
        
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
