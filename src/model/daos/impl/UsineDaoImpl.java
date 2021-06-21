package model.daos.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.TypedQuery;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import model.daos.JpaBaseDao;
import model.daos.UsineDao;
import model.entities.Usine;
import model.entities.references.Localisation;

public class UsineDaoImpl  extends JpaBaseDao<Usine,Long> implements UsineDao{
	
	
	@Override
	public List<Usine> list(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		
		String sql ="SELECT a FROM Usine a  WHERE 1=1";
		String nom =null ;
	
		
		Localisation localisation = null;
		
		if (filterBy.containsKey("nom")) {
			nom=(String) filterBy.get("nom").getFilterValue();
		}
		
		if (filterBy.containsKey("localisation")) {
			localisation=(Localisation) filterBy.get("localisation").getFilterValue();
		}
		
		if(nom !=null) {
			sql += " and a.nom like :nom";
		}
		
		if(localisation !=null) {
			sql += " and a.localisation like :localisation";
		}
		if (!sortBy.isEmpty()) {
			sql += " ORDER BY ";
			for (Entry<String, SortMeta> e : sortBy.entrySet()) {
				sql += e.getValue().getField() + " " +(e.getValue().getOrder().equals(SortOrder.ASCENDING) ? "ASC" : "DESC") + ",";
				
			}
			sql = sql.substring(0, sql.length()-1);
		}
		
		System.out.println(sql);
		
		
		TypedQuery<Usine> q = this.entityManager.createQuery(sql, Usine.class);
		
		if(nom !=null) {
			q.setParameter("nom", nom+"%");
		}
		
		if(localisation !=null) {
			q.setParameter("localisation", localisation+"%");
		}
		q.setFirstResult(first);
		q.setMaxResults(pageSize);
		
		return q.getResultList();
	}
	@Override
	public int count(Map<String, FilterMeta> filterBy) {
		String sql ="SELECT count(a) FROM Usine a  WHERE 1=1";
		String nom =null ;
		
		
		
		if (filterBy.containsKey("nom")) {
			nom=(String) filterBy.get("nom").getFilterValue();
		}
		
		
		if(nom !=null) {
			sql += " and a.nom like :nom";
		}
		
		
		
		TypedQuery<Long> q = this.entityManager.createQuery(sql, Long.class);
		
		if(nom !=null) {
			q.setParameter("nom", nom+"%");
		}
		
				
		
		return q.getSingleResult().intValue();
		
	}
}
