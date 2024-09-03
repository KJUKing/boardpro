package kr.or.ddit.board.service;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PageVO;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardServiceImpl implements IBoardService {

    private static IBoardService service;

    public static IBoardService getInstance() {
        if (service == null) {
            service = new BoardServiceImpl();
        }
        return service;
    }

    //다오 객체갖고오기
    private IBoardDao dao;
    private BoardServiceImpl() {
        dao = BoardDaoImpl.getInstance();
    }


    @Override
    public PageVO pageInfo(int page, String stype, String sword) {

        PageVO pvo = new PageVO();

        //전체 글 갯수 가져오기
        //map설정
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stype", stype);
        map.put("sword", sword);

        //전체글갯수 가져오기
        int count = this.countBoard(map);

        //전체페이지수 구하기
        int perList = PageVO.getPerList();
        int totalPage = (int) Math.ceil(count / (double)perList);

        //start, end
        int start = (page -1) * perList +1 ;
        int end = start + perList -1;

        if (count < end) {
            end = count;
        }

        //StartPage, endPage 1 2 / 3 4 / 5 6
        int perPage = PageVO.getPerPage();

        int startPage = ((page -1) / perPage * perPage) + 1;

        int endPage = startPage + perPage - 1;

        if (endPage > totalPage) {
            endPage = totalPage;
        }

        pvo.setStart(start);
        pvo.setEnd(end);
        pvo.setStartPage(startPage);
        pvo.setEndPage(endPage);
        pvo.setTotalPage(totalPage);

        return pvo;
    }

    @Override
    public List<BoardVO> selectBoardList(Map<String, Object> map) {
        return dao.selectBoardList(map);
    }

    @Override
    public int countBoard(Map<String, Object> map) {
        return dao.countBoard(map);
    }
}
