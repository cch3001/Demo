package com.example;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.domain.CurrencyForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootSwaggerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	    
	    @Test
	    public void testInsertObject() throws Exception { 
	       //  String url = BASE_URL + "/object";
	    	CurrencyForm anObject = new CurrencyForm();
	        anObject.setSymbol("$");
	        anObject.setChineseName("美金");
	        //... more
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	        String requestJson=ow.writeValueAsString(anObject );

	        mockMvc.perform(post("/currenyCreate").contentType(MediaType.APPLICATION_JSON_UTF8)
	            .content(requestJson))
	            .andExpect(status().isOk());
	    }
	    
	    


}
