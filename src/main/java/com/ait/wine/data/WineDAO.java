package com.ait.wine.data;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ait.wine.model.Wine;



@Stateless
@LocalBean
public class WineDAO {
	
	@Inject
    private Logger log;

    @PersistenceContext
    private EntityManager em;
    
	public List<Wine> getAllWines() {
    	Query query=em.createQuery("SELECT w FROM Wine w");
    	log.info("Result size is: "+query.getResultList().size());
        return query.getResultList();
    }
	
	public List<Wine> getWinesByName(String name) {
    	Query query=em.createQuery("SELECT w FROM Wine AS w "+
    								"WHERE w.name LIKE ?1");
    	query.setParameter(1, "%"+name.toUpperCase()+"%");
        return query.getResultList();
    }
	
	public Wine getWine(int id ) {
        return em.find(Wine.class, id);
    }
	
	public void save(Wine wine){
		em.persist(wine);
	}
	
	public void update(Wine wine) {
		em.merge(wine);
	}
	
	public void delete(int id) {
		em.remove(getWine(id));
	}
      
}

