package com.example.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CurrencyDao;
import com.example.domain.CoindeskForm;
import com.example.domain.CurrencyInfoForm;
import com.example.model.Currency;

@Service
public class CurrencyService {
	
    @Autowired
    private CurrencyDao currencyDao;
    
	@Autowired
	private CoindeskService coindeskService;
    
    public List<Currency> getAll() {
        return currencyDao.getAll();
    }
    
    public List<CurrencyInfoForm> getCurrencyInfo() {
    	
    	List<CurrencyInfoForm> list = new ArrayList<CurrencyInfoForm>();
    	
		currencyDao.getAll().forEach((currency) -> {
			CurrencyInfoForm currencyInfoForm = new CurrencyInfoForm();
			
				try {
					BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
					BeanUtils.copyProperties(currencyInfoForm, currency);
					list.add(currencyInfoForm);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}			
		});
    			
    	return list ;
    }
    

    public Optional<Currency> getById(Long id) {
        return currencyDao.getById(id);
    }

    public Optional<Currency>  updateByCode(Currency currency) {
        return currencyDao.updateByCode(currency);
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

    public int  delete(String code) {
    	return currencyDao.deleteByCode(code);
    }
    
	public CurrencyDao getCurrencyDao() {
		return currencyDao;
	}

	public void setCurrencyDao(CurrencyDao currencyDao) {
		this.currencyDao = currencyDao;
	}
	
	public List<Currency> syncCoinDesk() throws Exception {
		
		List<Currency> list = new ArrayList<Currency>();
		CoindeskForm coindeskForm = 
		 coindeskService. parseCoinDesk();			
		 coindeskForm.getBpi().forEach((key, value) -> {
			 Currency currency = new Currency();
			 try {
				BeanUtils.copyProperties(currency, value) ;

				Optional<Currency> currencyGet =  currencyDao.updateByCode(currency) ;				
				if (currencyGet.isPresent()) {
					list.add( currencyGet.get()) ;
				}
			} catch (IllegalAccessException | InvocationTargetException e) {

				e.printStackTrace();
			}
			});
		 return list ;
	}
	

}
