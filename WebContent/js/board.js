$.listPageServer = function(){

	stype = $("#stype option:selected").val().trim();
	sword = $('#sword').val().trim();
	
	datas= {page : currentPage, stype :  stype , sword : sword  };
	
	code = '<div class="container mt-3">'
    code += '<h2>Accordion Example</h2>'
    $.ajax({
        url: `${mypath}/BoardList.do`,
		type : 'post',
		data : JSON.stringify(datas),
		contentType : 'application/json',
		success : function(res){

            $.each(res.datas, function (i, v) {


                //내용을 먼저 가져온다 - 엔터로 저장되어있는 내용을 <br>로 바꾸기위해서
                cont = v.content;
                cont = cont.replaceAll(/\n/g, "<br>")
                code += `<div class="card">
                    <div class="card-header">
                        <a class="btn" data-bs-toggle="collapse" href="#collapse${v.num}">
                            ${v.subject}
                        </a>
                    </div>
                    <div id="collapse${v.num}" class="collapse" data-bs-parent="#accordion">
                        <div class="card-body">
                            <div class="p12">
                                <p class="p1">
                                    작성자:<span class="wr">${v.writer}</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    이메일:<span class="em">${v.mail}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    조회수:<span class="hi">0</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
		error : function(xhr){
			
			alert("오류 : " + xhr.status);
		},
		dataType : 'json'
		
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