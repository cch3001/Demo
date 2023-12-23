package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CurrencyDao;
import com.example.model.Currency;

@Service
public class CurrencyService {
	
    @Autowired
    private CurrencyDao currencyDao;
    
    

    public List<Currency> getAll() {
        return currencyDao.getAll();
    }


    public Optional<Currency> getById(Long id) {
        return currencyDao.getById(id);
    }


    public Currency save(Currency currency) {
    	return currencyDao.save(currency);
    }
    
    public void create(Currency currency) {
    	currencyDao.create(currency);
    }
    

    public void delete(Currency currency) {
    	currencyDao.delete(currency);
    }
    
    

	public CurrencyDao getCurrencyDao() {
		return currencyDao;
	}

	public void setCurrencyDao(CurrencyDao currencyDao) {
		this.currencyDao = currencyDao;
	}

}
