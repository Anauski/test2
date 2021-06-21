package model.daos;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import model.entities.Vaisseau;

public interface VaisseauDao extends BaseDao<Vaisseau, Serializable>{
	public List<Vaisseau> list(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy);

	int count( Map<String, FilterMeta> filterBy);
}
