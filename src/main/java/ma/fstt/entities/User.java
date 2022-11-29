package ma.fstt.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codeUser;
	
	@Column(nullable = true)
	private String nom;
	
	@Column(nullable = true)
	private String addr;
	
	@Column(nullable = true)
	private String tel;
	
	@Column(nullable = true)
	private String ville;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(columnDefinition = "integer default 0")
	private int role;
	
	@OneToOne(mappedBy = "user")
    private Panier panier;
	
//	@OneToMany(mappedBy ="user", fetch = FetchType.LAZY)
//	private Collection<Transaction> transaction;
//	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(String nom, String addr, String tel, String ville, String email, String password, int role) {
		super();
		this.nom = nom;
		this.addr = addr;
		this.tel = tel;
		this.ville = ville;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public User(String nom, String addr, String tel, String ville, String email, String password) {
		super();
		this.nom = nom;
		this.addr = addr;
		this.tel = tel;
		this.ville = ville;
		this.email = email;
		this.password = password;
	}


	public Long getCode() {
		return codeUser;
	}


	public void setCode(Long codeUser) {
		this.codeUser = codeUser;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public String getEmail() {
		return email;
	}


	@Override
	public String toString() {
		return "User [nom=" + nom + ", addr=" + addr + ", tel=" + tel + ", ville=" + ville + ", email=" + email
				+ ", password=" + password + ", role=" + role +  "]";
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getRole() {
		return role;
	}


	public void setRole(int role) {
		this.role = role;
	}
	
	
	
	
	
	
	
}
