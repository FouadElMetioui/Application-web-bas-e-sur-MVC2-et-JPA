package ma.fstt.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "panier")
public class Panier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codeP;
	private int qteItems;
	private float prix;
	
	
	//cascade = { CascadeType.ALL }
	

	public Collection<Article_Panier> getArticle_Paniers() {
		return article_Paniers;
	}

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "codeUser")
    private User user;

	public Long getCodeP() {
		return codeP;
	}


	public void setCodeP(Long codeP) {
		this.codeP = codeP;
	}


	public int getQteItems() {
		return qteItems;
	}


	public void setQteItems(int qteItems) {
		this.qteItems = qteItems;
	}


	public float getPrix() {
		return prix;
	}


	public void setPrix(float prix) {
		this.prix = prix;
	}


	public void setArticle_Paniers(Collection<Article_Panier> article_Paniers) {
		this.article_Paniers = article_Paniers;
	}


	@OneToMany(mappedBy = "panier", fetch = FetchType.LAZY)
	private Collection<Article_Panier> article_Paniers;
	
	
}
