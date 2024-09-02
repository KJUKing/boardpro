
<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="true" %>

 <script src="../js/jquery-3.7.1.js"></script>
 
 <script>
 mypath =  '<%= request.getContextPath()%>';
 
 $(function(){
	/*  $(document).on('click', '#logout', () => { */
	$('#logout').on('click', ()=>{		 
		 $.ajax({
			url : `${mypath}/LogOutPro.do`,
			type : 'get',
			success : function(res){
				
				location.href="/boardlogpro/start/index.jsp";
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);  //200 : json형태오류  404 : FileNotFound
			},
			dataType : 'html'
		 })
			
	 })
	 
	/* $(document).on('click', '#login', () => {  */
	$('#login').on('click', ()=>{	
		 //입력한 id와 pass 값를 가져온다 
		 idvalue = $('#id').val();
		 passvalue = $('#pass').val();
		 
		 console.log(idvalue, passvalue);
		 
		 
		 $.ajax({
				url : `${mypath}/logPro.do`,
				type : 'post',
				data : {"id" : idvalue , "pass"  : passvalue },
				success : function(res){
					
					location.href="/boardpro/start/index.jsp";
				},
				error : function(xhr){
					alert("상태 : " + xhr.status);  //200 : json형태오류  404 : FileNotFound
				},
				dataType : 'html'
			 })
	 })
 })
 </script>

<style>
#check {  color : red;}
</style>

 <%
 //세션 로그인 값 - 로그인컨트롤러에서 갖고옴
 	//HttpSession  session2 = request.getSession();
     MemberVO  vo = (MemberVO)session.getAttribute("loginok");
     String check = (String)session.getAttribute("check");
    
     System.out.println("login : " + vo);
     System.out.println("check : " + check);
     
     if(vo == null){
 %>
      <input id="id" type="text" placeholder="id" >&nbsp;&nbsp;
      <input id="pass" type="password" placeholder="password">&nbsp;&nbsp; 
      <button id="login" type="button">로그인</button><br> 
       
     
 <%   }else  if(vo != null ){  %>
    
       <span><%= vo.getMem_id() %>님 환영합니다</span>&nbsp;&nbsp;
       <button id="logout" type="button">로그아웃</button><br>
        
 <%  } %>
  
   <%    
      if(check == "false" ){
  %>
    	  <span id="check">로그인 오류 또는 비회원입니다</span>
  <%
      }
 %> 
 
 
 
 