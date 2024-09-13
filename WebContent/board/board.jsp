<%@page import="com.google.gson.Gson"%>
<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="true" %>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

  <script src="../js/jquery-3.7.1.js"></script>
  
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

  <script src="../js/board.js"></script>
  <script src="../js/jquery.serializejson.min.js"></script>

<style>
html, body {padding:-10; margin:-10; height:100%; width: 100%;   }

body *{
  box-sizing:   border-box;
}

body{
  min-width : 1000px;
}
/* #result{
  width : 100%;
  padding : 1px;
} */
.container{
   margin : 5px;
   max-width : 99%;
}
.p12{
  display: flex;
  flex-direction:  row;
}
p{
   border  : 1px dotted lightgray;
   padding : 5px;
   margin  :2px;
}
.p1{
   flex:  70%;
   word-break:keep-all; /* 줄바꿈: 단어단위로  */
}
.p2{
   flex : 30%;
   text-align:  right;
}

.card-body{
   display:  flex;
   flex-direction:  column;
}
input[name=reply]{
   height : 55px;
   vertical-align: top;
}
textarea {
	width : 70%;
}

nav {
  margin: 2%;
}

nav a{
 visibility: hidden;
}
#pagelist{
   margin-left : 10%;
}

.card-header:hover{
  background : #0078FF;
}

.reply-body{
  border : 1px dotted orange;
  background : #efdada;
  margin : 2px;
  padding : 2px;
}

.modal label{
  width : 100px;
  height : 30px;
}

span.pa{
 display : none;/* listPageServer에서 password  를 안보이도록*/
}

#modifyform{
  display: none;
}
#btnok, #btnreset{
  height : 40px;
  vertical-align: bottom;
}

</style>

<%
    //로그인 상태
    MemberVO vo = (MemberVO) session.getAttribute("loginok");

    String ss = null;
    Gson gson = new Gson();
    if (vo != null) {
        ss = gson.toJson(vo);
        /*
         ss = { "mem_id : "아이디" , "mem_pass" : "비밀번호" "mem_name" : "이름" }
         */
    }
%>

<script>
//JSP 실행 순서가  Java =>  => HTML => javascript

//자바스크립트 객체 -동적으로 속성을 추가
reply = {};

uvo = <%= ss %>
currentPage = 1;
mypath =  '<%= request.getContextPath()%>';


$(function(){

    //js 파일 함수 호출
    $.listPageServer();

    //다음 버튼 이벤트
    $(document).on('click', '#next', function () {
        //나열되어있는 페이지 번호(pageno)들의 마지막 값을 가져오기
        currentPage = parseInt($('.pageno').last().text()) +1;
        $.listPageServer();
        //$('.pageno:last);
    });

    //이전 버튼 이벤트
    $(document).on('click', '#prev', function () {
        currentPage = parseInt($('.pageno').first().text()) -1;
        $.listPageServer();
    });

    //페이지번호클릭이벤트
    $(document).on('click', '.pageno', function () {
        currentPage = parseInt($(this).text());
        $.listPageServer();
    });
    //검색어 입력 후 검색버튼 클릭이벤트
    $(document).on('click', '#search', function () {
        currentPage = 1;
        $.listPageServer();
    });

    //수정, 삭제, 댓글쓰기, 댓글리스트(제목, 등록). 댓글수정 댓글삭제
    $(document).on('click', '.action', function () {

        target = $(this);

        vname = $(this).attr('name');
        vidx = $(this).attr('idx');

        if (vname == "reply") {
            //alert(vidx + "번째의 댓글을 답니다");
            if (uvo == null) {
                alert("로그인하세요");
                return;
            }
            vcont = $(this).prev().val().trim();

            //저장할 데이터 수집
            reply.bonum = vidx;
            reply.name = uvo.mem_name;
            reply.cont = vcont;

            //전송 - board.js함수 호출
            $.replyWriteServer();

            $(this).prev().val("");
            // $(this).closest('.card-body').find('textarea').val("");

        }else if (vname == "modify") {
            //alert(vidx + "번째의 글을 수정합니다");

            //원글의 내용을 가져온다
            //수정 버튼을 기준으로 공통조상 찾기
            vcard = $(this).parents('.card');
            vtitle = $(vcard).find('a').text().trim();
            vname = $(vcard).find('.wr').text().trim();
            vmail = $(vcard).find('.em').text().trim();
            vcont = $(vcard).find('.p3').html().trim(); //<br> 태그가 포함

            vcont = vcont.replaceAll(/<br>/g, "")

            //모달창에 출력한다
            $('#uwriter').val(vname);
            $('#usubject').val(vtitle);
            $('#umail').val(vmail);
            $('#ucontent').val(vcont);
            $('#unum').val(vidx);

            //이름 수정 불가
            $('#uwriter').prop('readonly', true);

            //모달창 실행
            $('#uModal').modal('show');

        }else if (vname == "delete") {
            //alert(vidx + "번째의 글을 삭제합니다");
            //함수호출
            $.deleteBoardServer()

        }else if (vname == "title") {
            //vidx에 관련된 댓글 리스트 가져오기
            $.replyListServer()
            //조회수 증가하기 - 함수 호출
            vhit = $(this).attr('aria-expanded');
            if(vhit == "true"){
            	$.updateHitServer();
            }
        }else if (vname == "r_modify") {
            // alert(vidx + "댓글을 수정합니다");

            //댓글 수정 폼이 이미 어딘가에 열려있는지 검사
            //만약 열려있다면 body로 수정폼을 반납하고 다시가져와야함
            //반납시에는 원래내용을 rp3에 그대로출력
            if ($('#modifyform').css('display') != "none") {
                //수정폼이 어딘가에 이미 열려있다
                replyReset();
            }

            //버튼(this)기준으로 rp3을 찾는다
            vp3 = $(this).parents('.reply-body').find('.rp3');
            //원래 댓글 내용을 가져온다 = <br>태그가 포함
            //원래 내용을 보관하고 있어야한다 << 취소버튼의 복구기능때문에
            modifycont = vp3.html().trim();
            //<br>태그를 \n으로 변경
            mcont = modifycont.replaceAll(/<br>/g, "\n");
            //수정폼의 textarea에 출력
            $('#modifyform textarea').val(mcont);
            //수정폼을 rp3으로 이동(append) - body의 수정폼은 없어진다
            $(vp3).empty().append($('#modifyform'));
            //수정폼을 보이게한다
            $('#modifyform').show();


        }else if (vname == "r_delete") {
            // alert(vidx + "댓글을 삭제합니다");

            // js함수 호출
            $.deleteReply();
        }
    }); //action이벤트

    //댓글수정 폼 취소버튼 클릭했을때
    $('#btnreset').on('click', function () {

        replyReset();
    });

    replyReset = function () {
        //수정폼을 기준으로 rp3을 찾는다
        vrp3 = $('#modifyform').parent();
        //수정폼을 body로 이동 - append
        $('#modifyform').appendTo($('body'))
        //수정폼을 안보이도록 설정
        $('#modifyform').hide();
        //원래 내용을- modifycont(댓글 수정 클릭시) 다시 rp3으로 출력
        $(vrp3).html(modifycont);
    };

    //댓글수정 폼에서 확인버튼 눌렀을때
    $('#btnok').on('click', function () {
        //새롭게 입력된(수정내용)을 가져온다 - 서버로 전송할 내용(db에 저장) - 엔터가 포함

        modicont = $('#modifyform textarea').val();
        //엔터를 <br>태그로 바꾼다 - 화면에 변경할 내용 - db저장 성공후 success콜백에서 실행
        modiout = modicont.replaceAll(/\n/g, "<br>");
        //수정폼을 기준으로 rp3을 검색한다
        vp3 = $('#modifyform').parent();
        // $(vp3).html(modiout); //화면변경 -> success콜백에서실행한다
        //수정폼을 body로 보낸다 - 안보이도록 설정
        $('body').append($('#modifyform'));
        $('#modifyform').hide();
        //서버로 전송할 data수집
        reply.cont = modicont;
        reply.renum = vidx;
        //js함수 호출
        $.replyUpdateServer();
    });

    //수정 모달창에서 전송 버튼 클릭
    $('#usend').on('click', function () {
        //입력한 모든 값을 가져온다
        udata = $('#uform').serializeJSON();
        console.log(udata);

        $.updateBoardServer();
        //화면 수정 - 서버로 전송 - db수정이 완료된 후에 실행 - 비동기 실행시 success를 콜백

        //모달창에 입력된 내용 지우고
        $('#uform .txt').val("");
        // //모달창 닫기
        $('#uModal').modal('hide');
        //서버로 전송 js함수 호출
    });

    //글쓰기 이벤트
    $('#write').on('click', function(){

        if (uvo == null) {
            alert("로그인 하세요.");
            return;
        }
        //모달 이름에 로그인 한 사람의 이름을 출력
        $('#writer').val(uvo.mem_name);

        //모달이름 수정 할 수 없도록
        $('#writer').prop('readonly', true);

        //모달창 보이기
        $('#wModal').modal('show');
    })
    //글쓰기 입력후 전송버튼 클릭
    $('#send').on('click', function () {
        //입력한 모든 내용 가져오기
        fdata1 = $('#wform').serialize();
        fdata2 = $('#wform').serializeArray();
        fdata3 = $('#wform').serializeJSON();

        console.log(fdata1);
        console.log(fdata2);
        console.log(fdata3);

        //함수 호출
        $.boardWriteServer();
        // location.href="main" 위호출은 비동기호출이기떄문에
        // 성공하든 실패하든 메인으로가버려서 이렇게 쓰면안된다

        //모달창 내용 지우기
        $('#wModal .txt').val("");
        //모달창 닫기
        $('#wModal').modal('hide');

    });//이

})
</script>

</head>
<body>

 <div id="modifyform">
<textarea rows="5" cols="50"></textarea>
<input type="button" value="확인" id="btnok">
<input type="button" value="취소" id="btnreset">
</div>
 
  <br>
  <!--  <input type="button" data-bs-toggle="modal" data-bs-target="#wModal"  id="write" value="글쓰기">  -->
<!--  <input type="button" id="write" value="글쓰기"> -->
  <BR>
  <br>
   <nav class="navbar navbar-expand-sm navbar-dark bg-primary">
  <div class="container-fluid">
  <input type="button" id="write" value="글쓰기">
    <a class="navbar-brand" href="javascript:void(0)">Logo</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="mynavbar">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link" href="javascript:void(0)">Link</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="javascript:void(0)">Link</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="javascript:void(0)">Link</a>
        </li>
      </ul>
      <form class="d-flex">
      
      <select class="form-select" id="stype">
		  <option value="">전체</option>
		  <option value="writer">작성자</option>
		  <option value="subject">제목</option>
		  <option value="content">내용</option>
	  </select>
      
        <input class="form-control me-2" type="text" id="sword" placeholder="Search">
        <button id="search" class="btn btn-primary" type="button">Search</button>
      </form>
    </div>
  </div>
</nav>
   
   

   <!-- 리스트 3개씩을 출력 -->
   <div id="result"></div>
   
   <br>
   <br>
   
   <!-- 페이지정보를 출력  -->
   <div id="pagelist"></div>
   
   
<!------- 글쓰기   The Modal  ------- -->
<div class="modal" id="wModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">게시글 작성하기</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
       
       <form name="wfrom" id="wform">
            <label>이름</label>
            <input type="text" class="txt" id="writer" name="writer"> <br> 
            
            <label>제목</label>
            <input type="text" class="txt" id="subject" name="subject"> <br> 
            
            <label>메일</label>
            <input type="text"  class="txt" id="mail" name="mail"> <br> 
            
            <label>비밀번호</label>
            <input type="password"  class="txt" id="password"   name="password"> <br> 
            
            <label>내용</label>
            <br>
            <textarea rows="5" cols="40"  class="txt" id="content"  name="content"></textarea>
            <br>
            <br>
            <input type="button" value="전송" id="send">
        </form>
       
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>
   
  
  
<!----- 글 수정  The Modal    ----->
<div class="modal" id="uModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">게시글 수정하기</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
       
       <form name="ufrom" id="uform">
       
            <input type="hidden" id="unum" name="num">
            <label>이름</label>
            <input type="text" class="txt" id="uwriter" name="writer"> <br> 
            
            <label>제목</label>
            <input type="text" class="txt" id="usubject" name="subject"> <br> 
            
            <label>메일</label>
            <input type="text"  class="txt" id="umail" name="mail"> <br> 
            
            <label>비밀번호</label>
            <input type="password"  class="txt" id="upassword"   name="password"> <br> 
            
            <label>내용</label>
            <br>
            <textarea rows="5" cols="40"  class="txt" id="ucontent"  name="content"></textarea>
            <br>
            <br>
            <input type="button" value="전송" id="usend">
        </form>
       
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>
  
   
</body>
</html>











