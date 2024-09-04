package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.mybatis.config.MybatisUtil;

public class BoardDaoImpl implements IBoardDao {
    //싱글돝
	private static IBoardDao dao;
	
	private BoardDaoImpl() { }
	
	public static IBoardDao getDao() {
		if(dao == null) dao = new BoardDaoImpl();
		
		return dao;
	}
	
	
	@Override
	public List<BoardVO> selectBoardList(Map<String, Object> map) {
		//1. 선언 
		List<BoardVO>  list = null;
		SqlSession   sql = null;
		
		//2실행
		try {
			sql = MybatisUtil.getSqlSession();
			list = sql.selectList("board.selectBoardList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sql.commit();
			sql.close();
		}
		
		//3 리턴 
		return list;
	}

	@Override
	public int countBoard(Map<String, Object> map) {
		//1. 선언 
		int  cnt = 0;
		SqlSession   sql = null;
		
		//2실행
		try {
			sql = MybatisUtil.getSqlSession();
			cnt = sql.selectOne("board.countBoard", map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sql.commit();
			sql.close();
		}
		
		//3 리턴 
		return cnt;
	}

}
