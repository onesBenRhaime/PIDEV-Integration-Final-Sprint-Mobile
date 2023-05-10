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

public class OffreEmbaucheService {

    public static OffreEmbaucheService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<OffreEmbauche> listOffreEmbauches;

    

    private OffreEmbaucheService() {
        cr = new ConnectionRequest();
    }

    public static OffreEmbaucheService getInstance() {
        if (instance == null) {
            instance = new OffreEmbaucheService();
        }
        return instance;
    }
    
    public ArrayList<OffreEmbauche> getAll() {
        listOffreEmbauches = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "mobile/offreEmbauche");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listOffreEmbauches = getList();
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

        return listOffreEmbauches;
    }

    private ArrayList<OffreEmbauche> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                OffreEmbauche offreEmbauche = new OffreEmbauche(
                        (int) Float.parseFloat(obj.get("id").toString()),
                        
                        (String) obj.get("poste"),
                        new SimpleDateFormat("dd-MM-yyyy").parse((String) obj.get("dateEmbauche")),
                        Float.parseFloat(obj.get("salaire").toString()),
                        (int) Float.parseFloat(obj.get("duree").toString())
                        
                );

                listOffreEmbauches.add(offreEmbauche);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listOffreEmbauches;
    }
    
    public int add(OffreEmbauche offreEmbauche) {
        return manage(offreEmbauche, false);
    }

    public int edit(OffreEmbauche offreEmbauche) {
        return manage(offreEmbauche, true );
    }

    public int manage(OffreEmbauche offreEmbauche, boolean isEdit) {
        
        cr = new ConnectionRequest();

        
        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "mobile/offreEmbauche/edit");
            cr.addArgument("id", String.valueOf(offreEmbauche.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "mobile/offreEmbauche/add");
        }
        
        cr.addArgument("poste", offreEmbauche.getPoste());
        cr.addArgument("dateEmbauche", new SimpleDateFormat("dd-MM-yyyy").format(offreEmbauche.getDateEmbauche()));
        cr.addArgument("salaire", String.valueOf(offreEmbauche.getSalaire()));
        cr.addArgument("duree", String.valueOf(offreEmbauche.getDuree()));
        
        
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

    public int delete(int offreEmbaucheId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "mobile/offreEmbauche/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(offreEmbaucheId));

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
