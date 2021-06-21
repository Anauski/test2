package model.view.usine;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import model.entities.Usine;
import model.facade.FacadeUsine;
import utils.JsfUtils;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ListeUsineBean implements Serializable {

	@Inject
	private FacadeUsine facadeUsine;
	
	@Getter @Setter @Inject
	private LazyDataModelUsine usine;
	
	@PostConstruct
	public void init() {

	}
	
	public void delete(Usine usine) {
		try {
			this.facadeUsine.supprimer(usine);
		} catch (Exception e) {
			JsfUtils.sendGrowlMessage(FacesMessage.SEVERITY_ERROR, "error lors de la supression de ", usine);
		}
		this.init();
	}
	
	public void modifier(Usine usine) {
		try {
			JsfUtils.putInFlashScope("usine", usine);
		} catch (Exception e) {
			JsfUtils.sendGrowlMessage(FacesMessage.SEVERITY_ERROR, "erreur lors de la modification", usine);
		}
		
		JsfUtils.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Vous allez modifié l'usisne "+usine.getNom(), usine);
	}
}
