package com.example.dao;

import java.util.List;
import java.util.Optional;

import com.example.model.Currency;

public interface CurrencyDao {
	
    public List<Currency> getAll() ;

    public Optional<Currency> getById(Long id);
    
    public Currency save(Currency currency);
    
    public void create(Currency currency) ;

    public void delete(Currency currency) ;
    
    public void deleteById(Long id);

}
