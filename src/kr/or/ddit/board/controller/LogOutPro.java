package kr.or.ddit.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.vo.MemberVO;

/**
 * Servlet implementation class LogOutPro
 */
@WebServlet("/LogOutPro.do")
public class LogOutPro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogOutPro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession   session = request.getSession();
		
		MemberVO  vo =    (MemberVO)session.getAttribute("loginok");
		
		if(vo != null) {
			
			session.removeAttribute("loginok");
			session.removeAttribute("check");
		}
		
		
		request.getRequestDispatcher("/start/logpro.jsp").forward(request, response);
		
	}

}















