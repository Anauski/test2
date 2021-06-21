package model.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.entities.references.C;
import model.entities.references.Localisation;
@SuppressWarnings("serial")
@NoArgsConstructor
@Entity
@Table(name = "USINE")
public class Usine {
	
	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Getter @Setter
	@NotBlank(message = C.NOM_NOT_BLANK)
	@Column(name="NOM",nullable = false)
	@Length(max = 255)
    private String nom;
    
	@NotBlank(message = C.NOM_NOT_BLANK)
	@Getter @Setter
	@JoinColumn(name="LOCALISATION",nullable = false)
    private Localisation localisation;
    
	@Getter @Setter
	@OneToMany
	@JoinColumn(name="LISTE_VAISSEAU",nullable = false)
    private Set<Vaisseau> listeVaisseauFabricable;
}
