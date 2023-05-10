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

public class OffreAssuranceService {

    public static OffreAssuranceService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<OffreAssurance> listOffreAssurances;

    

    private OffreAssuranceService() {
        cr = new ConnectionRequest();
    }

    public static OffreAssuranceService getInstance() {
        if (instance == null) {
            instance = new OffreAssuranceService();
        }
        return instance;
    }
    
    public ArrayList<OffreAssurance> getAll() {
        listOffreAssurances = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "mobile/offreAssurance");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listOffreAssurances = getList();
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

        return listOffreAssurances;
    }

    private ArrayList<OffreAssurance> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                OffreAssurance offreAssurance = new OffreAssurance(
                        (int) Float.parseFloat(obj.get("id").toString()),
                        
                        (String) obj.get("libelle"),
                        (String) obj.get("image"),
                        (String) obj.get("partenaire"),
                        (String) obj.get("type")
                        
                );

                listOffreAssurances.add(offreAssurance);
            }
        } catch (IOException  e) {
            e.printStackTrace();
        }
        return listOffreAssurances;
    }
    
    public int add(OffreAssurance offreAssurance) {
        return manage(offreAssurance, false);
    }

    public int edit(OffreAssurance offreAssurance) {
        return manage(offreAssurance, true );
    }

    public int manage(OffreAssurance offreAssurance, boolean isEdit) {
        
        cr = new ConnectionRequest();

        
        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "mobile/offreAssurance/edit");
            cr.addArgument("id", String.valueOf(offreAssurance.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "mobile/offreAssurance/add");
        }
        
        cr.addArgument("libelle", offreAssurance.getLibelle());
        cr.addArgument("image", offreAssurance.getImage());
        cr.addArgument("partenaire", offreAssurance.getPartenaire());
        cr.addArgument("type", offreAssurance.getType());
        
        
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

    public int delete(int offreAssuranceId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "mobile/offreAssurance/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(offreAssuranceId));

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
}
