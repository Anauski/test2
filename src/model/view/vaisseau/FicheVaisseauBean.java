package model.view.vaisseau;

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
import model.entities.Vaisseau;
import model.entities.references.ClasseVaisseau;
import model.entities.references.TypeArme;
import model.facade.FacadeVaisseau;
import utils.JsfUtils;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class FicheVaisseauBean implements Serializable {

	@Inject
	private FacadeVaisseau facadeVaisseau;
	
	@Inject
	@Getter
	@Setter
	private Vaisseau vaisseau;

	@Getter
	private List<ClasseVaisseau> classeVaisseau = Arrays.asList(ClasseVaisseau.values());
	@Getter
	private List<TypeArme> typeArme = Arrays.asList(TypeArme.values());

	@PostConstruct
	public void init() {
		Vaisseau a = (Vaisseau) JsfUtils.getFromFlashScope("vaisseau");
		if (a != null) {
			this.vaisseau = a;
		}
	}

	public void enregistrer() {
		try {
			this.facadeVaisseau.sauvegarder(vaisseau);
		} catch (Exception e) {
			JsfUtils.sendGrowlMessage(FacesMessage.SEVERITY_ERROR, "erreur lors de l'enregistrement", vaisseau);
		}
		
		JsfUtils.sendGrowlMessage(FacesMessage.SEVERITY_INFO, "enregistrement reussit", vaisseau);
	}

}
