package kr.or.ddit.member.controller;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.ZipVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/zipSearch.do")
public class ZipSearchController extends HttpServlet {

    public ZipSearchController() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().append("Served at: ").append(req.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        //전송 데이터 받기
        String dongValue = req.getParameter("dong");
        //service객체 얻기
        IMemberService service = MemberServiceImpl.getInstance();
        //service메소드 호출 - 결과값 받기
        List<ZipVO> list = service.selectByDong(dongValue);
        //request에 저장
        req.setAttribute("list", list);
        //view페이지로 이동
        req.getRequestDispatcher("/비동기6_ajax/dongList.jsp").forward(req, resp);

    }
}
