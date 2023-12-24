package com.example.dao;

import java.util.List;
import java.util.Optional;

import com.example.model.Currency;

public interface CurrencyDao {
	
    public List<Currency> getAll() ;

    public Optional<Currency> getById(Long id);
    
    public Optional<Currency> findByCode(String code);
    
    public Currency save(Currency currency);
    
    //public Integer updateByCode(Currency currency);
    
    public Optional<Currency> updateByCode(Currency currency);
      
    
    public void create(Currency currency) ;

    public void delete(Currency currency) ;
    
    public int deleteByCode(String code);
    

}
