package model.daos.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.TypedQuery;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import model.daos.JpaBaseDao;
import model.daos.VaisseauDao;
import model.entities.Vaisseau;
import model.entities.references.ClasseVaisseau;

public class VaisseauDaoImpl extends JpaBaseDao<Vaisseau,Long> implements VaisseauDao {

	@Override
	public List<Vaisseau> list(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		
		String sql ="SELECT a FROM Vaisseau a  WHERE 1=1";
		String nom =null ;
		BigDecimal taille = null;
		BigDecimal vitesse = null;
		BigDecimal nbEquipage = null;
		boolean hyperdrive = false;
		ClasseVaisseau classe = null;
		
		
		if (filterBy.containsKey("nom")) {
			nom=(String) filterBy.get("nom").getFilterValue();
		}
		if (filterBy.containsKey("taille")) {
			taille=(BigDecimal) filterBy.get("taille").getFilterValue();
		}
		if (filterBy.containsKey("vitesse")) {
			vitesse=(BigDecimal) filterBy.get("vitesse").getFilterValue();
		}
		if (filterBy.containsKey("nbEquipage")) {
			nbEquipage=(BigDecimal) filterBy.get("nbEquipage").getFilterValue();
		}
		if (filterBy.containsKey("hyperdrive")) {
			hyperdrive=(boolean) filterBy.get("hyperdrive").getFilterValue();
		}
		if (filterBy.containsKey("classe")) {
			classe=(ClasseVaisseau) filterBy.get("classe").getFilterValue();
		}
		
		if(nom !=null) {
			sql += " and a.nom like :nom";
		}
		if(taille !=null) {
			sql += " and a.taille like :taille";
		}
		if(vitesse !=null) {
			sql += " and a.vitesse like :vitesse";
		}
		if(nbEquipage !=null) {
			sql += " and a.nbEquipage like :nbEquipage";
		}
		if(hyperdrive !=false) {
			sql += " and a.hyperdrive like :hyperdrive";
		}
		
		if(classe !=null) {
			sql += " and a.classe like :classe";
		}
		if (!sortBy.isEmpty()) {
			sql += " ORDER BY ";
			for (Entry<String, SortMeta> e : sortBy.entrySet()) {
				sql += e.getValue().getField() + " " +(e.getValue().getOrder().equals(SortOrder.ASCENDING) ? "ASC" : "DESC") + ",";
				
			}
			sql = sql.substring(0, sql.length()-1);
		}
		
		System.out.println(sql);
		
		
		TypedQuery<Vaisseau> q = this.entityManager.createQuery(sql, Vaisseau.class);
		
		if(nom !=null) {
			q.setParameter("nom", nom+"%");
		}
		if(taille !=null) {
			q.setParameter("taille", taille+"%");
		}
		if(vitesse !=null) {
			q.setParameter("vitesse", vitesse+"%");
		}
		if(nbEquipage !=null) {
			q.setParameter("nbEquipage", nbEquipage+"%");
		}
		if(hyperdrive !=false) {
			q.setParameter("hyperdrive", hyperdrive+"%");
		}
		
		if(classe !=null) {
			q.setParameter("prenom", classe+"%");
		}
		
		
		q.setFirstResult(first);
		q.setMaxResults(pageSize);
		
		return q.getResultList();
	}
	@Override
	public int count(Map<String, FilterMeta> filterBy) {
		String sql ="SELECT count(a) FROM Vaisseau a  WHERE 1=1";
		String nom =null ;
		BigDecimal taille = null;
		BigDecimal vitesse = null;
		BigDecimal nbEquipage = null;
		
		if (filterBy.containsKey("nom")) {
			nom=(String) filterBy.get("nom").getFilterValue();
		}
		if (filterBy.containsKey("taille")) {
			taille=(BigDecimal) filterBy.get("taille").getFilterValue();
		}
		if (filterBy.containsKey("vitesse")) {
			vitesse=(BigDecimal) filterBy.get("vitesse").getFilterValue();
		}
		if (filterBy.containsKey("nbEquipage")) {
			nbEquipage=(BigDecimal) filterBy.get("nbEquipage").getFilterValue();
		}
		
		if(nom !=null) {
			sql += " and a.nom like :nom";
		}
		if(taille !=null) {
			sql += " and a.taille like :taille";
		}
		if(vitesse !=null) {
			sql += " and a.vitesse like :vitesse";
		}
		if(nbEquipage !=null) {
			sql += " and a.nbEquipage like :nbEquipage";
		}
		
		TypedQuery<Long> q = this.entityManager.createQuery(sql, Long.class);
		
		if(nom !=null) {
			q.setParameter("nom", nom+"%");
		}
		if(taille !=null) {
			q.setParameter("taille", taille+"%");
		}
		if(vitesse !=null) {
			q.setParameter("vitesse", vitesse+"%");
		}
		if(nbEquipage !=null) {
			q.setParameter("nbEquipage", nbEquipage+"%");
		}
		
		
		return q.getSingleResult().intValue();
		
	}

}

