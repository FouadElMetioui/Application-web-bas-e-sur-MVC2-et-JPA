package ma.fstt.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categorie")
public class Categorie {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codeCat;
	private String nomCat;
	
	@OneToMany(mappedBy = "categorie", fetch = FetchType.LAZY)
	private Collection<Article> articles;

	public Categorie(String nomCat) {
		super();
		this.nomCat = nomCat;
	}
	
	public Categorie(Long codeCat ,String nomCat) {
		super();
		this.codeCat = codeCat ;
		this.nomCat = nomCat;
	}

	public Categorie() {
		super();
	}

	public Long getCodeCat() {
		return codeCat;
	}

	public void setCodeCat(Long codeCat) {
		this.codeCat = codeCat;
	}

	public String getNomCat() {
		return nomCat;
	}

	public void setNomCat(String nomCat) {
		this.nomCat = nomCat;
	}

	public Collection<Article> getArticles() {
		return articles;
	}

	public void setArticles(Collection<Article> articles) {
		this.articles = articles;
	}
	
	
	
}
