package kr.or.ddit.board.controller;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteBoard.do")
public class deleteBoard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //전송데이터 num
        int num = Integer.parseInt(req.getParameter("num"));

        //service객체 얻기
        IBoardService service = BoardServiceImpl.getService();

        int cnt = service.deleteBoard(num);

        //결과값 저장
        req.setAttribute("result", cnt);

        req.getRequestDispatcher("/boardView/result.jsp").forward(req, resp);
    }
}
