package com.mazebank.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.mazebank.entities.*;
import com.mazebank.utils.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CarteBancaireService {
  
    public static boolean resultOk = true;
    public static CarteBancaireService instance = null;
    public int resultCode;
    public boolean resultOK;
    private ConnectionRequest cr,req;
    private ArrayList<CarteBancaire> listDemande;

    private int nb=0;

    private CarteBancaireService() {
        cr = new ConnectionRequest();
        req= new ConnectionRequest();
    }

    public static CarteBancaireService getInstance() {
        if (instance == null) {
            instance = new CarteBancaireService();
        }
        return instance;
    }
    
    
        public boolean addTask(CarteBancaire t) {

            String email = t.getEmail();
            String identifier = t.getIdentifier();
            String description = t.getDescription();
            String cinS1 = t.getCinS1();
            

            String url =Statics.BASE_URL+"carte/bancaire/addDemandeCartes/JSON?email="+t.getEmail()
                                                                                    +"&identifier="+t.getIdentifier()
                                                                                    +"&description="+t.getDescription()
                                                                                    +"&cinS1="+t.getCinS1(); 
        
            System.out.println("url : "+ url);
            req.setUrl(url);
            req.setHttpMethod("POST");

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

    
    public ArrayList<CarteBancaire> getAll() {
        listDemande = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "carte/bancaire/AllDemandeUser/CarteJson");
        System.out.println("url = "+cr);
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listDemande = getList();   
                }

                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listDemande;
    }

    private ArrayList<CarteBancaire> getList() {
           JSONParser jsonp ;
            jsonp = new JSONParser();
            
        try {
            Map<String, Object> parsedJson =jsonp.parseJSON(new CharArrayReader(                    
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                CarteBancaire cartebancaire = new CarteBancaire();
                
                      float id = Float.parseFloat( obj.get("id").toString() ) ; 
                        cartebancaire.setEmail((String)obj.get("email").toString());
                        cartebancaire.setIdentifier((String)obj.get("identifier").toString());
                        cartebancaire.setDescription((String)obj.get("description").toString());
                        cartebancaire.setCinS1((String)obj.get("cinS1").toString());
                        
                         cartebancaire.setId((int)id);
                listDemande.add(cartebancaire);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listDemande;
    }
    
    public int add(CarteBancaire cartebancaire) {
        return manage(cartebancaire);
    }

    public int edit(CarteBancaire cartebancaire) {
        return manage(cartebancaire );
    }

    public int manage(CarteBancaire cartebancaire) {
        
        cr = new ConnectionRequest();
        
        cr.setHttpMethod("POST");                                               
            cr.setUrl(Statics.BASE_URL + "carte/bancaire/updateDemandeUserCartesJSON/?id="+(int)cartebancaire.getId()+"&email="+cartebancaire.getEmail()+"&identifier="+cartebancaire.getIdentifier()+"&description="+cartebancaire.getDescription());
            cr.addArgument("id", String.valueOf(cartebancaire.getId()));
     
        
        //cr.addArgument("date", new SimpleDateFormat("dd-MM-yyyy").format(reclamation.getDate()));
        cr.addArgument("email", cartebancaire.getEmail());
        cr.addArgument("identifier", cartebancaire.getIdentifier());
        cr.addArgument("description", cartebancaire.getDescription());
        cr.addArgument("cinS1", cartebancaire.getCinS1());
        
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = cr.getResponseCode();
                cr.removeResponseListener(this);
            }
        });
        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception ignored) {

        }
        return resultCode;
    }

    
    //Update 
    public boolean modifier(CarteBancaire cartebancaire) {
        
        String url = Statics.BASE_URL +"carte/bancaire/updateDemandeUserCartesJSON/"+(int)cartebancaire.getId()+"?email="+cartebancaire.getEmail()+"&identifier="+cartebancaire.getIdentifier()+"&description="+cartebancaire.getDescription()+"&cinS1="+cartebancaire.getCinS1();
        req.setUrl(url);
        System.out.println("url modif : "+url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    public int delete(int id) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "carte/bancaire/deleteDemandeCartesJSON/"+id);
        System.out.println("url delete : "+cr);
        cr.setHttpMethod("POST");
        // cr.addArgument("id", String.valueOf(id));
        
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseListener(this);
            }
        });
        
        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cr.getResponseCode();
    }
    
    public int getStat() {
       cr = new ConnectionRequest();
       cr.setUrl(Statics.BASE_URL + "carte/bancaire/allStatistique");
       System.out.println("url  allStatistique= " + cr);
       cr.setHttpMethod("GET");
       cr.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            if (cr.getResponseCode() == 200) {
                String response = new String(cr.getResponseData());
                nb = Integer.parseInt(response.trim()); // assuming the response is a single integer
            }
            cr.removeResponseListener(this);
        }
      });
    try {
        cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
        NetworkManager.getInstance().addToQueueAndWait(cr);
    } catch (Exception e) {
        e.printStackTrace();
    }
    System.out.println("nb = "+nb);
    return nb;
}
}
