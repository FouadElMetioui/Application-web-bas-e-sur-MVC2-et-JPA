package ma.fstt.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


// @JsonIgnore : before evry OneToMany


@Entity
@Table(name = "article")
public class Article implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codeArt;
	private String nomArt;
	private float pu;
	private int qteStock;
	private String path ;  
	
	
	
	
	@ManyToOne
	@JoinColumn(name = "codeCat")
	private Categorie categorie;

	
	@OneToMany(mappedBy ="article", fetch = FetchType.LAZY)
	private Collection<Article_Panier> article_paniers;
	
//	@OneToMany(mappedBy ="article", fetch = FetchType.LAZY)
//	private Collection<Article_Transaction> article_transactions;
	
	public Long getCodeArt() {
		return codeArt;
	}
	public void setCodeArt(Long codeArt) {
		this.codeArt = codeArt;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public String getNomArt() {
		return nomArt;
	}
	public void setNomArt(String nomArt) {
		this.nomArt = nomArt;
	}
	public float getPu() {
		return pu;
	}
	public void setPu(float pu) {
		this.pu = pu;
	}
	public int getQteStock() {
		return qteStock;
	}
	public void setQteStock(int qteStock) {
		this.qteStock = qteStock;
	}
	
	public Article( String nomArt, float pu, int qteStock ,Categorie categorie) {
		super();
		this.nomArt = nomArt;
		this.pu = pu;
		this.qteStock = qteStock;
		this.categorie =categorie;
	}
	
	public Article( Long codeArt ,String nomArt, float pu, int qteStock ,Categorie categorie, String path) {
		super();
		this.codeArt = codeArt;
		this.nomArt = nomArt;
		this.pu = pu;
		this.qteStock = qteStock;
		this.categorie =categorie;
		this.path = path;
	}
	
	public Article( String nomArt, float pu, int qteStock ,Categorie categorie ,String path) {
		super();
		
		this.nomArt = nomArt;
		this.pu = pu;
		this.qteStock = qteStock;
		this.categorie =categorie;
		this.path = path;
	}
	
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Article() {
		super();
	}
	
	public Long getCodeCat() {
		return this.categorie.getCodeCat();
		
	}

	

	
	

}
