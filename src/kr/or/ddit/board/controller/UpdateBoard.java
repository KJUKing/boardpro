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

@WebServlet("/updateBoard.do")
public class UpdateBoard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //전송데이터받기
        String reqData = StreamData.dataChange(req);
        //content, subject, writer, password, num, mail

        //자바 객체로 역직렬화
        Gson gson = new Gson();

        BoardVO vo = gson.fromJson(reqData, BoardVO.class);

        System.out.println("vo.getNum() = " + vo.getNum());


        IBoardService service = BoardServiceImpl.getService();

        //service메소드 호출
        int cnt = service.updateBoard(vo);
        //request저장
        req.setAttribute("result", cnt);

        //view로 이동
        req.getRequestDispatcher("/boardView/result.jsp").forward(req, resp);

    }
}
