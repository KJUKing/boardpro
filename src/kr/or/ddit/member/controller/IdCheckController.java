package kr.or.ddit.member.controller;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



// !!!url패턴에 /꼭 껴넣기!!!!

@WebServlet("/idCheck.do")
public class IdCheckController extends HttpServlet {

    public IdCheckController() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //전송 데이터 받기 - id
        String id = req.getParameter("id");

        //service객체 얻기
        IMemberService service = MemberServiceImpl.getInstance();

        //service메소드 호출 - 결과값 얻기
        String resId = service.idCheck(id);

        //request에 저장
        req.setAttribute("resultId", resId);

        //view페이지로 이동
        req.getRequestDispatcher("/비동기6_ajax/idCheck.jsp").forward(req, resp);

    }


}
