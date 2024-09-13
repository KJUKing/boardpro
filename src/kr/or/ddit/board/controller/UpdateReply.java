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

@WebServlet("/updateReply.do")
public class UpdateReply extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //전송 데이터 받기 - reply - cont, renum
        String reqData = StreamData.dataChange(req);
        // {"renum" : 19, "cont" : "123d"}

        //역직렬화
        Gson gson = new Gson();
        ReplyVO vo = gson.fromJson(reqData, ReplyVO.class);

        IBoardService service = BoardServiceImpl.getService();

        int cnt = service.updateReply(vo);

        req.setAttribute("result", cnt);
        req.getRequestDispatcher("boardView/result.jsp").forward(req, resp);
    }
}
