package com.example;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.domain.CoindeskForm;
import com.example.domain.CurrencyForm;
import com.example.service.CoindeskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootSwaggerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private CoindeskService coindeskService;

	@Test
	public void testInsertObject() throws Exception {

		CurrencyForm anObject = new CurrencyForm();
		anObject.setSymbol("$");
		anObject.setChineseName("美金");
		anObject.setCode("USD");
		anObject.setRate( "43,693.4630");
		anObject.setDescription("United States Dollar" );
		anObject.setRateFloat(new BigDecimal(43693.463f));
		
//		anObject.setSymbol("£");
//		anObject.setChineseName("英鎊");
//		anObject.setCode("GBP");
//		anObject.setRate( "36,557.5383");
//		anObject.setDescription("British Pound Sterling" );
	 
		anObject.setRateFloat(new BigDecimal(36557.5383f));
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(anObject);

		mockMvc.perform(post("/currenyCreate").contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson))
				.andExpect(status().isOk());
	}
 
	 
	 @Test
	 public void testQueryById() throws Exception {
	     MvcResult mvcResult = mockMvc.perform(get("/getCurrency/{id}", "68"))
	             .andExpect(status().isOk()).andReturn();
	     
	    // Assert.
	     //Assert.assertEquals("foo and bar", mvcResult.getResponse().getContentAsString());

	 }
	 
	 @Test
	 public void testUpdateObject() throws Exception {

			CurrencyForm anObject = new CurrencyForm();
			anObject.setSymbol("$");
			anObject.setChineseName("美金");
			anObject.setCode("USD");
			anObject.setRate( "43,693.4630");
			anObject.setDescription("United States Dollar2" );
			BigDecimal rateFloat = new BigDecimal(43693.463f);
			anObject.setRateFloat(rateFloat);
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			String requestJson = ow.writeValueAsString(anObject);

			mockMvc.perform(put("/updateCurrency").contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson))
					.andExpect(status().isOk());

	 }
	 
	 @Test
	 public void testDeleteByCode() throws Exception {
	     MvcResult mvcResult = mockMvc.perform(delete("/deleteCurrency/{code}", "USD"))
	             .andExpect(status().isOk()).andReturn();
	     
	    // Assert.
	     //Assert.assertEquals("foo and bar", mvcResult.getResponse().getContentAsString());

	 }
	 
	 @Test
	 public void testCoindeskService() throws Exception {
		 coindeskService. parseCoinDesk();
	 }
	 
	 

}
