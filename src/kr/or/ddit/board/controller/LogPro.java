package kr.or.ddit.board.controller;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logPro.do")
public class LogPro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        MemberVO vo = new MemberVO();
        //전송데이터받기
        String id = req.getParameter("id");
        String pass = req.getParameter("pass");

        vo.setMem_id(id);
        vo.setMem_pass(pass);


        //service객체 얻기
        IMemberService service = MemberServiceImpl.getInstance();
        //service메소드 호출 - 결과값 받기
        MemberVO returnVO = service.loginSelect(vo);

        HttpSession session = req.getSession();


        //결과값을저장 -session
        if (returnVO != null) {
            //로그인 성공
            //session에 저장
            session.setAttribute("loginok", returnVO);
            session.setAttribute("check", "true");
        } else {
            //로그인 실패
            session.setAttribute("check", "false");
        }

        //view페이지로가기 aa.jsp - sjon데이터 생성
        req.getRequestDispatcher("/start/logpro.jsp").forward(req, resp);

    }
}
