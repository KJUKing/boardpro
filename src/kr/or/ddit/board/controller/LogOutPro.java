package kr.or.ddit.board.controller;

import kr.or.ddit.member.vo.MemberVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logOutPro.do")
public class LogOutPro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        MemberVO vo = (MemberVO) session.getAttribute("loginok");

        if (vo != null) {
            session.removeAttribute("loginok");
            session.removeAttribute("check");
        }

        req.getRequestDispatcher("/start/logpro.jsp").forward(req, resp);
    }
}
