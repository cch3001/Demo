package com.example.controller;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.CurrencyForm;
import com.example.model.Currency;
import com.example.service.CurrencyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "幣別轉換")
@RestController
public class CurrencyController {
	
	    @Autowired
	    private CurrencyService currencyService;
	
	    @GetMapping
	    public List<Currency> getAllCurrencys() {
	        return currencyService.getAll();
	    }
	    
	    @ApiOperation(value = "",notes = "")
	    @PostMapping("/currenyCreate")
	    public CurrencyForm currenyCreate(@RequestBody CurrencyForm currencyForm){
	    	
	        return currencyForm;
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Currency> getCurrencyById(@PathVariable Long id) {
	        Optional<Currency> currency = currencyService.getById(id);
	        if (currency.isPresent()) {
	            return ResponseEntity.ok(currency.get());
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

//	    @PutMapping
//	    public Currency createCurrency(@RequestBody CurrencyForm currencyForm) {
//	    	Currency currency = new Currency();
//	    	try {
//				BeanUtils.copyProperties(currency, currencyForm);
//				Currency savedCurrency = currencyService.save(currency);
//				return  savedCurrency;
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//	        
//	        return  currency;
//	    }

//	    @PutMapping("/{id}")
//	    public ResponseEntity<Currency> updateCurrency(@PathVariable Long id, @RequestBody CurrencyForm currency) {
//	        Optional<Currency> existingCurrency = currencyService.getById(id);
//	        if (existingCurrency.isPresent()) {
//	        	currency.setId(id);
//	            Currency savedCurrency = currencyService.save(currency);
//	            return ResponseEntity.ok(savedCurrency);
//	        } else {
//	            return ResponseEntity.notFound().build();
//	        }
//	    }

//	    @DeleteMapping("/{id}")]= 8 
//	    public ResponseEntity<Void> deleteCurrency(@PathVariable Long id) {
//	        Optional<Currency> currency = currencyService.getById(id);
//	        if (currency.isPresent()) {
//	        	currencyService.delete(currency.get());
//	            return ResponseEntity.noContent().build();
//	        } else {
//	            return ResponseEntity.notFound().build();
//	        }
//	    }

	public CurrencyService getCurrencyService() {
		return currencyService;
	}

	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

}
