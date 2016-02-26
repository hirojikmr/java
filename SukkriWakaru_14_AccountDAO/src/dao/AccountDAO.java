package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Login;

/*

create database sukkiriShop_db;

CREATE TABLE ACCOUNT (
  USER_ID CHAR(10) PRIMARY KEY,
  PASS VARCHAR(10) NOT NULL,
  MAIL VARCHAR(100) NOT NULL,
  NAME VARCHAR(40) NOT NULL,
  AGE INT NOT NULL
);

INSERT INTO ACCOUNT (USER_ID, PASS, MAIL, NAME, AGE) VALUES('minato', '1234', 'minato@sukkiri.com', '湊 雄輔', 23);
INSERT INTO ACCOUNT (USER_ID, PASS, MAIL, NAME, AGE) VALUES('hoge', 'hoge', 'hoge@hoge.hog', 'ホゲ ホゲタロウ', 99);

*/

public class AccountDAO {
	private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/sukkiriShop_db";
	private final String DB_USER = "root";
	private final String DB_PASS = "pass";

	public Account findByLogin(Login login) {
		Connection conn = null;
		Account account = null;

		try {
			// JDBCドライバを読み込む
			Class.forName(DRIVER_NAME);

			// データベースに接続
			conn = DriverManager.getConnection(JDBC_URL,DB_USER ,DB_PASS);

			// SELECT文を準備
			String sql = "SELECT USER_ID, PASS, MAIL, NAME, AGE FROM ACCOUNT WHERE USER_ID = ? AND PASS = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, login.getUserId());
			pStmt.setString(2, login.getPass());

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			// 一致したユーザーが存在した場合
			// そのユーザーを表すAccountインスタンスを生成
			if (rs.next()) {
				// 結果表からデータを取得
				String userId = rs.getString("USER_ID");
				String pass = rs.getString("PASS");
				String mail = rs.getString("MAIL");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");

				account = new Account(userId, pass, mail, name, age);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		// 見つかったユーザーまたはnullを返す
		return account;
	}
}