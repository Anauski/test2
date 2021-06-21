package model.facade;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.collections4.IterableUtils;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import model.daos.VaisseauDao;
import model.entities.Vaisseau;

@Stateless
public class FacadeVaisseau {
	
	@Inject
	private VaisseauDao daoVaisseau;
	
	public List<Vaisseau> listerVaisseaux() {
		return IterableUtils.toList(this.daoVaisseau.findAll());
	}

	public Optional<Vaisseau> getVaisseauById(Long id) {
		return Optional.ofNullable(this.daoVaisseau.findOne(id));
	}
	
	public Vaisseau sauvegarder(Vaisseau vaisseau) {
		return this.daoVaisseau.save(vaisseau);
	}
	
	public void supprimer(Vaisseau vaisseau) throws Exception {
		try {
			this.daoVaisseau.delete(vaisseau);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	public List<Vaisseau> list(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		return this.daoVaisseau.list(first, pageSize, sortBy, filterBy);
	}
	
	public long count(Map<String, FilterMeta> filterBy) {
		return this.daoVaisseau.count( filterBy);
	}
}
