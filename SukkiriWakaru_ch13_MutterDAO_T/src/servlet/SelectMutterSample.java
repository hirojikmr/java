package servlet;

import java.util.List;

import model.Mutter;
import dao.MutterDAO;

public class SelectMutterSample {
  public static void main(String[] args) {

    // employeeテーブルの全レコードを取得
    MutterDAO mutterDAO = new MutterDAO();
    List<Mutter> mutterList = mutterDAO.findAll();

    // 取得したレコードの内容を出力
    for (Mutter emp : mutterList) {
      System.out.println("ID:" + emp.getId());
      System.out.println("名前:" + emp.getUserName());
      System.out.println("年齢:" + emp.getText() + "\n");
    }
  }
}