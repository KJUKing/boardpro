<%@ page import="kr.or.ddit.member.vo.MemberVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    //controller에서 온 값 꺼내기
    MemberVO vo = (MemberVO) session.getAttribute("loginok");

    String check = (String) session.getAttribute("check");

    if (vo != null) {
%>
<input id="id" type="text" placeholder="id" >&nbsp;&nbsp;
<input id="pass" type="password" placeholder="password">&nbsp;&nbsp;
<button id="login" type="button">로그인</button><br>
<% } else {%>

<span><%= vo.getMem_id() %>님 환영합니다</span>&nbsp;&nbsp;
<button id="logout" type="button">로그아웃</button><br>

<%}%>