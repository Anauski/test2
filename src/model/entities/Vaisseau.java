package model.entities;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.entities.references.C;
import model.entities.references.ClasseVaisseau;
import model.entities.references.TypeArme;
@SuppressWarnings("serial")
@NoArgsConstructor
@Entity
@Table(name = "VAISSEAU")
public class Vaisseau {
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

	@Getter @Setter
	@NotBlank(message = C.NOM_NOT_BLANK)
	@Column(name="TAILLE",nullable = false)
	@Length(max = 200000)
    private BigDecimal taille;
	
	@Getter @Setter
	@NotBlank(message = C.NOM_NOT_BLANK)
	@Column(name="VITESSE",nullable = false)
	@Length(min=100, max = 10000, message= "La Valeur doit etre comprise entre 100 et 10000 Km/h")
    private BigDecimal vitesse;
    
	@Getter @Setter
	@NotBlank(message = C.NOM_NOT_BLANK)
	@Column(name="NBEQUIPAGE",nullable = false)
    private BigDecimal nbEquipage;
    
	@Getter @Setter
	@Column(name="TYPESARME",nullable = false)
    private Set<TypeArme> typesArme;
    
	@Getter @Setter
	@JoinColumn(name="HYPERDRIVE",nullable = false)
    private boolean hyperdrive;
	
	@Getter @Setter
	@Column(name="CLASSE", nullable = false)
    private ClasseVaisseau classe;
    
	
	@Getter
	@Setter
	@Column(name = "PHOTO", nullable = true)
    private byte[] photo;
}
