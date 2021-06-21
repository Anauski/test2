package model.daos;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import model.entities.Usine;

public interface UsineDao extends BaseDao<Usine, Serializable>{
	public List<Usine> list(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy);

	int count( Map<String, FilterMeta> filterBy);
}
