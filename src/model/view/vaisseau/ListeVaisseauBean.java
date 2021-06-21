package model.view.vaisseau;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import model.entities.Vaisseau;
import model.facade.FacadeVaisseau;
import utils.JsfUtils;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ListeVaisseauBean {
	@Inject
	private FacadeVaisseau facadeVaisseau;
	
	@Getter @Setter @Inject
	private LazyDataModelVaisseau Vaisseaux;
	
	@PostConstruct
	public void init() {

	}
	
	public void delete(Vaisseau vaisseau) {
		try {
			this.facadeVaisseau.supprimer(vaisseau);
		} catch (Exception e) {
			JsfUtils.sendGrowlMessage(FacesMessage.SEVERITY_ERROR, "error lors de la supression de ", vaisseau);
		}
		this.init();
	}
	
	public void modifier( Vaisseau vaisseau) {
		try {
			JsfUtils.putInFlashScope("vaisseau", vaisseau);
		} catch (Exception e) {
			JsfUtils.sendGrowlMessage(FacesMessage.SEVERITY_ERROR, "erreur lors de la modification", vaisseau);
		}
		
		JsfUtils.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "Vous allez modifié l'auteur "+vaisseau.getNom(), vaisseau);
	}
}
