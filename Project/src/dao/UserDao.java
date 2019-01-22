package dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import model.User;

public class UserDao {
	public User findByLoginInfo(String loginId, String password) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager1.getConnection();

			//MD5でパスワードをハッシュ化
			String source = password;

			Charset charset = StandardCharsets.UTF_8;

			String algorithm = "MD5";

			byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
			String result = DatatypeConverter.printHexBinary(bytes);

			// SELECT文を準備
			String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

			// SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginId);
			pStmt.setString(2, result);

			ResultSet rs = pStmt.executeQuery();

			// 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
			if (!rs.next()) {
				return null;
			}

			String loginIdData = rs.getString("login_id");
			String nameData = rs.getString("name");

			return new User(loginIdData, nameData);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}

	public List<User> findAll() {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			// データベースへ接続
			conn = DBManager1.getConnection();

			String sql = "select * from user WHERE login_id not in ('admin')";

			// SELECTを実行し、結果表を取得
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// 結果表に格納されたレコードの内容を
			// Userインスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String loginId = rs.getString("login_id");
				Date birthDate = rs.getDate("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}

	//idと一致したユーザを返すメソッド
	public User findUserInfo(String ID) {
		Connection conn = null;

		try {
			// データベースへ接続
			conn = DBManager1.getConnection();

			String sql = "SELECT * FROM user WHERE id = ? ";

			// SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, ID);
			ResultSet rs = pStmt.executeQuery();

			if (!rs.next()) {
				return null;
			}
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String loginId = rs.getString("login_id");
			Date birthDate = rs.getDate("birth_date");
			String password = rs.getString("password");
			String createDate = rs.getString("create_date");
			String updateDate = rs.getString("update_date");

			User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

			return user;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}

	}

	//テーブルにデータを追加するメソッド
	public void insert(String loginId, String name, String birthDate, String password) {
		Connection conn = null;

		try {
			// データベースへ接続
			conn = DBManager1.getConnection();

			//MD5でパスワードをハッシュ化
			String source = password;

			Charset charset = StandardCharsets.UTF_8;

			String algorithm = "MD5";

			byte[] bytes;
			try {
				bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
				String result = DatatypeConverter.printHexBinary(bytes);

				String sql = "INSERT INTO user (login_id, name, birth_date, password, create_date, update_date) VALUES(?,?,?,?,current_timestamp,current_timestamp)";

				// insert文を実行
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, loginId);
				pStmt.setString(2, name);
				pStmt.setString(3, birthDate);
				pStmt.setString(4, result);

				pStmt.executeUpdate();

				// 自動生成された catch ブロック
			} catch (NoSuchAlgorithmException e) {

				e.printStackTrace();
			}
			//

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//テーブルのデータを削除するメソッド
	public void userDelete(String ID) {
		Connection conn = null;

		try {
			// データベースへ接続
			conn = DBManager1.getConnection();

			String sql = "DELETE FROM user WHERE id = ? ";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, ID);

			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	//データを変更するメソッド
	public void userUpdate(String ID, String password, String name, String birthDate) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager1.getConnection();

			String sql = "UPDATE user SET password = ?, name = ?, birth_date = ?, update_date = current_timestamp WHERE id = ? ";


			//MD5で暗号化
			String source = password;

			Charset charset = StandardCharsets.UTF_8;

			String algorithm = "MD5";

			byte[] bytes;
			try {
				bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
				String result = DatatypeConverter.printHexBinary(bytes);

				// insert文を実行
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, result);
				pStmt.setString(2, name);
				pStmt.setString(3, birthDate);
				pStmt.setString(4, ID);

				pStmt.executeUpdate();

				// 自動生成された catch ブロック
			} catch (NoSuchAlgorithmException e) {

				e.printStackTrace();
			}
			//

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	//テーブルのデータを変更するメソッド(引数がパスワード以外の時)
	public void userUpdate2(String ID, String name, String birthDate) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager1.getConnection();

			String sql = "UPDATE user SET name = ?, birth_date = ?, update_date = current_timestamp WHERE id = ? ";




				// insert文を実行
				PreparedStatement pStmt = conn.prepareStatement(sql);

				pStmt.setString(1, name);
				pStmt.setString(2, birthDate);
				pStmt.setString(3, ID);

				pStmt.executeUpdate();



		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}






	//ログインIDが一致したユーザを返す
	public User findByLoginID(String loginId) {
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager1.getConnection();

			// SELECT文を準備
			String sql = "SELECT * FROM user WHERE login_id = ?";

			// SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginId);
			ResultSet rs = pStmt.executeQuery();

			// 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
			if (!rs.next()) {
				return null;
			}

			String loginIdData = rs.getString("login_id");

			return new User(loginIdData);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
	}

	//ユーザ一覧からの検索
	public List<User> findUser(String loginID, String username, String birthday, String birthday2) {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			// データベースへ接続
			conn = DBManager1.getConnection();

			String sql = "SELECT * FROM user WHERE login_id not in ('admin')";

			if (!loginID.isEmpty()) {
				sql += " and login_id ='" + loginID + "' ";
			}

			if (!username.isEmpty()) {
				sql += " and name LIKE '%" + username + "%'";
			}

			if (!birthday.isEmpty()) {
				sql += " and birth_date >= '" + birthday + "'";
			}

			if (!birthday2.isEmpty()) {
				sql += " and birth_date <= '" + birthday2 + "'";
			}

			// SELECTを実行し、結果表を取得
			Statement pStmt = conn.createStatement();
			ResultSet rs = pStmt.executeQuery(sql);

			while (rs.next()) {

				int id = rs.getInt("id");
				String name = rs.getString("name");
				String loginId = rs.getString("login_id");
				Date birthDate = rs.getDate("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");

				User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);
				userList.add(user);
			}
			return userList;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}

	}

}