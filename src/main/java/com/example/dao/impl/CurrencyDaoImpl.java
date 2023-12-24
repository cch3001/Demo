package com.example.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.BeanUtils;
//import org.apache.commons.beanutils.BeanUtils;
//import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.CurrencyDao;
import com.example.model.Currency;

@Repository
public class CurrencyDaoImpl implements  CurrencyDao{

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Currency> getAll() {
        return entityManager.createQuery("SELECT c FROM Currency c", Currency.class).getResultList();
    }

    @Override
    public Optional<Currency> getById(Long id) {
        return Optional.ofNullable(entityManager.find(Currency.class, id));
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public Optional<Currency> findByCode(String code) {
        TypedQuery<Currency> query = entityManager.createQuery(
            "SELECT t FROM Currency t WHERE t.code=:code",
            Currency.class);
        return Optional.ofNullable(query.setParameter("code", code).getSingleResult() );
      }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public Currency save(Currency currency) {
        return entityManager.merge(currency);
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public  Optional<Currency>  updateByCode(Currency currency) {
    	
        TypedQuery<Currency> query = entityManager.createQuery(
                "SELECT t FROM Currency t WHERE t.code=:code",
                Currency.class);
        Currency getCurrency = 
        query.setParameter("code", currency.getCode()).getSingleResult() ;      
        Currency savedCurrency = null;
    	try {
    		copyPropertiesIgnoreNull(currency,getCurrency);
			savedCurrency = entityManager.merge(getCurrency);
 			
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return  Optional.ofNullable(savedCurrency) ;
    }
    
    @Transactional(propagation=Propagation.REQUIRED)
    public Integer updateByCode2(Currency currency) {
    		  Query query = entityManager.createQuery(
                "UPDATE Currency SET symbol=:symbol,rate=:rate,description=:description "
                + " ,rateFloat=:rateFloat,chineseName=:chineseName"
                + " WHERE code=:code",
                Currency.class);
        
        int updateCount = query.setParameter("code",currency.getCode())
        		.setParameter("symbol",currency.getSymbol())
        		.setParameter("rate",currency.getRate())
        		.setParameter("description",currency.getDescription())
        		.setParameter("rateFloat",currency.getRateFloat())
        		.setParameter("chineseName",currency.getChineseName())
        		.executeUpdate();
        
        return updateCount;
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void create(Currency currency) {
         entityManager.persist(currency);
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void delete(Currency currency) {
        entityManager.remove(currency);
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public int deleteByCode(String code) {
    	
        Query query = entityManager.createQuery(
      	      "DELETE FROM Currency  t WHERE t.code=:code ");
      	  int deletedCount = query.setParameter("code",code).executeUpdate();

        return deletedCount ;
    }
    


	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

    private static String[] getNullPropertyNames (Object source) {
        final BeanWrapperImpl src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
 
        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
 
    private static void copyPropertiesIgnoreNull(Object src, Object target){
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

}

