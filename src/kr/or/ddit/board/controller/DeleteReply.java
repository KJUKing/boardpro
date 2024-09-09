package kr.or.ddit.board.controller;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteReply.do")
public class DeleteReply extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //전송데이터 - renum 받기

        int renum = Integer.parseInt(req.getParameter("renum"));
        //service 객체 얻고 메소드 호출하고 값얻기
        IBoardService service = BoardServiceImpl.getService();

        int cnt = service.deleteReply(renum);
        //결과값 저장
        req.setAttribute("result", cnt);

        req.getRequestDispatcher("/boardView/result.jsp").forward(req, resp);
    }
}
