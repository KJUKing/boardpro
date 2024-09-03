package kr.or.ddit.board.service;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PageVO;

import java.util.List;
import java.util.Map;

public interface IBoardService {

    //페이지 정보 계산
    public PageVO pageInfo(int page, String stype, String sword);
    //글 리스트 가져오기
    public List<BoardVO> selectBoardList(Map<String, Object> map);
    //글 갯수 구하기
    public int countBoard(Map<String, Object> map);

    //글쓰기

    //글삭제

    //글수정

    //조회수 증가

    //댓글쓰기

    //댓글 수정

    //댓글 삭제

    //댓글 리스트
}
