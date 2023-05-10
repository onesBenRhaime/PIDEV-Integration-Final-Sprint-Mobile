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

public class OffreInvestissementService {

    public static OffreInvestissementService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<OffreInvestissement> listOffreInvestissements;

    

    private OffreInvestissementService() {
        cr = new ConnectionRequest();
    }

    public static OffreInvestissementService getInstance() {
        if (instance == null) {
            instance = new OffreInvestissementService();
        }
        return instance;
    }
    
    public ArrayList<OffreInvestissement> getAll() {
        listOffreInvestissements = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "mobile/offreInvestissement");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listOffreInvestissements = getList();
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

        return listOffreInvestissements;
    }

    private ArrayList<OffreInvestissement> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                OffreInvestissement offreInvestissement = new OffreInvestissement(
                        (int) Float.parseFloat(obj.get("id").toString()),
                        
                        (int) Float.parseFloat(obj.get("minBudget").toString())
                        
                );

                listOffreInvestissements.add(offreInvestissement);
            }
        } catch (IOException  e) {
            e.printStackTrace();
        }
        return listOffreInvestissements;
    }
    
    public int add(OffreInvestissement offreInvestissement) {
        return manage(offreInvestissement, false);
    }

    public int edit(OffreInvestissement offreInvestissement) {
        return manage(offreInvestissement, true );
    }

    public int manage(OffreInvestissement offreInvestissement, boolean isEdit) {
        
        cr = new ConnectionRequest();

        
        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "mobile/offreInvestissement/edit");
            cr.addArgument("id", String.valueOf(offreInvestissement.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "mobile/offreInvestissement/add");
        }
        
        cr.addArgument("minBudget", String.valueOf(offreInvestissement.getMinBudget()));
        
        
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

    public int delete(int offreInvestissementId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "mobile/offreInvestissement/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(offreInvestissementId));

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
