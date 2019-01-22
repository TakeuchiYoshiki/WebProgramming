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
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserUpdateServlet() {
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

		String ID = request.getParameter("id");

		UserDao userdao = new UserDao();
		User user = userdao.findUserInfo(ID);

		request.setAttribute("u", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String ID = request.getParameter("id");

		String password = request.getParameter("pass");
		String password2 = request.getParameter("pass2");
		String name = request.getParameter("name");
		String birthDate = request.getParameter("birthday");

		//確認パスワードが一致しなかった場合(passwordの中身はString型なので"="ではなく"equals()"を使用する。)
		if (!(password.equals(password2))) {

			request.setAttribute("errMsg1", "パスワードとパスワード（確認）が一致しません。");

			// ログインjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
			dispatcher.forward(request, response);
			return;
		}

		//パスワード以外に未入力項目があった場合
		if (name.isEmpty() || birthDate.isEmpty()) {

			request.setAttribute("errMsg2", "未入力の項目があります。");

			// ログインjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
			dispatcher.forward(request, response);
			return;

		}

		//パスワードがどちらも未入力だった場合パスワードだけ変更しない
		if (password.isEmpty() && password2.isEmpty()) {

			UserDao userdao = new UserDao();
			userdao.userUpdate2(ID, name, birthDate);

			response.sendRedirect("UserListServlet");
			return;
		}

		UserDao userdao = new UserDao();
		userdao.userUpdate(ID, password, name, birthDate);

		response.sendRedirect("UserListServlet");

	}

}
