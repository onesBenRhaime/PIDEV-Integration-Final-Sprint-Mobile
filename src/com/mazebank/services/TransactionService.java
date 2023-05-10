package com.mazebank.services;

import com.mazebank.entities.Transaction;
import com.mazebank.utils.Statics;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransactionService {
  
    public static boolean resultOk = true;
    public static TransactionService instance = null;
    public int resultCode;
    public boolean resultOK;
    private ConnectionRequest cr,req;
    private ArrayList<Transaction> listTransactions;
    private ArrayList<Transaction> listStat;
    private int nb=0;    

    private TransactionService() {
        cr = new ConnectionRequest();
        req= new ConnectionRequest();
    }

    public static TransactionService getInstance() {
        if (instance == null) {
            instance = new TransactionService();
        }
        return instance;
    }    
    
    public boolean addTask(Transaction t) {
        
            String requestTo = t.getRequestTo();
            String requestForm = t.getRequestFrom();
            String montant = t.getMontant();
            String typeTransaction = t.getTypeTransaction();
            String url =Statics.BASE_URL+"webServices/addTransactionsJson/new?typeTransaction="+t.getTypeTransaction()                                                                                                              
                                                                                    +"&requestTo="+t.getRequestTo()
                                                                                    +"&requestFrom="+t.getRequestFrom()
                                                                                    +"&montant="+t.getMontant(); 
        
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
    
    public ArrayList<Transaction> getAll() {
        listTransactions = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "webServices/allTransactionsJson");
        System.out.println("url = "+cr);
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listTransactions = getList();   
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

        return listTransactions;
    }

    public int getStat() {
       cr = new ConnectionRequest();
       cr.setUrl(Statics.BASE_URL + "webServices/allStatistique");
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

    private ArrayList<Transaction> getList() {
           JSONParser jsonp ;
            jsonp = new JSONParser();
            
        try {
            Map<String, Object> parsedJson =jsonp.parseJSON(new CharArrayReader(                    
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Transaction transaction = new Transaction();
                
                        float id = Float.parseFloat( obj.get("id").toString() ) ; 
                      
                        transaction.setRequestTo((String)obj.get("requestTo").toString());
                        transaction.setRequestFrom((String)obj.get("requestFrom").toString());
                        transaction.setMontant((String)obj.get("montant").toString());
                        transaction.setTypeTransaction((String)obj.get("typeTransaction").toString());
                        //System.out.println("reclamation test :  "+ reclamation);
                        
                         transaction.setId((int)id);
                listTransactions.add(transaction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listTransactions;
    }   
    
    public int add(Transaction transaction) {
        return manage(transaction);
    }

    public int edit(Transaction transaction) {
        return manage(transaction );
    }

    public int manage(Transaction transaction) {
        
        cr = new ConnectionRequest();
        
        cr.setHttpMethod("GET");                                               
            cr.setUrl(Statics.BASE_URL + "webServices/updateTransactionsJson/?id="+(int)transaction.getId()+"&requestTo="+transaction.getRequestTo()+"&montant="+transaction.getMontant());
            cr.addArgument("id", String.valueOf(transaction.getId()));
            
        cr.addArgument("requestTo", transaction.getRequestTo());
        cr.addArgument("montant", transaction.getMontant());
        
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
    public boolean modifier(Transaction transaction) {
        String url = Statics.BASE_URL +"webServices/updateTransactionsJson/"+(int)transaction.getId()+"?requestTo="+transaction.getRequestTo()+"&montant="+transaction.getMontant();
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
        cr.setUrl(Statics.BASE_URL + "webServices/deleteTransactionsJson/"+id);
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
}
