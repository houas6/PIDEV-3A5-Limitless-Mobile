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
import com.mycompany.myapp.entities.Categorie;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author USER
 */
public class ServiceCategorie {
    public static ServiceCategorie instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<Categorie> listCategorie=new ArrayList<>();

    public ServiceCategorie() {
        req = new ConnectionRequest();
    }
//rendre l constructeur prive w tkhalik testaana 
    public static ServiceCategorie getInstance() {
        if (instance == null) {
            instance = new ServiceCategorie();
        }
        return instance;
    }

    public boolean addCategorie(Categorie c) {

        String nom = c.getNomcategorie();
      //String description=t.getDescription();
        
       
        String url =  "http://127.0.0.1:8000/"+ "categorie/AddjsonM/" + nom ;

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
    /********** Affichage********************/
      
     public ArrayList<Categorie> affichageCategorie()
    {

        ArrayList<Categorie> result = new ArrayList<>();
     
        String url ="http://127.0.0.1:8000/"+"categorie/afficheMob";
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
                       Categorie c = new Categorie();
                       System.out.println(obj);
                        String nom = obj.get("nomCategorie").toString();
                        
                         int id = Integer.parseInt(obj.get("idCategorie").toString());
                       ////  String Description = obj.get("description").toString();
                       

                       c.setIdcategorie(id);
                        c.setNomcategorie(nom);
                        
                        //c.setDescription(Description);
                        
                        
                        result.add(c);
                        //System.out.println(c.getNomcategorie());
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

   
     /************************************************************************************************/    
  
   public boolean modifierCategorie(Categorie c) {
        String nom = c.getNomcategorie();
        String url= "http://127.0.0.1:8000/"+"categorie/modifierM?idcategorie="+c.getIdcategorie()+"&nomCategorie="+c.getNomcategorie();
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
   public boolean deletecategorie(int idcategorie ) {
        String url = "http://127.0.0.1:8000/" +"categorie/deleteM?idcategorie="+idcategorie;
        
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
   /***************************************************************************/
   
    
}
