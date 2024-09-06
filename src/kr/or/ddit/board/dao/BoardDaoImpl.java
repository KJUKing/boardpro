package kr.or.ddit.board.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.ReplyVO;
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

	@Override
	public int insertBoard(BoardVO board) {
		//1. 선언
		int  cnt = 0;
		SqlSession   sql = null;

		//2실행
		try {
			sql = MybatisUtil.getSqlSession();
			cnt = sql.insert("board.insertBoard", board);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sql.commit();
			sql.close();
		}

		//3 리턴
		return cnt;
	}

	@Override
	public int deleteBoard(int num) {
		int  cnt = 0;
		SqlSession   sql = null;

		//2실행
		try {
			sql = MybatisUtil.getSqlSession();
			cnt = sql.delete("board.deleteBoard", num);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sql.commit();
			sql.close();
		}

		return cnt;
	}

	@Override
	public int updateBoard(BoardVO vo) {
		//1. 선언
		int  cnt = 0;
		SqlSession   sql = null;

		//2실행
		try {
			sql = MybatisUtil.getSqlSession();
			cnt = sql.update("board.updateBoard", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sql.commit();
			sql.close();
		}

		return cnt;
	}

	@Override
	public int updateHit(int num) {
		//1. 선언
		int  cnt = 0;
		SqlSession   sql = null;

		//2실행
		try {
			sql = MybatisUtil.getSqlSession();
			cnt = sql.update("board.updateHit", num);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sql.commit();
			sql.close();
		}

		return cnt;
	}

	@Override
	public int insertReply(ReplyVO vo) {
		//1. 선언
		int  cnt = 0;
		SqlSession   sql = null;

		//2실행
		try {
			sql = MybatisUtil.getSqlSession();
			cnt = sql.insert("reply.insertReply", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sql.commit();
			sql.close();
		}
		System.out.println("cnt : " + cnt);

		//3 리턴
		return cnt;
	}

	@Override
	public int updateReply(ReplyVO vo) {
		//1. 선언
		int  cnt = 0;
		SqlSession   sql = null;

		//2실행
		try {
			sql = MybatisUtil.getSqlSession();
			cnt = sql.update("reply.updateReply", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sql.commit();
			sql.close();
		}

		return cnt;
	}

	@Override
	public int deleteReply(int num) {
		int  cnt = 0;
		SqlSession   sql = null;

		//2실행
		try {
			sql = MybatisUtil.getSqlSession();
			cnt = sql.delete("reply.deleteReply", num);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sql.commit();
			sql.close();
		}

		return cnt;
	}

	@Override
	public List<ReplyVO> selectByReply(int bonum) {
		//1. 선언
		List<ReplyVO>  list = null;
		SqlSession   sql = null;

		//2실행
		try {
			sql = MybatisUtil.getSqlSession();
			list = sql.selectList("reply.selectByReply", bonum);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sql.commit();
			sql.close();
		}

		//3 리턴
		return list;
	}

}
