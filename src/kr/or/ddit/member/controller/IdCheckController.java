package kr.or.ddit.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

/**
 * Servlet implementation class IdCheckController
 */
@WebServlet("/idCheck.do")
public class IdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//전송데이타 받기 - id
		String userId = request.getParameter("id");
		System.out.println("userId=" + userId);
		
		//service객체 얻기 
		IMemberService   service = MemberServiceImpl.getService();
		
		//service메소드 호출 -결과값 얻기 
		String  resId = service.idCheck(userId);
		
		//request에 결과값 저장 
		request.setAttribute("sldkfjs", resId);
		
		//view패이지로 이동 
		request.getRequestDispatcher("/member/idCheck.jsp").forward(request, response);
		
		
	}

}












