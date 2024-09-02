package kr.or.ddit.member.controller;

import com.google.gson.Gson;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/insertMember.do")
public class InsertMember extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");


        //전송데이터 받기
        String reqData = StreamData.dataChange(req);

        //역직렬화 -MemberVO로 바꿔줘야함
        Gson gson = new Gson();
        MemberVO vo = gson.fromJson(reqData, MemberVO.class);
        //vo.setMem_id("뭐시기") vo.setMem_name("뭐시기") 등등

        //service객체 얻기
        IMemberService service = MemberServiceImpl.getInstance();

        //service메소드 호출 - 결과값 받기
        int cnt = service.insertMember(vo);

        //결과값을 저장
        req.setAttribute("result", cnt);

        //view페이지로 이동
        req.getRequestDispatcher("/비동기6_ajax/insert.jsp").forward(req, resp);
    }
}
