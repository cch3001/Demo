package com.example.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public Currency save(Currency currency) {
        return entityManager.merge(currency);
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
    public void deleteById(Long id) {
    	Currency currency  = entityManager.find(Currency.class, id);
        entityManager.remove(currency);
    }

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}

