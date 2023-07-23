package com.blessing.tdd4.LogicLayer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login {
    public static boolean authenticateCredentials(String username, String password){
        try {

            URL url = new URL("https://database-microservice.azurewebsites.net/credentials/"+username);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            //output = <String>br.lines();
            //System.out.println(output);
            StringBuffer sb = new StringBuffer();
            //while ((output = br.read) != null) {
            while ((output = br.readLine()) != null) {
                    //System.out.println(output);
                    sb.append(output);
            }
            conn.disconnect();
            //String final = sb.toString();
            if ((sb.toString() != null) &&(sb.toString().contains(password))){
                return true;
            }else{
                return false;
            }

        } catch (Exception e) {
            System.out.println("unable to query data" +e.getMessage());
            //throw new Exception ("unable to query data" +e.getMessage());
            return false;
        }

    }
}
