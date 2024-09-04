
<%@page import="com.google.gson.stream.JsonWriter"%>
<%@page import="java.nio.file.Path"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.io.Writer"%>
<%@page import="com.google.gson.JsonArray"%>
<%@page import="com.google.gson.reflect.TypeToken"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.JsonElement"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%
  
 List<BoardVO> list = (List<BoardVO>)request.getAttribute("listvalue"); 
 Gson  gson = new Gson();
 String result =  gson.toJSON(list);
 out.print(result);
 out.flush();
 
  [
	           {
	        	   "num"  : "20",
	                "writer" :  "작성자20",
	       			"subject" : "제목20",
	       			"content" : "내용20"
	           },
	           {
	        	   "num"  : "19",
	                "writer" :  "작성자19",
	       			"subject" : "제목19",
	       			"content" : "내용19"
	           },
	           {
	        	   "num"  : "18",
	                "writer" :  "작성자18",
	       			"subject" : "제목18",
	       			"content" : "내용18"
	           }
	   ]  
   --------------------------------------
   
    JsonObject  obj = new JsonObject();
	
	
	
 {
	"sp" : "1", "ep" : "2", "tp" : "3",
	"datas" : [
	           {
	        	   "num"  : "20",
	                "writer" :  "작성자20",
	       			"subject" : "제목20",
	       			"content" : "내용20"
	           },
	           {
	        	   "num"  : "19",
	                "writer" :  "작성자19",
	       			"subject" : "제목19",
	       			"content" : "내용19"
	           },
	           {
	        	   "num"  : "18",
	                "writer" :  "작성자18",
	       			"subject" : "제목18",
	       			"content" : "내용18"
	           }
	   ]
 }
 
  
   
 
 
 %>