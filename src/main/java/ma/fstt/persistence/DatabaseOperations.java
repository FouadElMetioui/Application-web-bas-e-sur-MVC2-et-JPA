package ma.fstt.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ma.fstt.entities.Article;
import ma.fstt.entities.Categorie;
import ma.fstt.entities.User;

public class DatabaseOperations {

	private static final String PERSISTENCE_UNIT_NAME = "unit";
	private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
			.createEntityManager();
	private static EntityTransaction transactionObj = entityMgrObj.getTransaction();

	public DatabaseOperations() {
		super();
	}

	// toutes les fonctions qui permettent d'acceder a la base de donnees

	// 1- Article
	
	
	
	public Article findArticle(Long id) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}
		Article article = entityMgrObj.find(Article.class, id);
		transactionObj.commit();
		if (article != null) {
			return article;
		} else {
			return null;
		}
	}
	

	public static Article creerArticle(String nomArt, float pu, int qteStock , Categorie categorie , String img) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}

		Article article = new Article(nomArt, pu, qteStock,categorie , img);
		System.out.println("ajouter article : nomArt"+nomArt+", pu : " +pu);

		entityMgrObj.persist(article);
		transactionObj.commit();
		return article;
	}

	@SuppressWarnings("unchecked")
	public static List getAllArticles() {
		Query queryObj = entityMgrObj.createQuery("SELECT a from Article a");
		List<Article> lList = queryObj.getResultList();

		if (lList != null && lList.size() > 0) {
			return lList;
		} else {
			return null;
		}
	}


	
	public void deleteArticle(Article article) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}
		if (!entityMgrObj.contains(article)) {
			article = entityMgrObj.merge(article);
		}
		entityMgrObj.remove(article);
		transactionObj.commit();
	}
	
	
	public String updateArticle(Article article){
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}
		Article newArticle = entityMgrObj.find(Article.class, article.getCodeArt());
		newArticle.setNomArt(article.getNomArt());
		newArticle.setPu(article.getPu());
		newArticle.setQteStock(article.getQteStock());
		newArticle.setCategorie(article.getCategorie());
		newArticle.setPath(article.getPath());
		transactionObj.commit();
		return "true";
	}
	
	
	// 2- User
	
	
	public User findUser(Long id) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}
		User user = entityMgrObj.find(User.class, id);
		transactionObj.commit();
		if (user != null) {
			return user;
		} else {
			return null;
		}
	}
	
	
	public static User creerUser(User user) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}
		entityMgrObj.persist(user);
		transactionObj.commit();
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public static List getAllUsers() {
		Query queryObj = entityMgrObj.createQuery("SELECT u from User u");
		List<User> lList = queryObj.getResultList();

		if (lList != null && lList.size() > 0) {
			return lList;
		} else {
			return null;
		}
	}
	
//	public void deleteUser(User user) {
//		if (!transactionObj.isActive()) {
//			transactionObj.begin();
//		}
//		if (!entityMgrObj.contains(user)) {
//			user = entityMgrObj.merge(user);
//		}
//		entityMgrObj.remove(user);
//		transactionObj.commit();
//	}

	
//	public String updateUser(User u){
//		if (!transactionObj.isActive()) {
//			transactionObj.begin();
//		}
//		User user = entityMgrObj.find(User.class, u.getCode());
//		user.setNom(u.getNom());
//		user.setTel(u.getTel());
//		user.setAddr(u.getAddr());
//		user.setVille(u.getVille());
//		user.setNom(u.getEmail());
//		user.setPassword(u.getPassword());
//		transactionObj.commit();
//		return "true";
//	}
	


	// 3- Categorie
	
	
	
	public Categorie findCategorie(Long id) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}
		Categorie categorie = entityMgrObj.find(Categorie.class, id);
		transactionObj.commit();
		if (categorie != null) {
			return categorie;
		} else {
			return null;
		}
	}


	public static Categorie creerCategorie(String nomCat) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}

		Categorie categorie = new Categorie(nomCat);
		System.out.println("ajouter categorie : nomCat"+nomCat);

		entityMgrObj.persist(categorie);
		transactionObj.commit();
		return categorie;
	}
	

	@SuppressWarnings("unchecked")
	public static List getAllCategories() {
		Query queryObj = entityMgrObj.createQuery("SELECT c from Categorie c");
		List<Categorie> lList = queryObj.getResultList();

		if (lList != null && lList.size() > 0) {
			return lList;
		} else {
			return null;
		}
	}

	public void deleteCategorie(Categorie categorie) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}
		if (!entityMgrObj.contains(categorie)) {
			categorie = entityMgrObj.merge(categorie);
		}
		entityMgrObj.remove(categorie);
		transactionObj.commit();
	}
	
	
		
	
	public String updateCategorie(Categorie categorie){
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}
		Categorie newCategorie = entityMgrObj.find(Categorie.class, categorie.getCodeCat());
		newCategorie.setNomCat(categorie.getNomCat());
		transactionObj.commit();
		return "true";
	}



	// 3- Transaction

		public static Categorie creerTransaction(String nomCat) {
			if (!transactionObj.isActive()) {
				transactionObj.begin();
			}

			Categorie categorie = new Categorie(nomCat);

			entityMgrObj.persist(categorie);
			transactionObj.commit();
			return categorie;
		}

		@SuppressWarnings("unchecked")
		public static List getAllTransaction() {
			Query queryObj = entityMgrObj.createQuery("SELECT t from Transaction t");
			List<Categorie> lList = queryObj.getResultList();

			if (lList != null && lList.size() > 0) {
				return lList;
			} else {
				return null;
			}
		}

		@SuppressWarnings("unchecked")
		public static void deleteTransaction(Long numTr) {
			Query queryObj = entityMgrObj.createQuery("delete from Transaction WHERE codeCat ='" + numTr + "'");
//						queryObj.setParameter(1, codeArt);
			queryObj.executeUpdate();

		}

		

	
}
