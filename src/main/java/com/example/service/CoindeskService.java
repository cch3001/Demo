package com.example.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.domain.CoindeskForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class CoindeskService {

    @Value("${coindesk.url}")
    private String coindeskUrl;
    
    public CoindeskForm parseCoinDesk() {
    	
        URL url;
		try {
			url = new URL(coindeskUrl);        
	        Gson gson = new Gson();
	        InputStreamReader reader = new InputStreamReader(url.openStream());

	        TypeToken<CoindeskForm> typeToken = new TypeToken<CoindeskForm>(){};
	        CoindeskForm genericBean = gson.fromJson(reader, typeToken.getType());
	       
	        return genericBean ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
}
