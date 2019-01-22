
	package dao;

	import java.util.List;

import model.User;

	public class SelectUser {
	    public static void main(String[] args) {
	        // userテーブルの全レコードを取得
	        UserDao userDao = new UserDao();
	        List<User> userList = userDao.findAll();

	        // 取得したレコードの内容を出力
	        for (User use : userList) {
	            System.out.println("ID:" + use.getId());
	            System.out.println("名前:" + use.getName());
	            System.out.println("ログインID:" + use.getBirthDate());

	        }
	    }
	}




