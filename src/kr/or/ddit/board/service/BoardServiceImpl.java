package kr.or.ddit.board.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PageVO;
import kr.or.ddit.board.vo.ReplyVO;

public class BoardServiceImpl implements IBoardService {
	
	//싱글톤
	private static IBoardService service;
	public static IBoardService getService() {
		if(service == null) service = new BoardServiceImpl();
		
		return service;
	}
	//dao객체가 필요하다
	private IBoardDao dao;
	private BoardServiceImpl() {
		dao  = BoardDaoImpl.getDao();
	}
	
	@Override
	public PageVO pageInfo(int page, String stype, String sword) {
		
		PageVO  pvo = new PageVO();
		
		//전체글갯수 가져오기 
		//map설정 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("stype", stype);
		map.put("sword", sword);
		
		////전체글갯수 가져오기 
		int count = this.countBoard(map);
		
		//전체페이지수 구하기 
		int perList = PageVO.getPerList();
		int totalPage =  (int)Math.ceil(count / (double)perList);

		//마지막페이지7에서 마지막 데이터를 지웠을때
		//Page변수는 7(마지막 페이지) - totalPage = 6으로 바뀜
		if(page > totalPage) page = totalPage;

		//start, end 
		int start = (page-1) * perList + 1;
		int end = start + perList - 1;
		
		if(count < end) end = count;
			
		//startPage, endPage  1 2  / 3 4 / 5 6/7 
		 int perPage = PageVO.getPerPage();
		int startPage =  ((page-1) / perPage * perPage) + 1;
		int endPage = startPage + perPage -1;
		if( endPage > totalPage) endPage = totalPage;
		
		
		pvo.setStart(start);
		pvo.setEnd(end);
		pvo.setStartPage(startPage);
		pvo.setEndPage(endPage);
		pvo.setTotalPage(totalPage);
		
		return pvo;
	}

	@Override
	public List<BoardVO> selectBoardList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return  dao.selectBoardList(map);
	}

	@Override
	public int countBoard(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.countBoard(map);
	}

	@Override
	public int insertBoard(BoardVO board) {
		return dao.insertBoard(board);
	}

	@Override
	public int deleteBoard(int num) {
		return dao.deleteBoard(num);
	}

	@Override
	public int updateBoard(BoardVO vo) {
		return dao.updateBoard(vo);
	}

	@Override
	public int updateHit(int num) {
		return dao.updateHit(num);
	}

	@Override
	public int insertReply(ReplyVO vo) {
		return dao.insertReply(vo);
	}

	@Override
	public int updateReply(ReplyVO vo) {
		return dao.updateReply(vo);
	}

	@Override
	public int deleteReply(int num) {
		return dao.deleteReply(num);
	}

	@Override
	public List<ReplyVO> selectByReply(int bonum) {
		return dao.selectByReply(bonum);
	}

}
