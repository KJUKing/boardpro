package kr.or.ddit.board.controller;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.ReplyVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectByReply.do")
public class SelectByReply extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //전송 데이터 받기
        int bonum = Integer.parseInt(req.getParameter("bonum"));
        //service객체 얻기
        IBoardService service = BoardServiceImpl.getService();
        //service메소드 호출 - 결과값 받기
        List<ReplyVO> list = service.selectByReply(bonum);

        //result에 저장
        req.setAttribute("list", list);

        //view페이지 이동
        req.getRequestDispatcher("boardView/replyList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
