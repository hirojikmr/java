package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class MutterDAO {
  public List<Employee> findAll() {

    Connection conn = null;
    List<Employee> empList = new ArrayList<Employee>();
    
	String driver = "com.mysql.jdbc.Driver";
	String user   = "root";
	String pass   = "pass";
	String url    = "jdbc:mysql://localhost:3306/emp_db"; 

    try {

      // JDBCドライバを読み込み
      Class.forName(driver);

      // データベースへ接続
      conn = DriverManager.getConnection(url,user,pass);
      
      

      // SELECT文を準備
      String sql = "SELECT ID, NAME, AGE FROM emp";
      PreparedStatement pStmt = conn.prepareStatement(sql);

      // SELECTを実行し、結果表を取得
      ResultSet rs = pStmt.executeQuery();

      // 結果表に格納されたレコードの内容を
      // Employeeインスタンスに設定し、ArrayListインスタンスに追加
      while (rs.next()) {
        String id = rs.getString("ID");
        String name = rs.getString("NAME");
        int age = rs.getInt("AGE");
        Employee employee = new Employee(id, name, age);
        empList.add(employee);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    } catch (ClassNotFoundException e) {
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
    return empList;
  }
}