package kr.or.ddit.board.controller;

import com.google.gson.Gson;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.ReplyVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/insertReply.do")
public class InsertReply extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        //전송데이터 받기
        String reqData = StreamData.dataChange(req);

        //역직렬화 자바 객체로
        Gson gson = new Gson();

        ReplyVO rvo = gson.fromJson(reqData, ReplyVO.class);

        //서비스 객체
        IBoardService service = BoardServiceImpl.getService();

        int cnt = service.insertReply(rvo);

        req.setAttribute("result", cnt);

        req.getRequestDispatcher("/boardView/result.jsp").forward(req, resp);



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
