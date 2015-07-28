package com.cast.amanda.primeiraaplicacao.Model.Services;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;

/**
 * Created by Amanda on 27/07/2015.
 */
public final class CepService {

    private static final String URL = "http://correiosapi.apphb.com/cep/";
    private CepService(){
        super();
    }

    public static ClientAdress getAddressBy(String cep){

        ClientAdress address = null;

        try {
            URL url = new URL(URL + cep);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if (responseCode != HttpURLConnection.HTTP_OK){
                throw new RuntimeException("Error code: " + responseCode);
            }

            //pega transferencia de dados (body)
            InputStream inputStream = conn.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            address = objectMapper.readValue(inputStream, ClientAdress.class);
            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }
}
