package model.view.usine;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import model.entities.Usine;
import model.entities.references.Localisation;
import model.facade.FacadeUsine;
import utils.JsfUtils;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class FicheUsineBean implements Serializable{
	@Inject
	private FacadeUsine facadeUsine;
	
	@Inject
	@Getter
	@Setter
	private Usine usine;
	
	@Getter
	private List<Localisation> localisation = Arrays.asList(Localisation.values());
	
	
	@PostConstruct
	public void init() {
		Usine a = (Usine) JsfUtils.getFromFlashScope("usine");
		if (a != null) {
			this.usine = a;
		}
	}
	
	public void enregistrer() {
		try {
			this.facadeUsine.sauvegarder(usine);
		} catch (Exception e) {
			JsfUtils.sendGrowlMessage(FacesMessage.SEVERITY_ERROR, "erreur lors de l'enregistrement", usine);
		}
		
		JsfUtils.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "enregistrement reussit", usine);
	}
}
