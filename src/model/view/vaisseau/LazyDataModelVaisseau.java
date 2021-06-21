package model.view.vaisseau;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import model.entities.Vaisseau;
import model.facade.FacadeVaisseau;
@SuppressWarnings("serial")

public class LazyDataModelVaisseau extends LazyDataModel<Vaisseau>{
	@Inject
	private FacadeVaisseau facadeVaisseau;
 	
	@Override
	public List<Vaisseau> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		List<Vaisseau> list = facadeVaisseau.list(first, pageSize, sortBy, filterBy);
		this.setRowCount((int) this.facadeVaisseau.count(filterBy));
		return list;
	}

}
