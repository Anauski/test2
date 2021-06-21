package model.facade;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.collections4.IterableUtils;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import model.daos.UsineDao;
import model.entities.Usine;

@Stateless
public class FacadeUsine {
	@Inject
	private UsineDao daoUsine;
	
	
	public List<Usine> listerUsines() {
		return IterableUtils.toList(this.daoUsine.findAll());
	}

	public Optional<Usine> getUsineById(Long id) {
		return Optional.ofNullable(this.daoUsine.findOne(id));
	}
	
	public Usine sauvegarder(Usine usine) {
		return this.daoUsine.save(usine);
	}
	
	public void supprimer(Usine usine) throws Exception {
		try {
			this.daoUsine.delete(usine);
		} catch (Exception e) {
			throw new Exception(e);
		}
		
	}
	public List<Usine> list(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		return this.daoUsine.list(first, pageSize, sortBy, filterBy);
	}
	
	public long count(Map<String, FilterMeta> filterBy) {
		return this.daoUsine.count( filterBy);
	}
}
