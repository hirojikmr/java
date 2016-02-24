package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SiteEV;
import model.SiteEVLogic;



@WebServlet("/MinatoIndex")
public class MinatoIndex extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

    // アプリケーションスコープに保存されたサイト評価を取得
    ServletContext application = this.getServletContext();
    SiteEV siteEV = (SiteEV) application.getAttribute("siteEV");

    // サイト評価の初期化（初回リクエスト時実行）
    if (siteEV == null) {
      siteEV = new SiteEV();
    }

    // リクエストパラメータの取得
    request.setCharacterEncoding("UTF-8");
    String action = request.getParameter("action");

    // サイトの評価処理（初回リクエスト時は実行しない）
    SiteEVLogic siteEVLogic = new SiteEVLogic();
    if (action != null && action.equals("like")) {
      siteEVLogic.like(siteEV);
    } else if (action != null && action.equals("dislike")) {
      siteEVLogic.dislike(siteEV);
    }

    // アプリケーションスコープにサイト評価を保存
    application.setAttribute("siteEV", siteEV);
    
    // フォワード
    RequestDispatcher dispatcher =
        request.getRequestDispatcher
            ("/view/minatoIndex.jsp");
    dispatcher.forward(request, response);
  }
}