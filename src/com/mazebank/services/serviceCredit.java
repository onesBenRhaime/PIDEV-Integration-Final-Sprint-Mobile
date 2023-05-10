/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazebank.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mazebank.utils.Statics;
import com.mazebank.entities.CategoryCredit;
import com.mazebank.entities.Credit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class serviceCredit {

    public ArrayList<Credit> credits;

    public static serviceCredit instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private serviceCredit() {
        req = new ConnectionRequest();
    }

    public static serviceCredit getInstance() {
        if (instance == null) {
            instance = new serviceCredit();
        }
        return instance;
    }

    public ArrayList<Credit> parseCredits(String jsonText) {
        try {
            credits = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Credit c = new Credit();
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int) id);
                Map<String, Object> creditCategoryMap = (Map<String, Object>) obj.get("creditCategory");
                float creditCategoryId = Float.parseFloat(obj.get("id").toString());
                String creditCategoryName = String.valueOf(creditCategoryMap.get("name"));
                String creditCategoryDescription = String.valueOf(creditCategoryMap.get("description"));
                CategoryCredit creditCategory = new CategoryCredit((int) creditCategoryId, creditCategoryName, creditCategoryDescription);
                c.setCategory(creditCategory);
                float minAmount = Float.parseFloat(obj.get("minAmount").toString());
                c.setMinAmount((int) minAmount);
                float maxAmount = Float.parseFloat(obj.get("maxAmount").toString());
                c.setMaxAmount((int) maxAmount);
                float withdrawMonthly = Float.parseFloat(obj.get("withdrawMonthly").toString());
                c.setWithdrawMonthly((int) withdrawMonthly);
                float months = Float.parseFloat(obj.get("months").toString());
                c.setMonths((int) months);
                float loanRate = Float.parseFloat(obj.get("loanRate").toString());
                c.setLoanRate((int) loanRate);

                credits.add(c);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return credits;
    }

    public ArrayList<Credit> getAllCredits() {
        String url = Statics.BASE_URL + "loansJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                credits = parseCredits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return credits;
    }

}
