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
 * Servlet implementation class NewUserServlet
 */
@WebServlet("/NewUserServlet")
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		User U = (User) session.getAttribute("userInfo");

		if (U == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		// jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUser.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// リクエストパラメータの文字コードを指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの取得
		String loginId = request.getParameter("loginId");
		String name = request.getParameter("name");
		String birthDate = request.getParameter("birthDate");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");

		// リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
		UserDao userdao = new UserDao();
		User user = userdao.findByLoginID(loginId);

		//同じログインIDが存在した場合
		if (!(user == null)) {
			request.setAttribute("errMsg1", "このIDはすでに使用されています。");

			// ログインjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUser.jsp");
			dispatcher.forward(request, response);
			return;

		}

		//未入力の項目がある場合
		if (loginId.isEmpty() || name.isEmpty() || birthDate.isEmpty() || password.isEmpty()) {

			//リクエストスコープにメッセージをセット
			request.setAttribute("errMsg2", "未入力の項目があります。");

			// ログインjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUser.jsp");
			dispatcher.forward(request, response);
			return;
		}

		//パスワードと確認パスワードが一致しない場合

		if (!(password.equals(password2))) {

			request.setAttribute("errMsg3", "パスワードが等しくありません。");

			// ログインjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newUser.jsp");
			dispatcher.forward(request, response);
			return;

		}

		UserDao userDao = new UserDao();
		userDao.insert(loginId, name, birthDate, password);

		// UserListServletへリダイレクト
		response.sendRedirect("UserListServlet");

		//セッション切れたらログイン画面に遷移する。
		HttpSession session = request.getSession();
		User U = (User) session.getAttribute("userInfo");

		if (U == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

	}

}
