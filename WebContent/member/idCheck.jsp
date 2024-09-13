<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    //controller에서 저장한 값 꺼내기
    String res = (String)request.getAttribute("resultId");

    if (res == null) { // 사용가능한 아이디
%>
    {
        "flag " : "사용가능한 아이디"
    }

<%    }else { // 사용 불가능한 아이디 %>

    {
        "flag" : "사용 불가능한 아이디"
    }

<%
    }
%>