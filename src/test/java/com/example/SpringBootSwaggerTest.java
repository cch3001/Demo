package com.example;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;

import com.example.domain.CoindeskForm;
import com.example.domain.CurrencyForm;
import com.example.model.Currency;
import com.example.service.CoindeskService;
import com.example.service.CurrencyService;
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
	
	@Autowired
	private CurrencyService currencyService;

	/**
	 * create new Currency
	 * @throws Exception
	 */
	@Test
	public void testInsertObject() throws Exception {

		CurrencyForm anObject = new CurrencyForm();
//		anObject.setSymbol("$");
//		anObject.setChineseName("美金");
//		anObject.setCode("USD");
//		anObject.setRate( "43,693.4630");
//		anObject.setDescription("United States Dollar" );
//		anObject.setRateFloat(new BigDecimal(43693.463f));

		anObject.setSymbol("NT$");
		anObject.setChineseName("台幣");
		anObject.setCode("NTD");
		anObject.setRate( "143,693.4630");
		anObject.setDescription("Taiwan Dollar" );
		anObject.setRateFloat(new BigDecimal(143693.463f));
		
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
	
	
	
	/**
	 * query all
	 * @throws Exception
	 */
	 @Test
	 public void testGetCurrency() throws Exception {
	     MvcResult mvcResult = mockMvc.perform(get("/getCurrency"))
	             .andExpect(status().isOk()).andReturn();
	     System.out.println(mvcResult.getResponse().getContentAsString());
	 }
 
	/**
	 * query by primary key
	 * @throws Exception
	 */
	 @Test
	 public void testQueryById() throws Exception {
	     MvcResult mvcResult = mockMvc.perform(get("/getCurrency/{id}", "68"))
	             .andExpect(status().isOk()).andReturn();
	     System.out.println(mvcResult.getResponse().getContentAsString());
	 }
	 
	 /**
	  * update or 
	  * @throws Exception
	  */
	 @Test
	 public void testUpdateObject() throws Exception {

			CurrencyForm anObject = new CurrencyForm();
			anObject.setSymbol("$");
			anObject.setChineseName("美金");
			anObject.setCode("USD");
//			anObject.setRate( "43,693.4630");
//			anObject.setDescription("United States Dollar2" );
			BigDecimal rateFloat = new BigDecimal(43693.463f);
			anObject.setRateFloat(rateFloat);
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			String requestJson = ow.writeValueAsString(anObject);

			MvcResult mvcResult = 
			mockMvc.perform(put("/updateCurrency").contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson))
					.andExpect(status().isOk()).andReturn();
			System.out.println(mvcResult.getResponse().getContentAsString());

	 }
	 
	 /**
	  * 依照幣別刪除資料
	  * @throws Exception
	  */
	 @Test
	 public void testDeleteByCode() throws Exception {
	     MvcResult mvcResult = mockMvc.perform(delete("/deleteCurrency/{code}", "USD"))
	             .andExpect(status().isOk()).andReturn();
	     System.out.println(mvcResult.getResponse().getContentAsString());
	 }
	 
	 /**
	  * coindesk call test
	  * @throws Exception
	  */
	 @Test
	 public void testCoindeskService() throws Exception {
		 CoindeskForm coindeskForm = 
		 coindeskService. parseCoinDesk();
		 
		 Assert.notEmpty(coindeskForm.getBpi());
	 }
	 
	 /**
	  * 同步 coindesk data
	  * @throws Exception
	  */
	 @Test
	 public void testSyncCoinDesk() throws Exception {

		 List<Currency> list = currencyService.syncCoinDesk();
		 
		 Assert.notEmpty(list);
	 }
	 
	 /**
	  * API查詢 currency 資料
	  * @throws Exception
	  */
	 @Test
	 public void testGetCurrencyInfo() throws Exception {
	     MvcResult mvcResult = mockMvc.perform(get("/getCurrencyInfo"))
	             .andExpect(status().isOk()).andReturn();

	     System.out.println(mvcResult.getResponse().getContentAsString());
	  
	 }
	 
	 

}
