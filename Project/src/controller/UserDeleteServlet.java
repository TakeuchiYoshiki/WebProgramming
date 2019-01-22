package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User U = (User) session.getAttribute("userInfo");

		if (U == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		//getParameter()メソッドでuserlistの中のuserのidを取得してString型の変数に入れる。
		String ID = request.getParameter("id");

		UserDao userdao = new UserDao();
		User user = userdao.findUserInfo(ID);

		request.setAttribute("u", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userDelete.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//セッション切れたらログイン画面に遷移する。
		HttpSession session = request.getSession();
		User U = (User) session.getAttribute("userInfo");

		if (U == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		String ID = request.getParameter("id");
		UserDao userdao = new UserDao();
		userdao.userDelete(ID);

		//ユーザ一覧へリダイレクト
		response.sendRedirect("UserListServlet");

	}

}
