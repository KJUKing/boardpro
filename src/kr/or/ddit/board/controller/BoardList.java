package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PListVO;
import kr.or.ddit.board.vo.PageVO;

/**
 * Servlet implementation class BoardList
 */
@WebServlet("/BoardList.do")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		
		//전송데이타 가져오기 - 페이지번호page , stype, sword 
		String reqData = StreamData.dataChange(request);
		//{"page" : "1" , "stype" : "" , "sword" : ""}
		System.out.println("reqData =" + reqData);
		
		//역직렬화 - PListVO -json형식을 자바객체형식으로 변환 
		Gson  gson = new Gson();
		PListVO  vo = gson.fromJson(reqData, PListVO.class);
		//vo.setPage(1) vo.setStype("") vo.setSword("")
		
		//service객체 얻기 
		IBoardService   service = BoardServiceImpl.getService();
		
		//list를 가져오기 위한 page정보 가져오가
		PageVO  pvo = service.pageInfo(vo.getPage(), vo.getStype(), vo.getSword());
		//start, end startPage, endPage, totalPage
		
		//list가져오기 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pvo.getStart());
		map.put("end", pvo.getEnd());
		map.put("stype", vo.getStype());
		map.put("sword", vo.getSword());
		
		//list가져오기 -메소드 호출
		List<BoardVO> list = service.selectBoardList(map);
		
		//결과값을 request에 저장 
		request.setAttribute("list", list);
		request.setAttribute("startPage", pvo.getStartPage());
		request.setAttribute("endPage", pvo.getEndPage());
		request.setAttribute("totalPage", pvo.getTotalPage());
		
		//view페이지로 이동 
		request.getRequestDispatcher("/boardView/list.jsp").forward(request, response);
		
		
		
		
	}

}












