package model.view.usine;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import model.entities.Usine;
import model.facade.FacadeUsine;

@SuppressWarnings("serial")
public class LazyDataModelUsine extends LazyDataModel<Usine>{
	@Inject
	private FacadeUsine facadeUsine;
	@Override
	public List<Usine> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		List<Usine> list = facadeUsine.list(first, pageSize, sortBy, filterBy);
		this.setRowCount((int) this.facadeUsine.count(filterBy));
		return list;
	}
}
