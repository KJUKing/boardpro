package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import javax.activation.MailcapCommandMap;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PageVO;
import kr.or.ddit.board.vo.ReplyVO;

public interface IBoardService {

	//페이지정보계산
	public PageVO  pageInfo(int page , String stype, String sword);
	
	//글리스트 가져오기 
	public List<BoardVO>  selectBoardList(Map<String, Object> map);
	
	//글갯수 구하기 
	public int countBoard(Map<String, Object> map);
	
	//글쓰기 
	
	//글삭제
	
	//글수정 
	
	//조회수 증가
	
	//댓글쓰기 
	public int insertReply(ReplyVO vo);
	//댓글 수정 
	
	//댓글 삭제 
	
	//댓글리스트
	public List<ReplyVO> selectByReply(int bonum);
	
}
