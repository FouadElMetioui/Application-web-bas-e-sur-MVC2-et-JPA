package ma.fstt.entities;



import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "article_panier")
public class Article_Panier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codeAP;
	private int qte;
	private float prix;
	
		
	public Long getCodeAP() {
		return codeAP;
	}

	public void setCodeAP(Long codeAP) {
		this.codeAP = codeAP;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "code_art")
	private Article article;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "code_p")
	private Panier panier;
	
	//cascade = { CascadeType.ALL }
}
