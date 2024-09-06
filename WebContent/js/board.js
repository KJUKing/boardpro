
//게시글 삭제
$.deleteBoardServer = function () {
    //$.get(url, data, success, dataType);
    //$.getJSON(url, data, success)

    $.getJSON(
        `${mypath}/deleteBoard.do`,
        {num: vidx},
        function (res) {
            //alert(res.flag)
            //리스트
            $.listPageServer(); //이건 그페이지만

        }
    );
};


//게시글 쓰기
$.boardWriteServer = function () {

    $.ajax({
        url : `${mypath}/insertBoard.do`,
        data : JSON.stringify(fdata3),
        type : 'post',
        contentType : 'application/json',
        success : res => {
            //alert(res.flag);
            //리스트페이지 혹은 메인
            $.listPageServer() //이건 그페이지만
            // location.href=`${mypath}/start/index.jsp`; 이건전체바꾸기
        },
        error : xhr => {
            alert("오류 : " + xhr.status);
        },
        dataType : 'json'
    })
};

//조회수 증가하기
$.updateHitServer = function () {

    $.ajax({
        url : `${mypath}/updateHit.do`,
        data : {num : vidx},
        type : 'get',
        success: res => {
            //alert(res.flag);
            //ok이면 화면을 수정 - target
            //화면수정 - 조회수 부분 검색
            vhi = $(target).parents('.card').find('.hi');

            //조회수의 값을 가져온다
            hivalue = parseInt(vhi.text().trim()) + 1;

            //화면의 조회수 부분을 수정한다
            vhi.text(hivalue);
        },
        error : xhr => {
            alert("오류 : " + xhr.status);
        },
        dataType : 'json'
    })
};


//댓글 리스트 가져오기
$.replyListServer = function () {

    $.ajax({
        url: `${mypath}/selectByReply.do`,
        data: {bonum: vidx}, // {bonum : reply.bonum}
        type: 'get',
        success: res => {
            console.log(res);
            rcode = "";
            //댓글 리스트 res를 출력
            $.each(res, function (i, v) {
                cont = v.cont;
                cont = cont.replaceAll(/\n/g, "<br>");

                rcode += `<div class="reply-body">
            <div class="p12">
                <p class="p1">
                    작성자:<span class="rwr">${v.name}</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    날짜 :<span class="rda">${v.redate}</span>
                </p>
                <p class="p2">`;

                if (uvo != null && uvo.mem_name == v.name) {
                    rcode += `<input idx="${v.renum}" type="button" value="댓글 수정" name="r_modify" class="action">
                <input idx="${v.renum}" type="button" value="댓글 삭제" name="r_delete" class="action">`;
                 }

                rcode += `</p>
             </div>
            <p class="p3">
              ${cont}
            </p>
        </div>`;

            });//$each

            //출력
            //target변수는 제목, 등록을 클릭할때 this를 받은 변수
            $(target).parents('.card').find('.reply-body').remove();
            $(target).parents('.card').find('.card-body').append(rcode);
        },//success
        error: xhr => {
            alert("오류 : " + xhr.status);
        },
        dataType: 'json'
    });
};


//댓글 쓰기 요청 - 응답 - 출력
$.replyWriteServer = function () {

    $.ajax({
        url: `${mypath}/insertReply.do`,
        data: JSON.stringify(reply), // bonum : 19 , name : "김은대, cont : "내용"
        contentType: 'application/json',
        type: 'post',
        success: res => {
            //ok? 댓글 리스트 나오도록
            $.replyListServer();
        },
        error: xhr => {
            alert("오류 : " + xhr.status);
        },
        dataType: 'json'
    })
};


//게시글 리스트 요청 - 응답 - 출력
$.listPageServer = function () {

    stype = $("#stype option:selected").val().trim();
    sword = $('#sword').val().trim();

    datas = {page: currentPage, stype: stype, sword: sword};

    code = '<div class="container mt-3">'
    code += '<h2>Accordion Example</h2>'
    $.ajax({
        url: `${mypath}/BoardList.do`,
        type: 'post',
        data: JSON.stringify(datas),
        contentType: 'application/json',
        success: function (res) {

            $.each(res.datas, function (i, v) {


                //내용을 먼저 가져온다 - 엔터로 저장되어있는 내용을 <br>로 바꾸기위해서
                cont = v.content;
                cont = cont.replaceAll(/\n/g, "<br>")
                code += `<div class="card">
                    <div class="card-header">
                        <a class="btn action" idx="${v.num}" name="title" data-bs-toggle="collapse" href="#collapse${v.num}">
                            ${v.subject}
                        </a>
                    </div>
                    <div id="collapse${v.num}" class="collapse" data-bs-parent="#accordion">
                        <div class="card-body">
                            <div class="p12">
                                <p class="p1">
                                    작성자:<span class="wr">${v.writer}</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    이메일:<span class="em">${v.mail}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    조회수:<span class="hi">${v.hit}</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    날짜 :<span class="da">${v.wdate}</span>
                                </p>
                                <p class ="p2">`

                if (uvo != null && uvo.mem_name == v.writer) {
                    code += `<input idx="${v.num}" type="button"  value="수정" name="modify"  class="action">
                                <input idx="${v.num}" type="button"  value="삭제" name="delete"  class="action">`
                }
                code += `</p>
                            </div>
                            <p class="p3">
                                ${cont}
                            </p>
                            <p class="p4">
                                <textarea rows="" cols="60"></textarea>
                                <input idx="${v.num}" type="button"  value="등록" name="reply"  class="action">
                            </p>

                        </div>
                    </div>
                </div>`

            })//$each
            code += '</div></div>'

            //출력

            $('#result').html(code);

            //페이지 정보 출력 - 함수호출 콜백
            vpage = $.pageList(res.sp, res.ep, res.tp);
            $('#pagelist').html(vpage);


        },
        error: function (xhr) {

            alert("오류 : " + xhr.status);
        },
        dataType: 'json'

    })//ajax


}//$.listPageServer

$.pageList = function (sp, ep, tp) {

    //이전
    pager = "";
    pager += '<ul class="pagination">';
    if (sp > 1) {

        pager += `<li class="page-item"><a id="prev" class="page-link" href="#">Prev</a></li>`;
        //pager += `<li className="page-item"><a id="prev" className="page-link" href="#">Prev</a></li>`;
    }
    //페이지번호
    for (i = sp; i <= ep; i++) {
        if (currentPage == 1) {
            pager += `<li class="page-item active"><a class="page-link pageno" href="#">${i}</a></li>`;
        } else {
            pager += `<li class="page-item"><a class="page-link pageno" href="#">${i}</a></li>`;
        }
    }

    //다음
    if (ep < tp) {
        pager += `<li class="page-item"><a id="next" class="page-link" href="#">Next</a></li>`;
    }
    pager += "</ul>";

    return pager;
};