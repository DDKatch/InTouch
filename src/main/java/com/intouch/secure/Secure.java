/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intouch.secure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 *
 * @author kachu
 */
public class Secure {
    
    private String api_key ;
    
    private String secure_file_path; 
    
    public Secure(){
        secure_file_path = "src/main/resources/secure";
        
        try {
            this.api_key = getApi_key(secure_file_path);     
        } catch(FileNotFoundException e){
            System.out.printf(e.toString());
            System.out.printf("using default api key: SHEMODED");
            api_key = "SHEMODED";
        }
    }

    /**
     * @return the api_key
     */
    public String getApi_key() {
        return api_key;
    }

    /**
     * @param api_key the api_key to set
     */
    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }
    
    /**
     * @return the secure_file_path
     */
    public String getSecure_file_path() {
        return secure_file_path;
    }

    /**
     * @param secure_file_path the secure_file_path to set
     */
    public void setSecure_file_path(String secure_file_path) {
        this.secure_file_path = secure_file_path;
    }
    
    private String getApi_key(String file_path) throws FileNotFoundException {
                
    File secure_file = new File(file_path);    
    StringBuilder sb = new StringBuilder();
    
    secure_file.exists();
 
    try {
        BufferedReader in = new BufferedReader(new FileReader( secure_file.getAbsoluteFile()));
        try {
            String s;
            while ((s = in.readLine()) != null) {
                sb.append(s);
            }
        } finally {
            in.close();
        }
    } catch(IOException e) {
        throw new RuntimeException(e);
    }
 
    return sb.toString();
    }
    
}
