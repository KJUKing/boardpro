package kr.or.ddit.board.controller;

import com.google.gson.Gson;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/insertBoard.do")
public class InsertBoard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String reqData = StreamData.dataChange(req);
//        {"writer" : "김은대", "subject" : "sdf"}

        //자바 객체로 역직렬화 - BoardVO
        Gson gson = new Gson();
        BoardVO vo = gson.fromJson(reqData, BoardVO.class);
        //vo.setWriter("김은대"), mail, subject, password, content
        vo.setWip(req.getRemoteAddr());

        //service객체 얻기
        IBoardService service = BoardServiceImpl.getService();
        //service메소드 호출
        int cnt = service.insertBoard(vo);
        //결과값 저장 - result.jsp
        req.setAttribute("result", cnt);
        //view로이동
        req.getRequestDispatcher("/boardView/result.jsp").forward(req, resp);

    }
}
