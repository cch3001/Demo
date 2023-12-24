package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.example.domain.CurrencyInfoForm;
import com.example.model.Currency;
import com.example.service.CurrencyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "幣別轉換")
@RestController
public class CurrencyController {
	
	 private static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);

	@Autowired
	private CurrencyService currencyService;

	@GetMapping("/getCurrency")
	public List<Currency> getAllCurrencys() {
		
		logger.debug("getAllCurrencys");
		List<Currency> list =currencyService.getAll();

		return list ;
	}

	@ApiOperation(value = "新增", notes = "建立新幣資料")
	@PostMapping("/currenyCreate")
	public ResponseEntity<Currency> currenyCreate(@RequestBody CurrencyForm currencyForm) {
		
		logger.debug("currenyCreate");

		Currency currency = new Currency();
		try {
			BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
			BeanUtils.copyProperties(currency, currencyForm);
			Currency savedCurrency = currencyService.save(currency);

			if (savedCurrency != null) {
				return ResponseEntity.ok(currency);
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.noContent().build();

	}

	@GetMapping("/getCurrency/{id}")
	@ApiOperation(value = "查詢幣別", notes = "查詢幣別資訊")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", 
			value = "Primary key", 
			required = true, 
			paramType = "id") })
	public ResponseEntity<Currency> getCurrencyById(@PathVariable Long id) {
		
		logger.debug("getCurrencyById");	
		Optional<Currency> currency = currencyService.getById(id);
		if (currency.isPresent()) {
			return ResponseEntity.ok(currency.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@ApiOperation(value = "更新幣別", notes = "更新幣別資訊")
	@PutMapping("/updateCurrency")
	public ResponseEntity<Currency> updateCurrency(@RequestBody CurrencyForm currencyForm) {
		
		logger.debug("updateCurrency");
		Currency currency = new Currency();
		try {
			BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
			BeanUtils.copyProperties(currency, currencyForm);
			Optional<Currency> savedCurrency = currencyService.updateByCode(currency);

			if (savedCurrency.isPresent()) {
				return ResponseEntity.ok(savedCurrency.get());
			} else {
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.noContent().build();

	}

	@ApiOperation(value = "刪除", notes = "刪除幣別資訊")
	@DeleteMapping("/deleteCurrency/{code}")
	public ResponseEntity<Integer> deleteCurrency(@PathVariable String code) {
		
		logger.debug("deleteCurrency");
		int count = currencyService.delete(code);
		if (count > 0) {
			return ResponseEntity.ok(count);
		} else {
			return ResponseEntity.notFound().build();
		}

	}
	
	@ApiOperation(value = "查詢", notes = "查詢轉換幣別資訊")
	@GetMapping("/getCurrencyInfo")
	public ResponseEntity<List<CurrencyInfoForm>> getCurrencyInfo() {
		
		logger.debug("getCurrencyInfo");
		//同步CoinDesk
		try {
			currencyService.syncCoinDesk() ;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
			
		List<CurrencyInfoForm> list = currencyService.getCurrencyInfo();
		
		if (list.size() > 0) {
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	public CurrencyService getCurrencyService() {
		return currencyService;
	}

	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

}
