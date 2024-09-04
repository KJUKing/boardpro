package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PageVO;

public interface IBoardDao {
	
	//글리스트 가져오기 
	public List<BoardVO>  selectBoardList(Map<String, Object> map);
	
	//글갯수 구하기 
	public int countBoard(Map<String, Object> map);
	
	//글쓰기 
	
	//글삭제
	
	//글수정 
	
	//조회수 증가
	
	//댓글쓰기 
	
	//댓글 수정 
	
	//댓글 삭제 
	
	//댓글리스트
}
