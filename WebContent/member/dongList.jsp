<%@ page import="kr.or.ddit.member.vo.ZipVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    //controller에서 저장한 값 꺼내기
    List<ZipVO> list = (List<ZipVO>) request.getAttribute("list");

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    String result;

    if (list != null && !list.isEmpty()) {
        result = gson.toJson(list);
    } else {
        result = "[]";
    }
    out.print(result);
    out.flush();

%>
