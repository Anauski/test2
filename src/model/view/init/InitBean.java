package model.view.init;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.github.javafaker.Faker;

import model.entities.Usine;
import model.entities.Vaisseau;
import model.entities.references.ClasseVaisseau;
import model.entities.references.Localisation;
import model.entities.references.TypeArme;
import model.facade.FacadeUsine;
import model.facade.FacadeVaisseau;

@Startup
@Singleton
public class InitBean {
	@Inject
	private FacadeUsine facadeUsine;
	@Inject
	private FacadeVaisseau facadeVaisseau;
	
	@PostConstruct
	public void init() {
		Faker faker = new Faker(new Locale("fr"));
		List<Usine> lsUsine = new ArrayList<Usine>();
		List<Vaisseau> lsVaisseau = new ArrayList<Vaisseau>();
		
		//init Vaisseau
		if (this.facadeVaisseau.listerVaisseaux().size() == 0 || this.facadeVaisseau.listerVaisseaux() == null) {
			for (int i = 0; i < 30; i++) {
				lsVaisseau.get(i).setNom(faker.book().title());
				lsVaisseau.get(i).setTaille(generateRandomBigDecimalFromRange(0, 20000));
				lsVaisseau.get(i).setVitesse(generateRandomBigDecimalFromRange(100, 10000));
				lsVaisseau.get(i).setNbEquipage(generateRandomBigDecimalFromRange(1, 20));
//				lsVaisseau.get(i).setTypesArme(set<TypesArme>);
				lsVaisseau.get(i).setClasse(recupererRandomClasse());


				
			}

			for (Vaisseau v : lsVaisseau) {
				this.facadeVaisseau.sauvegarder(v);
			}
		}
		//init Usine
				if (this.facadeUsine.listerUsines().size() == 0 || this.facadeUsine.listerUsines() == null) {
					for (int i = 0; i < 15; i++) {
						lsUsine.get(i).setNom(faker.book().title());
						lsUsine.get(i).setLocalisation(recupererRandomLocalisation());
//						lsUsine.get(i).setListeVaisseauFabricable();
					}

					for (Usine u : lsUsine) {
						this.facadeUsine.sauvegarder(u);
					}
				}
		
	}

	private ClasseVaisseau recupererRandomClasse() {
		Random random = new Random();
		ClasseVaisseau[] tab= ClasseVaisseau.values();
		int num = random.nextInt(tab.length);
		return tab[num];
	}
	

	private TypeArme recupererRandomTypesArme() {
		Random random = new Random();
		TypeArme [] tab= TypeArme.values();
		int num = random.nextInt(tab.length);
		return tab[num];
	}
	
	private Localisation recupererRandomLocalisation() {
		Random random = new Random();
		Localisation[] tab = Localisation.values();
		int num = random.nextInt(tab.length);
		return tab[num];

	}
	
	private Vaisseau recupererRandomVaisseau() {
		Random random = new Random();
		int num = random.nextInt(this.facadeVaisseau.listerVaisseaux().size());
		return this.facadeVaisseau.listerVaisseaux().get(num);
	}
	

	
	public BigDecimal generateRandomBigDecimalFromRange(float min, float max) {
		return new BigDecimal(Math.random() * (max - min) + min);
	   
	}
}
