package fr.latelierchantdefleur.outilgestion.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "element", schema = "chantdefleur")
public class Element implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_element")
	private Integer idElement;

	private Date dateAjout;
	// @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
	// CascadeType.DETACH, CascadeType.MERGE,
	// CascadeType.REFRESH })
	// @JoinTable(name = "chantdefleur.element_liste_commentaire", joinColumns =
	// @JoinColumn(name = "id_commentaire"), inverseJoinColumns = @JoinColumn(name =
	// "id_element"))
	// private List<Commentaire> commentaires;
	// @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH,
	// CascadeType.MERGE, CascadeType.REFRESH })
	// @JoinColumn(name = "id_stock")
	// private Stock stock;

	public Element() {
	}

	// public Element(List<Commentaire> commentaires, Stock stock) {
	// this.commentaires = commentaires;
	// this.stock = stock;
	// }

	public Integer getIdElement() {
		return this.idElement;
	}

	public void setIdElement(Integer idElement) {
		this.idElement = idElement;
	}

	public Date getDateAjout() {
		return this.dateAjout;
	}

	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}

	// public Stock getStock() {
	// return this.stock;
	// }
	//
	// public void setStock(Stock stock) {
	// this.stock = stock;
	// }
	//
	// public List<Commentaire> getCommentaires() {
	// return this.commentaires;
	// }
	//
	// public void setCommentaires(List<Commentaire> commentaires) {
	// this.commentaires = commentaires;
	// }
	//
	// public void addCommentaire(Commentaire commentaire) {
	// if (this.commentaires.size() == 0) {
	// this.commentaires = new ArrayList<Commentaire>();
	// }
	// this.commentaires.add(commentaire);
	// }

	// @Override
	// public String toString() {
	// return "Element [idElement=" + this.idElement + ", commentaire=" +
	// this.commentaires.get(0) + ", stock="
	// + this.stock + "]";
	// }
}
