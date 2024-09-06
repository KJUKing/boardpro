package kr.or.ddit.board.controller;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateHit.do")
public class UpdateHit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //전송데이터 가져오기 -num
        int num = Integer.parseInt(req.getParameter("num"));

        //service객체얻기
        IBoardService service = BoardServiceImpl.getService();

        //service메소드 호출
        int cnt = service.updateHit(num);
        //request저장
        req.setAttribute("result", cnt);

        //view로 이동
        req.getRequestDispatcher("/boardView/result.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
