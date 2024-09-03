package kr.or.ddit.board.controller;

import com.google.gson.Gson;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PListVO;
import kr.or.ddit.board.vo.PageVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/boardList.do")
public class BoardList extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //전송 데이터 갖고오기 - 페이지번호, stype, sword

        //
        String reqData = StreamData.dataChange(req);
        //{"page" : "1", "stype" : "", "sword"  : ""} 보내지는 데이터 유형

        //보내고나서 역직렬화 돼서 옴
        //역직렬화 데이터를 위해 PlistVO를만든다

        //json형식을 자바객체형식으로 바꾼다 - 역직렬화
        Gson gson = new Gson();
        PListVO vo = gson.fromJson(reqData, PListVO.class);
        //vo.setPage(1) vo.setStype("") vo.setSword("") 이런형식이됨
        //물론 초기값

        //service객체 얻기
        IBoardService service = BoardServiceImpl.getInstance();

        //page정보 가져오기
        PageVO pvo = service.pageInfo(vo.getPage(), vo.getStype(), vo.getSword());
        //start, end startPage, endPage, totalPage

        //list 가져오기
        Map<String, Object> map = new HashMap<>();
        map.put("start", pvo.getStart());
        map.put("end", pvo.getEnd());
        map.put("stype", vo.getStype());
        map.put("sword", vo.getSword());

        //list가져오기 메소드 호출
        List<BoardVO> list = service.selectBoardList(map);

        //결과값을 request에 저장
        req.setAttribute("list", list);
        req.setAttribute("startPage", pvo.getStartPage());
        req.setAttribute("endPage", pvo.getEndPage());
        req.setAttribute("totalPage", pvo.getTotalPage());

        //view페이지로 이동
        req.getRequestDispatcher("/boardView/list.jsp").forward(req, resp);
    }
}
