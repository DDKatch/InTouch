/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.messages;

import com.google.android.gcm.server.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class GCM {
    private static final String GCM_API_KEY = "AIzaSyCTiheF3MWdAcpDuBbig_nkcQBEP1XPWrY";
   /* private static String token = "cYChf4O94kI:APA91bEfb1E6HWJeRtUwk-"
            + "QTmlXMXwNtCmVOcpoQPpxBrCdJNtAHOgTP1hZskJTIWIHJ2Wkbl9fvz9NY1wLqHt-"
            + "_FaaYMiOnyGzbNuI3bulCrsV3AIc-5_Wt48uOhkahJp_O8IDdhShy";*/
    //private static String numberOfProject = "619957916380";
    public static void send1(Map<String, String> map , String token){
        Sender sender = new Sender(GCM_API_KEY);
        Message.Builder mb = new Message.Builder();
        for(Map.Entry<String,String> entry: map.entrySet()){
            mb.addData(entry.getKey(), entry.getValue());
        }
        Message message = mb.build();
            
        try {
           // String temp = "cM_neUfFJnU:APA91bG8FRVsai5ttAatcAjedkhA8foZAc69lau68cV0KAn1CVXadmY0Flr-hHw5dSiETP_AqYycjWRaPnAjyUjbRXxJ3PXrjS4zxZ43TupNQpuldVVl48n5UIF8yWGC3n6-8IECVfm6";
            Result result = sender.send(message, token, 5);
        } catch (IOException ex) {
            Logger.getLogger(GCM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}