package ma.fstt.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.fstt.entities.Article;
import ma.fstt.entities.Categorie;
import ma.fstt.entities.User;
import ma.fstt.persistence.DatabaseOperations;

@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseOperations databaseOperations;

	private List<Article> articles;
	private List<Categorie> categories;
	private List<User> users;
	private User user;
	private boolean islogin = false;

	Hashtable<Article, Integer> articlespanier;

	public ControllerServlet() {
		super();
		databaseOperations = new DatabaseOperations();
		articles = new ArrayList<Article>();
		categories = new ArrayList<Categorie>();
		users = new ArrayList<User>();
		articles = databaseOperations.getAllArticles();
		categories = databaseOperations.getAllCategories();
		users = databaseOperations.getAllUsers();
		articlespanier = new Hashtable<Article, Integer>();
		user = new User();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {
			case "index":

				ShowIndex(request, response);
				break;

			case "loginform":

				logInform(request, response);
				break;
			case "logupform":

				logUpform(request, response);
				break;

			case "login":

				signin(request, response);
				break;

			case "signup":

				signup(request, response);
				break;

			case "panier":

				showPanier(request, response);
				break;
			case "onearticle":

				ShowArticle(request, response);
				break;
			case "ajouterPanier":

				addToCard(request, response);
				break;

			case "deletePanier":
				deleteFromCard(request, response);
				break;
			case "signout":
				signout(request, response);
				break;

			default:
				ShowIndex(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	private void ShowIndex(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		request.setAttribute("articles", articles);
		request.setAttribute("categories", categories);
		if(islogin)
			request.setAttribute("user", user);
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);

	}

	private void signin(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int flag = 0;

		for (User u : this.users) {
			if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
				if (u.getRole() == 0)
					flag = 1;
				else {
					flag = 2;
				}
				this.user = u;
				break;
			}
		}

		if (flag == 0)
			request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
		else if (flag == 1) {
			islogin = true;
			request.setAttribute("user", user);
			ShowIndex(request, response);
		} else {
			request.setAttribute("articles", articles);
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("jsp/admin/article.jsp").forward(request, response);
		}

	}

	private void signup(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		String nom = request.getParameter("nom");
		String ville = request.getParameter("ville");
		String tel = request.getParameter("tel");
		String addre = request.getParameter("addr");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User uu = new User();
		uu.setAddr(addre);
		uu.setEmail(email);
		uu.setNom(nom);
		uu.setPassword(password);
		uu.setTel(tel);
		uu.setVille(ville);
		this.databaseOperations.creerUser(uu);
		this.user = uu;
		islogin = true;
		request.setAttribute("user", user);
		ShowIndex(request, response);

	}

	private void logInform(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		request.getRequestDispatcher("jsp/login.jsp").forward(request, response);

	}

	private void logUpform(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		request.getRequestDispatcher("jsp/logup.jsp").forward(request, response);

	}

	private void showPanier(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		request.setAttribute("articles", articles);
		request.setAttribute("categories", categories);
		if (!articlespanier.isEmpty()) {
			request.setAttribute("articlesAuPanier", getArticlesInPanier());
			request.setAttribute("pu", getPrix());
		}
		if(islogin)
			request.setAttribute("user", user);
		request.getRequestDispatcher("jsp/panier.jsp").forward(request, response);

	}

	private void ShowArticle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		String id = request.getParameter("codeArt");
		Long idArt = Long.parseLong(id);
		Article ar = new Article();
		ar = databaseOperations.findArticle(idArt);
		request.setAttribute("article", ar);
		request.setAttribute("categories", categories);
		if(islogin)
			request.setAttribute("user", user);
		request.getRequestDispatcher("jsp/OneArticle.jsp").forward(request, response);

	}

	private void addToCard(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		String id = request.getParameter("codeArt");
		Long idArt = Long.parseLong(id);
		Article ar = new Article();
		ar = databaseOperations.findArticle(idArt);
		if (!articlespanier.isEmpty()) {
			if (!getArticlesInPanier().contains(ar))
				this.articlespanier.put(ar, 1);
			else {
				this.articlespanier.replace(ar, articlespanier.get(ar) + 1);
			}
		} else {
			this.articlespanier.put(ar, 1);
		}
		if (!articlespanier.isEmpty()) {
			request.setAttribute("articlesAuPanier", getArticlesInPanier());
			request.setAttribute("pu", getPrix());
		}
		if(islogin)
			request.setAttribute("user", user);
		request.getRequestDispatcher("jsp/panier.jsp").forward(request, response);

	}

	private void deleteFromCard(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String id = request.getParameter("codeArt");
		Long idArt = Long.parseLong(id);
		Article ar = new Article();
		ar = databaseOperations.findArticle(idArt);

		this.articlespanier.remove(ar);
		if (!articlespanier.isEmpty()) {
			request.setAttribute("articlesAuPanier", getArticlesInPanier());
			request.setAttribute("pu", getPrix());
		}
		if(islogin)
			request.setAttribute("user", user);
		request.getRequestDispatcher("jsp/panier.jsp").forward(request, response);
		
	}

	private ArrayList<Article> getArticlesInPanier() {
		ArrayList<Article> ars = new ArrayList();
		for (Article key : articlespanier.keySet()) {
			ars.add(key);
		}
		return ars;
	}

	private float getPrix() {
		float somme = 0;
		for (Article key : articlespanier.keySet()) {
			somme += key.getPu();
		}
		return somme;
	}
	
	private void signout(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		request.setAttribute("articles", articles);
		request.setAttribute("categories", categories);
		islogin = false;
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);

	}

}
