package ma.fstt.web;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import ma.fstt.entities.Article;
import ma.fstt.entities.Categorie;
import ma.fstt.entities.User;
import ma.fstt.persistence.DatabaseOperations;

@WebServlet("/BackServlet")

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)

public class BackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DatabaseOperations databaseOperations;

	private List<Article> articles;
	private List<Categorie> categories;
	private Article article;
	private Categorie categorie;
	private List<User> users;

	public static final String UPLOAD_DIR = "images";
	public String dbFileName = "";

	public BackServlet() {
		super();
		databaseOperations = new DatabaseOperations();
		articles = new ArrayList<Article>();
		categorie = new Categorie();
		article = new Article();
		categories = new ArrayList<Categorie>();
		users = new ArrayList<User>();
		articles = databaseOperations.getAllArticles();
		categories = databaseOperations.getAllCategories();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		try {
			switch (action) {

			case "article":

				showArticles(request, response);
				break;

			case "DeleteArticle":

				String id = request.getParameter("codeArt");
				Long codeArt = (long) Integer.parseInt(id);
				System.out.println("supprimer id " + codeArt);
				DeleteArticle(request, response, codeArt);
				break;

			case "UpdateArticle":

				Long codeArt1 = (long) Integer.parseInt(request.getParameter("codeArt"));
				this.article = databaseOperations.findArticle(codeArt1);
				UpdateArticle(request, response);
				break;

			case "AjouterArticle":

				String nom = request.getParameter("nomArt");
				float p = Integer.parseInt(request.getParameter("pu"));
				int qteSt = Integer.parseInt(request.getParameter("qteStock"));
				String idCat1 = request.getParameter("Categorielist");
				Long codeCat1 = (long) Integer.parseInt(idCat1);
				Categorie categorie1 = new Categorie();
				categorie1 = databaseOperations.findCategorie(codeCat1);
				AjouterArticle(request, response, nom, p, qteSt, categorie1);
				break;

			case "EditArticle":
				EditArticle(request, response);

				break;
				
				/////////
				
			case "categorie":

				showCategories(request, response);
				break;

			case "DeleteCategorie":

				String id2 = request.getParameter("codeCat");
				Long codeCat = (long) Integer.parseInt(id2);
				System.out.println("supprimer id " + codeCat);
				DeleteCategorie(request, response, codeCat);
				break;

			case "UpdateCategorie":

				Long codeCat2 = (long) Integer.parseInt(request.getParameter("codeCat"));
				this.categorie = databaseOperations.findCategorie(codeCat2);
				UpdateCategorie(request, response);
				break;

			case "AjouterCategorie":

				String nomC = request.getParameter("nomCat");
				AjouterCategorie(request, response, nomC);
				break;

			case "EditCategorie":
				EditCategorie(request, response);
				break;
				
				
				//////
			case "user":

				showUsers(request, response);
				break;


			default:
				showArticles(request, response);
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

	private void showArticles(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		articles = databaseOperations.getAllArticles();
		categories = databaseOperations.getAllCategories();

		request.setAttribute("articles", articles);
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("jsp/admin/article.jsp").forward(request, response);

	}

	private void DeleteArticle(HttpServletRequest request, HttpServletResponse response, Long codeArt)
			throws SQLException, IOException, ServletException {
		Article a = new Article();
		a = databaseOperations.findArticle(codeArt);
		databaseOperations.deleteArticle(a);
		showArticles(request, response);

	}

	private void UpdateArticle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		request.setAttribute("categories", categories);
		request.setAttribute("article", article);
		showArticles(request, response);

	}

	private void AjouterArticle(HttpServletRequest request, HttpServletResponse response, String nomArt, Float pu,
			int qteStock, Categorie categorie) throws SQLException, IOException, ServletException {
		String img = AjouterImage(request, response);
		DatabaseOperations.creerArticle(nomArt, pu, qteStock, categorie, img);
		showArticles(request, response);

	}

	private void EditArticle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String id1 = request.getParameter("codeArt");
		Long codeArt2 = (long) Integer.parseInt(request.getParameter("codeArt"));
		String nomArt = request.getParameter("nomArt");
		String sPu = request.getParameter("pu");
		float pu = Float.valueOf(sPu);
		int qteStock = Integer.parseInt(request.getParameter("qteStock"));
		Long idCat = Long.parseLong(request.getParameter("Categorielist"));

		Categorie cate = new Categorie();
		cate = databaseOperations.findCategorie(idCat);
		String img = AjouterImage(request, response);
		Article ar = new Article(codeArt2, nomArt, pu, qteStock, cate,img);
		databaseOperations.updateArticle(ar);
		showArticles(request, response);

	}

	private String AjouterImage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		Part part = request.getPart("photo");//
		String fileName = extractFileName(part);// file name
		String applicationPath = "D:\\AppVitrine\\AppVitrine\\src\\main\\webapp\\images\\";
		File fileUploadDirectory = new File(applicationPath);
		if (!fileUploadDirectory.exists()) {
			fileUploadDirectory.mkdirs();
		}
		String savePath = applicationPath + File.separator + fileName;
		String sRootPath = new File(savePath).getAbsolutePath();
		part.write(savePath + File.separator);
		File fileSaveDir1 = new File(savePath);
		
		dbFileName = UPLOAD_DIR + File.separator + fileName;
		part.write(savePath + File.separator);
		return fileName;

	}

	private String extractFileName(Part part) {// This method will print the file name.
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
	
	
	
	
	private void showCategories(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		categories = databaseOperations.getAllCategories();

		request.setAttribute("categories", categories);
		request.getRequestDispatcher("jsp/admin/categorie.jsp").forward(request, response);

	}
	
	private void DeleteCategorie(HttpServletRequest request, HttpServletResponse response, Long codeCat)
			throws SQLException, IOException, ServletException {
		Categorie a = new Categorie();
		a = databaseOperations.findCategorie(codeCat);
		databaseOperations.deleteCategorie(a);
		showCategories(request, response);

	}
	
	private void UpdateCategorie(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		request.setAttribute("categories", categories);
		request.setAttribute("categorie", categorie);
		showCategories(request, response);

	}
	
	private void AjouterCategorie(HttpServletRequest request, HttpServletResponse response, String nomCat) throws SQLException, IOException, ServletException {
		DatabaseOperations.creerCategorie(nomCat);
		showCategories(request, response);

	}
	
	private void EditCategorie(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String id = request.getParameter("codeCat");
		Long idCat = Long.parseLong(id);
		String nomCat = request.getParameter("nomCat");
		Categorie cate = new Categorie(idCat,nomCat);
		databaseOperations.updateCategorie(cate);
		showCategories(request, response);

	}
	
	private void showUsers(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		users = databaseOperations.getAllUsers();

		request.setAttribute("users", users);
		request.getRequestDispatcher("jsp/admin/user.jsp").forward(request, response);

	}
	
	
}
