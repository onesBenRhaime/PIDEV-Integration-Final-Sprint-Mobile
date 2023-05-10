/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazebank.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mazebank.utils.Statics;
import com.mazebank.entities.CategoryCredit;
import com.mazebank.entities.Credit;
import com.mazebank.entities.DemandeCredit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class serviceDemandes {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */

    public ArrayList<DemandeCredit> demandes;

    public static serviceDemandes instance = null;
    public boolean resultOK;
     public static boolean resultOk = true;
    private ConnectionRequest req;

    private serviceDemandes() {
        req = new ConnectionRequest();
    }

    public static serviceDemandes getInstance() {
        if (instance == null) {
            instance = new serviceDemandes();
        }
        return instance;
    }
public boolean addDemande(DemandeCredit d,int creditId) {

        int amount = d.getAmount();
        String note =d.getNote();
        String cin1 =d.getCin1();
        String cin2 =d.getCin2();
        String url =Statics.BASE_URL+"addDemandeCreditJSON/"+creditId
                                                             +"?amount="+d.getAmount()                                                                                                          
                                                             +"&note="+d.getNote()
                                                             +"&cin1="+d.getCin1()
                                                             +"&cin2"+d.getCin2();
        
        
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

    public ArrayList<DemandeCredit> parseDemandes(String jsonText) {
        try {
            demandes = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                DemandeCredit c = new DemandeCredit();
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int) id);

                Map<String, Object> creditMap = (Map<String, Object>) obj.get("creditId");
                float cid = Float.parseFloat(creditMap.get("id").toString());

                Map<String, Object> creditCategoryMap = (Map<String, Object>) creditMap.get("creditCategory");
                float creditCategoryId = Float.parseFloat(creditCategoryMap.get("id").toString());
                String creditCategoryName = String.valueOf(creditCategoryMap.get("name"));
                String creditCategoryDescription = String.valueOf(creditCategoryMap.get("description"));
                CategoryCredit creditCategory = new CategoryCredit((int) creditCategoryId, creditCategoryName, creditCategoryDescription);

                float minAmount = Float.parseFloat(creditMap.get("minAmount").toString());
                float maxAmount = Float.parseFloat(creditMap.get("maxAmount").toString());
                float withdrawMonthly = Float.parseFloat(creditMap.get("withdrawMonthly").toString());
                float months = Float.parseFloat(creditMap.get("months").toString());
                float loanRate = Float.parseFloat(creditMap.get("loanRate").toString());
                Credit creditId = new Credit((int) cid, creditCategory, (int) minAmount, (int) maxAmount, (int) withdrawMonthly, (int) months, (int) loanRate);
                c.setCredit(creditId);

                float amount = Float.parseFloat(obj.get("amount").toString());
                c.setAmount((int) amount);

                String note = String.valueOf(obj.get("note"));
                c.setNote(note);

                String cin1 = String.valueOf(obj.get("cin1"));
                c.setCin1(cin1);

                String cin2 = String.valueOf(obj.get("cin2"));
                c.setCin2(cin2);

                demandes.add(c);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return demandes;
    }

    public ArrayList<DemandeCredit> getAllDemandes() {
        String url = Statics.BASE_URL + "cdemande/credit/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                demandes = parseDemandes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return demandes;
    }
//Update 
    public boolean modifier(DemandeCredit transaction) {
        String url = Statics.BASE_URL +"updateDemandeCreditJSON/"+(int)transaction.getId()+"?note="+transaction.getNote();
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
        req = new ConnectionRequest();
        req.setUrl(Statics.BASE_URL + "deleteDemandeCreditJSON/"+id);
        System.out.println("url delete : "+req);
        req.setHttpMethod("POST");
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        
        try {
            req.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return req.getResponseCode();
    }
    
}
