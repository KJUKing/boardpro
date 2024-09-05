<%@ page import="java.util.List" %>
<%@ page import="kr.or.ddit.board.vo.ReplyVO" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    //controller에서 저장한 값 꺼내기
    List<ReplyVO> list = (List<ReplyVO>) request.getAttribute("list");

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String result = gson.toJson(list);

    out.print(result);
    out.flush();
%>
