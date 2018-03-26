package com.apackage.yvyor.androidfinal;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiHelper extends AsyncTask<String, Void, String> {

    private String getValue;

    //constructor passes value
    public ApiHelper(){
        getValue = "";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        //dataToSent
        if(params[0] == "sendData"){
            getValue = sendPlayerData(params[1], Integer.parseInt(params[2]));
        }

        return getValue;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    private String sendPlayerData(String playerName, int points){
        //database location
        //10.0.47.26
        //192.168.0.104
        //localhost <- ip fuckery //192.168.0.104 <-- local network PC IP
        String hostLocation = "10.1.5.71";
        String serviceUrl = "http://"+hostLocation+"/MyWebApi/api/db/recieveData/" + playerName + "/" + points;

        //connection
        URL url;
        HttpURLConnection connection = null;
        BufferedReader bf = null;
        String returnedValue = "";

        try {
            url = new URL(serviceUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.connect();

            bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            returnedValue = bf.readLine();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(connection != null){
                connection.disconnect();
            }
        }

        return returnedValue;
    }
}
